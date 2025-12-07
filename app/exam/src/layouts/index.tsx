import { Outlet } from "umi";
import "./index.less";
import { SharedContext } from "@/context/SharedContext";
import { useLocalExamService } from "@/service/LocalExamService";

export default function Layout() {
  const examService = useLocalExamService();

  return (
    <SharedContext.Provider value={{ examService }}>
      <div className="w-screen h-screen">
        <Outlet />
      </div>
    </SharedContext.Provider>
  );
}
