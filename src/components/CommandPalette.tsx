import React, { useState, useEffect, useCallback } from 'react';
import { motion, AnimatePresence } from 'framer-motion';
import { Search, Command, Settings, Plus, X } from 'lucide-react';
import { useChat } from '../contexts/ChatContext';

export const CommandPalette = ({ isOpen, onClose }: { isOpen: boolean; onClose: () => void }) => {
  const [query, setQuery] = useState('');
  const { sessions, createNewSession, setCurrentSessionId } = useChat();

  useEffect(() => {
    const handleKeyDown = (e: KeyboardEvent) => {
      if ((e.metaKey || e.ctrlKey) && e.key === 'k') {
        e.preventDefault();
        isOpen ? onClose() : null; // Toggle logic would be outside
      }
      if (e.key === 'Escape') onClose();
    };
    window.addEventListener('keydown', handleKeyDown);
    return () => window.removeEventListener('keydown', handleKeyDown);
  }, [isOpen, onClose]);

  const filteredSessions = sessions.filter(s => s.title.toLowerCase().includes(query.toLowerCase()));

  return (
    <AnimatePresence>
      {isOpen && (
        <>
          <motion.div
            initial={{ opacity: 0 }}
            animate={{ opacity: 1 }}
            exit={{ opacity: 0 }}
            onClick={onClose}
            className="fixed inset-0 bg-black/50 backdrop-blur-sm z-[100]"
          />
          <motion.div
            initial={{ opacity: 0, scale: 0.95, y: -20 }}
            animate={{ opacity: 1, scale: 1, y: 0 }}
            exit={{ opacity: 0, scale: 0.95, y: -20 }}
            className="fixed top-20 left-1/2 -translate-x-1/2 w-full max-w-lg bg-slate-900 border border-slate-700 rounded-2xl shadow-2xl z-[101] overflow-hidden"
          >
            <div className="flex items-center px-4 border-b border-slate-700">
              <Search className="w-5 h-5 text-slate-400" />
              <input
                autoFocus
                type="text"
                placeholder="Search sessions or commands..."
                className="w-full bg-transparent p-4 text-white outline-none"
                value={query}
                onChange={(e) => setQuery(e.target.value)}
              />
              <button onClick={onClose} className="p-1 hover:bg-slate-800 rounded-md"><X className="w-4 h-4 text-slate-400" /></button>
            </div>
            <div className="max-h-80 overflow-y-auto p-2">
              <button onClick={() => { createNewSession(); onClose(); }} className="w-full flex items-center gap-3 p-3 hover:bg-slate-800 rounded-lg text-white">
                <Plus className="w-4 h-4" /> New Awakening
              </button>
              {filteredSessions.map(session => (
                <button key={session.id} onClick={() => { setCurrentSessionId(session.id); onClose(); }} className="w-full text-left p-3 hover:bg-slate-800 rounded-lg text-slate-300">
                  {session.title}
                </button>
              ))}
            </div>
          </motion.div>
        </>
      )}
    </AnimatePresence>
  );
};
