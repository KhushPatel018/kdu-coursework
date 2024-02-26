import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { ItemContentType, ItemIdType, ListItemType } from "../../types";

const initialState: ListItemType[] = [];

const ToDoListSlice = createSlice({
  name: "ToDoList",
  initialState,
  reducers: {
    addItem: (state, action: PayloadAction<ItemContentType>) => {
      state.push({
        id: (state.length + 1).toString(),
        content: action.payload.content,
        isValid: true,
        isDone : false
      });
    },
    deleteItem: (state, action: PayloadAction<ItemIdType>) => {
      const index = state.findIndex((item) => item.id === action.payload.id);
      if (index !== -1) {
        state.splice(index, 1);
      }
    },
    updateItem: (state, action: PayloadAction<ListItemType>) => {
      const index = state.findIndex((item) => item.id === action.payload.id);
      if (index !== -1) {
        state[index].content = action.payload.content;
        state[index].isValid = action.payload.isValid;
        state[index].isDone = action.payload.isDone;
      }
    },
  },
});
export const { addItem, deleteItem, updateItem } = ToDoListSlice.actions;
export default ToDoListSlice.reducer;
