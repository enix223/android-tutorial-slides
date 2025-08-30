import { App } from "vue";
import { ZoomableImage } from "shared";

export default function setup({ app }: { app: App }) {
  // Register components globally
  app.component("ZoomableImage", ZoomableImage);
}
