import { Question, ExamResult } from "@/model/model";

export type ExamService = {
  getAllQuestions(): Promise<Question[]>;

  startExam(): Promise<void>;

  prevQuestioin(): Promise<Question>;

  nextQuestioin(): Promise<Question>;

  stopExam(): Promise<void>;

  resetExam(): Promise<void>;

  submitQuestionResult(answer?: number): Promise<void>;

  submitExam(): Promise<ExamResult>;

  get remainSeconds(): number;

  get totalSeconds(): number;

  get totalQuestions(): number;

  get currentQuestionNumber(): number;

  get myScore(): number;

  get totalScore(): number;
};
