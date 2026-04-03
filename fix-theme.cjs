const fs = require('fs');
const path = require('path');

const filePath = path.join(__dirname, 'src/components/SettingsModal.tsx');
let content = fs.readFileSync(filePath, 'utf8');

// First, revert any previous replacements if we run this multiple times (we haven't yet)
const replacements = [
  { from: /bg-\[\#0a0a0a\]/g, to: 'bg-white dark:bg-[#0a0a0a]' },
  { from: /bg-\[\#161616\]\/50/g, to: 'bg-slate-50/50 dark:bg-[#161616]/50' },
  { from: /bg-\[\#161616\]/g, to: 'bg-slate-50 dark:bg-[#161616]' },
  { from: /bg-\[\#222\]/g, to: 'bg-slate-100 dark:bg-[#222]' },
  { from: /bg-\[\#333\]/g, to: 'bg-slate-200 dark:bg-[#333]' },
  { from: /border-white\/10/g, to: 'border-slate-200 dark:border-white/10' },
  { from: /border-white\/5/g, to: 'border-slate-100 dark:border-white/5' },
  { from: /text-white/g, to: 'text-slate-900 dark:text-white' },
  { from: /text-\[\#717171\]/g, to: 'text-slate-500 dark:text-[#717171]' },
  { from: /text-\[\#444\]/g, to: 'text-slate-400 dark:text-[#444]' },
  { from: /shadow-white\/5/g, to: 'shadow-black/5 dark:shadow-white/5' },
  { from: /bg-white\/5/g, to: 'bg-slate-100 dark:bg-white/5' },
  { from: /hover:bg-white\/5/g, to: 'hover:bg-slate-100 dark:hover:bg-white/5' },
  { from: /hover:bg-white\/10/g, to: 'hover:bg-slate-200 dark:hover:bg-white/10' },
  { from: /ring-white\/5/g, to: 'ring-slate-200 dark:ring-white/5' },
  { from: /peer-checked:bg-white/g, to: 'peer-checked:bg-slate-900 dark:peer-checked:bg-white' },
  { from: /peer-checked:after:bg-black/g, to: 'peer-checked:after:bg-white dark:peer-checked:after:bg-black' },
  { from: /accent-white/g, to: 'accent-slate-900 dark:accent-white' },
  { from: /from-\[\#1d1d1d\] to-\[\#161616\]/g, to: 'from-white to-slate-50 dark:from-[#1d1d1d] dark:to-[#161616]' },
  
  // Specific buttons that were "bg-white text-black"
  // Note: text-white was already replaced above, so "bg-white text-black" is still "bg-white text-black"
  { from: /bg-white text-black/g, to: 'bg-slate-900 dark:bg-white text-white dark:text-black' },
];

for (const {from, to} of replacements) {
  content = content.replace(from, to);
}

// Some manual fixes for the toggle switch:
// The toggle switch has `after:bg-white`. In light mode, it should be white.
// Wait, `text-white` replacement might have caught `text-white` inside `bg-white text-black`? No, `text-white` is a separate class.
// Let's check `bg-white text-black`. If `text-white` was replaced, it wouldn't affect `text-black`.

fs.writeFileSync(filePath, content, 'utf8');
console.log('Theme classes updated.');
