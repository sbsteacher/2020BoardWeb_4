package com.koreait.pjt.vo;

public class BoardDomain extends BoardVO {
	private String nm;
	private int yn_like;
	private int record_cnt; //페이지당 나오는 레코드 수 (글 수)
	private int sIdx;
	private int eIdx;
	
	public int getsIdx() {
		return sIdx;
	}

	public void setsIdx(int sIdx) {
		this.sIdx = sIdx;
	}

	public int geteIdx() {
		return eIdx;
	}

	public void seteIdx(int eIdx) {
		this.eIdx = eIdx;
	}

	public int getRecord_cnt() {
		return record_cnt;
	}

	public void setRecord_cnt(int record_cnt) {
		this.record_cnt = record_cnt;
	}

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
