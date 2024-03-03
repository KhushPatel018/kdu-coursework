import { configureStore } from "@reduxjs/toolkit";
import ToDoListReducer  from "./ToDoList/ToDoListSlice";
export const store = configureStore({
    reducer : {
        ToDoList : ToDoListReducer,
    }
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;