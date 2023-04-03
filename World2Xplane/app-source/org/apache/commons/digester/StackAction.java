package org.apache.commons.digester;

public interface StackAction {
  Object onPush(Digester paramDigester, String paramString, Object paramObject);
  
  Object onPop(Digester paramDigester, String paramString, Object paramObject);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\StackAction.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */