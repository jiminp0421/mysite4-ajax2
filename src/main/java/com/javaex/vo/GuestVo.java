package com.javaex.vo;

public class GuestVo {
	
	private int no;
	private String name;
	private String pw;
	private String content;
	private String regdate;
	
	
	public GuestVo () {}

	public GuestVo(int no, String name, String pw, String content, String regdate) {
		super();
		this.no = no;
		this.name = name;
		this.pw = pw;
		this.content = content;
		this.regdate = regdate;
	}

	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	
	@Override
	public String toString() {
		return "GuestVo [no=" + no + ", name=" + name + ", pw=" + pw + ", content=" + content + ", regdate=" + regdate
				+ "]";
	}




}
