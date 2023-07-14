// Cracker.java
/*
 Generates SHA hashes of short strings in parallel.
*/

import java.security.*;
import java.util.concurrent.CountDownLatch;

public class Cracker {
	// Array of chars used to produce strings
	public static final char[] CHARS = "abcdefghijklmnopqrstuvwxyz0123456789.,-!".toCharArray();

	private final int  maxLen;
	private final String hash;
	private String decoded = "";
	static CountDownLatch latch;

	
	/*
	 Given a byte[] array, produces a hex String,
	 such as "234a6f". with 2 chars for each byte in the array.
	 (provided code)
	*/
	public static String hexToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i=0; i<bytes.length; i++) {
			int val = bytes[i];
			val = val & 0xff;  // remove higher bits, sign
			if (val<16) buff.append('0'); // leading 0
			buff.append(Integer.toString(val, 16));
		}
		return buff.toString();
	}

	public class Worker extends Thread{
		private int startInd;
		private int len;
		private String result = "";

		public Worker(int startInd, int len){
			this.startInd = startInd;
			this.len = len;
		}
		public void run(){
			for (int i = startInd; i < startInd + len; i++){
				if (!decoded.equals(""))break;

				helper(Character.toString(CHARS[i]));
				if (!result.equals("")){
					decoded = result;
//					System.out.println("vipoveet");
					System.out.println(decoded);
					break;
				}
			}
			latch.countDown();
		}

		private void helper(String cur){
			if (hash.equals(Cracker.hashCode(cur))){
				result = cur;
			}else if (decoded.equals("") && cur.length() < maxLen){
				for (char c : CHARS){
					helper(cur+c);
				}
			}
		}
	}
	public static String hashCode(String string) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA");
			digest.update(string.getBytes());
		} catch (Exception e) {
			System.exit(0);
		}
		return hexToString(digest.digest());
	}

	public Cracker(String hash, int maxLen, int threadNum) {
		this.hash = hash;
		this.maxLen = maxLen;

		int threadLen = CHARS.length / threadNum;
		if (threadLen * threadNum < CHARS.length)threadLen++;

		for (int i = 0; i < threadNum; i++){
			int startId = i * threadLen;
			int endId = (i+1) * threadLen-1;
			if(endId>=CHARS.length)endId = CHARS.length - 1;
			int intervalLen = endId - startId + 1;
//			System.out.println(startId + "  " + endId);

			Worker curr = new Worker(startId, intervalLen);
			curr.start();
		}

	}


	public static void main(String[] args) {
		if (args.length == 1){
			System.out.println(hashCode(args[0]));
			return;
		}
		if (args.length > 3 || args.length < 1) {
			System.out.println("Args: target length [workers]");
			System.exit(1);
		}

		// args: targ len [num]
		String targ = args[0];
		int maxLen = Integer.parseInt(args[1]);
		int threadNum = 1;

		if (args.length>2) {
			threadNum = Integer.parseInt(args[2]);
		}

		latch = new CountDownLatch(threadNum);
		new Cracker(targ, maxLen, threadNum);
		try {
			latch.await();
		} catch (InterruptedException ignored) { }
		System.out.println("All done.");
	}
}
