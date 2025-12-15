import Navbar from "@/components/Navbar";
import { observer } from "mobx-react-lite";
import { Knob } from "primereact/knob";
import { useScoreVm } from "./vm";

const ScorePage = observer(() => {
  const vm = useScoreVm();

  return (
    <div className="w-full h-full bg-slate-200 flex flex-col">
      {/* header */}
      <Navbar leading={"答题得分"} />

      {/* body */}
      <div className="flex flex-col flex-1 bg-white p-10 drop-shadow-sm gap-10 items-center">
        <div className="text-2xl">您的得分</div>
        <Knob max={vm.total} value={vm.score} size={200} />
      </div>
    </div>
  );
});
export default ScorePage;
