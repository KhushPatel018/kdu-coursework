import { useState } from 'react'
import { Header } from '../Header/Header'
import { StockList } from './StockList/StockList'
import { Container } from './Dashboard.styled'

export default function Dashboard() {
  const [active, setActive] = useState(false);

  return (
    <div>
      <Container $active = {active}>
      <Header/>
      <div className="buttons">
        <button className='explore' onClick={() => setActive(false)}>Explore</button>
        <button className='my-watchlist' onClick={() => setActive(true)}>My WatchList</button>
      </div>
      <StockList active={active}/>
      </Container>
    </div>
  )
}
