import styled from "styled-components";
import { useAppSelector } from "../../../redux/store";
import { Transaction } from "../../../Types/TransactionType";
import { TransactionCard } from "./TransactionCard/TransactionCard";
import { Loader } from "../../Loader/Loader";

const Container = styled.div`
  width: 100%;
  .dayWrapper {
    display: flex;
    flex-direction: column;
    margin-bottom: 18px;
  }
  .loading {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 40px;
    width: 40px;
  }
`;
const DateBlock = styled.div`
  color: #adaeae;
  border-bottom: 2px dotted #adaeae;
`;

export function TransactionList() {
  const dayWiseTransactions = useAppSelector(
    (state) => state.Transactions.transactions
  );
  const loading = useAppSelector((state) => state.Transactions.loading);
  const error = useAppSelector((state) => state.Transactions.error);
  const countVisibility = (transactions: Transaction[]) => {
    for (const tranc of transactions) {
      if (tranc.isVisible) {
        return true;
      }
    }
    return false;
  };
  return (
    <Container>
      {!loading &&
        dayWiseTransactions.map((dateTranc) => (
          <div className="dayWrapper" key={dateTranc.date}>
            {countVisibility(dateTranc.transactions) && (
              <DateBlock>{dateTranc.date}</DateBlock>
            )}
            <DisplayTransactions prop={dateTranc.transactions} />
          </div>
        ))}

      {loading && error && <Loader />}
    </Container>
  );
}

const Wrapper = styled.div``;
const DisplayTransactions = ({ prop }: { prop: Transaction[] }) => {
  return (
    <Wrapper>
      {prop.map(
        (item) =>
          item.isVisible && (
            <TransactionCard key={JSON.stringify(item.time)} {...item} />
          )
      )}
    </Wrapper>
  );
};
