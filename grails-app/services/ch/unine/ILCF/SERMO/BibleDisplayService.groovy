package ch.unine.ILCF.SERMO

import java.util.concurrent.ArrayBlockingQueue.Itrs.Node;

import grails.transaction.Transactional

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPath
import javax.xml.xpath.XPathExpression
import javax.xml.xpath.XPathFactory
import javax.xml.xpath.XPathConstants;
import groovy.xml.Namespace
import org.w3c.dom.Document
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

class BibleDisplayService {
	boolean transactional = false
	String getBibleText(String bibleFile, String verses) {
		StringBuilder resultText = new StringBuilder();

		resultText.append("<div class=\"verse\" >");
		def nXmlTei = new XmlSlurper(false,false).parseText(new File(bibleFile).getText());


		String [] fields = verses.split(';');

		for(int k=0;k < fields.length;k++){
			//	System.out.println(fields[k].toString());
			String [] refParts = (fields[k]).split('\\.');
			def book = refParts[0];
			def chapter = refParts[1];
			def verse = refParts.length == 3 ? refParts[2]:"ch";
			def theBook = nXmlTei.text.body.'**'.find{node-> node.name() == 'div' && node.'@xml:id' == book }
			if(theBook == null){
				resultText.append("<br/>").append("<p>"+"Les livres Ecclésiastiques, Sagesse, Judith et Baruch sont contenus dans la Bible de 1588, mais ne font plus partie du canon de la Bible Segond. "+"</p>").append("<br/>");
			}else{
				String [] ver = verse.split(':');
				for(int j=0;j<ver.length;j++){
					def v = ver[j].split('-');
					if(v.length == 2){

						for(int i = Integer.parseInt(v[0]); i<= Integer.parseInt(v[1]); i++){

							def bookTitle = theBook.head.text()
							resultText.append("<h2>").append(bookTitle).append(" "+chapter+". "+i).append("</h2>");

							String id = book+"."+chapter+"."+ i ;
							def par = nXmlTei.text.body.'**'.find{node-> node.name() == 'p' && node.'@xml:id' == id }
							if(par == null){
								resultText.append("<p>"+"Le verset n'existe pas dans cette Bible"+"</p>").append("<br/>");
							}else{
								resultText.append("<p>"+par.text()+"</p>").append("<br/>");
							}

						}

					}else if(v[0].equals("ch")){

						def bookTitle = theBook.head.text()
						resultText.append("<h2>").append(bookTitle).append(" "+chapter+".").append("</h2>");

						String id = book+"."+chapter+"."+ 1 ;
						def par = nXmlTei.text.body.'**'.find{node-> node.name() == 'p' && node.'@xml:id' == id }
						if(par == null){
							resultText.append("<p>"+"Le chapitre n'existe pas dans cette Bible"+"</p>").append("<br/>");
						}else{


							int index = 1;
							while(par!=null){

								resultText.append("<p>"+index+". "+par.text()+"</p>").append("<br/>");
								index++;
								id = book+"."+chapter+"."+ index ;
								par = nXmlTei.text.body.'**'.find{node-> node.name() == 'p' && node.'@xml:id' == id }

							}
						}

					}else{


						def bookTitle = theBook.head.text()
						resultText.append("<h2>").append(bookTitle).append(" "+chapter+". "+v[0]).append("</h2>");

						String id = book+"."+chapter+"."+ v[0] ;
						def par = nXmlTei.text.body.'**'.find{node-> node.name() == 'p' && node.'@xml:id' == id }
						if(par == null){
							resultText.append("<p>"+"Le verset n'existe pas dans cette Bible"+"</p>").append("<br/>");
						}else{
							resultText.append("<p>"+par.text()+"</p>").append("<br/>");
						}

					}
				}
			}
		}



		resultText.append("</div>");

		return resultText.toString();
	}

}
