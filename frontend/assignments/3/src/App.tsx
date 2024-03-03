import { useEffect, useState } from "react";

import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Dashboard from "./Components/Dashboard/Dashboard";
import StockPage from "./Components/StockPage/StockPage";
import NotFound from "./Components/NotFound/NotFound";
import Portfolio from "./Components/Portfolio/Portfolio";
import Summarizer from "./Components/Summarizer/Summarizer";
import { useAppDispatch } from "./redux/store";
import { fetchStocks } from "./redux/StockSlice/StockSlice";
import { fetchTransactions } from "./redux/PortfolioTransactionsSlice/PortfolioTransactionsSlice";
function App() {
  // stocks fetched
  const reduxDispatch = useAppDispatch();
  useEffect(() => {
    try {
      reduxDispatch(fetchStocks());
      reduxDispatch(fetchTransactions());
      console.log("thunk dispatched");
    } catch (e) {
      console.log(e);
    }
  }, [reduxDispatch]);
  return (
    <BrowserRouter>
      <Routes>
        <Route path="" element={<Dashboard />} />
        <Route path="/:stockName" element={<StockPage />} />
        <Route path="/portfolio/:userName" element={<Portfolio />} />
        <Route path="/summarizer" element={<Summarizer />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
