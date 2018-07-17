import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import { fetchCakes } from './fetchCakes';

class App extends Component {
  constructor(props) {
    super(props);

    this.state = {
      cakes: []
    }
  }

  componentDidMount() {
    fetchCakes().then(cakes => {
      this.setState({ cakes: cakes });
    }, err => {
      console.error(err);
    })
  }

  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo"/>
          <h1 className="App-title">Welcome to React</h1>
        </header>
        <ul>
          {this.state.cakes.map(c => <li key={c.name}>{c.name}</li>)}
        </ul>
      </div>
    );
  }
}

export default App;
