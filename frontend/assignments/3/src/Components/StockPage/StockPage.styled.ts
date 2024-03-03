import styled from "styled-components";

export const Wrapper = styled.div`
  height: 90%;
  width: 100%;
  display: flex;
  justify-content: space-between;
  margin-top: 2em;
  .stock-container {
    width: 70%;
    margin: auto;
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: center;
    .display {
      height: 10vh;
      display: flex;
      justify-content: flex-end;
      align-items: center;
      gap: 1em;
      .company {
        width: 29%;
        height: 100%;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        position: relative;
      }
      .companies,
      .dropdown-item {
        height: 70%;
        width: 100%;
        display: flex;
        justify-content: space-around;
        align-items: center;
        border: 1px solid black;
      }
      .dropdown-item {
        height: 7vh;
      }
      .dropdown-content {
        /* display: none; */
        height: 35vh;
        overflow-y: scroll;
        position: absolute;
        top: 71px;
        background-color: #f9f9f9;
        min-width: 160px;
        box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
        z-index: 2;
      }
      a {
        color: inherit;
        text-decoration: none;
      }
      .company-logo {
        height: 35px;
        width: 60px;
        padding: 0.2em;
        border: 1px solid #f5b653;
        color: #f5b653;
        text-align: center;
        background-color: #ffec99;
        font-weight: 500;
      }

      .name {
        font-size: 1rem;
        font-weight: 500;
        width: 60%;
      }

      .price {
        width: 35%;
        height: 60%;
        display: flex;
        font-weight: 500;
        justify-content: space-around;
        border: 1px solid black;
        align-items: center;
        .lable {
          font-size: 1.2rem;
        }
        .value {
          font-size: 1.2rem;
        }
        .hike {
          font-size: 0.5rem;
        }
      }
      #quntity {
        width: 20%;
        height: 60%;
        font-size: 1rem;
        text-align: center;
        outline: none;
        border: 1px solid black;
      }
      #buy {
        width: 5%;
        height: 60%;
        padding: 0.4em;
        background-color: #b2f2bb;
        color: #2f9e44;
        border: 1px solid #2f9e44;
      }
      #sell {
        width: 7%;
        height: 60%;
        padding: 0.4em;
        background-color: #ffc9c9;
        color: #e03131;
        border: 1px solid #e03131;
      }
    }
    .chart {
      width: 100%;
      height: 500px;
      border: 1px solid black;
      overflow: scroll;
      position: relative;
      &::-webkit-scrollbar {
        display: none;
      }

      .barChart{
        position: absolute;
        left: 0;
        bottom: 0;
      }
    }
  }
  .right-side-wrapper {
    width: 25%;
    margin-inline: auto;
    margin-top: 0.4em;
    .history {
      height: 60%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      border: 1px solid #000;
      border-radius: 4px;
      padding: 10px;
      margin-bottom: 0.8em;
      .heading {
        height: 10%;
        font-size: 1.5rem;
      }
      .transaction-box {
        height: 80%;
        overflow: scroll;
        display: flex;
        flex-direction: column;
        gap: 18px;
        &::-webkit-scrollbar {
          display: none;
        }
      }
    }
    .notifications {
      height: 25.58vh;
      border: 1px solid #000;
      overflow: scroll;
      &::-webkit-scrollbar {
        display: none;
      }
    }
  }
`;

export const GreenBar = styled.div<{ $price: number }>`
  height: ${(prop) => `${prop.$price}px`};
  min-width: 20px;
  background-color: #b2f2bb;
  border: 1px solid #2f9e44;
`;
export const RedBar = styled.div<{ $price: number }>`
  height: ${(prop) => `${prop.$price}px`};
  min-width: 20px;
  background-color: #ffc9c9;
  border: 1px solid #e03131;
`;

interface ArrowProps {
  isUp: number;
}

// Styled component for the arrow
export const ArrowIcon = styled.span<ArrowProps>`
  color: ${(props) => (props.isUp >= 0 ? "green" : "red")};
`;

export const Notification = styled.div`
  .notification-text {
    font-size: 1rem;
    font-weight: 500;
    padding: 0.3em;
    padding-bottom: 0;
  }
  .noti-time {
    padding-left: 0.3em;
  }
`;
