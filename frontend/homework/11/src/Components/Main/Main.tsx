import React, { useEffect, useState } from "react";
import { QuoteList } from "../QuoteList/QuoteList";
import { QuoteType } from "../../types";
import "./Main.scss";
import { FiltersSection } from "../utility/FiltersSection";
import { AddQuoteButton } from "../utility/AddQuoteButton";

/**
 * Main component for displaying quotes and handling filters.
 */
export const Main = () => {
  const [quotes, setQuotes] = useState<QuoteType[]>([]);
  const [filters, setFilters] = useState<string[]>([]);
  const [loading, setLoading] = useState(false);

  // Fetch quotes from API when component mounts
  useEffect(() => {
    const fetchQuotes = async () => {
      try {
        const res = await fetch(
          "https://api.quotable.io/quotes/random?limit=3"
        );
        const result = await res.json();
        const updatedResult = result.map((r: any) => ({
          ...r,
          isVisible: true,
        }));
        setQuotes(updatedResult);
      } catch (err: any) {
        console.error(err.message);
      }
    };

    fetchQuotes();
  }, []);

  // Filter quotes when filters change
  useEffect(() => {
    const filterQuotes = () => {
      if (filters.length === 0) {
        const updatedList = quotes.map((qt) => ({
          ...qt,
          isVisible: true,
        }));
        setQuotes(updatedList);
      } else {
        const updatedList = quotes.map((qt: QuoteType) => ({
          ...qt,
          isVisible: filters.some((filtr) => qt.tags.includes(filtr)),
        }));
        setQuotes(updatedList);
      }
    };
    filterQuotes();
  }, [filters, quotes]);

  // Handle adding a new quote
  const addQuoteClickHandler = async () => {
    setLoading(true);
    try {
      const res = await fetch("https://api.quotable.io/quotes/random");
      const result = await res.json();
      let visibility = false;
      if (filters.length === 0) {
        visibility = true;
      } else {
        result[0].tags.forEach((tag: string) => {
          if (filters.includes(tag)) {
            visibility = true;
          }
        });
      }

      const newResult = { ...result[0], isVisible: visibility };
      setLoading(false);
      setQuotes((prevQuotes) => [newResult, ...prevQuotes]);
    } catch (err: any) {
      console.error(err.message);
      setLoading(false);
    }
  };

  // Handle adding a filter
  const addFilterClickHandler: React.MouseEventHandler<HTMLButtonElement> = (
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ) => {
    const filter = (e.target as HTMLButtonElement).innerText;
    if (!filters.includes(filter)) setFilters((prevFilters) => [...prevFilters, filter]);
  };

  // Handle removing a filter
  const removeFilterHandler = (filter: string) => {
    setFilters((prevFilters) => prevFilters.filter((fl) => fl !== filter));
  };

  return (
    <div className="container">
      {/* AddQuoteButton component */}
      <AddQuoteButton onClick={addQuoteClickHandler} loading={loading} />

      {/* FiltersSection component */}
      <FiltersSection onRemoveFilter={removeFilterHandler} filters={filters} />

      {/* QuoteList component */}
      <div className="quote-container">
        <QuoteList quotes={quotes} onClick={addFilterClickHandler} />
      </div>
    </div>
  );
};
