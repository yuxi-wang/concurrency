package yuxiGroup.yuxiArtifact.concurrency.synch;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class Bank_Volatile {
	private volatile double[] accounts;
	
	public Bank_Volatile (int n, double initialBalance) {
		accounts = new double[n];
		Arrays.fill(accounts, initialBalance);
	}
	
	public  void transfer(int from, int to, double amount) throws InterruptedException{

		while (accounts[from] < amount ) 
			wait();
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf(" %10.2f from %d to %d", amount, from ,to);
		accounts[to] += amount;
		System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());			
			notifyAll();
	}

	public  double getTotalBalance() {
		double sum = 0;
		for (double d :accounts)
			sum += d;
		return sum;
	}
	
	public  int size() {
		return accounts.length;
	}
}
