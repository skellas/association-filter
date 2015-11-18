package com.ixxus.associationFilter.api.webscripts.json;

/**
 * Created by Ixxus on 11/17/2015.
 */
public class JSONMapping {
    public static final String FILTERS_NAME             = "filters";
        public static final String SOURCE_NODE_REF      = "sourceNodeRef";
        public static final String ASSOCIATION_TYPE     = "associationType";
        public static final String TARGET_NODE_REF      = "targetNodeRef";

    /*
        Example JSON Mapping

        {
            "filters" : [
              {
                "associationType": "references",
                "targetNodeRef" : [
                    "workspace://SpaceStore/000000-fff-000-fffff-0000",
                    "workspace://SpaceStore/000000-fff-000-fffff-0000",
                    "workspace://SpaceStore/000000-fff-000-fffff-0000"
                ]
              },
              {
                "associationType": "alsoReferences",
                "targetNodeRef" : [
                    "workspace://SpaceStore/000000-fff-000-fffff-0000",
                    "workspace://SpaceStore/000000-fff-000-fffff-0000",
                    "workspace://SpaceStore/000000-fff-000-fffff-0000"
                ]
              }
            ]
        }

     */
}
