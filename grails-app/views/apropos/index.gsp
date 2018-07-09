<g:applyLayout name="main">
<title>SERMO|About</title>
<div class="column span-5">
<%--<g:include controller="filters" action="countByAuthor"/>--%>
<g:render template="parts_menu/menu"></g:render>
</div>
<div class="column span-16 append-3" style="text-align:justify;">
<g:if test="${part == 'projet'}">
  <g:render template="parts/projet"></g:render>
</g:if>
<g:elseif test="${part == 'corpus'}">
   <g:render template="parts/corpus"></g:render>
</g:elseif>
<g:elseif test="${part == 'chaine'}">
    <g:render template="parts/chaine"></g:render>
</g:elseif>
</div>

</g:applyLayout>