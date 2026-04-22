## 2024-04-16 - Initial Setup\n**Learning:** Just starting to analyze this codebase for UX improvements. Noticed that aria-labels are missing on almost all icon-only buttons across the app.\n**Action:** Add aria-labels to the most critical interactive elements to improve accessibility.

## 2024-05-18 - Missing ARIA Labels on Overlay Buttons
**Learning:** Found a recurring pattern where icon-only buttons in modal/overlay components (like `CommandPalette` and `LiveVoiceOverlay`) lack `aria-label` attributes, making them inaccessible to screen readers. Even when a text label exists visually nearby (e.g., outside the button element), the button itself still needs an accessible name.
**Action:** Always ensure `aria-label` is applied to `<button>` elements that only contain SVG icons, especially in interactive overlays like dialogs or call controls.
## 2024-05-19 - Missing ARIA Labels in Lists
**Learning:** Icon-only buttons within dynamic lists (like tasks, items, or arrays) often lack `aria-label` attributes. Crucially, when these buttons reflect a state (e.g., complete vs. incomplete), the `aria-label` must dynamically convey the action that will happen when clicked, not just the visual state of the icon.
**Action:** Always add dynamic `aria-label` properties (e.g., `aria-label={isComplete ? "Mark incomplete" : "Mark complete"}`) to stateful icon-only buttons in list items.
