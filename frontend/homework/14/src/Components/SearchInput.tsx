// SearchInput.tsx
import React from "react";
import { FaSearch } from "react-icons/fa";
import { styles } from "./css/Home.style";

interface SearchInputProps {
  inputRef: React.RefObject<HTMLInputElement>;
  searchTerm: string;
  onSearch: () => void;
}

export const SearchInput: React.FC<SearchInputProps> = ({
  inputRef,
  searchTerm,
  onSearch,
}) => {
  return (
    <div className="search-wrapper" style={styles.searchWrapper}>
      <input
        ref={inputRef}
        type="text"
        placeholder="Search..."
        className="search-input"
        onChange={onSearch}
        style={styles.searchInput}
      />
      <button className="search" onClick={onSearch} style={styles.search}>
        <FaSearch />
      </button>
    </div>
  );
};

