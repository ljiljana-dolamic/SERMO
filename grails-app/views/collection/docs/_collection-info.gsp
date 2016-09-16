<div id="collection_info">
	<g:each in="${docs}" var="doc" status="i">
		<div class="status">
		 <div class= "block row">
			<div class="titleImageThumb column span-4">
				<g:render template="docs/thumb/titleImageThumb" model="${[doc:doc]}"></g:render>
			</div>
			<div class=" column span-18 last">

				<table style="width: 100%">

					<tr class="block">

						<td class="column span-4 ">Auteur:</td>
						<td class="column span-18 last">
							${doc.authorLastName}, ${doc.authorFirstName}
						</td>

					</tr>
					<tr class="block">

						<td class="column span-4 ">Année:</td>
						<td class="column span-18 last">
							${doc.editionYear}
						</td>

					</tr>
					<tr class="block">

						<td class="column span-4">Titre de recueil :</td>
						<td class="column span-18 last">
							${doc.docName}
						</td>

					</tr>


				</table>

			</div>
			</div>
		</div>
	</g:each>
</div>