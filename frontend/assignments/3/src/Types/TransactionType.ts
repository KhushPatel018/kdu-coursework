import dayjs from "dayjs";

export interface TransactionType {
  stock_name: string;
  stock_symbol: string;
  transaction_price: number;
  timestamp: string;
  status: string;
}

export interface Transaction {
  stock_name: string;
  stock_symbol: string;
  transaction_price: number;
  status: string;
  time: {
    hours: number;
    minutes: number;
    seconds: number;
  };
  isVisible : boolean
  opacify : boolean
}

export interface ApiTransactionData {
  stock_name: string;
  stock_symbol: string;
  transaction_price: number;
  timestamp: string;
  status: string;
}

export interface TransactionsByDate {
  date: string;
  transactions: Transaction[];
  length: number;
}

export interface TransactionSlice  {
  transactions : TransactionsByDate[],
  loading : boolean,
  error : string;
}

export type transactionStatus = "pass" | "fail" | 'none';
export type DateFilterPayload = {
  start :  dayjs.Dayjs | null,
  end :  dayjs.Dayjs | null
}

export interface notificationType {
  username  : string,
  qty : number,
  time : string,
  stock_name : string,
  type: "buy" | "sell"
}