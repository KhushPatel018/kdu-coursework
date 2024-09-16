import React from 'react';
import { IHobby } from '../types';
import './css/Hobbies.css'

const Hobbies = ({ hobbies }: { hobbies: IHobby[] }) => {
  return (
    <div className='hobbies-container'>
      <h2 className='heading'>Hobbies</h2>
      <ul className='items'>
        {hobbies.map(hobby => (
          <li key={hobby.id}>{hobby.hobby}</li>
        ))}
      </ul>
    </div>
  );
};

export default Hobbies;
