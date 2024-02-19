import React from 'react';
import { ISkill } from '../types';
import './css/Skills.css'

export const Skills = ({ skills }: { skills: ISkill[] }) => {
  return (
    <div className='skill-container'>
      <h2 className='title'>Skills</h2>
      <ul className='list'>
        {skills.map(skill => (
          <li key={skill.id}>{skill.skill}</li>
        ))}
      </ul>
    </div>
  );
};
