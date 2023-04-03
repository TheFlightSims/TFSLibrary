/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.RangeMeaning;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.datum.PixelInCell;
/*     */ import org.opengis.referencing.datum.VerticalDatumType;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ 
/*     */ final class URI_Type {
/*  47 */   private static final URI_Type[] TYPES = new URI_Type[] { 
/*  47 */       new URI_Type("crs", CRSAuthorityFactory.class), new URI_Type("datum", DatumAuthorityFactory.class), new URI_Type("meridian", DatumAuthorityFactory.class), new URI_Type("ellipsoid", DatumAuthorityFactory.class), new URI_Type("cs", CSAuthorityFactory.class), new URI_Type("axis", CSAuthorityFactory.class), new URI_Type("coordinateOperation", CoordinateOperationAuthorityFactory.class), new URI_Type("coordinate-operation", CoordinateOperationAuthorityFactory.class), new URI_Type("method", CoordinateOperationAuthorityFactory.class), new URI_Type("parameter", CoordinateOperationAuthorityFactory.class), 
/*  47 */       new URI_Type("group", CoordinateOperationAuthorityFactory.class), new URI_Type("verticalDatumType", VerticalDatumType.class), new URI_Type("vertical-datum-type", VerticalDatumType.class), new URI_Type("pixelInCell", PixelInCell.class), new URI_Type("pixel-in-cell", PixelInCell.class), new URI_Type("rangeMeaning", RangeMeaning.class), new URI_Type("range-meaning", RangeMeaning.class), new URI_Type("axisDirection", AxisDirection.class), new URI_Type("axis-direction", AxisDirection.class), new URI_Type("uom", CSAuthorityFactory.class) };
/*     */   
/*     */   public final String name;
/*     */   
/*     */   public final Class<?> type;
/*     */   
/*     */   private URI_Type(String name, Class<?> type) {
/*  85 */     this.name = name;
/*  86 */     this.type = type;
/*     */   }
/*     */   
/*     */   public static URI_Type get(String name) {
/*  93 */     for (int i = 0; i < TYPES.length; i++) {
/*  94 */       URI_Type candidate = TYPES[i];
/*  95 */       if (name.equalsIgnoreCase(candidate.name))
/*  96 */         return candidate; 
/*     */     } 
/*  99 */     return null;
/*     */   }
/*     */   
/*     */   public boolean isInstance(AuthorityFactory factory) {
/* 106 */     return this.type.isInstance(factory);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 114 */     return this.name;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\URI_Type.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */