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

	SelectedPageSummary processSearch(String fileDir,String doc_id, String part, String pb, String search){
		//System.out.print("Searching term: "+ search);
		return process2(fileDir,doc_id, part, pb, search);
	}

	SelectedPageSummary process(String fileDir,String doc_id, String part, String pageToShow){
		def docName = doc_id +".xml"
		filedir =  fileDir;


		File xmlFile = new File(filedir,docName);

		def url = xmlFile.toURI().toURL();

		def nXmlTei = new XmlSlurper(false,false).parseText(new File(filedir,docName).getText());
		def  allPages = nXmlTei.text.'**'.findAll{node-> node.name() == 'pb'}*.@'n';

		List<String> allPagesString = new LinkedList<String>();
		allPages.each{ allPagesString.add(it.toString()) }

		//allPagesString.each{println it.getClass();
		//	println it
		//}

		boolean firstPage=true;

		if(allPagesString.contains(pageToShow.toString())){
			firstPage = false;
			//System.out.println(allPages.toString()+" contains "+pageToShow);
		}
		//boolean firstPage=true;
		//if(pb=)
		//System.out.println(pageToShow);
		// System.out.println(allPages.toListString());

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

		String pageId;
		pages = new LinkedList<String>();
		String choiceClass = "";
		int colNo=1;// current coulmn
		int noCol=1; //number of columns
		int colW=24;
		String noteID="";
        
		boolean endLetterine=false;   


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
						
						if(endLetterine && sb.charAt(sb.length - 1).equals(' ')){
							sb.deleteCharAt(sb.length - 1);
							endLetterine = false;
						}
						switch (localname) {
							case "choice":
							case "subst":
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
							case "del":
								if(flagChoice){
									flagSic=true;

								}else{
									if(flagNote){
										noteContent.append("<span class=\"span-del\">");

									}else{
										sb.append("<span class=\"span-del\">");
										currentTagStack.addFirst("<span class=\"span-del\">");
										currentTagToCloseStack.addFirst("</span>");
									}
								};
								break;
							case "add":
								if(flagChoice){
									flagCorr=true;

								}else{
									if(flagNote){
										noteContent.append("<span class=\"span-add\">");

									}else{
										sb.append("<span class=\"span-add\">");
										currentTagStack.addFirst("<span class=\"span-add\">");
										currentTagToCloseStack.addFirst("</span>");
									}
								};
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

							case "ref":
								StringBuilder tmpSpan=new StringBuilder();
								tmpSpan.append("<a ");
								if (parser.getAttributeValue(null, "type") != null && parser.getAttributeValue(null, "type").equals("bible")) {
									//tmpSpan.append("onclick=\"openBiblVers(").append(parser.getAttributeValue(null, "type")).append(",").append(parser.getAttributeValue(null, "source")).append(")\"");
									tmpSpan.append("class=\"bibl\" href=\"#\"");
									if ( parser.getAttributeValue(null, "target") != null){
										tmpSpan.append(" rev=\"").append(parser.getAttributeValue(null, "target")).append("\"");
									}
								}else if(parser.getAttributeValue(null, "type") != null && parser.getAttributeValue(null, "type").equals("other")){
									tmpSpan.append("class=\"other\" href=\"#\"");
								}else if(parser.getAttributeValue(null, "type") != null && parser.getAttributeValue(null, "type").equals("noteAnchor")){
									tmpSpan.append("class=\"noteAnchor\" href=\"").append(parser.getAttributeValue(null, "target")).append("\"");
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
									currentTagToCloseStack.addFirst("</a>");
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
									//tmpSpanQ.append("onclick=\"openBiblVers(").append(parser.getAttributeValue(null, "type")).append(",").append(parser.getAttributeValue(null, "source")).append(")\"");
									tmpSpanQ.append("type=\"").append(parser.getAttributeValue(null, "type")).append("\" source=\"").append(parser.getAttributeValue(null, "source")).append("\"");
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
								if (parser.getAttributeValue(null, "xml:id") != null) {
									noteID = parser.getAttributeValue(null, "xml:id");
								}else{
									noteID="";
								}
								if(t.equals("margin")){
									noteType=1;
									if (parser.getAttributeValue(null, "n") != null) {
										noteContent.append(parser.getAttributeValue(null, "n")).append(" ");
									}
								}else if(t.equals("foot")){
									noteType=2;
									if (parser.getAttributeValue(null, "n") != null) {
										noteContent.append(parser.getAttributeValue(null, "n")).append(" ");
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
								}else if (headType.equals("headnote")){
									sb.append("<h4 class=\"par\">");
									currentTagStack.addFirst("<h4 class=\"par\">");
									currentTagToCloseStack.addFirst("</h4>");
								}else{
									sb.append("<h3 class=\"par\">");
									currentTagStack.addFirst("<h3 class=\"par\">");
									currentTagToCloseStack.addFirst("</h3>");
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
									if(firstPage || pageToShow.equals(pn)){
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
							case "subst":
								flagChoice=false;
								if(flagNote){
									noteContent.append("<br/>");
									noteContent.append("<span class=\"");
									noteContent.append(choiceClass);

									noteContent.append("\"  title=\"").append(choiceCorr).append("\">");
									noteContent.append(choiceSic).append("</span>");
									//if(choiceClass.equals("del")){
									//	noteContent.append(choiceCorr);
									//}
								}else{
									sb.append("<span class=\"");
									sb.append(choiceClass);

									sb.append("\"  title=\"").append(choiceCorr).append("\">");
									sb.append(choiceSic).append("</span>");

									if(choiceClass.equals("del")){
										sb.append(" "+choiceCorr);
									}
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
							case "del":
								if(flagSic){
									choiceClass="del";
									flagSic=false;

								}else{
									if(flagNote){
										noteContent.append("</span>");
									}else{
										sb.append(currentTagToCloseStack.removeFirst());
										currentTagStack.removeFirst();
									}
								}
								break;
							case "add":
								if(flagCorr){
									flagCorr=false;

								}else{
									if(flagNote){
										noteContent.append("</span>");
									}else{
										sb.append(currentTagToCloseStack.removeFirst());
										currentTagStack.removeFirst();
									}
								}
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
											sb.append("<span class=\"note margin-left\"  ");

											if(!noteID.equals("")){
												sb.append("id=\"").append(noteID).append("\" ");
											}

											sb.append("'>");
										}else{

											sb.append("<span class=\"note margin-right\"  ");

											if(!noteID.equals("")){
												sb.append("id=\"").append(noteID).append("\" ");
											}

											sb.append("'>");
										}
										//sb.append("<span class=\"note margin\"  title='").append(noteContent.toString()).append("'>");
										//sb.append("[*]").append("</span>");
										sb.append(noteContent.toString()).append("</span>");
									}else if(noteType==2){

										sb.append("<div class=\"note foot\" " );
										if(!noteID.equals("")){
											sb.append("id=\"").append(noteID).append("\" ");
										}
										sb.append(" >" );
										sb.append(noteContent.toString()).append("</div>");
									}else{

										sb.append("<div class=\"note otherNote\" " );
										if(!noteID.equals("")){
											sb.append("id=\"").append(noteID).append("\" ");
										}
										sb.append(" >" );
										sb.append(noteContent.toString()).append("</div>");
									}

								}
								noteType=0;
								break;

							case "lg":
							case "p":
								sb.append(currentTagToCloseStack.removeFirst());
								currentTagStack.removeFirst();
							//sb.append("</div>").append("<br/>");
								//sb.append("<br/>");
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
								endLetterine=true;
								break;


							case "ref":
								if(flagNote){
									noteContent.append("</a>");
								}else{
									sb.append(currentTagToCloseStack.removeFirst());
									currentTagStack.removeFirst();
								}
								break;
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
					if(endLetterine && sb.charAt(sb.length - 1).equals(' ')){
						sb.deleteCharAt(sb.length - 1);
						endLetterine = false;
					}
						if( !flagNote && parser.getText().replaceAll("\\s+", "").length() > 0){
							leftNote=false;

						}
						if(flagChoice && flagSic && parser.getText().length() > 0){
							choiceSic += parser.getText().replaceAll("\\s+", " ");
						}else if(flagChoice && flagCorr && parser.getText().length() > 0){
							choiceCorr += parser.getText().replaceAll("\\s+", " ");
						}else if (flagNote && parser.getText().length() > 0) {
							noteContent.append(parser.getText().replace("'", "&apos;"));
						}else{
						    
							sb.append(parser.getText().replace("\n", " ").replaceAll("\\s+", " ").trim());
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

	SelectedPageSummary process2(String fileDir,String doc_id, String part,String pb, String query){
		def docName = doc_id +".xml"
		filedir =  fileDir;

	//	System.out.println(fileDir+"; "+ docName+"; "+part+"; "+pb+"; "+query);
		File xmlFile = new File(filedir,docName);

		def url = xmlFile.toURI().toURL();

		def nXmlTei = new XmlSlurper(false,false).parseText(new File(filedir,docName).getText());
		def  allPages = nXmlTei.text.'**'.findAll{node-> node.name() == 'pb'}*.@'n';

		List<String> allPagesString = new LinkedList<String>();
		allPages.each{ allPagesString.add(it.toString()) }


		boolean firstPage=true;
		String pageToShow =''; // if we are searching the free text go to the page of first hit

		if(allPagesString.contains(pb.toString())){
			firstPage = false;
			pageToShow = pb;
		}

		inputData = url.openStream();
		factory = XMLInputFactory.newInstance();
		parser = factory.createXMLStreamReader(inputData);

		boolean flagNote = false;
		int noteType= 0; //1- margin;2-foot
		boolean leftNote=true;
		boolean flagChoice = false;
		boolean flagSic = false;
		boolean flagCorr = false;
		boolean flagAbbr = false;
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

		String pageId;
		pages = new LinkedList<String>();
		String choiceClass = "";
		int colNo=1;// current coulmn
		int noCol=1; //number of columns
		int colW=24;
		String noteID="";

		boolean ignore = false;
		// enable searching

		StringBuilder remQuery = new StringBuilder();
		remQuery.append(query.replaceAll("\\s+", ""));
		StringBuilder matchContent = new StringBuilder();
		StringBuilder noMatchContent = new StringBuilder();
		boolean inMatch = false;
         boolean closeSicHit =false;
		
		String currentPage;
		
		for (int event = parser.next(); event != XMLStreamConstants.END_DOCUMENT; event = parser.next()) {
			
			if(!start){
				
				if( event == XMLStreamConstants.START_ELEMENT){
					
					if( parser.getLocalName().equals(part)){
						start=true;
						
					}
				}
			}else{
				
				switch (event) {

					case XMLStreamConstants.START_ELEMENT:
						localname = parser.getLocalName();
						if( !localname.equals("note")
						&& !flagNote
						){
							leftNote=false;

						}
						///System.out.println("Event name start: "+localname);
						switch (localname) {
							case "choice":
							case "subst":
								flagChoice=true;
								choiceSic="";
								choiceCorr="";
								break;
							case "sic":
							case "abbr":
								flagSic=true;
								flagAbbr=true;
								break;
							case "corr":
							case "expan":
								flagCorr=true;
								break;
							case "del":
								if(flagChoice){
									flagSic=true;

								}else{
									if(flagNote){
										noteContent.append("<span class=\"span-del\">");

									}else{
										if(inMatch){
											matchContent.append("<span class=\"span-del\">");
											noMatchContent.append("<span class=\"span-del\">");
										}else{
											sb.append("<span class=\"span-del\">");
										}
										currentTagStack.addFirst("<span class=\"span-del\">");
										currentTagToCloseStack.addFirst("</span>");
									}
								};
								break;
							case "add":
								if(flagChoice){
									flagCorr=true;

								}else{
									if(flagNote){
										noteContent.append("<span class=\"span-add\">");

									}else{
										if(inMatch){
											matchContent.append("<span class=\"span-add\">");
											noMatchContent.append("<span class=\"span-add\">");
										}else{
											sb.append("<span class=\"span-add\">");
										}

										currentTagStack.addFirst("<span class=\"span-add\">");
										currentTagToCloseStack.addFirst("</span>");
									}
								};
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
									if(inMatch){
										matchContent.append(tagHi);
										noMatchContent.append(tagHi);
									}else{
										sb.append(tagHi);
									}
									
									currentTagStack.addFirst(tagHi);
									currentTagToCloseStack.addFirst("</span>");
								}
								break;
							case "ref":
								StringBuilder tmpSpan=new StringBuilder();
								tmpSpan.append("<a ");
								if (parser.getAttributeValue(null, "type") != null && parser.getAttributeValue(null, "type").equals("bible")  && parser.getAttributeValue(null, "target") != null) {
									tmpSpan.append("class=\"bibl\" href=\"#\"");
									tmpSpan.append("\" rev=\"").append(parser.getAttributeValue(null, "target")).append("\"");
								}else if(parser.getAttributeValue(null, "type") != null && parser.getAttributeValue(null, "type").equals("other")){
									tmpSpan.append("class=\"other\" href=\"#\"");
								}else if(parser.getAttributeValue(null, "type") != null && parser.getAttributeValue(null, "type").equals("noteAnchor")){
									tmpSpan.append("class=\"other\" href=\"").append(parser.getAttributeValue(null, "target")).append("\"");
								}

								tmpSpan.append(" >");
								if(flagNote){
									noteContent.append(tmpSpan.toString())
								}else{
									if(inMatch){
										matchContent.append(tmpSpan.toString());
										noMatchContent.append(tmpSpan.toString());
									}else{
										sb.append(tmpSpan.toString());
									}
									currentTagStack.addFirst(tmpSpan.toString());
									currentTagToCloseStack.addFirst("</a>");
									
								}
								break;

							case "quote":
								StringBuilder tmpSpanQ=new StringBuilder();
								tmpSpanQ.append("<span class=\"quote\" ");
								if (parser.getAttributeValue(null, "type") != null && parser.getAttributeValue(null, "source") != null) {
									tmpSpanQ.append("type=\"").append(parser.getAttributeValue(null, "type")).append("\" source=\"").append(parser.getAttributeValue(null, "source")).append("\"");
								}

								tmpSpanQ.append(" >");
								if(flagNote){
									noteContent.append(tmpSpanQ.toString())
								}else{
									if(inMatch){
										matchContent.append(tmpSpanQ.toString());
										noMatchContent.append(tmpSpanQ.toString());
									}else{
										sb.append(tmpSpanQ.toString());
									}
									currentTagStack.addFirst(tmpSpanQ.toString());
									currentTagToCloseStack.addFirst("</span>");
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
								if (parser.getAttributeValue(null, "xml:id") != null) {
									noteID = parser.getAttributeValue(null, "xml:id");
								}else{
									noteID="";
								}
								if(t.equals("margin")){
									noteType=1;
									if (parser.getAttributeValue(null, "n") != null) {
										noteContent.append(parser.getAttributeValue(null, "n")).append(" ");
									}
								}else if(t.equals("foot")){
									noteType=2;
									if (parser.getAttributeValue(null, "n") != null) {
										noteContent.append(parser.getAttributeValue(null, "n")).append(" ");
									}
								}else{
									noteType=3;
								}

								break;

							case "head":

								headType = parser.getAttributeValue(null, "type")
								if(headType.equals("main")){
									if(inMatch){
										matchContent.append("<h1>");
										noMatchContent.append("<h1>");
									}else{
										sb.append("<h1>");
									}
									currentTagStack.addFirst("<h1>");
									currentTagToCloseStack.addFirst("</h1>");
								}else if(headType.equals("sub")){
									if(inMatch){
										matchContent.append("<h2>");
										noMatchContent.append("<h2>");
									}else{
										sb.append("<h2>");
									}

									currentTagStack.addFirst("<h2>");
									currentTagToCloseStack.addFirst("</h2>");
								}else if (headType.equals("headnote")){
									if(inMatch){
										matchContent.append("<h4 class=\"par\">");
										noMatchContent.append("<h4 class=\"par\">");
									}else{
										sb.append("<h4 class=\"par\">");
									}

									currentTagStack.addFirst("<h4 class=\"par\">");
									currentTagToCloseStack.addFirst("</h4>");
								}
								break;

							case "graphic":
								if(inMatch){
									matchContent.append("<br/>");
									matchContent.append("<img src:url/>");
									noMatchContent.append("<br/>");
									noMatchContent.append("<img src:url/>");
								}else{
									sb.append("<br/>");
									sb.append("<img src:url/>");
								}

								break;

							case "p":

								String n = parser.getAttributeValue(null, "n")
								if(inMatch){
									matchContent.append("<div class=\"par\" id=\""+ n+"\">");
									noMatchContent.append("<div class=\"par\" id=\""+ n+"\">");
								}else{
									sb.append("<div class=\"par\" id=\""+ n+"\">");
								}

							//								sb.append("<span class=\"par\">[§"+n+"]</span>");
								currentTagStack.addFirst("<div class=\"par\" id=\""+ n+"\">");
								currentTagToCloseStack.addFirst("</div>");

								break;

							case "q":
								if(flagNote){
									noteContent.append("<span class=\"q\" style=\"color:gray\" >");

								}else{
									if(inMatch){
										matchContent.append("<span class=\"q\" style=\"color:gray\" >");
										noMatchContent.append("<span class=\"q\" style=\"color:gray\" >");
									}else{
										sb.append("<span class=\"q\" style=\"color:gray\" >");
									}

									currentTagStack.addFirst("<span class=\"q\" style=\"color:gray\" >");
									currentTagToCloseStack.addFirst("</span>");
								}
								break;

							case "pb":
								//System.out.println("Page break")
							//close the page div if it is not the first one
								if(pageId!=null ){	//sb.append("<hr/>")
									Iterator<String> closeTag = currentTagToCloseStack.descendingIterator();
									while(closeTag.hasNext()){
										String tagToClosse =closeTag.next();
										if(inMatch){
											matchContent.append(tagToClosse);
											noMatchContent.append(tagToClosse);
										}else{
											sb.append(tagToClosse);
										}

									}

									if(noCol>1){
										if(inMatch){
											matchContent.append("</div>");
											matchContent.append("</div>");
											noMatchContent.append("</div>");
											noMatchContent.append("</div>");
										}else{
											sb.append("</div>");// close last colon
											sb.append("</div>"); // close milestone div
										}

									}
									if(inMatch){
										matchContent.append("</div>");
										noMatchContent.append("</div>");
									}else{
										sb.append("</div>"); //close page
									}

								}
								if (parser.getAttributeValue(null,"n") != null) {
									String pn = parser.getAttributeValue(null, "n")
									//System.out.println("Page break: " + pn)
									currentPage=pn;
									//System.out.println("currentPage break: " + currentPage)
									String pageTag;
									pageId=doc_id+"_"+pn;
									
									if(firstPage || pb.equals(pn)){
										if(part.equals("front")){
											pageTag="<div id =\""+pageId+"\" class=\"pageBreak titre \" >";
										}else{
											pageTag="<div id =\""+pageId+"\" class=\"pageBreak \" >";
										}
										firstPage=false;
									}else{
										pageTag="<div id =\""+pageId+"\" class=\"pageBreak \" style=\"display:none\" >";
									}
									pages.add(pn);
									if(inMatch){
										matchContent.append(pageTag);
										noMatchContent.append(pageTag);
									}else{
										sb.append(pageTag);
									}

									if(noCol == 1){
										Iterator<String> openTag = currentTagStack.descendingIterator();
										while(openTag.hasNext()){
											String tagToOpen = openTag.next();
											if(inMatch){
												matchContent.append(tagToOpen);
												noMatchContent.append(tagToOpen);
											}else{
												sb.append(tagToOpen);
											}

										}

									}else{
										if(inMatch){
											matchContent.append("<div class=\"block row\">");
											noMatchContent.append("<div class=\"block row\">");
										}else{
											sb.append("<div class=\"block row\">" );//starting column block
										}
										colNo=1;
									}
								}


								break;

							case "lb":
							case "br":
								if(flagNote){
									noteContent.append("<br/>");
								}else{
									if(inMatch){
										matchContent.append("<br/>");
										noMatchContent.append("<br/>");
									}else{
										sb.append("<br/>")
									}

									leftNote=true;

								}

								break;

							case "fw":
								ignore = true;
								if(inMatch){
									matchContent.append("<br/><span class=\"catchWord\" >[");
									noMatchContent.append("<br/><span class=\"catchWord\" >[");
								}else{
									sb.append("<br/><span class=\"catchWord\" >[");
								}

								break;

							case "c":
								if (parser.getAttributeValue(null,"type").equals("lettrine")) {
									if(inMatch){
										matchContent.append("<span class=\"lettrine\" >");
										noMatchContent.append("<span class=\"lettrine\" >");
									}else{
										sb.append("<span class=\"lettrine\" >");
									}

								}

								break;

							case "milestone":

								int newM = parser.getAttributeValue(null, "n").toInteger();
								if(newM == 1){
									if(inMatch){
										matchContent.append("</div>");
										matchContent.append("</div>");
										noMatchContent.append("</div>");
										noMatchContent.append("</div>");
									}else{
										sb.append("</div>");
										sb.append("</div>");
									}

									noCol=newM;
								}else{
									if(noCol>1){
										if(inMatch){
											matchContent.append("</div>");
											matchContent.append("</div>");
											noMatchContent.append("</div>");
											noMatchContent.append("</div>");
										}else{
											sb.append("</div>");
											sb.append("</div>");
										}


									}
									noCol=newM;
									colNo=1;// current coulmn
									colW=24/noCol;
									if(inMatch){
										matchContent.append("<div class=\"block row\">");
										noMatchContent.append("<div class=\"block row\">");
									}else{
										sb.append("<div class=\"block row\">" ); //starting column block
									}

								}
								break;

							case "cb":
								if(colNo != 1){
									Iterator<String> closeTag = currentTagToCloseStack.descendingIterator();
									while(closeTag.hasNext()){
										String tagToClose = closeTag.next();
										if(inMatch){
											matchContent.append(tagToClose);
											noMatchContent.append(tagToClose);
										}else{
											sb.append(tagToClose);
										}

									}
									if(inMatch){
										matchContent.append("</div>");
										noMatchContent.append("</div>");
									}else{
										sb.append("</div>"); //end of previous column
									}

								}
								String colDiv;
								if(noCol == colNo){
									colDiv="<div class=\"column moreThan1 span-"+colW+" last\">";

								}
								else{
									colDiv="<div class=\"column moreThan1 span-"+colW+"\">";
								}
								if(inMatch){
									matchContent.append(colDiv);
									noMatchContent.append(colDiv);
								}else{
									sb.append(colDiv);
								}

								Iterator<String> openTag = currentTagStack.descendingIterator();
								while(openTag.hasNext()){
									String tagToOpen=openTag.next();
									if(inMatch){
										matchContent.append(tagToOpen);
										noMatchContent.append(tagToOpen);
									}else{
										sb.append(tagToOpen);
									}

								}

								colNo++;
						}
						break;

					case XMLStreamConstants.END_ELEMENT:
						localname = parser.getLocalName();
						//System.out.println("Event name end "+localname);
						switch (localname) {
							//case "body":
							case part:
								start=false;

								break;
							case "choice":
							case "subst":
								flagChoice=false;
								if(flagNote){
									noteContent.append("<br/>");
									noteContent.append("<span class=\"");
									noteContent.append(choiceClass);

									noteContent.append("\"  title=\"").append(choiceCorr).append("\">");
									noteContent.append(choiceSic).append("</span>");
								}else{
									if(inMatch){
										matchContent.append("<span class=\"");
										matchContent.append(choiceClass);
										matchContent.append("\"  title=\"").append(choiceCorr).append("\">")
										matchContent.append("<span class= \" hit \">")
										matchContent.append(choiceSic).append("</span>").append("</span>");
										if(closeSicHit){
											closeSicHit=false;
											inMatch=false;
											sb.append(matchContent.toString());
											if(pageToShow.equals('')){pageToShow=currentPage;}
											inMatch =false;
											matchContent.setLength(0);
											noMatchContent.setLength(0);
											remQuery.setLength(0);
										    remQuery.append(query.replaceAll("\\s+", ""));
											
										}
										

										noMatchContent.append("<span class=\"");
										noMatchContent.append(choiceClass);
										noMatchContent.append("\"  title=\"").append(choiceCorr).append("\">")
										noMatchContent.append(choiceSic).append("</span>");
										
										if(closeSicHit){
											closeSicHit=false;
											inMatch=false;
											sb.append(matchContent.toString());
											sb.append("</span>");
											if(pageToShow.equals('')){pageToShow=currentPage;}
											inMatch =false;
											matchContent.setLength(0);
											noMatchContent.setLength(0);
											remQuery.setLength(0);
											remQuery.append(query.replaceAll("\\s+", ""));
											
										}
									}else{
										sb.append("<span class=\"");
										sb.append(choiceClass);

										sb.append("\"  title=\"").append(choiceCorr).append("\">");
										sb.append(choiceSic).append("</span>");
									}


									if(choiceClass.equals("del")){
										if(inMatch){
											matchContent.append(" "+choiceCorr);
											noMatchContent.append(" "+choiceCorr);
										}else{
											sb.append(" "+choiceCorr);
										}

									}
								}
								break;
							case "sic":
								choiceClass="sic";
								flagSic=false;
								break;
							case "abbr":
								choiceClass="abbr";
								flagSic=false;
								flagAbbr = false;
								break;
							case "corr":
							case "expan":
								flagCorr=false;
								break;
							case "del":
								if(flagSic){
									choiceClass="del";
									flagSic=false;

								}else{
									if(flagNote){
										noteContent.append("</span>");
									}else{
										String tagToClose=currentTagToCloseStack.removeFirst();
										currentTagStack.removeFirst();
										if(inMatch){

											matchContent.append(tagToClose);
											noMatchContent.append(tagToClose);
										}else{
											sb.append(tagToClose);
										}


									}
								}
								break;
							case "add":
								if(flagCorr){
									flagCorr=false;

								}else{
									if(flagNote){
										noteContent.append("</span>");
									}else{
										String tagToClose=currentTagToCloseStack.removeFirst();
										currentTagStack.removeFirst();
										if(inMatch){
											matchContent.append(tagToClose);
											noMatchContent.append(tagToClose);
										}else{
											sb.append(tagToClose);
										}

									}
								}
								break;
							case "hi":
								if(flagNote){
									noteContent.append("</span>");
								}else{
									String tagToClose=currentTagToCloseStack.removeFirst();
									currentTagStack.removeFirst();
									if(inMatch){
										matchContent.append(tagToClose);
										noMatchContent.append(tagToClose);
									}else{
										sb.append(tagToClose);
									}

								}
								break;

							case "head":

								String tagToClose=currentTagToCloseStack.removeFirst();
								currentTagStack.removeFirst();
								if(inMatch){
									matchContent.append(tagToClose);
									noMatchContent.append(tagToClose);
								}else{
									sb.append(tagToClose);
								}

								break;

							case "note":
								flagNote = false;

								if (noteContent.length() > 0) {
									if(noteType==1){
										if(leftNote){
											if(inMatch){
												matchContent.append("<span class=\"note margin-left\"  ");
												noMatchContent.append("<span class=\"note margin-left\"  ");
											}else{
												sb.append("<span class=\"note margin-left\"  ");
											}

											
											if(!noteID.equals("")){
												if(inMatch){
													matchContent.append("id=\"").append(noteID).append("\" ");
													noMatchContent.append("id=\"").append(noteID).append("\" ");
												}else{
													sb.append("id=\"").append(noteID).append("\" ");
												}
											}

											sb.append("'>");
										}else{
											if(inMatch){
												matchContent.append("<span class=\"note margin-right\" ");
												noMatchContent.append("<span class=\"note margin-right\" ");
												if(!noteID.equals("")){
													matchContent.append("id=\"").append(noteID).append("\" ");
													noMatchContent.append("id=\"").append(noteID).append("\" ");

												}
												matchContent.append("'>");
												noMatchContent.append("<span class=\"note margin-right\" ");
											}else{
												sb.append("<span class=\"note margin-right\" ");
												if(!noteID.equals("")){
													sb.append("id=\"").append(noteID).append("\" ");
												}
												sb.append("'>");
											}
											
										}
										if(inMatch){
											matchContent.append(noteContent.toString()).append("</span>");
											noMatchContent.append(noteContent.toString()).append("</span>");
										}else{
											sb.append(noteContent.toString()).append("</span>");
										}
									
									}else if(noteType==2){
										if(inMatch){

											matchContent.append("<div class=\"note foot\" >" );
											if(!noteID.equals("")){
												matchContent.append("id=\"").append(noteID).append("\" ");
											}
											matchContent.append(" >" );
											matchContent.append(noteContent.toString()).append("</div>");

											noMatchContent.append("<div class=\"note foot\" >" );
											if(!noteID.equals("")){
												noMatchContent.append("id=\"").append(noteID).append("\" ");
											}
											noMatchContent.append(" >" );
											noMatchContent.append(noteContent.toString()).append("</div>");
										}else{
											sb.append("<div class=\"note foot\" >" );
											if(!noteID.equals("")){
												sb.append("id=\"").append(noteID).append("\" ");
											}
											sb.append(" >" );
											sb.append(noteContent.toString()).append("</div>");
										}

									}else{
										if(inMatch){
											matchContent.append("<div class=\"note otherNote\" ");
											if(!noteID.equals("")){
												matchContent.append("id=\"").append(noteID).append("\" ");
											}
											matchContent.append(" >" );
											matchContent.append(noteContent.toString()).append("</div>");

											noMatchContent.append("<div class=\"note otherNote\" ");
											if(!noteID.equals("")){
												noMatchContent.append("id=\"").append(noteID).append("\" ");
											}
											noMatchContent.append(" >" );
											noMatchContent.append(noteContent.toString()).append("</div>");
										}else{
											sb.append("<div class=\"note otherNote\" " );
											if(!noteID.equals("")){
												sb.append("id=\"").append(noteID).append("\" ");
											}
											sb.append(" >" );
											sb.append(noteContent.toString()).append("</div>");
										}



									}

								}
								noteType=0;
								break;

							case "lg":
							case "p":
								String tagToclose= currentTagToCloseStack.removeFirst();
								currentTagStack.removeFirst();
								if(inMatch){

									matchContent.append(tagToclose);
									//.append("<br/>");
									noMatchContent.append(tagToclose);
									//.append("<br/>");
								}else{
									sb.append(tagToclose);//.append("<br/>");
								}


								break;

							case "q":
								if(flagNote){
									noteContent.append("</span>");
								}else{
									String tagToClose = currentTagToCloseStack.removeFirst();
									currentTagStack.removeFirst();
									if(inMatch){
										matchContent.append(tagToClose);
										noMatchContent.append(tagToClose);
									}else{
										sb.append(tagToClose);
									}

								}
								break;

							case "fw":
								ignore = false;
								if(inMatch){
									matchContent.append("]</span>");
									noMatchContent.append("]</span>");
								}else{
									sb.append("]</span>");
								}

								break;

							case "c":
								if(inMatch){
									matchContent.append("</span>");
									noMatchContent.append("</span>");
								}else{
									sb.append("</span>");
								}

								break;

							case "ref":
								if(flagNote){
									noteContent.append("</a>");
								}else{
									String tagToClose = currentTagToCloseStack.removeFirst();
									currentTagStack.removeFirst();
									if(inMatch){
										matchContent.append(tagToClose);
										noMatchContent.append(tagToClose);
									}else{
										sb.append(tagToClose);
									}


								}
								break;
							case "quote":
								if(flagNote){
									noteContent.append("</span>");
								}else{
									String tagToClose = currentTagToCloseStack.removeFirst();
									currentTagStack.removeFirst();
									if(inMatch){
										matchContent.append(tagToClose);
										noMatchContent.append(tagToClose);
									}else{
										sb.append(tagToClose);
									}


								}
								break;
						}
						break;
					case XMLStreamConstants.CHARACTERS:
						//System.out.println("Event name text");
						if( !flagNote && parser.getText().replaceAll("\\s+", "").length() > 0){
							leftNote=false;

						}
						if(flagChoice && flagSic && parser.getText().length() > 0){
							choiceSic += parser.getText().replaceAll("\\s+", " ");
//							String choiseSicTest= parser.getText().replaceAll("\\s+", ""); 
//							if(flagAbbr && remQuery.length() > choiceSicTest.length() && remQuery.substring(0,choiceSicTest.length()).equals(choiceSicTest)){
//								inMatch=true;
//								String tmpRest= remQuery.substring(choiceSicTest.length(), remQuery.length());
//								remQuery.setLength(0);
//								remQuery.append(tmpRest);
//							}
							
						}else if(flagChoice && flagCorr && parser.getText().length() > 0){
							choiceCorr += parser.getText().replaceAll("\\s+", " ");
							String choiceCorrTest= parser.getText().replaceAll("\\s+", "");
							if( remQuery.length() > choiceCorrTest.length() && remQuery.substring(0,choiceCorrTest.length()).equals(choiceCorrTest)){
								if(!inMatch){
									matchContent.append("<span class= \" hit \">");
									
								}
								inMatch=true;
								String tmpRest= remQuery.substring(choiceCorrTest.length(), remQuery.length());
								remQuery.setLength(0);
								remQuery.append(tmpRest);
								if(tmpRest.length()==0){
									closeSicHit = true;
								}
							}
						}else if (flagNote && parser.getText().length() > 0) {
							noteContent.append(parser.getText().replace("'", "&apos;"));
						}else{

							//!!!!!!!!!! manage adding the mark and and matching
							String textToAdd = parser.getText().replaceAll("\\n", " ").replaceAll("\\s+", " ");
							noMatchContent.append(textToAdd);

							String textToMatch = parser.getText().replaceAll("\\s+", "").replaceAll("\\n", ""); //text without spaces

							//System.out.println ("add: "+textToAdd+"; match: "+textToMatch +"; query: "+ remQuery.toString());

							if(inMatch){
								if(remQuery.length() >= textToMatch.length()){
									if(remQuery.substring(0, textToMatch.length()).equals(textToMatch)){
										matchContent.append("<span class= \" hit \">").append(textToAdd).append("</span>");
										String tmpRest= remQuery.substring(textToMatch.length(), remQuery.length());
										
										//System.out.println("tmp q rest: "+ tmpRest);
										remQuery.setLength(0);
										remQuery.append(tmpRest);
										//remQuery = remQuery.substring(textToMatch.length(), remQuery.length());
										if(remQuery.length() == 0 ){ //if equal, match finished
											sb.append(matchContent.toString());
											inMatch =false;
											matchContent.setLength(0);
											noMatchContent.setLength(0);
											
											if(pageToShow.equals('')){pageToShow=currentPage;}
										}else{
											inMatch =true; // sanity check
										}

									}else{
										// restart matching
										inMatch =false;
										sb.append(noMatchContent.toString());
										matchContent.setLength(0);
										noMatchContent.setLength(0);
										
										remQuery.setLength(0);
										remQuery.append(query.replaceAll("\\s+", ""));
									}

								}else{//remQuery.length < textToMatch.length()

									if(textToMatch.substring(0, remQuery.length()).equals(remQuery.toString())){
//										
										int noCaracter=remQuery.length();
										int index=0;
										matchContent.append("<span class= \" hit \">");
										while(noCaracter>=0){
											char c=textToAdd.charAt(index);
											matchContent.append(c);
											if(!c.equals(" ")){
												noCaracter--;
											}
											index++;
										}


										matchContent.append("</span>");
										inMatch =false;
										sb.append(matchContent.toString());
										if(pageToShow.equals('')){pageToShow=currentPage;}
										matchContent.setLength(0);
										noMatchContent.setLength(0);
										
										remQuery.setLength(0);
										remQuery.append(query.replaceAll("\\s+", ""));

											sb.append(textToAdd.substring(index));
										

									}else{
										// restart matching
										inMatch =false;
										sb.append(noMatchContent.toString());
										matchContent.setLength(0);
										noMatchContent.setLength(0);
										
										remQuery.setLength(0);
										remQuery.append(query.replaceAll("\\s+", ""));
									}
								}

							}else{// not in match
								String [] queryParts =  query.split("\\s");


								int startIndex = getStartIndex(textToAdd, queryParts);//!!!!!!!
								if(startIndex==-1){
									//noMatch
									sb.append(textToAdd);

								}else{
								   sb.append(textToAdd.substring(0, startIndex));
								   noMatchContent.setLength(0);
									noMatchContent.append(textToAdd.substring(startIndex));

									textToAdd=textToAdd.substring(startIndex);
									
									textToMatch = textToAdd.replaceAll("\\s+", "").replaceAll("\\n", "");
									
									if(remQuery.length() >= textToMatch.length()){
										if(remQuery.substring(0, textToMatch.length()).equals(textToMatch)){
											matchContent.append("<span class= \" hit \">").append(textToAdd).append("</span>");
											String tmpRest= remQuery.substring(textToMatch.length(), remQuery.length());
											
											//System.out.println("tmp q rest: "+ tmpRest);
											remQuery.setLength(0);
											remQuery.append(tmpRest);
											
											if(remQuery.length() == 0 ){ //if equal, match finished
												sb.append(matchContent.toString());
												inMatch =false;
												matchContent.setLength(0);
										        noMatchContent.setLength(0);
												if(pageToShow.equals('')){pageToShow=currentPage;}
											}else{
												inMatch =true; // sanity check
											}
	
										}else{
											// restart matching
											inMatch =false;
											sb.append(noMatchContent.toString());
											matchContent.setLength(0);
											noMatchContent.setLength(0);
											
											remQuery.setLength(0);
											remQuery.append(query.replaceAll("\\s+", ""));
										}
	
									}else{//remQuery.length < textToMatch.length()
	
										if(textToMatch.substring(0, remQuery.length()).equals(remQuery.toString())){
											
											int noCaracter=remQuery.length();
											int index=0;
											matchContent.append("<span class= \" hit \">");
											while(noCaracter>=0){
												char c=textToAdd.charAt(index);
												matchContent.append(c);
												if(!c.equals(" ")){
													noCaracter--;
												}
												index++;
											}
	
	
											matchContent.append("</span>");
											
											
											
											sb.append(matchContent.toString());
											if(pageToShow.equals('')){pageToShow=currentPage;}
											inMatch =false;
											matchContent.setLength(0);
											noMatchContent.setLength(0);
											remQuery.setLength(0);
										    remQuery.append(query.replaceAll("\\s+", ""));
											//for(int i = index; i < textToAddParts.length; i++){
												sb.append(textToAdd.substring(index));
											//}
	
										}else{
											// restart matching
											inMatch =false;
											sb.append(noMatchContent.toString());
											matchContent.setLength(0);;
											noMatchContent.setLength(0);;
											
											remQuery.setLength(0);;
											remQuery.append(query.replaceAll("\\s+", ""));
										}
									}
	

								}



							}

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
		
		System.out.println("On page: "+ pageToShow);
		
		return new SelectedPageSummary(
				page : sb.toString(),
				pages: pages,
				pTs:pageToShow,
				)

	}

	def getStartIndex (String textToAdd, String [] queryParts){
		
		int startIndex = textToAdd.indexOf(queryParts[0]);
		
		if(startIndex==-1){
			return -1
		}else{
		return startIndex;



		}

	}

}
