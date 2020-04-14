package week04.practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Set up
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Trader> traders = Arrays.asList(raoul, mario, alan, brian);
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. Find out all transactions in 2011, and sort them from the lowest one to the highest
        System.out.println("----------Exercise 1----------");
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);
        System.out.println();

        // 2. Find out all cities that traders has worked.
        System.out.println("----------Exercise 2----------");
        traders.stream()
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        // 3. Find all tractors come from Cambridge, and sort them according to name
        System.out.println("----------Exercise 3----------");
        traders.stream()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .map(Trader::getName)
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        // 4. Find out all tractors' name, sort them and concatenate to a string
        System.out.println("----------Exercise 4----------");
        traders.stream()
                .map(Trader::getName)
                .sorted()
                .reduce((name1, name2) -> (name1 + name2))
                .ifPresent(System.out::println);
        System.out.println();

        // 5. Whether there is any trader works in Milan
        System.out.println("----------Exercise 5----------");
        traders.stream()
                .filter(trader -> trader.getCity().equals("Milan"))
                .findAny()
                .ifPresent((trader -> System.out.println("True")));
        System.out.println();

        // 6. Print each transaction value of those tractors who lives in Cambridge
        System.out.println("----------Exercise 6----------");
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);
        System.out.println();

        // 7. Print the highest value of transaction among all transactions
        System.out.println("----------Exercise 7----------");
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);
        System.out.println();

        // 8. Print the lowest value of transaction among all transactions
        System.out.println("----------Exercise 8----------");
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .ifPresent(System.out::println);
    }
}
