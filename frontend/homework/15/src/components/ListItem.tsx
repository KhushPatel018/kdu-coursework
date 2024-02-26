import "./css/ListItem.scss";
import { ListItemType } from "../types";
import { useDispatch } from "react-redux";
import { deleteItem, updateItem } from "../Redux/ToDoList/ToDoListSlice";
import { useState } from "react";

export const ListItem = (item: ListItemType) => {
  const [checked, setChecked] = useState<"checked" | "unchecked">("unchecked");
  const dispatch = useDispatch();
  const removeItem = () => {
    dispatch(deleteItem({ id: item.id }));
  };
  const handleCheckedItem = (
    e: React.MouseEvent<HTMLInputElement, MouseEvent>
  ) => {
    const value = e.currentTarget.id;
    if (value === "checked") {
      dispatch(updateItem({ ...item, isDone: false }));
      setChecked("unchecked");
    } else {
      dispatch(updateItem({ ...item, isDone: true }));
      setChecked("checked");
    }
    console.log(value);
  };
  return (
    <div className="item">
      <input type="checkbox" id={checked} onClick={handleCheckedItem} />
      {checked === "checked" ? (
        <s className="content">{item.content}</s>
      ) : (
        <p className="content">{item.content}</p>
      )}
      <button className="remove" onClick={removeItem}>
        X
      </button>
    </div>
  );
};
