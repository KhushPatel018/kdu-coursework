import { useProducts, useSetProducts } from "./context";
import { styles } from "./css/Home.style";
import { FilterBy } from "./FilterBy";
import { SortBy } from "./SortBy";
import { useEffect, useRef, useState } from "react";
import { IProduct } from "../types";
import { useLocation } from "react-router-dom";
import queryString from "query-string";
import { SearchInput } from "./SearchInput";
import ProductList from "./ProductList";
export default function Home() {
  const [searchTerm, setSearchTerm] = useState("");
  const inputRef = useRef<HTMLInputElement>(null);
  const products = useProducts();
  const setProducts = useSetProducts();
  const [filter, setFilter] = useState<string>("All Products");
  const location = useLocation();
  const [filteredProducts, setFilteredProducts] = useState<IProduct[]>([]);

  useEffect(() => {
    const queryParams = queryString.parse(location.search);

    const filterValue = queryParams.filter as string | undefined;

    if (filterValue) {
      setFilter(filterValue);
    }
  }, [location.search]);
  useEffect(() => {
    const filtered = products.filter(
      (product) => filter === "All Products" || product.category === filter
    );
    setFilteredProducts(filtered);
  }, [filter, products]);

  const handleFilterChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setFilter(e.target.value);
  };

  useEffect(() => {
    const newList = [...products];
    if (searchTerm === "") {
      newList.forEach((item) => (item.isVisible = true));
      setProducts(newList);
    } else {
      newList.forEach((item) => {
        if (!item.title.toLowerCase().includes(searchTerm.toLowerCase())) {
          item.isVisible = false;
        } else {
          item.isVisible = true;
        }
        setProducts(newList);
      });
    }
  }, [searchTerm]);
  const handleSortChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    const sortType = e.target.value;
    const sortedList = [...products];
    if (sortType === "Asc") {
      sortedList.sort((a, b) => a.price - b.price);
    } else if (sortType === "Desc") {
      sortedList.sort((a, b) => b.price - a.price);
    }
    setProducts(sortedList);
  };
  const handleSearch = () => {
    if (inputRef.current) setSearchTerm(inputRef.current.value);
  };

  return (
    <div className="container" style={styles.container}>
      <header className="header" style={styles.header}>
        <SearchInput
          inputRef={inputRef}
          searchTerm={searchTerm}
          onSearch={handleSearch}
        />
        <div className="wrapper" style={styles.wrapper}>
          <FilterBy filter={filter} onChange={handleFilterChange} />
          <SortBy onChange={handleSortChange} />
        </div>
      </header>
      <div className="title" style={styles.title}>
        <span className="kdu">KDU</span>{" "}
        <span className="marketplace" style={styles.marketplace}>
          MARKETPLACE
        </span>
      </div>
      <ProductList products={filteredProducts} />
    </div>
  );
}
