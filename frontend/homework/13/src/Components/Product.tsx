import React from 'react'
import { IProduct } from '../types'
import './css/Product.scss';
export default function Product({title,price,image} : Readonly<IProduct>) {
  return (
    <div className='product'>
      <img className='picture' src={image} alt="product" />
      <div className="wrapper">
      <p className="name">{title}</p>
      <p className="price">${price}</p>
      </div>
    </div>
  )
}
