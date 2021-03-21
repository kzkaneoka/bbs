import React, { Fragment } from 'react';

import { Link } from 'react-router-dom';

export default function Header() {
  return (
    <div className="header bg-primary">
      <h1>
        <Link to="/">
          <i>BBS</i>
        </Link>
      </h1>
      <ul>
        <Fragment>
          <li>
            <Link to="/signup">Sign Up</Link>
          </li>

          <li>
            <Link to="/login">Log In</Link>
          </li>

          <li>
            <Link to="/form">Create Form</Link>
          </li>

          <li>
            <Link to="/logout">Log Out</Link>
          </li>
        </Fragment>
      </ul>
    </div>
  );
}
