## 2024-04-16 - Initial Setup\n**Learning:** Just starting to analyze this codebase for UX improvements. Noticed that aria-labels are missing on almost all icon-only buttons across the app.\n**Action:** Add aria-labels to the most critical interactive elements to improve accessibility.

## 2024-05-18 - Missing ARIA Labels on Overlay Buttons
**Learning:** Found a recurring pattern where icon-only buttons in modal/overlay components (like `CommandPalette` and `LiveVoiceOverlay`) lack `aria-label` attributes, making them inaccessible to screen readers. Even when a text label exists visually nearby (e.g., outside the button element), the button itself still needs an accessible name.
**Action:** Always ensure `aria-label` is applied to `<button>` elements that only contain SVG icons, especially in interactive overlays like dialogs or call controls.

## 2026-04-22 - Proper Form Label Association
**Learning:** Found instances in  where `<label>` elements were placed before `<input>` elements but lacked the `htmlFor` attribute to associate them properly. Additionally, an `<input>` in `WelcomeModal.tsx` lacked any label or `aria-label`.
**Action:** Always verify that form inputs either have a properly associated `<label htmlFor="id">` or an `aria-label` attribute if a visual label is hidden or omitted.
## 2026-04-22 - Proper Form Label Association
**Learning:** Found instances in SettingsModal.tsx where label elements were placed before input elements but lacked the htmlFor attribute to associate them properly. Additionally, an input in WelcomeModal.tsx lacked any label or aria-label.
**Action:** Always verify that form inputs either have a properly associated label with htmlFor or an aria-label attribute if a visual label is hidden or omitted.
