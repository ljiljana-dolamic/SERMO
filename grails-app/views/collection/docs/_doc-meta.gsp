<div id="${docId}_meta" class="stats docs " style="display: none">

	<div class="status block row">

		<div class=" column span-24 last">

			<table style="width: 100%">

				<tr class="block">

					<td class="column span-8 ">Auteur:</td>
					<td class="column span-12 last">
<%--						${params.authorLastName},	${params.authorFirstName} --%>
						${doc.author_last_name},	${doc.author_first_name}
					</td>

				</tr>
				<tr class="block">

					<td class="column span-8 ">Titre de recueil:</td>
					<td class="column span-12 last">
<%--						${params.titleBook}--%>
						${doc.title_book}
					</td>

				</tr>
				<tr class="block">

					<td class="column span-8 ">Titre de sermon:</td>
					<td class="column span-12 last">
<%--						${params.titleSermon}--%>
						${doc.title_sermon}
					</td>

				</tr>
				<tr class="block">

					<td class="column span-8 ">Date d'édition:</td>
					<td class="column span-12 last">
<%--						${params.editionYear}--%>
						${doc.edition_year}
					</td>

				</tr>
				<tr class="block">

					<td class="column span-8 ">Imprimeur:</td>
					<td class="column span-12 last">
<%--						${params.publisher}--%>
						${doc.publisher}
					</td>

				</tr>
				<tr class="block">

					<td class="column span-8 ">Lieu d'édition:</td>
					<td class="column span-12 last">
<%--						${params.place}--%>
						${doc.place}
					</td>

				</tr>
				
<tr class="block">

						<td class="column span-8 ">Date de prononciation:</td>
						
						<td class="column span-12 last">
<%--						<g:if test="${params.sermonYear}">--%>
<%--							${params.sermonYear}--%>
<%--						</g:if>--%>
<%--						<g:else>--%>
<%--   UNKNOWN--%>
<%--</g:else>	--%>
<g:if test="${doc.sermon_date}">
							${doc.sermon_date}
						</g:if>
						<g:else>
   INCONNU
</g:else>	
						</td>

					</tr>

					<tr class="block">

						<td class="column span-8 ">Type de sermon:</td>
						
						<td class="column span-12 last">
<%--						<g:if test="${params.genre}">--%>
<%--							${params.genre}--%>
<%--						</g:if>--%>
<%--						<g:else>--%>
<%--   UNKNOWN--%>
<%--</g:else>	--%>
<g:if test="${doc.genre}">
							${doc.genre}
						</g:if>
						<g:else>
   INCONNU
</g:else>	
						</td>

					</tr>
					
				
				

			</table>


		</div>
	</div>




</div>