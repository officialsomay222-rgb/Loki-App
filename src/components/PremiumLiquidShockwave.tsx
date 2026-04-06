import React, { useRef, useEffect } from 'react';

interface Orb {
  x: number;
  y: number;
  radius: number;
  color: string;
  angle: number;
  speed: number;
  distance: number;
}

interface Particle {
  angle: number;
  speed: number;
  distance: number;
  maxDistance: number;
  size: number;
  color: string;
}

interface Ring {
  radius: number;
  speed: number;
  width: number;
}

export const PremiumLiquidShockwave: React.FC = () => {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const animationRef = useRef<number>(0);
  const startTimeRef = useRef<number>(0);

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    const ctx = canvas.getContext('2d', { alpha: true });
    if (!ctx) return;

    // Use a fixed virtual size for the canvas to keep performance high
    const dpr = window.devicePixelRatio || 1;
    const size = 800; // Increased size for an epic, god-level aura
    canvas.width = size * dpr;
    canvas.height = size * dpr;
    ctx.scale(dpr, dpr);

    const centerX = size / 2;
    const centerY = size / 2;

    // Gemini-inspired premium colors
    const colors = [
      'rgba(0, 242, 255, ',   // Cyan
      'rgba(66, 133, 244, ',  // Google Blue
      'rgba(189, 0, 255, ',   // Purple
      'rgba(255, 0, 127, ',   // Magenta/Pink
      'rgba(255, 215, 0, '    // Gold / Plasma core
    ];

    // Denser liquid base: 8 Orbs instead of 4
    const orbs: Orb[] = [];
    for (let i = 0; i < 8; i++) {
      orbs.push({
        x: centerX,
        y: centerY,
        radius: 60 + Math.random() * 80,
        color: colors[i % colors.length],
        angle: (i * Math.PI) / 4 + Math.random(), // Distributed evenly
        speed: 0.01 + Math.random() * 0.03,
        distance: 10 + Math.random() * 50
      });
    }

    // High performance particles (Sparks)
    const particles: Particle[] = [];
    for (let i = 0; i < 80; i++) {
      particles.push({
        angle: Math.random() * Math.PI * 2,
        speed: 3 + Math.random() * 8, // Fast burst
        distance: 0,
        maxDistance: 200 + Math.random() * 300,
        size: 1.5 + Math.random() * 3,
        color: colors[Math.floor(Math.random() * colors.length)]
      });
    }

    // Concentric expanding shockwave rings
    const rings: Ring[] = [
      { radius: 0, speed: 12, width: 4 },
      { radius: -60, speed: 15, width: 2 },
      { radius: -150, speed: 18, width: 1 }
    ];

    const duration = 3500; // 3.5 seconds total

    const animate = (timestamp: number) => {
      if (!startTimeRef.current) startTimeRef.current = timestamp;
      const elapsed = timestamp - startTimeRef.current;
      const progress = Math.min(elapsed / duration, 1);

      // Clear canvas completely to ensure perfect transparency blending
      ctx.clearRect(0, 0, size, size);

      // Easing functions
      const easeOutQuart = 1 - Math.pow(1 - progress, 4);
      const easeOutExpo = progress === 1 ? 1 : 1 - Math.pow(2, -10 * progress);

      // Smooth fade out at the end
      let globalAlpha = 1;
      if (progress > 0.7) {
        globalAlpha = 1 - ((progress - 0.7) / 0.3);
      }

      ctx.globalAlpha = Math.max(0, globalAlpha);

      const currentBaseRadius = 40 + (easeOutExpo * 250);

      // 1. Draw Rotating Light Rays (Aura)
      if (progress < 0.8) {
        ctx.save();
        ctx.translate(centerX, centerY);
        ctx.rotate(elapsed * 0.0005); // Slow rotation for majesty
        const rayAlpha = (1 - (progress / 0.8)) * 0.2;

        ctx.globalCompositeOperation = 'lighter';
        for (let i = 0; i < 12; i++) {
          const angle = (i * Math.PI * 2) / 12;
          const rayGradient = ctx.createLinearGradient(0, 0, Math.cos(angle) * 450, Math.sin(angle) * 450);
          rayGradient.addColorStop(0, `rgba(255, 255, 255, ${rayAlpha})`);
          rayGradient.addColorStop(0.3, `rgba(0, 242, 255, ${rayAlpha * 0.8})`);
          rayGradient.addColorStop(1, `rgba(189, 0, 255, 0)`);

          ctx.beginPath();
          ctx.moveTo(0, 0);
          ctx.lineTo(Math.cos(angle - 0.1) * 450, Math.sin(angle - 0.1) * 450);
          ctx.lineTo(Math.cos(angle + 0.1) * 450, Math.sin(angle + 0.1) * 450);
          ctx.closePath();
          ctx.fillStyle = rayGradient;
          ctx.fill();
        }
        ctx.restore();
      }

      // Use lighter composite operation for the glowing "liquid" blend effect
      ctx.globalCompositeOperation = 'lighter';

      // 2. Draw Liquid Orbs (Plasma Core)
      orbs.forEach(orb => {
        orb.angle += orb.speed;

        // Complex wobble
        const wobbleX = Math.cos(orb.angle) * orb.distance * (1 + easeOutQuart * 1.5);
        const wobbleY = Math.sin(orb.angle * 1.5) * orb.distance * (1 + easeOutQuart * 1.5);

        const currentX = centerX + wobbleX;
        const currentY = centerY + wobbleY;

        const currentRadius = currentBaseRadius * (orb.radius / 100);

        if (currentRadius > 0) {
           const gradient = ctx.createRadialGradient(
             currentX, currentY, 0,
             currentX, currentY, currentRadius
           );

           gradient.addColorStop(0, `${orb.color}0.9)`);
           gradient.addColorStop(0.4, `${orb.color}0.6)`);
           gradient.addColorStop(0.8, `${orb.color}0.15)`);
           gradient.addColorStop(1, `${orb.color}0)`);

           ctx.beginPath();
           ctx.arc(currentX, currentY, currentRadius, 0, Math.PI * 2);
           ctx.fillStyle = gradient;
           ctx.fill();
        }
      });

      // 3. Draw Rings
      rings.forEach(ring => {
        ring.radius += ring.speed * (1 - progress); // Rings slow down over time
        if (ring.radius > 0 && ring.radius < size) {
          const ringAlpha = (1 - (ring.radius / (size / 2))) * 0.9;
          if (ringAlpha > 0) {
            ctx.beginPath();
            ctx.arc(centerX, centerY, ring.radius, 0, Math.PI * 2);
            ctx.lineWidth = ring.width;
            ctx.strokeStyle = `rgba(0, 242, 255, ${ringAlpha})`;
            ctx.stroke();

            // Outer glow for the ring
            ctx.lineWidth = ring.width * 4;
            ctx.strokeStyle = `rgba(189, 0, 255, ${ringAlpha * 0.4})`;
            ctx.stroke();
          }
        }
      });

      // 4. Draw Particles (Sparks)
      particles.forEach(p => {
        p.distance += p.speed * (1 - easeOutQuart);
        if (p.distance > 0 && p.distance < p.maxDistance) {
          const px = centerX + Math.cos(p.angle) * p.distance;
          const py = centerY + Math.sin(p.angle) * p.distance;

          const pAlpha = (1 - (p.distance / p.maxDistance)) * 0.8;

          ctx.beginPath();
          ctx.arc(px, py, p.size, 0, Math.PI * 2);
          ctx.fillStyle = `${p.color}${pAlpha})`;
          ctx.fill();
        }
      });

      // 5. White Hot Core Flash (Supernova burst)
      if (progress < 0.6) {
        const coreAlpha = 1 - (progress / 0.6);
        const coreRadius = currentBaseRadius * 1.3;
        if (coreRadius > 0) {
           ctx.globalCompositeOperation = 'source-over';
           const coreGradient = ctx.createRadialGradient(
              centerX, centerY, 0,
              centerX, centerY, coreRadius
           );
           coreGradient.addColorStop(0, `rgba(255, 255, 255, ${coreAlpha})`);
           coreGradient.addColorStop(0.2, `rgba(255, 255, 255, ${coreAlpha * 0.9})`);
           coreGradient.addColorStop(0.5, `rgba(0, 242, 255, ${coreAlpha * 0.5})`);
           coreGradient.addColorStop(1, `rgba(189, 0, 255, 0)`);

           ctx.beginPath();
           ctx.arc(centerX, centerY, coreRadius, 0, Math.PI * 2);
           ctx.fillStyle = coreGradient;
           ctx.fill();
        }
      }

      if (progress < 1) {
        animationRef.current = requestAnimationFrame(animate);
      }
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
      style={{ width: '1200px', height: '1200px' }} // Expanded wrapper for majestic aura
    >
      <canvas
        ref={canvasRef}
        className="w-full h-full"
        style={{ transform: 'translateZ(0)' }} // Force GPU acceleration
      />
    </div>
  );
};
