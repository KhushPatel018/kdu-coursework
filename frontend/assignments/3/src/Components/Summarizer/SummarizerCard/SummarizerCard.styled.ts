import styled from "styled-components";

export const Container = styled.div`
  padding: 1em;
  .summary-card-list {
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 20px;
  }
`;
export const Card = styled.div`
  display: flex;
  border-radius: 9px;
  background-color: rgb(25, 113, 194);
  width: 90%;
  min-width: 380px;

  margin-bottom: 20px;
  padding: 1em;
  color: white;
  justify-content: space-between;
  .total-profit {
    color: white;
  }
  .company {
    font-size: 1.8rem;
    @media (width < 879px) {
      max-width: 10ch;
    }
  }
  .profit {
    font-size: 1rem;
    padding: 1em 0;
  }
  .buy-sell {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    gap: 1em;
    font-size: 1rem;
    min-width: 170px;
  }
  .buy-wrapper,
  .sell-wrapper {
    display: flex;
    gap: 10px;
    @media (width < 472px) {
      flex-direction: column;
    }
  }

  @media (width <= 768px) {
    width: 100%;
    padding: 0.5em;
    .company {
      font-size: 1.4rem;
    }
    .buy-sell {
      font-size: 0.8rem;
    }
    .profit {
      font-size: 0.8em;
    }
  }
`;
