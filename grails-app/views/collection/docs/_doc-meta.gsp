<div id="${docId}_meta" class="stats docs " style="display: none">

	<div class="status block row">

		<div class=" column span-24 last">

			<table style="width: 100%">

				<tr class="block">

					<td class="column span-8 ">Auteur:</td>
					<td class="column span-12 last">
						${params.authorLastName},	${params.authorFirstName} 
					</td>

				</tr>
				<tr class="block">

					<td class="column span-8 ">Titre de recueil:</td>
					<td class="column span-12 last">
						${params.titleBook}
					</td>

				</tr>
				<tr class="block">

					<td class="column span-8 ">Titre de sermon:</td>
					<td class="column span-12 last">
						${params.titleSermon}
					</td>

				</tr>
				<tr class="block">

					<td class="column span-8 ">Date edition:</td>
					<td class="column span-12 last">
						${params.editionYear}
					</td>

				</tr>
				<tr class="block">

					<td class="column span-8 ">Imprimeur:</td>
					<td class="column span-12 last">
						${params.publisher}
					</td>

				</tr>
				<tr class="block">

					<td class="column span-8 ">Lieu:</td>
					<td class="column span-12 last">
						${params.place}
					</td>

				</tr>
				
<tr class="block">

						<td class="column span-8 ">Date de la prononciation:</td>
						
						<td class="column span-12 last">
						<g:if test="${params.sermonYear}">
							${params.sermonYear}
						</g:if>
						<g:else>
   UNKNOWN
</g:else>	
						</td>

					</tr>

					<tr class="block">

						<td class="column span-8 ">Type de sermon:</td>
						
						<td class="column span-12 last">
						<g:if test="${params.genre}">
							${params.genre}
						</g:if>
						<g:else>
   UNKNOWN
</g:else>	
						</td>

					</tr>
					
				
				

			</table>


		</div>
	</div>




</div>