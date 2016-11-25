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

									$('#selected-doc').load(this.href,function(){loadTipsy();});
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

				
	
<%--		<div id="status" class="column span-4">--%>
<%--			<h1>Collection:</h1>--%>
<%--			<table style="width: 100%">--%>
				
<%----%>
<%--				<g:each in="${docs}" var="doc" status="i">--%>
<%----%>
<%--					<tr>--%>
<%--						--%>
<%--						<td><g:link action="showDocInfo" elementId="docInfo${i}"--%>
<%--								id="${doc.id}" params= "[ aFirstName:doc.authorFirstName, aLastName:doc.authorLastName, year:doc.editionYear]">--%>
<%--								${doc.authorLastName}, ${doc.authorFirstName}--%>
<%----%>
<%--							</g:link> --%>
<%--							<r:script>--%>
<%--								$('#docInfo${i}').click(function() {--%>
<%----%>
<%--									$('#selected-doc').load(this.href,function(){loadTipsy();});--%>
<%--									$('#collection-info').hide();	--%>
<%--									$('#selected-doc').show();					--%>
<%--									return false;--%>
<%--								});--%>
<%--							</r:script>--%>
<%--						</td>--%>
<%----%>
<%--						<td>--%>
<%--							${doc.editionYear}--%>
<%--						</td>--%>
<%----%>
<%--					</tr>--%>
<%----%>
<%--				</g:each>--%>
<%----%>
<%--			</table>--%>
<%--			<div class="pagination">--%>
<%--				<g:paginate action="index" total="${total}" params="${params}" />--%>
<%--			</div>--%>

		
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


</g:applyLayout>
