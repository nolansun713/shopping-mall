## 												小苏商城操作说明

### 1 .   条例说明：

本项目开源，可随便食用，但是不可用为商业途径，否则将追究一切责任

### 2 . 功能说明：

- 仿华为商城的小苏商城（后台开发和前后端对接）

- 后台开发语言 Java，框架Springboot，mybatis，mysql数据库，redis数据库。

- 收货地址，热销商品，最新商品，加入购物车，确认订单，商品分页展示等功能。

    

### 3. 项目部署：

将配置文件修改自己的数据库和邮箱地址，maven加载依赖即可食用，将resources中sql文件运行即可创建数据表。

### 4   .项目说明：

该项目采用redis进行了验证码存储，以及首页热销商品和最新商品的缓存加载，减少了mysql数据库的压力,商品列表的分页展示，添加购物车，确认订单，等等功能,。

### 5  .redis相关问题:

- 首页经典商品和热销商品利用redis缓存加载，减轻mysql数据库的压力。
- redis缓存击穿的处理：针对缓存击穿查询不到的key，采用的是：查询不到时，设置一个null值给redis，防止大量的查询给到数据库。
- redis的缓存雪崩的处理：利用key的有效期设置不同，来处理缓存在同一时间失效，减轻查询数据库是带来压力。

### 6.  项目的示例图：

![](https://github.com/AlanKafka/xiaosu-shop/blob/master/imges/1.png)

![](https://github.com/AlanKafka/xiaosu-shop/blob/master/imges/2.png)

![](https://github.com/AlanKafka/xiaosu-shop/blob/master/imges/3.png)

![](https://github.com/AlanKafka/xiaosu-shop/blob/master/imges/4.png)

![5](https://github.com/AlanKafka/xiaosu-shop/blob/master/imges/5.png)
