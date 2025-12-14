import { createContext, useContext } from "react";

export type ModalContextType = {
  dialog: {
    showConfirm: (props?: {
      title?: string;
      message?: string;
    }) => Promise<boolean>;
  };
};

export const ModalContext = createContext<ModalContextType>({
  dialog: {
    async showConfirm() {
      return false;
    },
  },
});

export const useModal = () => useContext(ModalContext);
