package org.apache.xerces.util;

import java.lang.reflect.Method;
import java.util.Hashtable;
import org.apache.xerces.dom.AttrImpl;
import org.apache.xerces.dom.NodeImpl;
import org.apache.xerces.impl.xs.opti.ElementImpl;
import org.apache.xerces.impl.xs.opti.NodeImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.EntityReference;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;
import org.w3c.dom.ls.LSException;

public class DOMUtil {
  public static void copyInto(Node paramNode1, Node paramNode2) throws DOMException {
    Document document = paramNode2.getOwnerDocument();
    boolean bool = document instanceof org.apache.xerces.dom.DocumentImpl;
    Node node1 = paramNode1;
    Node node2 = paramNode1;
    Node node3 = paramNode1;
    while (node3 != null) {
      Comment comment;
      Element element1;
      EntityReference entityReference;
      ProcessingInstruction processingInstruction;
      Text text;
      Element element2;
      NamedNodeMap namedNodeMap;
      int i;
      byte b;
      CDATASection cDATASection = null;
      short s = node3.getNodeType();
      switch (s) {
        case 4:
          cDATASection = document.createCDATASection(node3.getNodeValue());
          break;
        case 8:
          comment = document.createComment(node3.getNodeValue());
          break;
        case 1:
          element2 = document.createElement(node3.getNodeName());
          element1 = element2;
          namedNodeMap = node3.getAttributes();
          i = namedNodeMap.getLength();
          for (b = 0; b < i; b++) {
            Attr attr = (Attr)namedNodeMap.item(b);
            String str1 = attr.getNodeName();
            String str2 = attr.getNodeValue();
            element2.setAttribute(str1, str2);
            if (bool && !attr.getSpecified())
              ((AttrImpl)element2.getAttributeNode(str1)).setSpecified(false); 
          } 
          break;
        case 5:
          entityReference = document.createEntityReference(node3.getNodeName());
          break;
        case 7:
          processingInstruction = document.createProcessingInstruction(node3.getNodeName(), node3.getNodeValue());
          break;
        case 3:
          text = document.createTextNode(node3.getNodeValue());
          break;
        default:
          throw new IllegalArgumentException("can't copy node type, " + s + " (" + text.getNodeName() + ')');
      } 
      paramNode2.appendChild(text);
      if (node3.hasChildNodes()) {
        node2 = node3;
        node3 = node3.getFirstChild();
        paramNode2 = text;
        continue;
      } 
      node3 = node3.getNextSibling();
      while (node3 == null && node2 != node1) {
        node3 = node2.getNextSibling();
        node2 = node2.getParentNode();
        paramNode2 = paramNode2.getParentNode();
      } 
    } 
  }
  
  public static Element getFirstChildElement(Node paramNode) {
    for (Node node = paramNode.getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1)
        return (Element)node; 
    } 
    return null;
  }
  
  public static Element getFirstVisibleChildElement(Node paramNode) {
    for (Node node = paramNode.getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1 && !isHidden(node))
        return (Element)node; 
    } 
    return null;
  }
  
  public static Element getFirstVisibleChildElement(Node paramNode, Hashtable paramHashtable) {
    for (Node node = paramNode.getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1 && !isHidden(node, paramHashtable))
        return (Element)node; 
    } 
    return null;
  }
  
  public static Element getLastChildElement(Node paramNode) {
    for (Node node = paramNode.getLastChild(); node != null; node = node.getPreviousSibling()) {
      if (node.getNodeType() == 1)
        return (Element)node; 
    } 
    return null;
  }
  
  public static Element getLastVisibleChildElement(Node paramNode) {
    for (Node node = paramNode.getLastChild(); node != null; node = node.getPreviousSibling()) {
      if (node.getNodeType() == 1 && !isHidden(node))
        return (Element)node; 
    } 
    return null;
  }
  
  public static Element getLastVisibleChildElement(Node paramNode, Hashtable paramHashtable) {
    for (Node node = paramNode.getLastChild(); node != null; node = node.getPreviousSibling()) {
      if (node.getNodeType() == 1 && !isHidden(node, paramHashtable))
        return (Element)node; 
    } 
    return null;
  }
  
  public static Element getNextSiblingElement(Node paramNode) {
    for (Node node = paramNode.getNextSibling(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1)
        return (Element)node; 
    } 
    return null;
  }
  
  public static Element getNextVisibleSiblingElement(Node paramNode) {
    for (Node node = paramNode.getNextSibling(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1 && !isHidden(node))
        return (Element)node; 
    } 
    return null;
  }
  
  public static Element getNextVisibleSiblingElement(Node paramNode, Hashtable paramHashtable) {
    for (Node node = paramNode.getNextSibling(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1 && !isHidden(node, paramHashtable))
        return (Element)node; 
    } 
    return null;
  }
  
  public static void setHidden(Node paramNode) {
    if (paramNode instanceof NodeImpl) {
      ((NodeImpl)paramNode).setReadOnly(true, false);
    } else if (paramNode instanceof NodeImpl) {
      ((NodeImpl)paramNode).setReadOnly(true, false);
    } 
  }
  
  public static void setHidden(Node paramNode, Hashtable paramHashtable) {
    if (paramNode instanceof NodeImpl) {
      ((NodeImpl)paramNode).setReadOnly(true, false);
    } else {
      paramHashtable.put(paramNode, "");
    } 
  }
  
  public static void setVisible(Node paramNode) {
    if (paramNode instanceof NodeImpl) {
      ((NodeImpl)paramNode).setReadOnly(false, false);
    } else if (paramNode instanceof NodeImpl) {
      ((NodeImpl)paramNode).setReadOnly(false, false);
    } 
  }
  
  public static void setVisible(Node paramNode, Hashtable paramHashtable) {
    if (paramNode instanceof NodeImpl) {
      ((NodeImpl)paramNode).setReadOnly(false, false);
    } else {
      paramHashtable.remove(paramNode);
    } 
  }
  
  public static boolean isHidden(Node paramNode) {
    return (paramNode instanceof NodeImpl) ? ((NodeImpl)paramNode).getReadOnly() : ((paramNode instanceof NodeImpl) ? ((NodeImpl)paramNode).getReadOnly() : false);
  }
  
  public static boolean isHidden(Node paramNode, Hashtable paramHashtable) {
    return (paramNode instanceof NodeImpl) ? ((NodeImpl)paramNode).getReadOnly() : paramHashtable.containsKey(paramNode);
  }
  
  public static Element getFirstChildElement(Node paramNode, String paramString) {
    for (Node node = paramNode.getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1 && node.getNodeName().equals(paramString))
        return (Element)node; 
    } 
    return null;
  }
  
  public static Element getLastChildElement(Node paramNode, String paramString) {
    for (Node node = paramNode.getLastChild(); node != null; node = node.getPreviousSibling()) {
      if (node.getNodeType() == 1 && node.getNodeName().equals(paramString))
        return (Element)node; 
    } 
    return null;
  }
  
  public static Element getNextSiblingElement(Node paramNode, String paramString) {
    for (Node node = paramNode.getNextSibling(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1 && node.getNodeName().equals(paramString))
        return (Element)node; 
    } 
    return null;
  }
  
  public static Element getFirstChildElementNS(Node paramNode, String paramString1, String paramString2) {
    for (Node node = paramNode.getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1) {
        String str = node.getNamespaceURI();
        if (str != null && str.equals(paramString1) && node.getLocalName().equals(paramString2))
          return (Element)node; 
      } 
    } 
    return null;
  }
  
  public static Element getLastChildElementNS(Node paramNode, String paramString1, String paramString2) {
    for (Node node = paramNode.getLastChild(); node != null; node = node.getPreviousSibling()) {
      if (node.getNodeType() == 1) {
        String str = node.getNamespaceURI();
        if (str != null && str.equals(paramString1) && node.getLocalName().equals(paramString2))
          return (Element)node; 
      } 
    } 
    return null;
  }
  
  public static Element getNextSiblingElementNS(Node paramNode, String paramString1, String paramString2) {
    for (Node node = paramNode.getNextSibling(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1) {
        String str = node.getNamespaceURI();
        if (str != null && str.equals(paramString1) && node.getLocalName().equals(paramString2))
          return (Element)node; 
      } 
    } 
    return null;
  }
  
  public static Element getFirstChildElement(Node paramNode, String[] paramArrayOfString) {
    for (Node node = paramNode.getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1)
        for (byte b = 0; b < paramArrayOfString.length; b++) {
          if (node.getNodeName().equals(paramArrayOfString[b]))
            return (Element)node; 
        }  
    } 
    return null;
  }
  
  public static Element getLastChildElement(Node paramNode, String[] paramArrayOfString) {
    for (Node node = paramNode.getLastChild(); node != null; node = node.getPreviousSibling()) {
      if (node.getNodeType() == 1)
        for (byte b = 0; b < paramArrayOfString.length; b++) {
          if (node.getNodeName().equals(paramArrayOfString[b]))
            return (Element)node; 
        }  
    } 
    return null;
  }
  
  public static Element getNextSiblingElement(Node paramNode, String[] paramArrayOfString) {
    for (Node node = paramNode.getNextSibling(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1)
        for (byte b = 0; b < paramArrayOfString.length; b++) {
          if (node.getNodeName().equals(paramArrayOfString[b]))
            return (Element)node; 
        }  
    } 
    return null;
  }
  
  public static Element getFirstChildElementNS(Node paramNode, String[][] paramArrayOfString) {
    for (Node node = paramNode.getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1)
        for (byte b = 0; b < paramArrayOfString.length; b++) {
          String str = node.getNamespaceURI();
          if (str != null && str.equals(paramArrayOfString[b][0]) && node.getLocalName().equals(paramArrayOfString[b][1]))
            return (Element)node; 
        }  
    } 
    return null;
  }
  
  public static Element getLastChildElementNS(Node paramNode, String[][] paramArrayOfString) {
    for (Node node = paramNode.getLastChild(); node != null; node = node.getPreviousSibling()) {
      if (node.getNodeType() == 1)
        for (byte b = 0; b < paramArrayOfString.length; b++) {
          String str = node.getNamespaceURI();
          if (str != null && str.equals(paramArrayOfString[b][0]) && node.getLocalName().equals(paramArrayOfString[b][1]))
            return (Element)node; 
        }  
    } 
    return null;
  }
  
  public static Element getNextSiblingElementNS(Node paramNode, String[][] paramArrayOfString) {
    for (Node node = paramNode.getNextSibling(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1)
        for (byte b = 0; b < paramArrayOfString.length; b++) {
          String str = node.getNamespaceURI();
          if (str != null && str.equals(paramArrayOfString[b][0]) && node.getLocalName().equals(paramArrayOfString[b][1]))
            return (Element)node; 
        }  
    } 
    return null;
  }
  
  public static Element getFirstChildElement(Node paramNode, String paramString1, String paramString2, String paramString3) {
    for (Node node = paramNode.getFirstChild(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1) {
        Element element = (Element)node;
        if (element.getNodeName().equals(paramString1) && element.getAttribute(paramString2).equals(paramString3))
          return element; 
      } 
    } 
    return null;
  }
  
  public static Element getLastChildElement(Node paramNode, String paramString1, String paramString2, String paramString3) {
    for (Node node = paramNode.getLastChild(); node != null; node = node.getPreviousSibling()) {
      if (node.getNodeType() == 1) {
        Element element = (Element)node;
        if (element.getNodeName().equals(paramString1) && element.getAttribute(paramString2).equals(paramString3))
          return element; 
      } 
    } 
    return null;
  }
  
  public static Element getNextSiblingElement(Node paramNode, String paramString1, String paramString2, String paramString3) {
    for (Node node = paramNode.getNextSibling(); node != null; node = node.getNextSibling()) {
      if (node.getNodeType() == 1) {
        Element element = (Element)node;
        if (element.getNodeName().equals(paramString1) && element.getAttribute(paramString2).equals(paramString3))
          return element; 
      } 
    } 
    return null;
  }
  
  public static String getChildText(Node paramNode) {
    if (paramNode == null)
      return null; 
    StringBuffer stringBuffer = new StringBuffer();
    for (Node node = paramNode.getFirstChild(); node != null; node = node.getNextSibling()) {
      short s = node.getNodeType();
      if (s == 3) {
        stringBuffer.append(node.getNodeValue());
      } else if (s == 4) {
        stringBuffer.append(getChildText(node));
      } 
    } 
    return stringBuffer.toString();
  }
  
  public static String getName(Node paramNode) {
    return paramNode.getNodeName();
  }
  
  public static String getLocalName(Node paramNode) {
    String str = paramNode.getLocalName();
    return (str != null) ? str : paramNode.getNodeName();
  }
  
  public static Element getParent(Element paramElement) {
    Node node = paramElement.getParentNode();
    return (node instanceof Element) ? (Element)node : null;
  }
  
  public static Document getDocument(Node paramNode) {
    return paramNode.getOwnerDocument();
  }
  
  public static Element getRoot(Document paramDocument) {
    return paramDocument.getDocumentElement();
  }
  
  public static Attr getAttr(Element paramElement, String paramString) {
    return paramElement.getAttributeNode(paramString);
  }
  
  public static Attr getAttrNS(Element paramElement, String paramString1, String paramString2) {
    return paramElement.getAttributeNodeNS(paramString1, paramString2);
  }
  
  public static Attr[] getAttrs(Element paramElement) {
    NamedNodeMap namedNodeMap = paramElement.getAttributes();
    Attr[] arrayOfAttr = new Attr[namedNodeMap.getLength()];
    for (byte b = 0; b < namedNodeMap.getLength(); b++)
      arrayOfAttr[b] = (Attr)namedNodeMap.item(b); 
    return arrayOfAttr;
  }
  
  public static String getValue(Attr paramAttr) {
    return paramAttr.getValue();
  }
  
  public static String getAttrValue(Element paramElement, String paramString) {
    return paramElement.getAttribute(paramString);
  }
  
  public static String getAttrValueNS(Element paramElement, String paramString1, String paramString2) {
    return paramElement.getAttributeNS(paramString1, paramString2);
  }
  
  public static String getPrefix(Node paramNode) {
    return paramNode.getPrefix();
  }
  
  public static String getNamespaceURI(Node paramNode) {
    return paramNode.getNamespaceURI();
  }
  
  public static String getAnnotation(Node paramNode) {
    return (paramNode instanceof ElementImpl) ? ((ElementImpl)paramNode).getAnnotation() : null;
  }
  
  public static String getSyntheticAnnotation(Node paramNode) {
    return (paramNode instanceof ElementImpl) ? ((ElementImpl)paramNode).getSyntheticAnnotation() : null;
  }
  
  public static DOMException createDOMException(short paramShort, Throwable paramThrowable) {
    DOMException dOMException = new DOMException(paramShort, (paramThrowable != null) ? paramThrowable.getMessage() : null);
    if (paramThrowable != null && ThrowableMethods.fgThrowableMethodsAvailable)
      try {
        ThrowableMethods.fgThrowableInitCauseMethod.invoke(dOMException, new Object[] { paramThrowable });
      } catch (Exception exception) {} 
    return dOMException;
  }
  
  public static LSException createLSException(short paramShort, Throwable paramThrowable) {
    LSException lSException = new LSException(paramShort, (paramThrowable != null) ? paramThrowable.getMessage() : null);
    if (paramThrowable != null && ThrowableMethods.fgThrowableMethodsAvailable)
      try {
        ThrowableMethods.fgThrowableInitCauseMethod.invoke(lSException, new Object[] { paramThrowable });
      } catch (Exception exception) {} 
    return lSException;
  }
  
  static class ThrowableMethods {
    private static Method fgThrowableInitCauseMethod = null;
    
    private static boolean fgThrowableMethodsAvailable = false;
    
    static {
      try {
        fgThrowableInitCauseMethod = Throwable.class.getMethod("initCause", new Class[] { Throwable.class });
        fgThrowableMethodsAvailable = true;
      } catch (Exception exception) {
        fgThrowableInitCauseMethod = null;
        fgThrowableMethodsAvailable = false;
      } 
    }
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\xerce\\util\DOMUtil.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */