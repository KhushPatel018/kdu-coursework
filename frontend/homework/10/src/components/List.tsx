import React, { useState } from "react";
import { ListItem } from "./ListItem";
import "./css/List.scss";
import { ListItemType, ListPropsType } from "../types";

export const List = ({ list, setter }: ListPropsType) => {
  const [content, setContent] = useState("");

  const handleAddItem = () => {
    if (content.trim() === "") {
      alert("Add some content to add item");
      return;
    }
    const listItem: ListItemType = {
      id: `${list.length + 1}`,
      content: content,
      isValid: true,
    };
    setter((prevValues) => [...prevValues, listItem]);
    setContent("");
  };

  const noValidItem = (): boolean => {
    return list.every((item) => !item.isValid);
  };

  let contentToRender;
  if (list.length === 0) {
    contentToRender = <p className="no-items">No items to show!</p>;
  } else if (noValidItem()) {
    contentToRender = <p className="no-items">No Match Found!</p>;
  } else {
    contentToRender = list.map((item: ListItemType) =>
      item.isValid ? (
        <ListItem
          content={item.content}
          key={item.id}
          id={item.id}
          listProps={{ list, setter }}
        />
      ) : null
    );
  }

  return (
    <div className="item-container">
      <h1 className="title">Add Items</h1>
      <div className="input-wrapper">
        <input
          type="text"
          className="add-input"
          onChange={(e) => {
            setContent(e.target.value);
          }}
          value={content}
        />
        <button className="submit" onClick={handleAddItem}>
          Submit
        </button>
      </div>
      <div className="list-container">
        <div className="item-title">Items</div>
        {contentToRender}
      </div>
    </div>
  );
};
