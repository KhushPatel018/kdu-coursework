import React, { useState } from "react";
import styled from "styled-components";
import SearchIcon from "@mui/icons-material/Search";
import { useAppDispatch } from "../../../../redux/store";
import { changeSearchVisibility } from "../../../../redux/PortfolioTransactionsSlice/PortfolioTransactionsSlice";
const Container = styled.div`
  display: flex;
  align-items: center;
  border: 1px solid rgb(141, 143, 145);
  border-radius: 8px;
  padding: 0.3em 0.5em;
  .icon {
    display: block;
    width: 28px;
    color: rgb(141, 143, 145);
  }
  #stock-search {
    padding: 0.3em;
    font-size: 1rem;
    outline: none;
    border: none;
    width: 90%;
    background: transparent;
    @media (width  <= 481px) {
      font-size: 0.7rem;
    }
  }
  #stock-search::placeholder {
    color: rgb(141, 143, 145);
  }
`;

interface SearchFilterProps {
  searchTerm: string;
  setSearchTerm: React.Dispatch<React.SetStateAction<string>>;
}

export function SearchFilter({ searchTerm, setSearchTerm }: SearchFilterProps) {
  const dispatch = useAppDispatch();
  const handleSearchChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(e.target.value);
    dispatch(changeSearchVisibility(e.target.value));
  };
  return (
    <Container>
      <SearchIcon className="icon" />
      <input
        type="text"
        value={searchTerm}
        onChange={handleSearchChange}
        name=""
        id="stock-search"
        placeholder="Search for a stock"
      />
    </Container>
  );
}
