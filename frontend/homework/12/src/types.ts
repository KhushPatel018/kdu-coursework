export type ListItemType = {
    id: string;
    content: string;
    isValid : boolean;
}
export type ListItemPropsType = {
    content: string;
    id: string;
}
export type ListPropsType = {
    list : ListItemType[];
    setter : React.Dispatch<React.SetStateAction<ListItemType[]>>
}

export type contextType = {
    list: ListItemType[] | undefined;
    setList: React.Dispatch<React.SetStateAction<ListItemType[]>> | undefined;
  }