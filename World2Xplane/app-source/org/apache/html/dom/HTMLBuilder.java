package org.apache.html.dom;

import java.util.Vector;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.xerces.dom.ElementImpl;
import org.apache.xerces.dom.ProcessingInstructionImpl;
import org.apache.xerces.dom.TextImpl;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLDocument;
import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

public class HTMLBuilder implements DocumentHandler {
  protected HTMLDocumentImpl _document;
  
  protected ElementImpl _current;
  
  private boolean _ignoreWhitespace = true;
  
  private boolean _done = true;
  
  protected Vector _preRootNodes;
  
  public void startDocument() throws SAXException {
    if (!this._done)
      throw new SAXException("HTM001 State error: startDocument fired twice on one builder."); 
    this._document = null;
    this._done = false;
  }
  
  public void endDocument() throws SAXException {
    if (this._document == null)
      throw new SAXException("HTM002 State error: document never started or missing document element."); 
    if (this._current != null)
      throw new SAXException("HTM003 State error: document ended before end of document element."); 
    this._current = null;
    this._done = true;
  }
  
  public synchronized void startElement(String paramString, AttributeList paramAttributeList) throws SAXException {
    ElementImpl elementImpl;
    if (paramString == null)
      throw new SAXException("HTM004 Argument 'tagName' is null."); 
    if (this._document == null) {
      this._document = new HTMLDocumentImpl();
      elementImpl = (ElementImpl)this._document.getDocumentElement();
      this._current = elementImpl;
      if (this._current == null)
        throw new SAXException("HTM005 State error: Document.getDocumentElement returns null."); 
      if (this._preRootNodes != null) {
        int i = this._preRootNodes.size();
        while (i-- > 0)
          this._document.insertBefore(this._preRootNodes.elementAt(i), (Node)elementImpl); 
        this._preRootNodes = null;
      } 
    } else {
      if (this._current == null)
        throw new SAXException("HTM006 State error: startElement called after end of document element."); 
      elementImpl = (ElementImpl)this._document.createElement(paramString);
      this._current.appendChild((Node)elementImpl);
      this._current = elementImpl;
    } 
    if (paramAttributeList != null)
      for (byte b = 0; b < paramAttributeList.getLength(); b++)
        elementImpl.setAttribute(paramAttributeList.getName(b), paramAttributeList.getValue(b));  
  }
  
  public void endElement(String paramString) throws SAXException {
    if (this._current == null)
      throw new SAXException("HTM007 State error: endElement called with no current node."); 
    if (!this._current.getNodeName().equalsIgnoreCase(paramString))
      throw new SAXException("HTM008 State error: mismatch in closing tag name " + paramString + "\n" + paramString); 
    if (this._current.getParentNode() == this._current.getOwnerDocument()) {
      this._current = null;
    } else {
      this._current = (ElementImpl)this._current.getParentNode();
    } 
  }
  
  public void characters(String paramString) throws SAXException {
    if (this._current == null)
      throw new SAXException("HTM009 State error: character data found outside of root element."); 
    this._current.appendChild((Node)new TextImpl((CoreDocumentImpl)this._document, paramString));
  }
  
  public void characters(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    if (this._current == null)
      throw new SAXException("HTM010 State error: character data found outside of root element."); 
    this._current.appendChild((Node)new TextImpl((CoreDocumentImpl)this._document, new String(paramArrayOfchar, paramInt1, paramInt2)));
  }
  
  public void ignorableWhitespace(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws SAXException {
    if (!this._ignoreWhitespace)
      this._current.appendChild((Node)new TextImpl((CoreDocumentImpl)this._document, new String(paramArrayOfchar, paramInt1, paramInt2))); 
  }
  
  public void processingInstruction(String paramString1, String paramString2) throws SAXException {
    if (this._current == null && this._document == null) {
      if (this._preRootNodes == null)
        this._preRootNodes = new Vector(); 
      this._preRootNodes.addElement(new ProcessingInstructionImpl(null, paramString1, paramString2));
    } else if (this._current == null && this._document != null) {
      this._document.appendChild((Node)new ProcessingInstructionImpl((CoreDocumentImpl)this._document, paramString1, paramString2));
    } else {
      this._current.appendChild((Node)new ProcessingInstructionImpl((CoreDocumentImpl)this._document, paramString1, paramString2));
    } 
  }
  
  public HTMLDocument getHTMLDocument() {
    return this._document;
  }
  
  public void setDocumentLocator(Locator paramLocator) {}
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLBuilder.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */