<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--<meta name="layout" content="main" />--%>
<title>SERMO|People</title>
<g:applyLayout name="main">

	
		
<%--			<div id="status" class="columne span-4">--%>
				<div  class="column span-5">
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
			<div id = "equipe" class="column span-14 ">
				
					<g:img dir='images/people' file="equipe.PNG" alt='no image' />
					
	<%--					<div id="person-info" class="column span-18 last"></div>--%>
				<div id="person-info" ></div>
					
			</div>
				
			
				
   <div class="column span-4 last">
</div>
			


</g:applyLayout>