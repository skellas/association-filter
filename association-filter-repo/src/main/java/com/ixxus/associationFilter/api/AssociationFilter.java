package com.ixxus.associationFilter.api;

import com.ixxus.associationFilter.cannedquery.AbstractCannedQueryFactory;
import com.ixxus.associationFilter.objects.entities.Association;
import com.ixxus.associationFilter.objects.entities.Filter;

import java.util.List;

/**
 * Created by Ixxus on 11/13/2015.
 */
public interface AssociationFilter {
    public List<Association> getAssociationsByFilters(List<Filter> filters);

    public void setCannedQueryFactory(AbstractCannedQueryFactory cannedQueryFactory);
}
