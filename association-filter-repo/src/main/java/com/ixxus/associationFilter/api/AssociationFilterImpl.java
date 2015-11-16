package com.ixxus.associationFilter.api;

import com.ixxus.associationFilter.cannedquery.AbstractCannedQueryFactory;
import com.ixxus.associationFilter.objects.entities.Association;
import com.ixxus.associationFilter.objects.entities.Filter;
import org.alfresco.query.CannedQuery;
import org.alfresco.query.CannedQueryResults;

import java.util.List;

/**
 * Created by Ixxus on 11/13/2015.
 */
public class AssociationFilterImpl implements AssociationFilter {
    private AbstractCannedQueryFactory cannedQueryFactory;

    @Override public List<Association> getAssociationsByFilters(List<Filter> filters) {
        CannedQuery<Association> cannedQuery = cannedQueryFactory.getCannedQuery(filters);
        CannedQueryResults<Association> results = cannedQuery.execute();

        return results.getPage();
    }

    @Override public void setCannedQueryFactory(AbstractCannedQueryFactory cannedQueryFactory) {
        this.cannedQueryFactory = cannedQueryFactory;
    }

}
