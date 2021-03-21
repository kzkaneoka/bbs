import React, { useState } from 'react';

import axios from 'axios';
import { useHistory } from 'react-router-dom';

export default function Signup() {
  const [user, setUser] = useState({
    username: '',
    email: '',
    password: '',
    role: ['user'],
  });

  const { username, email, password, role } = user;

  const history = useHistory();

  const onChange = (e) => setUser({ ...user, [e.target.name]: e.target.value });

  const onSubmit = async (e) => {
    e.preventDefault();
    const data = {
      username,
      email,
      password,
      role,
    };
    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
    };
    try {
      await axios.post('http://localhost:8080/api/auth/signup', data, config);
      history.push('/login');
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="form-container">
      <h1>Sign Up</h1>
      <form onSubmit={onSubmit}>
        <div className="form-group">
          <label htmlFor="username">Username</label>
          <input
            id="username"
            type="text"
            name="username"
            value={username}
            onChange={onChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email</label>
          <input
            id="email"
            type="email"
            name="email"
            value={email}
            onChange={onChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="password">Password</label>
          <input
            id="password"
            type="password"
            name="password"
            value={password}
            onChange={onChange}
            required
            minLength="6"
          />
        </div>
        <input
          type="submit"
          value="Sign Up"
          className="btn btn-primary btn-block"
        />
      </form>
    </div>
  );
}
