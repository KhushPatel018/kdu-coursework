import { CSSProperties } from "react";

const styles: { [key: string]: CSSProperties } = {
  product: {
    backgroundColor: "white",
    height: "45vh",
    width: "90%",
    maxWidth : "455px",
    margin: "0 auto",
    display: "flex",
    flexDirection: "column",
    padding: ".8em",
    gap: "6px",
    overflow: "hidden",
    borderRadius : "6px"
  },
  productAnchor: {
    textDecoration: "none",
    color: "inherit",
    height: "100%",
    width: "100%",
    display: "flex",
    flexDirection: "column",
    justifyContent: "space-around",
    alignItems: "center",
  },
  productPicture: {
    width: "80%",
    height: "70%",
  },
  detailsWrapper: {
    height: "20%",
    width: "95%",
    display: "flex",
    justifyContent: "space-between",
  },
  productName: {
    fontSize: "clamp(16px, 1.3vw, 24px)", // Using clamp to handle font-size range
    fontWeight: 400,
  },
  productPrice: {
    fontSize: "clamp(16px, 1.3vw, 24px)", // Using clamp to handle font-size range
    fontWeight: 600,
    color: "#0c359e",
  },
};

export default styles;
