import React from "react";
import { RemoveFilterButton } from "./RemoveFilterButton";
import { FiltersSectionProps } from "../../types";

export const FiltersSection: React.FC<FiltersSectionProps> = ({
  filters,
  onRemoveFilter,
}) => {
  return (
    <div className="filters">
      <p className="filter-label">filters</p>
      {filters.map((value, index) => (
        <div className="singleFilter" key={index}>
          {value}
          <RemoveFilterButton filter={value} onClick={onRemoveFilter} />
        </div>
      ))}
    </div>
  );
};
