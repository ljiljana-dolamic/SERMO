<div>
<%--<g:link action="showDocInfo" elementId="docInfoTitleThumb${i}"--%>
<%--								id="${doc.id}" params=  "[docId:doc.id, authorFirstName:doc.authorFirstName, authorLastName:doc.authorLastName, --%>
<%--								editionYear:doc.editionYear, sermonYear:doc.sermonYear, titleBook:doc.titleBook,titleSermon:doc.titleSermon, genre:doc.genre, place:doc.place,--%>
<%--								publisher:doc.publisher]">--%>
<%--								--%>
<%--								<g:link action="doc"  params=  "[docId:doc.id]">--%>
<%--	<img id="title_thumb_${i}" class="imageThumb"--%>
<%--		src="${createLink(controller:'collection', action:'showPageImage',  params: [docId: doc.id, pageNo: "titre"])}" />--%>
<%--		--%>
<%--			</g:link> --%>
			<g:link action="doc"  params=  "[docId:doc.doc_id]">
	<img id="title_thumb_${i}" class="imageThumb"
		src="${createLink(controller:'collection', action:'showPageImage',  params: [docId: doc.doc_id, pageNo: "titre"])}" />
		
			</g:link> 
<%--			<r:script>--%>
<%--								$('#docInfoTitleThumb${i}').click(function() {--%>
<%----%>
<%--									$('#selected-doc').load(this.href);--%>
<%--									$('#collection-info').hide();	--%>
<%--									$('#selected-doc').show();					--%>
<%--									return false;--%>
<%--								});--%>
<%--							</r:script>--%>
</div>

