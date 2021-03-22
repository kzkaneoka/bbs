import React, { useEffect } from 'react';

import { useHistory } from 'react-router-dom';
import { Heading } from '@chakra-ui/react';

export default function Logout() {
  const history = useHistory();

  useEffect(() => {
    localStorage.clear();
    history.push('/');
  }, []);

  return <Heading>Logout</Heading>;
}
