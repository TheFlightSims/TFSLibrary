/*    */ package org.opengis.referencing;
/*    */ 
/*    */ public class NoSuchIdentifierException extends FactoryException {
/*    */   private static final long serialVersionUID = -6846799994429345902L;
/*    */   
/*    */   private final String identifier;
/*    */   
/*    */   public NoSuchIdentifierException(String message, String identifier) {
/* 45 */     super(message);
/* 46 */     this.identifier = identifier;
/*    */   }
/*    */   
/*    */   public String getIdentifierCode() {
/* 55 */     return this.identifier;
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\NoSuchIdentifierException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */