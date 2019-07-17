package yuxiGroup.yuxiArtifact.concurrency.synch;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class Bank_Lock {
	private final double[] accounts;
	private ReentrantLock bankLock = new ReentrantLock();
	
	public Bank_Lock (int n, double initialBalance) {
		accounts = new double[n];
		Arrays.fill(accounts, initialBalance);
	}
	
	public void transfer(int from, int to, double amount) {
		
		bankLock.lock();
		try {
			if(accounts[from] < amount )return;
			System.out.print(Thread.currentThread());
			accounts[from] -= amount;
			System.out.printf(" %10.2f from %d to %d", amount, from ,to);
			accounts[to] += amount;
			System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());	
		}finally {
			bankLock.unlock();
		}

	}

	public double getTotalBalance() {
		double sum = 0;
		for (double d :accounts)
			sum += d;
		return sum;
	}
	
	public int size() {
		return accounts.length;
	}
}
