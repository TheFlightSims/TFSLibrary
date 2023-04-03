package org.apache.commons.configuration.tree;

public interface ConfigurationNodeVisitor {
  void visitBeforeChildren(ConfigurationNode paramConfigurationNode);
  
  void visitAfterChildren(ConfigurationNode paramConfigurationNode);
  
  boolean terminate();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\ConfigurationNodeVisitor.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */