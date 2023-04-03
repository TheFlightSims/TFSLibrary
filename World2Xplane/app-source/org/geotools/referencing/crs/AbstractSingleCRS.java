/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ 
/*     */ public class AbstractSingleCRS extends AbstractCRS implements SingleCRS {
/*     */   private static final long serialVersionUID = 1815712797774273L;
/*     */   
/*     */   protected final Datum datum;
/*     */   
/*     */   public AbstractSingleCRS(SingleCRS crs) {
/*  88 */     super((CoordinateReferenceSystem)crs);
/*  89 */     this.datum = crs.getDatum();
/*     */   }
/*     */   
/*     */   public AbstractSingleCRS(Map<String, ?> properties, Datum datum, CoordinateSystem cs) {
/* 106 */     super(properties, cs);
/* 107 */     this.datum = datum;
/* 108 */     ensureNonNull("datum", datum);
/*     */   }
/*     */   
/*     */   public Datum getDatum() {
/* 117 */     return this.datum;
/*     */   }
/*     */   
/*     */   public int getDimension() {
/* 128 */     return this.coordinateSystem.getDimension();
/*     */   }
/*     */   
/*     */   public CoordinateSystemAxis getAxis(int dimension) throws IndexOutOfBoundsException {
/* 141 */     return this.coordinateSystem.getAxis(dimension);
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 156 */     if (super.equals(object, compareMetadata)) {
/* 157 */       AbstractSingleCRS that = (AbstractSingleCRS)object;
/* 158 */       return equals((IdentifiedObject)this.datum, (IdentifiedObject)that.datum, compareMetadata);
/*     */     } 
/* 160 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 176 */     return super.hashCode() ^ this.datum.hashCode();
/*     */   }
/*     */   
/*     */   final void formatDefaultWKT(Formatter formatter) {
/* 184 */     formatter.append((IdentifiedObject)this.datum);
/* 185 */     super.formatDefaultWKT(formatter);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\AbstractSingleCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */