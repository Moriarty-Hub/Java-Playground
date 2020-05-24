### 网络资产信息管理系统 &《Java实战》Sample code

开发环境：IntelliJ IDEA, Mysql 8.0, JDK1.8

系统简介：该系统主要是用于管理需要进行扫描的设备的列表，以及对扫描结果使用用户友好的方式展示出来，系统中设置了管理员与普通用户这两种角色，前者可以对待探测设备和用户进行增添和删除，后者则只能查看扫描结果以及个人信息。需要与网络资产信息收集系统配套使用，它通过爬取开源POC来检查局域网设备是否存在漏洞，并将扫描结果保存在数据库中

------

### Week 9

#### 完成内容：

1. 完成《Java实战》第十二章
2. 完成《Java并发编程实战》第一、二章
3. 阅读《Effective Java》

#### 遇到的问题：

1. 如何为多线程方法编写测试用例？我测试NotThreadSafeCounter和ThreadSafeCounter这四个类的的方式是创建1000个线程，每个线程对counter变量做10000累加，根据结果是否等于10000000来判断其是否是线程安全的，但是在实际测试过程中，我发现即便是对于非线程安全的类，其测试结果仍然有一定概率等于10000000，因此我不确定我采用的测试思路是否正确。



### Week 6

#### 完成内容：

1. 完善毕设项目，为User, Target这两个对象增加更多的字段，如Department, Job Title, Owner等，重构了这个系统的业务逻辑，并新增了用户设置/上传头像的功能。

#### 遇到的问题：

1.	在Controller.ManageTargetController.java这个文件中的updateTarget()这个方法里（第42-48行），我在返回页面的时候直接把Get请求的参数直接加到了URL里面，运行过程中没有发现什么问题，但是IDEA报错，那么正确的做法应该是怎样的呢？
2.	还是在同一方法里的第47行的位置，我给model添加了isOwnerExist这一参数，但估计是因为页面重定向的缘故，这一attribute并没有被传递过去，应该怎么纠正呢？
3.	我给这个系统设置了用户头像上传以及显示的功能，在开发过程中，我发现对于前端页面而言，它们的根目录是src/main/java/resources/static这个目录，这似乎就意味着我只能把要用于前端页面显示的图片存到这个目录以下的位置，而不能是磁盘的其他位置，是不是我采用的方法有问题呢？因为按理来说应该是磁盘上任一路径的图片都可以被读取到的，而不应该是被限制在这一文件夹下面（在profile.html这个文件中，当@{${avatar}}的值为/avatar/default.jpg的时候，读取的是src/main/java/resources/static/avatar/default.jpg这个文件，我由此推断出了它的根目录）



### Week 4

#### 完成内容：

1.	《Java实战》第四、五章
2.	学习了如何通过Spring Security组件自带的功能代替原有的登录验证，并对用户进行了普通用户与管理员这两种角色的区分，使管理员拥有了删除用户留言的功能
3.	完成了毕设项目的部分功能，将前几周学到的知识都用到了这里面

#### 遇到的问题：

1. 在Spring Boot里配置数据库的时候，我们是将数据库的URL，账号密码直接以明文的形式添加到application.properties里面的，在企业里进行开发的时候我们也是这样做的吗？感觉似乎有点不安全

    Answer: 在实际的企业开发中，数据库的用户名、密码及一些重要的配置是需要加密存储的，目前使用的加密的算法是 jasypt 加解密。如果项目中有使用到数据库连接池如druid，那么这个连接池会自带数据库用户名，密码的的加解密 具体参考：https://blog.csdn.net/jeikerxiao/article/details/96480136



### Week 3

#### 完成内容：

1.	《Java实战》前三章内容
2.	Mybatis的增删改查方法
3.	学习Spring Security的使用，了解如何用它进行身份验证和访问控制
4.	毕设项目的整体流程与数据库设计



### Week 2

#### 完成内容：

1. 对MessageBoard进行了重构，增加了Service层
2. 继续完善了MessageBoard，为所有Controller及Service编写了测试代码

#### 遇到的问题：

1. 感觉目前我所掌握的mockito里的测试方法还非常有限，有些我所关心的数据还不知道该怎么测试。比如在BoardServiceTest.java这个文件的 testGetAllMessageOrderByCreateTimeDesc()方法中，我只测试了messageRepository.save()这个方法执行了多少次，但是如果在BoardService.java 的getAllMessageOrderByCreateTimeDesc()方法中我误把message.setCreator()的参数设为了messageContent，由于类型一致，在编译阶段并不会报错，运行时也不会出错， 应该怎么检测出来呢？还是说只能重构源代码才能检测？

    Answer： 1、应该是addMessage方法的测试吧！你写成了testGetAllMessageOrderByCreateTimeDesc，已在BoardServiceTest#testAddMessage中给你加入如何测试的代码 2、测试的方法名、参数命名不规范，IndexServiceTest中的方法名test1VerifyUser、test2VerifyUser；IndexController#register方法的参数password1、password2 3、关于mockito的测试，网上也有很多资料，遇到不会写的或者没接触过的写法，但是自己心里有疑问的，可以上网查

2. 再比如，我在BoardController.java的addMessage方法中给model添加了attribute，我想在测试的时候再对model调用一下getAttribute来检查一下值是否正确，应该怎么办呢？ 尝试过使用@InjectMocks去自动注入Model，但由于它是一个接口，所以这个办法行不通。（我在IndexControllerTest.java的test2LogIn()中尝试了一种办法， 就是在verify中把原来的anystring()替换为我实际期望的值，测试发现可行，但不知道是否符合标准）

    Answer： 1、可以的，只要是验证了你的预期值和最终的实际值是一致的就是OK的，anystring()一般用于不是太关心具体值的时候为了方便可以可以这样写，最严谨的就是 你现在的这种写法，就是在verify中把原来的anyString()替换为我实际期望的值

3. 在IndexControllerTest.java中的这个位置出现了一个报错，显示是第73行中期望的matcher数量和实际输入的不符，该怎么知道到底需要几个matcher以及应该放在参数中的哪个位置呢？ ![image](https://github.com/Moriarty-Hub/Message-Board/blob/master/image-20200405165305184.png) 

    Answer： 1、在方法调用的时候是需要传"具体的参数"的值的，方法调用的时候是正常的程序调用，不属于Mockito的，不能使用any()、anyString()之类的，程序不认识 any()、anyString()这些是搭配Mockito的方法用的，一般用在when、verify、spy等等上的，所以你那样使用自然会报错，多写，多了解就熟悉了



### Week 1

#### 完成内容：

1. 用Spring-Boot框架配合MySQL搭建一个多用户的留言板程序(暂时只关注后端功能，不考虑前端页面美观度)，实现用户的注册，登录，以及查看留言板和发布留言的功能，并部署在云服务器上。

#### 遇到的问题：

1. 不知道该如何对Controller编写测试

    Answer: 参见test目录下的测试代码及注释