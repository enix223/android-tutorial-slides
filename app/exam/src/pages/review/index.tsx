import Navbar from "@/components/Navbar";
import { observer } from "mobx-react-lite";
import { useReviewVm } from "./vm";
import { RadioButton } from "primereact/radiobutton";
import ReactMarkdown from "react-markdown";
import remarkGfm from "remark-gfm";
import rehypeHighlight from "rehype-highlight";

const ReviewPage = observer(() => {
  const vm = useReviewVm();

  return (
    <div className="w-full h-full bg-slate-200 flex flex-col">
      <Navbar leading={"复习"} />

      <div className="flex flex-col flex-1 bg-white p-5 drop-shadow-sm gap-10">
        {vm.questions.map((question) => (
          <div className="flex flex-col gap-5">
            <div className="flex flex-col text-2xl">
              <ReactMarkdown
                remarkPlugins={[remarkGfm]}
                rehypePlugins={[rehypeHighlight]}
              >
                {question.title}
              </ReactMarkdown>
            </div>
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
