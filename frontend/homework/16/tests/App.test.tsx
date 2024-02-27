import { render } from '@testing-library/react'
import React from 'react'
import App from '../src/App'
import { describe,it } from 'vitest'
describe('App', () => {
  it('renders the App component', () => {
    render(<App />)
  })
})