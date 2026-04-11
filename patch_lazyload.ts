import fs from 'fs';

const filePath = 'src/components/MessageBubble.tsx';
let code = fs.readFileSync(filePath, 'utf8');

const oldImg = `          <img
            {...props}
            className={\`w-full h-full object-cover transition-all duration-700 \${isLoaded ? "opacity-100 scale-100" : "opacity-0 scale-105"}\`}
            referrerPolicy="no-referrer"
            loading="eager"
            onLoad={() => setIsLoaded(true)}
            onError={() => setHasError(true)}
          />`;

const newImg = `          <img
            {...props}
            className={\`w-full h-full object-cover transition-all duration-700 \${isLoaded ? "opacity-100 scale-100" : "opacity-0 scale-105"}\`}
            referrerPolicy="no-referrer"
            loading="lazy"
            decoding="async"
            onLoad={() => setIsLoaded(true)}
            onError={() => setHasError(true)}
          />`;

code = code.replace(oldImg, newImg);
fs.writeFileSync(filePath, code);
