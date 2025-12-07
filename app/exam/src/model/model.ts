export type Question = {
  title: string;
  answers: string[];
  correctAnswer: number;
  score: number;
  myAnswer?: number;
};

export type ExamResult = {
  totalScore: number;
};
