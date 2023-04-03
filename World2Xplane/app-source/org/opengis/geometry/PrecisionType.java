/*     */ package org.opengis.geometry;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ public final class PrecisionType extends CodeList<PrecisionType> {
/*     */   private static final long serialVersionUID = -2771887290382853282L;
/*     */   
/*     */   private final boolean isFloating;
/*     */   
/*  42 */   private static final List<PrecisionType> VALUES = new ArrayList<PrecisionType>(3);
/*     */   
/*  47 */   public static final PrecisionType FIXED = new PrecisionType("FIXED", false);
/*     */   
/*  53 */   public static final PrecisionType DOUBLE = new PrecisionType("DOUBLE", true);
/*     */   
/*  59 */   public static final PrecisionType FLOAT = new PrecisionType("FLOAT", true);
/*     */   
/*     */   private PrecisionType(String name, boolean isFloating) {
/*  70 */     super(name, VALUES);
/*  71 */     this.isFloating = isFloating;
/*     */   }
/*     */   
/*     */   private PrecisionType(String name) {
/*  79 */     this(name, true);
/*     */   }
/*     */   
/*     */   public boolean isFloating() {
/*  89 */     return this.isFloating;
/*     */   }
/*     */   
/*     */   public static PrecisionType[] values() {
/*  98 */     synchronized (VALUES) {
/*  99 */       return VALUES.<PrecisionType>toArray(new PrecisionType[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public PrecisionType[] family() {
/* 107 */     return values();
/*     */   }
/*     */   
/*     */   public static PrecisionType valueOf(String code) {
/* 118 */     return (PrecisionType)valueOf(PrecisionType.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\geometry\PrecisionType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */