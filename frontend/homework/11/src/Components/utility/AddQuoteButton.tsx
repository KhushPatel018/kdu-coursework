import React, { CSSProperties } from "react";
import { ClipLoader } from "react-spinners";
import { AddQuoteButtonProps } from "../../types";

/**
 * AddQuoteButton component for displaying a button to add a new quote.
 * @param {AddQuoteButtonProps} props - The properties passed to the component.
 * @returns {JSX.Element} - A React component representing the AddQuoteButton.
 */
export const AddQuoteButton: React.FC<AddQuoteButtonProps> = ({
  onClick,
  loading,
}) => {
  // CSS override for the loading spinner
  const override: CSSProperties = {
    display: "block",
    margin: "0 auto",
    borderColor: "blue",
  };

  return (
    <>
      {loading ? (
        // Show loading spinner if loading is true
        <div className="add-quote">
          <ClipLoader cssOverride={override} speedMultiplier={1} />
        </div>
      ) : (
        // Show button to add a new quote if loading is false
        <button className="add-quote" onClick={onClick}>
          NEW QUOTE
        </button>
      )}
    </>
  );
};
