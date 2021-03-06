<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<html>
<!--<![endif]-->
<head>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title><g:layoutTitle default="SERMO" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon"
	href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
<%--<link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />--%>
<link rel="stylesheet"
	href="${resource(dir:'css/blueprint',file:'liquid.css')}"
	type="text/css" />
<g:javascript library="jquery" />

<g:layoutHead />

<r:require module="sermo" />

<r:layoutResources />

</head>
<body>
<g:render template="/header/header" />
	<div id="wraper" class="content  block">
		

			<g:layoutBody />
	</div>
<div id="bottomContent">
			<g:render template="/footer/footer" />
		</div>
	<r:require module="sermo" />

	<r:layoutResources />
</body>

</html>
