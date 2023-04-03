package org.apache.html.dom;

import org.apache.xerces.dom.DeepNodeListImpl;
import org.apache.xerces.dom.ElementImpl;
import org.apache.xerces.dom.NodeImpl;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class NameNodeListImpl extends DeepNodeListImpl implements NodeList {
  public NameNodeListImpl(NodeImpl paramNodeImpl, String paramString) {
    super(paramNodeImpl, paramString);
  }
  
  protected Node nextMatchingElementAfter(Node paramNode) {
    while (paramNode != null) {
      if (paramNode.hasChildNodes()) {
        paramNode = paramNode.getFirstChild();
      } else {
        Node node;
        if (paramNode != this.rootNode && null != (node = paramNode.getNextSibling())) {
          paramNode = node;
        } else {
          node = null;
          while (paramNode != this.rootNode) {
            node = paramNode.getNextSibling();
            if (node != null)
              break; 
            paramNode = paramNode.getParentNode();
          } 
          paramNode = node;
        } 
      } 
      if (paramNode != this.rootNode && paramNode != null && paramNode.getNodeType() == 1) {
        String str = ((ElementImpl)paramNode).getAttribute("name");
        if (str.equals("*") || str.equals(this.tagName))
          return paramNode; 
      } 
    } 
    return null;
  }
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\html\dom\NameNodeListImpl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */