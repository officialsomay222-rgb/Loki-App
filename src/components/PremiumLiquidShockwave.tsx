import React, { useRef, useEffect } from 'react';

export const PremiumLiquidShockwave: React.FC = () => {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const animationRef = useRef<number>(0);

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    const ctx = canvas.getContext('2d', { alpha: true });
    if (!ctx) return;

    const dpr = window.devicePixelRatio || 1;
    const size = 800; // slightly smaller to ensure it renders on all screens
    canvas.width = size * dpr;
    canvas.height = size * dpr;
    ctx.scale(dpr, dpr);

    const centerX = size / 2;
    const centerY = size / 2;

    // Use full hex/rgba strings so there is zero chance of string interpolation errors
    const colors = [
      'rgba(0, 242, 255',   // Cyan
      'rgba(189, 0, 255',   // Purple
      'rgba(255, 0, 127',   // Magenta
      'rgba(66, 133, 244',  // Google Blue
      'rgba(255, 215, 0'    // Gold
    ];

    interface Orb {
      color: string;
      radius: number;
      baseX: number;
      baseY: number;
      angle: number;
      speed: number;
      scaleSpeed: number;
      offsetX: number;
      offsetY: number;
    }

    const orbs: Orb[] = [];

    for(let i = 0; i < 30; i++) {
        const color = colors[i % colors.length];
        orbs.push({
            color,
            radius: 100 + Math.random() * 150,
            baseX: centerX + (Math.random() - 0.5) * 100,
            baseY: centerY + (Math.random() - 0.5) * 100,
            angle: Math.random() * Math.PI * 2,
            speed: 0.001 + Math.random() * 0.002,
            scaleSpeed: 0.001 + Math.random() * 0.002,
            offsetX: (Math.random() > 0.5 ? 1 : -1) * (100 + Math.random() * 150),
            offsetY: (Math.random() > 0.5 ? 1 : -1) * (100 + Math.random() * 150),
        });
    }

    const animate = (timestamp: number) => {
      ctx.clearRect(0, 0, size, size);

      ctx.globalCompositeOperation = 'screen';

      orbs.forEach((orb) => {
        const x = orb.baseX + Math.cos(timestamp * orb.speed + orb.angle) * orb.offsetX;
        const y = orb.baseY + Math.sin(timestamp * orb.speed * 1.2 + orb.angle) * orb.offsetY;

        const currentScale = 1 + Math.sin(timestamp * orb.scaleSpeed) * 0.2;
        const currentRadius = orb.radius * currentScale;

        if (currentRadius > 0) {
            try {
                const gradient = ctx.createRadialGradient(x, y, 0, x, y, currentRadius);

                // Extremely safe string construction
                gradient.addColorStop(0, orb.color + ', 0.9)');
                gradient.addColorStop(0.3, orb.color + ', 0.6)');
                gradient.addColorStop(0.7, orb.color + ', 0.2)');
                gradient.addColorStop(1, orb.color + ', 0)');

                ctx.beginPath();
                ctx.arc(x, y, currentRadius, 0, Math.PI * 2);
                ctx.fillStyle = gradient;
                ctx.fill();
            } catch(e) {
                console.error("Canvas draw error", e);
            }
        }
      });

      animationRef.current = requestAnimationFrame(animate);
    };

    animationRef.current = requestAnimationFrame(animate);

    return () => {
      if (animationRef.current) {
        cancelAnimationFrame(animationRef.current);
      }
    };
  }, []);

  return (
    <div
      className="absolute top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 pointer-events-none z-[-1]"
      style={{
          width: '800px',
          height: '800px',
      }}
    >
      {/* Test background just to ensure the div is visible */}
      <div style={{position: 'absolute', inset: 0, backgroundColor: 'rgba(255,255,255,0.05)', borderRadius: '50%'}}></div>
      <canvas
        ref={canvasRef}
        className="w-full h-full"
        style={{
          transform: 'translateZ(0)',
          filter: 'blur(30px) saturate(200%)',
          opacity: 0.9
        }}
      />
    </div>
  );
};
