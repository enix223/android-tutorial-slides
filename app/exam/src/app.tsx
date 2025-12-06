import { PrimeReactProvider } from "primereact/api";
import { defineApp } from "umi";

export default defineApp({
  rootContainer(lastRootContainer) {
    return <PrimeReactProvider>{lastRootContainer}</PrimeReactProvider>;
  },
});
