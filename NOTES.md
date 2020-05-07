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



### Week-04 Summary

本周学习了和流相关的概念，以及相关的使用。流可以理解为将多个操作拼接在一起形成的一条流水线，比如给定了一个装有1000个随机数的集合，我们想从这里面先筛选出不大于80的数，再对这些数字进行排序，接着再去除掉它们中所有重复的元素，并最终以List的形式返回给用户。

以往，我们如果想进行这种操作，可能会采用这样的形式

```
List<Integer> result = Distinct(Sort(FilterNumbersLessThanEighty(randomNumberList)));
```

如果是采用流，则可以用这样的方式来完成

```
List<Integer> result = randomNumberList.stream().filter(i -> i <= 80).sort().distinct().collect(toList());
```

虽然看上去两种方式的表达都差不多，但内部实现却有很大差异。

第一种方式采用的是外部迭代，也就是说得由用户自己去定义以什么样的规则去遍历并操作那个集合，并且所有操作都是以串行的方式进行，也就是说必须得等FilterNumbersLessThanEighty()这个函数将所有不大于80的数字筛选完并返回之后，Sort()这个函数才会对它进行排序，然后再是由Distinct()对它们进行去重操作。

而第二种方式的迭代操作则是对用户透明的，用户无法知道也不能干涉程序迭代集合的方式，这一切完全是由程序自己决定的。另外，这些操作也会以并行的方式进行，也就是说，不必等filter()这一函数执行完成，后面的sort()函数便可以获取到一部分它操作产生的结果，然后对这部分结果进行排序，此时distinct()将会从这些结果中去除掉重复的元素再交给collect()来将它们组成列表。

这一点可以在week04.filter.FilterDistinctNumberLessThanEighty.java这个程序里体会得到，这个程序首先实现了RandomNumberSupplier这样一个不断产生随机数的类，然后FilterDistinctNumberLessThanEighty在main方法里用它不断产生新的随机数，再筛选出100个位于0到80之间的数字并对它们去重再输出。

这个程序将会运行很长一段时间，在运行的过程中你会发现这些数字是每隔一小段时间就会输出一个，这就说明filter(),limit(),distince()这些方法还没执行完的时候，foreach()就已经开始对它们进行打印了。如果我们不采用流的话，那我们看到的效果将会是程序在很长一段时间里没有任何输出，并在最后一刻一下子输出全部的结果。

Stream这个对象将一些常用的操作集成在了内置方法里，比如filer()接收一个布尔表达式，将判断结果为true的对象返回；map()则是提供从一种数据类型到另一种数据类型之间的一种映射，比如传入一个字符串，并返回它的长度；而reduce()就是对数据进行聚合，比如获取一个数组里的最大值、最小值，或是总和等等。

我在week04.practice这个包里完成了书中的一些练习题，就是利用流相关的操作来从Trader与Transaction这两个对象的集合里面筛选出符合条件的对象，这一点和模拟数据库的增删改查比较相似。



### Week-07 Summary

在将实现了Collection接口的实例对象转换为流之后，我们就可以在此之上实现一些类似于数据库的操作了，比如：显示一个对象的其中一种或几种属性，将对象根据某一属性进行排序或者分类，等等。我们可以将collect，Collector和Collectors配合使用来达到这一目的，其中collect是stream对象的一个方法，参数为一个收集器；Collector是一个接口，接口里的方法定义了如何对流执行归约操作；Collectors则是一个包含了很多常用静态工厂方法的实用类，我们可以对其直接进行调用。

Collectors里面有一些Int数组常用的方法，比如：计数count，查找最大值maxBy，查找最小值minBy，求和summingInt，求平均数avaragingInt，IntSummaryStatistics方法则可以一次性的计算出以上五个数值；另外，把后三个方法中的Int替换为Double或者Long，就能适用于Double和Long数组了。对于字符串，也有joining方法用于连接。

其实以上几种归约函数都是可以利用reducing工厂方法来完成的，该方法包含了三个参数，第一个是归约操作的起始值，第二个就是对Stream中每一个对象执行的操作，也就是一个转换函数，第三个参数就是指定对操作之后的结果所执行的操作。

如同数据库一样，我们也可以通过groupingBy方法来对流中的所有对象进行分组，这一方法接收一个lambda表达式作为转换函数，转换后值相等的对象会被分进同一组里。

还有另一种操作被称为分区，它是分组的特殊情况，在分组中，转换函数执行的结果可以有很多种，而流中的对象也会被分为很多组；而对于分区来说，它的执行结果是一个布尔值，也就是最多只会被分为两组。

尽管Collectors已经给我们提供了诸多常用方法，但在某些场景中也不一定能满足我们的需要，因此我们可以通过实现Collector接口来自定义收集器，该接口中有五个方法：第一个supplier方法用于建立一个容器来存储结果，第二个accumulator用于定义要对对象执行的归约操作，第三个finisher方法则是将中间过程转换为最终的结果并返回，由于Stream内部采用的是并行的方式来运行，所以还需要在combiner中指明这些并行过程中产生的多个结果集应该如何进行合并；最后还有characteristics方法用于定义流是否可以进行归约以及如何做优化的一些提示。

如果像让多核处理器的每一个核都承担一部分计算任务的话，就可以通过parallel()方法来将流转换为并行流，只有在parallel()之后的方法才会以并行的方式运行。

在week07这一目录下有关于本周所学知识的代码示例，其中DataSource负责读取数据库里的内容，并建立一个Student对象组成的List，以供后续使用。