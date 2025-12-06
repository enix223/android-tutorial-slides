import { Button } from "primereact/button";

export default function HomePage() {
  return (
    <div className="flex flex-col w-full h-full items-center justify-center gap-20">
      <div className="text-2xl">Android应用开发期末考试</div>
      <div className="flex flex-row items-center justify-center">
        <div className="flex flex-row gap-3">
          <Button size="large">复习</Button>
          <Button size="large">测试</Button>
        </div>
      </div>
    </div>
  );
}
