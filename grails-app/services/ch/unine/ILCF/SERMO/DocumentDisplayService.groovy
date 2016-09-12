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

@Transactional
class DocumentDisplayService {
	boolean transactional = false
	/** The parser. */
	private XMLStreamReader parser;
	
    def process(File file) {
    }
}
