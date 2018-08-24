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

						<td class="column span-8 ">Type de publication:</td>
						<td class="column span-12 last">
						<g:if test="${doc.how_published=='recueil'}">
						
								Recueil de ${doc.no_sermons} sermons
						
						</g:if>
						<g:else>
   								Sermon isolé
						</g:else>	
						</td>

					</tr>
							
				<g:if test="${doc.type_collection}">
					<tr class="block">

						<td class="column span-8 ">Type de collection:</td>
						
						<td class="column span-12 last">

							
								${doc.type_collection}
							
						</td>

					</tr>
				</g:if>
				<g:if test="${doc.theme_collection}">
					<tr class="block">

						<td class="column span-8 ">Theme de collection:</td>
						
						<td class="column span-12 last">

							
								${doc.theme_collection}
							
						</td>

					</tr>
				</g:if>
				<tr class="block">

					<td class="column span-8 ">Titre de sermon:</td>
					<td class="column span-12 last">
<%--						${params.titleSermon}--%>
						${doc.title_sermon}
					</td>

				</tr>
				<tr class="block">

						<td class="column span-8 ">Type de sermon:</td>
						
						<td class="column span-12 last">

							<g:if test="${doc.genre}">
								${doc.genre}
							</g:if>
							<g:else>
   								INCONNU
							</g:else>	
						</td>

					</tr>
				<g:if test="${doc.sermon_theme}">
					<tr class="block">

						<td class="column span-8 ">Theme de sermon:</td>
						
						<td class="column span-12 last">

							
								${doc.sermon_theme}
							
						</td>

					</tr>
				</g:if>
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

							<g:if test="${doc.sermon_date}">
								${doc.sermon_date}
							</g:if>
							<g:else>
   								INCONNU
							</g:else>	
						</td>

					</tr>
					
					

<tr class="block">

						<td class="column span-8 ">Texte biblique commenté:</td>
						<td class="column span-12 last">
					<g:each in="${doc_bibl}" var="docBibl" status="i">
						<g:if test="${docBibl.bibl_text}">
							${docBibl.bibl_text} <br/>
						</g:if>
						<g:else>
							INCONNU
						</g:else>

					</g:each>
				
</td>

				</tr>	
<tr class="block">

						<td class="column span-8 ">Paratexte:</td>
						<td class="column span-12 last">				
						<g:each in="${doc_para}" var="docPara" status="i">
						  <g:if test="${docPara.pt_type =="preface"}">
							<strong>Préface:</strong><br/>
							${docPara.para_text}<br/>
						  </g:if>
						  <g:elseif test="${docPara.pt_type == "epistle"}">
							<strong>Êpitre:</strong><br/>
							${docPara.para_text}<br/>
							 
						  </g:elseif>
						  <g:elseif test="${docPara.pt_type == "tableOfContents"}">
							<strong>Table des matières:</strong><br/>
							${docPara.para_text}<br/>
							 
						  </g:elseif>
						  <g:else>
							<strong>Autre:</strong><br/>
							${docPara.para_text}<br/>
						</g:else>

					</g:each>		

			</table>


		</div>
	</div>




</div>