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
public class GetAssociationsBySourceNodeRef extends AbstractGetAssociations {
    @Override protected List<Filter> getFiltersFromRequest(WebScriptRequest request) {
        Map<String, String> templateVars = request.getServiceMatch().getTemplateVars();
        Serializable sourceNodeRefParam = templateVars.get(PARAM_SOURCE_NODE_REF);
        LOGGER.debug("PARAM SOURCE NODE REF : " + sourceNodeRefParam);
        return getAssociationFilter().getFiltersWithSourceOf(Arrays.asList((String)sourceNodeRefParam), getAssociationTypeFromRequest(request));
    }

    @Override protected Map<String, Object> populateModelWithAssociations(List<Association> associations) {
        Map<String, Object> model = new HashMap<String, Object>(8, 1.0f);
        List<NodeRef> nodeRefs = new ArrayList<>(associations.size());
        for (Association association : associations) {
            nodeRefs.add(new NodeRef(association.getTargetNodeRef()));
        }
        try {
            model.put("results", convertNodesToJsonScriptNodes(nodeRefs));
        } catch (JSONException jsone) {
            LOGGER.error("Error during conversion of NodeRefs to ScriptNodes", jsone);
        }
        return model;
    }
}
