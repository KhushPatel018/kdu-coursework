import { useState, useEffect } from "react";
import "./css/Header.scss";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../Redux/store";
import { updateItem } from "../Redux/ToDoList/ToDoListSlice";

export const Header = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const list = useSelector((state: RootState) => state.ToDoList);
  const dispatch = useDispatch();
  useEffect(() => {
    if (searchTerm === "") {
      list.forEach((item) => dispatch(updateItem({ ...item, isValid: true })));
    } else {
      list.forEach((item) => {
        if (!item.content.toLowerCase().includes(searchTerm.toLowerCase())) {
          dispatch(updateItem({ ...item, isValid: false }));
        } else {
          dispatch(updateItem({ ...item, isValid: true }));
        }
      });
    }
  }, [searchTerm]);

  return (
    <div className="header">
      <h1 className="title">Item Lister</h1>
      <input
        type="search"
        name=""
        id="search"
        placeholder="Search Items"
        onChange={(e) => {
          setSearchTerm(e.target.value.trim());
        }}
      />
    </div>
  );
};
