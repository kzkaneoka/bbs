import React, { Fragment } from 'react';

import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';

import Header from './components/header/Header';
import Main from './components/main/Main';
import CreateForm from './components/form/CreateForm';
import FormDetail from './components/form/FormDetail';
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
          <Route exact path="/form" component={CreateForm}></Route>
          <Route path="/forms/:formId" component={FormDetail}></Route>
          <Route exact path="/signup" component={Signup}></Route>
          <Route exact path="/login" component={Login}></Route>
          <Route exact path="/logout" component={Logout}></Route>
        </Switch>
      </Fragment>
    </Router>
  );
}
