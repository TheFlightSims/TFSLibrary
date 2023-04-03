/*     */ package org.geotools.referencing.datum;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.ImageDatum;
/*     */ import org.opengis.referencing.datum.PixelInCell;
/*     */ import org.opengis.util.CodeList;
/*     */ 
/*     */ public class DefaultImageDatum extends AbstractDatum implements ImageDatum {
/*     */   private static final long serialVersionUID = -4304193511244150936L;
/*     */   
/*     */   private final PixelInCell pixelInCell;
/*     */   
/*     */   public DefaultImageDatum(ImageDatum datum) {
/*  69 */     super((Datum)datum);
/*  70 */     this.pixelInCell = datum.getPixelInCell();
/*     */   }
/*     */   
/*     */   public DefaultImageDatum(String name, PixelInCell pixelInCell) {
/*  80 */     this(Collections.singletonMap("name", name), pixelInCell);
/*     */   }
/*     */   
/*     */   public DefaultImageDatum(Map<String, ?> properties, PixelInCell pixelInCell) {
/*  91 */     super(properties);
/*  92 */     this.pixelInCell = pixelInCell;
/*  93 */     ensureNonNull("pixelInCell", pixelInCell);
/*     */   }
/*     */   
/*     */   public PixelInCell getPixelInCell() {
/* 102 */     return this.pixelInCell;
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 115 */     if (object == this)
/* 116 */       return true; 
/* 118 */     if (super.equals(object, compareMetadata)) {
/* 119 */       DefaultImageDatum that = (DefaultImageDatum)object;
/* 120 */       return Utilities.equals(this.pixelInCell, that.pixelInCell);
/*     */     } 
/* 122 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 137 */     return super.hashCode() ^ this.pixelInCell.hashCode();
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 152 */     super.formatWKT(formatter);
/* 153 */     formatter.append((CodeList)this.pixelInCell);
/* 154 */     formatter.setInvalidWKT(ImageDatum.class);
/* 155 */     return "IMAGE_DATUM";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\datum\DefaultImageDatum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */