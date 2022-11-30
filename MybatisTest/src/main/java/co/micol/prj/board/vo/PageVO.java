package co.micol.prj.board.vo;

public class PageVO {
	int totalCnt;
	int pageNum;
	int startPage, endPage, totalPage;
	boolean prev, next;
	
	public PageVO(int totalCnt, int pageNum) {
		this.totalCnt = totalCnt;
		this.pageNum = pageNum;
		
		totalPage = (int)Math.ceil((totalCnt)/10.0)*10;
		//startPage, endPage계산
		this.endPage = (int)Math.ceil(this.pageNum/10.0)*10;
		this.startPage = this.endPage - 9;
		if(this.endPage>totalPage) {
			this.endPage = totalPage;
		}
		//if(this.startPage)
		prev = this.startPage > 10; // 이전페이지.
		next = this.endPage < totalPage; // 다음페이지.
	}
		
	public int getTotalCnt() {
		return totalCnt;
	}
	public int getPageNum() {
		return pageNum;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public boolean isPrev() {
		return prev;
	}
	public boolean isNext() {
		return next;
	}
	@Override
	public String toString() {
		return "PageVO [totalCnt=" + totalCnt + ", pageNum=" + pageNum + ", startPage=" + startPage + ", endPage="
				+ endPage + ", totalPage=" + totalPage + ", prev=" + prev + ", next=" + next + "]";
	}
}
