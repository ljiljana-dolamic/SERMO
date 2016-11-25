<div id="${docId}_stats" class="stats docs " style="display:none"> 

<div class=" status block row">

			<div class=" column span-24 last">

				<table style="width: 100%">

					<tr class="block">

						<td class="column span-8 ">Nombre des pages:</td>
						<td class="column span-12 last">
							${tagInfo["pb"]}
						</td>

					</tr>
					<tr class="block">

						<td class="column span-8 ">Nombre des tokens dans le sermon:</td>
						<td class="column span-12 last">
							${tokenInfo["body_sermon"]}
						</td>

					</tr>
					<tr class="block">

						<td class="column span-8 ">Nombre des tokens dans les note:</td>
						<td class="column span-12 last">
							${tokenInfo["body_sermon_note"]}
						</td>

					</tr>
					<g:if test="${tokenInfo["body_text"]}">
 

					<tr class="block">

						<td class="column span-8 ">Nombre des tokens dans le text:</td>
						<td class="column span-12 last">
							${tokenInfo["body_text"]}
						</td>

					</tr>
					<tr class="block">

						<td class="column span-8 ">Nombre des tokens dans le text notes:</td>
						<td class="column span-12 last">
							${tokenInfo["body_text_note"]}
						</td>

					</tr>
					</g:if>
					
					</table>
				
				
				</div>
		</div>	
		
	<div class=" status block row">

			<div class=" column span-24 last">
				<table style="width: 100%">
					<tr class="block">

						<td class="column span-8">Nombre de réferences biblique:</td>
						<td class="column span-12 last">
							${tagInfo["bibl"]}
						</td>

					</tr>
					<tr class="block">

						<td class="column span-8">Nombre de citations:</td>
						<td class="column span-112 last">
							${tagInfo["quote"]}
						</td>

					</tr>
					<tr class="block">

						<td class="column span-8">Nombre de notes marginals:</td>
						<td class="column span-12 last">
							${tagInfo["note"]}
						</td>

					</tr>
<%----%>
				</table>

			</div>
		</div>


</div>