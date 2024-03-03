import React, { useState } from "react";
import { useAppSelector } from "../../../redux/store";
import {
  getPaginatedStocks,
  getPaginatedStocksInWishList,
} from "../../../util/getPaginatedStocks";
import { Stock } from "../Stock/Stock";
import { Pagination } from "@mui/material";
import { Container } from "../StockList/StockList.styled";
import { ClipLoader } from "react-spinners";
import { SnackbarComponent } from "../../Snackbar/SnackbarComponent";
import { CSSProperties } from "styled-components";

export function StockList({ active }: Readonly<{ active: boolean }>) {
  const stocks = useAppSelector((state) => state.Stocks.stocks);
  const loading = useAppSelector((state) => state.Stocks.loading);
  const error = useAppSelector((state) => state.Stocks.error);
  const [page, setPage] = useState<number>(0);
  const [watchPage, setWatchPage] = useState<number>(0);
  const pageSize = 7;
  const paginatedStocks = getPaginatedStocks(stocks, pageSize);
  const watchListedPaginatedStock = getPaginatedStocksInWishList(
    stocks,
    pageSize
  );
  const handlePageChange = (
    event: React.ChangeEvent<unknown>,
    value: number
  ) => {
    setPage(value - 1);
  };

  const handleWatchPageChange = (
    event: React.ChangeEvent<unknown>,
    value: number
  ) => {
    setWatchPage(value - 1);
  };

  let contentToRender;
  if (!active) {
    contentToRender = paginatedStocks[page]?.map((stock) => (
      <Stock {...stock} key={stock.stock_name} />
    ));
  } else {
    contentToRender = watchListedPaginatedStock[watchPage]?.map(
      (stock) =>
        stock.isWatchlisted && <Stock {...stock} key={stock.stock_name} />
    );
  }

  const override: CSSProperties = {
    display: "block",
    margin: "0 auto",
    border: "4px solid white",
  };

  return (
    <Container>
      <header className="stocklist-header">
        <div className="company">Company</div>
        <div className="base-watch">
          <div className="base">Base Price</div>
          <div className="watch">Watchlist</div>
        </div>
      </header>

      {!loading && paginatedStocks.length > 0 && (
        <>
          <div className="stock-page-container">{contentToRender}</div>
          <div className="pagination">
            <Pagination
              count={
                active
                  ? getPaginatedStocksInWishList(stocks, pageSize).length
                  : paginatedStocks.length
              }
              page={active ? watchPage + 1 : page + 1}
              onChange={active ? handleWatchPageChange : handlePageChange}
              color="primary"
            />
          </div>
        </>
      )}

      {loading && error && (
        <div className="loading">
          <ClipLoader
            cssOverride={override}
            size={150}
            color="rgb(25, 113, 194)"
          />
        </div>
      )}

      {/* snack bar setup */}
      {!loading && (
        <SnackbarComponent
          type={"success"}
          content={"Stocks rendered successfully"}
        />
      )}

      {/* {error && <SnackbarComponent type={"error"} content={error} />} */}
    </Container>
  );
}
