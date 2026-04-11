import fs from 'fs';
const dbFile = 'src/lib/db.ts';
let code = fs.readFileSync(dbFile, 'utf8');

code = code.replace(
  "export class ChatDatabase extends Dexie {",
  `import { Message } from '../contexts/ChatContext';\n\nexport class ChatDatabase extends Dexie {`
);

code = code.replace(
  "sessions!: Table<ChatSession, string>;",
  "sessions!: Table<Omit<ChatSession, 'messages'>, string>;\n  messages!: Table<Message & { sessionId: string }, string>;"
);

code = code.replace(
  "this.version(1).stores({",
  "this.version(2).stores({\n      sessions: 'id, title, updatedAt',\n      messages: 'id, sessionId, timestamp'\n    }).upgrade(tx => {\n      // Migration: split messages out of sessions\n      return tx.table('sessions').toCollection().modify(session => {\n        if (session.messages && Array.isArray(session.messages)) {\n          const messagesToInsert = session.messages.map(m => ({ ...m, sessionId: session.id }));\n          if (messagesToInsert.length > 0) {\n            tx.table('messages').bulkAdd(messagesToInsert).catch(console.error);\n          }\n          delete session.messages;\n        }\n      });\n    });\n    this.version(1).stores({"
);

code = code.replace(
  "sessions = new DummyTable() as unknown as Table<ChatSession, string>;",
  "sessions = new DummyTable() as unknown as Table<Omit<ChatSession, 'messages'>, string>;\n  messages = new DummyTable() as unknown as Table<Message & { sessionId: string }, string>;"
);

fs.writeFileSync(dbFile, code);
