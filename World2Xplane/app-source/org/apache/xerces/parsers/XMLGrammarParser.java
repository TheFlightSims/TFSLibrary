package org.apache.xerces.parsers;

import org.apache.xerces.impl.dv.DTDDVFactory;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.parser.XMLParserConfiguration;

public abstract class XMLGrammarParser extends XMLParser {
  protected DTDDVFactory fDatatypeValidatorFactory;
  
  protected XMLGrammarParser(SymbolTable paramSymbolTable) {
    super((XMLParserConfiguration)ObjectFactory.createObject("org.apache.xerces.xni.parser.XMLParserConfiguration", "org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
    this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", paramSymbolTable);
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\parsers\XMLGrammarParser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */