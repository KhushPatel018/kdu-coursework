import { createContext, useContext } from "react";
import { IProduct } from "../types";

export const ProductsContext = createContext<IProduct[] | undefined >(undefined);
export const SetProductsContext = createContext<React.Dispatch<React.SetStateAction<IProduct[]>> | undefined >(undefined);

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

export const useSetProducts = () => {
    const setProducts = useContext(SetProductsContext);
    if(setProducts === undefined){
        throw new Error('use the useSetProducts with Products context');
    }
    return setProducts;
}
