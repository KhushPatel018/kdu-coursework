import "./App.css";
import { ToDoList } from "./components/ToDoList";
import { Provider } from "react-redux";
import { store } from "./Redux/store.ts";

function App() {
  return (
    <Provider store={store}>
      <ToDoList />;
    </Provider>
  );
}

export default App;
