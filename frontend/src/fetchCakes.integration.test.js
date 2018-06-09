import ifetch from 'isomorphic-fetch';
import { fetchCakes } from './fetchCakes';

global.fetch = ifetch;

it('should fetch cakes from API', async () => {
  const cakes = await fetchCakes();
  expect(cakes.length).toBe(4);
});