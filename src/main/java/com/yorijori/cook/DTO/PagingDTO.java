package com.yorijori.cook.DTO;

import java.util.List;

public class PagingDTO<T> {
	// 실제 데이터가 저장될 리스트
	private List<T> list;
	
	private int page;
	private int limit;
	private int listcount;
	
	private int maxpage;
	private int startpage;
	private int endpage;
	private int startrow;
	private int endrow;
	
	public int getStartrow() {
		return startrow;
	}

	public void setStartrow(int startrow) {
		this.startrow = startrow;
	}

	public int getEndrow() {
		return endrow;
	}

	public void setEndrow(int endrow) {
		this.endrow = endrow;
	}

	public PagingDTO(int page, int limit, int listcount) {
		super();
		this.page = page;
		this.limit = limit;
		this.listcount = listcount;
		calc();
	}
	
	private void calc() {
		maxpage = (listcount + limit - 1) / limit;
		startpage = ((page - 1) / 10) * 10 + 1;
		endpage = startpage + 10 - 1;
		
		if (endpage > maxpage)
			endpage = maxpage;
		
		startrow = (page - 1) * limit + 1;
		endrow = startrow + limit - 1;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getListcount() {
		return listcount;
	}

	public void setListcount(int listcount) {
		this.listcount = listcount;
	}

	public int getMaxpage() {
		return maxpage;
	}

	public void setMaxpage(int maxpage) {
		this.maxpage = maxpage;
	}

	public int getStartpage() {
		return startpage;
	}

	public void setStartpage(int startpage) {
		this.startpage = startpage;
	}

	public int getEndpage() {
		return endpage;
	}

	public void setEndpage(int endpage) {
		this.endpage = endpage;
	}

	
}
