<table style="width: 100%">
	<tr class="block">
		<td>
			<h1>Liste des sermons</h1></td>
	</tr>
	<g:each in="${docs}" var="doc" status="i">
	<tr class="block">
		<td>
			${doc.author_last_name}, ${doc.author_first_name} (${doc.edition_year}) <strong><em>${doc.title_sermon}</em></strong>, ${doc.title_book}, ${doc.place}.
			 
			 <g:link action="downloadXML" params ="[doc_id:doc.doc_id]"><img 
					src="${resource(dir:'images/sermo',file:'xml-icon.png')}"
					alt="xml" height="24px" target="_blank" title="xml"/>
			</g:link>
<%--			<g:link action="downloadPDF" params ="[doc_id:doc.doc_id]"><img --%>
<%--					src="${resource(dir:'images/sermo',file:'pdf-icon.png')}"--%>
<%--					alt="pdf" height="24px" target="_blank" title="pdf"/>--%>
<%--			</g:link>--%>
		</td>	
		</tr>		
	</g:each>

			</table>