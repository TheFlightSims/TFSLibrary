package org.apache.xerces.parsers;

import org.apache.xerces.impl.dtd.DTDGrammar;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.XMLDTDContentModelHandler;
import org.apache.xerces.xni.XMLDTDHandler;
import org.apache.xerces.xni.XMLLocator;
import org.apache.xerces.xni.XMLResourceIdentifier;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLDTDContentModelSource;
import org.apache.xerces.xni.parser.XMLDTDScanner;
import org.apache.xerces.xni.parser.XMLDTDSource;

public abstract class DTDParser extends XMLGrammarParser implements XMLDTDHandler, XMLDTDContentModelHandler {
  protected XMLDTDScanner fDTDScanner;
  
  public DTDParser(SymbolTable paramSymbolTable) {
    super(paramSymbolTable);
  }
  
  public DTDGrammar getDTDGrammar() {
    return null;
  }
  
  public void startEntity(String paramString1, String paramString2, String paramString3, String paramString4) throws XNIException {}
  
  public void textDecl(String paramString1, String paramString2) throws XNIException {}
  
  public void startDTD(XMLLocator paramXMLLocator, Augmentations paramAugmentations) throws XNIException {}
  
  public void comment(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void processingInstruction(String paramString, XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {}
  
  public void startExternalSubset(XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {}
  
  public void endExternalSubset(Augmentations paramAugmentations) throws XNIException {}
  
  public void elementDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void startAttlist(String paramString, Augmentations paramAugmentations) throws XNIException {}
  
  public void attributeDecl(String paramString1, String paramString2, String paramString3, String[] paramArrayOfString, String paramString4, XMLString paramXMLString1, XMLString paramXMLString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void endAttlist(Augmentations paramAugmentations) throws XNIException {}
  
  public void internalEntityDecl(String paramString, XMLString paramXMLString1, XMLString paramXMLString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void externalEntityDecl(String paramString, XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {}
  
  public void unparsedEntityDecl(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException {}
  
  public void notationDecl(String paramString, XMLResourceIdentifier paramXMLResourceIdentifier, Augmentations paramAugmentations) throws XNIException {}
  
  public void startConditional(short paramShort, Augmentations paramAugmentations) throws XNIException {}
  
  public void endConditional(Augmentations paramAugmentations) throws XNIException {}
  
  public void endDTD(Augmentations paramAugmentations) throws XNIException {}
  
  public void endEntity(String paramString, Augmentations paramAugmentations) throws XNIException {}
  
  public void startContentModel(String paramString, short paramShort) throws XNIException {}
  
  public void mixedElement(String paramString) throws XNIException {}
  
  public void childrenStartGroup() throws XNIException {}
  
  public void childrenElement(String paramString) throws XNIException {}
  
  public void childrenSeparator(short paramShort) throws XNIException {}
  
  public void childrenOccurrence(short paramShort) throws XNIException {}
  
  public void childrenEndGroup() throws XNIException {}
  
  public void endContentModel() throws XNIException {}
  
  public abstract XMLDTDSource getDTDSource();
  
  public abstract void setDTDSource(XMLDTDSource paramXMLDTDSource);
  
  public abstract void ignoredCharacters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException;
  
  public abstract void endParameterEntity(String paramString, Augmentations paramAugmentations) throws XNIException;
  
  public abstract void textDecl(String paramString1, String paramString2, Augmentations paramAugmentations) throws XNIException;
  
  public abstract void startParameterEntity(String paramString1, XMLResourceIdentifier paramXMLResourceIdentifier, String paramString2, Augmentations paramAugmentations) throws XNIException;
  
  public abstract XMLDTDContentModelSource getDTDContentModelSource();
  
  public abstract void setDTDContentModelSource(XMLDTDContentModelSource paramXMLDTDContentModelSource);
  
  public abstract void endContentModel(Augmentations paramAugmentations) throws XNIException;
  
  public abstract void endGroup(Augmentations paramAugmentations) throws XNIException;
  
  public abstract void occurrence(short paramShort, Augmentations paramAugmentations) throws XNIException;
  
  public abstract void separator(short paramShort, Augmentations paramAugmentations) throws XNIException;
  
  public abstract void element(String paramString, Augmentations paramAugmentations) throws XNIException;
  
  public abstract void pcdata(Augmentations paramAugmentations) throws XNIException;
  
  public abstract void startGroup(Augmentations paramAugmentations) throws XNIException;
  
  public abstract void empty(Augmentations paramAugmentations) throws XNIException;
  
  public abstract void any(Augmentations paramAugmentations) throws XNIException;
  
  public abstract void startContentModel(String paramString, Augmentations paramAugmentations) throws XNIException;
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerces\parsers\DTDParser.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */