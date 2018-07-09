<g:if test="${ft == 'genre'}" >
<div id="genre_filter_list" class="filter_list">
 </g:if>
  <g:else>
 <div id="genre_filter_list" class="filter_list" style= "display:none">
  </g:else>


<table class="filters" style="width: 100%">
<g:each in="${countByGenre}" var="genre" status="i">
<tr class="block">
<td class="column span-2 prepend-2">
-
</td>
<td class="filter column span-16 ">
<g:link action="addGenreToFilter" elementId="${genre.genre}"
								 params= "[genre:genre.genre]">
								${genre.genre} (${genre.'count(genre)'})

							</g:link>  
   </td>
   <g:if test="${filter == "${genre.genre}"}" >
  <td id="${genre.genre}_cf" class="close_filter" style="color:red;  ">&#10008;</td>

   </g:if>
   <g:else>
  <td id="${genre.genre}_cf" class="close_filter" style="color:red;  display:none">&#10008;</td>
   </g:else>
  
</tr>
</g:each>

</table>
</div>