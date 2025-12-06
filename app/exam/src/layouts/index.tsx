import { Outlet } from "umi";
import "./index.less";

export default function Layout() {
  return (
    <div className="w-screen h-screen">
      <Outlet />
    </div>
  );
}
