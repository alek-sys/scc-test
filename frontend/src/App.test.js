import React from 'react';
import * as api from './fetchCakes';
import { shallow } from 'enzyme';
import App from './App';

describe('When rendering App component', () => {
  let data;

  beforeEach(() => {
    data = Promise.resolve([{ name: 'foo' }, { name: 'bar' }]);
    api.fetchCakes = () => data;
  });

  it('displays a list of cakes', async () => {
    const wrapper = await render(<App/>);
    expect(getDisplayedTitles(wrapper)).toEqual(['foo', 'bar']);
  });

  function getDisplayedTitles(wrapper) {
    return wrapper.find('li').map(e => e.text());
  }

  async function render(component) {
    const wrapper = shallow(component);
    await data;
    wrapper.update();

    return wrapper;
  }
});