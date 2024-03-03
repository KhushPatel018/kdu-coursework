import React, { useEffect, useMemo, useState } from "react";
import { Header } from "../Header/Header";
import SummarizerCard from "./SummarizerCard/SummarizerCard";
import { Container } from "./SummarizerCard/SummarizerCard.styled";
import { useAppDispatch, useAppSelector } from "../../redux/store";
import {
  ParsedStockData,
  SummarizedStock,
  workerResult,
} from "../../Types/SummarizerType";
import { getParsedStockPriceData } from "../../util/stockPrice";
export default function Summarizer() {
  // const summarizedStocks = useAppSelector(
  //   (state) => state.SummarizedStocks.summarizedStocks
  // );
  // const loading = useAppSelector((state) => state.SummarizedStocks.loading);
  // const error = useAppSelector((state) => state.SummarizedStocks.error);
  // const reduxDispatch = useAppDispatch();
  // TODO : persist in to store and add loading state and check performance
  const [summarizedStocks, setSummarizedStocks] = useState<SummarizedStock[]>(
    []
  );
  const [loading, setLoading] = useState(true);
  const getData: Worker = useMemo(
    () => new Worker(new URL("./worker.ts", import.meta.url)),
    []
  );

  useEffect(() => {
    const doSomeWork = async () => {
      if (window.Worker) {
        try {
          const companies = await getParsedStockPriceData();
          getData.postMessage(JSON.stringify(companies));
        } catch (error) {
          console.error(error);
        }
      }
    };
    doSomeWork();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  useEffect(() => {
    if (window.Worker) {
      getData.onmessage = (e: MessageEvent<string>) => {
        // console.log("got the message");
        const response = JSON.parse(e.data) as unknown as workerResult[];
        const stocks = response.map((obj, index) => {
          return {
            id: index + 1,
            ...obj,
          };
        }) as SummarizedStock[];
        setSummarizedStocks(stocks);
        setLoading(false);
      };
    }
  }, [getData]);

  return (
    <Container>
      <Header />
      <div className="summary-card-list">
        {summarizedStocks.map((stock) => (
          <SummarizerCard key={stock.id} {...stock} />
        ))}
      </div>
    </Container>
  );
}
