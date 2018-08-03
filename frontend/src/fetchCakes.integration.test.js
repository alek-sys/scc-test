import {fetchCakes} from './fetchCakes';
import {restoreFetch, setFetch} from "./stubRunner";
import { runStubs } from 'spring-cloud-contracts-stub-runner';


describe('Cakes service', () => {
    let stubRunnerInstance;
    const port = Math.floor(Math.random() * 1000 + 9000);

    beforeAll(async () => {
        setFetch();
        stubRunnerInstance = await runStubs(`com.example:cakefactory:+:${port}`);
    });

    afterAll(() => {
        restoreFetch();
        stubRunnerInstance.kill();
    });

    it('should fetch cakes from API', async () => {
        const cakes = await fetchCakes(`http://localhost:${port}`);
        expect(cakes.length).toBe(3);
    });
});