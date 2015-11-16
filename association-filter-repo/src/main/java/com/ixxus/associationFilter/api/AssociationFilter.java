package com.ixxus.associationFilter.api;

import com.ixxus.associationFilter.cannedquery.AbstractCannedQueryFactory;
import com.ixxus.associationFilter.objects.entities.Association;
import com.ixxus.associationFilter.objects.entities.Filter;
import com.ixxus.associationFilter.objects.factories.FilterFactory;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

import java.util.List;

/**
 * Created by Ixxus on 11/13/2015.
 */
public interface AssociationFilter {
    List<Association> getAssociationsByFilters(List<Filter> filters);

    List<Filter> getFiltersForTargetOf(List<NodeRef> targetNodeRefs, QName associationType);

    List<Filter> getFiltersWithSourceOf(List<NodeRef> sourceNodeRefs, QName associationType);

    void setCannedQueryFactory(AbstractCannedQueryFactory cannedQueryFactory);

    void setFilterFactory(FilterFactory filterFactory);
}
