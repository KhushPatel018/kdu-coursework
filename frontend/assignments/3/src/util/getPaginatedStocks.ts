
import { StockType } from "../Types/StockType";

export function getPaginatedStocks(
  stocks: StockType[],
  pageSize: number
): StockType[][] {
  const paginatedStocks: StockType[][] = [];
  const totalStocks = stocks.length;
  let pageIndex = 0;

  while (pageIndex * pageSize < totalStocks) {
    const startIndex = pageIndex * pageSize;
    const endIndex = Math.min(startIndex + pageSize, totalStocks);
    paginatedStocks.push(stocks.slice(startIndex, endIndex));
    pageIndex++;
  }

  return paginatedStocks;
}

export function getPaginatedStocksInWishList(stocks: StockType[],
  pageSize: number){
    const watchListedStacks = stocks.filter(stock => stock.isWatchlisted === true)
    return getPaginatedStocks(watchListedStacks,pageSize);
  }
