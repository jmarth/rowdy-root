package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import database.AccountTableGateway;
import database.GatewayException;

public class AccountList {
	private List<Account> myList;
	private AccountTableGateway gateway;
	private HashMap<Long, Account> myIdMap;
	
	/**
	 * Create a new AccountList
	 */
	public AccountList() {
		myList = new ArrayList<Account>();
		myIdMap = new HashMap<Long, Account>();
	}
	
	/**
	 * Load records from DB into AccountList
	 */
	public void loadFromGateway() {
		//fetch list of objects from the database
		try {
			List<Account> accounts = gateway.fetchAccounts();
			for(Account tmpAcc: accounts){
				//this.addPartToList(patient);
				myIdMap.put(tmpAcc.getId(), tmpAcc);
			}
		} catch (GatewayException e) {
			//TODO: handle exception here
			return;
		}
	}
	
	/**
	 * Helper method to add an Account to AccountList
	 * @param acc
	 */
	public void addAccountToList(Account acc) {
		myList.add(acc);
	}
	
	/**
	 * Set AccountList gateway
	 * @param gateway AccountTableGateway to set
	 */
	public void setGateway(AccountTableGateway gateway) {
		this.gateway = gateway;
	}
	
	/**
	 * Returns ArrayList of Accounts in the AccountList
	 * @return All Accounts in list
	 */
	public List<Account> getAccountList() {
		return myList;
	}
	
	/**
	 * Find a specific Account by it's ID
	 * @param id ID of Account to find
	 * @return Account with specified ID
	 */
	public Account findById(long id) {
		//check the identity map
		if(myIdMap.containsKey(new Long(id)))
			return myIdMap.get(new Long(id));
		return null;
	}

}
