package org.apache.html.dom;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLCollection;
import org.w3c.dom.html.HTMLElement;

class HTMLCollectionImpl implements HTMLCollection {
  static final short ANCHOR = 1;
  
  static final short FORM = 2;
  
  static final short IMAGE = 3;
  
  static final short APPLET = 4;
  
  static final short LINK = 5;
  
  static final short OPTION = 6;
  
  static final short ROW = 7;
  
  static final short ELEMENT = 8;
  
  static final short AREA = -1;
  
  static final short TBODY = -2;
  
  static final short CELL = -3;
  
  private short _lookingFor;
  
  private Element _topLevel;
  
  HTMLCollectionImpl(HTMLElement paramHTMLElement, short paramShort) {
    if (paramHTMLElement == null)
      throw new NullPointerException("HTM011 Argument 'topLevel' is null."); 
    this._topLevel = paramHTMLElement;
    this._lookingFor = paramShort;
  }
  
  public final int getLength() {
    return getLength(this._topLevel);
  }
  
  public final Node item(int paramInt) {
    if (paramInt < 0)
      throw new IllegalArgumentException("HTM012 Argument 'index' is negative."); 
    return item(this._topLevel, new CollectionIndex(paramInt));
  }
  
  public final Node namedItem(String paramString) {
    if (paramString == null)
      throw new NullPointerException("HTM013 Argument 'name' is null."); 
    return namedItem(this._topLevel, paramString);
  }
  
  private int getLength(Element paramElement) {
    int i;
    synchronized (paramElement) {
      i = 0;
      for (Node node = paramElement.getFirstChild(); node != null; node = node.getNextSibling()) {
        if (node instanceof Element)
          if (collectionMatch((Element)node, null)) {
            i++;
          } else if (recurse()) {
            i += getLength((Element)node);
          }  
      } 
    } 
    return i;
  }
  
  private Node item(Element paramElement, CollectionIndex paramCollectionIndex) {
    synchronized (paramElement) {
      for (Node node = paramElement.getFirstChild(); node != null; node = node.getNextSibling()) {
        if (node instanceof Element)
          if (collectionMatch((Element)node, null)) {
            if (paramCollectionIndex.isZero())
              return node; 
            paramCollectionIndex.decrement();
          } else if (recurse()) {
            Node node1 = item((Element)node, paramCollectionIndex);
            if (node1 != null)
              return node1; 
          }  
      } 
    } 
    return null;
  }
  
  private Node namedItem(Element paramElement, String paramString) {
    synchronized (paramElement) {
      for (Node node = paramElement.getFirstChild(); node != null; node = node.getNextSibling()) {
        if (node instanceof Element) {
          if (collectionMatch((Element)node, paramString))
            return node; 
          if (recurse()) {
            Node node1 = namedItem((Element)node, paramString);
            if (node1 != null)
              return node1; 
          } 
        } 
      } 
      return node;
    } 
  }
  
  protected boolean recurse() {
    return (this._lookingFor > 0);
  }
  
  protected boolean collectionMatch(Element paramElement, String paramString) {
    boolean bool;
    synchronized (paramElement) {
      bool = false;
      switch (this._lookingFor) {
        case 1:
          bool = (paramElement instanceof org.w3c.dom.html.HTMLAnchorElement && paramElement.getAttribute("name").length() > 0);
          break;
        case 2:
          bool = paramElement instanceof org.w3c.dom.html.HTMLFormElement;
          break;
        case 3:
          bool = paramElement instanceof org.w3c.dom.html.HTMLImageElement;
          break;
        case 4:
          bool = (paramElement instanceof org.w3c.dom.html.HTMLAppletElement || (paramElement instanceof org.w3c.dom.html.HTMLObjectElement && ("application/java".equals(paramElement.getAttribute("codetype")) || paramElement.getAttribute("classid").startsWith("java:"))));
          break;
        case 8:
          bool = paramElement instanceof HTMLFormControl;
          break;
        case 5:
          bool = ((paramElement instanceof org.w3c.dom.html.HTMLAnchorElement || paramElement instanceof org.w3c.dom.html.HTMLAreaElement) && paramElement.getAttribute("href").length() > 0);
          break;
        case -1:
          bool = paramElement instanceof org.w3c.dom.html.HTMLAreaElement;
          break;
        case 6:
          bool = paramElement instanceof org.w3c.dom.html.HTMLOptionElement;
          break;
        case 7:
          bool = paramElement instanceof org.w3c.dom.html.HTMLTableRowElement;
          break;
        case -2:
          bool = (paramElement instanceof org.w3c.dom.html.HTMLTableSectionElement && paramElement.getTagName().equals("TBODY"));
          break;
        case -3:
          bool = paramElement instanceof org.w3c.dom.html.HTMLTableCellElement;
          break;
      } 
      if (bool && paramString != null) {
        if (paramElement instanceof org.w3c.dom.html.HTMLAnchorElement && paramString.equals(paramElement.getAttribute("name")))
          return true; 
        bool = paramString.equals(paramElement.getAttribute("id"));
      } 
    } 
    return bool;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\HTMLCollectionImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */