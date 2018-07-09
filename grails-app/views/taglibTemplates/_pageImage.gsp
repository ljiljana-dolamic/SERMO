<div class="pageImage status column span-${size} last">
	<%--<g:img dir='images/people' file='LjD.png'  alt='no image' height= '400' />--%>
	<g:each in="${pages}" var="docPage" status="i">
		<g:if test="${docPage == 'titre'}">
			<img id="${docId}_${docPage}_image" class="currentPageImage titre titreImage"
				src="${createLink(controller:'collection', action:'showPageImage',  params: [docId: docId, pageNo: docPage])}"
				 />
		</g:if>
		<g:else>
			<g:if test="${docPage.equals(pageToShow.toString())}">
				<img id="${docId}_${docPage}_image" class="currentPageImage selectedImage"
					src="${createLink(controller:'collection', action:'showPageImage',  params: [docId: docId, pageNo: docPage])}"
					 />
			</g:if>
			<g:elseif test="${i == 0}">
				<g:if test="${pageToShow == '-1'}">
					<img id="${docId}_${docPage}_image" class="currentPageImage selectedImage"
						src="${createLink(controller:'collection', action:'showPageImage',  params: [docId: docId, pageNo: docPage])}"
					 />
				</g:if>
				<g:else>
				<img id="${docId}_${docPage}_image" class="currentPageImage selectedImage"
						src="${createLink(controller:'collection', action:'showPageImage',  params: [docId: docId, pageNo: docPage])}"
					 style="display: none;"/>
				</g:else>
			</g:elseif>
			<g:else>
				<img id="${docId}_${docPage}_image" class="currentPageImage"
					src="${createLink(controller:'collection', action:'showPageImage',  params: [docId: docId, pageNo: docPage])}"
					style="display: none;"  />
			</g:else>
		</g:else>

	</g:each>

</div>