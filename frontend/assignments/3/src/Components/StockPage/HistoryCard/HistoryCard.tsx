
import { HistoryItem } from "../../../redux/UserSlice/userSlice";
import styled from "styled-components";

const Container = styled.div`
  display: flex;
  align-content: center;
  justify-content: space-between;
  padding: 8px;
  border: 1px solid #000;
  border-radius: 10px;
  .no-of-stocks{
    font-size: 2rem;
  }
`;

export default function HistoryCard({ qty, time, type }: Readonly<HistoryItem>) {
  return (
    <Container>
      <div className="details">
        <p className="no-of-stocks">
          {qty} {"  "} Stokes
        </p>
        <p className="time">{time}</p>
      </div>
      <div
        className="type"
        style={type === "sell" ? { color: "#2f9e44" } : { color: "#e03131" }}
      >
        {type.toUpperCase()}
      </div>
    </Container>
  );
}
