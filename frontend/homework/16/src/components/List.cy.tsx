import { List } from "./List";
import { Provider } from "react-redux";
import { store } from "../Redux/store";

describe("<List />", () => {
  it("renders", () => {
    cy.mount(
      <Provider store={store}>
        <List />
      </Provider>
    );

    // Check if the title is visible
    cy.contains('.title', 'Add Items').should('be.visible');

    // Check if the input field is visible and can be typed into
    cy.get('[data-testid="todo-input"]').should('be.visible').type('Example item');

    // Check if the submit button is visible and can be clicked
    cy.contains('button', 'Submit').should('be.visible').click();

    // Check if the list items are visible
    cy.get('[data-testid="items"]').should('be.visible');

    // Check if the clear completed button is visible and can be clicked
    cy.contains('[data-testid="clear"]', 'clear completed').should('be.visible').click();
  });
});
