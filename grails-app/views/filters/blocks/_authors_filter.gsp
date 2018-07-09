 <g:if test="${ft == 'author'}" >
 <div id="author_filter_list" class="filter_list">
 </g:if>
  <g:else>
   <div id="author_filter_list" class="filter_list" style= "display:none">
  </g:else>

<table class="filters" style="width: 100%">
<g:each in="${countByAuthor}" var="author" status="i">
<tr class="block">
<td class="column span-1 prepend-1">
-
</td>
<td class="filter column span-20 ">
<g:link action="addAuthorToFilter" elementId="${author.author_first_name}:${author.author_last_name}"
								 params= "[first_name:author.author_first_name, last_name:author.author_last_name]">
								${author.author_last_name}, ${author.author_first_name} (${author.'count(author_first_name)'})

							</g:link>  
   </td>
   <g:if test="${filter == "${author.author_first_name}:${author.author_last_name}"}" >
   <td id="${author.author_first_name}:${author.author_last_name}_cf" class="close_filter" style="color:red;">&#10008;</td>

   </g:if>
   <g:else>
   <td id="${author.author_first_name}:${author.author_last_name}_cf" class="close_filter" style="color:red;  display:none">&#10008;</td>
   </g:else>
  
</tr>
</g:each>

</table>
</div>