import React from "react";
import { QuoteListPropType, QuoteType } from "../../types";
import { Quote } from "../Quote/Quote";
import './QuoteList.scss';

/**
 * Represents a list of quotes.
 * @param {QuoteListPropType} props - The properties passed to the component.
 * @returns {JSX.Element} - JSX element representing the QuoteList component.
 */
export const QuoteList = ({ quotes, onClick }: QuoteListPropType) => {
  return (
    <div className="all-quotes">
      {quotes.map((quote: QuoteType) =>
        quote.isVisible ? (
          <Quote key={quote._id} quote={quote} onClick={onClick} />
        ) : null
      )}
    </div>
  );
};
