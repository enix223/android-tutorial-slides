export type VodResponse<T> = {
  requestId: string;
  code: string;
  message: string;
  data: T;
};

export type VideoPlayAuthRequest = {
  videoId: string;
};

export type VideoPlayAuthResult = {
  playAuth: string;
};

export type VideoPlayAuthResponse = VodResponse<VideoPlayAuthResult>;

export interface VideoItem {
  videoId: string;
  title: string;
  description: string;
  status: string;
  creationTime: string;
  duration: number;
  coverUrl: string;
  size: number;
  cateId: number;
  cateName: string;
  tags: string;
  snapshots: string[];
  storageLocation: string;
}

export interface VideoListData {
  total: number;
  videoList: VideoItem[];
}

export type VideoListResponse = VodResponse<VideoListData>;
