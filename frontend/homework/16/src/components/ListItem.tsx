import "./css/ListItem.scss";
import { ListItemType } from "../types";
import { useDispatch } from "react-redux";
import { deleteItem, updateItem } from "../Redux/ToDoList/ToDoListSlice";
import { useState } from "react";

export const ListItem = (item: ListItemType) => {
  const [isChecked, setIsChecked] = useState<boolean>(item.isDone);
  const dispatch = useDispatch();

  const removeItem = () => {
    dispatch(deleteItem({ id: item.id }));
  };

  const handleCheckedItem = () => {
    const newCheckedState = !isChecked;
    setIsChecked(newCheckedState);
    dispatch(updateItem({ ...item, isDone: newCheckedState }));
  };
  const testId = `remove${item.id}`
  const checkId = `check${item.id}`
  return (
    <div className="item">
      <input type="checkbox" checked={isChecked}  data-testid ={checkId} onChange={handleCheckedItem} />
      {isChecked ? (
        <s className="content" data-testid = 'crossed'>{item.content}</s>
      ) : (
        <p className="content" data-testid = 'normal' >{item.content}</p>
      )}
      <button className="remove" data-testid ={testId} onClick={removeItem}>
        X
      </button>
    </div>
  );
};
