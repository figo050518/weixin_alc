package com.fcgo.weixin.uitl;

import javax.servlet.http.HttpServletRequest;

import com.fcgo.weixin.common.dto.Page;

public class PaginationContext {

	private int pagesNumber = 2;//分页栏页面个数，即分页栏显示几个页面链接
	private Page page ;//分页对象
	
	public PaginationContext(Page page) {
		super();
		this.page = page;
	}
	public int getPagesNumber() {
		return pagesNumber;
	}
	public void setPagesNumber(int pagesNumber) {
		this.pagesNumber = pagesNumber;
	}
	public int getCurrentPage() {//当前分页栏页
		return page.getPageIndex()%this.pagesNumber == 0 ? page.getPageIndex()/this.pagesNumber:page.getPageIndex()/this.pagesNumber+1;
	}
	public int getTotalPages() {//分页栏页数
		return page.getTotal()%this.pagesNumber == 0 ? page.getTotal()/this.pagesNumber:page.getTotal()/this.pagesNumber+1;
	}
	public int getPageIndex() {//当前页
		return page.getPageIndex();
	}
	public int getStartPage() {//分页栏从第几页开始
		int toatalPages = page.getTotal();
		int p = this.getCurrentPage();
		int start = p*this.pagesNumber-this.pagesNumber+1;
		return start;
	}
	public int getEndPage() {//分页栏到第几页结束
		int toatalPages = page.getTotal();
		int p = this.getCurrentPage();
		int end = p*this.pagesNumber+1;
		end = end>= toatalPages? toatalPages:end;//取最小的
		return end;
	}
	public int getStartIndex(){//当前页记录开始
		return (page.getPageIndex() - 1) * page.getPageSize();
	}
	public int getEndIndex(){//当前页记录结束
		return page.getPageIndex()*page.getPageSize();
	}
	public int getRecords(){//当前页记录
		return page.getRecords();
	}
	public boolean isShowPre(){//分页栏是否显示上一页
		return this.getStartPage() != 1;
	}
	public boolean isShowNext(){//分页栏是否显示下一页
		
		return this.getEndPage() != page.getTotal();
	}
}
