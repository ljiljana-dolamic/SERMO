<%--<g:link view="/index">Sermo</g:link>--%>

<a href="${createLink(uri: '/', absolute: true)}">SERMO</a>

<ul class="about_dropdown-content">
	<li><g:link controller="apropos" action="index"
			params="[part: 'projet']">
								Projet SERMO

							</g:link></li>
	<li><g:link controller="apropos" action="index"
			params="[part: 'corpus']">
								Corpus SERMO

							</g:link></li>
	<li><g:link controller="apropos" action="index"
			params="[part: 'chaine']">
								Chaine de traitement

							</g:link></li>
	<li><g:link controller="equipe" action="index">
								Équipe

							</g:link></li>

</ul>