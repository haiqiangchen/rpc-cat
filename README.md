### RPC的选型和应用（java中间件开发：定制RPC组件解决方案）
#### 理论部分
##### 前言
RPC是远程过程调用（Remote Procedure Call）的缩写形式。RPC的概念是1981年Nelson提出。1984年，Birrell和Nelson把其用于支持异构型分布式系统间的通讯。Birrell的RPC 模型引入存根进程( stub) 作为远程的本地代理，调用RPC运行时库来传输网络中的调用。Stub和RPC runtime屏蔽了网络调用所涉及的许多细节，特别是，参数的编码/译码及网络通讯是由stub和RPC runtime完成的，因此这一模式被各类RPC所采用。由于分布式系统的异构性及分布式计算模式与计算任务的多样性，RPC作为网络通讯与委托计算的实现机制，在方法、协议、语义、实现上不断发展，种类繁多。
##### 1、rpc的原理
rpc原理是通过在客户端和服务端之间建立TCP连接，在这个过程中需要解决三个问题：
1. 注册中心寻址问题。
2. TCP传输过程编解码问题。
3. 高性能网络协议IO框架。
##### 2、rpc的适用框架
netty框架是一个高性能的网络协议框架，使用netty最大的好处就是不用关心IO流传输的问题，netty自定制协议，多hander处理机制心跳检测等，总而言之，能想到的网络传输解决方案，netty都有。 netty的使用方法也比较API化

![image](https://pic1.zhimg.com/80/7decee9dca245621988ebb0fea7d2be3_720w.jpg?source=1940ef5c)
##### 3、动态代理
java方面的动态代理最让人津津乐道的就有Cglib代理和jdk代理，这里主要讲解两种方式：

jdk代理
> 1. 通过实现InvocationHandler接口创建自己的调用处理器；
> 2. 通过为Proxy类指定ClassLoader对象和一组interface来创建动态代理；
> 3. 通过反射机制获取动态代理类的构造函数，其唯一参数类型就是调用处理器接口类型；
> 4. 通过构造函数创建动态代理类实例，构造时调用处理器对象作为参数参入；
> 1. JDK动态代理是面向接口的代理模式，如果被代理目标没有接口那么Spring也无能为力，Spring通过Java的反射机制生产被代理接口的新的匿名实现类，重写了其中AOP的增强方法。

CGLIb
> 利用ASM开源包，对代理对象类的class文件加载进来，通过修改其字节码生成子类来处理。

使用动态代理处理相关的反射类型形式，转换相关的处理结果。

##### 4、编码和解码
PRC协议框架是基于TCP协议中，其中传输的内容为二进制的内容，所有可以根据需要定制编解码的协议。netty提供相应的协议定制，如http协议，tcp协议；众所周知，tcp是传输层的协议，http是应用层的协议；在数据传输方面，越靠近底层，性能越快。市场上有比较成熟的集成编解码组件：Protobuf、Kryo、Hessian等等；编解码的主要作用是将对象内容转化为序列化内容。  
![image](https://pica.zhimg.com/80/45366c44f775abfd0ac3b43bccc1abc3_720w.jpg?source=1940ef5c)
##### 5、注册中心
目前springcloud的解决方案eureka+feign,原理实际上差不多，eureka的主要功能还是解决寻址的问题，开箱即用，加上相关的容错原则就比较完美了。选择注册中心的最大的作用是管理服务端和客户端的地址问题，这里实现的轻量级RPC组件，就先暂时使用zookeeper来作为配置中心。
![image](https://dubbo.apache.org/imgs/architecture.png)





