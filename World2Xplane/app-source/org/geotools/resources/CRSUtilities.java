/*     */ package org.geotools.resources;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.geometry.GeneralDirectPosition;
/*     */ import org.geotools.measure.AngleFormat;
/*     */ import org.geotools.measure.Latitude;
/*     */ import org.geotools.measure.Longitude;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.crs.DefaultGeographicCRS;
/*     */ import org.geotools.referencing.cs.DefaultEllipsoidalCS;
/*     */ import org.geotools.referencing.datum.DefaultGeodeticDatum;
/*     */ import org.geotools.referencing.datum.DefaultPrimeMeridian;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CompoundCRS;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.GeneralDerivedCRS;
/*     */ import org.opengis.referencing.crs.GeographicCRS;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.EllipsoidalCS;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ import org.opengis.referencing.datum.PrimeMeridian;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransform2D;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ 
/*     */ public final class CRSUtilities {
/*     */   public static int dimensionColinearWith(CoordinateSystem cs, CoordinateSystemAxis axis) {
/* 108 */     int candidate = -1;
/* 109 */     int dimension = cs.getDimension();
/* 110 */     AxisDirection direction = axis.getDirection().absolute();
/* 111 */     for (int i = 0; i < dimension; i++) {
/* 112 */       CoordinateSystemAxis xi = cs.getAxis(i);
/* 113 */       if (direction.equals(xi.getDirection().absolute())) {
/* 114 */         candidate = i;
/* 115 */         if (axis.equals(xi))
/*     */           break; 
/*     */       } 
/*     */     } 
/* 120 */     return candidate;
/*     */   }
/*     */   
/*     */   public static Unit<?> getUnit(CoordinateSystem cs) {
/* 134 */     Unit<?> unit = null;
/* 135 */     for (int i = cs.getDimension(); --i >= 0; ) {
/* 136 */       Unit<?> candidate = cs.getAxis(i).getUnit();
/* 137 */       if (candidate != null) {
/* 138 */         if (unit == null) {
/* 139 */           unit = candidate;
/*     */           continue;
/*     */         } 
/* 140 */         if (!unit.equals(candidate))
/* 141 */           return null; 
/*     */       } 
/*     */     } 
/* 145 */     return unit;
/*     */   }
/*     */   
/*     */   private static List<CoordinateReferenceSystem> getComponents(CoordinateReferenceSystem crs) {
/* 152 */     if (crs instanceof CompoundCRS) {
/* 154 */       List<CoordinateReferenceSystem> components = ((CompoundCRS)crs).getCoordinateReferenceSystems();
/* 155 */       if (!components.isEmpty())
/* 156 */         return components; 
/*     */     } 
/* 159 */     return null;
/*     */   }
/*     */   
/*     */   public static int getDimensionOf(CoordinateReferenceSystem crs, Class<? extends CoordinateReferenceSystem> type) throws IllegalArgumentException {
/* 177 */     if (type.isAssignableFrom(crs.getClass()))
/* 178 */       return 0; 
/* 180 */     List<CoordinateReferenceSystem> c = getComponents(crs);
/* 181 */     if (c != null) {
/* 182 */       int offset = 0;
/* 183 */       for (CoordinateReferenceSystem ci : c) {
/* 184 */         int index = getDimensionOf(ci, type);
/* 185 */         if (index >= 0)
/* 186 */           return index + offset; 
/* 188 */         offset += ci.getCoordinateSystem().getDimension();
/*     */       } 
/*     */     } 
/* 191 */     return -1;
/*     */   }
/*     */   
/*     */   public static CoordinateReferenceSystem getSubCRS(CoordinateReferenceSystem crs, int lower, int upper) {
/* 206 */     int dimension = crs.getCoordinateSystem().getDimension();
/* 207 */     if (lower < 0 || lower > upper || upper > dimension)
/* 208 */       throw new IndexOutOfBoundsException(Errors.format(79, Integer.valueOf((lower < 0) ? lower : upper))); 
/* 211 */     while (lower != 0 || upper != dimension) {
/* 212 */       List<CoordinateReferenceSystem> c = getComponents(crs);
/* 213 */       if (c == null)
/* 214 */         return null; 
/* 216 */       for (Iterator<CoordinateReferenceSystem> it = c.iterator(); it.hasNext(); ) {
/* 217 */         crs = it.next();
/* 218 */         dimension = crs.getCoordinateSystem().getDimension();
/* 219 */         if (lower < dimension)
/*     */           break; 
/* 222 */         lower -= dimension;
/* 223 */         upper -= dimension;
/*     */       } 
/*     */     } 
/* 226 */     return crs;
/*     */   }
/*     */   
/*     */   public static CoordinateReferenceSystem getCRS2D(CoordinateReferenceSystem crs) throws TransformException {
/* 245 */     if (crs != null)
/* 246 */       while (crs.getCoordinateSystem().getDimension() != 2) {
/* 247 */         List<CoordinateReferenceSystem> c = getComponents(crs);
/* 248 */         if (c == null)
/* 249 */           throw new TransformException(Errors.format(29, crs.getName())); 
/* 252 */         crs = c.get(0);
/*     */       }  
/* 255 */     return crs;
/*     */   }
/*     */   
/*     */   public static Map<String, ?> changeDimensionInName(IdentifiedObject object, String search, String replace) {
/* 274 */     StringBuilder name = new StringBuilder(object.getName().getCode());
/* 275 */     int last = name.length() - search.length();
/* 276 */     boolean append = true;
/*     */     int i;
/* 277 */     for (i = name.lastIndexOf(search); i >= 0; i = name.lastIndexOf(search, i - 1)) {
/* 278 */       if (i == 0 || !Character.isLetterOrDigit(name.charAt(i - 1)))
/* 281 */         if (i == last || !Character.isLetterOrDigit(i + search.length())) {
/* 284 */           name.replace(i, i + search.length(), replace);
/* 285 */           i = name.indexOf(". ", i);
/* 286 */           if (i >= 0)
/* 294 */             name.setLength(i + 1); 
/* 296 */           append = false;
/*     */           break;
/*     */         }  
/*     */     } 
/* 299 */     if (append)
/* 300 */       if (name.indexOf(" ") >= 0) {
/* 301 */         name.append(" (").append(replace).append(')');
/*     */       } else {
/* 303 */         name.append('_').append(replace);
/*     */       }  
/* 306 */     return Collections.singletonMap("name", name.toString());
/*     */   }
/*     */   
/*     */   public static Datum getDatum(CoordinateReferenceSystem crs) {
/* 316 */     return (crs instanceof SingleCRS) ? ((SingleCRS)crs).getDatum() : null;
/*     */   }
/*     */   
/*     */   public static Ellipsoid getHeadGeoEllipsoid(CoordinateReferenceSystem crs) {
/* 328 */     while (!(crs instanceof GeographicCRS)) {
/* 329 */       List<CoordinateReferenceSystem> c = getComponents(crs);
/* 330 */       if (c == null)
/* 331 */         return null; 
/* 333 */       crs = c.get(0);
/*     */     } 
/* 335 */     return ((GeographicCRS)crs).getDatum().getEllipsoid();
/*     */   }
/*     */   
/*     */   public static GeographicCRS getStandardGeographicCRS2D(CoordinateReferenceSystem crs) {
/*     */     DefaultGeodeticDatum defaultGeodeticDatum;
/* 347 */     while (crs instanceof GeneralDerivedCRS)
/* 348 */       crs = ((GeneralDerivedCRS)crs).getBaseCRS(); 
/* 350 */     if (!(crs instanceof SingleCRS))
/* 351 */       return (GeographicCRS)DefaultGeographicCRS.WGS84; 
/* 353 */     Datum datum = ((SingleCRS)crs).getDatum();
/* 354 */     if (!(datum instanceof GeodeticDatum))
/* 355 */       return (GeographicCRS)DefaultGeographicCRS.WGS84; 
/* 357 */     GeodeticDatum geoDatum = (GeodeticDatum)datum;
/* 358 */     if (geoDatum.getPrimeMeridian().getGreenwichLongitude() != 0.0D) {
/* 359 */       defaultGeodeticDatum = new DefaultGeodeticDatum(geoDatum.getName().getCode(), geoDatum.getEllipsoid(), (PrimeMeridian)DefaultPrimeMeridian.GREENWICH);
/* 361 */     } else if (crs instanceof GeographicCRS && 
/* 362 */       CRS.equalsIgnoreMetadata(DefaultEllipsoidalCS.GEODETIC_2D, crs.getCoordinateSystem())) {
/* 363 */       return (GeographicCRS)crs;
/*     */     } 
/* 366 */     return (GeographicCRS)new DefaultGeographicCRS(crs.getName().getCode(), (GeodeticDatum)defaultGeodeticDatum, (EllipsoidalCS)DefaultEllipsoidalCS.GEODETIC_2D);
/*     */   }
/*     */   
/*     */   public static DirectPosition deltaTransform(MathTransform transform, DirectPosition origin, DirectPosition source) throws TransformException {
/* 387 */     int sourceDim = transform.getSourceDimensions();
/* 388 */     int targetDim = transform.getTargetDimensions();
/* 389 */     GeneralDirectPosition generalDirectPosition1 = new GeneralDirectPosition(sourceDim);
/* 390 */     GeneralDirectPosition generalDirectPosition2 = new GeneralDirectPosition(sourceDim);
/*     */     int i;
/* 391 */     for (i = 0; i < sourceDim; i++) {
/* 392 */       double c = origin.getOrdinate(i);
/* 393 */       double d = source.getOrdinate(i) * 0.5D;
/* 394 */       generalDirectPosition1.setOrdinate(i, c - d);
/* 395 */       generalDirectPosition2.setOrdinate(i, c + d);
/*     */     } 
/* 397 */     DirectPosition directPosition1 = transform.transform((DirectPosition)generalDirectPosition1, (sourceDim == targetDim) ? (DirectPosition)generalDirectPosition1 : null);
/* 398 */     DirectPosition directPosition2 = transform.transform((DirectPosition)generalDirectPosition2, (sourceDim == targetDim) ? (DirectPosition)generalDirectPosition2 : null);
/* 399 */     for (i = 0; i < targetDim; i++)
/* 400 */       directPosition2.setOrdinate(i, directPosition2.getOrdinate(i) - directPosition1.getOrdinate(i)); 
/* 402 */     return directPosition2;
/*     */   }
/*     */   
/*     */   public static Point2D deltaTransform(MathTransform2D transform, Point2D origin, Point2D source, Point2D dest) throws TransformException {
/* 425 */     if (transform instanceof AffineTransform)
/* 426 */       return ((AffineTransform)transform).deltaTransform(source, dest); 
/* 428 */     double ox = origin.getX();
/* 429 */     double oy = origin.getY();
/* 430 */     double dx = source.getX() * 0.5D;
/* 431 */     double dy = source.getY() * 0.5D;
/* 432 */     Point2D P1 = new Point2D.Double(ox - dx, oy - dy);
/* 433 */     Point2D P2 = new Point2D.Double(ox + dx, oy + dy);
/* 434 */     P1 = transform.transform(P1, P1);
/* 435 */     P2 = transform.transform(P2, P2);
/* 436 */     if (dest == null)
/* 437 */       dest = P2; 
/* 439 */     dest.setLocation(P2.getX() - P1.getX(), P2.getY() - P1.getY());
/* 440 */     return dest;
/*     */   }
/*     */   
/*     */   public static String toWGS84String(CoordinateReferenceSystem crs, Rectangle2D bounds) {
/*     */     FactoryException factoryException;
/* 462 */     StringBuffer buffer = new StringBuffer();
/* 463 */     SingleCRS singleCRS = CRS.getHorizontalCRS(crs);
/* 464 */     if (singleCRS == null) {
/* 465 */       Exception exception = new UnsupportedOperationException(Errors.format(31, crs.getName()));
/*     */     } else {
/*     */       try {
/* 468 */         if (!CRS.equalsIgnoreMetadata(DefaultGeographicCRS.WGS84, singleCRS)) {
/* 469 */           CoordinateOperation op = ReferencingFactoryFinder.getCoordinateOperationFactory(null).createOperation((CoordinateReferenceSystem)singleCRS, (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84);
/* 471 */           bounds = CRS.transform(op, bounds, null);
/*     */         } 
/* 473 */         AngleFormat fmt = new AngleFormat("DD°MM.m'");
/* 474 */         fmt.format(new Latitude(bounds.getMinY()), buffer, null).append('-');
/* 475 */         fmt.format(new Latitude(bounds.getMaxY()), buffer, null).append(' ');
/* 476 */         fmt.format(new Longitude(bounds.getMinX()), buffer, null).append('-');
/* 477 */         fmt.format(new Longitude(bounds.getMaxX()), buffer, null);
/* 478 */         return buffer.toString();
/* 479 */       } catch (TransformException e) {
/* 480 */         TransformException transformException1 = e;
/* 481 */       } catch (FactoryException e) {
/* 482 */         factoryException = e;
/*     */       } 
/*     */     } 
/* 484 */     buffer.append(Classes.getShortClassName(factoryException));
/* 485 */     String message = factoryException.getLocalizedMessage();
/* 486 */     if (message != null)
/* 487 */       buffer.append(": ").append(message); 
/* 489 */     return buffer.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\resources\CRSUtilities.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */