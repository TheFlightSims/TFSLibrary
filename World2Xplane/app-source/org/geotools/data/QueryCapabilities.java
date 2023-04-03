/*     */ package org.geotools.data;
/*     */ 
/*     */ import org.opengis.filter.sort.SortBy;
/*     */ 
/*     */ public class QueryCapabilities {
/*     */   public boolean isOffsetSupported() {
/*  62 */     return false;
/*     */   }
/*     */   
/*     */   public boolean supportsSorting(SortBy[] sortAttributes) {
/*  83 */     return (sortAttributes == null || sortAttributes.length == 0);
/*     */   }
/*     */   
/*     */   public boolean isReliableFIDSupported() {
/*  96 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isUseProvidedFIDSupported() {
/* 109 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isJoiningSupported() {
/* 116 */     return false;
/*     */   }
/*     */   
/*     */   public boolean isVersionSupported() {
/* 123 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\data\QueryCapabilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */