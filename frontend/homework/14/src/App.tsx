import { useEffect, useState } from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import "./App.css";
import FullScreenProduct from "./Components/FullScreenProduct";
import NotFound from "./Components/NotFound";
import { IProduct } from "./types";
import { ProductsContext, SetProductsContext } from "./Components/context";
import { Main } from "./Components/Main";

function App() {
  const [products, setProducts] = useState<IProduct[]>([]);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await fetch("https://fakestoreapi.com/products");
        const result = await res.json();
        console.log(result);
        const newResult = result.map((rs: IProduct) => ({
          ...rs,
          isVisible: true,
        }));
        setProducts(newResult);
      } catch (err) {
        console.error(err);
      }
    };
    fetchData();
  }, []);
  return (
    <ProductsContext.Provider value={products}>
      <SetProductsContext.Provider value={setProducts}>
        <BrowserRouter>
          <Routes>
            <Route path="" element={<Main />} />
            <Route path="/:id" element={<FullScreenProduct />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </BrowserRouter>
      </SetProductsContext.Provider>
    </ProductsContext.Provider>
  );
}

export default App;
