import React from "react";
import { RemoveFilterButtonProps } from "../../types";

/**
 * RemoveFilterButton component for displaying a button to remove a filter.
 * @param {RemoveFilterButtonProps} props - The properties passed to the component.
 * @returns {JSX.Element} - A React component representing the RemoveFilterButton.
 */
export const RemoveFilterButton: React.FC<RemoveFilterButtonProps> = ({
  filter,
  onClick,
}) => {
  /**
   * Event handler for button click.
   * Calls the onClick function with the filter value.
   * @param {React.MouseEvent<HTMLButtonElement, MouseEvent>} event - The button click event.
   */
  const handleClick: React.MouseEventHandler<HTMLButtonElement> = (
    event
  ) => {
    onClick(filter);
  };

  return (
    <button className="remove" name={filter} onClick={handleClick}>
      X
    </button>
  );
};
