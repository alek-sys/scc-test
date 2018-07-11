const baseUrl = process.env.API_URL || 'http://localhost:8080';

export function fetchCakes() {
  return fetch(`${baseUrl}/cake`).then(r => r.json())
}