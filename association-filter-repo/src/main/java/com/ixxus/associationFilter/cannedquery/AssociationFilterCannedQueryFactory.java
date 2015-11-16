package com.ixxus.associationFilter.cannedquery;

import org.alfresco.query.CannedQuery;
import org.alfresco.query.CannedQueryParameters;
import org.alfresco.util.ParameterCheck;

import java.util.List;

/**
 * Created by Ixxus on 11/13/2015.
 */
public class AssociationFilterCannedQueryFactory extends AbstractCannedQueryFactory {
    @Override public <Filter> CannedQuery getCannedQuery(List<com.ixxus.associationFilter.objects.entities.Filter> filters) {
        ParameterCheck.mandatoryCollection("filters", filters);
        AssociationFilterQueryParameters parameterBean = new AssociationFilterQueryParameters(filters);
        CannedQueryParameters parameters = new CannedQueryParameters(parameterBean);
        return getCannedQuery(parameters);
    }
    @Override public CannedQuery getCannedQuery(CannedQueryParameters cannedQueryParameters) {
        return new AssociationFilterCannedQuery(cannedQueryDAO, methodSecurity, cannedQueryParameters);
    }
}
