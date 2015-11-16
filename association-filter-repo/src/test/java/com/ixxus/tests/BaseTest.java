package com.ixxus.tests;

import org.alfresco.model.ContentModel;
import org.alfresco.repo.model.Repository;
import org.alfresco.repo.security.authentication.AuthenticationUtil;
import org.alfresco.service.cmr.dictionary.DictionaryService;
import org.alfresco.service.cmr.repository.ChildAssociationRef;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.cmr.repository.NodeService;
import org.alfresco.service.namespace.NamespaceService;
import org.alfresco.service.namespace.QName;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Ixxus on 11/13/2015.
 */
@ContextConfiguration("classpath:alfresco/application-context.xml")
public class BaseTest {

    @Autowired
    @Qualifier("NodeService")
    protected NodeService nodeService;

    @Autowired
    @Qualifier("DictionaryService")
    protected DictionaryService dictionaryService;

    @Autowired
    @Qualifier("repositoryHelper")
    protected Repository repository;

    @Before
    public void checkWiring() {
        assertNotNull(nodeService);
        assertNotNull(dictionaryService);
        assertNotNull(repository);
    }

    protected void deleteTestFolderIfExists(String name) {
        NodeRef companyHomeNodeRef = repository.getCompanyHome();
        List<ChildAssociationRef> children = nodeService.getChildrenByName(companyHomeNodeRef, ContentModel.ASSOC_CONTAINS, Arrays.asList(name));
        if (!children.isEmpty()) {
            ChildAssociationRef test = children.get(0);
            if (test != null) {
                nodeService.deleteNode(test.getChildRef());
            }
        }
    }
    /**
     * Create a folder in Alfresco CompanyHome
     *
     * @param folderName
     * @return nodeRef for created folder
     */
    protected NodeRef createFolderInCompanyHome(String folderName) {
        AuthenticationUtil.setFullyAuthenticatedUser("admin");
        NodeRef companyHomeNodeRef = repository.getCompanyHome();

        return createFolder(companyHomeNodeRef, folderName, null);
    }

    protected NodeRef createFolder(NodeRef parentNodeRef, String folderName,
            Map<QName, Serializable> properties) {
        if (properties == null) {
            properties = new HashMap<QName, Serializable>();
        }
        properties.put(ContentModel.PROP_NAME, folderName);
        return nodeService.createNode(parentNodeRef, ContentModel.ASSOC_CONTAINS,
                QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, folderName),
                ContentModel.TYPE_FOLDER, properties).getChildRef();
    }

    protected NodeRef createContainer(NodeRef parentNodeRef, String folderName,
            Map<QName, Serializable> properties,
            QName containerTypeQName) {
        if (properties == null) {
            properties = new HashMap<QName, Serializable>();
        }
        properties.put(ContentModel.PROP_NAME, folderName);
        return nodeService.createNode(parentNodeRef, ContentModel.ASSOC_CONTAINS,
                QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, folderName),
                containerTypeQName, properties).getChildRef();
    }

    protected NodeRef createContent(NodeRef parentNodeRef, String contentName, QName contentTypeQName) {
        return createContent(parentNodeRef, contentName, null, contentTypeQName);
    }

    protected NodeRef createContent(NodeRef parentNodeRef, String contentName,
            Map<QName, Serializable> properties,
            QName contentTypeQName) {
        if (properties == null) {
            properties = new HashMap<QName, Serializable>();
        }
        properties.put(ContentModel.PROP_NAME, contentName);
        return nodeService.createNode(parentNodeRef, ContentModel.ASSOC_CONTAINS,
                QName.createQName(NamespaceService.CONTENT_MODEL_1_0_URI, contentName),
                contentTypeQName, properties).getChildRef();
    }
}
