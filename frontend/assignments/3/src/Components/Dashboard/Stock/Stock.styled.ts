import {styled} from 'styled-components'


export const Container = styled.div`
    width: 94%;
    margin: auto;
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 2px solid black;
    padding: .8em;
    font-size: 1.2rem;
    a{
        text-decoration: none;
        color: inherit;
    }
    .stock-wrapper{
        display: flex;
        justify-content: space-between;
        width: 22%;
        align-items: center;
        min-width: 190px;
        padding: 0.3em 0.5em;
    }
    img{
        display: block;
        width : 20px;
    }
    
`
