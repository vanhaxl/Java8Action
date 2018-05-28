package lambdasinaction.chap5;

import java.util.*;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class PuttingIntoPractice {
	public static void main(String... args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));

		// Query 1: Find all transactions from year 2011 and sort them by value (small
		// to high).
		// Normal
		List<Transaction> result = new ArrayList<>();
		for (Transaction transaction : transactions) {
			if (transaction.getYear() == 2011) {
				result.add(transaction);
			}
		}
		Collections.sort(result, (t1, t2) -> new Comparator<Transaction>() {
			@Override
			public int compare(Transaction t1, Transaction t2) {
				return Integer.compare(t1.getValue(), t2.getValue());
			}
		}.compare(t1, t2));
		System.out.println(result);

		// Use Java 8
		List<Transaction> result2 = transactions.stream().filter(t -> t.getYear() == 2011)
				.sorted(comparing(Transaction::getValue)).collect(toList());
		System.out.println(result2);

		// Query 2: What are all the unique cities where the traders work?
		// normal
		List<String> uniqueCityList = new ArrayList<>();
		Set<String> set = new HashSet<>();
		for (Transaction t : transactions) {
			set.add(t.getTrader().getCity());
		}
		Iterator iterator = set.iterator();
		while (iterator.hasNext()) {
			uniqueCityList.add((String) iterator.next());
		}
		System.out.println(uniqueCityList);

		// use Java 8
		List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct()
				.collect(toList());
		System.out.println(cities);

		// Query 3: Find all traders from Cambridge and sort them by name.
		// normal
		List<Trader> traderList = new ArrayList<>();
		for (Transaction transaction : transactions) {
			if (transaction.getTrader().getCity() == "Cambridge" && !traderList.contains(transaction.getTrader())) {
				traderList.add(transaction.getTrader());
			}
		}
		Collections.sort(traderList, (t1, t2) -> new Comparator<Trader>() {
			@Override
			public int compare(Trader t1, Trader t2) {
				return t1.getName().compareTo(t2.getName());
			}
		}.compare(t1, t2));
		System.out.println(traderList);

		// use Java 8
		List<Trader> traders = transactions.stream().map(Transaction::getTrader)
				.filter(trader -> trader.getCity() == "Cambridge").distinct().sorted(comparing(Trader::getName))
				.collect(toList());
		System.out.println(traders);
		
		// Query 4: Return a string of all traders’ names sorted alphabetically.

		// Query 5: Are there any trader based in Milan?

		// Query 6: Update all transactions so that the traders from Milan are set to
		// Cambridge.

		// Query 7: What's the highest value in all the transactions?

	}
}