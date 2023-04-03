/*    */ package org.apache.commons.digester.xmlrules;
/*    */ 
/*    */ public class CircularIncludeException extends XmlLoadException {
/*    */   public CircularIncludeException(String fileName) {
/* 37 */     super("Circular file inclusion detected for file: " + fileName);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\digester\xmlrules\CircularIncludeException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */