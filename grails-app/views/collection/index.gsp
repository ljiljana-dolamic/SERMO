<%----%>
<%--<head>--%>
<%--<meta name="layout" content="main" />--%>
<%--<title>SERMO|Collection</title>--%>
<%--<g:javascript library="jquery" />--%>
<%--<r:require modules="sermo" />--%>
<%--<r:layoutResources />--%>

<%--<body>--%>
<%--<title>SERMO|Collection</title>--%>
<%--<g:layoutHead />--%>
<%--</head>--%>

<g:applyLayout name="main">
<title>SERMO|Collection</title>
<div class="column span-5">
<%--<g:include controller="filters" action="countByAuthor"/>--%>
<g:render template="filters/filters"></g:render>
</div>
<div class=" column span-14">

<div id="myNav" class="overlay">
  <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
  <div class="overlay-content">
  <g:each in="${docs}" var="doc" status="i">
		<g:link action="showDocInfo" elementId="docInfo${i}"
								id='${doc."id"}' params= "[docId:doc.id, authorFirstName:doc.authorFirstName, authorLastName:doc.authorLastName, 
								editionYear:doc.editionYear, sermonYear:doc.sermonYear, titleBook:doc.titleBook,titleSermon:doc.titleSermon, genre:doc.genre, place:doc.place,
								publisher:doc.publisher]">
								${doc.authorFirstName} ${doc.authorLastName}  (${doc.editionYear})

							</g:link> 
							<r:script>
								$('#docInfo${i}').click(function() {
$('#selected-doc').load(this.href);
<%--								$('#selected-doc').load(this.href,function(){loadTipsy();});--%>
									$('#collection-info').hide();	
									$('#selected-doc').show();
									closeNav();					
									return false;
								});
							</r:script>
						
				</g:each>

		
			<div class="pagination">
				<g:paginate next="Suivant" prev="Précédent" action="index" total="${total}" params="${params}" />
			</div>
   
  </div>
</div>


	<div id="page-body" class ="block">
	
	
	
<%--	<div id="status" class="column span-4">--%>
	<div id="collectionMenu" >
			<span style="font-size:20px;cursor:pointer" onclick="openNav()">&#9776; COLLECTION</span>
			</div>		



		
<%--		<div id ="doc-container" class="column span-19 last" >--%>
		<div id ="doc-container"  >
		<div id="collection-info" >
<%--		  <g:render template="docs/collection-stats" model="${[noDocs:total,totalTokens:totalTokens,tokens:tokens]}"></g:render>--%>
		  <g:render template="docs/collection-info" model="${[docs:docs]}"></g:render>
		 </div>
			<div id="selected-doc" style="display:none"> </div>
		
		<div class="pagination">
				<g:paginate next="Suivant" prev="Précédent" action="index" total="${total}" params="${params}" />
			</div>	
		   
		</div>	


	
</div>

</div>

<div class="columne span-4 last">
<%----%>
<%--<div class="modal fade" tabindex="-1" role="dialog">--%>
<%--  <div class="modal-dialog">--%>
<%--    <div class="modal-content">--%>
<%--      <div class="modal-header">--%>
<%--        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>--%>
<%--        <h4 class="modal-title"></h4>--%>
<%--      </div>--%>
<%--      <div class="modal-body">--%>
<%-- --%>
<%--      </div>--%>
<%--      <div class="modal-footer">--%>
<%--        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
<%--      </div>--%>
<%--    </div><!-- /.modal-content -->--%>
<%--  </div><!-- /.modal-dialog -->--%>
<%--</div><!-- /.modal -->--%>
</div>




</g:applyLayout>
