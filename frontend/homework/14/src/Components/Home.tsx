import { useProducts, useSetProducts } from "./context";
import Product from "./Product";
import { styles } from "./css/Home.style";
import { FaSearch } from "react-icons/fa";
import { FilterBy } from "./FilterBy";
import { SortBy } from "./SortBy";
import { useEffect, useRef, useState } from "react";
import { IProduct } from "../types";
import { useSearchParams } from "react-router-dom";
export default function Home() {
  const [searchTerm, setSearchTerm] = useState("");
  const inputRef = useRef<HTMLInputElement>(null);
  const products = useProducts();
  const setProducts = useSetProducts();
  const [searchParams] = useSearchParams();
  const [filter, setFilter] = useState<string>("All Products");
  useEffect(() => {
    if (searchParams.get("filter")) {
      setFilter(searchParams.get("filter")!);
    }
  });
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

  useEffect(() => {
    setProducts((prevProducts) => {
      const updatedList = prevProducts.map((card) => ({
        ...card,
        isVisible: filter === "All Products" || card.category === filter,
      }));
      return updatedList;
    });
  }, [filter]);

  const handleFilterChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
    setFilter(e.target.value);
  };

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

  const noValidItem = (): boolean => {
    return products.every((item) => !item.isVisible);
  };

  let contentToRender;
  if (products.length === 0) {
    contentToRender = (
      <p className="no-items" style={styles.noItems}>
        No items to show!
      </p>
    );
  } else if (noValidItem()) {
    contentToRender = (
      <p className="no-items" style={styles.noItems}>
        No Match Found!
      </p>
    );
  } else {
    contentToRender = products.map((item: IProduct) =>
      item.isVisible ? <Product key={item.id} {...item} /> : null
    );
  }

  return (
    <div className="container" style={styles.container}>
      <header className="header" style={styles.header}>
        <div className="search-wrapper" style={styles.searchWrapper}>
          <input
            ref={inputRef}
            type="text"
            placeholder="Search..."
            className="search-input"
            onChange={(e) => setSearchTerm(e.target.value)}
            style={styles.searchInput}
          />
          <button
            className="search"
            onClick={handleSearch}
            style={styles.search}
          >
            <FaSearch />
          </button>
        </div>
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
      <div className="products" style={styles.products}>
        {contentToRender}
      </div>
    </div>
  );
}
