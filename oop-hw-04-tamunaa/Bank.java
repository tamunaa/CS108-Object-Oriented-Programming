// Bank.java

/*
 Creates a bunch of accounts and uses threads
 to post transactions to the accounts concurrently.
*/

import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

public class Bank {
	public static final int ACCOUNTS = 20;	 // number of accounts
	private static final int ACCOUNT_BALANCE = 1000; // default balance
	private static final Transaction STOP_TRANSACTION = new Transaction(-1, -1, 0);
	private static final ArrayList<Account> accounts = new ArrayList<>();
	private static final LinkedBlockingQueue<Transaction> qu = new LinkedBlockingQueue<>();
	protected static CountDownLatch latch;

	public class worker extends Thread{
		public void run(){
			while (true){
				try {
					Transaction cur = qu.take();
					if (cur.equals(STOP_TRANSACTION)) break;

					int from = cur.getSource();
					int to = cur.getDestination();
					int amount = cur.getAmount();

					int fromIndx = getIdIndexInAccounts(from);
					int toIndx = getIdIndexInAccounts(to);

					accounts.get(fromIndx).makeTransaction(amount);
					accounts.get(toIndx).makeTransaction(-amount);
				}catch (Exception e){
					System.out.println("nothing serious");
				}
			}

			latch.countDown();
		}
	}

	public Bank(int numWorkers){
		IntStream.range(0, ACCOUNTS)
				.forEach(i -> accounts.add(new Account(this, i, ACCOUNT_BALANCE)));

		ArrayList<worker> workers = new ArrayList<>();

		IntStream.range(0, numWorkers)
				.forEach(i -> workers.add(new worker()));

		workers.forEach(w -> w.start());
	}

	private int getIdIndexInAccounts(int id){
		OptionalInt index = IntStream.range(0, accounts.size())
				.filter(i -> id == accounts.get(i).getId())
				.findFirst();

		return index.orElse(-1);
	}

	/*
	 Reads transaction data (from/to/amt) from a file for processing.
	 (provided code)
	 */
	public static void readFile(String file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			// Use stream tokenizer to get successive words from file
			StreamTokenizer tokenizer = new StreamTokenizer(reader);
			
			while (true) {
				int read = tokenizer.nextToken();
				if (read == StreamTokenizer.TT_EOF) break;  // detect EOF
				int from = (int)tokenizer.nval;
				
				tokenizer.nextToken();
				int to = (int)tokenizer.nval;
				
				tokenizer.nextToken();
				int amount = (int)tokenizer.nval;
				
				// Use the from/to/amount
				Transaction newTransaction = new Transaction(from, to, amount);
				qu.put(newTransaction);
			}

		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/*
	 Processes one file of transaction data
	 -fork off workers
	 -read file into the buffer
	 -wait for the workers to finish
	*/
	public static void processFile(String file, int numWorkers) throws InterruptedException {
		latch = new CountDownLatch(numWorkers);
		readFile(file);
		IntStream.range(0, numWorkers).forEach(i -> qu.add(STOP_TRANSACTION));
		latch.await();
	}

	
	
	/*
	 Looks at commandline args and calls Bank processing.
	*/
	public static void main(String[] args) throws InterruptedException {
		// deal with command-lines args
		if (args.length == 0) {
			System.out.println("Args: transaction-file [num-workers [limit]]");
			System.exit(1);
		}
		String file = args[0];
		int numWorkers = 1;

		if (args.length >= 2) {
			numWorkers = Integer.parseInt(args[1]);
		}

		Bank bank = new Bank(numWorkers);
		processFile(file, numWorkers);

//		System.out.println("aq shemovidaaaa yeeeeaaay");
		accounts.forEach(i -> System.out.println(i.toString()));
	}
}

