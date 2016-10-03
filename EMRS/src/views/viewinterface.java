package views;

import models.MasterModel;

public interface viewinterface {
	public void HideallView();
	public MasterModel getMasterModel();
	public void ShowView();
	public void reload();
	public HomeView getHomeView();

}
