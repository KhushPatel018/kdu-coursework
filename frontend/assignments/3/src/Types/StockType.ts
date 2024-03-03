// Generated by https://quicktype.io

export interface StockType {
    stock_name:   string;
    stock_symbol: string;
    base_price:   number;
    isWatchlisted : boolean
}

export interface StockStoreState {
    stocks  : StockType[];
    loading : boolean;
    error : string;
}

export interface ChangeWatchlistType {
    name : string;
    isWatchlisted : boolean;
}