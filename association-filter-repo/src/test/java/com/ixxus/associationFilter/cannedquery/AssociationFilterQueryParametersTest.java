package com.ixxus.associationFilter.cannedquery;

import com.ixxus.associationFilter.objects.entities.FilterEntity;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Ixxus on 11/16/2015.
 */
public class AssociationFilterQueryParametersTest {
    private static final String SOURCE_NODE_REF         = "test://test/00000000-ffff-0000-ffff-000000000000";
    private static final String ASSOCIATION_TYPE        = "testing";
    private static final String TARGET_NODE_REF         = "test://test/ffffffff-0000-ffff-0000-ffffffffffff";

    private static final String SOURCE_NODE_REF_QUERY   = "SourceNodeRef";
    private static final String ASSOCIATION_TYPE_QUERY  = "AssociationType";
    private static final String TARGET_NODE_REF_QUERY   = "TargetNodeRef";

    @Test
    public void testGetFiltersSize() throws Exception {
        FilterEntity filterEntityA = new FilterEntity(),
                filterEntityB = new FilterEntity(),
                filterEntityC = new FilterEntity();
        AssociationFilterQueryParameters oneFilterParameters =
                new AssociationFilterQueryParameters(Arrays.asList(filterEntityA));
        AssociationFilterQueryParameters threeFilterParameters =
                new AssociationFilterQueryParameters(Arrays.asList(filterEntityA, filterEntityB, filterEntityC));

        Assert.assertEquals("Should only be 1 filter in size() call", 1, oneFilterParameters.getFiltersSize());
        Assert.assertEquals("Should be 3 filter in size() call", 3, threeFilterParameters.getFiltersSize());
    }

    @Test
    public void testGetFiltersString() throws Exception {
        FilterEntity blankFilter = new FilterEntity(),
                sourceNodeFilter = new FilterEntity(SOURCE_NODE_REF, "", ""),
                sourceNodeAndTypeFilter = new FilterEntity(SOURCE_NODE_REF, ASSOCIATION_TYPE, ""),
                targetNodeFilter = new FilterEntity("","",TARGET_NODE_REF),
                targetNodeAndAssociationFilter = new FilterEntity("", ASSOCIATION_TYPE, TARGET_NODE_REF),
                allPossibleParamsFilter = new FilterEntity(SOURCE_NODE_REF,ASSOCIATION_TYPE,TARGET_NODE_REF);

        // sourceNodeFilter Tests
        Assert.assertEquals("sourceNodeFilter contains the SourceNodeRef query string",
                true, sourceNodeFilter.filterString().contains(SOURCE_NODE_REF_QUERY));
        Assert.assertEquals("sourceNodeFilter doesn't contain the AssociationType query string",
                false, sourceNodeFilter.filterString().contains(ASSOCIATION_TYPE_QUERY));
        Assert.assertEquals("sourceNodeFilter doesn't contain the TargetNodeRef query string",
                false, sourceNodeFilter.filterString().contains(TARGET_NODE_REF_QUERY));

        // sourceNodeAndTypeFilter Tests
        Assert.assertEquals("sourceNodeAndTypeFilter contains the SourceNodeRef query string",
                true, sourceNodeAndTypeFilter.filterString().contains(SOURCE_NODE_REF_QUERY));
        Assert.assertEquals("sourceNodeAndTypeFilter doesn't contain the AssociationType query string",
                true, sourceNodeAndTypeFilter.filterString().contains(ASSOCIATION_TYPE_QUERY));
        Assert.assertEquals("sourceNodeAndTypeFilter doesn't contain the TargetNodeRef query string",
                false, sourceNodeAndTypeFilter.filterString().contains(TARGET_NODE_REF_QUERY));

        // targetNodeFilter Tests
        Assert.assertEquals("targetNodeFilter contains the SourceNodeRef query string",
                false, targetNodeFilter.filterString().contains(SOURCE_NODE_REF_QUERY));
        Assert.assertEquals("targetNodeFilter doesn't contain the AssociationType query string",
                false, targetNodeFilter.filterString().contains(ASSOCIATION_TYPE_QUERY));
        Assert.assertEquals("targetNodeFilter doesn't contain the TargetNodeRef query string",
                true, targetNodeFilter.filterString().contains(TARGET_NODE_REF_QUERY));

        // targetNodeAndAssociationFilter Tests
        Assert.assertEquals("targetNodeAndAssociationFilter contains the SourceNodeRef query string",
                false, targetNodeAndAssociationFilter.filterString().contains(SOURCE_NODE_REF_QUERY));
        Assert.assertEquals("targetNodeAndAssociationFilter doesn't contain the AssociationType query string",
                true, targetNodeAndAssociationFilter.filterString().contains(ASSOCIATION_TYPE_QUERY));
        Assert.assertEquals("targetNodeAndAssociationFilter doesn't contain the TargetNodeRef query string",
                true, targetNodeAndAssociationFilter.filterString().contains(TARGET_NODE_REF_QUERY));

        // allPossibleParamsFilter Tests
        Assert.assertEquals("allPossibleParamsFilter contains the SourceNodeRef query string",
                true, allPossibleParamsFilter.filterString().contains(SOURCE_NODE_REF_QUERY));
        Assert.assertEquals("allPossibleParamsFilter doesn't contain the AssociationType query string",
                true, allPossibleParamsFilter.filterString().contains(ASSOCIATION_TYPE_QUERY));
        Assert.assertEquals("allPossibleParamsFilter doesn't contain the TargetNodeRef query string",
                true, allPossibleParamsFilter.filterString().contains(TARGET_NODE_REF_QUERY));

    }
}