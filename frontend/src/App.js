import React, { Fragment } from 'react';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import Header from './components/header/Header';
import Main from './components/main/Main';
import Form from './components/form/Form';
import Signup from './components/signup/Signup';
import Login from './components/login/Login';
import Logout from './components/logout/Logout';

import './App.css';

export default function App() {
  return (
    <Router>
      <Fragment>
        <Header />
        <Switch>
          <Route exact path="/" component={Main}></Route>
          <Route exact path="/form" component={Form}></Route>
          <Route exact path="/signup" component={Signup}></Route>
          <Route exact path="/login" component={Login}></Route>
          <Route exact path="/logout" component={Logout}></Route>
        </Switch>
      </Fragment>
    </Router>
  );
}
