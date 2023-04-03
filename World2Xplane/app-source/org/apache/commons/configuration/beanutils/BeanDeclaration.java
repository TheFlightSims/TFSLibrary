package org.apache.commons.configuration.beanutils;

import java.util.Map;

public interface BeanDeclaration {
  String getBeanFactoryName();
  
  Object getBeanFactoryParameter();
  
  String getBeanClassName();
  
  Map getBeanProperties();
  
  Map getNestedBeanDeclarations();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\configuration\beanutils\BeanDeclaration.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       1.1.3
 */