/*     */ package org.apache.commons.lang;
/*     */ 
/*     */ public class IllegalClassException extends IllegalArgumentException {
/*     */   private static final long serialVersionUID = 8063272569377254819L;
/*     */   
/*     */   public IllegalClassException(Class expected, Object actual) {
/*  62 */     super("Expected: " + safeGetClassName(expected) + ", actual: " + ((actual == null) ? "null" : actual.getClass().getName()));
/*     */   }
/*     */   
/*     */   public IllegalClassException(Class expected, Class actual) {
/*  76 */     super("Expected: " + safeGetClassName(expected) + ", actual: " + safeGetClassName(actual));
/*     */   }
/*     */   
/*     */   public IllegalClassException(String message) {
/*  89 */     super(message);
/*     */   }
/*     */   
/*     */   private static final String safeGetClassName(Class cls) {
/* 100 */     return (cls == null) ? null : cls.getName();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\apache\commons\lang\IllegalClassException.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */