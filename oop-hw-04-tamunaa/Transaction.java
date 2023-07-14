// Transaction.java
/*
 (provided code)
 Transaction is just a dumb struct to hold
 one transaction. Supports toString.
*/
public class Transaction {
	public int from;
	public int to;
	public int amount;
	
   	public Transaction(int from, int to, int amount) {
		this.from = from;
		this.to = to;
		this.amount = amount;
	}

	public int getSource(){
		   return from;
	}

	public int getDestination(){
		   return to;
	}

	public int getAmount(){
		   return amount;
	}

	@Override
	public String toString() {
		return("from: " + from + " to: " + to + " amount: " + amount);
	}
}
