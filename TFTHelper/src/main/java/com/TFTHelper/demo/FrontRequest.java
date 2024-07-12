package com.TFTHelper.demo;

import java.util.List;

public class FrontRequest {
	private List<String> championList;

	public FrontRequest(List<String> championList) {
		super();
		this.championList = championList;
	}
	
	public List<String> getChampionList() {
		return championList;
	}

	public void setChampionList(List<String> championList) {
		this.championList = championList;
	}

	public int countSize() {
		int result = 0;
		result = this.championList.size();
		return result;
	}
	
}
