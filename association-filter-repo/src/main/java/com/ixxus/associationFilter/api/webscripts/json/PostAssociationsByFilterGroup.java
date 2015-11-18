package com.ixxus.associationFilter.api.webscripts.json;

import com.ixxus.associationFilter.api.webscripts.AbstractGetAssociations;
import com.ixxus.associationFilter.objects.entities.Association;
import com.ixxus.associationFilter.objects.entities.Filter;
import com.ixxus.associationFilter.objects.entities.FilterGroupEntity;
import com.ixxus.associationFilter.objects.exceptions.MissingParameterException;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.service.cmr.repository.NodeRef;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Scriptable;
import org.springframework.extensions.webscripts.WebScriptRequest;

import java.util.*;

/**
 * Created by Ixxus on 11/17/2015.
 */
public class PostAssociationsByFilterGroup extends AbstractGetAssociations {
    @Override protected List<Filter> getFiltersFromRequest(WebScriptRequest request) {
        JSONObject json = null;
        Object jsonO = request.parseContent();
        if (jsonO instanceof JSONObject && jsonO != null)
        {
            json = (JSONObject)jsonO;
        }
        LOGGER.debug(json);
        List<Filter> filtersFromJSON = new ArrayList<>();
        try {
            JSONArray jsonFilters = json.getJSONArray(JSONMapping.FILTERS_NAME);
            for (int i = 0; i < jsonFilters.length(); i++) {
                JSONObject jsonFilter = jsonFilters.getJSONObject(i);
                LOGGER.debug(jsonFilter);
                String associationType = jsonFilter.getString(JSONMapping.ASSOCIATION_TYPE);
                // now we're not sure if targetNodeRefs or sourceNodeRefs have been
                // passed in, so we need to play this one carefully
                JSONArray jsonSourceNodeRefs = jsonFilter.optJSONArray(JSONMapping.SOURCE_NODE_REF);
                JSONArray jsonTargetNodeRefs = jsonFilter.optJSONArray(JSONMapping.TARGET_NODE_REF);
                // did they not pass in either? I think this shouldn't be allowed and
                // am going to throw an exception to prove that point
                if (null == jsonSourceNodeRefs && null == jsonTargetNodeRefs)
                    throw new MissingParameterException("Missing Parameter: Please Provide Either SourceNodeRefs or TargetNodeRefs");

                FilterGroupEntity filterGroupEntity = new FilterGroupEntity();
                List<String> nodeRefs = new ArrayList<>();
                if (null == jsonSourceNodeRefs) {
                    for (int j = 0; j < jsonTargetNodeRefs.length(); j++) {
                        nodeRefs.add(jsonTargetNodeRefs.getString(j));
                    }
                    filterGroupEntity.setChildFilters(this.getAssociationFilter().getFiltersForTargetOf(nodeRefs, associationType));
                } else {
                    for (int j = 0; j < jsonSourceNodeRefs.length(); j++) {
                        nodeRefs.add(jsonSourceNodeRefs.getString(j));
                    }
                    filterGroupEntity.setChildFilters(this.getAssociationFilter().getFiltersWithSourceOf(nodeRefs, associationType));
                }
                filtersFromJSON.add(filterGroupEntity);
            }
        } catch (Exception e) {
            LOGGER.error("Error thrown in getFiltersFromRequest", e);
        }

        return filtersFromJSON;
    }

    @Override protected Map<String, Object> populateModelWithAssociations(List<Association> associations) {

        Map<String, Object> model = new HashMap<String, Object>(8, 1.0f);

        Scriptable scope;
        Context cx = Context.enter();
        scope = new ImporterTopLevel(cx);
        Set<JSONObject> assocObjs = new HashSet<>();
        try {

            for (Association association : associations) {
                JSONObject assocObj = new JSONObject();
                LOGGER.debug("creating ScriptNode of association : " + association.toString());
                ScriptNode scriptSourceNode = new ScriptNode(new NodeRef(association.getSourceNodeRef()), getServiceRegistry(), scope);
                ScriptNode scriptTargetNode = new ScriptNode(new NodeRef(association.getTargetNodeRef()), getServiceRegistry(), scope);
                assocObj.put("associationType", association.getAssociationType());
                assocObj.put("sourceNode", scriptSourceNode);
                assocObj.put("targetNode", scriptTargetNode);
                assocObjs.add(assocObj);
            }
            model.put("results", assocObjs);
        } catch (JSONException jsone) {
            LOGGER.error("Unknown JSON Exception", jsone);
        }

        return model;
    }
}
