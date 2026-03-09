import React, { memo } from 'react';

export const InfinityLogo = memo(({ className = "" }: { className?: string }) => (
  <svg className={`w-full h-full will-change-transform ${className}`} viewBox="0 0 200 100" style={{ animation: 'float3d 5s infinite ease-in-out', contain: 'paint' }}>
    <defs>
        <linearGradient id="rainbowGradMain" x1="0%" y1="0%" x2="100%" y2="0%">
            <stop offset="0%" stopColor="#ff0000"><animate attributeName="stop-color" values="#ff0000;#ff7f00;#ffff00;#00ff00;#00f0ff;#bd00ff;#ff00ff;#ff0000" dur="6s" repeatCount="indefinite" /></stop>
            <stop offset="50%" stopColor="#bd00ff"><animate attributeName="stop-color" values="#bd00ff;#00f0ff;#00ff00;#ffff00;#ff7f00;#ff0000;#ff00ff;#bd00ff" dur="6s" repeatCount="indefinite" /></stop>
            <stop offset="100%" stopColor="#00f0ff"><animate attributeName="stop-color" values="#00f0ff;#bd00ff;#ff00ff;#ff0000;#ff7f00;#ffff00;#00ff00;#00f0ff" dur="6s" repeatCount="indefinite" /></stop>
        </linearGradient>
        <filter id="neonGlow">
          <feGaussianBlur stdDeviation="2.5" result="coloredBlur"/>
          <feMerge>
            <feMergeNode in="coloredBlur"/>
            <feMergeNode in="SourceGraphic"/>
          </feMerge>
        </filter>
    </defs>
    <path d="M50 50 C30 30, 20 50, 30 70 C40 90, 60 90, 80 70 C100 50, 120 50, 140 70 C160 90, 180 90, 170 70 C160 50, 140 30, 120 50 C100 70, 80 70, 60 50 C40 30, 30 30, 50 50" fill="none" stroke="url(#rainbowGradMain)" strokeWidth="6" filter="url(#neonGlow)" style={{ strokeDasharray: 500, strokeDashoffset: 500, animation: 'drawInfinity 4s linear infinite' }} />
  </svg>
));

export const HeaderInfinityLogo = memo(({ className = "" }: { className?: string }) => (
  <svg className={`w-full h-full will-change-transform ${className}`} viewBox="0 0 100 50" style={{ contain: 'paint' }}>
    <defs>
        <linearGradient id="rainbowGradHeader" x1="0%" y1="0%" x2="100%" y2="0%">
            <stop offset="0%" stopColor="#ff0000"><animate attributeName="stop-color" values="#ff0000;#ff7f00;#ffff00;#00ff00;#00f0ff;#bd00ff;#ff00ff;#ff0000" dur="6s" repeatCount="indefinite" /></stop>
            <stop offset="50%" stopColor="#bd00ff"><animate attributeName="stop-color" values="#bd00ff;#00f0ff;#00ff00;#ffff00;#ff7f00;#ff0000;#ff00ff;#bd00ff" dur="6s" repeatCount="indefinite" /></stop>
            <stop offset="100%" stopColor="#00f0ff"><animate attributeName="stop-color" values="#00f0ff;#bd00ff;#ff00ff;#ff0000;#ff7f00;#ffff00;#00ff00;#00f0ff" dur="6s" repeatCount="indefinite" /></stop>
        </linearGradient>
    </defs>
    <path d="M25 15 C 5 15, 5 35, 25 35 C 45 35, 55 15, 75 15 C 95 15, 95 35, 75 35 C 55 35, 45 15, 25 15" fill="none" stroke="url(#rainbowGradHeader)" strokeWidth="7" strokeLinecap="round" style={{ strokeDasharray: '60 40', animation: 'dashMoveTitle 2s linear infinite' }}/>
  </svg>
));
