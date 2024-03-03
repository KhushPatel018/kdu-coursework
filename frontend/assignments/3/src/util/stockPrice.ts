

// for summarizer page api data fetching

import { StockData , ParsedStockData} from "../Types/SummarizerType";

const fetchPrices = async (): Promise<
  { company: string; data: StockData[] }[]
> => {
  const stockPrice: { company: string; data: StockData[] }[] = [];
  try {
    const res = await fetch(
      "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/all-stocks-transactions.json"
    );
    console.log(res.statusText);
    const data = await res.json();
    data.map((obj: { company: string; data: StockData[] }) => {
      stockPrice.push({ company: obj.company, data: obj.data });
    });
    return stockPrice;
  } catch (error) {
    console.log(error);
    throw error;
  }
};

// company wise date processing
const getCompanyData = (
  companyData: StockData[]
): {
  prices: number[];
  dayToIndexMap: { date: string; indexMap: number[] }[];
} => {
  let prices: number[] = [];
  let dayToIndexMap: { date: string; indexMap: number[] }[] = [];
  companyData.forEach((dataBlock) => {
    const date = dataBlock.date;
    const startIndex = prices.length;
    const endIndex = startIndex + dataBlock.prices.length - 1;
    prices = [...prices, ...dataBlock.prices];
    dayToIndexMap.push({ date: date, indexMap: [startIndex, endIndex] });
  });
  return { prices, dayToIndexMap };
};

const getParsedStockPriceData = async (): Promise<ParsedStockData[]> => {
  const parsedStockPriceData: ParsedStockData[] = [];
  try {
    const stockPrice = await fetchPrices();
    stockPrice.forEach((stock) => {
      const company = stock.company;
      parsedStockPriceData.push({
        company: company,
        data: getCompanyData(stock.data),
      });
    });
    return parsedStockPriceData;
  } catch (error) {
    console.log(error);
    throw error;
  }
};

export { getParsedStockPriceData};
