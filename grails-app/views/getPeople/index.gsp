<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>SERMO|People</title>
<r:require modules="sermo" />
<g:javascript library="jquery" />

<r:layoutResources />
</head>
<body>
	<div id="page-body" role="main" class="span-24">
		<div>
<%--			<div id="status" role="complementary" class="span-4">--%>
<div id="status" class="column span-4">
				<h1>Équipe SERMO</h1>
				<table style="width: 100%">

					<g:each in="${sermo_people}" var="person" status="i">
						<tr>
							<td><g:link action="showPersonInfo"
									elementId="personInfo${i}" id="${person.id}">
									  ${person.lastName}, ${person.firstName}
								</g:link> <r:script>
								$('#personInfo${i}').click(function() {
									$('#person-info').load(this.href); return false;
								});
							</r:script></td>
						</tr>
					</g:each>
				</table>

			</div>
			<div id="person-info" class="column span-18 last"></div>

		</div>
	</div>

	<r:layoutResources />
</body>
</html>