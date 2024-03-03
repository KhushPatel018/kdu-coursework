import React from "react";
import "./css/ListItem.scss";
import { useContent, useSetList,useId } from "../context";

export const ListItem = () => {
  const setter = useSetList();
  const id = useId();
  const content = useContent();
  const removeItem = () => {
    setter((prev) => [...prev.filter((item) => item.id !== id)]);
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
