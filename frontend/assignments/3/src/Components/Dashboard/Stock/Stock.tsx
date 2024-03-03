import React, { useState } from "react";
import { StockType } from "../../../Types/StockType";
import { useAppDispatch } from "../../../redux/store";
import { changeStockWatchlist } from "../../../redux/StockSlice/StockSlice";
import { Container } from "./Stock.styled";
import { Link } from "react-router-dom";

export function Stock({
  stock_name,
  base_price,
  isWatchlisted,
}: Readonly<StockType>) {
  const reduxDispatch = useAppDispatch();
  const handleWatchlistIconCLick = () => {
    const newWatchValue = !isWatchlisted;
    reduxDispatch(
      changeStockWatchlist({ name: stock_name, isWatchlisted: newWatchValue })
    );
  };

  const [hovered, setHovered] = useState(false);

  const handleMouseOver = () => {
    setHovered(true);
  };

  const handleMouseOut = () => {
    setHovered(false);
  };

  let contentToRender = <img src="checked.png" alt="" />;
  // if (hovered) {
  //   contentToRender = <img src="checked.png" alt="" />;
  // } else {
  //   contentToRender = <img src="remove.png" alt="" />;
  // }

  return (
    <Container>
      <Link to={`/${stock_name}`}>
        <div className="name">{stock_name}</div>
      </Link>
      <div className="stock-wrapper">
        <div className="base-price">{base_price}</div>
        <div
          className="watch-list"
          onClick={handleWatchlistIconCLick}
          // onMouseEnter={handleMouseOver}
          // onMouseLeave={handleMouseOut}
        >
          {isWatchlisted ? (
            <div className="icon">{contentToRender}</div>
          ) : (
            <img src="add1.png" alt="addLogo" />
          )}
        </div>
      </div>
    </Container>
  );
}
