import { createContext, useContext } from "react";
import { ListItemType } from "./types";

export const ListContext = createContext<ListItemType[] | undefined>(undefined);

export const SetListContext = createContext<
  React.Dispatch<React.SetStateAction<ListItemType[]>> | undefined
>(undefined);

export const ContentContext = createContext<string | undefined>(undefined);

export const IdContext = createContext<string | undefined>(undefined);

export const useList = () => {
  const list = useContext(ListContext);

  if (list === undefined) {
    throw new Error("useList can only be used with List context");
  }
  return list;
};

export const useSetList = () => {
  const setList = useContext(SetListContext);
  if (setList === undefined) {
    throw new Error("useSetList can only be used with SetListContext");
  }
  return setList;
};
export const useContent = () => {
  const content = useContext(ContentContext);
  if (content === undefined) {
    throw new Error("useContent can only be used with ContentContext");
  }
  return content;
};
export const useId = () => {
  const id = useContext(IdContext);
  if (id === undefined) {
    throw new Error("useId can only be used with IdContext");
  }
  return id;
};
