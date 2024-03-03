import { Link } from "react-router-dom";
import { Container, LogoTitleContainer } from "./Header.styled";
import { useState } from "react";
import { HamComponent } from "./HamComponent/HamComponent";

export function Header() {
  // fetch from server --> take it from store ?
  let username = "khush";
  const [toggleHam, setToggleHam] = useState(false);
  const openHamburger = () => {
    setToggleHam((prev) => !prev);
  };
  return (
    <div>
      <Container>
        <LogoTitleContainer>
          <Link to="/">
            <img src="stock-market.svg" alt="logo" />
          </Link>
          <h1 className="title">KDU Stock Market</h1>
        </LogoTitleContainer>
        <div className="portfolio-summarizer">
          <div className="hamburger" onClick={openHamburger}>
            <img src="hamburger.png" alt="" />
          </div>
          {toggleHam ? <HamComponent toggle={toggleHam} /> : null}
          <Link to="/summarizer" className="summarizer">
            Summarizer
          </Link>
          <Link to={`/portfolio/${username}`} className="portfolio">
            My Portfolio
          </Link>
        </div>
      </Container>
    </div>
  );
}
