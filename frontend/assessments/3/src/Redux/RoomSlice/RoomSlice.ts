import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { RoomType, RoomTypeSlice } from "../../types";

const initialState: RoomTypeSlice = {
  rooms: [],
  loading: false,
  error: "",
};

export const fetchRooms = createAsyncThunk("products/fetchStocks", async () => {
  try {
    const response = await fetch(
      "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/assessment-3.json"
    );
    const result = await response.json();
    console.log(result);
    return result.roomTypes;
  } catch (err) {
    throw new Error("An error occurred while fetching rooms: " + err);
  }
});

const roomSlice = createSlice({
  name: "products",
  initialState,
  reducers: {
    setRooms: (state, action: PayloadAction<RoomType[]>) => {
      state.rooms = [...action.payload];
    },
    setLoading: (state, action: PayloadAction<boolean>) => {
      state.loading = action.payload;
    },
    setError: (state, action: PayloadAction<string>) => {
      state.error = action.payload;
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(
        fetchRooms.fulfilled,
        (state, action: PayloadAction<RoomType[]>) => {
          state.rooms = [...action.payload];
          state.loading = false;
          state.error = "";
        }
      )
      .addCase(fetchRooms.pending, (state) => {
        state.loading = true;
        state.error = "";
      })
      .addCase(fetchRooms.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message ?? "An error occurred";
      });
  },
});

export const { setRooms, setError, setLoading } = roomSlice.actions;
export default roomSlice.reducer;
