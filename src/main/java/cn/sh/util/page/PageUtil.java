package cn.sh.util.page;

import java.io.Serializable;
import java.util.List;

public class PageUtil<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int currentPage;
	
	private int pageSize=10;
	
	private int pageTotal;
	
	private int recordTotal=0;
	
	private int previousPage;
	
	private int nextPage;
	
	private int firstPage=1;
	
	private int lastPage;
	
	private List<T> data;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}

	public int getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(int recordTotal) {
		this.recordTotal = recordTotal;
		init();
	}

	public int getPreviousPage() {
		return previousPage;
	}

	public void setPreviousPage(int previousPage) {
		this.previousPage = previousPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	private void init() {
		//设置第一页
		this.firstPage=1;
		//计算总页数
		this.pageTotal=this.recordTotal % this.pageSize == 0 ? this.recordTotal / this.pageSize: (this.recordTotal / this.pageSize)+1;
		//设置最后一页
		this.lastPage=this.pageTotal;
		//根据当前页来设置上一页
		if(this.currentPage>1) {
			this.previousPage=this.currentPage-1;
		}else {
			this.previousPage=this.firstPage;
		}
		//根据当前页来设置下一页
		if(this.currentPage<this.lastPage) {
			this.nextPage=this.currentPage+1;
		}else {
			this.nextPage=this.lastPage;
		}
	}

	@Override
	public String toString() {
		return "PageUtil [currentPage=" + currentPage + ", pageSize=" + pageSize + ", pageTotal=" + pageTotal
				+ ", recordTotal=" + recordTotal + ", previousPage=" + previousPage + ", nextPage=" + nextPage
				+ ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", data=" + data + "]";
	}
	
	
	
	
	
	
	

}
