const baseUrl = process.env.API_URL || '';

export function fetchCakes() {
  return fetch(`${baseUrl}/cake`).then(r => r.json())
}