<div id="place_filter_list" class="filter_list" style= "display:none">
<table class="filters" style="width: 100%">
<g:each in="${countByPubPlace}" var="place" status="i">
<tr class="block">
<td class="column span-16 prepend-4">
 <g:checkBox name="by_place" id="${place.place}"/>
<%-- </td>--%>
<%-- 	<td class="column span-12 last">--%>
  <label for="${place.place}">
  ${place.place} (${place.'count(place)'})
  </label>
  
   </td>
</tr>
</g:each>

</table>
</div>