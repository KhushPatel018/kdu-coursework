import { render, screen, fireEvent } from "@testing-library/react";
import React from "react";
import { describe, it, expect } from "vitest";
import { ListItem } from "../src/components/ListItem";
import { ListItemType } from "../src/types";
import { Provider } from "react-redux";
import { store } from "../src/Redux/store.ts";

describe("ListItem Component", () => {
  const item: ListItemType = {
    id: "1",
    content: "Test Content",
    isDone: false,
    isValid: true,
  };

  it("should render the content", () => {
    const component = render(
      <Provider store={store}>
        <ListItem {...item} />
      </Provider>
    );
    const contentElement = component.getByText(/Test Content/i);
    expect(contentElement).toBeTruthy();
  });

  it("should update item status and make it checked", () => {
    const component = render(
      <Provider store={store}>
        <ListItem {...item} />
      </Provider>
    );
    const checkbox = component.getByRole("checkbox");
    fireEvent.click(checkbox);
    expect(checkbox).toHaveProperty("checked", true);
  });

  it("should delete the item", async () => {
    render(
      <Provider store={store}>
        <ListItem {...item} />
      </Provider>
    );
    await screen.findByText(/Test Content/i);
    const removeButton = screen.getByTestId("remove1");
    console.log(removeButton);
    fireEvent.click(removeButton);
    expect(screen.queryByText(/Test Content/i)).toBeInTheDocument();
  });
});
