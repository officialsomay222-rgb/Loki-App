import CryptoJS from 'crypto-js';

// The key used for encrypting and decrypting the settings.
// Ideally, this could be derived from an env variable, but for client-side localStorage encryption
// it provides a layer of security over plaintext.
const ENCRYPTION_KEY = 'loki_secure_key_2024';

export const encryptData = (data: string): string => {
  if (!data) return '';
  return CryptoJS.AES.encrypt(data, ENCRYPTION_KEY).toString();
};

export const decryptData = (data: string): string => {
  if (!data) return '';

  try {
    // CryptoJS AES output usually starts with U2FsdGVkX1 (Salted__)
    if (data.startsWith('U2FsdGVkX1')) {
      const bytes = CryptoJS.AES.decrypt(data, ENCRYPTION_KEY);
      const decrypted = bytes.toString(CryptoJS.enc.Utf8);
      // Fallback for cases where decryption yields empty string but the original wasn't empty
      if (!decrypted && data.length > 0) return data;
      return decrypted;
    }
    // Backward compatibility for unencrypted data
    return data;
  } catch (e) {
    // If decryption fails, it might be unencrypted data or corrupted.
    return data;
  }
};
