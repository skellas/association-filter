package com.ixxus.associationFilter.objects.entities;

import org.alfresco.service.cmr.repository.NodeRef;
import org.alfresco.service.namespace.QName;

/**
 * Created by Ixxus on 11/13/2015.
 */
public class FilterEntity implements Filter{
    protected String sourceNodeRef;
    protected String associationType;
    protected String targetNodeRef;


    public FilterEntity(){
        this("","","");
    }
    public FilterEntity(NodeRef sourceNodeRef, QName associationType, NodeRef targetNodeRef) {
        this();
        String safeSourceNodeRef = "", safeAssociationType = "", safeTargetNodeRef = "";
        // make safe these variables, as the NodeRefs may be null
        if (null != sourceNodeRef) {
            setSourceNodeRef(sourceNodeRef.toString());
        }
        if (null != associationType) {
           setAssociationType(associationType.getLocalName());
        }
        if (null != targetNodeRef) {
            setTargetNodeRef(targetNodeRef.toString());
        }
    }
    public FilterEntity(String sourceNodeRef, String associationType, String targetNodeRef) {
        setSourceNodeRef(sourceNodeRef);
        setAssociationType(associationType);
        setTargetNodeRef(targetNodeRef);
    }
    public String filterString() {
        StringBuilder sb = new StringBuilder();
        if (!"".equals(this.getSourceNodeRef())) {
            sb.append(queryModifier(sb));
            sb.append(" SourceNodeRef = " + '"' + this.getSourceNodeRef() + '"');
        }
        if (!"".equals(this.getAssociationType())) {
            sb.append(queryModifier(sb));
            sb.append(" AssociationType = " + '"' + this.getAssociationType() + '"');
        }
        if (!"".equals(this.getTargetNodeRef())) {
            sb.append(queryModifier(sb));
            sb.append(" TargetNodeRef = " + '"' + this.getTargetNodeRef() + '"');
        }

        return sb.toString();
    }

    protected  String queryModifier(StringBuilder sb) { return sb.length() > 0 ? " and " : "" ;}
    /* Getters and Setters */

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

    public String getSourceNodeRef() {
        return sourceNodeRef;
    }

    public void setSourceNodeRef(String sourceNodeRef) {
        this.sourceNodeRef = sourceNodeRef;
    }

    @Override
    public String toString() {
        String spacer = "   ";
        String newline = "\n";
        StringBuilder sb = new StringBuilder();
        sb.append("FilterEntity(" + newline);
        sb.append(spacer + "SourceNodeRef   : " + sourceNodeRef + newline);
        sb.append(spacer + "TargetNodeRef   : " + targetNodeRef + newline);
        sb.append(spacer + "AssociationType : " + associationType + newline);
        sb.append(")");

        return sb.toString();
    }
}
