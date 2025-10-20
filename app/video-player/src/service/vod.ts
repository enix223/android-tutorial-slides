import axios, { AxiosInstance } from "axios";
import {
  VideoItem,
  VideoListResponse,
  VideoPlayAuthRequest,
  VideoPlayAuthResponse,
} from "./types";
import { PlayInfo } from "@/types";

export class VodService {
  private axiosInstance: AxiosInstance;

  constructor() {
    this.axiosInstance = axios.create();
  }

  async getVideoList(): Promise<PlayInfo[]> {
    try {
      const resp = await this.axiosInstance.get<VideoListResponse>(
        "https://vod-getdeo-list-ggkrwepugm.cn-shenzhen.fcapp.run"
      );
      if (resp.data.code !== "200") {
        console.error("获取播放列表失败", resp.data.message);
        throw "获取播放列表失败";
      }
      return resp.data.data.videoList.map((e) => ({
        VideoId: e.videoId,
        CoverURL: e.coverUrl,
        Title: e.title,
        Duration: this.formatDuration(e.duration),
        Description: e.description,
      }));
    } catch (e) {
      throw "获取播放列表失败";
    }
  }

  async getPlayAuth(videoId: string): Promise<string> {
    try {
      const data: VideoPlayAuthRequest = { videoId };
      const resp = await this.axiosInstance.post<VideoPlayAuthResponse>(
        "https://vod-getplayauth-bsiizirfku.cn-shenzhen.fcapp.run",
        data
      );
      if (resp.data.code !== "200") {
        console.error("获取播放地址失败", resp.data.message);
        throw "获取播放地址失败";
      }
      return resp.data.data.playAuth;
    } catch (e) {
      throw "获取播放地址失败";
    }
  }

  formatDuration(
    seconds: number,
    options: {
      showHours?: boolean;
      showMilliseconds?: boolean;
      separator?: string;
    } = {}
  ): string {
    const {
      showHours = true,
      showMilliseconds = false,
      separator = ":",
    } = options;

    const totalSeconds = Math.floor(seconds);
    const milliseconds = Math.round((seconds - totalSeconds) * 1000);

    const hours = Math.floor(totalSeconds / 3600);
    const minutes = Math.floor((totalSeconds % 3600) / 60);
    const remainingSeconds = totalSeconds % 60;

    const parts: string[] = [];

    if (showHours || hours > 0) {
      parts.push(hours.toString().padStart(2, "0"));
    }

    parts.push(minutes.toString().padStart(2, "0"));
    parts.push(remainingSeconds.toString().padStart(2, "0"));

    let result = parts.join(separator);

    if (showMilliseconds && milliseconds > 0) {
      result += `.${milliseconds.toString().padStart(3, "0")}`;
    }

    return result;
  }
}
