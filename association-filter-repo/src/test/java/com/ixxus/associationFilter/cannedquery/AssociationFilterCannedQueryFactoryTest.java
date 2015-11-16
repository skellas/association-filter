package com.ixxus.associationFilter.cannedquery;

import com.ixxus.associationFilter.objects.entities.FilterEntity;
import com.ixxus.tests.BaseTest;
import com.tradeshift.test.remote.Remote;
import com.tradeshift.test.remote.RemoteTestRunner;
import org.alfresco.query.CannedQuery;
import org.alfresco.query.CannedQueryParameters;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

/**
 * Created by Ixxus on 11/16/2015.
 */
@RunWith(RemoteTestRunner.class)
@Remote(runnerClass = SpringJUnit4ClassRunner.class)
public class AssociationFilterCannedQueryFactoryTest extends BaseTest{

    @Autowired
    @Qualifier("AssociationFilterCannedQueryFactory")
    AssociationFilterCannedQueryFactory factory;
    @Test
    public void testGetCannedQueryViaFilters() throws Exception {
        FilterEntity filter = new FilterEntity();
        CannedQuery cannedQuery = factory.getCannedQuery(Arrays.asList(filter));

        Assert.assertNotNull("This should not be null", cannedQuery);
        Assert.assertEquals("This should be our specialised query type", AssociationFilterCannedQuery.class, cannedQuery.getClass());
        Assert.assertNotNull("Should have a complete parameters bean", cannedQuery.getParameters());
    }

    @Test
    public void testGetCannedQueryViaParameterClass() throws Exception {
        // this is technically the same test, as the above method
        // does the exact functionality call as implemented below

        FilterEntity filter = new FilterEntity();
        AssociationFilterQueryParameters parameterBean = new AssociationFilterQueryParameters(Arrays.asList(filter));
        CannedQueryParameters parameters = new CannedQueryParameters(parameterBean);
        CannedQuery cannedQuery = factory.getCannedQuery(parameters);

        Assert.assertNotNull("This should not be null", cannedQuery);
        Assert.assertEquals("This should be our specialised query type", AssociationFilterCannedQuery.class, cannedQuery.getClass());
        Assert.assertNotNull("Should have a complete parameters bean", cannedQuery.getParameters());
    }
}