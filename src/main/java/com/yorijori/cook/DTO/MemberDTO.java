package com.yorijori.cook.DTO;

public class MemberDTO implements Comparable<MemberDTO>{
	private String MEMBER_ID;
	private String MEMBER_PASSWORD;
	private String MEMBER_NAME;
	private String MEMBER_EMAIL;
	private String MEMBER_PHONE;
	private String MEMBER_RANK;
	
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getMEMBER_PASSWORD() {
		return MEMBER_PASSWORD;
	}
	public void setMEMBER_PASSWORD(String mEMBER_PASSWORD) {
		MEMBER_PASSWORD = mEMBER_PASSWORD;
	}
	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}
	public void setMEMBER_NAME(String mEMBER_NAME) {
		MEMBER_NAME = mEMBER_NAME;
	}
	public String getMEMBER_EMAIL() {
		return MEMBER_EMAIL;
	}
	public void setMEMBER_EMAIL(String mEMBER_EMAIL) {
		MEMBER_EMAIL = mEMBER_EMAIL;
	}
	public String getMEMBER_PHONE() {
		return MEMBER_PHONE;
	}
	public void setMEMBER_PHONE(String mEMBER_PHONE) {
		MEMBER_PHONE = mEMBER_PHONE;
	}
	public String getMEMBER_RANK() {
		return MEMBER_RANK;
	}
	public void setMEMBER_RANK(String mEMBER_RANK) {
		MEMBER_RANK = mEMBER_RANK;
	}
	@Override
	public int compareTo(MemberDTO o) {
		if(this.MEMBER_RANK.equals("마스터"))
			return 1;
		else if(this.MEMBER_RANK.equals("요린이"))
			return -1;
		return 0;
	}
	
}
