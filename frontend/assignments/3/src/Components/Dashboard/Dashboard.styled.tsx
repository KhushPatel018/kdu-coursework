import { styled } from "styled-components";

export const Container = styled.div<{ $active? : boolean; }>`
  padding: 1em;
  width: 100%;
  .buttons {
    display: flex;
    gap: 0.5em;
    padding: 0.7em 0em;
    .explore,
    .my-watchlist {
      font-weight: 500;
      border: none;
      background: transparent;
      font-size: 1.2rem;
    }
    position: relative;
    .explore {
      border-bottom: ${props => !props.$active && "3px solid rgb(25, 113, 194)"};
    }
    .my-watchlist{
        border-bottom: ${props => props.$active && "3px solid rgb(25, 113, 194)"};;
    }
  }
`;
