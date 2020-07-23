package com.javaex.vo;

public class GalleryVo {
	
	
	private int no;
	private long fileSize;
	private int user_no;
	private String content;
	private String filePath;
	private String orgName;
	private String saveName;
	private String name;
	
	
	public GalleryVo() {}
	
	public GalleryVo(long fileSize, int user_no, String content, String filePath, String orgName,
			String saveName) {
		this.fileSize = fileSize;
		this.user_no = user_no;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
	}




	public GalleryVo(int no, long fileSize, int user_no, String content, String filePath, String orgName,
			String saveName, String name) {
		this.no = no;
		this.fileSize = fileSize;
		this.user_no = user_no;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.name = name;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public long getFileSize() {
		return fileSize;
	}


	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}


	public int getUser_no() {
		return user_no;
	}


	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getSaveName() {
		return saveName;
	}


	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", fileSize=" + fileSize + ", user_no=" + user_no + ", content=" + content
				+ ", filePath=" + filePath + ", orgName=" + orgName + ", saveName=" + saveName + ", name=" + name + "]";
	}



}
