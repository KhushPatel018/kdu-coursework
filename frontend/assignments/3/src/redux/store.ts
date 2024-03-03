import { configureStore } from "@reduxjs/toolkit";
import StockSliceReducer from "./StockSlice/StockSlice";
import { TypedUseSelectorHook, useDispatch, useSelector } from "react-redux";
import TransactionReducer from './PortfolioTransactionsSlice/PortfolioTransactionsSlice'
import UserSliceReducer from './UserSlice/userSlice'
export const store = configureStore({
  reducer: {
    Stocks: StockSliceReducer,
    Transactions : TransactionReducer,
    User : UserSliceReducer
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
type DispatchFunc = () => AppDispatch;
export const useAppDispatch: DispatchFunc = useDispatch;
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
