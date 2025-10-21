import "../assets/main.css";
import "./index.less";
import Aliplayer from "aliyun-aliplayer";
import ThePlayer from "../components/thePlayer/ThePlayer";
import VideoList from "../components/videoList/VideoList";
import { useState, useEffect } from "react";
import "aliyun-aliplayer/build/skins/default/aliplayer-min.css";
import type { PlayInfo } from "../types";
import { useSharedContext } from "@/service/context";

export default function HomePage() {
  const [player, setPlayer] = useState<typeof Aliplayer>(null);
  const [playObj, setPlayObj] = useState<PlayInfo>({
    VideoId: "",
    Description: "",
    Title: "",
    Duration: "",
    CoverURL: "",
  });
  const [videoList, setVideoList] = useState<PlayInfo[]>([]);
  const { vodService } = useSharedContext();

  useEffect(() => {
    vodService.getVideoList().then((res) => {
      setVideoList(res);
      if (res.length === 0) {
        return;
      }

      const item = res[0];
      update(item);
    });
  }, [vodService]);

  const createPlayer = (vid: string, playauth: string, cover: string) => {
    if (player) {
      player.dispose();
    }
    const playerInstance = new Aliplayer(
      {
        vid,
        playauth,
        id: "container",
        width: "100%",
        height: "485px",
        cover: cover,
        license: {
          domain: "course.cloudesk.top",
          key: "SPpuvPIFUBZX3Du3Y04184d51ddd34a5fb364c03ee03c4b51",
        },
        components: [
          {
            name: "MemoryPlayComponent",
            type: window.AliPlayerComponent.MemoryPlayComponent,
            args: [false, getTime, saveTime],
          },
        ],
      },
      (_player: typeof Aliplayer) => {
        //播放下一个视频
        _player.on("ended", () => {
          let index = videoList.findIndex(
            (item) => item.VideoId === playObj?.VideoId
          );
          if (index === -1 || index === videoList.length - 1) {
            return;
          }
          update(videoList[index + 1]);
        });
      }
    );

    setPlayer(playerInstance);
  };

  //点击右侧列表视频切换
  const update = (video: PlayInfo) => {
    setPlayObj(video);
    vodService.getPlayAuth(video.VideoId).then((playAuth) => {
      createPlayer(video.VideoId, playAuth, video.CoverURL);
    });
  };

  // 存储当前播放时间
  const saveTime = function (memoryVideo: string, currentTime: string) {
    localStorage.setItem(memoryVideo, currentTime);
  };

  // 获取此视频上次播放时间
  const getTime = function (memoryVideo: string): string | null {
    return localStorage.getItem(memoryVideo);
  };

  return (
    <div>
      <header>
        <div className="main-content flex items-center">
          <div>Android应用开发</div>
        </div>
      </header>
      <main>
        <div className="main-content content-wrap">
          {/* left */}
          <div className="left-area">
            <ThePlayer playingVideo={playObj} />
          </div>
          {/* right */}
          <div className="right-area">
            <VideoList update={update} videoList={videoList} />
          </div>
        </div>
      </main>
    </div>
  );
}
