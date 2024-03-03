import { Checkbox, FormControlLabel, FormGroup } from "@mui/material";
import React, { useState } from "react";
import styled from "styled-components";
import { useAppDispatch } from "../../../../redux/store";
import { transactionStatus } from "../../../../Types/TransactionType";
import { changeVisibilityStatusFilter } from "../../../../redux/PortfolioTransactionsSlice/PortfolioTransactionsSlice";

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
`;

interface StatusFilterProps {
  status : transactionStatus,
  setStatus : React.Dispatch<React.SetStateAction<transactionStatus>>
}
export default function StatusFilter({status,setStatus} : StatusFilterProps) {
  const reduxDispatch = useAppDispatch();
  const handleStatusChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.checked) {
      if (e.target.name === "Passed") {
        setStatus("pass");
        reduxDispatch(changeVisibilityStatusFilter("pass"));
      } else {
        setStatus("fail");
        reduxDispatch(changeVisibilityStatusFilter("fail"));
      }
    } else {
      setStatus("none");
      reduxDispatch(changeVisibilityStatusFilter("none"));
    }
  };
  return (
    <FormGroup>
      <Container>
        <FormControlLabel
          className="check-item"
          control={<Checkbox onChange={handleStatusChange} />}
          label="Passed"
          name="Passed"
          checked={status === "pass"}
        />
        <FormControlLabel
          className="check-item"
          control={<Checkbox onChange={handleStatusChange} />}
          label="Failed"
          name="Failed"
          checked={status === "fail"}
        />
      </Container>
    </FormGroup>
  );
}
