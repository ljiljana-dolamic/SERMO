<%--<div class="selectPage status block row">--%>
<div class="selectPage status ">

<%--		<div class="column span-2 prepend-6">--%>
		<div class="selectPageNav">
			<button id="prevPage" onclick="previousPage()">Prev</button>
		</div>
<%--		<div class="column span-6">--%>
<div class="selectPageNav">
				<select class="pageSelect_menu" name="pageSelect" id="pageSelect_menu" onchange="changePage(this.value)">

					<g:each in="${pages}" var="docPage" status="i">

						<g:if test="${i == 0}">
							<option value="${docId}_${docPage}" selected="selected">
								${docPage} [${i+1} / ${pages.size()}]
							</option>
						</g:if>

						<g:else>
							<option value="${docId}_${docPage}">
								${docPage} [${i+1} / ${pages.size()}]
							</option>
						</g:else>
					</g:each>

				</select>

		</div>

<%--		<div class="column span-2 append-6 last">--%>
		<div class="selectPageNav">
			<button id="nextPage" onclick="nextPage()">Next</button>
			
	</div>	
	

</div>

