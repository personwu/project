package com.oracle.common;

public class PageUtil {
	private int pagesize;//每页的条数
	private int allsize;//所有的条数
	private int last;//
	private int first;
	private int currentpage;//当前页码
	private int lastPage;//最后页码
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	/* 1  1-5
	 * 2  6-10
	 * 3  11-15    
	  */
	public int getPagesize() {
		return pagesize;
	}
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}
	public int getAllsize() {
		return allsize;
	}
	public void setAllsize(int allsize) {
		if (allsize<0){
			this.allsize=0;
		}
		this.allsize = allsize;
	}
	public int getLast() {
		return last;
	}
	public void setLast(int last) {
		this.last = last;
	}
	public int getFirst() {
		return first;
	}
	public void setFirst(int first) {
		this.first = first;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage=currentpage;
		this.lastPage=(int) Math.ceil((double)allsize/pagesize);
		if (lastPage<=0){
			this.lastPage=1;
		}
		if(currentpage>lastPage)
			this.currentpage=lastPage;
		if(currentpage<1)
			this.currentpage=1;
		this.first=(this.currentpage-1)*this.pagesize+1;
		this.last=this.currentpage*this.pagesize;
}
	public PageUtil(){}
	public PageUtil(int pagesize){
		this.pagesize=pagesize;
	}

	@Override
	public String toString() {
		return "PageUtil{" +
				"pagesize=" + pagesize +
				", allsize=" + allsize +
				", last=" + last +
				", first=" + first +
				", currentpage=" + currentpage +
				", lastPage=" + lastPage +
				'}';
	}
}
