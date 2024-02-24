import { CSSProperties } from "react";

const quoteBg = "#0c359e";
const mainBg = "#f6f5f5";

const styles: { [key: string]: CSSProperties } = {
  "*": {
    boxSizing: "border-box",
  },
  mainDiv: {
    height: "100vh",
    width: "100%",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: mainBg,
  },
  title: {
    fontSize: "max(3.5vw,25px)", // Default font size
    color: quoteBg,
    fontWeight: 500,
  },
  detailWrapper: {
    display: "flex",
  },
  productImage: {
    display:"block",

    height: "65vh",
    maxWidth : "50%",
    mixBlendMode: "multiply",
  },
  details: {
    width: "50%",
    display: "flex",
    flexDirection: "column",
    justifyContent: "center",
    gap: "12px",
  },
  model: {
    fontSize: "max(2.5vw,25px)",
    fontWeight: 500,
  },
  madeBy: {
    fontSize: "1.6rem",
    fontWeight: 700,
    color: "grey",
  },
  price: {
    fontSize: "1.4rem",
    fontWeight: 600,
    color: quoteBg,
  },
  description: {
    fontWeight: 600,
  },
  content: {
    color: "grey",
  },
  back: {
    color: "rgb(21, 151, 244)",
    width : "max(30%,200px)",
    textDecoration: "none",
    textAlign: "center",
    padding: "0.2em",
    fontWeight: 600,
    fontSize: "1.3rem",
    border: "1px solid rgb(21, 151, 244)",
    borderRadius: "6px",
  },
};

// Define media query separately
const mediaQuery = {
  "@media (min-width: 800px)": {
    title: {
      fontSize: "2.5rem", // Font size for larger screens
    },
  },
};

// Merge media query with styles
Object.assign(styles, mediaQuery);

export { styles };
