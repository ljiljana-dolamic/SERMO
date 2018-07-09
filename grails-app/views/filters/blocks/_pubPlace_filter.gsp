<g:if test="${ft == 'place'}" >
<div id="place_filter_list" class="filter_list">
 </g:if>
  <g:else>
<div id="place_filter_list" class="filter_list" style= "display:none">
  </g:else>

 </td>
 	<td class="column span-12 last">


<table class="filters" style="width: 100%">
<g:each in="${countByPubPlace}" var="place" status="i">
<tr class="block">
<td class="column span-2 prepend-2">
-
</td>
<td class="filter column span-16 ">
<g:link action="addEditionPlaceFilter" elementId="${place.place}"
								 params= "[place_edition:place.place]">
								 ${place.place} (${place.'count(place)'})

							</g:link>  
   </td>
   
   <g:if test="${filter == "${place.place}"}" >
 <td id="${place.place}_cf" class="close_filter" style="color:red; ">&#10008;</td>

   </g:if>
   <g:else>
  <td id="${place.place}_cf" class="close_filter" style="color:red;  display:none">&#10008;</td>
   </g:else>
  
</g:each>

</table>
</div>