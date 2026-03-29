import { useState, useEffect } from 'react';

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

  return (
    <iframe
      src="https://loki-x-prime.vercel.app"
      style={{ border: 'none', width: '100vw', height: '100vh', display: 'block' }}
      title="Loki Prime X"
      allow="camera; microphone; geolocation; display-capture; autoplay; clipboard-write"
    />
  );
}
