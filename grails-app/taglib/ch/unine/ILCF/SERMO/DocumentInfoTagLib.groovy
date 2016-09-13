package ch.unine.ILCF.SERMO

class DocumentInfoTagLib {
	static defaultEncodeAs = 'raw'
	//static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	static namespace = "document"
	static final DEFAULT_DOC_NAME ="_1555_Jean_Calvin_RSL"
	
	
	
	def documentInfo = { attrs, body->
		def id = attrs.remove('docId')?.toString()
		def page = attrs.remove('page')
		def pages = attrs.remove('pages')
		
		out << "<div class=\"docBody block row\">"
		out << render(template:"/taglibTemplates/pageContent", model:[page:page]);
		out << render(template:"/taglibTemplates/pageImage", model:[docId:id,pages:pages]);
		out << "</div>"

		out << render(template:"/taglibTemplates/pageSelection", model:[pages:pages,docId:id]);
	}
}

