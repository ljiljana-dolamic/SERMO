package ch.unine.ILCF.SERMO

class CollectionController {
	//def IMAGES_DIR = grailsApplication.config.sermoPagesImages.filedir
	
    def index() {
		params.max = params.max ?: 10
		def docsInCollection = Collection.list(params)
		def total= Collection.list().size()
		
		[docs:docsInCollection, total:total, params:params]
	}
	
	def withLayout() {
		params.max = params.max ?: 10
		def docsInCollection = Collection.list(params)
		def total= Collection.list().size()
		
		[docs:docsInCollection, total:total, params:params]
		
	}
	
	
	
	def showDocInfo(){
		def id = params.id
		params.remove('id')
		render(template : "docs/doc_info_head", model:[params:params]);
		render(template : "docs/doc-body", model:[docId:id])
		 //render "$id"
		
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

//	String getImageDir(){
//		if(!_imageDir){
//			_imageDir = grailsApplication.config.sermoPagesImages.filedir
//		}
//		_imageDir
//	}
}
