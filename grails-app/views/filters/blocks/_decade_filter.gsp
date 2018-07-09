 <g:if test="${ft == 'time'}" >
 <div id="decade_filter_list" class="filter_list" >
 </g:if>
  <g:else>
  <div id="decade_filter_list" class="filter_list" style= "display:none">
  </g:else>


<table class="filters" style="width: 100%">
<g:each in="${countByDecade}" var="decade" status="i">
<tr class="block">
<td class="column span-2 prepend-1">
-
</td>
<td class="filter column span-18 ">
<g:link action="addDecadeToFilter" elementId="${decade.start}:${decade.end}"
								 params= "[start:decade.start, end:decade.end]">
								${decade.name} (${decade.cnt})

							</g:link>  
   </td>
   <g:if test="${filter == "${decade.start}:${decade.end}"}" >
   <td id="${decade.start}:${decade.end}_cf" class="close_filter" style="color:red;  ">&#10008;</td>

   </g:if>
   <g:else>
  <td id="${decade.start}:${decade.end}_cf" class="close_filter" style="color:red;  display:none">&#10008;</td>
   </g:else>
  
</tr>
</g:each>

</table>
</div>