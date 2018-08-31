<div class="status">

	<div class="block row">

		<div class=" column prepend-2 span-20 append-2 last">

			<table style="width: 100%">


				<tr class="block">
					<td><h1>Publications</h1></td>
				</tr>
			</table>
			<table style="width: 100%" id="bibtex_sermoPubBib">

			</table>
			<table style="width: 100%" id="bibtex_sermoPubBibNoPdf">

			</table>
			<table style="width: 100%">


				<tr class="block">
					<td><h1>Bibliographie</h1></td>
				</tr>
			</table>
			<table style="width: 100%">


				<tr class="block">
					<td><h2>Bibles</h></td>
				</tr>
			</table>
			<table style="width: 100%" id="bibtex_bibBibles">

			</table>
			
			<hr>
			<table style="width: 100%">


				<tr class="block">
					<td><h2>Dictionnaires et grammaires</h></td>
				</tr>
			</table>
			<table style="width: 100%" id="bibtex_bibDicoGram">

			</table>
			
			<hr>
			
			<table style="width: 100%">


				<tr class="block">
					<td><h2>Livres</h></td>
				</tr>
			</table>
			<table style="width: 100%" id="bibtex_bibBook">

			</table>
			
			<hr>
             <table style="width: 100%">


				<tr class="block">
					<td><h2>Articles</h></td>
				</tr>
			</table>
			<table style="width: 100%" id="bibtex_bibArticle">

			</table>

		</div>
	</div>

</div>

<%--<textarea style="display:none" name="bibtex_input" id="bibtex_input" cols=50 rows=20>--%>
<%--    <g:render template= "bibContent" model="[bibCode:sermoBook]"></g:render>--%>
   <g:include controller="documentation" action="bibContent" params="[bibCode:'sermoPubBib', target:'bibtex_sermoPubBib', pdf:'pdf']"/>
   <g:include controller="documentation" action="bibContent" params="[bibCode:'sermoPubBibNoPdf', target:'bibtex_sermoPubBibNoPdf']"/>
   <g:include controller="documentation" action="bibContent" params="[bibCode:'sermoBibBIBLES', target:'bibtex_bibBibles']"/>
   <g:include controller="documentation" action="bibContent" params="[bibCode:'sermoBibDictionnaires', target:'bibtex_bibDicoGram']"/>
   <g:include controller="documentation" action="bibContent" params="[bibCode:'sermoBibBook', target:'bibtex_bibBook']"/>
   <g:include controller="documentation" action="bibContent" params="[bibCode:'sermoBibArticle', target:'bibtex_bibArticle']"/>
    

<div id="bibtex_errors"></div>



<table style="display:none" >
<tr class="bibtex_template">
    <td>
    <span class="author"></span> (<span class="if year"><span class="year"></span></span>).
    <strong><span class="title"></span></strong>.
    <span class="if booktitle">
    	<span class="booktitle"></span>
    </span>
    <span class="if journal">
		<span class="journal"></span>.
    </span>
    <span class="if publisher">
		<span class="publisher"></span>.
    </span>
    <span class="if volume">
	v.	<span class="volume"></span>,
    </span>
    <span class="if pages">
      p. <span class="pages"></span>.
    </span>
   
    <a class="bibtexCodeLink">
	    [bib]
    </a>
    
  </td>
</tr>
</table>

<div id="myModal" class="modal">
    <div class="modal-content">
      <div class="modal-header">
        <span class="close-modal">&times;</span>
        <h4 class="modal-title"></h4>
      </div>
      <div class="modal-body">
 
      </div>
      <div class="modal-footer">
        
      </div>
   </div><!-- /modal-content --> 
</div><!-- /.modal -->
