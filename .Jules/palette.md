## 2026-04-23 - Adding ARIA labels to Icon-Only Buttons
**Learning:** For interactive icon-only components (like checkboxes and trash cans in a task list), it's important to provide `aria-label` attributes to ensure screen readers can announce the action. Specifically for toggle buttons, dynamically updating the label (e.g., 'Mark task as complete' vs 'Mark task as incomplete') provides clear state context to the user.
**Action:** Always check icon-only buttons for missing `aria-label`s and verify if dynamic labels are needed for toggle states.
