import React, { createContext, useContext, useState, useEffect, ReactNode, useRef } from 'react';
import { GoogleGenAI } from '@google/genai';
import { useSettings } from './SettingsContext';

export type Message = {
  id: string;
  role: 'user' | 'model';
  content: string;
  timestamp: Date;
  status?: 'pending' | 'sent' | 'error';
};

export type ChatSession = {
  id: string;
  title: string;
  messages: Message[];
  updatedAt: Date;
};

interface ChatState {
  sessions: ChatSession[];
  currentSessionId: string | null;
  isLoading: boolean;
  createNewSession: () => void;
  deleteSession: (id: string) => void;
  clearAllSessions: () => void;
  setCurrentSessionId: (id: string) => void;
  sendMessage: (text: string) => void;
  stopGeneration: () => void;
  renameSession: (id: string, title: string) => void;
}

const ChatContext = createContext<ChatState | undefined>(undefined);

const getAiClient = () => {
  const apiKey = process.env.GEMINI_API_KEY;
  if (!apiKey) {
    console.error("GEMINI_API_KEY is missing!");
    return null;
  }
  return new GoogleGenAI({ apiKey });
};

export const ChatProvider = ({ children }: { children: ReactNode }) => {
  const [sessions, setSessions] = useState<ChatSession[]>([]);
  const [currentSessionId, setCurrentSessionId] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(false);
  const sessionsRef = useRef(sessions);
  const chatInstanceRef = useRef<any>(null);
  const abortControllerRef = useRef<AbortController | null>(null);
  
  const { commanderName, modelMode, systemInstruction, temperature, topP, topK } = useSettings();

  useEffect(() => {
    const savedSessions = localStorage.getItem('loki_chat_sessions');
    if (savedSessions) {
      try {
        const parsed = JSON.parse(savedSessions);
        const formatted = parsed.map((s: any) => ({
          ...s,
          updatedAt: new Date(s.updatedAt),
          messages: s.messages.map((m: any) => ({
            ...m,
            timestamp: new Date(m.timestamp)
          }))
        }));
        setSessions(formatted);
        if (formatted.length > 0) {
          setCurrentSessionId(formatted[0].id);
        } else {
          createNewSession();
        }
      } catch (e) {
        console.error("Failed to parse sessions", e);
        createNewSession();
      }
    } else {
      createNewSession();
    }
  }, []);

  useEffect(() => {
    sessionsRef.current = sessions;
    if (sessions.length > 0) {
      localStorage.setItem('loki_chat_sessions', JSON.stringify(sessions));
    }
  }, [sessions]);

  const getFullSystemInstruction = () => {
    let modeInstruction = '';
    switch(modelMode) {
      case 'fast': modeInstruction = `Provide concise, direct, and fast answers. `; break;
      case 'happy': modeInstruction = `Be extremely cheerful, enthusiastic, and positive! `; break;
      case 'pro': modeInstruction = `Provide detailed, step-by-step reasoning. `; break;
    }
    return `Address the user as ${commanderName}. ${modeInstruction} ${systemInstruction}`;
  };

  useEffect(() => {
    if (currentSessionId) {
      const session = sessionsRef.current.find(s => s.id === currentSessionId);
      if (session) {
        const history = session.messages.map(m => ({
          role: m.role,
          parts: [{ text: m.content }]
        }));

        const ai = getAiClient();
        if (ai) {
          chatInstanceRef.current = ai.chats.create({
            model: 'gemini-3-flash-preview',
            history: history,
            config: {
              systemInstruction: getFullSystemInstruction(),
              temperature,
              topP,
              topK
            }
          });
        }
      }
    }
  }, [currentSessionId, modelMode, commanderName, systemInstruction, temperature, topP, topK]);

  const createNewSession = () => {
    const newSession: ChatSession = {
      id: Date.now().toString(),
      title: 'New Awakening',
      messages: [],
      updatedAt: new Date()
    };
    setSessions(prev => [newSession, ...prev]);
    setCurrentSessionId(newSession.id);
    
    const ai = getAiClient();
    if (ai) {
      chatInstanceRef.current = ai.chats.create({
        model: 'gemini-3-flash-preview',
        config: {
          systemInstruction: getFullSystemInstruction(),
          temperature,
          topP,
          topK
        }
      });
    }
  };

  const deleteSession = (id: string) => {
    const updatedSessions = sessions.filter(s => s.id !== id);
    setSessions(updatedSessions);
    if (currentSessionId === id) {
      if (updatedSessions.length > 0) {
        setCurrentSessionId(updatedSessions[0].id);
      } else {
        createNewSession();
      }
    }
  };

  const clearAllSessions = () => {
    setSessions([]);
    localStorage.removeItem('loki_chat_sessions');
    createNewSession();
  };

  const renameSession = (id: string, title: string) => {
    setSessions(prev => prev.map(s => s.id === id ? { ...s, title } : s));
  };

  const stopGeneration = () => {
    if (abortControllerRef.current) {
      abortControllerRef.current.abort();
      abortControllerRef.current = null;
      setIsLoading(false);
    }
  };

  const sendMessage = async (text: string) => {
    if (!text.trim() || !currentSessionId || isLoading) return;

    const userMessage: Message = {
      id: Date.now().toString(),
      role: 'user',
      content: text.trim(),
      timestamp: new Date(),
      status: 'pending'
    };

    setSessions(prev => prev.map(s => {
      if (s.id === currentSessionId) {
        const title = s.title === 'New Awakening' 
          ? (userMessage.content.length > 30 ? userMessage.content.substring(0, 30) + '...' : userMessage.content)
          : s.title;
          
        return {
          ...s,
          title,
          messages: [...s.messages, userMessage],
          updatedAt: new Date()
        };
      }
      return s;
    }));

    setIsLoading(true);
    abortControllerRef.current = new AbortController();

    try {
      if (!chatInstanceRef.current) {
        const ai = getAiClient();
        if (ai) {
          chatInstanceRef.current = ai.chats.create({
            model: 'gemini-3-flash-preview',
            config: { 
              systemInstruction: getFullSystemInstruction(),
              temperature,
              topP,
              topK
            }
          });
        }
      }

      setSessions(prev => prev.map(s => {
        if (s.id === currentSessionId) {
          const updatedMessages = s.messages.map(m => m.id === userMessage.id ? { ...m, status: 'sent' as const } : m);
          return { ...s, messages: updatedMessages };
        }
        return s;
      }));

      const modelMessageId = (Date.now() + 1).toString();
      setSessions(prev => prev.map(s => {
        if (s.id === currentSessionId) {
          return {
            ...s,
            messages: [...s.messages, {
              id: modelMessageId,
              role: 'model',
              content: '',
              timestamp: new Date()
            }]
          };
        }
        return s;
      }));

      // NOTE: The @google/genai SDK doesn't natively support abort signals in the same way as fetch,
      // but we can simulate stopping by breaking the loop if aborted.
      const responseStream = await chatInstanceRef.current.sendMessageStream({
        message: userMessage.content
      });

      let fullResponse = '';
      for await (const chunk of responseStream) {
        if (abortControllerRef.current?.signal.aborted) {
          break;
        }
        const text = chunk.text || '';
        fullResponse += text;
        
        setSessions(prev => prev.map(s => {
          if (s.id === currentSessionId) {
            const updatedMessages = [...s.messages];
            const lastMsgIndex = updatedMessages.findIndex(m => m.id === modelMessageId);
            if (lastMsgIndex !== -1) {
              updatedMessages[lastMsgIndex] = {
                ...updatedMessages[lastMsgIndex],
                content: fullResponse
              };
            }
            return { ...s, messages: updatedMessages };
          }
          return s;
        }));
      }
    } catch (error: any) {
      if (error.name === 'AbortError') {
        console.log('Generation stopped by user');
      } else {
        console.error("Error sending message:", error);
        setSessions(prev => prev.map(s => {
          if (s.id === currentSessionId) {
            const updatedMessages = s.messages.map(m => m.id === userMessage.id ? { ...m, status: 'error' as const } : m);
            return {
              ...s,
              messages: [...updatedMessages, {
                id: Date.now().toString(),
                role: 'model',
                content: 'SYSTEM ERROR: Connection to core interrupted. Please try again.',
                timestamp: new Date()
              }]
            };
          }
          return s;
        }));
      }
    } finally {
      setIsLoading(false);
      abortControllerRef.current = null;
    }
  };

  return (
    <ChatContext.Provider value={{
      sessions, currentSessionId, isLoading,
      createNewSession, deleteSession, clearAllSessions, setCurrentSessionId, sendMessage, stopGeneration, renameSession
    }}>
      {children}
    </ChatContext.Provider>
  );
};

export const useChat = () => {
  const context = useContext(ChatContext);
  if (context === undefined) {
    throw new Error('useChat must be used within a ChatProvider');
  }
  return context;
};
