import { styles } from "./css/Home.style";
import { FilterBy } from "./FilterBy";
import { SortBy } from "./SortBy";
import { CSSProperties, useEffect, useRef, useState } from "react";
import { IProduct } from "../types";
import { useLocation } from "react-router-dom";
import queryString from "query-string";
import { SearchInput } from "./SearchInput";
import ProductList from "./ProductList";
import { RootState, useAppDispatch, useAppSelector } from "../redux/store";
import {
  changeProductVisibility,
  setProducts,
} from "../redux/ProductSlice/ProductsSlice";
import { ClipLoader } from "react-spinners";
import { SnackbarComponent } from "./Snackbar";
export default function Home() {
  
  

  const [searchTerm, setSearchTerm] = useState("");
  const inputRef = useRef<HTMLInputElement>(null);
  const products = useAppSelector(
    (state: RootState) => state.Products.products
  );
  const loading = useAppSelector((state) => state.Products.loading);
  const error = useAppSelector((state) => state.Products.error);
  const reduxDispatcher = useAppDispatch();
  const [filter, setFilter] = useState<string>("All Products");
  const location = useLocation();
  const [filteredProducts, setFilteredProducts] = useState<IProduct[]>([]);

  // query param's filter
  useEffect(() => {
    const queryParams = queryString.parse(location.search);

    const filterValue = queryParams.filter as string | undefined;

    if (filterValue) {
      setFilter(filterValue);
    }
  }, [location.search]);

  // apply the filter on the products
  useEffect(() => {
    const filtered = products.filter(
      (product) => filter === "All Products" || product.category === filter
    );
    setFilteredProducts(filtered);
  }, [filter, products]);

  // changing the filter on the header
  const handleFilterChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setFilter(e.target.value);
  };

  // search functionality
  useEffect(() => {
    if (searchTerm === "") {
      products.map((product) => {
        reduxDispatcher(
          changeProductVisibility({ id: product.id, visibility: true })
        );
      });
    } else {
      products.forEach((item) => {
        reduxDispatcher(
          changeProductVisibility({
            id: item.id,
            visibility: item.title
              .toLowerCase()
              .includes(searchTerm.toLowerCase()),
          })
        );
      });
    }
  }, [searchTerm]);


  // sort 
  const handleSortChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const sortType = e.target.value;
    const sortedList = [...products];
    if (sortType === "Asc") {
      sortedList.sort((a, b) => a.price - b.price);
    } else if (sortType === "Desc") {
      sortedList.sort((a, b) => b.price - a.price);
    }
    reduxDispatcher(setProducts(sortedList));
  };

  // search box change
  const handleSearch = () => {
    if (inputRef.current) setSearchTerm(inputRef.current.value);
  };

  // loader css props
  const override: CSSProperties = {
    display: "block",
    margin: "0 auto",
    border: "5px solid white",
  };

  return (

    <div className="container" style={styles.container}>
      {/* header */}
      <header className="header" style={styles.header}>
        <SearchInput inputRef={inputRef} onSearch={handleSearch} />
        <div className="wrapper" style={styles.wrapper}>
          <FilterBy filter={filter} onChange={handleFilterChange} />
          <SortBy onChange={handleSortChange} />
        </div>
      </header>

      {/* title */}
      <div className="title" style={styles.title}>
        <span className="kdu">KDU</span>{" "}
        <span className="marketplace" style={styles.marketplace}>
          MARKETPLACE
        </span>
      </div>

      {/* loader setup */}
      {!loading ? (
        <ProductList products={filteredProducts} />
      ) : (
        <div className="loading" style={styles.loading}>
          <ClipLoader cssOverride={override} size={150} color="grey" />
        </div>
      )}

      {/* snack bar setup */}
      {!loading && (
        <SnackbarComponent
          type={"success"}
          content={"Products rendered successfully"}
        />
      )}
      {error && <SnackbarComponent type={"error"} content={error} />}
    </div>
  );
}
