import React from "react";
import { UserInfo } from "./UserInfo";
import { Skills } from "./Skills";
import Hobbies from "./Hobbies";
import { IUser } from "../types";
import './css/DisplayUser.css'

export const DisplayUser = ({ name,fullName,qualification,skills,hobbies }: IUser) => {
  return (
    <div className="container">
      <div className="user-info">
        <UserInfo
          name={name}
          fullName={fullName}
          qualification={qualification}
        />
      </div>
      <div className="skill-hobbies">
        <div className="skill">
        <Skills skills={skills} />
        </div>
        <div className="hobbies">
        <Hobbies hobbies={hobbies} />
        </div>
      </div>
    </div>
  );
};
