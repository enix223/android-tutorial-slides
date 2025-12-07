import { ExamService } from "@/service/ExamService";
import { createContext, useContext } from "react";

export type SharedContextType = {
  examService: ExamService;
};

export const SharedContext = createContext<SharedContextType>({
  examService: {} as ExamService,
});

export const useSharedContext = () => useContext(SharedContext);
