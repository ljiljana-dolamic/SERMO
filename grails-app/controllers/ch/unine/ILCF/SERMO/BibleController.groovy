package ch.unine.ILCF.SERMO

import ch.unine.ILCF.SERMO.BibleDisplayService;

class BibleController {
	BibleDisplayService bibleDisplayService
	
    def index() {
		
		
		def bibleSegond = grailsApplication.config.bibleSegond21.xml;
		def verses
		StringBuilder result= new StringBuilder();
		
		if(params.verses){
		   verses =params.verses
		   params.remove('verses')
	    }
		
		if(params.bible && !params.bible.equals("bibleSegond21")){
			
			def bibleFile = params.bible;
			
			def bibleFilePath = grailsApplication.config.getProperty(bibleFile).xml;
			//System.out.println("path "+bibleFilePath);
			//System.out.println("path S "+bibleSegond);
			result.append("<h1> ").append(grailsApplication.config.getProperty(bibleFile).title).append("</h1>");
			result.append(bibleDisplayService.getBibleText(bibleFilePath,verses));
			params.remove('bible');
		}
		
		
		//String result = bibleDisplayService.getBibleText(bible,verses)
		//String result = "Le verset correspondant de la Bible Version Segond 21 sera affiché prochainement; 2"
		 result.append("<h1> Bible Version Segond 21 </h1>");
		 result.append(bibleDisplayService.getBibleText(bibleSegond,verses));
		
		[text:result]
	}


	
}
