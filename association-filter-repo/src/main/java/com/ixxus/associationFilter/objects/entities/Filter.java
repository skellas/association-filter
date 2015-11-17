package com.ixxus.associationFilter.objects.entities;

/**
 * Created by Ixxus on 11/13/2015.
 */
public interface Filter {
    String filterString();

    String getAssociationType() throws Exception;

    void setAssociationType(String associationType) throws Exception;

    String getTargetNodeRef() throws Exception;

    void setTargetNodeRef(String targetNodeRef) throws Exception;

    String getSourceNodeRef() throws Exception;

    void setSourceNodeRef(String sourceNodeRef) throws Exception;

    boolean isValid();
}
