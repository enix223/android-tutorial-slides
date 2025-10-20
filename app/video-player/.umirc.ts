import { defineConfig } from "umi";

export default defineConfig({
  base: "/androidv",
  publicPath: "/androidv/",
  routes: [{ path: "/", component: "index" }],
  hash: true,
  headScripts: [
    "https://player.alicdn.com/lib/aliplayercomponents-1.1.1.min.js",
  ],
  npmClient: "pnpm",
  tailwindcss: {},
  plugins: ["@umijs/plugins/dist/tailwindcss"],
});
