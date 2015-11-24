package com.ixxus.associationFilter.objects.entities;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ixxus on 11/18/2015.
 */
public class FilterGroupEntityTest {

    private static final String SOURCE_NODE_REF_A         = "test://test/00000000-ffff-0000-ffff-000000000000";
    private static final String SOURCE_NODE_REF_B         = "test://test/00000000-aaaa-0000-aaaa-000000000000";
    private static final String ASSOCIATION_TYPE          = "testing";
    private static final String TARGET_NODE_REF_A         = "test://test/ffffffff-0000-ffff-0000-ffffffffffff";
    private static final String TARGET_NODE_REF_B         = "test://test/ffffffff-1111-ffff-1111-ffffffffffff";


    @Test
    public void testValidity() {

        FilterEntity noSourceNodeRefFilter = new FilterEntity("","",TARGET_NODE_REF_A),
                noTargetNodeRefFilter = new FilterEntity(SOURCE_NODE_REF_A, "",""),
                neitherRefFilter = new FilterEntity("","","");

        List<Filter> validFilterList = Arrays.asList(noSourceNodeRefFilter, noTargetNodeRefFilter),
                invalidFilterList = Arrays.asList(noSourceNodeRefFilter, noTargetNodeRefFilter, neitherRefFilter);

        FilterGroupEntity validFilterGroup = new FilterGroupEntity(validFilterList),
                invalidFilterGroup = new FilterGroupEntity(invalidFilterList);

        Assert.assertEquals("FilterGroup child filters are all valid", true, validFilterGroup.isValid());
        Assert.assertEquals("FilterGroup child filters are not all valid ", false, invalidFilterGroup.isValid());
    }
}