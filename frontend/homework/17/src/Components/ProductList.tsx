// ProductList.tsx
import React from "react";
import { IProduct } from "../types";
import Product from "./Product";
import { styles } from "./css/Home.style";
interface ProductListProps {
  products: IProduct[];
}

const ProductList: React.FC<ProductListProps> = ({ products }) => {
  const noValidItem = (): boolean => {
    return products.every((item: IProduct) => !item.isVisible);
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
    <div className="products" style={styles.products}>
      {contentToRender}
    </div>
  );
};

export default ProductList;
