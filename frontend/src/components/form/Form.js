import React from 'react';

export default function Form({ form }) {
  return (
    <div className="card">
      <div className="card-top">
        <h2>{form.title}</h2>
      </div>
      <div className="card=body">
        <p>{form.description}</p>
      </div>
    </div>
  );
}
