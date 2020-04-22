### Week03 - Summary

在软件开发的过程中，我们经常需要面对的一个难题就是用户不断变化的需求。有时候，当用户需求出现了微小的改动，我们的代码就需要进行大面积的修改甚至是重构。因此，Java8引进了一些新的编程思想和功能，来帮助我们面对这样的变化，其中就有行为参数化以及Lambda表达式。

比如有一个装有Apple的List，起初用户希望能把里面为红色的苹果筛选出来，我们的实现方式就可以是这样

```java
public List<Apple> filterRedApple(List<Apple> apples) {
    List<Apple> result = new LinkedList<>();
    for (Apple apple : apples) {
        if (apple.getColor().equals("RED")) {
            result.add(apple);
        }
    }
    return result;
}
```

一段时间以后，用户希望把功能修改为筛选大于100克的苹果，我们就需要再另外再加一个方法，如下

```java
public List<Apple> filterHeaveyApple(List<Apple> apples) {
	List<Apple> result = new LinkedList<>();
	for (Apple apple : apples) {
		if (apple.getWeight() > 100) {
            result.add(apple);
        }
	}
    return result;
}
```

对比以上两段代码，我们可以发现他们存在着大量的重复，唯一的不同之处，除了方法名，就只是if后面的条件语句了，因此，我们能不能构造一个类似于模板的方法，将条件作为参数，传入模板里进行执行呢？这就要用到行为参数化这一思想了。

按照这种思路，我们需要把用来执行判断的代码，单独写到一个方法内。再构造一个方法，它接收两个参数，其中一个是我们要操作的对象，另外一个则是需要对对象执行的操作，而在函数体内，我们用传递进来的代码，对对象执行一个判断，如果符合，则将它添加到结果集中，并最终输出。

```java
public List<Apple> filterApple(List<Apple> apples, Predicate predicate) {
	List<Apple> result = new LinkedList<>();
    for (Apple apple : apples) {
        if (predicate.test(apple)) {
            result.add(apple);
        }
    }
}
```

在这里，predicate是一个接口，其中有一个名为test的方法，其返回值是boolean类型。

接下来，则需要用一个类来实现这一接口，并以参数的形式传递到filterApple这个方法里。

当用户的需求为筛选红色苹果时，我们可以这样实现

```java
Public RedApplePredicate implements Predicate {
    public static boolean test(Apple apple) {
        if (apple.getColor().equals("RED")) {
            return true;
        }
    }
}
```

然后以这样的方式调用

```
List<Apple> result = filterApple(apples, RedApplePredicate);
```

这样一来我们就实现了用于判断的代码于实际执行的代码的解耦。

但是我们发现，大量重复代码的问题依然存在，如果用户将需求改变为筛选苹果重量，那么我们还是要实现一个几乎和RedApplePredicate一样的类，并只对其中用于判断的部分进行修改，这个问题我们可以通过用行为参数化的思想配合Lambda表达式来解决。

我们刚才已经定义好了List<Apple> filterApple(List<Apple> apples, Predicate predicate);这个方法，通过Lambda表达式，我们可以通过一行语句一次性的实现实例化接口、参数传递以及执行的过程。

```
List<Apple> result = filterApple(apples, (Apple apple) -> (apple.getColor().equals("RED")));
```

Lambda表达式的语法大概就是

```
(输入参数) -> (表达式)
```

其中输入参数可以没有，也可以将参数的类型省略，因为编译器会自动推断其类型。

我通过三个例子来对以上知识点进行了实现，第一个位于week03.apple这个包内，主要是实现了以不同的格式来对列表里的Apple进行格式化输出。第二个位于week03.student包，它将数据库中的学生读取出来，并分别将历史学院、数学学院以及学分大于50的学生筛选出来。第三个则是在week03.function包中，读取一个字符串组成的List，然后将其中每个字符串的长度加入到另一个List中并返回，这个例子还用到了泛型方法相关的知识点。

#### 数据库密码配置
> * 1、在 Spring Boot 里配置数据库的时候，我们是将数据库的 URL，账号密码 直接以明文的形式添加到 application.properties 里面的，在企业里进行开 发的时候我们也是这样做的吗?感觉似乎有点不安全

#### answer：
> * 1、在实际的企业开发中，数据库的用户名、密码及一些重要的配置是需要加密存储的，目前使用的加密的算法是 jasypt 加解密。如果项目中有使用到数据库连接池如druid，那么这个连接池会自带数据库用户名，密码的的加解密
    具体参考：https://blog.csdn.net/jeikerxiao/article/details/96480136
