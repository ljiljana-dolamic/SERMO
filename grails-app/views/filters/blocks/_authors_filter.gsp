
<div id="author_filter_list" class="filter_list" style= "display:none">
<table class="filters" style="width: 100%">
<g:each in="${countByAuthor}" var="author" status="i">
<tr class="block">
<td class="column span-2 prepend-2">
-
</td>
<td class="column span-16 ">
<%-- <g:checkBox name="by_author" id="${author.author_first_name}:${author.author_last_name}" --%>
<%-- value="${false}" chacked="false" --%>
<%-- onchange="${remoteFunction(action:'addAuthorToFilter',params:'\'author=\'+this.id',options: '[asynchronous: false]')} "/>--%>
<g:link action="addAuthorToFilter" elementId="${author.author_first_name}:${author.author_last_name}"
								id="${author.author_first_name}:${author.author_last_name}" params= "[first_name:author.author_first_name, last_name:author.author_last_name]">
								${author.author_last_name}, ${author.author_first_name} (${author.'count(author_first_name)'})

							</g:link>  
<%-- <g:checkBox name="by_author" id="${author.author_first_name}:${author.author_last_name}" --%>
<%-- value="${false}" chacked="false" --%>
<%-- onchange="${remoteFunction(action:'addAuthorToFilter',params:'\'author=\'+this.id',options: '[asynchronous: false]')} "/>--%>
<%-- </td>--%>
<%-- 	<td class="column span-12 last">--%>
<%--  <label for="${author.author_first_name}:${author.author_last_name}">--%>
<%--  ${author.author_last_name}, ${author.author_first_name} (${author.'count(author_first_name)'})--%>
<%--  </label>--%>
  
   </td>
</tr>
</g:each>

</table>
</div>