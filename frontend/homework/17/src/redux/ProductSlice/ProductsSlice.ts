import { PayloadAction, createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { IProduct, changeVisibilityType, productSliceType } from "../../types";

const initialState: productSliceType = {
  products: [],
  loading: false,
  error: "",
};

export const fetchProducts = createAsyncThunk(
  "products/fetchProducts",
  async () => {
    try {
      const response = await fetch("https://fakestoreapi.com/products");
      const result = await response.json();
      const newResult = result.map((item: IProduct) => ({
        ...item,
        isVisible: true,
      }));
      return newResult;
    } catch (err) {
      throw new Error("An error occurred while fetching products: " + err);
    }
  }
);

const productSlice = createSlice({
  name: "products",
  initialState,
  reducers: {
    setProducts: (state, action: PayloadAction<IProduct[]>) => {
      state.products = [...action.payload];
    },
    setLoading: (state, action: PayloadAction<boolean>) => {
      state.loading = action.payload;
    },
    setError: (state, action: PayloadAction<string>) => {
      state.error = action.payload;
    },
    changeProductVisibility: (
      state,
      action: PayloadAction<changeVisibilityType>
    ) => {
      state.products.map((product) => {
        if (product.id === action.payload.id) {
          product.isVisible = action.payload.visibility;
        }
      });
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(
        fetchProducts.fulfilled,
        (state, action: PayloadAction<IProduct[]>) => {
          state.products = [...action.payload];
          state.loading = false;
          state.error = "";
        }
      )
      .addCase(fetchProducts.pending, (state) => {
        state.loading = true;
        state.error = "";
      })
      .addCase(fetchProducts.rejected, (state, action) => {
        state.loading = false;
        state.error = action.error.message ?? "An error occurred";
      });
  },
});

export const { setProducts, setError, setLoading, changeProductVisibility } =
  productSlice.actions;
export default productSlice.reducer;
