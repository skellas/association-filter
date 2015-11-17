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
        if (exists(this.getSourceNodeRef())) {
            sb.append(queryModifier(sb));
            sb.append(" SourceNodeRef = " + '"' + this.getSourceNodeRef() + '"');
        }
        if (exists(this.getAssociationType())) {
            sb.append(queryModifier(sb));
            sb.append(" AssociationType = " + '"' + this.getAssociationType() + '"');
        }
        if (exists(this.getTargetNodeRef())) {
            sb.append(queryModifier(sb));
            sb.append(" TargetNodeRef = " + '"' + this.getTargetNodeRef() + '"');
        }

        return sb.toString();
    }

    protected  String queryModifier(StringBuilder sb) { return sb.length() > 0 ? " AND " : "" ;}
    /* Getters and Setters */

    private boolean exists(String value) {
        if (null == value)
            return false;
        if ("".equalsIgnoreCase(value))
            return false;
        if ("null".equalsIgnoreCase(value))
            return false;

        return true;
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

    public String getSourceNodeRef() {
        return sourceNodeRef;
    }

    public void setSourceNodeRef(String sourceNodeRef) {
        this.sourceNodeRef = sourceNodeRef;
    }

    /**
     * This tests to see if the filter is valid.
     * A filter must have at least one of its NodeRefs filled.
     * A filter should have an AssociationType, though technically it doesn't have to.
     * @return if the filter is valid to run against the database
     */
    public boolean isValid() {
        // default to assuming they all exist
        boolean hasSourceNodeRef = true, hasAssociationType = true, hasTargetNodeRef = true;
        // test to see if each individual property hasn't been filled in
        if (null == sourceNodeRef || "".equalsIgnoreCase(sourceNodeRef))
            hasSourceNodeRef = false;
        if (null == targetNodeRef || "".equalsIgnoreCase(targetNodeRef))
            hasTargetNodeRef = false;

        return (hasSourceNodeRef || hasTargetNodeRef);
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
