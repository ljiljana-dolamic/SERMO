<table style="width: 100%">


				<tr class="block">
					<td><h1>Accès aux outils</h1></td>
					</tr>
					<tr class="block">
					<td><h2><a href="https://github.com/ljiljana-dolamic/SERMO-NLP">Accès aux outils de la chaine de traitement SERMO</a></h2></td>
					</tr>
					<tr class="block">
					<td><h2><a href="https://github.com/ljiljana-dolamic/SERMO-CQPweb">Accès au code de la partie analyse</a></h2></td>
					</tr>
					<tr class="block">
					<td><h2><a href="https://github.com/ljiljana-dolamic/SERMO">Accès au code du site web</a></h2></td>
					</tr>
					
			</table>
			
			<table style="width: 100%">


				<tr class="block">
					<td><h1>Téléchargement</h1></td>
					</tr>
					<tr class="block">
					<td><h2>Lexique/Dictionnaire</h2></td>
					</tr>
					<tr class="block">
					<td class="column span-18">Lexique de tokenization</td>
					<td class="column span-4 last">
						<g:link action="downloadTXT" params ="[name_txt:'sermoLex', type:'csv']"><img 
					src="${resource(dir:'images/sermo',file:'csv-icon.png')}"
					alt="csv/tsv" height="24px" target="_blank" title="csv/tsv"/>
			</g:link>
					</td>
					</tr>
					
					
					
					<tr class="block">
					<td class="column span-18">Dictionnaire de TreeTagger (utilisé dans l'option -lex de TreeTagger) </td>
					<td class="column span-4 last">
						<g:link action="downloadTXT" params ="[name_txt:'sermoTTdicoShortNc', type:'tsv']"><img 
					src="${resource(dir:'images/sermo',file:'csv-icon.png')}"
					alt="csv/tsv" height="24px" target="_blank" title="csv/tsv"/>
			</g:link>
					</td>
					</tr>
				<tr class="block">
					<td class="column span-18">Dictionnaire complet (utilisé en post-traitement) </td>
					<td class="column span-4 last">
						<g:link action="downloadTXT" params ="[name_txt:'sermoTTdico', type:'tsv']"><img 
					src="${resource(dir:'images/sermo',file:'csv-icon.png')}"
					alt="csv/tsv" height="24px" target="_blank" title="csv/tsv"/>
			</g:link>
					</td>
					</tr>
					
						</table>