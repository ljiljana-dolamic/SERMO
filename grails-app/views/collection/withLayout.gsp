<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>SERMO|About</title>
<r:require modules="sermo" />
<r:layoutResources />
</head>
<body>
	<r:layoutResources />
	<a href="#page-body" class="skip"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>
	<div id="status" role="complementary">
		<h1>Available documents:</h1>
		<table style="width: 100%">
			<tr>
				<td>Author</td>
				<td>Year</td>
			</tr>
			<g:each in="${docsInCollection}" var="doc" status="i">
				<tr>
					<td>
						${doc.authorLastName}, ${doc.authorFirstName}
					</td>

					<td>
						${doc.editionYear}
					</td>

				</tr>
			</g:each>

		</table>
		<g:paginate action="withLayout" total="${total}" params="${params}" />
		
	</div>
	<div id="page-body" role="main">
		<!--  h1>the document clicked should be displayed here</h1-->
		<p>Congratulations, you have successfully started your first
			Grails application! At the moment this is the default page, feel free
			to modify it to either redirect to a controller or display whatever
			content you may choose. Below is a list of controllers that are
			currently deployed in this application, click on each to execute its
			default action:</p>

		
		</div>
	</div>
</body>
</html>
