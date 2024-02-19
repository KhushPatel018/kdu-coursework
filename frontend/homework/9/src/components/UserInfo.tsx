import React from 'react';
import { IInfoProp } from '../types';
import './css/UserInfo.css'

export const UserInfo = ({ name, fullName, qualification }: IInfoProp) => {
  return (
    <div className='info-div'>
      <p className='name'>{name}</p>
      <p  className='full-name'>{fullName}</p>
      <p  className='name'>{qualification}</p>
    </div>
  );
};
