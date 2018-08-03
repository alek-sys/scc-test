import { baseUrl } from './config';

export function fetchCakes(base = baseUrl) {
  return fetch(`${base}/cake`).then(r => r.json())
}