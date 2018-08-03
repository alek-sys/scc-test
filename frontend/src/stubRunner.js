const isomorphicFetch = require('isomorphic-fetch');
const originalFetch = global.fetch;

export function setFetch() {
    global.fetch = isomorphicFetch;
}

export function restoreFetch() {
    global.fetch = originalFetch;
}