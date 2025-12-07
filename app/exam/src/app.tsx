import { PrimeReactProvider } from "primereact/api";
import { defineApp } from "umi";
import "primeicons/primeicons.css";

export default defineApp({
  rootContainer(lastRootContainer) {
    return <PrimeReactProvider>{lastRootContainer}</PrimeReactProvider>;
  },
});
