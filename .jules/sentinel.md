## 2024-05-24 - [CRITICAL] Prevent Global Cleartext Traffic
**Vulnerability:** Android application explicitly allowed all HTTP traffic globally via `android:usesCleartextTraffic="true"`.
**Learning:** This exposes the app to MITM attacks for all network communication.
**Prevention:** Use a `network_security_config.xml` to globally enforce HTTPS (`cleartextTrafficPermitted="false"`) while maintaining explicit exceptions for local development domains (e.g., `10.0.2.2`, `localhost`).
