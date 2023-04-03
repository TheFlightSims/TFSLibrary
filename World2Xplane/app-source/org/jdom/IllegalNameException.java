/*     */ package org.jdom;
/*     */ 
/*     */ public class IllegalNameException extends IllegalArgumentException {
/*     */   private static final String CVS_ID = "@(#) $RCSfile: IllegalNameException.java,v $ $Revision: 1.14 $ $Date: 2007/11/10 05:28:59 $ $Name:  $";
/*     */   
/*     */   IllegalNameException(String name, String construct, String reason) {
/*  83 */     super("The name \"" + name + "\" is not legal for JDOM/XML " + construct + "s: " + reason + ".");
/*     */   }
/*     */   
/*     */   IllegalNameException(String name, String construct) {
/* 104 */     super("The name \"" + name + "\" is not legal for JDOM/XML " + construct + "s.");
/*     */   }
/*     */   
/*     */   public IllegalNameException(String reason) {
/* 119 */     super(reason);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\jdom\IllegalNameException.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */