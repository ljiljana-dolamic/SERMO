<div  id="filters" class="span-24">
		<ul class="filters">
			<li class="filters prepend-2 span-20">
				<g:include controller="filters" action="countByAuthor" params="[filter:filter]"/>
			</li>
			<li class="filters prepend-2 span-20">
				<g:include controller="filters" action="countByPubPlace"/>
			</li>

        <li class="filters prepend-2 span-20">
				<g:include controller="filters" action="countByDecade"/>
			</li>
	
		<li class="filters prepend-2 span-20">
				<g:include controller="filters" action="countByGenre"/>
			</li>
		</ul>

</div>

