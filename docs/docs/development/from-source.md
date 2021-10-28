---
template: overrides/main.html
---

#### 必要条件

---

- Java SDK >= 1.8  < 11
- Maven >= 3.5.x
- Node >= 8.x

#### 准备工作

---

> 在源码编译前我们需要将源代码克隆到我们的机器上的某位置

如果是使用https克隆请使用以下代码在控制台进行执行

```bash
git clone https://github.com/EdurtIO/bootstack.git
```

> 需要您在GitHub上配置的您当前使用的电脑密钥

使用git协议进行克隆源码

```bash
git clone git@github.com:EdurtIO/bootstack.git
```

#### 开始编译

---

> 我们将从编译Java服务源码和Web UI源码开始

- 编译java服务源码,首先进入到项目的根目录运行以下命令

```bash
mvn clean install package -DskipTests=true
```

> 需要注意的是我们在编译命令时使用了`-DskipTests=true`跳过测试策略,但是我们不建议您去这么做,如果是为了调试服务可执行该操作

它编译需要一段时间,如果编译期间尚未出现错误,编译完成后将会生成服务的二进制文件,它的位置是

```bash
bootstack/core/target/bootstack-core.jar
```

同时Web的安装包也会被导入到

```bash
bootstack/web/target/bootstack-web.jar
```

##### 单独编译web

---

如果web编译失败,请反馈问题到[GitHub](https://github.com/EdurtIO/bootstack/issues/new/choose),或者尝试以下方式进行模块的单独编译

- 进入源码目录

```bash
/Users/shicheng/Documents/Code/Company/EdurtIO/bootstack/web/src/main/angular
```

执行以下代码进行编译

```bash
yarn run build
```

编译完成后会在dist目录下生成相关文件,需要使用Nginx或者Apache服务器代理即可!
