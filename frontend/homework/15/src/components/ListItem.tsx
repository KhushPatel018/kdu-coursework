import "./css/ListItem.scss";
import { ListItemPropsType } from "../types";
import { useDispatch } from "react-redux";
import { deleteItem } from "../Redux/ToDoList/ToDoListSlice";

export const ListItem = ({ id, content }: ListItemPropsType) => {
  const dispatch = useDispatch();
  const removeItem = () => {
    dispatch(deleteItem({ id }));
  };
  return (
    <div className="item">
      <p className="content">{content}</p>
      <button className="remove" onClick={removeItem}>
        X
      </button>
    </div>
  );
};
