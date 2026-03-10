import React from 'react';
import { renderToString } from 'react-dom/server';
import ReactMarkdown from 'react-markdown';

const customUrlTransform = (url: string) => url;

const MarkdownImage = ({node, ...props}: any) => {
  if (!props.src) return null;
  return React.createElement('img', props);
};

const MarkdownComponents = {
  img: MarkdownImage,
};

const longBase64 = 'A'.repeat(2000000); // 2MB
const markdown = `![Generated Image](data:image/jpeg;base64,${longBase64})`;

const html = renderToString(
  React.createElement(ReactMarkdown, {
    components: MarkdownComponents,
    urlTransform: customUrlTransform
  }, markdown)
);

console.log("HTML length:", html.length);
console.log("HTML start:", html.substring(0, 100));
