import { render} from "@testing-library/react";
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
});
