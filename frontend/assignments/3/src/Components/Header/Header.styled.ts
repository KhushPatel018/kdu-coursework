import { styled } from "styled-components";

export const Container = styled.div`
  width: 100%;
  background-color: rgb(25, 113, 194);
  color: rgb(254, 255, 255);
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0.7em;
  .portfolio-summarizer {
    display: flex;
    align-content: center;
    justify-content: center;
    gap: 0.7rem;
    font-size: 1.2rem;

    img {
      width: 32px;
      display: none;
      @media (max-width: 667px) {
        display: block;
      }
    }
    .summarizer,
    .portfolio {
      text-decoration: none;
      color: white;
      font-weight: 500;
      @media (max-width: 667px) {
        display: none;
      }
    }
  }
`;

export const LogoTitleContainer = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  .title {
    font-size: 1.8rem;
    font-weight: 550;
  }
  a{
    text-decoration: none;
  }
  img {
    display: block;
    width: 48px;
  }
`;
