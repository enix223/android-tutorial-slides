import { defineApp, useAppData } from "umi";
import { SharedContext } from "./service/context";
import { useMemo } from "react";
import { VodService } from "./service/vod";

const App = defineApp({
  rootContainer: (lastRootContainer) => {
    const vodService = new VodService();

    return (
      <SharedContext.Provider value={{ vodService }}>
        {lastRootContainer}
      </SharedContext.Provider>
    );
  },
});

export default App;
