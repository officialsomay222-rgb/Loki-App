import http from 'http';

const req = http.request({
  hostname: 'localhost',
  port: 3000,
  path: '/api/chat',
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  }
}, (res) => {
  let data = '';
  res.on('data', (chunk) => {
    data += chunk;
    console.log("Received chunk of size:", chunk.length);
  });
  res.on('end', () => {
    console.log("Response ended. Total size:", data.length);
    console.log("First 200 chars:", data.substring(0, 200));
  });
});

req.on('error', (e) => {
  console.error(`Problem with request: ${e.message}`);
});

req.write(JSON.stringify({
  message: "An apple",
  mode: "image"
}));
req.end();
