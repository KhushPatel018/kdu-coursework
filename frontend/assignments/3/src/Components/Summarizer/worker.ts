/* eslint-disable no-restricted-globals */
import { ParsedStockData } from "../../Types/SummarizerType";

function maxProfit(prices: number[]): {
    maxProfit: number;
    buyIndex: number;
    sellIndex: number;
  } {
    let maxProfit = 0;
    let minPrice = Infinity;
    let buyIndex = 0;
    let sellIndex = 0;
  
    for (let i = 0; i < prices.length; i++) {
      if (prices[i] < minPrice) {
        minPrice = prices[i];
        buyIndex = i;
      } else if (prices[i] - minPrice > maxProfit) {
        maxProfit = prices[i] - minPrice;
        sellIndex = i;
      }
    }
  
    return {
      maxProfit: maxProfit,
      buyIndex: buyIndex,
      sellIndex: sellIndex,
    };
  }
  
  // returns the date according to price index
  const getDate = (
    index: number,
    mapOfIndex: { date: string; indexMap: number[] }[]
  ): { date: string; indexMap: number[] } => {
    for (const data of mapOfIndex) {
      const [start, end] = data.indexMap;
      if (index >= start && index <= end) {
        return data;
      }
    }
    return { date: "2024-02-30", indexMap: [] }; // this will never come
  };

self.onmessage = (e: MessageEvent<string>) => {
  const companies = JSON.parse(e.data) as ParsedStockData[];
  const response = [];
  for (const company of companies) {
    const prices = company.data.prices;
    let result = maxProfit(prices);
    const buyDate = getDate(result.buyIndex, company.data.dayToIndexMap).date;
    const sellDate = getDate(result.sellIndex, company.data.dayToIndexMap).date;
    const buyPrice = prices[result.buyIndex];
    const sellPrice = prices[result.sellIndex];
    const newResult = {
      ...result,
      company: company.company,
      buyDate,
      sellDate,
      buyPrice,
      sellPrice,
    };
    response.push(newResult);
}
    self.postMessage(JSON.stringify(response));
};
