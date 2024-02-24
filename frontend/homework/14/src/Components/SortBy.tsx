import React from "react";
import { styles } from "./css/Home.style";
export const SortBy = ({ onChange } : {onChange : React.ChangeEventHandler<HTMLSelectElement>}) => {
  return (
    <div className="sort-by" style={styles.sortBy}>
     { window.innerWidth > 610 ? <label htmlFor="sort">Sort By</label> : null}
      <select name="sort" onChange={onChange} style={styles.select}>
        <option value="default">Sort</option>
        <option value="Asc">Asc</option>
        <option value="Desc">Desc</option>
      </select>
    </div>
  );
};
