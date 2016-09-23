<div id="collection_info">
	<g:each in="${docs}" var="doc" status="i">
		<div class="status">
		 <div class= "block row">
			<div class="titleImageThumb column span-4">
				<g:render template="docs/thumb/titleImageThumb" model="${[doc:doc]}"></g:render>
			</div>
			<div class=" column span-20 last">

				<table style="width: 100%">

					<tr class="block">

						<td class="column span-5 ">Auteur:</td>
						<td class="column span-16 last">
							${doc.authorLastName}, ${doc.authorFirstName}
						</td>

					</tr>
					<tr class="block">

						<td class="column span-5 ">Année:</td>
						<td class="column span-16 last">
							${doc.editionYear}
						</td>

					</tr>
					<tr class="block">

						<td class="column span-5">Titre de recueil :</td>
						<td class="column span-16 last">
							${doc.docName}
						</td>

					</tr>


				</table>

			</div>
			</div>
		</div>
	</g:each>
</div>