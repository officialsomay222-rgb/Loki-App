import type { CapacitorConfig } from '@capacitor/cli';

const config: CapacitorConfig = {
  appId: 'com.lokiprimex.app',
  appName: 'Loki Prime X',
  webDir: 'dist',
  server: {
    allowNavigation: ['loki-x-prime.vercel.app']
  },
  plugins: {
    CapacitorHttp: {
      enabled: true,
    },
  },
};

export default config;
