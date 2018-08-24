<g:applyLayout name="main">
<title>SERMO|Documentation</title>

<div class="column span-5">

<g:render template="parts_menu/menu"></g:render>
</div>

<div class="column span-16 append-3" style="text-align:justify;">
<g:if test="${part == 'description'}">
    <g:render template="parts/description"></g:render>
</g:if>
<g:elseif test="${part == 'publications'}">
  <g:render template="parts/publications"></g:render>
</g:elseif>
<g:elseif test="${part == 'tutoriel'}">
   <g:render template="parts/tutoriel"></g:render>
</g:elseif>
<g:elseif test="${part == 'type_sermon'}">
   <g:render template="parts/type_sermons"></g:render>
</g:elseif>

</div>

</g:applyLayout>

 