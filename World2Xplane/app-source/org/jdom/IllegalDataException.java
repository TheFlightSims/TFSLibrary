/*     */ package org.jdom;
/*     */ 
/*     */ public class IllegalDataException extends IllegalArgumentException {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: IllegalDataException.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   IllegalDataException(String data, String construct, String reason) {
/*  81 */     super("The data \"" + data + "\" is not legal for a JDOM " + construct + ": " + reason + ".");
/*     */   }
/*     */   
/*     */   IllegalDataException(String data, String construct) {
/* 101 */     super("The data \"" + data + "\" is not legal for a JDOM " + construct + ".");
/*     */   }
/*     */   
/*     */   public IllegalDataException(String reason) {
/* 116 */     super(reason);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\IllegalDataException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */