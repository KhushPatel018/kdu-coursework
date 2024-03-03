import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import {
  ApiTransactionData,
  DateFilterPayload,
  TransactionSlice,
  TransactionType,
  TransactionsByDate,
  transactionStatus,
} from "../../Types/TransactionType";
import dayjs from "dayjs";
import {
  extractDateAndTime,
  parseTime,
  processAPIData,
} from "../../util/transaction";
import { useLocalStorage } from "../../lib/hooks/useLocalStorage";
const { setItem, getItem } = useLocalStorage("transactions");
const initialState: TransactionSlice = {
  transactions: getItem() ?? [],
  loading: true,
  error: "",
};

// Binary search
// find the first element in the array that is greater than or equal
const lowerBound = (
  transactions: TransactionsByDate[],
  targetDate: dayjs.Dayjs
): number => {
  let left = 0;
  let right = transactions.length;
  while (left < right) {
    const mid = Math.floor((left + right) / 2);
    const transactionDate = dayjs(transactions[mid].date);
    if (transactionDate.isAfter(targetDate, "day")) {
      left = mid + 1;
    } else {
      right = mid;
    }
  }
  return left;
};

const makeAllVisible = (transactions: TransactionsByDate) => {
  const newTransactions = transactions.transactions.map((tr) => {
    return { ...tr, isVisible: true };
  });
  return {
    ...transactions,
    transactions: newTransactions,
  };
};

const makeEverythingInvisible = (
  transactions: TransactionsByDate[]
): TransactionsByDate[] => {
  return transactions.map((dateEntry) => ({
    ...dateEntry,
    transactions: dateEntry.transactions.map((transaction) => ({
      ...transaction,
      isVisible: false,
    })),
  }));
};

export const fetchTransactions = createAsyncThunk(
  "transactions/fetchTransactions",
  async () => {
    try {
      const data = getItem();
      if (data && data.length > 0) {
        return data;
      }
      console.log("thunk called again");
      const response = await fetch(
        "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json"
      );
      const result = (await response.json()) as ApiTransactionData[];
      return processAPIData(result);
    } catch (err) {
      throw new Error("An error occurred while fetching stocks: " + err);
    }
  }
);

const transactionSlice = createSlice({
  name: "transactions",
  initialState,
  reducers: {
    addTransaction: (state, action: PayloadAction<TransactionType>) => {
      const rawTransaction = action.payload;
      const { date, time } = extractDateAndTime(rawTransaction.timestamp);
      const newTransaction = {
        stock_name: rawTransaction.stock_name,
        stock_symbol: rawTransaction.stock_symbol,
        transaction_price: rawTransaction.transaction_price,
        status: rawTransaction.status,
        time: parseTime(time),
        isVisible: true,
        opacify : false
      };
      if (
        state.transactions.length > 0 &&
        state.transactions[0].date === date
      ) {
        state.transactions[0].transactions.unshift(newTransaction);
      } else {
        // for the first time on that date
        const newTransactions = [newTransaction];
        state.transactions.unshift({
          date: date,
          transactions: newTransactions,
          length: 0,
        });
      }
      setItem(state.transactions);
    },
    setLoading: (state, action: PayloadAction<boolean>) => {
      state.loading = action.payload;
    },
    setError: (state, action: PayloadAction<string>) => {
      state.error = action.payload;
    },
    changeVisibilityCompanyFilter: (
      state: TransactionSlice,
      action: PayloadAction<string[]>
    ) => {
      state.transactions.forEach((dateEntry) => {
        dateEntry.transactions.forEach((transaction) => {
          if (
            action.payload.length === 0 ||
            action.payload.includes(transaction.stock_name)
          ) {
            transaction.opacify = false;
          } else {
            transaction.opacify = true;
          }
        });
      });
    },
    changeVisibilityStatusFilter: (
      state,
      action: PayloadAction<transactionStatus>
    ) => {
      // apply status (none and pass+failed do the same thing in this cases no filter applied) failed filter if they are different from their previous state
      const shouldShowAll = action.payload === "none";

      state.transactions.forEach((dateEntry) => {
        dateEntry.transactions.forEach((transaction) => {
          const showFailed =
            action.payload === "fail" && transaction.status === "Failed";
          const showPassed =
            action.payload === "pass" && transaction.status === "Passed";
          if (shouldShowAll || showFailed || showPassed) {
            transaction.isVisible = true;
          } else {
            transaction.isVisible = false;
          }
        });
      });
    },
    changeVisibilityDateFilter: (state, action: PayloadAction<string>) => {
      // apply start date and end date filter if they are different from their previous state
      const data: DateFilterPayload = JSON.parse(action.payload);
      const startDate = data.start ? dayjs(data.start) : null;
      const endDate = data.end ? dayjs(data.end) : null;
      console.log(startDate, endDate);

      const startIndex = endDate ? lowerBound(state.transactions, endDate) : 0;
      const endIndex = startDate
        ? lowerBound(state.transactions, startDate)
        : state.transactions.length - 1;

      console.log(startIndex, endIndex);
      state.transactions = makeEverythingInvisible(state.transactions);
      for (let i = startIndex; i < endIndex; i++) {
        state.transactions[i] = makeAllVisible(state.transactions[i]);
      }
    },
    changeSearchVisibility: (state, action: PayloadAction<string>) => {
      state.transactions.forEach((dateEntry) => {
        dateEntry.transactions.forEach((transaction) => {
          if (
            action.payload.trim() === "" ||
            transaction.stock_name.toLowerCase().includes(action.payload.toLowerCase())
          ) {
            transaction.isVisible = true;
          } else {
            transaction.isVisible = false;
          }
        });
      });
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(
        fetchTransactions.fulfilled,
        (state, action: PayloadAction<TransactionsByDate[]>) => {
          state.transactions = [...action.payload];
          setItem(state.transactions);
          state.loading = false;
          state.error = "";
        }
      )
      .addCase(fetchTransactions.pending, (state) => {
        state.loading = true;
        state.error = "";
      })
      .addCase(fetchTransactions.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message ?? "An error occurred";
      });
  },
});

export const {
  addTransaction,
  setError,
  setLoading,
  changeVisibilityDateFilter,
  changeVisibilityStatusFilter,
  changeVisibilityCompanyFilter,
  changeSearchVisibility,
} = transactionSlice.actions;
export default transactionSlice.reducer;
