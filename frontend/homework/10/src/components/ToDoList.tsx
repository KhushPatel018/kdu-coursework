import React, { useState } from 'react';
import { Header } from './Header';
import { List } from './List';
import './css/ToDoList.scss';
import {ListItemType} from '../types';



export const ToDoList = () => {
  let arr : ListItemType[]  = [];
  const [list,setList] = useState(arr);

  return (
    <div className='container'>
      <div className="header">
      <Header list={list} setter={setList}/>
      </div>
      <div className="list-wrapper">
      <List list={list} setter={setList} />
      </div>
    </div>
  );
};
