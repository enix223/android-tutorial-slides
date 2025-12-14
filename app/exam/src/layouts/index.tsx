import { Outlet } from "umi";
import "./index.less";
import { SharedContext } from "@/context/SharedContext";
import { useLocalExamService } from "@/service/LocalExamService";
import { ModalProvider } from "@/components/ModalProvider";

export default function Layout() {
  const examService = useLocalExamService();

  return (
    <SharedContext.Provider value={{ examService }}>
      <ModalProvider>
        <div className="w-screen h-screen">
          <Outlet />
        </div>
      </ModalProvider>
    </SharedContext.Provider>
  );
}
