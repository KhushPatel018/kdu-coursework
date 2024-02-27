import { slowCypressDown } from "cypress-slow-down";

// slow dow test for better experience
slowCypressDown(500);
describe("<ToDoList /> Integration Test", () => {
  beforeEach(() => {
    cy.visit("http://localhost:5173/"); // Assuming the ToDoList component is rendered at the root path
  });

  it("adds, removes, checks items", () => {
    // Add an item
    cy.get('[data-testid="todo-input"]').type("Example Item");
    cy.get('[data-testid="submit"]').click();

    // Check if the item is added
    cy.contains('[data-testid="normal"]', "Example Item").should("be.visible");

    // Check the added item
    cy.get('[data-testid="check1"]').check();

    // Check if the item is checked
    cy.contains('[data-testid="crossed"]', "Example Item").should("be.visible");

    // Remove the item
    cy.get('[data-testid="remove1"]').click();

    // Check if the item is removed
    cy.get('[data-testid="item1"]').should("not.exist");
  });

  it("filters items using the search in header", () => {
    // Added 3 items
    cy.get('[data-testid="todo-input"]').type("Item 1{enter}");
    cy.get('[data-testid="submit"]').click();
    cy.get('[data-testid="todo-input"]').type("Item 2{enter}");
    cy.get('[data-testid="submit"]').click();
    cy.get('[data-testid="todo-input"]').type("Item 3{enter}");
    cy.get('[data-testid="submit"]').click();

    // Check if all items are visible
    cy.contains('[data-testid="items"]', "Item 1").should("be.visible");
    cy.contains('[data-testid="items"]', "Item 2").should("be.visible");
    cy.contains('[data-testid="items"]', "Item 3").should("be.visible");

    // Search for an item1
    cy.get("#search").type("Item 1");

    // Check if only the filtered item is visible
    cy.contains('[data-testid="items"]', "Item 1").should("be.visible");
    cy.contains('[data-testid="items"]', "Item 2").should("not.exist");
    cy.contains('[data-testid="items"]', "Item 3").should("not.exist");
  });

  it("clear the checked item using completed clear", () => {
    // Added 3 items
    cy.get('[data-testid="todo-input"]').type("Item 1{enter}");
    cy.get('[data-testid="submit"]').click();
    cy.get('[data-testid="todo-input"]').type("Item 2{enter}");
    cy.get('[data-testid="submit"]').click();
    cy.get('[data-testid="todo-input"]').type("Item 3{enter}");
    cy.get('[data-testid="submit"]').click();

    // Check if all items are visible
    cy.contains('[data-testid="items"]', "Item 1").should("be.visible");
    cy.contains('[data-testid="items"]', "Item 2").should("be.visible");
    cy.contains('[data-testid="items"]', "Item 3").should("be.visible");

    // checked item3
    cy.get('[data-testid="check3"]').check();

    cy.contains('[data-testid="clear"]', "clear completed").click();
    // Check if only non checked item is visible
    cy.contains('[data-testid="items"]', "Item 1").should("be.visible");
    cy.contains('[data-testid="items"]', "Item 2").should("be.visible");
    cy.contains('[data-testid="items"]', "Item 3").should("not.exist");
  });
});
