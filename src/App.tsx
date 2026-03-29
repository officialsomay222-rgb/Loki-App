import { useState, useEffect } from 'react';
import { Capacitor } from '@capacitor/core';

export default function App() {
  const [isOnline, setIsOnline] = useState(navigator.onLine);

  useEffect(() => {
    const handleOnline = () => setIsOnline(true);
    const handleOffline = () => setIsOnline(false);

    window.addEventListener('online', handleOnline);
    window.addEventListener('offline', handleOffline);

    return () => {
      window.removeEventListener('online', handleOnline);
      window.removeEventListener('offline', handleOffline);
    };
  }, []);

  useEffect(() => {
    if (isOnline && Capacitor.isNativePlatform()) {
      // This is the CORRECT way to wrap a site in Capacitor.
      // It will NOT open Chrome anymore because we added 'allowNavigation' to capacitor.config.ts
      window.location.href = 'https://loki-x-prime.vercel.app';
    }
  }, [isOnline]);

  if (!isOnline) {
    return (
      <div className="flex items-center justify-center w-screen h-screen bg-slate-950 text-white p-6 text-center">
        <div className="max-w-md flex flex-col items-center gap-4">
          <div className="w-16 h-16 rounded-full bg-rose-500/10 border border-rose-500/20 flex items-center justify-center mb-2">
            <div className="w-4 h-4 rounded-full bg-rose-500 animate-pulse" />
          </div>
          <h2 className="text-2xl font-bold tracking-tight">Loki Prime X is offline.</h2>
          <p className="text-slate-400">Please connect to the internet to initialize the AI.</p>
        </div>
      </div>
    );
  }

  // If we are in the web browser (like the AI Studio Preview), we show a button instead of an iframe.
  // Vercel blocks iframes by default (X-Frame-Options), which is why your preview was blank!
  return (
    <div className="flex items-center justify-center w-screen h-screen bg-slate-950 text-white p-6 text-center">
      <div className="max-w-md flex flex-col items-center gap-6">
        <div className="w-16 h-16 rounded-full bg-cyan-500/10 border border-cyan-500/20 flex items-center justify-center mb-2">
          <div className="w-8 h-8 rounded-full bg-cyan-500 animate-pulse" />
        </div>
        <h2 className="text-2xl font-bold tracking-tight">App Ready</h2>
        <p className="text-slate-400">
          In the native Android app, this will automatically load the live site seamlessly without opening Chrome.
          <br/><br/>
          Since you are in the web preview, click below to open it (Vercel blocks iframes):
        </p>
        <a 
          href="https://loki-x-prime.vercel.app" 
          target="_blank" 
          rel="noopener noreferrer"
          className="px-6 py-3 bg-cyan-600 hover:bg-cyan-500 text-white rounded-lg font-bold transition-colors"
        >
          Open Loki Prime X
        </a>
      </div>
    </div>
  );
}
