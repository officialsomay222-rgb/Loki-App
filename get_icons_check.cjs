const fs = require('fs');
const content = fs.readFileSync('app/src/main/java/com/lokixprime/ui/icons/Icons.kt', 'utf8');

const matches = content.match(/val Sparkles:/g);
console.log('Sparkles occurrences:', matches ? matches.length : 0);
