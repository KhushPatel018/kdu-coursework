import React from "react";
import "./css/ListItem.scss";
import { ListItemPropsType } from "../types";

export const ListItem = ({
  content,
  id,
  listProps
}: ListItemPropsType) => {
  const removeItem = () => {
    listProps.setter((prev) => [...prev.filter((item) => item.id !== id)]);
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
