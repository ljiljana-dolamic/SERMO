package ch.unine.ILCF.SERMO

import grails.transaction.Transactional

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.*
import javax.xml.stream.*;

import java.net.URL;

import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import ch.unine.ILCF.SERMO.SelectedPageSummary;

@Transactional
class DocumentDisplayService {
	boolean transactional = false
	
	/** The url. */
	private def url;

	/** The input data. */
	private def inputData;

	/** The factory. */
	private def factory;

	/** The parser. */
	private XMLStreamReader parser;
	def pages;
	
		
	
		def filedir;
		
	
   SelectedPageSummary process(String fileDir,String doc_id, String part){
	   def docName = doc_id +".xml"
	   filedir =  fileDir;
	   //pdfDir =  params.filedir?: grailsApplication.config.sermoPDFpagesDefault.filedir;

	   File xmlFile = new File(filedir,docName);

	   def url = xmlFile.toURI().toURL();
	   //infile = infile;

	   inputData = url.openStream();
	   factory = XMLInputFactory.newInstance();
	   parser = factory.createXMLStreamReader(inputData);

		boolean flagNote = false;
		int noteType= 0; //1- margin;2-foot
		boolean leftNote=true;
		boolean flagChoice = false;
		boolean flagSic = false;
		boolean flagCorr = false;
		String choiceSic="";
		String choiceCorr="";
		boolean start = false;
		StringBuilder noteContent = new StringBuilder();
		StringBuilder sb = new StringBuilder();		
		String localname = "";
		String headType="";
		Deque<String> noteCurrentTagStack = new LinkedList<String>();
		Deque<String> noteCurrentTagToCloseStack = new LinkedList<String>();
		Deque<String> currentTagStack = new LinkedList<String>();
		Deque<String> currentTagToCloseStack = new LinkedList<String>();
		boolean firstPage=true;
		String pageId;
		pages = new LinkedList<String>();
		String choiceClass = "";
		int colNo=1;// current coulmn
		int noCol=1; //number of columns
		int colW=24;

		for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
			if(!start){
				if( event == XMLStreamConstants.START_ELEMENT){
					//if( parser.getLocalName().equals("body")){
					if( parser.getLocalName().equals(part)){
						start=true;
					}}
			}else{
				switch (event) {
					
					case XMLStreamConstants.START_ELEMENT:
						localname = parser.getLocalName();
						if( !localname.equals("note")
												&& !flagNote
												){
											leftNote=false;
											
											}
						switch (localname) {
							case "choice":
								flagChoice=true;
								choiceSic="";
								choiceCorr="";
								break;
							case "sic":
							case "abbr":
								flagSic=true;
								break;
							case "corr":
							case "expan":
								flagCorr=true;
								break;

							case "hi":
								String hi = parser.getAttributeValue(null, "rend");
								String tagHi;
								switch(hi){
									case "I": tagHi="<span class=\"italic\" >";break;
									case "G": tagHi="<span class=\"bold\" >";break;
									case "S": tagHi="<span class=\"underline\" >";break;
									case "E": tagHi="<span class=\"superscript\" >";break;
									case "i": tagHi="<span class=\"subscript\" >";break;
									case "IG": tagHi="<span class=\"italic bold\" >";break;
									case "GE": tagHi="<span class=\"superscript bold\" >";break;
									case "ST": tagHi="<span class=\"strikeThrought\" >";break;
									case "pI": tagHi="<span class=\"smallItalic\" >";break;

								}
								if(flagNote){
									noteContent.append(tagHi);

								}else{
									sb.append(tagHi);
									currentTagStack.addFirst(tagHi);
									currentTagToCloseStack.addFirst("</span>");
								}
								break;
							case "bibl":
							 StringBuilder tmpSpan=new StringBuilder();
							 tmpSpan.append("<span class=\"bibl\" ");
							 if (parser.getAttributeValue(null, "source") != null) {
								 tmpSpan.append("onclick=\"openBiblVers(").append(parser.getAttributeValue(null, "source")).append("\")");
							 }
							 
							tmpSpan.append(" >");
								if(flagNote){
//									noteContent.append("<span class=\"bibl\" ");
//									if (parser.getAttributeValue(null, "source") != null) {
//										noteContent.append("onclick=\"openNav(parser.getAttributeValue(null, \"source\"))\"");
//									}
//									
//									noteContent.append(" >");
									noteContent.append(tmpSpan.toString())
								}else{
								sb.append(tmpSpan.toString());
								currentTagStack.addFirst(tmpSpan.toString());
								currentTagToCloseStack.addFirst("</span>");
//									 
//									sb.append("<span class=\"bibl\" >");
//									currentTagStack.addFirst("<span class=\"bibl\" >");
//									currentTagToCloseStack.addFirst("</span>");
								}
								break;

							case "quote":
							StringBuilder tmpSpanQ=new StringBuilder();
							tmpSpanQ.append("<span class=\"quote\" ");
							if (parser.getAttributeValue(null, "source") != null) {
								tmpSpanQ.append("onclick=\"openBiblVers(").append(parser.getAttributeValue(null, "source")).append("\")");
							}
							
						   tmpSpanQ.append(" >");
								if(flagNote){
									//noteContent.append("<span class=\"quote\" >");
									noteContent.append(tmpSpanQ.toString())
								}else{
								sb.append(tmpSpanQ.toString());
								currentTagStack.addFirst(tmpSpanQ.toString());
								currentTagToCloseStack.addFirst("</span>");
//									sb.append("<span class=\"quote\" >");
//									currentTagStack.addFirst("<span class=\"quote\" >");
//									currentTagToCloseStack.addFirst("</span>");
								}
								break;


							case "note":
								flagNote = true;
								String t;
								noteContent.setLength(0);
								if (parser.getAttributeValue(null, "type") != null) {
								 t = parser.getAttributeValue(null, "type");
								}else{
									t="other"
								}
								if(t.equals("margin")){
									noteType=1;
								}else if(t.equals("foot")){
									noteType=2;
									if (parser.getAttributeValue(null, "n") != null) {
										noteContent.append(parser.getAttributeValue(null, "n"));
									}
								}else{
									noteType=3;
								}
								

								break;

							case "head":

								headType = parser.getAttributeValue(null, "type")
								if(headType.equals("main")){
									sb.append("<h1>");
									currentTagStack.addFirst("<h1>");
									currentTagToCloseStack.addFirst("</h1>");
								}else if(headType.equals("sub")){
									sb.append("<h2>");
									currentTagStack.addFirst("<h2>");
									currentTagToCloseStack.addFirst("</h2>");
								}
								break;

							case "graphic":
								sb.append("<br/>")
								sb.append("<img src:url/>")
								break;

							case "p":

								String n = parser.getAttributeValue(null, "n")
								sb.append("<div class=\"par\" id=\""+ n+"\">");
//								sb.append("<span class=\"par\">[§"+n+"]</span>");
								currentTagStack.addFirst("<div class=\"par\" id=\""+ n+"\">");
								currentTagToCloseStack.addFirst("</div>");

								break;

							case "q":
								if(flagNote){
									noteContent.append("<span class=\"q\" style=\"color:gray\" >");

								}else{
									sb.append("<span class=\"q\" style=\"color:gray\" >");
									currentTagStack.addFirst("<span class=\"q\" style=\"color:gray\" >");
									currentTagToCloseStack.addFirst("</span>");
								}
								break;

							case "pb":

							//close the page div if it is not the first one
								if(pageId!=null ){	//sb.append("<hr/>")
									Iterator<String> closeTag = currentTagToCloseStack.descendingIterator();
									while(closeTag.hasNext()){
										sb.append(closeTag.next());
									}

									if(noCol>1){
										sb.append("</div>");// close last colon
										sb.append("</div>"); // close milestone div
									}
									sb.append("</div>"); //close page
								}
								if (parser.getAttributeValue(null,"n") != null) {
									String pn = parser.getAttributeValue(null, "n")

									String pageTag;
									pageId=doc_id+"_"+pn;
									if(firstPage){
										//pageTag="<div id =\""+pageId+"\" class=\"pageBreak column span-12\" >";
										if(part.equals("front")){
											pageTag="<div id =\""+pageId+"\" class=\"pageBreak titre \" >";
										}else{
											pageTag="<div id =\""+pageId+"\" class=\"pageBreak \" >";
										}
										firstPage=false;
									}else{
										//	pageTag="<div id =\""+pageId+"\" class=\"pageBreak column span-12\" style=\"display:none\" >";
										pageTag="<div id =\""+pageId+"\" class=\"pageBreak \" style=\"display:none\" >";
									}
									pages.add(pn);
									sb.append(pageTag);
									if(noCol == 1){
										//Iterator<String> openTag = currentTagStack.iterator();
										Iterator<String> openTag = currentTagStack.descendingIterator();
										while(openTag.hasNext()){
											sb.append(openTag.next());
										}

									}else{
										sb.append("<div class=\"block row\">" ); //starting column block
										colNo=1;
									}
								}


								break;

							case "lb":
							case "br":
								if(flagNote){
									noteContent.append("<br/>");
									//	 noteCurrentTagStack.addFirst(tagHi);
									//	 noteCurrentTagToCloseStack.addFirst("</span>");
								}else{
									sb.append("<br/>")
									leftNote=true;
									
								}
								
								break;

							case "fw":
								sb.append("<br/><span class=\"catchWord\" >[");
								break;

							case "c":
							if (parser.getAttributeValue(null,"type").equals("lettrine")) {
								sb.append("<span class=\"lettrine\" >");
								}
							
								break;

							case "milestone":

								int newM = parser.getAttributeValue(null, "n").toInteger();
								if(newM == 1){
									sb.append("</div>");
									sb.append("</div>");
									noCol=newM;
								}else{
									if(noCol>1){
										sb.append("</div>");
										sb.append("</div>");
										
									}
									noCol=newM;
									colNo=1;// current coulmn
									colW=24/noCol;
									sb.append("<div class=\"block row\">" ); //starting column block
								}
								break;

							case "cb":
							if(colNo != 1){
								Iterator<String> closeTag = currentTagToCloseStack.descendingIterator();
								while(closeTag.hasNext()){
									sb.append(closeTag.next());
								}
								
									sb.append("</div>"); //end of previous column
								}
								String colDiv;
								if(noCol == colNo){
									colDiv="<div class=\"column moreThan1 span-"+colW+" last\">";

								}
								else{
									colDiv="<div class=\"column moreThan1 span-"+colW+"\">";
								}
								sb.append(colDiv);
								
								//Iterator<String> openTag = currentTagStack.iterator();
								Iterator<String> openTag = currentTagStack.descendingIterator();
								while(openTag.hasNext()){
									sb.append(openTag.next());
								}

								colNo++;
						}
						break;

					case XMLStreamConstants.END_ELEMENT:
						localname = parser.getLocalName();
						switch (localname) {
							//case "body":
							case part:
								start=false;

								break;
							case "choice":

								flagChoice=false;
								if(flagNote){
									noteContent.append("<br/>");
									noteContent.append("<span class=\"");
									noteContent.append(choiceClass);

									noteContent.append("\"  title=\"").append(choiceCorr).append("\">");
									noteContent.append(choiceSic).append("</span>");
								}else{
									sb.append("<span class=\"");
									sb.append(choiceClass);

									sb.append("\"  title=\"").append(choiceCorr).append("\">");
									sb.append(choiceSic).append("</span>");
								}
							//choiceClass.length = 0;
								break;
							case "sic":
								choiceClass="sic";
								flagSic=false;
								break;
							case "abbr":
								choiceClass="abbr";
								flagSic=false;
								break;
							case "corr":
							case "expan":
								flagCorr=false;
								break;
							case "hi":
								if(flagNote){
									noteContent.append("</span>");
								}else{
									sb.append(currentTagToCloseStack.removeFirst());
									currentTagStack.removeFirst();
								}
								break;
							case "head":
							//								if(headType.equals("main")){
							//									sb.append("</h1>")
							//								}else if(headType.equals("sub")){
							//									sb.append("</h2>")
							//								}
								sb.append(currentTagToCloseStack.removeFirst());
								currentTagStack.removeFirst();
								break;

							case "note":
								flagNote = false;

								if (noteContent.length() > 0) {
									if(noteType==1){
										if(leftNote){
											sb.append("<span class=\"note margin-left\" '>");
										}else{
											sb.append("<span class=\"note margin-right\" '>");
										}
										//sb.append("<span class=\"note margin\"  title='").append(noteContent.toString()).append("'>");
										//sb.append("[*]").append("</span>");
										sb.append(noteContent.toString()).append("</span>");
									}else if(noteType==2){
										sb.append("<div class=\"note foot\" >" ).append(noteContent.toString()).append("</div>");
									}else{
									sb.append("<div class=\"note otherNote\" >" ).append(noteContent.toString()).append("</div>");
									}

								}
								noteType=0;
								break;

							case "lg":
							case "p":
								sb.append(currentTagToCloseStack.removeFirst());
								currentTagStack.removeFirst();
								//sb.append("</div>").append("<br/>");
								sb.append("<br/>");
								break;

							case "q":
								if(flagNote){
									noteContent.append("</span>");
								}else{
									sb.append(currentTagToCloseStack.removeFirst());
									currentTagStack.removeFirst();
								}
								break;

							case "fw":
								sb.append("]</span>");
								break;
								
							case "c":
								sb.append("</span>");
								break;
								
							case "bibl":
							case "quote":
								if(flagNote){
									noteContent.append("</span>");
								}else{
									sb.append(currentTagToCloseStack.removeFirst());
									currentTagStack.removeFirst();
								}
								break;

						}
						break;
					case XMLStreamConstants.CHARACTERS:
					if( !flagNote && parser.getText().replaceAll("\\s+", "").length() > 0){
						leftNote=false;
						
					}
						if (flagNote && parser.getText().length() > 0) {
							noteContent.append(parser.getText().replace("'", "&apos;"));
						}else if(flagChoice && flagSic && parser.getText().length() > 0){
							choiceSic += parser.getText().replaceAll("\\s+", " ");
						}else if(flagChoice && flagCorr && parser.getText().length() > 0){
							choiceCorr += parser.getText().replaceAll("\\s+", " ");
						}else{
							sb.append(parser.getText().replace("\n", " ").replaceAll("\\s+", " "));
						}
						break;
				}
			}
		}
		
		if(noCol>1){
			sb.append("</div>");// close last colon
			sb.append("</div>"); // close milestone div
		}
		
		sb.append("</div>");//close last page
		
        
		 return new SelectedPageSummary(
			page : sb.toString(),
			pages: pages,
			)
		}
		//return sb.toString();
}



