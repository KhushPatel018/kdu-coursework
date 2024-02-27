import { ListItem } from "./ListItem";
import { Provider } from "react-redux";
import { store } from "../Redux/store";

describe("<ListItem />", () => {
  const item = {
    id: "1",
    content: "Test Content",
    isDone: false,
    isValid: true,
  };

  it("renders", () => {
    cy.mount(
      <Provider store={store}>
        <ListItem {...item} />
      </Provider>
    );

    // Check if the content is visible
    cy.contains('.content', 'Test Content').should('be.visible');

    // Check if the checkbox is visible and can be clicked
    cy.get('input[type="checkbox"]').should('be.visible').click();

    // Check if the remove button is visible and can be clicked
    cy.get('[data-testid="remove"]').should('be.visible').click();
  });
});
