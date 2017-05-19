<div id= "by_author_filter" class="filters">
<h4>By author</h4>
<table style="width: 100%">
<g:each in="${countByAuthor}" var="author" status="i">
<tr class="block">
<td class="column span-4 ">
 <g:checkBox name="smth" value="HELLO" />
 </td>
 	<td class="column span-12 last">
   ${author.author_first_name}  ${author.author_last_name} (${author.'count(author_first_name)'})
   </td>
 </tr>
</g:each>

</table>

</div>



					