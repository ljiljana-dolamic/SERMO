package ch.unine.ILCF.SERMO

import java.io.File;
import javax.xml.parsers.*
import javax.xml.stream.*;
import java.lang.StringBuilder;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Iterator;


class DocumentInfoTagLib {
	static defaultEncodeAs = 'raw'
	//static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
	static namespace = "document"
	static final DEFAULT_DOC_NAME ="_1555_Jean_Calvin_RSL"


	/** The url. */
	private def url;

	/** The input data. */
	private def inputData;

	/** The factory. */
	private def factory;

	/** The parser. */
	private XMLStreamReader parser;
	//
	//		/** The infile. */
	//		File infile;
	//
	private int noPages;

	def pages;

	//	private HashMap<String,String> pagesDiv;

	def filedir;
	def pdfDir ;

	def documentInfo = { attrs, body->
		def id = attrs.remove('docId')?.toString()
		def docName = id +".xml"
		filedir = params.filedir?: grailsApplication.config.sermoXMLDefault.filedir;
		//pdfDir =  params.filedir?: grailsApplication.config.sermoPDFpagesDefault.filedir;

		File xmlFile = new File(filedir,docName);

		def url = xmlFile.toURI().toURL();
		//infile = infile;

		inputData = url.openStream();
		factory = XMLInputFactory.newInstance();
		parser = factory.createXMLStreamReader(inputData);

		//		pages = new LinkedList<String>();
		//
		//		pagesDiv = getPagesDivs();

		//out<< "<div class=\"block\">"


		//out << process2(id)
		//out << process2(id)
		//out << render(template:"/taglibTemplates/menu_pageSelection", model:[pages:pages,docId:id]);
		def page = process2(id);
		out << "<div class=\"block row\">"
		out << render(template:"/taglibTemplates/pageContent", model:[page:page]);
		out << render(template:"/taglibTemplates/pageImage", model:[docId:id,pages:pages]);
		out << "</div>"

		out << render(template:"/taglibTemplates/pageSelection", model:[pages:pages,docId:id]);
		//out << render(template:"/taglibTemplates/menu_pageSelection", model:[pages:pages,docId:id]);
	}
	/**
	 * Process paginates. 
	 */

	String process(String doc_id){
		boolean flagNote = false;
		int noteType= 0; //- margin;2-foot
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
					if( parser.getLocalName().equals("body")){
						start=true;
					}}
			}else{

				switch (event) {
					case XMLStreamConstants.START_ELEMENT:
						localname = parser.getLocalName();
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
									case "G": tagHi="<span class=\"italic\" >";break;
									case "S": tagHi="<span class=\"italic\" >";break;
									case "E": tagHi="<span class=\"superscript\" >";break;
									case "i": tagHi="<span class=\"italic\" >";break;
									case "IG": tagHi="<span class=\"italic\" >";break;
									case "GE": tagHi="<span class=\"italic\" >";break;
									case "ST": tagHi="<span class=\"italic\" >";break;

								}
								if(flagNote){
									noteContent.append(tagHi);
									//	 noteCurrentTagStack.addFirst(tagHi);
									//	 noteCurrentTagToCloseStack.addFirst("</span>");
								}else{
									sb.append(tagHi);
									currentTagStack.addFirst(tagHi);
									currentTagToCloseStack.addFirst("</span>");
								}
								break;
							case "bibl":
								if(flagNote){
									noteContent.append("<span class=\"bibl\" >");
									//	 noteCurrentTagStack.addFirst(tagHi);
									//	 noteCurrentTagToCloseStack.addFirst("</span>");
								}else{
									sb.append("<span class=\"bibl\" >");
									currentTagStack.addFirst("<span class=\"bibl\" >");
									currentTagToCloseStack.addFirst("</span>");
								}
								break;

							case "quote":
								if(flagNote){
									noteContent.append("<span class=\"quote\" >");
									//	 noteCurrentTagStack.addFirst(tagHi);
									//	 noteCurrentTagToCloseStack.addFirst("</span>");
								}else{
									sb.append("<span class=\"quote\" >");
									currentTagStack.addFirst("<span class=\"quote\" >");
									currentTagToCloseStack.addFirst("</span>");
								}
								break;


							case "note":
								flagNote = true;
								String t = parser.getAttributeValue(null, "type")
								if(t.equals("margin")){
									noteType=1;
								}else{
									noteType=2;
								}
								noteContent.setLength(0);
							//sb.append("<span class=\"note\" style=\"color:blue\" >");
								break;

							case "head":
							//sb.append("<br/>")
							//sb.append("<h2>")
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
								sb.append("<div class=\"p\" id=\""+ n+"\">");
								sb.append("<span class=\"par\">[§"+n+"]</span>");
								currentTagStack.addFirst("<div class=\"p\" id=\""+ n+"\">");
							//currentTagStack.addFirst("<span class=\"par\">[§"+n+"]</span>");
								currentTagToCloseStack.addFirst("</div>");
							//	currentTagToCloseStack.addFirst("</span>");
								break;

							case "q":
								if(flagNote){
									noteContent.append("<div class=\"q\" style=\"color:gray\" >");
									//	 noteCurrentTagStack.addFirst(tagHi);
									//	 noteCurrentTagToCloseStack.addFirst("</span>");
								}else{
									sb.append("<div class=\"q\" style=\"color:gray\" >");
									currentTagStack.addFirst("<div class=\"q\" style=\"color:gray\" >");
									currentTagToCloseStack.addFirst("</div>");
								}
								break;

							case "pb":

								if(pageId!=null){
									Iterator<String> closeTag = currentTagToCloseStack.descendingIterator();
									while(closeTag.hasNext()){
										sb.append(closeTag.next());
									}
									sb.append("</div>"); // close page

								}
								if (parser.getAttributeValue(null,"n") != null) {
									String pn = parser.getAttributeValue(null, "n")

									String pageTag;
									pageId=doc_id+"_"+pn;
									if(firstPage){
										pageTag="<div id =\""+pageId+"\" class=\"pageBreak \" >";
										firstPage=false;
									}else{
										pageTag="<div id =\""+pageId+"\" class=\"pageBreak \" style=\"display:none\" >";
									}
									pages.add(pn);
									sb.append(pageTag);
									Iterator<String> openTag = currentTagStack.iterator();
									while(openTag.hasNext()){
										sb.append(openTag.next());
									}


								}

								break;

							case "lb":
							case "br":
								if(flagNote){
									noteContent.append("<br/>");
								}else{
									sb.append("<br/>")
								}
								break;

							case "fw":
								sb.append("<br/><span class=\"catchWord\" >[");
								break;

						}
						break;

					case XMLStreamConstants.END_ELEMENT:
						localname = parser.getLocalName();
						switch (localname) {
							case "body":
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
								sb.append(currentTagToCloseStack.removeFirst());
								currentTagStack.removeFirst();
								break;

							case "note":
								flagNote = false;

								if (noteContent.length() > 0) {
									if(noteType==1){
										sb.append("<span class=\"note margin\"  title='").append(noteContent.toString()).append("'>");
										sb.append("[*]").append("</span>");
									}else if(noteType==2){
										sb.append("<div class=\"note foot\" >" ).append(noteContent.toString()).append("</div>");
									}

								}
								noteType=0;
								break;

							case "lg":
							case "p":
								sb.append(currentTagToCloseStack.removeFirst());
								currentTagStack.removeFirst();
								sb.append("<br/>");
								break;

							case "q":
								if(flagNote){
									noteContent.append("</div>");
								}else{
									sb.append(currentTagToCloseStack.removeFirst());
									currentTagStack.removeFirst();
								}
								break;

							case "fw":
								sb.append("]</span>");
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
						if (flagNote && parser.getText().length() > 0) {
							noteContent.append(parser.getText().replace("'", "&apos;"));
						}else if(flagChoice && flagSic && parser.getText().length() > 0){
							choiceSic += parser.getText();
						}else if(flagChoice && flagCorr && parser.getText().length() > 0){
							choiceCorr += parser.getText();
						}else{
							sb.append(parser.getText().replace("\n", " "));
						}
						break;
				}
			}
		}
		sb.append("</div>");

		return sb.toString();
	}

	String process2(String doc_id){
		boolean flagNote = false;
		int noteType= 0; //1- margin;2-foot
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
					if( parser.getLocalName().equals("body")){
						start=true;
					}}
			}else{

				switch (event) {
					case XMLStreamConstants.START_ELEMENT:
						localname = parser.getLocalName();
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
								if(flagNote){
									noteContent.append("<span class=\"bibl\" >");

								}else{
									sb.append("<span class=\"bibl\" >");
									currentTagStack.addFirst("<span class=\"bibl\" >");
									currentTagToCloseStack.addFirst("</span>");
								}
								break;

							case "quote":
								if(flagNote){
									noteContent.append("<span class=\"quote\" >");

								}else{
									sb.append("<span class=\"quote\" >");
									currentTagStack.addFirst("<span class=\"quote\" >");
									currentTagToCloseStack.addFirst("</span>");
								}
								break;


							case "note":
								flagNote = true;
								String t;
								if (parser.getAttributeValue(null, "type") != null) {
								 t = parser.getAttributeValue(null, "type");
								}else{
									t="other"
								}
								if(t.equals("margin")){
									noteType=1;
								}else if(t.equals("foot")){
									noteType=2;
								}else{
									noteType=3;
								}
								noteContent.setLength(0);

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
								sb.append("<div class=\"p\" id=\""+ n+"\">");
								sb.append("<span class=\"par\">[§"+n+"]</span>");
								currentTagStack.addFirst("<div class=\"p\" id=\""+ n+"\">");
								currentTagToCloseStack.addFirst("</div>");

								break;

							case "q":
								if(flagNote){
									noteContent.append("<div class=\"q\" style=\"color:gray\" >");

								}else{
									sb.append("<div class=\"q\" style=\"color:gray\" >");
									currentTagStack.addFirst("<div class=\"q\" style=\"color:gray\" >");
									currentTagToCloseStack.addFirst("</div>");
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
										pageTag="<div id =\""+pageId+"\" class=\"pageBreak \" >";
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
								}
								break;

							case "fw":
								sb.append("<br/><span class=\"catchWord\" >[");
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
							case "body":
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
										sb.append("<span class=\"note margin\"  title='").append(noteContent.toString()).append("'>");
										sb.append("[*]").append("</span>");
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
							//	sb.append("</div>").append("<br/>");
								sb.append("<br/>");
								break;

							case "q":
								if(flagNote){
									noteContent.append("</div>");
								}else{
									sb.append(currentTagToCloseStack.removeFirst());
									currentTagStack.removeFirst();
								}
								break;

							case "fw":
								sb.append("]</span>");
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
						if (flagNote && parser.getText().length() > 0) {
							noteContent.append(parser.getText().replace("'", "&apos;"));
						}else if(flagChoice && flagSic && parser.getText().length() > 0){
							choiceSic += parser.getText();
						}else if(flagChoice && flagCorr && parser.getText().length() > 0){
							choiceCorr += parser.getText();
						}else{
							sb.append(parser.getText().replace("\n", " "));
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
		

		return sb.toString();
	}





}
