<div id="decade_filter_list" class="filter_list" style= "display:none">
<table class="filters" style="width: 100%">
<g:each in="${countByPubDecade}" var="decade" status="i">
<tr class="block">
<td class="column span-16 prepend-4">
 <g:checkBox name="by_decade" id="${decade.label}"/>
<%-- </td>--%>
<%-- 	<td class="column span-12 last">--%>
  <label for="${decade.label}">
  ${decade.label} (${decade.count})
  </label>
  
   </td>
</tr>
</g:each>

</table>
</div>