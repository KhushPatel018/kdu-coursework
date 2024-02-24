import React from "react";
import { styles } from "./css/Home.style";
export const FilterBy = ({
  onChange,
  filter,
}: {
  onChange: React.ChangeEventHandler<HTMLSelectElement>;
  filter: string;
}) => {
  return (
    <div className="filters" style={styles.filters}>
     { window.innerWidth > 610 ? <label htmlFor="filters" style={styles.filterLabel}>
        Filter By
      </label> : null}
      <select
        name="filters"
        value={filter}
        onChange={onChange}
        style={styles.select}
      >
        { window.innerWidth < 500 ? <option value="All Products">Filter</option> : null}
        <option value="All Products">All Products</option>
        <option value="men's clothing">men's clothing</option>
        <option value="jewelery">jewelery</option>
        <option value="electronics">electronics</option>
        <option value="women's clothing">women's clothing</option>
      </select>
    </div>
  );
};
