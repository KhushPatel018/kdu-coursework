import { render, screen, fireEvent } from "@testing-library/react";
import React from "react";
import { describe, it, expect } from "vitest";
import { List } from "../src/components/List";
import { Provider } from "react-redux";
import { store } from "../src/Redux/store.ts";

describe("List Component", () => {


  it("should render the title", () => {
    const component = render(
      <Provider store={store}>
        <List />
      </Provider>
    );
    const contentElement = component.getByText(/Add Items/i);
    expect(contentElement).toBeTruthy();
  });

  it("should add the item", () => {
    const component = render(
      <Provider store={store}>
        <List />
      </Provider>
    );
    const textbox = component.getByTestId("todo-input") as HTMLInputElement;
    const testValue = "test item";
    fireEvent.change(textbox, { target : { value: testValue } });
    expect(textbox.value, "typed in textbox").toBe(testValue);
    const submit = component.getByText(/Submit/i);
    fireEvent.click(submit);
    expect(
      screen.queryByText(testValue),
      "test item should be added"
    ).toBeVisible();
  });

  // checked item should be removed on clear completed
  it("should remove checked items", () => {
    const component = render(
      <Provider store={store}>
        <List />
      </Provider>
    );
    const textbox = component.getByTestId("todo-input") as HTMLInputElement;
    const testValue = "test item";
    const testValue2 = "test item 2";
    fireEvent.change(textbox, { target : { value: testValue } });
    expect(textbox.value, "typed in textbox").toBe(testValue);
    const submit = component.getByText(/Submit/i);
    fireEvent.click(submit);
    fireEvent.change(textbox, { target : { value: testValue2 } });
    expect(textbox.value, "typed in textbox").toBe(testValue2);
    fireEvent.click(submit);
    const listOfItems = component.getByTestId('items');

    // as 1 -> title, 2 ->  items, 1 - item show, 1 ref 1 clear button 
    expect(listOfItems.childNodes.length).toBe(6); 
    const checkbox = component.getAllByRole("checkbox")[0];
    fireEvent.click(checkbox);
    expect(checkbox,"checked items").toHaveProperty("checked", true); 
    const clear = component.getByTestId('clear');
    fireEvent.click(clear);

    // one item is removed
    expect(listOfItems.childNodes.length).toBe(5); 
  });
});
