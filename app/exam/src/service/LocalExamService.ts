import { ExamResult, Question } from "@/model/model";
import questions from "@/model/exam.json";
import { useLocalObservable } from "mobx-react-lite";

export const useLocalExamService = () => {
  return useLocalObservable(() => ({
    timer_: null as NodeJS.Timeout | null,
    elapsed_: 0,
    totalTimes_: 7200,
    questions_: [] as Question[],
    currentIndex_: 0,
    score: 0,

    get totalSeconds(): number {
      return this.totalTimes_;
    },

    setQuestions(v: Question[]) {
      this.questions_ = v;
    },

    get remainSeconds() {
      return this.totalTimes_ - this.elapsed_;
    },

    get totalQuestions() {
      return this.questions_.length;
    },

    get currentQuestionNumber(): number {
      return this.currentIndex_ + 1;
    },

    increaseElapsed() {
      this.elapsed_++;
    },

    async getAllQuestions(): Promise<Question[]> {
      return questions;
    },

    async startExam(): Promise<void> {
      const q = await this.getAllQuestions();
      this.setQuestions(this.shuffle(q));
      this.timer_ = setInterval(() => {
        this.increaseElapsed();
      }, 1000);
      this.currentIndex_ = -1;
    },

    async prevQuestioin(): Promise<Question> {
      if (this.currentIndex_ >= 1) {
        this.currentIndex_--;
      }
      return this.questions_[this.currentIndex_];
    },

    async nextQuestioin(): Promise<Question> {
      if (this.currentIndex_ < this.totalQuestions - 1) {
        this.currentIndex_++;
      }
      return this.questions_[this.currentIndex_];
    },

    async stopExam(): Promise<void> {
      if (this.timer_) {
        clearInterval(this.timer_);
      }
    },

    async resetExam(): Promise<void> {
      this.currentIndex_ = -1;
      this.elapsed_ = 0;
      this.questions_ = [];
    },

    async submitQuestionResult(answer?: number): Promise<void> {
      this.questions_[this.currentIndex_].myAnswer = answer;
    },

    async submitExam(): Promise<ExamResult> {
      this.score = this.questions_
        .map((q) => (q.correctAnswer === q.myAnswer ? q.score : 0))
        .reduce((p, c) => p + c);
      return { totalScore: this.score };
    },

    shuffle<T>(arr: T[]): T[] {
      const shuffle = [...arr];
      for (let i = shuffle.length - 1; i > 0; i--) {
        const randomIndex = Math.floor(Math.random() * (i + 1));
        [shuffle[i], shuffle[randomIndex]] = [shuffle[randomIndex], shuffle[i]];
      }
      return shuffle;
    },
  }));
};
