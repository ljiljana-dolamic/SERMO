<div class="selectPage status block row">
<%--	<div class="block row">--%>
		<div class="column span-2 prepend-6">
			<button id="prevPage" onclick="previousPage()">Prev</button>
		</div>
		<div class="column span-6">
<%--			<g:form controller="collection" action="showSelectedPage">--%>
<%--				<g:select name="pageSelect" from="${pages}" optionKey="id" class="ajax-loader" />--%>
				<select class="pageSelect_menu" name="pageSelect" id="pageSelect_menu" onchange="changePage(this.value)">
<%--					onchange="submit()">--%>
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
<%----%>
<%--			</g:form>--%>
		</div>
<%--		<div class="column span-2">--%>
<%--			<div>--%>
<%--				<label for="pageSelect">/${pages.size()}</label>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div class="column span-2 append-6 last">
			<button id="nextPage" onclick="nextPage()">Next</button>
			
	</div>	
	
<%--</div>--%>
<%--<r:script>--%>
<%--(function($){--%>
<%--	$(document).ready(function(){--%>
<%--		$('#nextPage').click(function() { --%>
<%--			alert("next clicked.");--%>
<%----%>
<%--		});--%>
<%--	});--%>
<%--})(jQuery);--%>
<%--</r:script>			--%>
</div>



<%--<r:layoutResources />--%>
