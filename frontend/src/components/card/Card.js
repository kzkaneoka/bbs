import React from 'react';

import { Text } from '@chakra-ui/react';

export default function Card({ form }) {
  return (
    <div className="card">
      <div className="card-top">
        <Text fontSize="30px">{form.title}</Text>
      </div>
      <div className="card=body">
        <Text fontSize="25px">{form.description}</Text>
      </div>
    </div>
  );
}
