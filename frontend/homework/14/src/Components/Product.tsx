import { IProduct } from "../types";
import { Link } from "react-router-dom";
import styles from "./css/Product.style";
export default function Product({
  id,
  title,
  price,
  image,
}: Readonly<IProduct>) {
  return (
    <div className="product" style={styles.product}>
      <Link to={`/${id}`} style={styles.productAnchor }>
        <img
          className="picture"
          src={image}
          alt="product"
          style={styles.productPicture}
        />
        <div className="details-wrapper" style={styles.detailsWrapper}>
          <p className="name" style={styles.productName}>
            {title}
          </p>
          <p className="price" style={styles.productPrice}>
            ${price}
          </p>
        </div>
      </Link>
    </div>
  );
}
