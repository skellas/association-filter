package com.ixxus.associationFilter.api;

import com.ixxus.associationFilter.objects.entities.Association;
import com.ixxus.associationFilter.objects.entities.FilterEntity;
import com.ixxus.tests.BaseTest;
import com.tradeshift.test.remote.Remote;
import com.tradeshift.test.remote.RemoteTestRunner;
import org.alfresco.model.ContentModel;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.repo.transaction.RetryingTransactionHelper;
import org.alfresco.service.cmr.repository.AssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ixxus on 11/13/2015.
 */
@RunWith(RemoteTestRunner.class)
@Remote(runnerClass = SpringJUnit4ClassRunner.class)
public class AssociationFilterImplTest extends BaseTest {
    @Autowired
    @Qualifier("ixxus-association-filter")
    protected AssociationFilter associationFilter;

    private static final String TEST_FOLDER_NAME = "test-folder";
    private static final String CHILD_CONTENT_A_NAME = "test-content-a";
    private static final String CHILD_CONTENT_B_NAME = "test-content-b";
    private static final String CHILD_CONTENT_C_NAME = "test-content-c";
    private NodeRef testFolder;

    @Before
    public void setUp() throws Exception {
        AuthenticationUtil.setFullyAuthenticatedUser("admin");
        deleteTestFolderIfExists(TEST_FOLDER_NAME);
        testFolder = createFolderInCompanyHome(TEST_FOLDER_NAME);

    }

    @After
    public void tearDown() throws Exception {
       if (null != testFolder && nodeService.exists(testFolder))
            nodeService.deleteNode(testFolder);
    }

    @Test
    public void testGetAssociationByString() {
        NodeRef childNodeA = createContent(testFolder,CHILD_CONTENT_A_NAME, ContentModel.TYPE_CONTENT),
                childNodeB = createContent(testFolder, CHILD_CONTENT_B_NAME, ContentModel.TYPE_CONTENT);

        nodeService.addAspect(childNodeA, ContentModel.ASPECT_REFERENCING, null);
        AssociationRef associationRef = nodeService.createAssociation(childNodeA, childNodeB, ContentModel.ASSOC_REFERENCES);


        FilterEntity filterTestFolderContains = new FilterEntity(childNodeA.toString(), "references", "");

        List<Association> associations = associationFilter.getAssociationsByFilters(Arrays.asList(filterTestFolderContains));
        System.out.println(associations.toString());
        Assert.assertEquals("Should only have one association", 1, associations.size());
    }

    @Test
    public void testGetAssociationsByString() {
        NodeRef childNodeA = createContent(testFolder,CHILD_CONTENT_A_NAME, ContentModel.TYPE_CONTENT),
                childNodeB = createContent(testFolder, CHILD_CONTENT_B_NAME, ContentModel.TYPE_CONTENT),
                childNodeC = createContent(testFolder, CHILD_CONTENT_C_NAME, ContentModel.TYPE_CONTENT);

        nodeService.addAspect(childNodeA, ContentModel.ASPECT_REFERENCING, null);
        nodeService.createAssociation(childNodeA, childNodeB, ContentModel.ASSOC_REFERENCES);
        nodeService.createAssociation(childNodeA, childNodeC, ContentModel.ASSOC_REFERENCES);


        FilterEntity filterTestFolderContains = new FilterEntity(childNodeA.toString(), "references", "");

        List<Association> associations = associationFilter.getAssociationsByFilters(Arrays.asList(filterTestFolderContains));
        System.out.println(associations.toString());
        Assert.assertEquals("Should have two associations", 2, associations.size());
    }

    @Test
    public void testGetAssociationByQName() {
        NodeRef childNodeA = createContent(testFolder,CHILD_CONTENT_A_NAME, ContentModel.TYPE_CONTENT),
                childNodeB = createContent(testFolder, CHILD_CONTENT_B_NAME, ContentModel.TYPE_CONTENT);

        nodeService.addAspect(childNodeA, ContentModel.ASPECT_REFERENCING, null);
        AssociationRef associationRef = nodeService.createAssociation(childNodeA, childNodeB, ContentModel.ASSOC_REFERENCES);


        FilterEntity filterTestFolderContains = new FilterEntity(childNodeA, ContentModel.ASSOC_REFERENCES, null);

        List<Association> associations = associationFilter.getAssociationsByFilters(Arrays.asList(filterTestFolderContains));
        System.out.println(associations.toString());
        Assert.assertEquals("Should only have one association", 1, associations.size());
    }

    @Test
    public void testGetAssociationsByQName() {
        NodeRef childNodeA = createContent(testFolder,CHILD_CONTENT_A_NAME, ContentModel.TYPE_CONTENT),
                childNodeB = createContent(testFolder, CHILD_CONTENT_B_NAME, ContentModel.TYPE_CONTENT),
                childNodeC = createContent(testFolder, CHILD_CONTENT_C_NAME, ContentModel.TYPE_CONTENT);

        nodeService.addAspect(childNodeA, ContentModel.ASPECT_REFERENCING, null);
        nodeService.createAssociation(childNodeA, childNodeB, ContentModel.ASSOC_REFERENCES);
        nodeService.createAssociation(childNodeA, childNodeC, ContentModel.ASSOC_REFERENCES);


        FilterEntity filterTestFolderContains = new FilterEntity(childNodeA, ContentModel.ASSOC_REFERENCES, null);

        List<Association> associations = associationFilter.getAssociationsByFilters(Arrays.asList(filterTestFolderContains));
        System.out.println(associations.toString());
        Assert.assertEquals("Should have two associations", 2, associations.size());
    }

    @Test
    public void testGetAssociationsByFilters() throws Exception {

    }

    @Test
    public void testGetFiltersForTargetOf() throws Exception {

    }

    @Test
    public void testGetFiltersWithSourceOf() throws Exception {

    }
}