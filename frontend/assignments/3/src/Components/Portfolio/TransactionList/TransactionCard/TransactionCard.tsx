import React from "react";
import { Transaction } from "../../../../Types/TransactionType";
import styled from "styled-components";

const Container = styled.div<{$opacity : boolean}>`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1em 0;
  border-bottom: 2px solid black;
  opacity: ${props => props.$opacity ? "0.7" : "1"};
  .stock-details {
    display: flex;
    width: 45%;
    justify-content: space-between;
    align-items: center;
    gap:10px;
  }
  .price-details {
    display: flex;
    width: 45%;
    justify-content: space-between;
  }
  .time-status {
    display: flex;
    width: 40%;
    align-items: center; 
    justify-content: space-between;
    min-width: 9ch;
  }
  .status {
    height: 15px;
    width: 15px;
    border-radius: 50%;
  }
  @media (width < 467px) {
    display: block;
   .stock-details{
    width: 90%;
    margin: auto;
   } 
   .price-details{
    width: 90%;
    margin: auto;
   }
   .time-status{
    width: 90px;
   }
  }
`;
export function TransactionCard({
  status,
  stock_name,
  stock_symbol,
  transaction_price,
  time,
  opacify
}: Readonly<Transaction>) {
  return (
    <Container $opacity={opacify}>
      <div className="stock-details">
        <p className="name">{stock_name}</p>
        <p className="symbol">{stock_symbol}</p>
      </div>
      <div className="price-details">
        <p className="price">{transaction_price}</p>
        <div className="time-status">
          <p className="time">
            {time.hours % 12}:{time.minutes < 10  ?  "0"+time.minutes :time.minutes} {"  "}{" "}
            {time.hours >= 12 ? "PM" : "AM"}
          </p>
          <div
            className="status"
            style={
              status == "Passed"
                ? { backgroundColor: "rgb(112,187,127)" }
                : { backgroundColor: "rgb(231,109,109)" }
            }
          ></div>
        </div>
      </div>
    </Container>
  );
}
