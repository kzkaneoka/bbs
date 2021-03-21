import React, { useState } from 'react';

import axios from 'axios';
import { useHistory } from 'react-router-dom';

export default function Login() {
  const [user, setUser] = useState({
    username: '',
    password: '',
  });

  const { username, password } = user;

  const history = useHistory();

  const onChange = (e) => setUser({ ...user, [e.target.name]: e.target.value });

  const onSubmit = async (e) => {
    e.preventDefault();
    const data = {
      username,
      password,
    };
    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
    };
    try {
      const response = await axios.post(
        'http://localhost:8080/api/auth/login',
        data,
        config
      );
      localStorage.setItem('accessToken', response.data.accessToken);
      history.push('/');
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="form-container">
      <h1>Log In</h1>
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
          value="Log In"
          className="btn btn-primary btn-block"
        />
      </form>
    </div>
  );
}
