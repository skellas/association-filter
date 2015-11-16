package com.ixxus.associationFilter.objects.entities;

/**
 * Created by Ixxus on 11/13/2015.
 */
public interface Filter {
    String filterString();
    String getAssociationType();

    void setAssociationType(String associationType);

    String getTargetNodeRef();

    void setTargetNodeRef(String targetNodeRef);

    String getSourceNodeRef();

    void setSourceNodeRef(String sourceNodeRef);

    boolean isValid();
}
