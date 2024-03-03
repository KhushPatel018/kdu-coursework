import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  StockType,
  ChangeWatchlistType,
  StockStoreState,
} from "../../Types/StockType";

const initialState: StockStoreState = {
  stocks: [],
  loading: false,
  error: "",
};

export const fetchStocks = createAsyncThunk(
  "products/fetchStocks",
  async () => {
    try {
      const response = await fetch(
        "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json"
      );
      const result = await response.json();
      const newResult = result.map((item: StockType) => ({
        ...item,
        isWatchlisted: false,
      }));
      return newResult;
    } catch (err) {
      throw new Error("An error occurred while fetching stocks: " + err);
    }
  }
);

const stockSlice = createSlice({
  name: "products",
  initialState,
  reducers: {
    setProducts: (state, action: PayloadAction<StockType[]>) => {
      state.stocks = [...action.payload];
    },
    setLoading: (state, action: PayloadAction<boolean>) => {
      state.loading = action.payload;
    },
    setError: (state, action: PayloadAction<string>) => {
      state.error = action.payload;
    },
    changeStockWatchlist: (
      state,
      action: PayloadAction<ChangeWatchlistType>
    ) => {
      state.stocks.map((stock) => {
        if (stock.stock_name === action.payload.name) {
          stock.isWatchlisted = action.payload.isWatchlisted;
        }
      });
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(
        fetchStocks.fulfilled,
        (state, action: PayloadAction<StockType[]>) => {
          state.stocks = [...action.payload];
          state.loading = false;
          state.error = "";
        }
      )
      .addCase(fetchStocks.pending, (state) => {
        state.loading = true;
        state.error = "";
      })
      .addCase(fetchStocks.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message ?? "An error occurred";
      });
  },
});

export const { setProducts, setError, setLoading, changeStockWatchlist } =
  stockSlice.actions;
export default stockSlice.reducer;
