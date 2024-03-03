import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { DatePicker } from "@mui/x-date-pickers/DatePicker";
import styled from "styled-components";
import { useAppDispatch, useAppSelector } from "../../../../redux/store";
import dayjs from "dayjs";
import { useState } from "react";
import { changeVisibilityDateFilter } from "../../../../redux/PortfolioTransactionsSlice/PortfolioTransactionsSlice";

const Container = styled.div`
  display: flex;
  gap: 10px;
  
`;

interface DateFilterProps {
  startDate: dayjs.Dayjs | null;
  endDate: dayjs.Dayjs | null;
  setStartDate: React.Dispatch<React.SetStateAction<dayjs.Dayjs | null>>;
  setEndDate: React.Dispatch<React.SetStateAction<dayjs.Dayjs | null>>;
}

export default function DataFilter({
  startDate,
  endDate,
  setStartDate,
  setEndDate,
}: DateFilterProps) {
  const reduxDispatch = useAppDispatch();
  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
      <Container>
        <DatePicker
          label="start date"
          value={startDate}
          onChange={(newValue) => {
            setStartDate(newValue);
            reduxDispatch(
              changeVisibilityDateFilter(
                JSON.stringify({ start: newValue, end: endDate })
              )
            );
          }}
        />
        <DatePicker
          label="end date"
          value={endDate}
          onChange={(newValue) => {
            setEndDate(newValue);
            reduxDispatch(
              changeVisibilityDateFilter(
                JSON.stringify({ start: startDate, end: newValue })
              )
            );
          }}
        />
      </Container>
    </LocalizationProvider>
  );
}
