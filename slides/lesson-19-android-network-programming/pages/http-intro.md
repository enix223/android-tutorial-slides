# HTTP 是什么？

HTTP（HyperText Transfer Protocol）是 **超文本传输协议**，用于 **客户端（浏览器或 APP）与服务器之间的数据交换**。

特点：

- **无状态**：服务器不会自动记住你是谁  
- **基于请求–响应模型**：客户端发出请求 → 服务器响应  

---

# 一个完整的 HTTP 请求示例

```http
POST /api/login HTTP/1.1
Host: localhost:5173
Content-Type: application/json
Content-Length: 37

{
    "username": "admin",
    "password": "admin"
}
```

HTTP 请求由 4 部分组成：

1. **请求行**  
2. **请求头（Headers）**  
3. **空行**  
4. **请求体（Body）**

---

# 常见的 HTTP 请求方法

| 方法 | 作用 |
|------|------|
| **GET** | 获取资源。参数写在 URL 上 |
| **POST** | 提交数据（登录、注册等） |
| **PUT** | 更新资源（整体替换） |
| **PATCH** | 部分更新资源 |
| **DELETE** | 删除资源 |

---

# 请求头（Headers）

常见请求头：

```http
Content-Type: application/json
Authorization: Bearer <token>
Accept: application/json
```

| Header | 作用 |
|--------|------|
| Content-Type | 请求体格式 |
| Authorization | 身份验证 |
| Accept | 客户端能处理的格式 |

---

# HTTP 响应码

## 成功 / 重定向

| 成功（2xx） | 重定向（3xx） |
|-------------|---------------|
| 200 OK      | 301 永久重定向 |
| 201 Created | 302 临时重定向 |
| 204 No Content | 304 缓存可用 |

---

## 客户端错误 / 服务器错误

| 客户端错误（4xx） | 服务器错误（5xx） |
|------------------|------------------|
| 400 请求错误     | 500 服务器内部错误 |
| 401 未认证       | 502 网关错误      |
| 403 无权限       | 503 服务不可用    |
| 404 Not Found    |                  |

---

# URL 的组成

示例：

```
http://localhost:5173/api/login?username=admin&password=admin#section1
```

| 部分 | 说明 |
|------|------|
| http | 协议 |
| localhost | 域名/IP |
| 5173 | 端口 |
| /api/login | 路径 |
| ?username=… | 查询参数 |
| #section1 | 页面锚点 |

---

# 常见请求体格式

### JSON（最常用）
```json
{
  "username": "admin",
  "password": "admin"
}
```

### 表单格式（application/x-www-form-urlencoded）
```
username=admin&password=admin
```
