import React, { useEffect } from 'react';

import { useHistory } from 'react-router-dom';

export default function Logout() {
  const history = useHistory();

  useEffect(() => {
    localStorage.clear();
    history.push('/');
  }, []);

  return <h1>Logout</h1>;
}
