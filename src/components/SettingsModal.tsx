import React, { useState, useEffect, useRef } from 'react';
import { X, User, Sliders, Monitor, Zap, Volume2, Type, Layout, Sparkles, Camera, Shield, Database, LogOut, Trash2, Download, ChevronDown } from 'lucide-react';
import { useSettings } from '../contexts/SettingsContext';

interface SettingsModalProps {
  isOpen: boolean;
  onClose: () => void;
  onExportChat: () => void;
  onClearAllChats: () => void;
}

export const SettingsModal: React.FC<SettingsModalProps> = ({ isOpen, onClose, onExportChat, onClearAllChats }) => {
  const {
    theme, setTheme,
    bgStyle, setBgStyle,
    commanderName, setCommanderName,
    avatarUrl, setAvatarUrl,
    modelMode, setModelMode,
    tone, setTone,
    systemInstruction, setSystemInstruction,
    temperature, setTemperature,
    topP, setTopP,
    topK, setTopK,
    enterToSend, setEnterToSend,
    bubbleStyle, setBubbleStyle,
    fontSize, setFontSize,
    fontStyle, setFontStyle,
    soundEnabled, setSoundEnabled,
    messageAnimation, setMessageAnimation,
    autoScroll, setAutoScroll,
    typingSpeed, setTypingSpeed,
    showAvatars, setShowAvatars,
    responseLength, setResponseLength,
    accentColor, setAccentColor,
    messageDensity, setMessageDensity,
    thinkingMode, setThinkingMode,
    searchGrounding, setSearchGrounding,
    imageSize, setImageSize,
    liveAudioEnabled, setLiveAudioEnabled,
    animationSpeed, setAnimationSpeed,
    borderRadius, setBorderRadius,
    textReveal, setTextReveal,
    appWidth, setAppWidth,
    glowIntensity, setGlowIntensity,
    isAwakened, setIsAwakened,
    resetSettings
  } = useSettings();

  const [showClearConfirm, setShowClearConfirm] = useState(false);
  const fileInputRef = useRef<HTMLInputElement>(null);

  if (!isOpen) return null;

  const handleImageUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setAvatarUrl(reader.result as string);
      };
      reader.readAsDataURL(file);
    }
  };

  return (
    <div className="fixed inset-0 z-[100000] flex bg-white dark:bg-[#000000] animate-in fade-in duration-300 font-sans overscroll-none settings-modal">
      <div className="w-full h-[100dvh] flex flex-col overflow-hidden max-w-2xl mx-auto relative">
        
        {/* Header */}
        <div className="flex items-center justify-between p-4 md:p-8 border-b border-slate-100 dark:border-white/10 shrink-0 bg-white/80 dark:bg-[#000000]/80 backdrop-blur-md sticky top-0 z-10">
          <h2 className="text-2xl font-bold text-slate-900 dark:text-white tracking-tight">Settings</h2>
          <button 
            onClick={onClose}
            className="p-2.5 rounded-full text-slate-400 hover:text-slate-900 dark:hover:text-white hover:bg-slate-100 dark:hover:bg-white/10 transition-colors"
          >
            <X className="w-6 h-6" />
          </button>
        </div>
          
          <div className="flex-1 overflow-y-auto p-4 md:p-12 custom-scrollbar min-h-0 overscroll-contain touch-pan-y">
            <div className="max-w-3xl mx-auto space-y-12 pb-24">
              
              {/* Profile Section */}
              <section className="space-y-6">
                <h3 className="text-xl font-bold text-slate-900 dark:text-white">Profile</h3>
                <div className="space-y-6">
                  <h4 className="text-xs font-bold text-slate-500 dark:text-slate-400 uppercase tracking-widest">Profile Picture</h4>
                  <div className="flex items-center gap-6">
                    <div className="relative group cursor-pointer" onClick={() => fileInputRef.current?.click()}>
                      <div className="w-24 h-24 rounded-full overflow-hidden border-2 border-slate-200 dark:border-white/10 group-hover:border-slate-400 dark:group-hover:border-white/30 transition-colors">
                        <img src={avatarUrl} alt="Avatar" className="w-full h-full object-cover group-hover:opacity-50 transition-opacity" />
                      </div>
                      <div className="absolute inset-0 flex items-center justify-center opacity-0 group-hover:opacity-100 transition-opacity rounded-full">
                        <Camera className="w-8 h-8 text-slate-900 dark:text-white drop-shadow-md" />
                      </div>
                    </div>
                    <div>
                      <button 
                        onClick={() => fileInputRef.current?.click()}
                        className="px-4 py-2 bg-slate-900 hover:bg-slate-800 text-white dark:bg-white dark:hover:bg-slate-200 dark:text-black rounded-full text-sm font-semibold transition-colors"
                      >
                        Change Avatar
                      </button>
                      <p className="text-xs text-slate-500 dark:text-slate-400 mt-2">JPG, GIF or PNG. 1MB max.</p>
                      <input 
                        type="file" 
                        ref={fileInputRef} 
                        onChange={handleImageUpload} 
                        accept="image/*" 
                        className="hidden" 
                      />
                    </div>
                  </div>
                </div>
                <div>
                  <label className="block text-sm font-semibold text-slate-900 dark:text-white mb-2">Display Name</label>
                  <input 
                    type="text" 
                    value={commanderName}
                    onChange={(e) => setCommanderName(e.target.value)}
                    className="w-full bg-transparent border-b border-slate-300 dark:border-white/20 px-0 py-2 text-slate-900 dark:text-white focus:outline-none focus:border-slate-900 dark:focus:border-white transition-colors text-lg placeholder-slate-400 dark:placeholder-slate-600"
                    placeholder="Enter your name"
                  />
                </div>
              </section>

              {/* General Section */}
              <section className="space-y-6">
                <h3 className="text-xl font-bold text-slate-900 dark:text-white">General</h3>
                <h4 className="text-xs font-bold text-slate-500 dark:text-slate-400 uppercase tracking-widest">Chat Preferences</h4>
                <div className="flex items-center justify-between py-4 border-b border-slate-100 dark:border-white/5 last:border-0">
                  <div>
                    <div className="text-sm font-semibold text-slate-900 dark:text-white">Enter to Send</div>
                    <div className="text-sm text-slate-500 dark:text-slate-400 mt-0.5">Send message on Enter, Shift+Enter for new line</div>
                  </div>
                  <label className="relative inline-flex items-center cursor-pointer shrink-0 ml-4">
                    <input type="checkbox" className="sr-only peer" checked={enterToSend} onChange={(e) => setEnterToSend(e.target.checked)} />
                    <div className="w-10 h-5 bg-slate-200 peer-focus:outline-none rounded-full peer dark:bg-slate-800 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-slate-300 after:border after:rounded-full after:h-4 after:w-4 after:transition-all dark:border-slate-600 peer-checked:bg-slate-900 dark:peer-checked:bg-white"></div>
                  </label>
                </div>
                <div className="flex items-center justify-between py-4 border-b border-slate-100 dark:border-white/5 last:border-0">
                  <div>
                    <div className="text-sm font-semibold text-slate-900 dark:text-white">Sound Effects</div>
                    <div className="text-sm text-slate-500 dark:text-slate-400 mt-0.5">Play sounds for messages and actions</div>
                  </div>
                  <label className="relative inline-flex items-center cursor-pointer shrink-0 ml-4">
                    <input type="checkbox" className="sr-only peer" checked={soundEnabled} onChange={(e) => setSoundEnabled(e.target.checked)} />
                    <div className="w-10 h-5 bg-slate-200 peer-focus:outline-none rounded-full peer dark:bg-slate-800 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-slate-300 after:border after:rounded-full after:h-4 after:w-4 after:transition-all dark:border-slate-600 peer-checked:bg-slate-900 dark:peer-checked:bg-white"></div>
                  </label>
                </div>
                <div className="flex items-center justify-between py-4 border-b border-slate-100 dark:border-white/5 last:border-0">
                  <div>
                    <div className="text-sm font-semibold text-slate-900 dark:text-white">Auto Scroll</div>
                    <div className="text-sm text-slate-500 dark:text-slate-400 mt-0.5">Automatically scroll to new messages</div>
                  </div>
                  <label className="relative inline-flex items-center cursor-pointer shrink-0 ml-4">
                    <input type="checkbox" className="sr-only peer" checked={autoScroll} onChange={(e) => setAutoScroll(e.target.checked)} />
                    <div className="w-10 h-5 bg-slate-200 peer-focus:outline-none rounded-full peer dark:bg-slate-800 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-slate-300 after:border after:rounded-full after:h-4 after:w-4 after:transition-all dark:border-slate-600 peer-checked:bg-slate-900 dark:peer-checked:bg-white"></div>
                  </label>
                </div>
              </section>

              {/* Model Section */}
              <section className="space-y-6">
                <h3 className="text-xl font-bold text-slate-900 dark:text-white">Model Configuration</h3>
                <div className="pt-4">
                  <label className="block text-sm font-semibold text-slate-900 dark:text-white mb-3">Model Mode</label>
                  <div className="flex gap-3">
                    {['pro', 'fast', 'happy'].map((m) => (
                      <button
                        key={m}
                        onClick={() => setModelMode(m as any)}
                        className={`flex-1 py-3 rounded-lg border text-sm font-bold capitalize transition-all ${modelMode === m ? 'bg-slate-900 border-slate-900 text-white dark:bg-white dark:border-white dark:text-black shadow-md' : 'bg-transparent border-slate-200 dark:border-white/20 text-slate-600 dark:text-slate-400 hover:border-slate-400 dark:hover:border-white/40'}`}
                      >
                        {m}
                      </button>
                    ))}
                  </div>
                </div>
                <div>
                  <label className="block text-sm font-semibold text-slate-900 dark:text-white mb-3">System Instruction</label>
                  <textarea 
                    value={systemInstruction}
                    onChange={(e) => setSystemInstruction(e.target.value)}
                    className="w-full bg-slate-50 dark:bg-[#111] border border-slate-200 dark:border-white/10 rounded-lg px-5 py-4 text-slate-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-slate-900 dark:focus:ring-white transition-all min-h-[160px] resize-y text-base font-mono"
                    placeholder="You are a helpful AI assistant..."
                  />
                </div>
                <div className="space-y-10 pt-6">
                  <div>
                    <label className="block text-sm font-semibold text-slate-900 dark:text-white mb-3">Response Tone</label>
                    <div className="relative">
                      <select 
                        value={tone} 
                        onChange={(e) => setTone(e.target.value as any)}
                        className="w-full bg-slate-50 dark:bg-[#111] border border-slate-200 dark:border-white/10 rounded-lg px-5 py-4 text-slate-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-slate-900 dark:focus:ring-white appearance-none text-base font-medium cursor-pointer capitalize"
                      >
                        <option value="formal">Formal & Professional</option>
                        <option value="casual">Casual & Friendly</option>
                        <option value="humorous">Witty & Humorous</option>
                        <option value="concise">Concise & Direct</option>
                      </select>
                      <ChevronDown className="absolute right-5 top-1/2 -translate-y-1/2 w-5 h-5 text-slate-500 pointer-events-none" />
                    </div>
                  </div>
                  <div>
                    <div className="flex justify-between mb-4">
                      <label className="text-sm font-semibold text-slate-900 dark:text-white">Temperature</label>
                      <span className="text-sm font-mono text-slate-500 bg-slate-100 dark:bg-white/10 px-2 py-1 rounded-md">{temperature}</span>
                    </div>
                    <input 
                      type="range" 
                      min="0" max="2" step="0.1" 
                      value={temperature} 
                      onChange={(e) => setTemperature(parseFloat(e.target.value))}
                      className="w-full h-2 bg-slate-200 dark:bg-slate-800 rounded-lg appearance-none cursor-pointer accent-slate-900 dark:accent-white"
                    />
                  </div>
                  <div>
                    <div className="flex justify-between mb-4">
                      <label className="text-sm font-semibold text-slate-900 dark:text-white">Top P</label>
                      <span className="text-sm font-mono text-slate-500 bg-slate-100 dark:bg-white/10 px-2 py-1 rounded-md">{topP}</span>
                    </div>
                    <input 
                      type="range" 
                      min="0" max="1" step="0.05" 
                      value={topP} 
                      onChange={(e) => setTopP(parseFloat(e.target.value))}
                      className="w-full h-2 bg-slate-200 dark:bg-slate-800 rounded-lg appearance-none cursor-pointer accent-slate-900 dark:accent-white"
                    />
                  </div>
                  <div>
                    <div className="flex justify-between mb-4">
                      <label className="text-sm font-semibold text-slate-900 dark:text-white">Top K</label>
                      <span className="text-sm font-mono text-slate-500 bg-slate-100 dark:bg-white/10 px-2 py-1 rounded-md">{topK}</span>
                    </div>
                    <input 
                      type="range" 
                      min="1" max="100" step="1" 
                      value={topK} 
                      onChange={(e) => setTopK(parseInt(e.target.value))}
                      className="w-full h-2 bg-slate-200 dark:bg-slate-800 rounded-lg appearance-none cursor-pointer accent-slate-900 dark:accent-white"
                    />
                  </div>
                </div>
              </section>


              {/* Appearance Section */}
              <section className="space-y-12 animate-in fade-in slide-in-from-bottom-4 duration-500">
                <h3 className="text-xl font-bold text-slate-900 dark:text-white">Appearance</h3>
                <div className="space-y-6">
                  <h4 className="text-xs font-bold text-slate-500 dark:text-slate-400 uppercase tracking-widest">Theme & Background</h4>
                  <div className="grid grid-cols-1 sm:grid-cols-3 gap-4">
                    {['dark', 'light', 'system'].map((t) => (
                      <button
                        key={t}
                        onClick={() => setTheme(t as any)}
                        className={`p-5 rounded-lg border text-base font-semibold capitalize transition-all ${theme === t ? 'bg-slate-900 border-slate-900 text-white dark:bg-white dark:border-white dark:text-black shadow-lg' : 'bg-transparent border-slate-200 dark:border-white/20 text-slate-600 dark:text-slate-400 hover:border-slate-400 dark:hover:border-white/40'}`}
                      >
                        {t}
                      </button>
                    ))}
                  </div>
                  
                  <div className="pt-4">
                    <label className="block text-sm font-semibold text-slate-900 dark:text-white mb-3">Background Style</label>
                    <div className="grid grid-cols-2 sm:grid-cols-4 gap-3">
                      {['nebula', 'aurora', 'void', 'minimal'].map((s) => (
                        <button
                          key={s}
                          onClick={() => setBgStyle(s as any)}
                          className={`px-4 py-2.5 rounded-lg border text-sm font-medium capitalize transition-all ${bgStyle === s ? 'bg-slate-900 border-slate-900 text-white dark:bg-white dark:border-white dark:text-black shadow-md' : 'bg-transparent border-slate-200 dark:border-white/20 text-slate-600 dark:text-slate-400 hover:border-slate-400 dark:hover:border-white/40'}`}
                        >
                          {s}
                        </button>
                      ))}
                    </div>
                  </div>
                </div>

                <div className="space-y-6">
                  <h4 className="text-xs font-bold text-slate-500 dark:text-slate-400 uppercase tracking-widest">Typography & Layout</h4>
                  <div className="grid grid-cols-1 sm:grid-cols-2 gap-6">
                    <div>
                      <label className="block text-sm font-semibold text-slate-900 dark:text-white mb-3">Font Size</label>
                      <div className="relative">
                        <select 
                          value={fontSize} 
                          onChange={(e) => setFontSize(e.target.value as any)}
                          className="w-full bg-slate-50 dark:bg-[#111] border border-slate-200 dark:border-white/10 rounded-lg px-5 py-4 text-slate-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-slate-900 dark:focus:ring-white appearance-none text-base font-medium cursor-pointer"
                        >
                          <option value="small">Small</option>
                          <option value="medium">Medium</option>
                          <option value="large">Large</option>
                        </select>
                        <ChevronDown className="absolute right-5 top-1/2 -translate-y-1/2 w-5 h-5 text-slate-500 pointer-events-none" />
                      </div>
                    </div>
                    <div>
                      <label className="block text-sm font-semibold text-slate-900 dark:text-white mb-3">Font Style</label>
                      <div className="relative">
                        <select 
                          value={fontStyle} 
                          onChange={(e) => setFontStyle(e.target.value as any)}
                          className="w-full bg-slate-50 dark:bg-[#111] border border-slate-200 dark:border-white/10 rounded-lg px-5 py-4 text-slate-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-slate-900 dark:focus:ring-white appearance-none text-base font-medium cursor-pointer"
                        >
                          <option value="sans">Sans Serif</option>
                          <option value="serif">Serif</option>
                          <option value="mono">Monospace</option>
                        </select>
                        <ChevronDown className="absolute right-5 top-1/2 -translate-y-1/2 w-5 h-5 text-slate-500 pointer-events-none" />
                      </div>
                    </div>
                  </div>

                  <div className="grid grid-cols-1 sm:grid-cols-2 gap-6 pt-2">
                    <div>
                      <label className="block text-sm font-semibold text-slate-900 dark:text-white mb-3">Message Style</label>
                      <div className="relative">
                        <select 
                          value={bubbleStyle} 
                          onChange={(e) => setBubbleStyle(e.target.value as any)}
                          className="w-full bg-slate-50 dark:bg-[#111] border border-slate-200 dark:border-white/10 rounded-lg px-5 py-4 text-slate-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-slate-900 dark:focus:ring-white appearance-none text-base font-medium cursor-pointer"
                        >
                          <option value="glass">Glassmorphism</option>
                          <option value="flat">Flat Minimal</option>
                          <option value="gradient">Vibrant Gradient</option>
                          <option value="brutalist">Brutalist</option>
                        </select>
                        <ChevronDown className="absolute right-5 top-1/2 -translate-y-1/2 w-5 h-5 text-slate-500 pointer-events-none" />
                      </div>
                    </div>
                    <div>
                      <label className="block text-sm font-semibold text-slate-900 dark:text-white mb-3">Border Radius</label>
                      <div className="relative">
                        <select 
                          value={borderRadius} 
                          onChange={(e) => setBorderRadius(e.target.value as any)}
                          className="w-full bg-slate-50 dark:bg-[#111] border border-slate-200 dark:border-white/10 rounded-lg px-5 py-4 text-slate-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-slate-900 dark:focus:ring-white appearance-none text-base font-medium cursor-pointer"
                        >
                          <option value="sharp">Square</option>
                          <option value="rounded">Rounded Rectangle</option>
                          <option value="pill">Pill</option>
                        </select>
                        <ChevronDown className="absolute right-5 top-1/2 -translate-y-1/2 w-5 h-5 text-slate-500 pointer-events-none" />
                      </div>
                    </div>
                  </div>
                </div>
              </section>

              {/* Animations Section */}
              <section className="space-y-6">
                <h3 className="text-xl font-bold text-slate-900 dark:text-white">Animations & Effects</h3>
                <div className="space-y-8">
                  <div>
                    <div className="flex justify-between mb-4">
                      <label className="text-sm font-semibold text-slate-900 dark:text-white">Typing Speed</label>
                      <span className="text-sm font-mono text-slate-500 bg-slate-100 dark:bg-white/10 px-2 py-1 rounded-md">{typingSpeed}ms</span>
                    </div>
                    <input 
                      type="range" 
                      min="0" max="100" step="5" 
                      value={typingSpeed} 
                      onChange={(e) => setTypingSpeed(parseInt(e.target.value))}
                      className="w-full h-2 bg-slate-200 dark:bg-slate-800 rounded-lg appearance-none cursor-pointer accent-slate-900 dark:accent-white"
                    />
                    <div className="flex justify-between text-xs font-medium text-slate-500 mt-3 uppercase tracking-wider">
                      <span>Instant</span>
                      <span>Slow</span>
                    </div>
                  </div>

                  <div className="grid grid-cols-1 sm:grid-cols-2 gap-6 pt-2">
                    <div>
                      <label className="block text-sm font-semibold text-slate-900 dark:text-white mb-3">Animation Speed</label>
                      <div className="relative">
                        <select 
                          value={animationSpeed} 
                          onChange={(e) => setAnimationSpeed(e.target.value as any)}
                          className="w-full bg-slate-50 dark:bg-[#111] border border-slate-200 dark:border-white/10 rounded-lg px-5 py-4 text-slate-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-slate-900 dark:focus:ring-white appearance-none text-base font-medium cursor-pointer"
                        >
                          <option value="fast">Fast</option>
                          <option value="normal">Normal</option>
                          <option value="slow">Slow</option>
                        </select>
                        <ChevronDown className="absolute right-5 top-1/2 -translate-y-1/2 w-5 h-5 text-slate-500 pointer-events-none" />
                      </div>
                    </div>
                    <div>
                      <label className="block text-sm font-semibold text-slate-900 dark:text-white mb-3">Text Reveal Effect</label>
                      <div className="relative">
                        <select 
                          value={textReveal} 
                          onChange={(e) => setTextReveal(e.target.value as any)}
                          className="w-full bg-slate-50 dark:bg-[#111] border border-slate-200 dark:border-white/10 rounded-lg px-5 py-4 text-slate-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-slate-900 dark:focus:ring-white appearance-none text-base font-medium cursor-pointer"
                        >
                          <option value="none">None (Instant)</option>
                          <option value="typewriter">Typewriter</option>
                          <option value="fade">Fade In</option>
                          <option value="slide">Slide Up</option>
                        </select>
                        <ChevronDown className="absolute right-5 top-1/2 -translate-y-1/2 w-5 h-5 text-slate-500 pointer-events-none" />
                      </div>
                    </div>
                  </div>
                </div>
              </section>

              {/* Visuals Section */}
              <section className="space-y-6">
                <h3 className="text-xl font-bold text-slate-900 dark:text-white">Visuals</h3>
                <div className="flex items-center justify-between py-4 border-b border-slate-100 dark:border-white/5 last:border-0">
                  <div>
                    <div className="text-sm font-semibold text-slate-900 dark:text-white">Message Density</div>
                    <div className="text-sm text-slate-500 dark:text-slate-400 mt-0.5">Spacing between messages</div>
                  </div>
                  <div className="relative w-32 shrink-0 ml-4">
                    <select 
                      value={messageDensity} 
                      onChange={(e) => setMessageDensity(e.target.value as any)}
                      className="w-full bg-slate-50 dark:bg-[#111] border border-slate-200 dark:border-white/10 rounded-lg px-4 py-2 text-slate-900 dark:text-white focus:outline-none focus:ring-2 focus:ring-slate-900 dark:focus:ring-white appearance-none text-sm font-medium cursor-pointer capitalize"
                    >
                      <option value="compact">Compact</option>
                      <option value="comfortable">Comfortable</option>
                      <option value="spacious">Spacious</option>
                    </select>
                    <ChevronDown className="absolute right-3 top-1/2 -translate-y-1/2 w-4 h-4 text-slate-500 pointer-events-none" />
                  </div>
                </div>

                <div className="flex items-center justify-between py-4 border-b border-slate-100 dark:border-white/5 last:border-0">
                  <div>
                    <div className="text-sm font-semibold text-slate-900 dark:text-white">Show Avatars</div>
                    <div className="text-sm text-slate-500 dark:text-slate-400 mt-0.5">Display avatars next to messages</div>
                  </div>
                  <label className="relative inline-flex items-center cursor-pointer shrink-0 ml-4">
                    <input type="checkbox" className="sr-only peer" checked={showAvatars} onChange={(e) => setShowAvatars(e.target.checked)} />
                    <div className="w-10 h-5 bg-slate-200 peer-focus:outline-none rounded-full peer dark:bg-slate-800 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-slate-300 after:border after:rounded-full after:h-4 after:w-4 after:transition-all dark:border-slate-600 peer-checked:bg-slate-900 dark:peer-checked:bg-white"></div>
                  </label>
                </div>

                <div className="flex items-center justify-between py-4 border-b border-slate-100 dark:border-white/5 last:border-0">
                  <div>
                    <div className="text-sm font-semibold text-slate-900 dark:text-white">Message Animation</div>
                    <div className="text-sm text-slate-500 dark:text-slate-400 mt-0.5">Animate messages as they appear</div>
                  </div>
                  <label className="relative inline-flex items-center cursor-pointer shrink-0 ml-4">
                    <input type="checkbox" className="sr-only peer" checked={messageAnimation} onChange={(e) => setMessageAnimation(e.target.checked)} />
                    <div className="w-10 h-5 bg-slate-200 peer-focus:outline-none rounded-full peer dark:bg-slate-800 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-slate-300 after:border after:rounded-full after:h-4 after:w-4 after:transition-all dark:border-slate-600 peer-checked:bg-slate-900 dark:peer-checked:bg-white"></div>
                  </label>
                </div>
              </section>



              {/* Experimental Section */}
              <section className="space-y-6">
                <h3 className="text-xl font-bold text-slate-900 dark:text-white">Experimental</h3>
                <div className="flex items-center justify-between py-4 border-b border-slate-100 dark:border-white/5 last:border-0">
                  <div>
                    <div className="text-sm font-semibold text-slate-900 dark:text-white flex items-center gap-2">
                      Awakened Mode
                      <span className="text-[10px] bg-purple-500/10 text-purple-500 px-1.5 py-0.5 rounded border border-purple-500/20 uppercase tracking-wider font-bold">Experimental</span>
                    </div>
                    <div className="text-sm text-slate-500 dark:text-slate-400 mt-0.5">Enable experimental AI personality traits</div>
                  </div>
                  <label className="relative inline-flex items-center cursor-pointer shrink-0 ml-4">
                    <input type="checkbox" className="sr-only peer" checked={isAwakened} onChange={(e) => setIsAwakened(e.target.checked)} />
                    <div className="w-10 h-5 bg-slate-200 peer-focus:outline-none rounded-full peer dark:bg-slate-800 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-slate-300 after:border after:rounded-full after:h-4 after:w-4 after:transition-all dark:border-slate-600 peer-checked:bg-purple-600 dark:peer-checked:bg-purple-500"></div>
                  </label>
                </div>
              </section>

              {/* Data & Privacy Section */}
              <section className="space-y-6">
                <h3 className="text-xl font-bold text-slate-900 dark:text-white">Data & Privacy</h3>
                <div className="space-y-4">
                  <div className="flex items-center justify-between p-5 rounded-lg border border-slate-200 dark:border-white/10 hover:border-slate-300 dark:hover:border-white/20 transition-colors">
                    <div className="flex items-center gap-4">
                      <div className="p-3 bg-slate-100 dark:bg-white/5 rounded-full">
                        <Download className="w-5 h-5 text-slate-700 dark:text-slate-300" />
                      </div>
                      <div>
                        <h5 className="text-base font-semibold text-slate-900 dark:text-white">Export Data</h5>
                        <p className="text-sm text-slate-500 dark:text-slate-400">Download your current chat history as JSON</p>
                      </div>
                    </div>
                    <button 
                      onClick={onExportChat}
                      className="px-4 py-2 bg-slate-900 hover:bg-slate-800 text-white dark:bg-white dark:hover:bg-slate-200 dark:text-black rounded-full text-sm font-semibold transition-colors"
                    >
                      Export
                    </button>
                  </div>

                  <div className="flex items-center justify-between p-5 rounded-lg border border-red-200 dark:border-red-900/30 hover:border-red-300 dark:hover:border-red-900/50 transition-colors bg-red-50/50 dark:bg-red-950/10">
                    <div className="flex items-center gap-4">
                      <div className="p-3 bg-red-100 dark:bg-red-900/30 rounded-full">
                        <Trash2 className="w-5 h-5 text-red-600 dark:text-red-400" />
                      </div>
                      <div>
                        <h5 className="text-base font-semibold text-red-600 dark:text-red-400">Clear History</h5>
                        <p className="text-sm text-red-500/80 dark:text-red-400/80">Permanently delete all chat sessions</p>
                      </div>
                    </div>
                    {!showClearConfirm ? (
                      <button 
                        onClick={() => setShowClearConfirm(true)}
                        className="px-4 py-2 bg-[#b3261e] hover:bg-[#8c1d18] text-white rounded-full text-sm font-semibold transition-colors"
                      >
                        Clear
                      </button>
                    ) : (
                      <div className="flex items-center gap-2">
                        <button 
                          onClick={() => setShowClearConfirm(false)}
                          className="px-3 py-1.5 text-xs font-bold text-slate-500 hover:text-slate-900 dark:hover:text-white transition-colors"
                        >
                          Cancel
                        </button>
                        <button 
                          onClick={() => {
                            onClearAllChats();
                            onClose();
                            setShowClearConfirm(false);
                          }}
                          className="px-4 py-2 bg-[#b3261e] hover:bg-[#8c1d18] text-white rounded-full text-sm font-bold transition-colors shadow-lg shadow-red-500/20"
                        >
                          Confirm
                        </button>
                      </div>
                    )}
                  </div>
                </div>
                <div className="space-y-4">
                  <div className="flex items-center justify-between py-4 border-b border-slate-100 dark:border-white/5 last:border-0">
                    <div>
                      <div className="text-sm font-semibold text-slate-900 dark:text-white">Local Storage Only</div>
                      <div className="text-sm text-slate-500 dark:text-slate-400 mt-0.5">Your data never leaves your browser</div>
                    </div>
                    <div className="px-3 py-1 bg-emerald-100 dark:bg-emerald-900/30 text-emerald-600 dark:text-emerald-400 text-xs font-bold rounded-full">Active</div>
                  </div>
                  <div className="flex items-center justify-between py-4 border-b border-slate-100 dark:border-white/5 last:border-0">
                    <div>
                      <div className="text-sm font-semibold text-slate-900 dark:text-white">Anonymous Usage Data</div>
                      <div className="text-sm text-slate-500 dark:text-slate-400 mt-0.5">Help improve Loki by sharing anonymous metrics</div>
                    </div>
                    <label className="relative inline-flex items-center cursor-pointer shrink-0 ml-4">
                      <input type="checkbox" className="sr-only peer" checked={true} readOnly />
                      <div className="w-10 h-5 bg-slate-200 peer-focus:outline-none rounded-full peer dark:bg-slate-800 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-slate-300 after:border after:rounded-full after:h-4 after:w-4 after:transition-all dark:border-slate-600 peer-checked:bg-slate-900 dark:peer-checked:bg-white"></div>
                    </label>
                  </div>
                </div>
              </section>

              {/* Terms & Conditions Section */}
              <section className="space-y-6">
                <h3 className="text-xl font-bold text-slate-900 dark:text-white">Terms & Conditions</h3>
                <div className="p-5 rounded-lg border border-slate-200 dark:border-white/10 text-sm text-slate-500 dark:text-slate-400">
                  <p className="mb-4">By using this application, you agree to our terms of service and privacy policy. We are committed to protecting your data and providing a secure experience.</p>
                  <a href="#" className="text-slate-900 dark:text-white font-semibold hover:underline">Read full Terms & Conditions</a>
                </div>
              </section>

              {/* System Section */}
              <section className="space-y-6">
                <h3 className="text-xl font-bold text-slate-900 dark:text-white">System</h3>
                <div className="flex items-center justify-between p-5 rounded-lg border border-slate-200 dark:border-white/10 hover:border-slate-300 dark:hover:border-white/20 transition-colors">
                    <div className="flex items-center gap-4">
                      <div className="p-3 bg-slate-100 dark:bg-white/5 rounded-full">
                        <LogOut className="w-5 h-5 text-slate-700 dark:text-slate-300" />
                      </div>
                      <div>
                        <h5 className="text-base font-semibold text-slate-900 dark:text-white">Reset Settings</h5>
                        <p className="text-sm text-slate-500 dark:text-slate-400">Restore all preferences to default</p>
                      </div>
                    </div>
                    <button 
                      onClick={resetSettings}
                      className="px-4 py-2 bg-slate-200 hover:bg-slate-300 text-slate-900 dark:bg-white/10 dark:hover:bg-white/20 dark:text-white rounded-full text-sm font-semibold transition-colors"
                    >
                      Reset
                    </button>
                  </div>
              </section>

            </div>
          </div>
        </div>
      </div>
  );
};
