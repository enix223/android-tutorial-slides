import { useSharedContext } from "@/context/SharedContext";
import { useModal } from "@/hooks/useModal";
import { Question } from "@/model/model";
import { useLocalObservable } from "mobx-react-lite";
import { useEffect } from "react";
import { sprintf } from "sprintf-js";

export const useExamPageVm = () => {
  const { examService } = useSharedContext();
  const { dialog } = useModal();

  const vm = useLocalObservable(() => ({
    currentQuestion_: null as Question | null,

    set currentQuestion(v: Question) {
      this.currentQuestion_ = v;
    },

    get headerTitle() {
      return `题目 ${examService.currentQuestionNumber}/${examService.totalQuestions}`;
    },

    get questionTitle() {
      return this.currentQuestion_?.title ?? "-";
    },

    get questionChoices() {
      return (
        this.currentQuestion_?.answers.map((a, i) => ({
          value: i,
          label: a,
          inputId: `choice${i}`,
        })) ?? []
      );
    },

    get showPrev() {
      return examService.currentQuestionNumber > 1;
    },

    get showNext() {
      return examService.currentQuestionNumber < examService.totalQuestions;
    },

    isChoiceChecked(value: number) {
      return this.currentQuestion_?.myAnswer === value;
    },

    // 剩余时间
    get remainTime(): string {
      const remains = examService.remainSeconds;
      const h = Math.floor(remains / 3600);
      const m = Math.floor((remains - h * 3600) / 60);
      const s = remains - h * 3600 - m * 60;
      return sprintf("%02d小时%02d分%02d秒", h, m, s);
    },

    onChangeChoice(value: number) {
      if (!this.currentQuestion_) {
        return;
      }
      this.currentQuestion_.myAnswer = value;
    },

    async next() {
      if (this.currentQuestion_) {
        await examService.submitQuestionResult(this.currentQuestion_.myAnswer);
      }
      const question = await examService.nextQuestioin();
      this.currentQuestion = question;
    },

    async prev() {
      if (this.currentQuestion_) {
        await examService.submitQuestionResult(this.currentQuestion_.myAnswer);
      }
      const question = await examService.prevQuestioin();
      this.currentQuestion = question;
    },

    submit() {
      dialog
        .showConfirm({ title: "提示", message: "是否确定提交答卷?" })
        .then(async (ok) => {
          if (ok) {
            const res = await examService.submitExam();
            console.log("total score", res.totalScore);
          }
        });
    },

    async start() {
      await examService.startExam();
      await this.next();
    },

    async stop() {
      examService.stopExam();
    },
  }));

  useEffect(() => {
    vm.start();

    return () => {
      vm.stop();
    };
  }, []);

  return vm;
};
