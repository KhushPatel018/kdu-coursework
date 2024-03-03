import React, { useState } from "react";
import { SearchFilter } from "./SearchFilter/SearchFilter";
import DataFilter from "./DateFilter/DataFilter";
import StatusFilter from "./StatusFiter/StatusFilter";
import CompanyFilter from "./CompanyFilter/CompanyFilter";
import styled from "styled-components";
import { transactionStatus } from "../../../Types/TransactionType";
import dayjs from "dayjs";
import { useAppDispatch } from "../../../redux/store";
import {
  changeSearchVisibility,
  changeVisibilityStatusFilter,
} from "../../../redux/PortfolioTransactionsSlice/PortfolioTransactionsSlice";

const Container = styled.div`
  position: fixed;
  border-radius: 8px;
  border: 2px solid black;
  width: 35%;
  height: 65vh;
  background-color: #e9ecef;
  .filter-head {
    display: flex;
    padding: 0.5em 0.8em;
    justify-content: space-between;
    align-items: center;
    font-size: 1.2rem;
    font-weight: 500;
    border-bottom: 2px solid black;
    .clear {
      font-size: 1.2rem;
      border: none;
      background: transparent;
      color: rgb(53, 130, 199);
    }
  }
  .search,
  .dates,
  .status {
    padding: 0.5em 0.8em;
    border-bottom: 2px solid black;
  }
  .companies {
    height: 52%;
    padding: 0.5em 0.8em;
    overflow: scroll;
    &::-webkit-scrollbar {
      display: none;
    }
  }

  @media (width < 817px) {
    position: absolute;
    display: flex;
    flex-wrap: wrap;
    top: -25px;
    width: 100%;
    height: 27vh;
    .companies {
      display: flex;
      height: 5vh;
      align-items: center;
      font-size: 0.2rem;
      overflow-x: scroll;
      overflow-y: hidden;
    }
    .filter-head {
      width: 40%;
      padding-inline: 10px;
      font-size: 0.9rem;
      .clear {
        font-size: 0.9rem;
      }
    }
    .search {
      width: 60%;
      padding-left: 0;
      padding-right: 10px;
    }
    .dates {
      width: 80%;
      padding-right: 0;
    }
    .status {
      width: 20%;
      display: flex;
      flex-direction: column;
      justify-content: space-around;
      align-items: center;
      padding: 0;
      font-size: 16px;
    }
  }
  @media (width <= 481px) {
    width: 105%;
    transform: translateX(-10px);
    .search{
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .clear {
      font-size: 25px;
    }
    .dates {
      width: 76%;
      padding-right: 0;
      padding-left: 0.2em;
    }
    .status {
      width: 24%;
      display: flex;
      align-items: center;
      padding: 0;
      .MuiTouchRipple-root {
        height: 30px;
        width: 28px;
      }
      span {
        font-size: 16px;
      }
      svg {
        width: 20px;
      }
    }
  }
`;

export function FilterList() {
  const [status, setStatus] = useState<transactionStatus>("none");
  const [startDate, setStartDate] = useState<dayjs.Dayjs | null>(null);
  const [endDate, setEndDate] = useState<dayjs.Dayjs | null>(null);
  const [searchTerm, setSearchTerm] = useState("");
  const [companyFilters, setCompanyFilters] = useState<string[]>([]);
  const reduxDispatch = useAppDispatch();
  const handleClearAll = () => {
    setStatus("none");
    setStartDate(null);
    setEndDate(null);
    setSearchTerm("");
    setCompanyFilters([]);
    reduxDispatch(changeSearchVisibility(""));
    reduxDispatch(changeVisibilityStatusFilter("none"));
  };
  return (
    <Container>
      <div className="filter-head">
        <p className="Filter">Filter</p>
        <button className="clear" onClick={handleClearAll}>
          Clear All
        </button>
      </div>
      <div className="search">
        <SearchFilter searchTerm={searchTerm} setSearchTerm={setSearchTerm} />
      </div>
      <div className="dates">
        <DataFilter
          startDate={startDate}
          endDate={endDate}
          setStartDate={setStartDate}
          setEndDate={setEndDate}
        />
      </div>
      <div className="status">
        <StatusFilter status={status} setStatus={setStatus} />
      </div>
      <div className="companies">
        <CompanyFilter
          companyFilters={companyFilters}
          setCompanyFilters={setCompanyFilters}
        />
      </div>
    </Container>
  );
}
