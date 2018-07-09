<div id= "by_place_filter" class="filters">

<div class="filters-menu" id="place_filter">
	<h1>Lieu de publication (${count})</h1>
</div>
<g:render template="blocks/pubPlace_filter" model="${[countByPubPlace:countByPubPlace]}"/>


<%--<table class="filters" style="width: 100%">--%>
<%--<g:each in="${countByAuthor}" var="author" status="i">--%>
<%--<tr class="block">--%>
<%--<td class="column span-16 ">--%>
<%-- <g:checkBox name="by_author" id="${author.author_first_name}:${author.author_last_name}"/>--%>
 
<%--  <label for="${author.author_first_name}:${author.author_last_name}">--%>
<%--  ${author.author_last_name}, ${author.author_first_name} (${author.'count(author_first_name)'})--%>
<%--  </label>--%>
<%--  --%>
<%--   </td>--%>
<%--</tr>--%>
<%--</g:each>--%>
<%----%>
<%--</table>--%>


</div>