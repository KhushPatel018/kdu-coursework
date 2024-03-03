import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import FullScreenProduct from "./Components/FullScreenProduct";
import NotFound from "./Components/NotFound";

import { Main } from "./Components/Main";
import { useEffect } from "react";
import { fetchProducts } from "./redux/ProductSlice/ProductsSlice";
import { useAppDispatch } from "./redux/store";


function App() {
  const reduxDispatch = useAppDispatch();
  useEffect(() => {
    reduxDispatch(fetchProducts());
  }, [reduxDispatch]);
  return (
    <BrowserRouter>
      <Routes>
        <Route path="" element={<Main />} />
        <Route path="/:id" element={<FullScreenProduct />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
