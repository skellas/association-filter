package com.ixxus.associationFilter.objects.entities;

import com.ixxus.associationFilter.objects.exceptions.MethodNotImplementedException;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ixxus on 11/17/2015.
 */
public class FilterGroupEntity implements Filter {
    protected Set<Filter> childFilters;

    public FilterGroupEntity() {
        this.childFilters = new HashSet<>();
    }
    @Override public String filterString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (Filter childFilter : childFilters) {
            sb.append(queryModifier(sb));
            sb.append(childFilter.filterString());
        }
        sb.append(")");
        return sb.toString();
    }

    protected  String queryModifier(StringBuilder sb) { return sb.length() > 0 ? " AND " : "" ;}
    @Override public String getAssociationType() throws Exception {
        throw new MethodNotImplementedException("FilterGroupEntity.getAssociatedType() Not Implemented");
    }

    @Override public void setAssociationType(String associationType) throws Exception {
        throw new MethodNotImplementedException("FilterGroupEntity.setAssociationType() Not Implemented");
    }

    @Override public String getTargetNodeRef() throws Exception {
        throw new MethodNotImplementedException("FilterGroupEntity.getTargetNodeRef() Not Implemented");
    }

    @Override public void setTargetNodeRef(String targetNodeRef) throws Exception {
        throw new MethodNotImplementedException("FilterGroupEntity.setTargetNodeRef() Not Implemented");
    }

    @Override public String getSourceNodeRef() throws Exception {
        throw new MethodNotImplementedException("FilterGroupEntity.getSourceNodeRef() Not Implemented");
    }

    @Override public void setSourceNodeRef(String sourceNodeRef) throws Exception {
        throw new MethodNotImplementedException("FilterGroupEntity.setSourceNodeRef() Not Implemented");
    }

    public void addChildFilter(Filter filter) {
        this.childFilters.add(filter);
    }
    public void setChildFilters(Collection<Filter> filters) {
        this.childFilters = (Set)filters;
    }

    @Override public boolean isValid() {
        for (Filter childFilter : childFilters) {
            if (!childFilter.isValid())
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String spacer = "   ";
        String newline = "\n";
        StringBuilder sb = new StringBuilder();
        sb.append("FilterGroupEntity(" + newline);
        for (Filter childFilter : childFilters) {
            sb.append(spacer + "[");
            sb.append(childFilter.toString());
            sb.append(spacer + "]");
        }
        sb.append(")");

        return sb.toString();
    }
}
