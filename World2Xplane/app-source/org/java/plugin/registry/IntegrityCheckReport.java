/*     */ package org.java.plugin.registry;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Locale;
/*     */ 
/*     */ public interface IntegrityCheckReport {
/*     */   int countErrors();
/*     */   
/*     */   int countWarnings();
/*     */   
/*     */   Collection<ReportItem> getItems();
/*     */   
/*     */   public enum Severity {
/*  59 */     ERROR, WARNING, INFO;
/*     */   }
/*     */   
/*     */   public enum Error {
/*  81 */     NO_ERROR, CHECKER_FAULT, MANIFEST_PROCESSING_FAILED, UNSATISFIED_PREREQUISITE, BAD_LIBRARY, INVALID_EXTENSION_POINT, INVALID_EXTENSION;
/*     */   }
/*     */   
/*     */   public static interface ReportItem {
/*     */     IntegrityCheckReport.Severity getSeverity();
/*     */     
/*     */     Identity getSource();
/*     */     
/*     */     IntegrityCheckReport.Error getCode();
/*     */     
/*     */     String getMessage();
/*     */     
/*     */     String getMessage(Locale param1Locale);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\java\plugin\registry\IntegrityCheckReport.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */