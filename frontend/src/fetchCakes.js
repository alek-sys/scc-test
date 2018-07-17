const baseUrl = process.env.API_URL || 'https://cakefactory.pcfbeta.io';

export function fetchCakes() {
  return fetch(`${baseUrl}/cake`).then(r => r.json())
}