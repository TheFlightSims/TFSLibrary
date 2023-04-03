/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.text.FieldPosition;
/*     */ import java.util.Locale;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.geometry.GeneralEnvelope;
/*     */ import org.geotools.measure.AngleFormat;
/*     */ import org.geotools.measure.Latitude;
/*     */ import org.geotools.measure.Longitude;
/*     */ import org.geotools.metadata.iso.extent.GeographicBoundingBoxImpl;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.crs.DefaultGeographicCRS;
/*     */ import org.geotools.referencing.operation.TransformPathNotFoundException;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.geometry.Envelope;
/*     */ import org.opengis.metadata.extent.GeographicBoundingBox;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.GeographicCRS;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public final class BoundingBoxes {
/*  64 */   private static final Hints LENIENT = new Hints((RenderingHints.Key)Hints.LENIENT_DATUM_SHIFT, Boolean.TRUE);
/*     */   
/*     */   public static void copy(Envelope envelope, GeographicBoundingBoxImpl box) throws TransformException {
/*     */     GeneralEnvelope generalEnvelope;
/*  83 */     CoordinateReferenceSystem crs = envelope.getCoordinateReferenceSystem();
/*  84 */     if (crs != null) {
/*  85 */       GeographicCRS standardCRS = CRSUtilities.getStandardGeographicCRS2D(crs);
/*  86 */       if (!startsWith(crs, (CoordinateReferenceSystem)standardCRS) && !startsWith(crs, (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84) && !startsWith(crs, (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84_3D)) {
/*     */         CoordinateOperation operation;
/*  92 */         CoordinateOperationFactory factory = ReferencingFactoryFinder.getCoordinateOperationFactory(LENIENT);
/*     */         try {
/*  94 */           operation = factory.createOperation(crs, (CoordinateReferenceSystem)standardCRS);
/*  95 */         } catch (FactoryException exception) {
/*  96 */           throw new TransformPathNotFoundException(Errors.format(33, exception));
/*     */         } 
/*  99 */         generalEnvelope = CRS.transform(operation, envelope);
/*     */       } 
/*     */     } 
/* 102 */     box.setWestBoundLongitude(generalEnvelope.getMinimum(0));
/* 103 */     box.setEastBoundLongitude(generalEnvelope.getMaximum(0));
/* 104 */     box.setSouthBoundLatitude(generalEnvelope.getMinimum(1));
/* 105 */     box.setNorthBoundLatitude(generalEnvelope.getMaximum(1));
/*     */   }
/*     */   
/*     */   private static final boolean startsWith(CoordinateReferenceSystem crs, CoordinateReferenceSystem head) {
/* 114 */     int dimension = head.getCoordinateSystem().getDimension();
/* 115 */     return (crs.getCoordinateSystem().getDimension() >= dimension && CRS.equalsIgnoreMetadata(CRSUtilities.getSubCRS(crs, 0, dimension), head));
/*     */   }
/*     */   
/*     */   public static String toString(GeographicBoundingBox box, String pattern, Locale locale) {
/* 131 */     AngleFormat format = (locale != null) ? new AngleFormat(pattern, locale) : new AngleFormat(pattern);
/* 132 */     FieldPosition pos = new FieldPosition(0);
/* 133 */     StringBuffer buffer = new StringBuffer();
/* 134 */     format.format(new Latitude(box.getNorthBoundLatitude()), buffer, pos).append(", ");
/* 135 */     format.format(new Longitude(box.getWestBoundLongitude()), buffer, pos).append(" - ");
/* 136 */     format.format(new Latitude(box.getSouthBoundLatitude()), buffer, pos).append(", ");
/* 137 */     format.format(new Longitude(box.getEastBoundLongitude()), buffer, pos);
/* 138 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\BoundingBoxes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */