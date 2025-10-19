import { defineConfig } from "umi";

export default defineConfig({
  routes: [{ path: "/", component: "index" }],
  headScripts: [
    "https://player.alicdn.com/lib/aliplayercomponents-1.1.1.min.js",
  ],
  npmClient: "pnpm",
});
