import { useSharedContext } from "@/context/SharedContext";
import { useLocalObservable } from "mobx-react-lite";

export const useReviewVm = () => {
  const { examService } = useSharedContext();

  return useLocalObservable(() => ({}));
};
