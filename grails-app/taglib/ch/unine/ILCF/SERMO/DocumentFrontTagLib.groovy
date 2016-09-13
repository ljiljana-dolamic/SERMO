package ch.unine.ILCF.SERMO

//import javax.xml.stream.XMLStreamReader;
import ch.unine.ILCF.SERMO.DocumentDisplayService;

class DocumentFrontTagLib {
    static defaultEncodeAs = 'raw'
	//static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	static namespace = "document"
	static final DEFAULT_DOC_NAME ="_1555_Jean_Calvin_RSL"
    
	DocumentDisplayService documentDisplayService
	
	def documentFront = { attrs, body->
		def id = attrs.remove('docId')?.toString()
		def page = attrs.remove('page')
		def pages = attrs.remove('pages')
		//def filedir = params.filedir?: grailsApplication.config.sermoXMLDefault.filedir;

		//def pageDetails = documentDisplayService.process(filedir,id,"front");
		
//		
//		out << "<div class=\"docFront block row\">"
//		out << render(template:"/taglibTemplates/pageContent", model:[page:pageDetails.page]);
//		out << render(template:"/taglibTemplates/pageImage", model:[docId:id,pages:pageDetails.pages]);
//		out << "</div>"
		
		out << "<div class=\"docFront block row\">"
		out << render(template:"/taglibTemplates/pageContent", model:[page:page]);
		out << render(template:"/taglibTemplates/pageImage", model:[docId:id,pages:pages]);
		out << "</div>"

	}
}
