import { defineConfig } from "umi";

export default defineConfig({
  title: "Android应用开发考试",
  publicPath: "/exam/",
  routes: [
    { path: "/", component: "index" },
    { path: "/review", component: "review" },
    { path: "/exam", component: "exam" },
    { path: "/score", component: "score" },
  ],
  hash: true,
  history: {
    type: "hash",
  },
  npmClient: "pnpm",
  tailwindcss: {},
  vite: {},
  plugins: ["@umijs/plugins/dist/tailwindcss"],
});
