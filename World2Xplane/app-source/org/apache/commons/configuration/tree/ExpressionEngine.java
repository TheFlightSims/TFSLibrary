package org.apache.commons.configuration.tree;

import java.util.List;

public interface ExpressionEngine {
  List query(ConfigurationNode paramConfigurationNode, String paramString);
  
  String nodeKey(ConfigurationNode paramConfigurationNode, String paramString);
  
  NodeAddData prepareAdd(ConfigurationNode paramConfigurationNode, String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\tree\ExpressionEngine.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */