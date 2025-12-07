import { useSharedContext } from "@/context/SharedContext";
import { Question } from "@/model/model";
import { useLocalObservable } from "mobx-react-lite";
import { useEffect } from "react";

type QuestionVm = Question & {
  choices: {
    label: string;
    value: number;
    inputId: string;
    checked: boolean;
  }[];
};

export const useReviewVm = () => {
  const { examService } = useSharedContext();

  const vm = useLocalObservable(() => ({
    questions_: [] as QuestionVm[],

    set questions(v: QuestionVm[]) {
      this.questions_ = v;
    },

    get questions() {
      return this.questions_;
    },

    async refresh() {
      const questions = await examService.getAllQuestions();
      this.questions = questions.map((e, idx) => ({
        ...e,
        title: `${idx + 1}. ${e.title}`,
        choices: e.answers.map((a, i) => ({
          label: a,
          value: i,
          inputId: `choice${i}`,
          checked: i === e.correctAnswer,
        })),
      }));
    },
  }));

  useEffect(() => {
    vm.refresh();
  }, []);

  return vm;
};
