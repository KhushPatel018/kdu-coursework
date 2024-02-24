import React from "react";
import { useProducts } from "./context";
import { FaSearch } from "react-icons/fa";
import Product from "./Product";
import './css/Home.scss';
export default function Home() {
  const products = useProducts();
  return (
    <div className="container">
      <header className="header">
        <input type="text"  placeholder="Search..." className="search-input"/>
        <button className="search">
        <FaSearch />
        </button>
      </header>
      <div className="products-container">
          <div className="title"><span className="kdu">KDU</span> <span className="marketplace">MARKETPLACE</span></div>
          <div className="products">
            {
              products.map(product => (
                product.isVisible  ? <Product key={product.id} {...product}/> : null
              ))
            }
          </div>
      </div>
    </div>
  );
}
