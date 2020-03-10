# 叁度


### 约定

erp 小程序  后台接口的开发
erp 小程序 前后台联调



1. 版本约定
    
    > jdk 版本为 1.8.0_191，不允许使用大版本不一致的JDK，不允许使用OpenJDK
    
    
2. 字符集约定

    > 全部项目（JAVA，VUE等）必须使用 UTF-8 编码格式
    
    > 数据库字符集必须为 UTF-8 ，排序规则必须为 utf8_general_ci 
    
    > 数据库表引擎必须为 InnoDB
    
3. 版本管理
    
    > 开发人员可修改 .gitignore 文件但不允许提交
    
    > 严禁提交除代码文件之外的其它文件，尤其注意 target 、 .idea 、 *.iml 等文件
    
    > 每次 commit 需要明确到具体文件，必须标注提交原因 
    
4. 开发规范

    > 项目根目录下提供以下三个开发规范文档，要求开发人员必须严格遵守此三个开发文档要求进行开发
    
    > 阿里巴巴Java开发手册(终极版).pdf
    
    > MYSQL 数据库设计规范.docx
    
    > 接口设计规范说明书.docx
    
### 注意事项

    本项目使用 lombok 简洁开发，开发人员需要在自己的 IDEA 中安装 lombok 插件，
    否则使用到 lombok 的地方会提示异常
    项目中全部的时候类型均使用JDK8新增时间类型Instant
    全部的主键ID均是使用雪花算法自动生成，所以数据库中字段必须为bigint（19）

### 目录结构

    common 中仅能放置全局通用的一些代码，若代码与某个业务模块强耦合则不允许放入本包下
    
    config 全部项目中的配置，模块内部不允许再进行配置
    
    handler 处理类，模块内部不允许定义全局处理，全局处理必须放在本目录下
    
    其它的同级包均为业务模块分类，具体业务模块内部所需要的POJO，枚举应都要定义到自己的模块包下

1. common(通用代码)
    
    > enums(全局通用枚举)
    
    > exception(全局自定义异常)
    
    > model(全局通用POJO)
    
    > util(全局通用工具类)
    
2. config(全局配置)

3. handler(全局处理)
    
    

### 命令

1. 启动 jar 命令 nohup java -jar ***.jar > ***.txt &

2.查看日志 tail -f -n 100 ***.txt
