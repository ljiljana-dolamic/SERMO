<g:applyLayout name="main">
 <div class=" column prepend-1 span-22 append-1">  
	<div class =" page-body block">

		<div id ="doc-container"  >
		
			<div id="selected-doc"> 
			<g:render template = "docs/doc_info_head", model="${[doc:doc]}"></g:render>
		
		
		
		
		<g:render template = "docs/doc-front" model="${[docId:docId,page:frontPage,pages:frontPages]}"></g:render>
		
	
		
		<g:render template= "docs/doc-body" model="${[docId:docId,page:bodyPage,pages:bodyPages, pageToShow:pageToShow]}"></g:render>
		
		
		
		<g:render template = "docs/doc-meta" model="${[docId:docId,doc:doc]}"></g:render>
			</div>
		
<%--		<div class="pagination">--%>
<%--				<g:paginate next="Suivant" prev="Précédent" action="index" total="${total}" params="${params}" />--%>
<%--			</div>	--%>
<%--		   --%>
<%--		</div>	--%>


	
</div>

</div>

</div>
<div id="myModal" class="modal">
    <div class="modal-content">
      <div class="modal-header">
        <span class="close-modal">&times;</span>
        <h4 class="modal-title"></h4>
      </div>
      <div class="modal-body">
 
      </div>
      <div class="modal-footer">
        
      </div>
    </div><!-- /.modal-content -->
</div><!-- /.modal -->

	

</g:applyLayout>