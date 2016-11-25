package ch.unine.ILCF.SERMO


import ch.unine.ILCF.SERMO.DocumentDisplayService;
import ch.unine.ILCF.SERMO.statistics.CollectionStatsService;


class CollectionController {
	
	DocumentDisplayService documentDisplayService
	
	CollectionStatsService collectionStatsService
	
	
    def index() {
		params.max = params.max ?: 10
		def docsInCollection = Collection.list(params)
		def total= Collection.list().size()
		
		
		Map result = collectionStatsService.getCollBaseStats();
	
		[docs:docsInCollection, total:total, totalTokens:result.totalCount ,
			tokens:result.noTokens,
			params:params]
	}
	

	
	
	
	def showDocInfo(){
		def id = params.docId
		
		 
		
		render(template : "docs/doc_info_head", model:[params:params]);
		def filedir = params.filedir?: grailsApplication.config.sermoXMLDefault.filedir;
		
		
		def frontPageDetails = documentDisplayService.process(filedir,id,"front");
		
		render(template : "docs/doc-front", model:[docId:id,page:frontPageDetails.page,pages:frontPageDetails.pages])
		
		def bodyPageDetails = documentDisplayService.process(filedir,id,"body");
		
		
		render(template : "docs/doc-body", model:[docId:id,page:bodyPageDetails.page,pages:bodyPageDetails.pages])
		
		
		Map docTag = collectionStatsService.docTagCount(id);
		
		
		Map docToken = collectionStatsService.docTokenCount(id);
	
		//render(template : "docs/doc-stats", model:[docId:id,tagInfo:docTag,tokenInfo:docToken])
		
		render(template : "docs/doc-meta", model:[docId:id,params:params])
//		
		 
		
	}
	
	def showSelectedPage () {
		if(params.pageSelect)
		System.out.println(params.pageSelect)
		
	}

	def showPageImage(){
		def doc_id = params.docId
		def page_no  = params.pageNo+".png"
		//def page =params.id+"_"+ params.pn
		//System.out.println(page);
		def file = imageFile(doc_id,page_no)
		try {
			renderImage(file);
		} catch (Throwable ignore) {
			file = grailsAttributes.getApplicationContext().getResource(
				"images/sermo/broken_image.png").getFile()
			renderImage(file)
		}
		
	}
	
	void renderImage(File imgFile){
		byte[] image = imgFile.bytes

		response.contentType = "image/png";
		response.outputStream << image
	}
	
	
	File imageFile(String name, String page){
		def imageDir = grailsApplication.config.sermoPagesImages.filedir
		return new File("$imageDir/$name/$page")
	}

	def getImageUrl(){
		def doc_id = params.docId
		def page_no  = params.pageNo+".png"
		def file = imageFile(doc_id,page_no)
		try {
			 g.createLink(renderImage(file));
		} catch (Throwable ignore) {
			file = grailsAttributes.getApplicationContext().getResource(
				"images/sermo/broken_image.png").getFile()
			g.createLink(renderImage(file));
		}
	}

	
	def showCollectionStats(){
		
	
		def total= Collection.list().size()
		
		Map result = collectionStatsService.getCollBaseStats();
		
		[ total:total, totalTokens:result.totalCount ,
//			
			tokens:result.noTokens]
		
	}
	
//	def docTagInfo(String doc_id){
//		
//	
//		
//		
//		Map result = collectionStatsService.getDocTagCount();
//		
//		[ refBibl:result.bibl, noQuote:result.quote ,
////
//			noPages:result.pb,noNotes:result.note]
//		
//	}
	
  
}
