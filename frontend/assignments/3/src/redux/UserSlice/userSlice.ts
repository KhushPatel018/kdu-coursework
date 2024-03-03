import { PayloadAction, createSlice } from "@reduxjs/toolkit";

export interface HistoryItem {
  qty: number;
  time: string;
  type: "buy" | "sell";
}
interface StockBalance {
  stockname: string;
  qty: number;
}

const initialState: {
  user: string;
  balance: number;
  history: HistoryItem[];
  StockBalance: StockBalance[];
} = {
  user: "",
  balance: 10000,
  history: [],
  StockBalance: [],
};

const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    setUsername: (state, action: PayloadAction<string>) => {
      state.user = action.payload;
    },
    setHistory: (state, action: PayloadAction<HistoryItem[]>) => {
      state.history = action.payload;
    },
    setBalance: (state, action: PayloadAction<number>) => {
      state.balance = action.payload;
    },
    setStockBalance: (state, action: PayloadAction<StockBalance>) => {
      const stk = state.StockBalance.find(
        (sb) => sb.stockname === action.payload.stockname
      );
      if (stk) {
        stk.qty = action.payload.qty;
      } else {
        state.StockBalance.push(action.payload);
      }
    },
  },
});

export const { setHistory, setUsername, setBalance,setStockBalance } = userSlice.actions;
export default userSlice.reducer;
