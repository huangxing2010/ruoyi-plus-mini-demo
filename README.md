
- - -

## 系统介绍
小程序授权登录
基于若依管理系统RuoYi-Vue-Plus开发的小程序脚手架，功能不限于小程序授权一键登录以及获取手机号；小程序端基于uview-ui，封装了常用组件，方便开发小程序业务逻辑。

## 功能介绍
* 项目集成了微信授权登录，并做了小程序端导航管理、banner管理，直接在后台设置，即可在首页展示，另外做了文章分类、文章内容管理，以及小程序端用户登录查看数据的场景。适用于微信程序端用户登录查看数据的场景，减少免用户名、密码登录的步骤。
* 源码全部开源，并配备了详细的视频文档，旨在满足小程序业务开发开箱即用的目的

## 开源地址
gitee：https://gitee.com/xnqysabout/ruoyi-plus-mini-demo

github：https://github.com/huangxing2010/ruoyi-plus-mini-demo

## 后端启动配置
1、修改数据库配置文件application-dev.yml
```agsl
          url: jdbc:mysql://localhost:3306/ruoyi-plus-mini-demo?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&autoReconnect=true&rewriteBatchedStatements=true
          username: root
          password: root
```
2、小程序配置文件application-dev.yml
```agsl
# wechat 微信
wechat:
  # 小程序
  mini:
    appId: 你的小程序appId
    secret: 你的小程序secret
    accessTokenKey: MINI-ACCESS-TOKEN-AUTH
```

# 小程序配置
<p align="center">
<img alt="开源版" src="https://github.com/huangxing2010/ruoyi-plus-mini-demo/blob/main/script/demo/web01.png?raw=true">
</p>

## 后台前端启动
### 开发

```bash
# 进入项目目录
cd ruoyi-ui

# 安装依赖
npm install

# 建议不要直接使用 cnpm 安装依赖，会有各种诡异的 bug。可以通过如下操作解决 npm 下载速度慢的问题
npm install --registry=https://registry.npmmirror.com

# 启动服务
npm run dev
```

浏览器访问 http://localhost:8068

### 发布

```bash
# 构建测试环境
npm run build:stage

# 构建生产环境
npm run build:prod
```

## 视频教程
> https://www.bilibili.com/video/BV1SW421w7HM/?vd_source=0ac5540aa42f19843d58667041f62db8

## 软件架构图

<p align="center">
	  <img alt="开源版" src="https://github.com/huangxing2010/ruoyi-plus-mini-demo/blob/main/script/demo/demo01.jpg?raw=true">
	  <img alt="开源版" src="https://github.com/huangxing2010/ruoyi-plus-mini-demo/blob/main/script/demo/hd01.png?raw=true">
	  <img alt="开源版" src="https://github.com/huangxing2010/ruoyi-plus-mini-demo/blob/main/script/demo/hd02.png?raw=true">
	  <img alt="开源版" src="https://github.com/huangxing2010/ruoyi-plus-mini-demo/blob/main/script/demo/hd03.png?raw=true">
	  <img alt="开源版" src="https://github.com/huangxing2010/ruoyi-plus-mini-demo/blob/main/script/demo/hd04.png?raw=true">
	  <img alt="开源版" src="https://github.com/huangxing2010/ruoyi-plus-mini-demo/blob/main/script/demo/hd05.png?raw=true">
</p>

## 贡献代码




## 演示图例
