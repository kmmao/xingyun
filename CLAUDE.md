# CLAUDE.md

这个文件为Claude Code (claude.ai/code)在此仓库中工作提供指导。

## 项目概述

星云ERP是一个基于SpringBoot的开源进销存ERP系统，采用多模块Maven架构。主要为中小企业提供完整的业务管理解决方案，包括采购、销售、库存、结算等核心功能。

## 技术栈

- **后端框架**: SpringBoot 2.2.2.RELEASE
- **ORM**: MyBatis-plus 3.4.2
- **认证**: Sa-Token
- **文档**: Knife4j (Swagger)
- **缓存**: Redis (用于Session和缓存)
- **消息队列**: RabbitMQ 3.12.4
- **数据库**: MySQL 5.7.18
- **工作流**: Warm-Flow
- **底层框架**: jugg 4.0.6 (自研starter集合)

## 模块架构

项目采用多模块架构，各模块职责如下：

- **xingyun-api**: 主应用入口和API接口层，包含SpringBoot启动类
- **xingyun-core**: 核心工具类、组件和消息队列处理
- **xingyun-basedata**: 基础数据模块(仓库、供应商、客户、商品等)
- **xingyun-sc**: 供应链模块(采购、销售、零售、库存管理)
- **xingyun-settle**: 结算管理模块(费用单、预付款、对账等)
- **xingyun-chart**: 报表图表模块
- **xingyun-comp**: 通用组件模块
- **cloud/**: 微服务相关模块(网关、云端API)

## 常用命令

### 构建和运行
```bash
# 编译整个项目
mvn clean compile

# 打包整个项目
mvn clean package

# 运行API服务(默认端口8080)
cd xingyun-api && mvn spring-boot:run

# 或运行jar包
java -jar xingyun-api/target/xingyun-api.jar
```

### 环境配置
项目支持多环境配置：
- `dev` (默认): 开发环境
- `test`: 测试环境  
- `prod`: 生产环境

使用 `-Dspring.profiles.active=prod` 指定环境

### Docker部署
```bash
# 构建Docker镜像
docker build -t xingyun-api ./xingyun-api/

# 运行容器
docker run -p 8080:8080 xingyun-api
```

## 代码约定

### 后端开发约定
- cacheName不使用`{}`占位符
- 主库名称统一为`master`
- 创建子线程时使用`DefaultCallable`或`DefaultRunnable`包装
- 数据库操作自动填充创建人/修改人信息

### 包结构约定
每个业务模块遵循统一的包结构：
```
com.lframework.xingyun.{module}/
├── bo/          # 业务对象
├── controller/  # 控制器层
├── dto/         # 数据传输对象
├── entity/      # 实体类
├── enums/       # 枚举类
├── excel/       # Excel相关
├── impl/        # 服务实现类
├── mappers/     # MyBatis映射
├── service/     # 服务接口
└── vo/          # 视图对象
```

## 多租户支持

通过`application.yml`中的`jugg.tenant.enabled`控制多租户模式：
- `true`: 开启多租户
- `false`: 关闭多租户

注意：两种模式数据库结构不同，运行时不支持动态切换。

## API文档

启动后访问Knife4j文档：http://localhost:8080/doc.html

## 重要配置

### Sa-Token认证配置
- Token名称: `X-Auth-Token`
- Token有效期: 30天
- 临时有效期: 2小时
- 支持并发登录

### 缓存配置
- 默认TTL: 1800秒
- 特殊缓存TTL可在`jugg.cache.regions`中配置

### 文件上传限制
- 单文件最大: 10MB
- 总请求最大: 100MB

## 底层依赖

项目依赖自研底层框架jugg，提供以下starter：
- web-starter: Web相关功能
- mybatis-starter: 数据库操作
- redis-starter: 缓存功能
- rabbitmq-starter: 消息队列
- security-starter: 安全认证

框架源码：https://gitee.com/lframework/jugg