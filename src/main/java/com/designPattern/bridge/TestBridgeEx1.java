package com.designPattern.bridge;

public class TestBridgeEx1 {
	public static void main(String[] args) {
		BankAccount c=new SavingAccount();
		BankUser u=new DollarUser(c);
		u.getMoney();
		
		u = new YuanUser(new CreditAccount());
		u.saveMoney();
	}
}

interface BankAccount{			//Implementor	//只提供基本操作
	void deposit();
	void withdraw();
}
class SavingAccount implements BankAccount{
	public void deposit(){
		System.out.println("SavingAccount -- deposit()");
	}
	public void withdraw(){
		System.out.println("SavingAccount -- withdraw()");
	}
}
class CreditAccount implements BankAccount{
	public void deposit(){
		System.out.println("CreditAccount -- deposit()");
	}
	public void withdraw(){
		System.out.println("CreditAccount -- withdraw()");
	}
}

abstract class BankUser{		//Abstraction	//提供基于Implementor操作的更高层次的操作, 供Client调用
	protected BankAccount account;
	public BankUser(BankAccount account) {
		this.account = account;
	}
	public abstract void saveMoney();
	public abstract void getMoney();
}
class YuanUser extends BankUser{
	public YuanUser(BankAccount account) {
		super(account);
	}
	public void getMoney() {
		System.out.println("YuanUser -- getMoney()");
		account.withdraw();
	}
	public void saveMoney() {
		System.out.println("YuanUser -- saveMoney()");
		account.deposit();
	}
}
class DollarUser extends BankUser{
	public DollarUser(BankAccount account) {
		super(account);
	}
	public void getMoney() {
		System.out.println("DollarUser -- getMoney()");
		account.withdraw();
	}
	public void saveMoney() {
		System.out.println("DollarUser -- saveMoney()");
		account.deposit();
	}
}