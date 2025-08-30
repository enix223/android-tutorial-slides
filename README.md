# 安卓入门教程slides

## 1. 创建新的slides

### 1.1 创建slide

```sh
cd slides
pnpm create sldiev
```

### 1.2 添加依赖

打开新建的项目中的`package.json`，并添加依赖`shared`和`slidev-theme-android-theme`

```json
{
  ...
  "dependencies": {
    ...
    "shared": "workspace:*",
    "slidev-theme-android-theme": "workspace:*",
    ...
  }
}
```

### 1.3 安装依赖

```sh
pnpm i
```

### 1.4 添加执行script

打开根目录的`package.json`，添加如下的dev命令，其中`XX`替换为新的slides序号

```json
{
  ...
  "scripts": {
    ...
    "dev:lessonXX": "pnpm run --filter='lesson-XX-*' dev"
  },
  ...
}
```

### 1.5 运行dev

```sh
pnpm dev:lessonXX
```

## 主题替换

新建的slides主页使用`android-theme`作为主题

slides.md

```
---
theme: android-theme
colorSchema: light
layout: cover
---
```
