import { ModalContext } from "@/hooks/useModal";
import { ConfirmDialog } from "primereact/confirmdialog";
import {
  PropsWithChildren,
  useCallback,
  useMemo,
  useRef,
  useState,
} from "react";

export function ModalProvider(props: PropsWithChildren) {
  const [showDialog, setShowDialog] = useState(false);
  const [title, setTitle] = useState("");
  const [message, setMessage] = useState("");
  const resolveRef = useRef<(v: boolean) => void>();
  const dialog = useMemo(
    () => ({
      showConfirm: (props?: { title?: string; message?: string }) => {
        setShowDialog(true);
        setTitle(props?.title ?? "");
        setMessage(props?.message ?? "");
        return new Promise<boolean>((resolve) => {
          resolveRef.current = resolve;
        });
      },
    }),
    []
  );
  const onClose = useCallback(() => {
    setShowDialog(false);
    resolveRef.current?.(false);
  }, []);
  const onConfirm = useCallback(() => {
    setShowDialog(false);
    resolveRef.current?.(true);
  }, []);

  return (
    <ModalContext.Provider value={{ dialog }}>
      {props.children}
      <ConfirmDialog
        header={title}
        message={<div className="my-5">{message}</div>}
        contentStyle={{ padding: 0 }}
        visible={showDialog}
        accept={onConfirm}
        reject={onClose}
        acceptLabel="确定"
        rejectLabel="取消"
        closeOnEscape={false}
        showCloseIcon={false}
        style={{ minWidth: 300, background: "white" }}
      />
    </ModalContext.Provider>
  );
}
