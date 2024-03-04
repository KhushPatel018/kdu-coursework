import { ListItemType } from "../../types";

export const useLocalStorage = (key: string) => {
  const getItem = () : ListItemType[] | undefined => {
    try {
      const item = window.localStorage.getItem(key);
      return item ? JSON.parse(item) : undefined;
    } catch (err) {
      console.log(err);
    }
  };
  const setItem = (value: ListItemType[]) => {
    try {
      window.localStorage.setItem(key, JSON.stringify(value));
    } catch (err) {
      console.log(err);
    }
  };
  const removeItem = () => {
    try {
      window.localStorage.removeItem(key);
    } catch (error) {
      console.log(error);
    }
  };

  return { getItem, setItem, removeItem };
};
