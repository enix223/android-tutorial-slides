import { PrimeIcons } from "primereact/api";
import { Button } from "primereact/button";
import { useCallback } from "react";
import { history } from "umi";

export default function HomePage() {
  const onReview = useCallback(() => {
    history.push({ pathname: "/review" });
  }, []);

  const onExam = useCallback(() => {
    history.push({ pathname: "/exam" });
  }, []);

  return (
    <div className="flex flex-col w-full h-full items-center justify-center gap-20">
      <div className="text-2xl">Android应用开发期末考试</div>
      <div className="flex flex-row items-center justify-center">
        <div className="flex flex-row gap-3">
          <Button
            size="large"
            onClick={onReview}
            icon={PrimeIcons.BOOK}
            label="复习"
          />
          <Button
            size="large"
            onClick={onExam}
            label="测试"
            icon={PrimeIcons.CLOCK}
          />
        </div>
      </div>
    </div>
  );
}
