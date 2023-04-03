/*    */ package org.apache.commons.math3.exception.util;
/*    */ 
/*    */ import java.util.Locale;
/*    */ 
/*    */ public class DummyLocalizable implements Localizable {
/*    */   private static final long serialVersionUID = 8843275624471387299L;
/*    */   
/*    */   private final String source;
/*    */   
/*    */   public DummyLocalizable(String source) {
/* 39 */     this.source = source;
/*    */   }
/*    */   
/*    */   public String getSourceString() {
/* 44 */     return this.source;
/*    */   }
/*    */   
/*    */   public String getLocalizedString(Locale locale) {
/* 49 */     return this.source;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 55 */     return this.source;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\math3\exceptio\\util\DummyLocalizable.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */