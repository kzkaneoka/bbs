import React, { useState } from 'react';

import axios from 'axios';
import { useHistory } from 'react-router-dom';

export default function Form() {
  const [form, setForm] = useState({
    title: '',
    description: '',
  });

  const { title, description } = form;

  const history = useHistory();

  const onChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const onSubmit = async (e) => {
    e.preventDefault();
    const data = {
      title,
      description,
    };
    const config = {
      headers: {
        'Content-Type': 'application/json',
        Authorization: `Bearer ${localStorage.getItem('accessToken')}`,
      },
    };
    try {
      const response = await axios.post(
        'http://localhost:8080/api/forms',
        data,
        config
      );
      history.push(`form/${response.data.id}`);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="form-container">
      <h1>Create Form</h1>
      <form onSubmit={onSubmit}>
        <div className="form-group">
          <label htmlFor="title">Title</label>
          <input
            id="title"
            type="text"
            name="title"
            value={title}
            onChange={onChange}
            required
          />
        </div>
        <div className="form-group">
          <label htmlFor="description">Description</label>
          <input
            id="description"
            type="text"
            name="description"
            value={description}
            onChange={onChange}
            required
          />
        </div>
        <input
          type="submit"
          value="Create Form"
          className="btn btn-primary btn-block"
        />
      </form>
    </div>
  );
}
