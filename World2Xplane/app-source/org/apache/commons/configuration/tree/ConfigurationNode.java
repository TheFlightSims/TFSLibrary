package org.apache.commons.configuration.tree;

import java.util.List;

public interface ConfigurationNode {
  String getName();
  
  void setName(String paramString);
  
  Object getValue();
  
  void setValue(Object paramObject);
  
  Object getReference();
  
  void setReference(Object paramObject);
  
  ConfigurationNode getParentNode();
  
  void setParentNode(ConfigurationNode paramConfigurationNode);
  
  void addChild(ConfigurationNode paramConfigurationNode);
  
  List getChildren();
  
  int getChildrenCount();
  
  List getChildren(String paramString);
  
  int getChildrenCount(String paramString);
  
  ConfigurationNode getChild(int paramInt);
  
  boolean removeChild(ConfigurationNode paramConfigurationNode);
  
  boolean removeChild(String paramString);
  
  void removeChildren();
  
  boolean isAttribute();
  
  void setAttribute(boolean paramBoolean);
  
  List getAttributes();
  
  int getAttributeCount();
  
  List getAttributes(String paramString);
  
  int getAttributeCount(String paramString);
  
  ConfigurationNode getAttribute(int paramInt);
  
  boolean removeAttribute(ConfigurationNode paramConfigurationNode);
  
  boolean removeAttribute(String paramString);
  
  void removeAttributes();
  
  void addAttribute(ConfigurationNode paramConfigurationNode);
  
  boolean isDefined();
  
  void visit(ConfigurationNodeVisitor paramConfigurationNodeVisitor);
  
  Object clone();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\ConfigurationNode.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */