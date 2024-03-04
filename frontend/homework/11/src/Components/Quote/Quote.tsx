import React from "react";
import { QuotePropType } from "../../types";
import "./Quote.scss";
import { v4 as uuidv4 } from "uuid";

/**
 * Quote component for displaying a single quote.
 * @param {QuotePropType} props - The properties passed to the component.
 * @returns {JSX.Element} - A React component representing the Quote.
 */
export const Quote = ({ quote, onClick }: QuotePropType) => {
  return (
    <div className="quote-wrapper">
      <p className="content">{quote.content}</p>
      <header className="wrapper">
        <h1 className="author">~ {quote.author}</h1>
        <p className="date">{quote.dateAdded}</p>
      </header>
      <div className="tags">
        {quote.tags.map((tag, index) => (
          <button className="tag" key={uuidv4()} onClick={onClick}>
            {tag}
          </button>
        ))}
      </div>
    </div>
  );
};
