package database;

import java.util.List;

import models.Account;

public interface AccountTableGateway {
	public abstract List<Account> fetchAccounts() throws GatewayException;
}
