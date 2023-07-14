// Account.java

/*
 Simple, thread-safe Account class encapsulates
 a balance and a transaction count.
*/
public class Account {
	private final int id;
	private int balance;
	private int transactions;
	
	// It may work out to be handy for the account to
	// have a pointer to its Bank.
	// (a suggestion, not a requirement)
	private Bank bank;  
	
	public Account(Bank bank, int id, int balance) {
		this.bank = bank;
		this.id = id;
		this.balance = balance;
		transactions = 0;
	}

	public synchronized void makeTransaction(int transactionAmount){
		balance -= transactionAmount;
		transactions++;
	}

	@Override
	public synchronized String toString(){
		return "id: " + id + " balance: " + balance + " trans: " + transactions;
	}

	public int getId(){
		return id;
	}

	public int getBalance(){
		return balance;
	}
	
}
