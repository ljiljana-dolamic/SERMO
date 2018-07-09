<div  class="docToShow">
<div class="status docInfo">
<table style="width: 100%">
<tr class="block">

						<td class="column span-4 ">Auteur:</td>
						<td class="column span-12">
<%--${params.authorLastName}, ${params.authorFirstName}  (${params.editionYear})--%>
${doc.author_last_name}, ${doc.author_first_name}  (${doc.edition_year})
</td>
<td id = "searchDoc" class="status docPartToShow column span-6 last"> 
						<input class="span-18 column" type="search" id="query" placeholder="Recherche dans ce sermon..">
<button class="btn span-4 column" id="query_btn" onclick="searchInDoc('${doc.doc_id}')">Valider</button>
</td>
</tr>
<tr class="block">

						<td class="column span-4 ">Titre:</td>
						<td class="column span-16 last">${doc.title_sermon}
</td>

</tr>
</table>
</div>

<div>
<ul id="docInfoTabs" class="block row" >

<li id = "showFront" class="status docPartToShow  span-6 column ">Titre de recueil</li>
<li id = "showSermon" class="status docPartToShow  span-6 column selected">Sermon</li>
<li id = "showStats" class="status docPartToShow span-6 column">Métadonnées</li>

</ul>
</div>
</div>
