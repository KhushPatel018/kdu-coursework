import React, { useState } from "react";
import "./App.css";
import { ToDoList } from "./components/ToDoList";
import { ListContext, SetListContext } from "./context";
import { ListItemType } from "./types";

function App() {
  const [list, setList] = useState<ListItemType[]>([]);

  return (
    <ListContext.Provider value={list}>
      <SetListContext.Provider value={setList}>
        <ToDoList />
      </SetListContext.Provider>
    </ListContext.Provider>
  );
}

export default App;
