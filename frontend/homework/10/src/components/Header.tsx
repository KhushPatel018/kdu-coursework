import React, { useState, useEffect } from "react";
import "./css/Header.scss";
import { ListPropsType } from "../types";

export const Header = ({ list, setter }: ListPropsType) => {
  const [searchTerm, setSearchTerm] = useState("");

  useEffect(() => {
    const newList = [...list];
    if (searchTerm === "") {
      newList.forEach((item) => (item.isValid = true));
      setter(newList);
    } else {
      newList.forEach((item) => {
        if (!item.content.toLowerCase().includes(searchTerm.toLowerCase())) {
          item.isValid = false;
        } else {
          item.isValid = true;
        }
        setter(newList);
      });
      console.log("header list: ", list);
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
