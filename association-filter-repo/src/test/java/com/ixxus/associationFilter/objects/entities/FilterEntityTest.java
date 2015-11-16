package com.ixxus.associationFilter.objects.entities;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ixxus on 11/16/2015.
 */
public class FilterEntityTest {

    private static final String SOURCE_NODE_REF         = "test://test/00000000-ffff-0000-ffff-000000000000";
    private static final String ASSOCIATION_TYPE        = "testing";
    private static final String TARGET_NODE_REF         = "test://test/ffffffff-0000-ffff-0000-ffffffffffff";

    @Test
    public void testFilterEntityValidity() {
        // basic pojo, but want to test out the
        // isValid() function.
        // basic rules are that there needs to be
        // either a SourceNodeRef or TargetNodeRef
        // at all times.

        FilterEntity noSourceNodeRefFilter = new FilterEntity("","",TARGET_NODE_REF),
                noTargetNodeRefFilter = new FilterEntity(SOURCE_NODE_REF, "",""),
                neitherRefFilter = new FilterEntity("","","");

        Assert.assertEquals("Filter can have no SourceNodeRef", true, noSourceNodeRefFilter.isValid());
        Assert.assertEquals("Filter can have no TargetNodeRef", true, noTargetNodeRefFilter.isValid());
        Assert.assertEquals("Filter can't have neither. One must be present", false, neitherRefFilter.isValid());
    }

}