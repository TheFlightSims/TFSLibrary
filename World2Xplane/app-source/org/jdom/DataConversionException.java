/*    */ package org.jdom;
/*    */ 
/*    */ public class DataConversionException extends JDOMException {
/*    */   private static final String CVS_ID = "@(#) $RCSfile: DataConversionException.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:28:58 $ $Name:  $";
/*    */   
/*    */   public DataConversionException(String name, String dataType) {
/* 80 */     super("The XML construct " + name + " could not be converted to a " + dataType);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\DataConversionException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */