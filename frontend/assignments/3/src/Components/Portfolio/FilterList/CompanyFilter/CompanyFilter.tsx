import React, { useState } from "react";
import { useAppDispatch, useAppSelector } from "../../../../redux/store";
import styled from "styled-components";
import { Checkbox, FormControlLabel, FormGroup } from "@mui/material";
import { changeVisibilityCompanyFilter } from "../../../../redux/PortfolioTransactionsSlice/PortfolioTransactionsSlice";
import { Loader } from "../../../Loader/Loader";

const Container = styled.div`
  display: flex;
  flex-direction: column;
  gap: 10px;
  .check-item {
    height: 15px;
    margin: 0;
    color: rgb(141, 143, 145);
    .PrivateSwitchBase-input {
      color: rgb(141, 143, 145);
    }
  }
  @media (width < 817px) {
    flex-direction: row;
    width: 100%;
    align-items: center;
    justify-content: center;
    .check-item {
      height: 40px;
      width: fit-content;
      .MuiTypography-body1 {
        width: fit-content;
        min-width: 15ch;
        font-size: 16px;
      }
      svg{
        width : 20px
      }
  }
  }
`;

interface companyFiltersProps {
  companyFilters: string[];
  setCompanyFilters: React.Dispatch<React.SetStateAction<string[]>>;
}

export default function CompanyFilter({
  companyFilters,
  setCompanyFilters,
}: companyFiltersProps) {
  const stocks = useAppSelector((state) => state.Stocks.stocks);
  const loading = useAppSelector((state) => state.Stocks.loading);
  const error = useAppSelector((state) => state.Stocks.error);

  const reduxDispatch = useAppDispatch();
  const handleCompanyChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.checked) {
      console.log("event dispached with value ", e.target.name);
      setCompanyFilters((prev) => [...prev, e.target.name]);
      reduxDispatch(
        changeVisibilityCompanyFilter([...companyFilters, e.target.name])
      );
    } else {
      setCompanyFilters((prev) =>
        prev.filter((comp) => comp !== e.target.name)
      );
      reduxDispatch(
        changeVisibilityCompanyFilter([
          ...companyFilters.filter((comp) => comp !== e.target.name),
        ])
      );
    }
  };
  return (
    <FormGroup>
      <Container>
        {!loading &&
          stocks.map((item) => (
            <FormControlLabel
              className="check-item"
              control={<Checkbox onChange={handleCompanyChange} />}
              label={item.stock_name}
              name={item.stock_name}
              key={item.stock_name}
            />
          ))}

        {loading && error && <Loader />}
      </Container>
    </FormGroup>
  );
}
