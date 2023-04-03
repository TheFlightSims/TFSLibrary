package org.apache.html.dom;

import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.util.Hashtable;
import java.util.Locale;
import org.apache.xerces.dom.CoreDocumentImpl;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.NodeImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLDocument;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.html.HTMLTitleElement;

public class HTMLDocumentImpl extends DocumentImpl implements HTMLDocument {
  private static final long serialVersionUID = 4285791750126227180L;
  
  private HTMLCollectionImpl _anchors;
  
  private HTMLCollectionImpl _forms;
  
  private HTMLCollectionImpl _images;
  
  private HTMLCollectionImpl _links;
  
  private HTMLCollectionImpl _applets;
  
  private StringWriter _writer;
  
  private static Hashtable _elementTypesHTML;
  
  private static final Class[] _elemClassSigHTML = new Class[] { HTMLDocumentImpl.class, String.class };
  
  public HTMLDocumentImpl() {
    populateElementTypes();
  }
  
  public synchronized Element getDocumentElement() {
    Node node1;
    for (node1 = getFirstChild(); node1 != null; node1 = node1.getNextSibling()) {
      if (node1 instanceof org.w3c.dom.html.HTMLHtmlElement)
        return (HTMLElement)node1; 
    } 
    node1 = new HTMLHtmlElementImpl(this, "HTML");
    for (Node node2 = getFirstChild(); node2 != null; node2 = node) {
      Node node = node2.getNextSibling();
      node1.appendChild(node2);
    } 
    appendChild(node1);
    return (HTMLElement)node1;
  }
  
  public synchronized HTMLElement getHead() {
    Node node;
    Element element = getDocumentElement();
    synchronized (element) {
      for (node = element.getFirstChild(); node != null && !(node instanceof org.w3c.dom.html.HTMLHeadElement); node = node.getNextSibling());
      if (node != null) {
        synchronized (node) {
          for (Node node1 = element.getFirstChild(); node1 != null && node1 != node; node1 = node2) {
            Node node2 = node1.getNextSibling();
            node.insertBefore(node1, node.getFirstChild());
          } 
        } 
        return (HTMLElement)node;
      } 
      node = new HTMLHeadElementImpl(this, "HEAD");
      element.insertBefore(node, element.getFirstChild());
    } 
    return (HTMLElement)node;
  }
  
  public synchronized String getTitle() {
    HTMLElement hTMLElement = getHead();
    NodeList nodeList = hTMLElement.getElementsByTagName("TITLE");
    if (nodeList.getLength() > 0) {
      Node node = nodeList.item(0);
      return ((HTMLTitleElement)node).getText();
    } 
    return "";
  }
  
  public synchronized void setTitle(String paramString) {
    HTMLElement hTMLElement = getHead();
    NodeList nodeList = hTMLElement.getElementsByTagName("TITLE");
    if (nodeList.getLength() > 0) {
      Node node = nodeList.item(0);
      if (node.getParentNode() != hTMLElement)
        hTMLElement.appendChild(node); 
      ((HTMLTitleElement)node).setText(paramString);
    } else {
      HTMLTitleElementImpl hTMLTitleElementImpl = new HTMLTitleElementImpl(this, "TITLE");
      hTMLTitleElementImpl.setText(paramString);
      hTMLElement.appendChild(hTMLTitleElementImpl);
    } 
  }
  
  public synchronized HTMLElement getBody() {
    Node node;
    Element element = getDocumentElement();
    HTMLElement hTMLElement = getHead();
    synchronized (element) {
      for (node = hTMLElement.getNextSibling(); node != null && !(node instanceof org.w3c.dom.html.HTMLBodyElement) && !(node instanceof org.w3c.dom.html.HTMLFrameSetElement); node = node.getNextSibling());
      if (node != null) {
        synchronized (node) {
          for (Node node1 = hTMLElement.getNextSibling(); node1 != null && node1 != node; node1 = node2) {
            Node node2 = node1.getNextSibling();
            node.insertBefore(node1, node.getFirstChild());
          } 
        } 
        return (HTMLElement)node;
      } 
      node = new HTMLBodyElementImpl(this, "BODY");
      element.appendChild(node);
    } 
    return (HTMLElement)node;
  }
  
  public synchronized void setBody(HTMLElement paramHTMLElement) {
    synchronized (paramHTMLElement) {
      Element element = getDocumentElement();
      HTMLElement hTMLElement = getHead();
      synchronized (element) {
        NodeList nodeList = getElementsByTagName("BODY");
        if (nodeList.getLength() > 0) {
          Node node = nodeList.item(0);
          synchronized (node) {
            HTMLElement hTMLElement1 = hTMLElement;
            while (hTMLElement1 != null) {
              if (hTMLElement1 instanceof Element) {
                if (hTMLElement1 != node) {
                  element.insertBefore(paramHTMLElement, hTMLElement1);
                } else {
                  element.replaceChild(paramHTMLElement, node);
                } 
                return;
              } 
              Node node1 = hTMLElement1.getNextSibling();
            } 
            element.appendChild(paramHTMLElement);
          } 
          return;
        } 
        element.appendChild(paramHTMLElement);
      } 
    } 
  }
  
  public synchronized Element getElementById(String paramString) {
    Element element = super.getElementById(paramString);
    return (element != null) ? element : getElementById(paramString, this);
  }
  
  public NodeList getElementsByName(String paramString) {
    return new NameNodeListImpl((NodeImpl)this, paramString);
  }
  
  public final NodeList getElementsByTagName(String paramString) {
    return super.getElementsByTagName(paramString.toUpperCase(Locale.ENGLISH));
  }
  
  public final NodeList getElementsByTagNameNS(String paramString1, String paramString2) {
    return (paramString1 != null && paramString1.length() > 0) ? super.getElementsByTagNameNS(paramString1, paramString2.toUpperCase(Locale.ENGLISH)) : super.getElementsByTagName(paramString2.toUpperCase(Locale.ENGLISH));
  }
  
  public Element createElementNS(String paramString1, String paramString2, String paramString3) throws DOMException {
    return createElementNS(paramString1, paramString2);
  }
  
  public Element createElementNS(String paramString1, String paramString2) {
    return (paramString1 == null || paramString1.length() == 0) ? createElement(paramString2) : super.createElementNS(paramString1, paramString2);
  }
  
  public Element createElement(String paramString) throws DOMException {
    paramString = paramString.toUpperCase(Locale.ENGLISH);
    Class clazz = (Class)_elementTypesHTML.get(paramString);
    if (clazz != null)
      try {
        Constructor constructor = clazz.getConstructor(_elemClassSigHTML);
        return constructor.newInstance(new Object[] { this, paramString });
      } catch (Exception exception) {
        throw new IllegalStateException("HTM15 Tag '" + paramString + "' associated with an Element class that failed to construct.\n" + paramString);
      }  
    return new HTMLElementImpl(this, paramString);
  }
  
  public Attr createAttribute(String paramString) throws DOMException {
    return super.createAttribute(paramString.toLowerCase(Locale.ENGLISH));
  }
  
  public String getReferrer() {
    return null;
  }
  
  public String getDomain() {
    return null;
  }
  
  public String getURL() {
    return null;
  }
  
  public String getCookie() {
    return null;
  }
  
  public void setCookie(String paramString) {}
  
  public HTMLCollection getImages() {
    if (this._images == null)
      this._images = new HTMLCollectionImpl(getBody(), (short)3); 
    return this._images;
  }
  
  public HTMLCollection getApplets() {
    if (this._applets == null)
      this._applets = new HTMLCollectionImpl(getBody(), (short)4); 
    return this._applets;
  }
  
  public HTMLCollection getLinks() {
    if (this._links == null)
      this._links = new HTMLCollectionImpl(getBody(), (short)5); 
    return this._links;
  }
  
  public HTMLCollection getForms() {
    if (this._forms == null)
      this._forms = new HTMLCollectionImpl(getBody(), (short)2); 
    return this._forms;
  }
  
  public HTMLCollection getAnchors() {
    if (this._anchors == null)
      this._anchors = new HTMLCollectionImpl(getBody(), (short)1); 
    return this._anchors;
  }
  
  public void open() {
    if (this._writer == null)
      this._writer = new StringWriter(); 
  }
  
  public void close() {
    if (this._writer != null)
      this._writer = null; 
  }
  
  public void write(String paramString) {
    if (this._writer != null)
      this._writer.write(paramString); 
  }
  
  public void writeln(String paramString) {
    if (this._writer != null)
      this._writer.write(paramString + "\n"); 
  }
  
  public Node cloneNode(boolean paramBoolean) {
    HTMLDocumentImpl hTMLDocumentImpl = new HTMLDocumentImpl();
    callUserDataHandlers(this, hTMLDocumentImpl, (short)1);
    cloneNode((CoreDocumentImpl)hTMLDocumentImpl, paramBoolean);
    return hTMLDocumentImpl;
  }
  
  private Element getElementById(String paramString, Node paramNode) {
    for (Node node = paramNode.getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node instanceof Element) {
        if (paramString.equals(((Element)node).getAttribute("id")))
          return (Element)node; 
        Element element = getElementById(paramString, node);
        if (element != null)
          return element; 
      } 
    } 
    return null;
  }
  
  private static synchronized void populateElementTypes() {
    if (_elementTypesHTML != null)
      return; 
    _elementTypesHTML = new Hashtable(63);
    populateElementType("A", "HTMLAnchorElementImpl");
    populateElementType("APPLET", "HTMLAppletElementImpl");
    populateElementType("AREA", "HTMLAreaElementImpl");
    populateElementType("BASE", "HTMLBaseElementImpl");
    populateElementType("BASEFONT", "HTMLBaseFontElementImpl");
    populateElementType("BLOCKQUOTE", "HTMLQuoteElementImpl");
    populateElementType("BODY", "HTMLBodyElementImpl");
    populateElementType("BR", "HTMLBRElementImpl");
    populateElementType("BUTTON", "HTMLButtonElementImpl");
    populateElementType("DEL", "HTMLModElementImpl");
    populateElementType("DIR", "HTMLDirectoryElementImpl");
    populateElementType("DIV", "HTMLDivElementImpl");
    populateElementType("DL", "HTMLDListElementImpl");
    populateElementType("FIELDSET", "HTMLFieldSetElementImpl");
    populateElementType("FONT", "HTMLFontElementImpl");
    populateElementType("FORM", "HTMLFormElementImpl");
    populateElementType("FRAME", "HTMLFrameElementImpl");
    populateElementType("FRAMESET", "HTMLFrameSetElementImpl");
    populateElementType("HEAD", "HTMLHeadElementImpl");
    populateElementType("H1", "HTMLHeadingElementImpl");
    populateElementType("H2", "HTMLHeadingElementImpl");
    populateElementType("H3", "HTMLHeadingElementImpl");
    populateElementType("H4", "HTMLHeadingElementImpl");
    populateElementType("H5", "HTMLHeadingElementImpl");
    populateElementType("H6", "HTMLHeadingElementImpl");
    populateElementType("HR", "HTMLHRElementImpl");
    populateElementType("HTML", "HTMLHtmlElementImpl");
    populateElementType("IFRAME", "HTMLIFrameElementImpl");
    populateElementType("IMG", "HTMLImageElementImpl");
    populateElementType("INPUT", "HTMLInputElementImpl");
    populateElementType("INS", "HTMLModElementImpl");
    populateElementType("ISINDEX", "HTMLIsIndexElementImpl");
    populateElementType("LABEL", "HTMLLabelElementImpl");
    populateElementType("LEGEND", "HTMLLegendElementImpl");
    populateElementType("LI", "HTMLLIElementImpl");
    populateElementType("LINK", "HTMLLinkElementImpl");
    populateElementType("MAP", "HTMLMapElementImpl");
    populateElementType("MENU", "HTMLMenuElementImpl");
    populateElementType("META", "HTMLMetaElementImpl");
    populateElementType("OBJECT", "HTMLObjectElementImpl");
    populateElementType("OL", "HTMLOListElementImpl");
    populateElementType("OPTGROUP", "HTMLOptGroupElementImpl");
    populateElementType("OPTION", "HTMLOptionElementImpl");
    populateElementType("P", "HTMLParagraphElementImpl");
    populateElementType("PARAM", "HTMLParamElementImpl");
    populateElementType("PRE", "HTMLPreElementImpl");
    populateElementType("Q", "HTMLQuoteElementImpl");
    populateElementType("SCRIPT", "HTMLScriptElementImpl");
    populateElementType("SELECT", "HTMLSelectElementImpl");
    populateElementType("STYLE", "HTMLStyleElementImpl");
    populateElementType("TABLE", "HTMLTableElementImpl");
    populateElementType("CAPTION", "HTMLTableCaptionElementImpl");
    populateElementType("TD", "HTMLTableCellElementImpl");
    populateElementType("TH", "HTMLTableCellElementImpl");
    populateElementType("COL", "HTMLTableColElementImpl");
    populateElementType("COLGROUP", "HTMLTableColElementImpl");
    populateElementType("TR", "HTMLTableRowElementImpl");
    populateElementType("TBODY", "HTMLTableSectionElementImpl");
    populateElementType("THEAD", "HTMLTableSectionElementImpl");
    populateElementType("TFOOT", "HTMLTableSectionElementImpl");
    populateElementType("TEXTAREA", "HTMLTextAreaElementImpl");
    populateElementType("TITLE", "HTMLTitleElementImpl");
    populateElementType("UL", "HTMLUListElementImpl");
  }
  
  private static void populateElementType(String paramString1, String paramString2) {
    try {
      _elementTypesHTML.put(paramString1, ObjectFactory.findProviderClass("org.apache.html.dom." + paramString2, HTMLDocumentImpl.class.getClassLoader(), true));
    } catch (Exception exception) {
      new RuntimeException("HTM019 OpenXML Error: Could not find or execute class " + paramString2 + " implementing HTML element " + paramString1 + "\n" + paramString2 + "\t" + paramString1);
    } 
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLDocumentImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */