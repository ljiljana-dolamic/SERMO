<table style="width: 100%">
<g:each in="${tutorials}" var="section" status="i">

	<tr class="block">
		<td><h1>${section.value.title}</h1></td>
	</tr>
 <g:each in="${section.value.tutorials}" var="tutorial" status="j">
 	<g:each in="${tutorial}" var="t" status="k">
	<tr class="block">
		<td>
			<div class="tut_video column span-20" id="${t.key}">
				${t.value }
				</div>
			<div class="close_video column span-2 last"
				id="${t.key }_video_close" style="color: red; display: none">&#10008;</div>
		</td>
	</tr>
	</g:each>
	</g:each>
</g:each>
</table>
