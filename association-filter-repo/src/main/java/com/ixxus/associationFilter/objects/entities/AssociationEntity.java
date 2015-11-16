package com.ixxus.associationFilter.objects.entities;

/**
 * Created by Ixxus on 11/13/2015.
 */
public class AssociationEntity implements Association {

    protected String sourceNodeRef;
    protected String associationType;
    protected String targetNodeRef;



    /* Getters and Setters */

    public String getSourceNodeRef() {
        return sourceNodeRef;
    }

    public void setSourceNodeRef(String sourceNodeRef) {
        this.sourceNodeRef = sourceNodeRef;
    }

    public String getAssociationType() {
        return associationType;
    }

    public void setAssociationType(String associationType) {
        this.associationType = associationType;
    }

    public String getTargetNodeRef() {
        return targetNodeRef;
    }

    public void setTargetNodeRef(String targetNodeRef) {
        this.targetNodeRef = targetNodeRef;
    }

    @Override
    public String toString() {
        String spacer = "   ";
        String newline = "\n";
        StringBuilder sb = new StringBuilder();
        sb.append("AssociationEntity(" + newline);
        sb.append(spacer + "sourceNodeRef   : " + sourceNodeRef + newline);
        sb.append(spacer + "targetNodeRef   : " + targetNodeRef + newline);
        sb.append(spacer + "associationType : " + associationType + newline);
        sb.append(")");

        return sb.toString();
    }
}
