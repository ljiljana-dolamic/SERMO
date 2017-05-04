<%--<div id="${person.id}" class="person span-20">--%>
<div class="person block">
	<div class = "blockrow">
	<div class="column span-6">
		<g:img dir='images/people' file="${person.id}.png" height="200"
			alt='no image' />

	</div>
	<div class="column span-12 append-6 last">
		<table style="width: 100%">
			<tr class="block">
				<td class="column span-5 ">NOM:</td>
				<td class="column span-16 last">
					${person.lastName}
				</td>

			</tr>
			<tr class="block">

				<td class="column span-5 ">Prénom:</td>
				<td class="column span-16 last">
					${person.firstName}
				</td>

			</tr>
			<tr class="block">

				<td class="column span-5 ">E-mail: </td>
				<td class="column span-16 last">
						${person.mail}
				</td>

			</tr>


		</table>
	</div>
</div>
</div>
