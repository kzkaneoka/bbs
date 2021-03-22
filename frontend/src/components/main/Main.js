import React, { Fragment, useEffect, useState } from 'react';

import axios from 'axios';
import { Link } from 'react-router-dom';

import Card from '../card/Card';

export default function Main() {
  const [forms, setForms] = useState([]);

  const config = {
    headers: {
      'Content-Type': 'application/json',
    },
  };

  const fetchData = async () => {
    try {
      const response = await axios.get(
        'http://localhost:8080/api/forms',
        config
      );
      setForms(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  if (
    forms !== null &&
    forms.length === 0 &&
    localStorage.getItem('accessToken') === null
  ) {
    return <h2>Please sign up / log in and create form.</h2>;
  } else if (forms !== null && forms.length === 0) {
    return <h2>Please create form.</h2>;
  }

  return (
    <Fragment>
      <div className="card-container">
        {Object.keys(forms).map((key) => (
          <Link to={`forms/${forms[key].id}`} key={key}>
            <Card form={forms[key]} />
          </Link>
        ))}
      </div>
    </Fragment>
  );
}
