/*     */ package org.geotools.referencing.factory.epsg;
/*     */ 
/*     */ final class TableInfo {
/*     */   public final Class<?> type;
/*     */   
/*     */   public final String table;
/*     */   
/*     */   public final String codeColumn;
/*     */   
/*     */   public final String nameColumn;
/*     */   
/*     */   public final String typeColumn;
/*     */   
/*     */   public final Class<?>[] subTypes;
/*     */   
/*     */   public final String[] typeNames;
/*     */   
/*     */   TableInfo(Class<?> type, String table, String codeColumn, String nameColumn) {
/*  74 */     this(type, table, codeColumn, nameColumn, null, null, null);
/*     */   }
/*     */   
/*     */   TableInfo(Class<?> type, String table, String codeColumn, String nameColumn, String typeColumn, Class<?>[] subTypes, String[] typeNames) {
/*  84 */     this.type = type;
/*  85 */     this.table = table;
/*  86 */     this.codeColumn = codeColumn;
/*  87 */     this.nameColumn = nameColumn;
/*  88 */     this.typeColumn = typeColumn;
/*  89 */     this.subTypes = subTypes;
/*  90 */     this.typeNames = typeNames;
/*     */   }
/*     */   
/*     */   public boolean isTypeOf(Class<?> kind) {
/* 110 */     return (this.type.isAssignableFrom(kind) || kind.isAssignableFrom(this.type));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\TableInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */