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



	<div id="page-body" class ="block">
	
		<div id="status" class="column span-4">
			<h1>Collection:</h1>
			<table style="width: 100%">
<%--				<tr>--%>
<%--					<td><h2>Author</h2></td>--%>
<%--					<td><h2>Year</h2></td>--%>
<%----%>
<%--				</tr>--%>

				<g:each in="${docs}" var="doc" status="i">

					<tr>
						
						<td><g:link action="showDocInfo" elementId="docInfo${i}"
								id="${doc.id}" params= "[ aFirstName:doc.authorFirstName, aLastName:doc.authorLastName, year:doc.editionYear]">
								${doc.authorLastName}, ${doc.authorFirstName}

							</g:link> 
							<r:script>
								$('#docInfo${i}').click(function() {

									$('#selected-doc').load(this.href,function(){loadTipsy();});	
															
									return false;
								});
							</r:script>
						</td>

						<td>
							${doc.editionYear}
						</td>

					</tr>

				</g:each>

			</table>
			<div class="pagination">
				<g:paginate action="index" total="${total}" params="${params}" />
			</div>
		</div>
		
		<div id ="doc-container" class="column span-18 last">
			<div id="selected-doc"> </div>
		   
		</div>	

	
</div>


</g:applyLayout>
