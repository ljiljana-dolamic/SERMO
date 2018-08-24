<div class="status">

	<%--	<div class="status">--%>
	<div class="block row">

		<div class="column prepend-2 span-20 append-2 last">

			<table style="width: 100%">


				<tr class="block">
					<td><h1>Corpus SERMO</h1></td>
				</tr>

				<tr class="block">
					<td class="column span-22 last"><a href="../collection">Le corpus SERMO</a> contient ${noDocs}
						sermons protestants francophones, édités pour la plupart à Genève,
						entre 1550 et 1750, représentant ${tokens["body_sermon"]} tokens.
						Ces textes ont été préparés pour l’analyse et l’exploitation
						semi-automatique dans une approche de linguistique diachronique,
						d’histoire des genres paralittéraires et d’analyse du discours en
						diachronie. Les critères de sélection des sermons inclus dans le
						corpus SERMO sont les suivants :
						<ul class="liste_main">
							<li class="liste_main">Date de performance du sermon (quand
								elle est indiquée dans le titre ou le péritexte) ; par défaut,
								date de publication du sermon : comprise entre 1550 et 1750</li>
							<li class="liste_main">Lieu de publication du sermon :
								Genève, à quelques exceptions près, indiquées dans les
								métadonnées</li>
							<li class="liste_main">Sauf exception, le premier sermon de chaque recueil a
								été retranscrit, afin d’adopter un critère unique, qui convienne
								aussi aux recueils qui ne contiennent qu’un seul sermon.</li>
						</ul>

					</td>

				</tr>


			</table>
			<g:include controller="collection" action="listeDesSermons"/>

		</div>
	</div>
	<%--	</div>--%>

</div>

