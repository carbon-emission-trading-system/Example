# miaosha-all

#### 介绍
慕课网 SpringBoot构建电商基础秒杀项目

有问题可以联系QQ 349032805 午夜屠猪男

#### 软件架构
后端:
https://www.imooc.com/learn/1079

前端用了Vuejs,比jQuery方便很多.这个不重要,这课程主要是学习后端的技术,前端用什么都可以.


#### 安装教程

1. 安装Nodejs,去Nodejs中文网比较方便: http://nodejs.cn/?d=123 具体步骤百度
2. 前端项目编辑器,可以使用visualstudio,这个比较好用
3. 在前端项目根目录下打开cmd窗口

npm install

一般情况下npm可能会下载缓慢,所以可以安装cnpm淘宝镜像:
http://npm.taobao.org/

安装: npm install -g cnpm --registry=https://registry.npm.taobao.org

删除Node包之后重新执行:

cnpm install

npm run dev

----------------------------------------------分割线-------------------------------------

### 附:一些笔记

搜到别人的练习项目,完完全全跟着老师敲的:

https://github.com/WeiLeiming/seckill

https://github.com/chenkai0912/miaosha

####  mybatis-generatorx.xml生成地址:
http://www.mybatis.org/generator/configreference/xmlconfig.html

####  开发中跨域问题

SpringBoot提供 @CrossOrigin注解 以协助前后端跨域问题的解决
虽然真实项目开发中用不到,因为前端采用nodejs做了本地的代理请求,
线上则是Nginx做中间的代理

@CrossOrigin 注解在Controller类上

@CrossOrigin(allowCreddentials="true",allowHeaders="*")

所有请求的前缀加了api开头,为了前端做匹配拦截.

####  CommonReturnType
改了一下这个类,个人比较喜欢成功返回code 0,和message一起放在最外层. 如果没有数据体,CommonReturnType就不传null了.

####  密码加密
也可以使用别的方式进行加密,如commons-codec jar包,DigestUtils.md5Hex(password)

####  一个提问
学生提问:
temService的list方法
itemService的list方法，你是遍历一次，转化，然后stock是在遍历的时候查询，
这里只因为方便视频演示吗？还是实际开发中也是这么干的吗，不影响性能吗？
如果实际开发中不是这么干的，请问是怎么干的？ 

老师回答:
你的建议很好，实际项目应用中根据item id查询item库存应该开一个批量查询的方法，
类似于select * from item_stock where item_id in (?,?) 这样的，
如此一来查询item批量的时候也可是使用一条sql语句完成itemstock的查询，
注意在内存中组装模型时要考虑排序问题

所以按老师说的试了试: GoodsServiceImpl.listGoods方法