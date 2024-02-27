import { fireEvent, render } from "@testing-library/react";
import React from "react";
import { describe, it, expect } from "vitest";
import { ToDoList } from "../src/components/ToDoList.tsx";
import { Provider } from "react-redux";
import { store } from "../src/Redux/store.ts";

describe("ToDoList Component", () => {
  it("should render the title", () => {
    const component = render(
      <Provider store={store}>
        <ToDoList />
      </Provider>
    );
    const contentElement = component.getByText("Add Items");
    expect(contentElement).toBeTruthy();
  });

  it("should verify the search functionality", () => {
    const component = render(
      <Provider store={store}>
        <ToDoList />
      </Provider>
    );

    const textbox = component.getByTestId("todo-input") as HTMLInputElement;
    const testValue = "test item 1";
    const testValue1 = "test item 2";
    const testValue2 = "test item 3";

    // added 3 items
    fireEvent.change(textbox, { target: { value: testValue } });
    const submit = component.getByText(/Submit/i);
    fireEvent.click(submit);
    fireEvent.change(textbox, { target: { value: testValue1 } });
    fireEvent.click(submit);
    fireEvent.change(textbox, { target: { value: testValue2 } });
    fireEvent.click(submit);

    const beforeSearch = component.getByTestId("items").childNodes.length;
    const searchBox = component.getByPlaceholderText(/Search Items/i);
    fireEvent.change(searchBox, { target: { value: testValue } });
    const afterSearch = component.getByTestId("items").childNodes.length;

    // to items are less
    expect(beforeSearch - afterSearch).toBe(2);
  });
});
