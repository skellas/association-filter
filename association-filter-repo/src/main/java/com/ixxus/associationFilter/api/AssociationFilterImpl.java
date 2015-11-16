package com.ixxus.associationFilter.api;

import com.ixxus.associationFilter.cannedquery.AbstractCannedQueryFactory;
import com.ixxus.associationFilter.objects.entities.Association;
import com.ixxus.associationFilter.objects.entities.Filter;
import com.ixxus.associationFilter.objects.factories.FilterFactory;
import org.alfresco.query.CannedQuery;
import org.alfresco.query.CannedQueryResults;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ixxus on 11/13/2015.
 */
public class AssociationFilterImpl implements AssociationFilter {
    private AbstractCannedQueryFactory cannedQueryFactory;
    private FilterFactory filterFactory;

    @Override public List<Association> getAssociationsByFilters(List<Filter> filters) {
        CannedQuery<Association> cannedQuery = cannedQueryFactory.getCannedQuery(filters);
        CannedQueryResults<Association> results = cannedQuery.execute();

        return results.getPage();
    }

    @Override public List<Filter> getFiltersForTargetOf(List<NodeRef> targetNodeRefs, QName associationType) {
        List<Filter> filters = new ArrayList<>(targetNodeRefs.size());
        for (NodeRef targetNodeRef : targetNodeRefs) {
            filters.add(filterFactory.getFilter(null, associationType, targetNodeRef));
        }
        return filters;
    }
    @Override public List<Filter> getFiltersForTargetOf(List<String> targetNodeRefs, String associationType) {
        List<Filter> filters = new ArrayList<>(targetNodeRefs.size());
        for (String targetNodeRef : targetNodeRefs) {
            filters.add(filterFactory.getFilter("", associationType, targetNodeRef));
        }
        return filters;
    }

    @Override public List<Filter> getFiltersWithSourceOf(List<NodeRef> sourceNodeRefs, QName associationType) {
        List<Filter> filters = new ArrayList<>(sourceNodeRefs.size());
        for (NodeRef sourceNodeRef : sourceNodeRefs) {
            filters.add(filterFactory.getFilter(sourceNodeRef, associationType, null));
        }
        return filters;
    }
    @Override public List<Filter> getFiltersWithSourceOf(List<String> sourceNodeRefs, String associationType) {
        List<Filter> filters = new ArrayList<>(sourceNodeRefs.size());
        for (String sourceNodeRef : sourceNodeRefs) {
            filters.add(filterFactory.getFilter(sourceNodeRef, associationType, ""));
        }
        return filters;
    }

    @Override public void setCannedQueryFactory(AbstractCannedQueryFactory cannedQueryFactory) {
        this.cannedQueryFactory = cannedQueryFactory;
    }
    @Override public void setFilterFactory(FilterFactory filterFactory) { this.filterFactory = filterFactory; }

}
