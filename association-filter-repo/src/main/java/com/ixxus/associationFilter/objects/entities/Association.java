package com.ixxus.associationFilter.objects.entities;

/**
 * Created by Ixxus on 11/13/2015.
 */
public interface Association {
    String getSourceNodeRef();

    void setSourceNodeRef(String sourceNodeRef);

    String getAssociationType();

    void setAssociationType(String associationType);

    String getTargetNodeRef();

    void setTargetNodeRef(String targetNodeRef);
}
