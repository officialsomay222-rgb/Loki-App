## 2024-04-16 - Initial Setup\n**Learning:** Just starting to analyze this codebase for UX improvements. Noticed that aria-labels are missing on almost all icon-only buttons across the app.\n**Action:** Add aria-labels to the most critical interactive elements to improve accessibility.

## 2024-05-18 - Missing ARIA Labels on Overlay Buttons
**Learning:** Found a recurring pattern where icon-only buttons in modal/overlay components (like `CommandPalette` and `LiveVoiceOverlay`) lack `aria-label` attributes, making them inaccessible to screen readers. Even when a text label exists visually nearby (e.g., outside the button element), the button itself still needs an accessible name.
**Action:** Always ensure `aria-label` is applied to `<button>` elements that only contain SVG icons, especially in interactive overlays like dialogs or call controls.

## 2026-04-23 - Added Missing ARIA Labels to Interactive Buttons
**Learning:** Identified a widespread pattern where buttons containing only SVG icons were missing `aria-label` attributes, leaving them inaccessible to screen readers. This is particularly critical in dynamic interfaces like overlays, sidebars, and input controls.
**Action:** Applied `aria-label` attributes with descriptive, action-oriented text to all icon-only interactive buttons across the application (e.g., `CommandPalette`, `ChatInput`, `TimelineItem`, `SettingsModal`, `ErrorBoundary`, `MessageBubble`) to ensure robust accessibility without affecting visual styling or layout.
