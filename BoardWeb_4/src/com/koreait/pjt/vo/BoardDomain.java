package com.koreait.pjt.vo;

public class BoardDomain extends BoardVO {
	private String nm;
	private int yn_like;

	public int getYn_like() {
		return yn_like;
	}

	public void setYn_like(int yn_like) {
		this.yn_like = yn_like;
	}

	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}
}
