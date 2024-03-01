import { useEffect, useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";
import { Home } from "./Components/Home";
import { useAppDispatch } from "./Redux/store";
import { fetchRooms } from "./Redux/RoomSlice/RoomSlice";

function App() {
  const reduxDispatch = useAppDispatch();
  useEffect(() => {
    reduxDispatch(fetchRooms());
    console.log("thunk dispatched");
  }, [reduxDispatch]);
  return <Home />;
}

export default App;
