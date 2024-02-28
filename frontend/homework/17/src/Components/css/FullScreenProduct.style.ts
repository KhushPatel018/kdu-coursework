import { CSSProperties } from "react";

const quoteBg = "#0c359e";
const mainBg = "#E4EAF0";
interface MediaQueries {
  [key: string]: { [key: string]: CSSProperties };
}
const styles: { [key: string]: CSSProperties | MediaQueries } = {
  "*": {
    boxSizing: "border-box",
  },
  mainDiv: {
    height: "100vh",
    width: "100%",
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    backgroundColor: mainBg,
  },
  title: {
    fontSize: "max(3.5vw,25px)", // Default font size
    color: quoteBg,
    fontWeight: 500,
    padding : "1em",
  },
  detailWrapper: {
    display: "flex",
    flexDirection : "row",
    gap : "1em"
  },
  image : {
    width : "40%",
    display : "flex",
    justifyContent : 'center'
  },
  productImage: {
    display:"block",
    width : "100%",
    objectPosition : "centre",
    maxWidth : "500px",
    minWidth : "200px",
    maxHeight : "450px", 
    mixBlendMode: "multiply",
  },
  details: {
    width: "50%",
    display: "flex",
    flexDirection: "column",
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
  "@media (max-width: 520px)": {
    detailWrapper: { // Styles specific to screens with a maximum width of 520px
      flexDirection: "column",
    }
  } as MediaQueries
}


export { styles };
