package models;

public class Drug {
	
	private String trade;
	private String generic;
	
	public Drug(String param_trade, String param_generic) {
		this.setTrade(param_trade);
		this.setGeneric(param_generic);
	}

	public String getTrade() {
		return trade;
	}

	public void setTrade(String trade) {
		this.trade = trade;
	}

	public String getGeneric() {
		return generic;
	}

	public void setGeneric(String generic) {
		this.generic = generic;
	}

}
