import React, { Fragment, useEffect, useState } from 'react';

import axios from 'axios';
import { Link } from 'react-router-dom';
import { Heading } from '@chakra-ui/react';

import Card from '../card/Card';
import { isUser } from '../../utils/checkUser';

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
        'http://localhost:8080/api/v1/forms',
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

  if (forms !== null && forms.length === 0 && !isUser()) {
    return <Heading as="h2">Please sign up / log in and create form.</Heading>;
  } else if (forms !== null && forms.length === 0) {
    return <Heading as="h2">Please create form.</Heading>;
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
