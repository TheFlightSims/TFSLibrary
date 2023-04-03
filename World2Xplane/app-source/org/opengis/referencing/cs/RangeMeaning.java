/*     */ package org.opengis.referencing.cs;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "CS_RangeMeaning", specification = Specification.ISO_19111)
/*     */ public final class RangeMeaning extends CodeList<RangeMeaning> {
/*     */   private static final long serialVersionUID = -3525560558294789416L;
/*     */   
/*  47 */   private static final List<RangeMeaning> VALUES = new ArrayList<RangeMeaning>(2);
/*     */   
/*     */   @UML(identifier = "exact", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*  54 */   public static final RangeMeaning EXACT = new RangeMeaning("EXACT");
/*     */   
/*     */   @UML(identifier = "wraparound", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*  64 */   public static final RangeMeaning WRAPAROUND = new RangeMeaning("WRAPAROUND");
/*     */   
/*     */   private RangeMeaning(String name) {
/*  73 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static RangeMeaning[] values() {
/*  82 */     synchronized (VALUES) {
/*  83 */       return VALUES.<RangeMeaning>toArray(new RangeMeaning[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public RangeMeaning[] family() {
/*  91 */     return values();
/*     */   }
/*     */   
/*     */   public static RangeMeaning valueOf(String code) {
/* 102 */     return (RangeMeaning)valueOf(RangeMeaning.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\cs\RangeMeaning.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */