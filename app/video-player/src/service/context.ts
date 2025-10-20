import { createContext, useContext } from "react";
import { VodService } from "./vod";

type SharedContextType = {
  vodService: VodService;
};

export const SharedContext = createContext<SharedContextType>(
  {} as unknown as SharedContextType
);

export const useSharedContext = () => useContext(SharedContext);
