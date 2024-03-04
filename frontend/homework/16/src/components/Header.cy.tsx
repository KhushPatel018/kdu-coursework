import { Header } from "./Header";
import { Provider } from "react-redux";
import { store } from "../Redux/store";

describe("<Header />", () => {
  it("renders", () => {
    cy.mount(
      <Provider store={store}>
        <Header />
      </Provider>
    );

    // Check if the title is visible
    cy.get('.title').should('be.visible');

    // Check if the search input is visible and can be typed into
    cy.get('#search').should('be.visible').type('example search term');

    // You can add more UI tests as needed
  });
});
