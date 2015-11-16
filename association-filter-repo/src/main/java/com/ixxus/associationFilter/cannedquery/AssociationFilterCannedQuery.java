package com.ixxus.associationFilter.cannedquery;

import com.ixxus.associationFilter.objects.entities.Association;
import org.alfresco.query.CannedQueryParameters;
import org.alfresco.repo.domain.query.CannedQueryDAO;
import org.alfresco.repo.security.permissions.impl.acegi.AbstractCannedQueryPermissions;
import org.alfresco.repo.security.permissions.impl.acegi.MethodSecurityBean;

import java.util.List;

/**
 * Created by Ixxus on 11/13/2015.
 */
public class AssociationFilterCannedQuery extends AbstractCannedQueryPermissions<Association> {
    private static final String QUERY_NAMESPACE                 = "ixxus.association-filter";
    private static final String QUERY_SELECT_NAME               = "select_AssociationFilterByTypeQuery";

    private final CannedQueryDAO cannedQueryDAO;

    protected AssociationFilterCannedQuery(
            CannedQueryDAO cannedQueryDAO,
            MethodSecurityBean<Association> methodSecurity,
            CannedQueryParameters params)
    {
        super(params, methodSecurity);
        this.cannedQueryDAO = cannedQueryDAO;
    }

    @Override protected List<Association> queryAndFilter(CannedQueryParameters cannedQueryParameters) {
        Object paramBeanObj = cannedQueryParameters.getParameterBean();
        if (null == paramBeanObj)
            throw new NullPointerException("NULL AssociationFilterCannedQuery");

        AssociationFilterQueryParameters paramBean = (AssociationFilterQueryParameters)paramBeanObj;
        List<Association> results = cannedQueryDAO.executeQuery(QUERY_NAMESPACE, QUERY_SELECT_NAME,
                paramBean, 0, Integer.MAX_VALUE);
        return results;
    }
}
