package DTO;

import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ListComputerParameter {
	private String pageIterator;
	private String taillePage;
	private String search;
	private String order;

	public ListComputerParameter(String pageIterator, String taillePage, String search, String order) {
		this.pageIterator = pageIterator;
		this.taillePage = taillePage;
		this.search = search;
		this.order = order;
	}

	public String getPageIterator() {
		return pageIterator;
	}

	public void setPageIterator(String pageIterator) {
		this.pageIterator = pageIterator;
	}

	public String getTaillePage() {
		return taillePage;
	}

	public void setTaillePage(String taillePage) {
		this.taillePage = taillePage;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder()
				.append(this.order)
				.append(this.pageIterator)
				.append(this.search)
				.append(this.taillePage)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListComputerParameter other = (ListComputerParameter) obj;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (pageIterator == null) {
			if (other.pageIterator != null)
				return false;
		} else if (!pageIterator.equals(other.pageIterator))
			return false;
		if (search == null) {
			if (other.search != null)
				return false;
		} else if (!search.equals(other.search))
			return false;
		if (taillePage == null) {
			if (other.taillePage != null)
				return false;
		} else if (!taillePage.equals(other.taillePage))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);

	}
	
	
}