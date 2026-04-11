import fs from 'fs';

const filePath = 'src/contexts/ChatContext.tsx';
let code = fs.readFileSync(filePath, 'utf8');

// Update updateSessionMessage to use messages table
code = code.replace(
  `const updateSessionMessage = async (sessionId: string, messageId: string, content: string, reasoning?: string): Promise<void> => {
  const session = await localDb.sessions.get(sessionId);
  if (session) {
    const msgIndex = session.messages.findIndex(m => m.id === messageId);
    if (msgIndex !== -1) {
      session.messages[msgIndex].content = content;
      if (reasoning !== undefined) {
        session.messages[msgIndex].reasoning = reasoning;
      }
      await localDb.sessions.put(session);
    }
  }
};`,
  `const updateSessionMessage = async (sessionId: string, messageId: string, content: string, reasoning?: string): Promise<void> => {
  const message = await localDb.messages.get(messageId);
  if (message && message.sessionId === sessionId) {
    message.content = content;
    if (reasoning !== undefined) {
      message.reasoning = reasoning;
    }
    await localDb.messages.put(message);
  }
};`
);

// Update load sessions logic
code = code.replace(
  `  // Load sessions using Dexie live query
  const rawSessions = useLiveQuery(
    async () => {
      try {
        return await localDb.sessions.orderBy('updatedAt').reverse().toArray();
      } catch (error) {
        console.warn('Failed to load sessions from IndexedDB (expected in some iframe environments):', error);
        return [];
      }
    },
    [],
    []
  );

  const sessions = rawSessions || [];`,
  `  // Load sessions using Dexie live query
  const rawSessions = useLiveQuery(
    async () => {
      try {
        return await localDb.sessions.orderBy('updatedAt').reverse().toArray();
      } catch (error) {
        console.warn('Failed to load sessions from IndexedDB (expected in some iframe environments):', error);
        return [];
      }
    },
    [],
    []
  );

  const rawMessages = useLiveQuery(
    async () => {
      if (!currentSessionId) return [];
      try {
        return await localDb.messages.where('sessionId').equals(currentSessionId).sortBy('timestamp');
      } catch (error) {
        console.warn('Failed to load messages from IndexedDB:', error);
        return [];
      }
    },
    [currentSessionId],
    []
  );

  const sessions = React.useMemo(() => {
    return (rawSessions || []).map(s => ({
      ...s,
      messages: currentSessionId === s.id ? (rawMessages || []) : []
    })) as ChatSession[];
  }, [rawSessions, rawMessages, currentSessionId]);`
);

// Update createNewSession
code = code.replace(
  `      await localDb.sessions.add({
        id: sessionId,
        title: 'New Awakening',
        messages: [],
        updatedAt: new Date()
      });`,
  `      await localDb.sessions.add({
        id: sessionId,
        title: 'New Awakening',
        updatedAt: new Date()
      });`
);

// Update deleteSession
code = code.replace(
  `  const deleteSession = useCallback(async (id: string) => {
    await localDb.sessions.delete(id);`,
  `  const deleteSession = useCallback(async (id: string) => {
    await localDb.sessions.delete(id);
    await localDb.messages.where('sessionId').equals(id).delete();`
);

// Update deleteMessage
code = code.replace(
  `  const deleteMessage = useCallback(async (sessionId: string, messageId: string) => {
    const session = await localDb.sessions.get(sessionId);
    if (session) {
      session.messages = session.messages.filter(m => m.id !== messageId);
      await localDb.sessions.put(session);
    }
  }, []);`,
  `  const deleteMessage = useCallback(async (sessionId: string, messageId: string) => {
    await localDb.messages.delete(messageId);
  }, []);`
);

// Update clearSessionMessages
code = code.replace(
  `  const clearSessionMessages = useCallback(async (id: string) => {
    const session = await localDb.sessions.get(id);
    if (session) {
      session.messages = [];
      await localDb.sessions.put(session);
    }
  }, []);`,
  `  const clearSessionMessages = useCallback(async (id: string) => {
    await localDb.messages.where('sessionId').equals(id).delete();
  }, []);`
);

// Update clearAllSessions
code = code.replace(
  `  const clearAllSessions = useCallback(async () => {
    await localDb.sessions.clear();
    createNewSession();
  }, [createNewSession]);`,
  `  const clearAllSessions = useCallback(async () => {
    await localDb.sessions.clear();
    await localDb.messages.clear();
    createNewSession();
  }, [createNewSession]);`
);

// Update sendMessage - append to messages
code = code.replace(
  `    session.title = title;
    session.updatedAt = new Date();
    session.messages.push(userMessage);
    await localDb.sessions.put(session);`,
  `    session.title = title;
    session.updatedAt = new Date();
    await localDb.sessions.put(session);
    await localDb.messages.add({ ...userMessage, sessionId: currentSessionId });`
);

code = code.replace(
  `    session.messages.push(modelMessage);
    await localDb.sessions.put(session);`,
  `    await localDb.messages.add({ ...modelMessage, sessionId: currentSessionId });`
);

code = code.replace(
  `      const history = session.messages.slice(0, -1).map(m => ({
        role: m.role,
        content: m.content
      }));`,
  `      const currentMessages = await localDb.messages.where('sessionId').equals(currentSessionId).sortBy('timestamp');
      const history = currentMessages.slice(0, -1).map(m => ({
        role: m.role,
        content: m.content
      }));`
);


fs.writeFileSync(filePath, code);
