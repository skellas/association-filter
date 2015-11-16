package com.ixxus.associationFilter.cannedquery;

import org.alfresco.query.CannedQuery;
import org.alfresco.query.CannedQueryParameters;
import org.alfresco.repo.query.AbstractQNameAwareCannedQueryFactory;

import java.util.List;

/**
 * Created by Ixxus on 11/13/2015.
 */
public abstract class AbstractCannedQueryFactory extends AbstractQNameAwareCannedQueryFactory {

    abstract public <Filter> CannedQuery getCannedQuery(List<com.ixxus.associationFilter.objects.entities.Filter> filters);

    abstract public CannedQuery getCannedQuery(CannedQueryParameters cannedQueryParameters);

}
