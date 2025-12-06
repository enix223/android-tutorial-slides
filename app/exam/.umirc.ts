import { defineConfig } from "umi";

export default defineConfig({
  routes: [
    { path: "/", component: "index" },
    { path: "/review", component: "review" },
    { path: "/exam", component: "exam" },
  ],
  npmClient: "pnpm",
  tailwindcss: {},
  vite: {},
  plugins: ["@umijs/plugins/dist/tailwindcss"],
});
