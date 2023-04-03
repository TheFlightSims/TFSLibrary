/*     */ package org.geotools.referencing.crs;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.opengis.referencing.crs.ImageCRS;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.cs.AffineCS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.ImageDatum;
/*     */ 
/*     */ public class DefaultImageCRS extends AbstractSingleCRS implements ImageCRS {
/*     */   private static final long serialVersionUID = 7312452786096397847L;
/*     */   
/*     */   public DefaultImageCRS(ImageCRS crs) {
/*  69 */     super((SingleCRS)crs);
/*     */   }
/*     */   
/*     */   public DefaultImageCRS(String name, ImageDatum datum, AffineCS cs) {
/*  83 */     this(Collections.singletonMap("name", name), datum, cs);
/*     */   }
/*     */   
/*     */   public DefaultImageCRS(Map<String, ?> properties, ImageDatum datum, AffineCS cs) {
/*  98 */     super(properties, (Datum)datum, (CoordinateSystem)cs);
/*     */   }
/*     */   
/*     */   public AffineCS getCoordinateSystem() {
/* 106 */     return (AffineCS)super.getCoordinateSystem();
/*     */   }
/*     */   
/*     */   public ImageDatum getDatum() {
/* 114 */     return (ImageDatum)super.getDatum();
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 125 */     return 0xE0175E17 ^ super.hashCode();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\crs\DefaultImageCRS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */