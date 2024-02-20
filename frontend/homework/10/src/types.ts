export type ListItemType = {
    id: string;
    content: string;
    isValid : boolean;
}
export type ListItemPropsType = {
    content: string;
    id: string;
    listProps: ListPropsType;
}
export type ListPropsType = {
    list : ListItemType[];
    setter : React.Dispatch<React.SetStateAction<ListItemType[]>>
}