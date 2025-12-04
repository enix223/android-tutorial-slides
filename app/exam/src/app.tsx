import { PrimeReactProvider } from "primereact/api";
import { defineApp } from "umi";
import "primereact/resources/themes/lara-light-cyan/theme.css";
import React from "react";

export default defineApp({
  rootContainer(lastRootContainer, args) {
    return React.createElement(PrimeReactProvider, null, lastRootContainer);
  },
});
