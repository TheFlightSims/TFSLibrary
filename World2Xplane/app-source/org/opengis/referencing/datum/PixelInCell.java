/*     */ package org.opengis.referencing.datum;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.opengis.annotation.Obligation;
/*     */ import org.opengis.annotation.Specification;
/*     */ import org.opengis.annotation.UML;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ @UML(identifier = "CD_PixelInCell", specification = Specification.ISO_19111)
/*     */ public final class PixelInCell extends CodeList<PixelInCell> {
/*     */   private static final long serialVersionUID = 2857889370030758462L;
/*     */   
/*  45 */   private static final List<PixelInCell> VALUES = new ArrayList<PixelInCell>(2);
/*     */   
/*     */   @UML(identifier = "cell center", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*  53 */   public static final PixelInCell CELL_CENTER = new PixelInCell("CELL_CENTER");
/*     */   
/*     */   @UML(identifier = "cell corner", obligation = Obligation.CONDITIONAL, specification = Specification.ISO_19111)
/*  62 */   public static final PixelInCell CELL_CORNER = new PixelInCell("CELL_CORNER");
/*     */   
/*     */   private PixelInCell(String name) {
/*  71 */     super(name, VALUES);
/*     */   }
/*     */   
/*     */   public static PixelInCell[] values() {
/*  80 */     synchronized (VALUES) {
/*  81 */       return VALUES.<PixelInCell>toArray(new PixelInCell[VALUES.size()]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public PixelInCell[] family() {
/*  89 */     return values();
/*     */   }
/*     */   
/*     */   public static PixelInCell valueOf(String code) {
/* 100 */     return (PixelInCell)valueOf(PixelInCell.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\opengis\referencing\datum\PixelInCell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */