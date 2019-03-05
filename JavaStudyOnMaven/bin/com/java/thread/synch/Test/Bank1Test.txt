package com.java.thread.synch;

/**
 * 1. 不加锁机制 - 不加锁的银行系统
 * This program shows data corruption when multiple threads access a data structure.
 * 缺点：
 *     不能支持并行访问，若并行访问则可能造成总金额出现流失的现象
 */
public class Bank1Test {
	public static final int NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;
	
	public static void main(String[] args) {
		Bank b = new Bank(NACCOUNTS, INITIAL_BALANCE);
		
		for(int i = 0; i < NACCOUNTS; i++){
			TransferRunnable r = new TransferRunnable(b, i, INITIAL_BALANCE);
			Thread t = new Thread(r);
			t.start();
		}
	}
}

/**
 * A bank with a number of bank accounts.
 */
class Bank{
	private final double[] accounts;
	
	/**
	 * Constructs the bank.
	 * @param n the number of accounts
	 * @param initialBalance the initial balance for each account.
	 */
	public Bank(int n, double initialBalance){
		accounts = new double[n];
		for(int i = 0; i < accounts.length; i++){
			accounts[i] = initialBalance;
		}
	}
	
	/**
	 * Transfers money from one account to another.
	 * @param from the account to transfer from.
	 * @param to the account to transfer to.
	 * @param amount the amount to transfer.
	 */
	public void transfer(int from, int to, double amount){
		if(accounts[from] < amount)
			return;
		System.out.print(Thread.currentThread());
		accounts[from] -= amount;
		System.out.printf("\t%10.2f from %d to %d", amount, from, to);
		accounts[to] += amount;
		System.out.printf("\tTotal Balance: %10.2f%n", getTotalBalance());
	}
	
	/**
	 * Gets the sum of all account balances.
	 * @return the total balance.
	 */
	public double getTotalBalance(){
		double sum = 0;
		for(double a : accounts){
			sum += a;
		}
		return sum;
	}
	
	/**
	 * Gets the number of accounts in the bank.
	 * @return the number of accounts.
	 */
	public int size(){
		return accounts.length;
	}
}

/**
 * A runnable that transfers money from an account to other accounts in a bank.
 */
class TransferRunnable implements Runnable{
	private Bank bank;
	private int fromAccount;
	private double maxAmount;
	private int DELAY = 10;
	
	/**
	 * Constructs a transfer runnable.
	 * @param b the bank between whose account money is transferred.
	 * @param from the account to transfer money from.
	 * @param max the maximum amount of money in each transfer.
	 */
	public TransferRunnable(Bank b, int from, double max){
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}
	
	public void run(){
		try{
			while(true){
				int toAccount = (int) (bank.size() * Math.random());
				double amount = 10;//maxAmount * Math.random();
				bank.transfer(fromAccount, toAccount, amount);
				Thread.sleep((int) (DELAY * Math.random()));
			}
		}catch(InterruptedException e){
			
		}
	}
}