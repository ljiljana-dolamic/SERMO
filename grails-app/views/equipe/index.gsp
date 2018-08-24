<g:applyLayout name="main">
	<title>SERMO|Equipe</title>
	<div class="column span-5">
		<%--<g:include controller="filters" action="countByAuthor"/>--%>
		<g:render template="/apropos/parts_menu/menu"></g:render>
	</div>
	<div class="column span-16 append-3">
	
		<div class="status">
			<div class="block row">
				<div class="column prepend-2 span-20 append-2 last">
					<g:img dir='images/people' file="equipe.PNG" alt='équipe sermo'
						width="800" />
				</div>
			</div>
		</div>
		<div class="status">
			<div class="block row">
				<div class="column prepend-2 span-20 append-2 last">
					<div id="team-content">
						<h1>Notre équipe</h1>

						<div id="teamcontent" class="clearfix">
							<g:img dir='images/people' file="equipe.PNG" alt='no image'
								id="replaceimg" class="bigimg" height="400" />
							<%--              <img id="replaceimg" src="http://i.imgur.com/TkSNOpF.jpg" class="bigimg" width="200" height="200">--%>
							<div id="teamdetails">
								<h3 id="bigname">Name</h3>
								<h4 id="bigjob">Title</h4>
								<h4 id="bigurl">url page</h4>
								<p id="bigdesc">A brief description.</p>
							</div>
						</div>

						<ul class="team clearfix">
							<li><table><tr><td><g:img dir='images/people' file="CSD.jpg"
									alt="Carine SKUPIEN DEKENS" height="200" class="profilepic" />
									<span class="hcontent job">Professeure <a
									href="https://www.unine.ch/ilcf/home.html" target="_blank">ILCF</a></span>
								<span class="hcontent url"><a
									href="https://www.unine.ch/cms/render/live/fr/sites/ilcf/home/ilcf/collaborateurs/carine-skupien-1.html">Page
										personelle</a></span> 
										<span class="hcontent desc"><strong>Auteur
										d’une thèse sur la traduction française de la Bible par
										Sébastien Castellion (1555), Carine Skupien Dekens consacre
										l’essentiel de ses recherches à la langue française des XVI au
										XVIIIe siècle, en particulier dans son usage religieux. Les
										sermons protestants constituent son centre d’intérêt depuis
										quelques années. A ce titre, elle dirige et coordonne le
										projet SERMO depuis septembre 2015. Elle enseigne en outre le
										français langue étrangère, l’histoire et la civilisation
										françaises ainsi que l’histoire de la langue française pour un
										public FLE de l’Institut de langue et civilisation françaises
										(ILCF) de l’Université de Neuchâtel.</strong><br></span>
									</tr></td>
									<tr><td><span class="equipe-label">Carine SKUPIEN DEKENS</span> 
									</tr></td>
								</table>	
								</li>

							<li><table><tr><td><g:img dir='images/people' file="MAG.jpg"
									alt='Magdalena AUGUSTYN-GAULTIER' alt="Magdalena AUGUSTYN-GAULTIER" 
									height="200" class="profilepic" />
									<span class="hcontent job">collaboratrice
									scientifique <a href="https://www.unine.ch/ilcf/home.html">ILCF</a>
							</span><span class="hcontent url"></span> <span class="hcontent desc"><strong>
							Docteure en Sciences du langage, Spécialité : Didactique et linguistique<br>
							Intérêts de recherche :
							<ul>
							<li>Sémantique et lexicologie : figement, collocations, mécanismes figuratifs, polysémie</li>
							<li>Analyse du discours : discours rapporté, modalisation, métadiscours</li>
							<li>Linguistique de corpus : création, édition/outillage numérique, exploitation et analyse des corpus écrits</li>
							<li>Didactique du FLE</li>
							
							</ul>


							</strong><br></span></tr></td>
									<tr><td><span class="equipe-label">Magdalena AUGUSTYN-GAULTIER</span> 
									</tr></td>
								</table>	
									<tr><td> </li>

							<li><table><tr><td><g:img dir='images/people' file="DLJ.png" alt='no image'
									alt="Ljiljana DOLAMIC"  height="200"
									class="profilepic" />
									<span class="hcontent job">collaboratrice
									scientifique <a href="https://www.unine.ch/ilcf/home.html">ILCF</a>
							</span><span class="hcontent url"></span> <span class="hcontent desc"><strong>Particulièrement intéressée par le 
							traitement des données textuelles dans les langues morphologiquement complexes et peu étudiées, 
							elle est l'auteur de la thèse intitulé: " Influence de la complexité morphologique de la langue sur les Recherches d'Information" 
							</strong><br></span>
							</tr></td>
									<tr><td><span class="equipe-label">Ljiljana DOLAMIC</span> 
									</tr></td>
								</table>	
								 </li>

							<li><table><tr><td><g:img dir='images/people' file="CiM.png"
									alt='Cinthia MELI' alt="Cinthia MELI" width="200" height="200"
									class="profilepic" />
									 <span class="hcontent job">collaboratrice
									scientifique <a href="https://www.unine.ch/ilcf/home.html">ILCF</a>
							</span><span class="hcontent url"><a
									href="https://www.unige.ch/lettres/framo/enseignant/meli/">Page
										personelle</a></span> <span class="hcontent desc"><strong></strong><br></span>
										</tr></td>
									<tr><td><span class="equipe-label">Cinthia MELI</span> 
									</tr></td>
								</table>	
								</li>


						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="status">
			<div class="block row">
				<div class="column prepend-2 span-20 append-2 last">
					<div id="team-content-2">
						<h1>Autres collaborateurs: </h1>
						
						<ul class="liste_main">
							<li class="liste_main">Aurélie REUSSER-ELZINGRE</li>
								
							<li class="liste_main">Michaela BJUGGFALT-CHÂTEAUX</li>
							<li class="liste_main">Ruth STAWARZ-LUGINBUEHL</li>
							
						</ul>



					</div> <!-- team-content-2 -->

				</div><!-- column prepend-2 span-20 append-2 last -->
			</div><!-- block row -->
		</div><!-- status -->
	</div> <!-- column span-16 -->



</g:applyLayout>