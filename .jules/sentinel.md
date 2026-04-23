## 2024-05-24 - [CRITICAL] Prevent Global Cleartext Traffic
**Vulnerability:** Android application explicitly allowed all HTTP traffic globally via `android:usesCleartextTraffic="true"`.
**Learning:** This exposes the app to MITM attacks for all network communication.
**Prevention:** Use a `network_security_config.xml` to globally enforce HTTPS (`cleartextTrafficPermitted="false"`) while maintaining explicit exceptions for local development domains (e.g., `10.0.2.2`, `localhost`).

## 2024-05-25 - [HIGH] Missing API Rate Limiting
**Vulnerability:** The backend Express API had no rate limiting on sensitive endpoints like `/api/chat` and `/api/tts`, leaving them vulnerable to Denial of Service (DoS) and potential API quota exhaustion via automated abuse.
**Learning:** Middleware order is critical for accurate operation. Rate limiters MUST be configured `trust proxy` true for environments like Vercel and should be placed *after* `cors` but before API routes. We also need to use the named export `{ rateLimit } from 'express-rate-limit'` in ES Modules for version 7+.
**Prevention:** Always install and configure `express-rate-limit` on public-facing Node/Express APIs. Ensure `app.set('trust proxy', 1)` is used when deployed behind reverse proxies.
