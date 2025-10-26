# 什么是 Camera

- **Camera** 是 Android 提供的 **相机接口**，用于拍照、录像、预览等功能。
- Android 相机 API 发展历程：
  - **Camera（旧版）** — 已 **弃用**  
    - API 简单易用，但功能受限  
    - 支持基础预览和拍照，不支持高级参数控制
  - **Camera2（标准 API）**  
    - 提供对 **曝光、对焦、ISO、帧率** 的全面控制  
    - 可处理 **图像流（ImageStream）**，支持高性能应用
  - **CameraX（现代推荐）**  
    - 封装 Camera2，更易用，兼容大多数设备  
    - 支持 **Preview、ImageCapture、ImageAnalysis** 等 UseCase  
    - 可在 Java 和 Kotlin 中使用

- ✅ 大多数应用推荐使用 **CameraX**

---

# Camera 的主要功能

1. 打开相机设备  
2. 实时预览画面  
3. 拍摄照片或录制视频  
4. 保存照片/视频并显示到界面  
5. 可进行图像分析（如二维码扫描、人脸检测等）  

---

# 环境准备

- Android Studio（推荐最新版）

- JDK 11+（Android Studio 内置）

- Gradle 插件与 AndroidX 支持

- 真机调试（模拟器常不支持相机功能）

- 📱 提示：Camera API 在模拟器上表现不稳定，调试时务必使用真机。

---

# Camera2 vs CameraX 工作流程图


<div class="flex flex-col items-center justify-center">
  <img src="/android-camera-1.png" width="550"/>
</div>

- Camera2 需要手动管理 CameraDevice 和 Session  
- CameraX 封装 Camera2，提供 **Preview、ImageCapture、ImageAnalysis** UseCase  
- CameraX 自动绑定生命周期，开发更方便  
