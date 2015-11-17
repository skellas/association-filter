package com.ixxus.associationFilter.api.webscripts;

import com.ixxus.associationFilter.objects.entities.Association;
import com.ixxus.associationFilter.objects.entities.Filter;
import org.alfresco.service.cmr.repository.NodeRef;
import org.json.JSONException;
import org.springframework.extensions.webscripts.WebScriptRequest;

import java.io.Serializable;
import java.util.*;

/**
 * Created by Ixxus on 11/16/2015.
 */
public class GetAssociationsByTargetNodeRef extends AbstractGetAssociations {
    @Override protected List<Filter> getFiltersFromRequest(WebScriptRequest request) {
        Map<String, String> templateVars = request.getServiceMatch().getTemplateVars();
        Serializable targetNodeRefParam = templateVars.get(PARAM_TARGET_NODE_REF);
        LOGGER.debug("PARAM TARGET NODE REF : " + targetNodeRefParam);
        return getAssociationFilter().getFiltersForTargetOf(Arrays.asList(((String)targetNodeRefParam).split(",")), getAssociationTypeFromRequest(request));
    }

    @Override protected Map<String, Object> populateModelWithAssociations(List<Association> associations) {
        Map<String, Object> model = new HashMap<String, Object>(8, 1.0f);
        Set<NodeRef> nodeRefs = new HashSet<>(associations.size());
        for (Association association : associations) {
            nodeRefs.add(new NodeRef(association.getSourceNodeRef()));
        }
        try {
            model.put("results", convertNodesToJsonScriptNodes(nodeRefs));
        } catch (JSONException jsone) {
            LOGGER.error("Error during conversion of NodeRefs to ScriptNodes", jsone);
        }
        return model;
    }
}
