import React, { useEffect, useRef } from 'react';

interface AvatarShockwaveProps {
  isActive: boolean;
}

class BlastRing {
  radius: number;
  maxRadius: number;
  thickness: number;
  alpha: number;
  colorStops: string[];
  active: boolean;

  constructor(startX: number, startY: number, colorStops: string[]) {
    this.radius = 0;
    this.maxRadius = 150; // Max expansion radius
    this.thickness = 15;
    this.alpha = 1;
    this.colorStops = colorStops;
    this.active = false;
  }

  reset() {
    this.radius = 0;
    this.alpha = 1;
    this.active = true;
  }

  update() {
    if (!this.active) return;

    // Quick expansion (explosion style)
    this.radius += (this.maxRadius - this.radius) * 0.15;

    // Fade out as it expands
    if (this.radius > this.maxRadius * 0.5) {
      this.alpha -= 0.05;
    }

    // Deactivate when faded
    if (this.alpha <= 0) {
      this.active = false;
      this.alpha = 0;
    }
  }

  draw(ctx: CanvasRenderingContext2D, centerX: number, centerY: number) {
    if (!this.active || this.alpha <= 0) return;

    ctx.save();
    ctx.globalAlpha = this.alpha;

    // Create conical/angular gradient for RGB explosion effect
    // We simulate it using a linear gradient across the ring for performance
    const gradient = ctx.createLinearGradient(
      centerX - this.radius,
      centerY - this.radius,
      centerX + this.radius,
      centerY + this.radius
    );

    gradient.addColorStop(0, this.colorStops[0]);
    gradient.addColorStop(0.5, this.colorStops[1]);
    gradient.addColorStop(1, this.colorStops[2]);

    // Draw the outer ring
    ctx.beginPath();
    ctx.arc(centerX, centerY, this.radius + this.thickness, 0, Math.PI * 2);
    // Draw the inner hole (counter-clockwise to subtract)
    ctx.arc(centerX, centerY, Math.max(0, this.radius), 0, Math.PI * 2, true);
    ctx.closePath();

    ctx.fillStyle = gradient;
    ctx.fill();
    ctx.restore();
  }
}

export const AvatarShockwave: React.FC<AvatarShockwaveProps> = ({ isActive }) => {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const isActiveRef = useRef(isActive);
  const wasActiveRef = useRef(isActive);
  const animationRef = useRef<number | undefined>(undefined);

  // Store our explosion blast ring
  const blastRef = useRef<BlastRing | null>(null);

  // Sync prop to ref and trigger explosion on state change
  useEffect(() => {
    isActiveRef.current = isActive;

    // Trigger blast when active state transitions from false -> true
    if (isActive && !wasActiveRef.current && blastRef.current) {
      blastRef.current.reset();
    }

    wasActiveRef.current = isActive;
  }, [isActive]);

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    const ctx = canvas.getContext('2d');
    if (!ctx) return;

    // Handle high DPI displays for crisp rendering
    const dpr = window.devicePixelRatio || 1;
    const rect = canvas.getBoundingClientRect();

    // Size the canvas backing store
    canvas.width = rect.width * dpr;
    canvas.height = rect.height * dpr;

    // Normalize coordinate system to use css pixels
    ctx.scale(dpr, dpr);

    let width = rect.width;
    let height = rect.height;
    let centerX = width / 2;
    let centerY = height / 2;

    const baseRadius = Math.min(width, height) * 0.35;

    // Initialize the blast ring
    blastRef.current = new BlastRing(centerX, centerY, [
      'rgba(255, 0, 100, 1)',   // Red/Pink
      'rgba(0, 255, 200, 1)',   // Cyan/Green
      'rgba(100, 50, 255, 1)'   // Purple/Blue
    ]);

    // Scale blast size based on canvas size
    blastRef.current.maxRadius = Math.max(width, height) * 0.6;
    blastRef.current.thickness = Math.max(width, height) * 0.15;

    let wasActive = false;

    const render = () => {
      if (blastRef.current && blastRef.current.active) {
        ctx.clearRect(0, 0, width, height);
        // Apply global glow/blend for the explosion
        ctx.globalCompositeOperation = 'screen';

        blastRef.current.update();
        blastRef.current.draw(ctx, centerX, centerY);

        // Reset state
        ctx.globalCompositeOperation = 'source-over';
        wasActive = true;
      } else if (wasActive) {
        ctx.clearRect(0, 0, width, height);
        wasActive = false;
      }

      animationRef.current = requestAnimationFrame(render);
    };

    animationRef.current = requestAnimationFrame(render);

    // Resize handler
    const handleResize = () => {
      const newRect = canvas.getBoundingClientRect();
      canvas.width = newRect.width * dpr;
      canvas.height = newRect.height * dpr;
      ctx.scale(dpr, dpr);

      width = newRect.width;
      height = newRect.height;
      centerX = width / 2;
      centerY = height / 2;

      if (blastRef.current) {
        blastRef.current.maxRadius = Math.max(width, height) * 0.6;
        blastRef.current.thickness = Math.max(width, height) * 0.15;
      }
    };

    window.addEventListener('resize', handleResize);

    return () => {
      if (animationRef.current) {
        cancelAnimationFrame(animationRef.current);
      }
      window.removeEventListener('resize', handleResize);
    };
  }, []); // Empty dependency array so initialization happens only once

  return (
    <div className="absolute -inset-12 z-[0] pointer-events-none" style={{ filter: 'blur(8px)' }}>
      <canvas
        ref={canvasRef}
        className="w-full h-full"
      />
    </div>
  );
};
