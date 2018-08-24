<%--<div id="header" class="container">--%>
<div id="header">
		<div id="sermoLogo">
			<a href="http://sermo.unine.ch/SERMO"> <img class="logo"
					src="${resource(dir:'images/sermo',file:'sermoLogo4.png')}"
					alt="SERMO" />
			</a>
<%--			<div  id="v_beta" title="Version finale: août 2018">beta</div>--%>
		</div>
		

<div  id="navigation-bar" >
		<ul class="nav">
			<li class="nav about_dropdown">
				<g:render template="/navigation/menu-about" />
			</li>

			<li class="nav">
				<g:render template="/navigation/menu-collection" />
			</li>
			<li class="nav about_dropdown">
				<g:render template="/navigation/menu-documentation" />
			</li>
			<li class="nav">
				<a href="http://sermo.unine.ch/SERMO-CQPweb" target="_blank">Analyse</a>
		
			</li>

		</ul>

</div>
</div>