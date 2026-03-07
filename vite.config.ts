import tailwindcss from '@tailwindcss/vite';
import react from '@vitejs/plugin-react';
import path from 'path';
import {defineConfig, loadEnv} from 'vite';
import { VitePWA } from 'vite-plugin-pwa';

export default defineConfig(({mode}) => {
  const env = loadEnv(mode, '.', '');
  return {
    plugins: [
      react(), 
      tailwindcss(),
      VitePWA({
        registerType: 'autoUpdate',
        manifest: {
          name: 'Loki Prime X',
          short_name: 'Loki X',
          description: 'Advanced AI Chat Interface - Awakened Intelligence',
          theme_color: '#08080c',
          background_color: '#08080c',
          display: 'standalone',
          orientation: 'portrait',
          start_url: '/',
          id: '/',
          icons: [
            {
              src: 'https://i.ibb.co/5XjVRg3S/Picsart-26-03-07-20-42-18-789.png',
              sizes: '192x192',
              type: 'image/png',
              purpose: 'any maskable'
            },
            {
              src: 'https://i.ibb.co/5XjVRg3S/Picsart-26-03-07-20-42-18-789.png',
              sizes: '512x512',
              type: 'image/png',
              purpose: 'any maskable'
            },
            {
              src: 'https://i.ibb.co/5XjVRg3S/Picsart-26-03-07-20-42-18-789.png',
              sizes: '1024x1024',
              type: 'image/png',
              purpose: 'any maskable'
            }
          ],
          screenshots: [
            {
              src: 'https://i.ibb.co/5XjVRg3S/Picsart-26-03-07-20-42-18-789.png',
              sizes: '1024x1024',
              type: 'image/png',
              form_factor: 'wide',
              label: 'Loki Prime X Interface'
            },
            {
              src: 'https://i.ibb.co/5XjVRg3S/Picsart-26-03-07-20-42-18-789.png',
              sizes: '1024x1024',
              type: 'image/png',
              form_factor: 'narrow',
              label: 'Loki Prime X Mobile'
            }
          ]
        },
        workbox: {
          globPatterns: ['**/*.{js,css,html,ico,png,svg,jpg}'],
          navigateFallback: '/index.html',
          cleanupOutdatedCaches: true,
          clientsClaim: true,
          skipWaiting: true,
          runtimeCaching: [
            {
              urlPattern: /^https:\/\/i\.ibb\.co\/.*/i,
              handler: 'CacheFirst',
              options: {
                cacheName: 'external-images',
                expiration: {
                  maxEntries: 10,
                  maxAgeSeconds: 60 * 60 * 24 * 30 // 30 days
                },
                cacheableResponse: {
                  statuses: [0, 200]
                }
              }
            }
          ]
        },
        devOptions: {
          enabled: true
        }
      })
    ],
    define: {
      'process.env.GEMINI_API_KEY': JSON.stringify(env.GEMINI_API_KEY),
    },
    resolve: {
      alias: {
        '@': path.resolve(__dirname, '.'),
      },
    },
    server: {
      // HMR is disabled in AI Studio via DISABLE_HMR env var.
      // Do not modifyâfile watching is disabled to prevent flickering during agent edits.
      hmr: process.env.DISABLE_HMR !== 'true',
    },
  };
});
