import React from "react";
import { Card } from "./SummarizerCard.styled";
import { SummarizedStock } from "../../../Types/SummarizerType";

export default function SummarizerCard({
  maxProfit,
  buyDate,
  sellDate,
  company,
  buyPrice,
  sellPrice,
}: SummarizedStock) {
  const formatPrice: (price: number) => string = (price: number) => {
    if (price < 0) {
      return " -₹" + (-1 * price).toString();
    }
    return " ₹" + price.toString();
  };
  const formatDate: (date: string) => string = (date: string) => {
    const monthMap = [
      "noun",
      "Jan",
      "Feb",
      "Mar",
      "Apr",
      "May",
      "Jun",
      "Jul",
      "Aug",
      "Sept",
      "Oct",
      "Nov",
      "Dec",
    ];
    const day = date.split("-")[0];
    const year = date.split("-")[2];
    const month = parseInt(date.split("-")[1]);
    //  console.log(month);
    return " " + year + "-" + monthMap[month] + "-" + day;
  };
  return (
    <Card>
      <div className="title-profit">
        <h2 className="company"> {company} </h2>
        <p className="profit">Profit margin: {maxProfit} </p>
      </div>
      <div className="buy-sell">
        <div className="buy-wrapper">
          <span className="buy">Buy : {formatPrice(buyPrice)} on</span>
          <span className="buy">{formatDate(buyDate)}</span>
        </div>
        <div className="sell-wrapper">
          <span className="sell">Sell : {formatPrice(sellPrice)} on</span>
          <span className="sell">{formatDate(sellDate)}</span>
        </div>
      </div>
    </Card>
  );
}
