package com.fcgo.weixin.common.dto;

import java.util.List;
/**
 * 分页调用公用类
 * @author Ww
 *
 */


public class Page {
	/**默认每页数据条数*/
	private static final int DEFAULT_PAGE_SIZE = 5;
	/**默认当前页码*/
	private static final int DEFAULT_CURRENT_PAGE = 1;
	/**每页的数据条数*/
	protected int pageSize;
	/**当前页码*/
	protected int pageIndex;
	/**总记录条数*/
	protected int records;//总记录数
	/**总页数*/
	protected int total;//总页数
	/**分页查询的数据集合*/
	protected List<?> rows;

	public Page(int pageIndex, int pageSize) {
		this.pageIndex = pageIndex;
		if(pageIndex < 0){
			this.pageIndex = 1;
		}
		
		this.pageSize = pageSize;
	}

	public Page(int pageIndex) {
		this.pageIndex = pageIndex;
		if(pageIndex < 0){
			this.pageIndex = 1;
		}
		this.pageSize = DEFAULT_PAGE_SIZE;
	}

	public Page() {
		this.pageIndex = DEFAULT_CURRENT_PAGE;
		this.pageSize = DEFAULT_PAGE_SIZE;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}


	public List<?> getRows() {
		return rows;
	}
	public void setRow(List<?> row) {
		this.rows = row;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}


	public boolean hasPrevious() {
		return pageIndex > 1;
	}

	public boolean hasNext() {
		return pageIndex < getTotal();
	}

	public int getRecords() {
		if (pageSize == -1)
			return rows == null ? 0 : rows.size();
		else
			return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public int getPage() {
		return pageIndex;
	}

	public int getTotal() {

		long remainder = records % this.getPageSize();

		if (0 == remainder) {
			return records / this.getPageSize();
		}

		return records / this.getPageSize() + 1;
	}
}
