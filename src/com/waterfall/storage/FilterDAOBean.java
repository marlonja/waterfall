package com.waterfall.storage;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.waterfall.models.DropModel;
import com.waterfall.models.FilterModel;

@Stateful
public class FilterDAOBean {

	@PersistenceContext
	private EntityManager em;

	public void storeFilterAsPool(FilterModel filterModel) {
		em.merge(filterModel);
	}

	@SuppressWarnings("unchecked")
	public List<FilterModel> getAllFilters() {
		return em.createNamedQuery("FilterModel.findAll").getResultList();

	}

	public FilterModel getFilterById(Long filterid) {
		FilterModel filterModel = (FilterModel) em.createNamedQuery("FilterModel.findFilterById").setParameter("filterid", filterid).getSingleResult();
		return filterModel;
	}
}
