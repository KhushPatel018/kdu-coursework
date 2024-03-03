import React from "react";
import { ClipLoader } from "react-spinners";
import { CSSProperties } from "styled-components";

export function Loader() {
  const override: CSSProperties = {
    display: "block",
    margin: "0 auto",
    border: "3px solid white",
  };
  return (
    <div className="loading">
      <ClipLoader cssOverride={override} size={150} color="rgb(25, 113, 194)" />
    </div>
  );
}
