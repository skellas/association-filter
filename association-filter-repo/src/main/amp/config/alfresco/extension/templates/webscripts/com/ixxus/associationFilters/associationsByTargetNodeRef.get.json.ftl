<#escape x as jsonUtils.encodeJSONString(x)>
{
    "data":
    {
        <#if parent??>
            <@renderParent parent />
        </#if>
        "items":
        [
            <#list results as row>
            {
            "type": "${row.item.typeShort}",
            "parentType": "${row.item.parentTypeShort!""}",
            <#if row.item.parent??>"parentName": "${row.item.parent.name!""}",</#if>
            "isContainer": ${row.item.isContainer?string},
            "name": "${row.item.properties['cm:name']!""}",
            "title": "${row.item.properties['cm:title']!""}",
            "description": "${row.item.properties['cm:description']!""}",
            <#if row.item.siteShortName??>"site": "${row.item.siteShortName}",</#if>
            "displayPath": "${row.item.displayPath!""}",

            "nodeRef": "${row.item.nodeRef}"
            }<#if row_has_next>,</#if>
            </#list>
        ]
    }
}
</#escape>