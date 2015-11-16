package com.ixxus.associationFilter.patch.impl;

import com.ixxus.associationFilter.cannedquery.AssociationFilterQueryParameters;
import com.ixxus.associationFilter.objects.entities.AssociationEntity;

import java.util.List;

/**
 * Created by Ixxus on 11/16/2015.
 */
public interface IxxusAssociationFilterMapper {

    void createNodeRefByIdView();
    void createAssociationByTypeView();
    List<AssociationEntity> selectAssociationFilterByTypeQuery(AssociationFilterQueryParameters parameters);
}
