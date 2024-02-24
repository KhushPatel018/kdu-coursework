import React, { useEffect, useState } from 'react'
import { IProduct } from '../types'
import { ProductsContext } from './context';
import Home from './Home';

export function Main() {
    const [products,setProducts] = useState<IProduct[]>([]);
    useEffect(() => {
        const fetchData = async () =>{
            try {
                const res = await fetch('https://fakestoreapi.com/products');
                const result = await res.json();
                console.log(result);
                const newResult = result.map((rs : IProduct) => ({...rs,isVisible : true}));
                setProducts(newResult);
            } catch (err) {
                console.error(err);
            }
        };
        fetchData(); 
    },[])
  return (
    <ProductsContext.Provider value={products}>
        <Home/>
    </ProductsContext.Provider>
  )
}
