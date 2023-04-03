/*     */ package org.opengis.filter.sort;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ public final class SortOrder extends CodeList<SortOrder> {
/*     */   private static final long serialVersionUID = 7840334200112859571L;
/*     */   
/*  41 */   private static final List<SortOrder> VALUES = new ArrayList<SortOrder>(2);
/*     */   
/*     */   @UML(identifier = "ASC", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_02059)
/*  51 */   public static final SortOrder ASCENDING = new SortOrder("ASCENDING", "ASC");
/*     */   
/*     */   @UML(identifier = "DESC", obligation = Obligation.CONDITIONAL, specification = Specification.OGC_02059)
/*  61 */   public static final SortOrder DESCENDING = new SortOrder("DESCENDING", "DESC");
/*     */   
/*     */   private final String sqlKeyword;
/*     */   
/*     */   private SortOrder(String name, String sqlKeyword) {
/*  76 */     super(name, VALUES);
/*  77 */     this.sqlKeyword = sqlKeyword;
/*     */   }
/*     */   
/*     */   private SortOrder(String name) {
/*  85 */     this(name, name);
/*     */   }
/*     */   
/*     */   public String toSQL() {
/*  97 */     return this.sqlKeyword;
/*     */   }
/*     */   
/*     */   public static SortOrder[] values() {
/* 106 */     synchronized (VALUES) {
/* 107 */       return VALUES.<SortOrder>toArray(new SortOrder[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SortOrder[] family() {
/* 115 */     return values();
/*     */   }
/*     */   
/*     */   public static SortOrder valueOf(String code) {
/* 126 */     return (SortOrder)valueOf(SortOrder.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\filter\sort\SortOrder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */