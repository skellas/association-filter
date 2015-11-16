package com.ixxus.associationFilter.api.webscripts;

import com.ixxus.associationFilter.api.AssociationFilter;
import com.ixxus.associationFilter.objects.entities.Association;
import com.ixxus.associationFilter.objects.entities.Filter;
import org.alfresco.service.ServiceRegistry;
import org.json.JSONException;
import org.alfresco.repo.jscript.ScriptNode;
import org.alfresco.service.cmr.repository.NodeRef;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.Scriptable;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ixxus on 11/16/2015.
 */
public abstract class AbstractGetAssociations extends DeclarativeWebScript {
    protected static final Log LOGGER = LogFactory.getLog(AbstractGetAssociations.class);
    protected static final String PARAM_SOURCE_NODE_REF               = "sourceNodeRef";
    protected static final String PARAM_ASSOCIATION_TYPE              = "associationType";
    protected static final String PARAM_TARGET_NODE_REF               = "targetNodeRef";

    private AssociationFilter associationFilter;
    private ServiceRegistry serviceRegistry;

    @Override
    protected Map<String, Object> executeImpl(WebScriptRequest req, Status status, Cache cache) {

        LOGGER.info("GetAssociations WebScript Called");
        Map<String, Object> model = new HashMap<String, Object>(8, 1.0f);

        List<Filter> filters = getFiltersFromRequest(req);
        LOGGER.debug("Filters.size() : " + filters.size());
        LOGGER.trace("Filters.toString() : " + filters.toString());

        List<Association> associations = associationFilter.getAssociationsByFilters(filters);
        LOGGER.debug("Associations.size() : " + associations.size());
        LOGGER.trace("Associations.toString() : " + associations.toString());

        model.putAll(populateModelWithAssociations(associations));


        return model;
    }

    protected abstract List<Filter> getFiltersFromRequest(WebScriptRequest request);
    protected abstract Map<String, Object> populateModelWithAssociations(List<Association> associations);

    protected String getAssociationTypeFromRequest(WebScriptRequest request) {
        return (String)request.getServiceMatch().getTemplateVars().get(PARAM_ASSOCIATION_TYPE);
    }
    protected List<JSONObject> convertNodesToJsonScriptNodes(List<NodeRef> nodeRefs) throws JSONException {

        LOGGER.debug("wrapping " + nodeRefs.size() + " results");
        Scriptable scope;
        Context cx = Context.enter();
        scope = new ImporterTopLevel(cx);
        List<JSONObject> results = new ArrayList<>();
        for (NodeRef nodeRef : nodeRefs) {
            JSONObject resultObj = new JSONObject();
            LOGGER.debug("creating ScriptNode of nodeRef : " + nodeRef.toString());
            ScriptNode scriptNode = new ScriptNode(nodeRef, serviceRegistry);
            scriptNode.setScope(scope);
            resultObj.put("item", scriptNode);
            LOGGER.trace(scriptNode.toJSON(true).toString());
            results.add(resultObj);
        }
        return results;
    }

    public AssociationFilter getAssociationFilter() {
        return associationFilter;
    }
    public void setAssociationFilter(AssociationFilter associationFilter) {
        this.associationFilter = associationFilter;
    }

    public void setServiceRegistry(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
    }
}
