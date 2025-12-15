import { useSharedContext } from "@/context/SharedContext";
import { useLocalObservable } from "mobx-react-lite";
import { useEffect } from "react";

export const useScoreVm = () => {
  const { examService } = useSharedContext();
  const vm = useLocalObservable(() => ({
    total: 0,
    score: 0,

    reload() {
      this.total = examService.totalScore;
      this.score = examService.myScore;
    },
  }));

  useEffect(() => {
    vm.reload();
  }, []);

  return vm;
};
