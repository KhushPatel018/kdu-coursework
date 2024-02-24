import { Link, useParams } from "react-router-dom";
import { useProductById } from "./context";
import { styles } from "./css/FullScreenProduct.style";
export default function FullScreenProduct() {
  const { id } = useParams();
  const product = useProductById(parseInt(id!));
  return (
    <div className="main-div" style={styles.mainDiv}>
      <h1 className="title" style={styles.title}>
        {product?.title}
      </h1>
      <div className="detail-wrapper" style={styles.detailWrapper}>
        <img
          src={product?.image}
          alt="productImage"
          className="product-image"
          style={styles.productImage}
        />
        <div className="details" style={styles.details}>
          <div className="model" style={styles.model}>
            Model : {product?.title}
          </div>
          <div className="made-by" style={styles.madeBy}>
            MADE BY : {product?.title.split(' ')[0]}
          </div>
          <div className="price" style={styles.price}>
            Price : ${product?.price}
          </div>
          <div className="description" style={styles.description}>
            product description:
          </div>
          <div className="content" style={styles.content}>
            {product?.description}
          </div>
          <Link to="/" className="back" style={styles.back}>
            Back To Products
          </Link>
        </div>
      </div>
    </div>
  );
}
