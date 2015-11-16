package com.ixxus.associationFilter.cannedquery;

import com.ixxus.associationFilter.objects.entities.Filter;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Ixxus on 11/13/2015.
 */
public class AssociationFilterQueryParameters {
    protected List<Filter> filters;
    public AssociationFilterQueryParameters(){}
    public AssociationFilterQueryParameters(List<Filter> filters){
        this.filters = filters;
    }
    /**
     * How many Filters are we using in our filter?
     * @return int # of filters
     */
    public int getFiltersSize() { return filters.size(); }

    /**
     * Get the String representation of the filters to be
     * used in the SQL query.
     * @return
     */
    public String getFiltersString() {
        StringBuilder sb = new StringBuilder();
        Iterator<Filter> iter = filters.iterator();
        while (iter.hasNext()) {
            Filter entity = iter.next();
            if (entity.isValid()) {
                sb.append("(" + entity.filterString() + ")");
                if (iter.hasNext())
                    sb.append(" OR ");
            }
        }
        return sb.toString();
    }

}
