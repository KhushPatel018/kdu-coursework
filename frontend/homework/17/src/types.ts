export interface IProduct {
    id:          number;
    title:       string;
    price:       number;
    description: string;
    category:    string;
    image:       string;
    rating:      Rating;
    isVisible : boolean;
}

export interface Rating {
    rate:  number;
    count: number;
}

export type productSliceType = {
    products : IProduct[],
    loading : boolean,
    error : string
}

export type changeVisibilityType = {
    id : number,
    visibility : boolean
}