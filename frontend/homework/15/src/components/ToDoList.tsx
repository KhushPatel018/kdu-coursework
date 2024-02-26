import React from 'react';
import { Header } from './Header';
import { List } from './List';
import './css/ToDoList.scss';
..


export const ToDoList = () => {
  return (
    <div className='container'>
      <div className="header">
      <Header/>
      </div>
      <div className="list-wrapper">
      <List />
      </div>
    </div>
  );
};
