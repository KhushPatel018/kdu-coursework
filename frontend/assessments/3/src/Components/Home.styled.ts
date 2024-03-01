import styled from "styled-components";

export const Container = styled.div<{ $active?: string }>`
  width: 100%;
  .title {
    text-align: center;
    font-size: 2rem;
    padding-bottom: 1em;
  }
  .label {
    padding: 1em;
    background-color: orange;
    color: white;
    font-weight: 500;
    font-size: 1rem;
    margin-bottom: 10px;
  }
  .roomTypeButtons {
    display: flex;
    gap: 10px;
    flex-wrap: wrap;
    margin-bottom: 3em;
  }
  .radioButton {
    padding: 1em;
    border: 1px solid black;
    background: transparent;
    width: 20vw;
    font-weight: 500;
    min-width: 150px;
    cursor: pointer;
  }
  /* .radioButton + .radioButton{
    margin-bottom: 10px;
  } */



  .cost {
    margin-bottom: 1em;
  }

  .dates {
    display: flex;
    gap: 10px;
    flex-wrap: wrap;
    margin-bottom: 40px;
  }

  .date {
    width: 25vw;
    font-size: 1rem;
    font-weight: 500;
    min-width: 200px;
  }

  .add-ons {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
    margin-bottom: 3em;
  }
  .submit {
    padding: 1em;
    font-weight: 500;
    color: white;
    background: transparent;
    background-color: orange;
    border: none;
    width: 13vw;
    min-width: 130px;
  }
`;