package com.ixxus.associationFilter.objects.factories;

import com.ixxus.associationFilter.objects.entities.Filter;
import com.ixxus.associationFilter.objects.entities.FilterEntity;
import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

/**
 * Created by Ixxus on 11/16/2015.
 */
public class FilterEntityFactory extends FilterFactory {
    public Filter getFilter() {
        return new FilterEntity();
    }
    public Filter getFilter(NodeRef sourceNodeRef, QName associationType, NodeRef targetNodeRef) {
        return new FilterEntity(sourceNodeRef, associationType, targetNodeRef);
    }
    public Filter getFilter(String sourceNodeRef, String associationType, String targetNodeRef) {
        return new FilterEntity(sourceNodeRef, associationType, targetNodeRef);
    }
}
