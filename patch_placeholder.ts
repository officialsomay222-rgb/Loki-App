import fs from 'fs';

const filePath = 'src/components/MessageBubble.tsx';
let code = fs.readFileSync(filePath, 'utf8');

const oldComponent = `const ImageGenerationPlaceholder = () => {
  const [showCanvas, setShowCanvas] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      setShowCanvas(true);
    }, 1000);
    return () => clearTimeout(timer);
  }, []);

  if (!showCanvas) {
    return (
      <div className="flex items-center gap-1 h-4 sm:h-5">
        <span
          className="w-1 h-1 sm:w-1.5 sm:h-1.5 bg-white rounded-full animate-bounce shadow-[0_0_6px_rgba(255,255,255,0.8)]"
          style={{ animationDelay: "0ms" }}
        ></span>
        <span
          className="w-1 h-1 sm:w-1.5 sm:h-1.5 bg-white rounded-full animate-bounce shadow-[0_0_6px_rgba(255,255,255,0.8)]"
          style={{ animationDelay: "150ms" }}
        ></span>
        <span
          className="w-1 h-1 sm:w-1.5 sm:h-1.5 bg-white rounded-full animate-bounce shadow-[0_0_6px_rgba(255,255,255,0.8)]"
          style={{ animationDelay: "300ms" }}
        ></span>
      </div>
    );
  }

  return (
    <div className="my-4 aspect-square w-full max-w-[512px] mx-auto rounded-lg overflow-hidden border border-white/30 shadow-[0_0_30px_rgba(255,255,255,0.15)] bg-black/60 relative animate-in fade-in zoom-in-95 duration-500">
      {/* Scanning Line */}
      <div className="absolute inset-0 bg-gradient-to-b from-transparent via-white/20 to-transparent h-[200%] -translate-y-full animate-[scanline_3s_linear_infinite]"></div>

      {/* Corner Accents */}
      <div className="absolute top-0 left-0 w-8 h-8 border-t-2 border-l-2 border-white/50 rounded-tl-2xl"></div>
      <div className="absolute top-0 right-0 w-8 h-8 border-t-2 border-r-2 border-white/50 rounded-tr-2xl"></div>
      <div className="absolute bottom-0 left-0 w-8 h-8 border-b-2 border-l-2 border-white/50 rounded-bl-2xl"></div>
      <div className="absolute bottom-0 right-0 w-8 h-8 border-b-2 border-r-2 border-white/50 rounded-br-2xl"></div>

      {/* Center Logo & Text */}
      <div className="absolute inset-0 flex flex-col items-center justify-center z-10">
        <div className="w-32 h-16 mb-6">
          <InfinityLogo />
        </div>
        <div className="text-xs font-mono text-white tracking-[0.4em] uppercase animate-pulse drop-shadow-[0_0_8px_rgba(255,255,255,0.8)]">
          Rendering Canvas
        </div>
      </div>
    </div>
  );
};`;

const newComponent = `const ImageGenerationPlaceholder = () => {
  const canvasRef = useRef<HTMLCanvasElement>(null);
  const [showCanvas, setShowCanvas] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => setShowCanvas(true), 1000);
    return () => clearTimeout(timer);
  }, []);

  useEffect(() => {
    if (!showCanvas) return;
    const canvas = canvasRef.current;
    if (!canvas) return;
    const ctx = canvas.getContext("2d", { alpha: false });
    if (!ctx) return;

    let animationId: number;
    let time = 0;

    // Resize handler
    const resize = () => {
      const rect = canvas.parentElement?.getBoundingClientRect();
      if (rect) {
        canvas.width = rect.width;
        canvas.height = rect.height;
      }
    };
    resize();
    window.addEventListener("resize", resize);

    // Particles
    const particles = Array.from({ length: 40 }).map(() => ({
      x: Math.random() * canvas.width,
      y: Math.random() * canvas.height,
      size: Math.random() * 2 + 1,
      speedX: (Math.random() - 0.5) * 1,
      speedY: (Math.random() - 0.5) * 1,
      color: ['#ff3366', '#ffcc00', '#00ffcc', '#0066ff'][Math.floor(Math.random() * 4)]
    }));

    const render = () => {
      // Clear with dark background
      ctx.fillStyle = "#050505";
      ctx.fillRect(0, 0, canvas.width, canvas.height);

      // Pulse circle in the middle
      const cx = canvas.width / 2;
      const cy = canvas.height / 2;
      const pulseRadius = 50 + Math.sin(time * 0.05) * 10;

      const grad = ctx.createRadialGradient(cx, cy, 0, cx, cy, pulseRadius * 2);
      grad.addColorStop(0, "rgba(255, 255, 255, 0.1)");
      grad.addColorStop(1, "transparent");

      ctx.fillStyle = grad;
      ctx.beginPath();
      ctx.arc(cx, cy, pulseRadius * 2, 0, Math.PI * 2);
      ctx.fill();

      // Draw particles
      particles.forEach(p => {
        p.x += p.speedX;
        p.y += p.speedY;

        if (p.x < 0) p.x = canvas.width;
        if (p.x > canvas.width) p.x = 0;
        if (p.y < 0) p.y = canvas.height;
        if (p.y > canvas.height) p.y = 0;

        ctx.fillStyle = p.color;
        ctx.shadowBlur = 10;
        ctx.shadowColor = p.color;
        ctx.beginPath();
        ctx.arc(p.x, p.y, p.size, 0, Math.PI * 2);
        ctx.fill();
        ctx.shadowBlur = 0; // Reset
      });

      time++;
      animationId = requestAnimationFrame(render);
    };

    render();

    return () => {
      window.removeEventListener("resize", resize);
      cancelAnimationFrame(animationId);
    };
  }, [showCanvas]);

  if (!showCanvas) {
    return (
      <div className="flex items-center gap-1 h-4 sm:h-5">
        <span
          className="w-1 h-1 sm:w-1.5 sm:h-1.5 bg-white rounded-full animate-bounce shadow-[0_0_6px_rgba(255,255,255,0.8)]"
          style={{ animationDelay: "0ms" }}
        ></span>
        <span
          className="w-1 h-1 sm:w-1.5 sm:h-1.5 bg-white rounded-full animate-bounce shadow-[0_0_6px_rgba(255,255,255,0.8)]"
          style={{ animationDelay: "150ms" }}
        ></span>
        <span
          className="w-1 h-1 sm:w-1.5 sm:h-1.5 bg-white rounded-full animate-bounce shadow-[0_0_6px_rgba(255,255,255,0.8)]"
          style={{ animationDelay: "300ms" }}
        ></span>
      </div>
    );
  }

  return (
    <div className="my-4 aspect-square w-full max-w-[512px] mx-auto rounded-lg overflow-hidden border border-white/10 shadow-2xl bg-[#050505] relative gpu-accelerate" style={{ transform: "translateZ(0)" }}>
      <canvas ref={canvasRef} className="absolute inset-0 w-full h-full block" />

      {/* Corner Accents */}
      <div className="absolute top-0 left-0 w-8 h-8 border-t-2 border-l-2 border-white/20 rounded-tl-2xl"></div>
      <div className="absolute top-0 right-0 w-8 h-8 border-t-2 border-r-2 border-white/20 rounded-tr-2xl"></div>
      <div className="absolute bottom-0 left-0 w-8 h-8 border-b-2 border-l-2 border-white/20 rounded-bl-2xl"></div>
      <div className="absolute bottom-0 right-0 w-8 h-8 border-b-2 border-r-2 border-white/20 rounded-br-2xl"></div>

      {/* Center Logo & Text */}
      <div className="absolute inset-0 flex flex-col items-center justify-center z-10 pointer-events-none">
        <div className="w-24 h-12 mb-6 opacity-80">
          <InfinityLogo />
        </div>
        <div className="text-[10px] font-mono text-white/80 tracking-[0.4em] uppercase animate-pulse">
          Rendering Canvas
        </div>
      </div>
    </div>
  );
};`;

code = code.replace(oldComponent, newComponent);
fs.writeFileSync(filePath, code);
