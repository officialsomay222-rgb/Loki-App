## 2025-04-16 - Prevent Path Injection in File Creation
**Vulnerability:** Constructing a file extension based directly on a user-provided `mimeType` in `api/index.ts` can open up path traversal and server-side logic exploitation risks.
**Learning:** Even trivial string matching or interpolation using user inputs (like `mimeType?.includes('mp4') ? 'mp4' : ...`) into system paths introduces unnecessary complexity and potential vulnerabilities if the logic is flawed or the parameter is heavily manipulated.
**Prevention:** Hardcode the extension or enforce an extremely strict validation whitelist independent of the raw user input when generating file names dynamically on the server.

## 2024-05-18 - Express Rate Limit & CORS Ordering
**Vulnerability:** Application layer DoS vulnerability due to lack of API rate limiting on sensitive endpoints like `/api/chat` and `/api/transcribe`.
**Learning:** Adding `express-rate-limit` requires careful middleware ordering. If applied *before* `cors()`, a 429 response will lack `Access-Control-Allow-Origin` headers, causing the frontend to throw a generic CORS error rather than handling the rate limit gracefully. Furthermore, `express-rate-limit` v7+ requires named imports (`import { rateLimit } from "express-rate-limit"`), and using default imports will crash the server. Finally, `app.set('trust proxy', 1)` is crucial for reverse proxies like Vercel/Cloud Run to avoid global rate limiting based on the proxy IP.
**Prevention:** Always place CORS middleware before rate limiters in Express. Use named imports for modern ES modules, and ensure proxy trust is configured when deploying to PaaS platforms.
