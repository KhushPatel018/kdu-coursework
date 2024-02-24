import { createContext, useContext } from "react";
import { IProduct } from "../types";

export const ProductsContext = createContext<IProduct[] | undefined >(undefined);

export const useProducts = () => { 
    const products = useContext(ProductsContext);
    if(products === undefined){
        throw new Error('use the useProducts with Products context');
    }
    return products;
}

export const useProductById = (id : number) => {
    const products = useProducts();
    return products.find(product => product.id === id);
}
