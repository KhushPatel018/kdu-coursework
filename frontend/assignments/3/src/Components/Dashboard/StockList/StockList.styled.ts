import {styled} from 'styled-components'

export const Container = styled.div`
    width : 80%;
    min-width: 380px;
    border: 2px solid black;
    margin: auto;
    font-size: 1.2rem;
    border-radius: 9px;
    .stocklist-header{
        margin: auto;
        display: flex;
        justify-content: space-between;
        border-bottom: 2px solid black;
        font-weight: 500;
        padding: .5em 1em;
    }
    .base-watch{
        display: flex;
        justify-content: space-between;
        width: 25%;
        min-width: 200px;
    }
    .pagination{
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 1em 0.5em;
    }
`