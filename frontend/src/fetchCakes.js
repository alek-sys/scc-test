import { baseUrl } from './config';

export function fetchCakes() {
  return fetch(`${baseUrl}/cake`).then(r => r.json())
}