import { observer } from "mobx-react-lite";
import { PrimeIcons } from "primereact/api";
import { Button } from "primereact/button";
import { RadioButton } from "primereact/radiobutton";
import { useExamPageVm } from "./vm";
import Navbar from "@/components/Navbar";

const ExamPage = observer(() => {
  const vm = useExamPageVm();
  return (
    <div className="w-full h-full bg-slate-200 flex flex-col">
      {/* header */}
      <Navbar
        leading={vm.headerTitle}
        trailing={<div className="text-red-500 text-bold">{vm.remainTime}</div>}
      />

      {/* body */}
      <div className="flex flex-col flex-1 bg-white p-5 drop-shadow-sm gap-10">
        <div className="flex text-2xl">{vm.questionTitle}</div>
        <div className="flex flex-col gap-8">
          {vm.questionChoices.map((choice) => (
            <div className="flex flex-row items-center" key={choice.value}>
              <RadioButton
                inputId={choice.inputId}
                name="choice"
                value={choice.value}
                onChange={(e) => vm.onChangeChoice(e.value)}
                checked={vm.isChoiceChecked(choice.value)}
              />
              <label className="ml-2 text-xl" htmlFor={choice.inputId}>
                {choice.label}
              </label>
            </div>
          ))}
        </div>

        <div className="flex flex-row gap-2">
          <Button
            visible={vm.showPrev}
            severity="secondary"
            label="上一题"
            icon={PrimeIcons.CARET_LEFT}
            onClick={() => vm.prev()}
          />
          <Button
            visible={vm.showNext}
            severity="secondary"
            label="下一题"
            icon={PrimeIcons.CARET_RIGHT}
            onClick={() => vm.next()}
          />
        </div>
      </div>
    </div>
  );
});

export default ExamPage;
