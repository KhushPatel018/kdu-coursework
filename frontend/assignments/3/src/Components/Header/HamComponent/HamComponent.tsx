import { Link } from "react-router-dom";
import styled from "styled-components";

const Container = styled.div<{ $toggle? : boolean; }>`
    position: absolute;
    top: 80px;
    right : 70px;
  /* border: 1px solid white; */
  border-radius: 6px;
  background-color: #5997ee;
  transform: scale(${props => props.$toggle ? 1 : 0});
  display: flex;
  flex-direction: column;
  color: white;
  align-items: center;
  width: 120px;
  height: 50px;
  z-index: 1;
  font-size: 20px;
  a {
    text-decoration: none;
    color: white;
    font-weight: 500;
    border-bottom: 1px solid white;
  }
  transition: transform 2s ease-in;
`;

export function HamComponent({toggle} : Readonly<{toggle : boolean}>) {
  const username = "khush";
  return (
    <Container $toggle={toggle}>
      <Link to="/summarizer">
        Summarizer
      </Link>
      <Link to={`/portfolio/${username}`}>
        My Portfolio
      </Link>
    </Container>
  );
}
