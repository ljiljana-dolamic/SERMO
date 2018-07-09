package ch.unine.ILCF.SERMO


import ch.unine.ILCF.SERMO.DocumentDisplayService;
import ch.unine.ILCF.SERMO.filter.FilterService;
import ch.unine.ILCF.SERMO.statistics.CollectionStatsService;
import ch.unine.ILCF.SERMO.CollectionService;


class CollectionController {
	
	DocumentDisplayService documentDisplayService
	
	CollectionStatsService collectionStatsService
	
	CollectionService collectionService
	FilterService filterService
	
	def index() {
		//params.max = params.max?: 10
		
		def docsToShow;
		def filter;
		def filterType;
		params.remove(filter);
		params.remove(filterType);
		if(params.first_name && params.last_name){
			def first_name = params.first_name
			params.remove('first_name')
			def last_name = params.last_name
			params.remove('last_name')
			LinkedList fd = filterService.getAuthorFilterDocsList(first_name,last_name) ;
		    filter=first_name+":"+last_name;
			filterType="author";
			
			docsToShow = collectionService.getDocs(fd);

		}else if(params.place_edition){
			def place_edition = params.place_edition
			params.remove('place_edition')
			LinkedList fd = filterService.getEditionFilterDocsList(place_edition) ;
			filter=place_edition;
			filterType="place";
			docsToShow = collectionService.getDocs(fd);
		}else if(params.decade_start && params.decade_end ){
			def decade_start = params.decade_start
			params.remove('decade_start')
			def decade_end = params.decade_end
			params.remove('decade_end')
			LinkedList fd = filterService.getDecadeFilterDocsList(decade_start,decade_end) ;
			filter=decade_start+":"+decade_end;
			filterType="time";
			docsToShow = collectionService.getDocs(fd);
		}else if(params.genre){
			def genre = params.genre
			params.remove('genre')
			LinkedList fd = filterService.getGenreFilterDocsList(genre) ;
			filter=genre;
			filterType="genre";
			docsToShow = collectionService.getDocs(fd);
		}else{
			docsToShow = collectionService.getAllDocs();
			filter=null;
		}

		
		def total= docsToShow.size()

		[docs:docsToShow, total:total,filter:filter, ft:filterType,
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
	
	def doc(){
		def id = params.docId;
		
		def  doc = collectionService.getDocById(id)[0]; 
		
		
		def filedir = params.filedir?: grailsApplication.config.sermoXMLDefault.filedir;
		
		
		def frontPageDetails = documentDisplayService.process(filedir,id,"front","titre");
		
		def pageToShow;
		if(params.pb){
			 pageToShow = params.pb
			 params.remove('pb')
		}else{
			pageToShow = "-1"
			 
		}
		
		def bodyPageDetails;
		if(params.q){
			def query = params.q
			params.remove('q')
			 bodyPageDetails = documentDisplayService.processSearch(filedir,id,"body",pageToShow,query);
			 if(!pageToShow.equals(bodyPageDetails.pTs) && !bodyPageDetails.pTs.equals('')){
				 pageToShow = bodyPageDetails.pTs;
				 bodyPageDetails = documentDisplayService.processSearch(filedir,id,"body",bodyPageDetails.pTs,query);
			 }
		}else{
		    bodyPageDetails = documentDisplayService.process(filedir,id,"body",pageToShow);
		}
		
		
	//	bodyPageDetails = documentDisplayService.process2(filedir,id,"body",pageToShow,);

		[docId:id,frontPage:frontPageDetails.page,frontPages:frontPageDetails.pages,bodyPage:bodyPageDetails.page, bodyPages:bodyPageDetails.pages, doc:doc, pageToShow:pageToShow, params:params]
		
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
	
  def bible(){
	  def bible=params.bible
	  def verses=params.verses
	  
	   return redirect(controller:'bible', params:[bible:bible , verses:verses ])
  }
	
  
}
