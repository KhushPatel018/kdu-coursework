import { useEffect, useRef, useState } from "react";
import { ListItem } from "./ListItem";
import "./css/List.scss";
import { ItemContentType, ListItemType } from "../types";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../Redux/store";
import {
  addItem,
  deleteItem,
} from "../Redux/ToDoList/ToDoListSlice";

export const List = () => {
  const [content, setContent] = useState("");
  const list = useSelector((state: RootState) => state.ToDoList);
  const dispatch = useDispatch();
  const listEndRef = useRef<HTMLDivElement>(null);

  const handleAddItem = () => {
    if (content.trim() === "") {
      alert("Add some content to add item");
      return;
    }
    const listItem: ItemContentType = {
      content: content,
    };
    dispatch(addItem(listItem));
    setContent("");
  };

  const noValidItem = (): boolean => {
    return list.every((item) => !item.isValid);
  };

  useEffect(() => {
    // Scroll to the end of the list when a new item is added
    listEndRef.current?.scrollIntoView({ behavior: "smooth" });
  }, [list]);

  let contentToRender;
  if (list.length === 0) {
    contentToRender = <p className="no-items">No items to show!</p>;
  } else if (noValidItem()) {
    contentToRender = <p className="no-items">No Match Found!</p>;
  } else {
    contentToRender = list.map((item: ListItemType) =>
      item.isValid ? (
        <ListItem key={item.id} {...item} />
      ) : null
    );
  }

  const handleClearComplete = () => {
    list.map((item) => {
      if (item.isDone) {
        dispatch(deleteItem({ id: item.id }));
      }
    });
  };

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
        <button
          className="clearTheComplete"
          onClick={handleClearComplete}
        >
          clear completed
        </button>
        <div ref={listEndRef} />
      </div>
    </div>
  );
};
