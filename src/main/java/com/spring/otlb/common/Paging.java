package com.spring.otlb.common;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Paging {

	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int total;
	private Criteria cri;

	public Paging(Criteria _criteria, int _total) {
		this.cri = _criteria;
		this.total = _total;
		this.endPage = (int) (Math.ceil(_criteria.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 10;
		int realEnd = (int) (Math.ceil((_total * 1.0) / _criteria.getAmount()));
		if (realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}

}
