import Navbar from "@/components/Navbar";
import { observer } from "mobx-react-lite";
import { Button } from "primereact/button";
import { useReviewVm } from "./vm";
import { RadioButton } from "primereact/radiobutton";

const ReviewPage = observer(() => {
  const vm = useReviewVm();

  return (
    <div className="w-full h-full bg-slate-200 flex flex-col">
      <Navbar leading={"复习"} />

      <div className="flex flex-col flex-1 bg-white p-5 drop-shadow-sm gap-10">
        {vm.questions.map((question) => (
          <div className="flex flex-col gap-5">
            <div className="flex text-2xl">{question.title}</div>
            <div className="flex flex-col gap-3">
              {question.choices.map((choice) => (
                <div className="flex flex-row items-center" key={choice.value}>
                  <RadioButton
                    inputId={choice.inputId}
                    name="choice"
                    value={choice.value}
                    checked={choice.checked}
                  />
                  <label className="ml-2 text-xl" htmlFor={choice.inputId}>
                    {choice.label}
                  </label>
                </div>
              ))}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
});

export default ReviewPage;
