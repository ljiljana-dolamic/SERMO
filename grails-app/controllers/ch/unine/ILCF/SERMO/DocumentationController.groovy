package ch.unine.ILCF.SERMO

class DocumentationController {
	
    def collectionService
	
    def index() { 
		[part:params.part]
	}
	
	def collectionOverview(){
		
		def collOverview = collectionService.getAllDocs();
		
		
	}
	
	def bibContent(){
		def bibCode
		def target
		def pdf
		if(params.bibCode){
			bibCode =params.bibCode
			params.remove('bibCode')
		 }
		if(params.target){
			target =params.target
			params.remove('target')
		 }
		if(params.pdf){
			pdf =params.pdf
			params.remove('pdf')
		 }
		def docName = bibCode+".bib"
		
		def fileDir = grailsApplication.config.bibDir;
		
		def text =new File(fileDir,docName).getText();
		//System.out.println(text);
		
		[bibText:text,target:target, pdf:pdf]
		
	}
	def downloadPDF(){
		def doc_id = params.doc_id
		def fileName = doc_id+".pdf"
		def pdfdir = grailsApplication.config.bibPdfDir
		def file = new File(pdfdir,fileName)
		byte[] pdfBytes = file.bytes
		response.contentType = "application/pdf";
		response.outputStream << pdfBytes
	 }
	
	def downloadTXT(){
		 def name
		 def type
		
		if(params.type){
			type =params.type
			params.remove('type')
		 }
		if(params.name_txt){
			name =params.name_txt
			params.remove('name_txt')
		 }
		def fileName = name + "."+ type
		def txtdir = grailsApplication.config.downloadDir
		def file = new File(txtdir,fileName)
		byte[] xmlBytes = file.bytes
		response.contentType = "text/csv";
		response.setHeader("Content-disposition", "filename=${fileName}")
		response.outputStream << xmlBytes
	 }
	
	def bibPdfLink(){
		def doc_id = params.doc_id
		
		[doc_id:doc_id]
		
	}
	
	def tutorialVideo(){
		def name_v = params.name_v
		def fileName = name_v+".mp4"
		if(params.name_v){
			name_v =params.name_v
			params.remove('name_v')
		 }
		
		def videoDir=grailsApplication.config.videoDir
		
		def source_v = videoDir.toString()+fileName;
		
		[name_v:name_v,source_v:source_v]
		
	}
}
