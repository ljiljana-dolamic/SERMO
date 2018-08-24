<div id="collection_info">
	<g:each in="${docs}" var="doc" status="i">
<%--		<div class="status" id="${doc.id}_status">--%>
<div class="status" id="${doc.doc_id}_status">
		 <div class= "block row">
			<div class="titleImageThumb column span-4">
				<g:render template="docs/thumb/titleImageThumb" model="${[doc:doc,i:i]}"></g:render>
			</div>
			<div class=" column span-20 last">

				<table style="width: 100%">

					<tr class="block">

						<td class="column span-5 ">Auteur:</td>
						<td class="column span-16 last">
							${doc.author_last_name}, ${doc.author_first_name}
						</td>

					</tr>
					<tr class="block">

						<td class="column span-5 ">Année:</td>
						<td class="column span-16 last">
							${doc.edition_year}
						</td>

					</tr>
					 <tr class="block">

						<td class="column span-5">Titre de sermon :</td>
						<td class="column span-16 last">
						${doc.title_sermon}
						</td>

					</tr>
					<tr class="block">

						<td class="column span-5">Titre de recueil :</td>
						<td class="column span-16 last">
						${doc.title_book}
						</td>

					</tr>
                  

				</table>

			</div>
			</div>
		</div>
	</g:each>
</div>