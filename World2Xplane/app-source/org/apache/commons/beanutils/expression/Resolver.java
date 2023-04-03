package org.apache.commons.beanutils.expression;

public interface Resolver {
  int getIndex(String paramString);
  
  String getKey(String paramString);
  
  String getProperty(String paramString);
  
  boolean hasNested(String paramString);
  
  boolean isIndexed(String paramString);
  
  boolean isMapped(String paramString);
  
  String next(String paramString);
  
  String remove(String paramString);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\beanutils\expression\Resolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */