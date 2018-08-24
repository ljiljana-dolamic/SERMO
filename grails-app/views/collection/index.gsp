<g:applyLayout name="main">
	<title>SERMO|Collection</title>
	<div class="column span-5">
		<%--<g:include controller="filters" action="countByAuthor"/>--%>
		<g:render template="filters/filters" model="[filter:filter]"></g:render>
	</div>
	<div class=" column span-16">

		<div id="myNav" class="overlay" style="display: none;">
			<a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
			<div class="overlay-content">
				<g:each in="${docs}" var="doc" status="i">
					<g:link action="doc" params="[docId:doc.doc_id]">
						${doc.author_first_name}
						${doc.author_last_name}  (${doc.edition_year})

							</g:link>


				</g:each>


			</div>
		</div>
		<div class="block page-body">
			<div id="collectionMenu">
				<span style="font-size: 20px; cursor: pointer" onclick="openNav()">&#9776;
					COLLECTION</span>
			</div>
			<div id="coll-container">

				<g:render template="docs/collection-info" model="${[docs:docs]}"></g:render>
			</div>



		</div>

	</div>

	<div class="column span-3 last">
		<div class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title"></h4>
					</div>
					<div class="modal-body"></div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- /.modal -->
	</div>




</g:applyLayout>
