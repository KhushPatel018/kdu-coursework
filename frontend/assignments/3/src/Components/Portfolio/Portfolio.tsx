import styled from "styled-components";
import { Header } from "../Header/Header";
import { TransactionList } from "./TransactionList/TransactionList";
import { FilterList } from "./FilterList/FilterList";

const Container = styled.div`
  width: 100%;
  height: 100vh;
  padding: 1em;
  display: flex;
  flex-direction: column;
  gap: 3rem;
`;

const Wrapper = styled.div`
  width: 95%;
  margin: 0 auto;
  display: flex;
  gap: 7vw;
  position: relative;
  .align-end {
    width: 55%;
    height: 85vh;
    position: absolute;
    right: 0;
    overflow-y: scroll;
    &::-webkit-scrollbar {
      display: none;
    }
  }
  @media (width < 817px) {
    .align-end {
      left: 0;
      top: 220px;
      width: 100%;
    }
  }
`;
export default function Portfolio() {
  return (
    <Container>
      <Header />
      <Wrapper>
        <FilterList />
        <div className="align-end">
          <TransactionList />
        </div>
      </Wrapper>
    </Container>
  );
}
