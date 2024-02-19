import React from "react";
import "./App.css";
import { DisplayUser } from "./components/DisplayUser";
import { IUser } from "./types";

function getUserData(): IUser {
  return {
    name: "Amey",
    fullName: "Amey Aditya",
    qualification: "SSE",
    skills: [
      {
        id: 1,
        skill: "Python",
      },
      {
        id: 2,
        skill: "React",
      },
    ],
    hobbies: [
      {
        id: 1,
        hobby: "Cricket",
      },
    ],
  };
}

function App() {
  return (
    <div className="App">
      <DisplayUser {...getUserData()} />
    </div>
  );
}

export default App;
