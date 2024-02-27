import { render, fireEvent } from "@testing-library/react";
import React from "react";
import { describe, it, expect } from "vitest";
import { Header } from "../src/components/Header.tsx";
import { Provider } from "react-redux";
import { store } from "../src/Redux/store.ts";

describe("List Component", () => {


  it("should render the title", () => {
    const component = render(
      <Provider store={store}>
        <Header />
      </Provider>
    );
    const contentElement = component.getByText(/item lister/i);
    expect(contentElement).toBeTruthy();
  });

  it("search should be visible", () => {
    const component = render(
      <Provider store={store}>
        <Header />
      </Provider>
    );
    const searchBox = component.getByPlaceholderText(/Search Items/i);
    expect(searchBox).toBeVisible();
  });

  it("should add text in search box", () => {
    const component = render(
      <Provider store={store}>
        <Header />
      </Provider>
    );
    const searchBox = component.getByPlaceholderText(/Search Items/i);
    const testValue = "test item";
    fireEvent.change(searchBox, { target: { value: testValue } });
    expect(searchBox.value, "typed in searchBox").toBe(testValue);
  });
});
