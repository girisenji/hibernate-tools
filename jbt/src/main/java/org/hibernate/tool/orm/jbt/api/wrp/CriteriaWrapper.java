package org.hibernate.tool.orm.jbt.api.wrp;

import java.util.List;

import org.hibernate.tool.orm.jbt.wrp.Wrapper;

public interface CriteriaWrapper extends Wrapper {
	
	void setMaxResults(int intValue);
	List<?> list();

}