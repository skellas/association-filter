<#escape x as jsonUtils.encodeJSONString(x)>
{
"data":
{
"associations":
[
    <#list results as association>
    {
    "associationType": "${association.associationType}",
    "sourceNodeRef":
    {
    "type": "${association.sourceNode.typeShort}",
    "isContainer": ${association.sourceNode.isContainer?string},
    "name": "${association.sourceNode.properties['cm:name']!""}",
    "title": "${association.sourceNode.properties['cm:title']!""}",
    "description": "${association.sourceNode.properties['cm:description']!""}",
        <#if association.sourceNode.siteShortName??>"site": "${association.sourceNode.siteShortName}",</#if>
    "displayPath": "${association.sourceNode.displayPath!""}",
    "nodeRef": "${association.sourceNode.nodeRef}"
    },
    "targetNodeRef":
    {
    "type": "${association.targetNode.typeShort}",
    "isContainer": ${association.targetNode.isContainer?string},
    "name": "${association.targetNode.properties['cm:name']!""}",
    "title": "${association.targetNode.properties['cm:title']!""}",
    "description": "${association.targetNode.properties['cm:description']!""}",
        <#if association.targetNode.siteShortName??>"site": "${association.targetNode.siteShortName}",</#if>
    "displayPath": "${association.targetNode.displayPath!""}",
    "nodeRef": "${association.targetNode.nodeRef}"
    }
    }<#if association_has_next>,</#if>
    </#list>
]
}
}
</#escape>