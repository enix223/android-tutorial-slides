import { PrimeReactProvider } from "primereact/api";
import { defineApp } from "umi";
import "primereact/resources/themes/lara-light-cyan/theme.css";

export default defineApp({
  rootContainer(lastRootContainer, args) {
    <PrimeReactProvider>{lastRootContainer}</PrimeReactProvider>;
  },
});
