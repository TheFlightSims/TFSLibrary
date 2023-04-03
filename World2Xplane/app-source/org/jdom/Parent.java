package org.jdom;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.jdom.filter.Filter;

public interface Parent extends Cloneable, Serializable {
  int getContentSize();
  
  int indexOf(Content paramContent);
  
  List cloneContent();
  
  Content getContent(int paramInt);
  
  List getContent();
  
  List getContent(Filter paramFilter);
  
  List removeContent();
  
  List removeContent(Filter paramFilter);
  
  boolean removeContent(Content paramContent);
  
  Content removeContent(int paramInt);
  
  Object clone();
  
  Iterator getDescendants();
  
  Iterator getDescendants(Filter paramFilter);
  
  Parent getParent();
  
  Document getDocument();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\Parent.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */