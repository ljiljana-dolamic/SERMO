<%--<div id="${person.id}" class="person span-20">--%>
<div class="person block">
	<div>
		<div class="column span-12">
			<g:img dir='images/people' file="${person.id}.png" height="200"
				alt='no image' />

		</div>
		<div class="column span-12 last">
			<div class="personal">
				NOM: <span class="personal">
					${person.lastName}
				</span>
			</div>
			<div class="personal">
				Prénom: <span class="personal">
					${person.firstName}
				</span>
			</div>
			<div class="personal">
				E-mail: <span class="personal">
					${person.mail}
				</span>
			</div>
		</div>
	</div>
</div>
<%--</div>--%>