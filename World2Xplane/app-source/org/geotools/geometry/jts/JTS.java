/*      */ package org.geotools.geometry.jts;
/*      */ 
/*      */ import com.vividsolutions.jts.algorithm.CGAlgorithms;
/*      */ import com.vividsolutions.jts.geom.Coordinate;
/*      */ import com.vividsolutions.jts.geom.CoordinateSequence;
/*      */ import com.vividsolutions.jts.geom.CoordinateSequenceFilter;
/*      */ import com.vividsolutions.jts.geom.Envelope;
/*      */ import com.vividsolutions.jts.geom.Geometry;
/*      */ import com.vividsolutions.jts.geom.GeometryFactory;
/*      */ import com.vividsolutions.jts.geom.LineString;
/*      */ import com.vividsolutions.jts.geom.LinearRing;
/*      */ import com.vividsolutions.jts.geom.MultiPolygon;
/*      */ import com.vividsolutions.jts.geom.Point;
/*      */ import com.vividsolutions.jts.geom.Polygon;
/*      */ import com.vividsolutions.jts.operation.polygonize.Polygonizer;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.IllegalPathStateException;
/*      */ import java.awt.geom.PathIterator;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import org.geotools.geometry.AbstractDirectPosition;
/*      */ import org.geotools.geometry.Envelope2D;
/*      */ import org.geotools.geometry.GeneralDirectPosition;
/*      */ import org.geotools.referencing.CRS;
/*      */ import org.geotools.referencing.GeodeticCalculator;
/*      */ import org.geotools.referencing.crs.DefaultGeographicCRS;
/*      */ import org.geotools.referencing.operation.TransformPathNotFoundException;
/*      */ import org.geotools.referencing.operation.projection.PointOutsideEnvelopeException;
/*      */ import org.geotools.resources.Classes;
/*      */ import org.geotools.resources.geometry.ShapeUtilities;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.opengis.geometry.BoundingBox;
/*      */ import org.opengis.geometry.DirectPosition;
/*      */ import org.opengis.geometry.MismatchedDimensionException;
/*      */ import org.opengis.geometry.coordinate.Position;
/*      */ import org.opengis.referencing.FactoryException;
/*      */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.referencing.crs.SingleCRS;
/*      */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*      */ import org.opengis.referencing.operation.CoordinateOperation;
/*      */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*      */ import org.opengis.referencing.operation.MathTransform;
/*      */ import org.opengis.referencing.operation.OperationNotFoundException;
/*      */ import org.opengis.referencing.operation.TransformException;
/*      */ 
/*      */ public final class JTS {
/*   94 */   private static final GeneralDirectPosition[] POSITIONS = new GeneralDirectPosition[4];
/*      */   
/*      */   static {
/*   97 */     for (int i = 0; i < POSITIONS.length; i++)
/*   98 */       POSITIONS[i] = new GeneralDirectPosition(i); 
/*      */   }
/*      */   
/*  109 */   private static final Map<CoordinateReferenceSystem, GeodeticCalculator> CALCULATORS = new HashMap<CoordinateReferenceSystem, GeodeticCalculator>();
/*      */   
/*      */   private static void ensureNonNull(String name, Object object) throws IllegalArgumentException {
/*  129 */     if (object == null)
/*  130 */       throw new IllegalArgumentException(Errors.format(143, name)); 
/*      */   }
/*      */   
/*      */   public static Envelope transform(Envelope envelope, MathTransform transform) throws TransformException {
/*  151 */     return transform(envelope, null, transform, 5);
/*      */   }
/*      */   
/*      */   public static Envelope transform(Envelope sourceEnvelope, Envelope targetEnvelope, MathTransform transform, int npoints) throws TransformException {
/*  184 */     ensureNonNull("sourceEnvelope", sourceEnvelope);
/*  185 */     ensureNonNull("transform", transform);
/*  187 */     if (transform.getSourceDimensions() != transform.getTargetDimensions() || transform.getSourceDimensions() < 2)
/*  189 */       throw new MismatchedDimensionException(Errors.format(15, Classes.getShortClassName(transform))); 
/*  193 */     npoints++;
/*  195 */     double[] coordinates = new double[4 * npoints * 2];
/*  196 */     double xmin = sourceEnvelope.getMinX();
/*  197 */     double xmax = sourceEnvelope.getMaxX();
/*  198 */     double ymin = sourceEnvelope.getMinY();
/*  199 */     double ymax = sourceEnvelope.getMaxY();
/*  200 */     double scaleX = (xmax - xmin) / npoints;
/*  201 */     double scaleY = (ymax - ymin) / npoints;
/*  203 */     int offset = 0;
/*      */     int t;
/*  205 */     for (t = 0; t < npoints; t++) {
/*  206 */       double dx = scaleX * t;
/*  207 */       double dy = scaleY * t;
/*  208 */       coordinates[offset++] = xmin;
/*  209 */       coordinates[offset++] = ymin + dy;
/*  210 */       coordinates[offset++] = xmin + dx;
/*  211 */       coordinates[offset++] = ymax;
/*  212 */       coordinates[offset++] = xmax;
/*  213 */       coordinates[offset++] = ymax - dy;
/*  214 */       coordinates[offset++] = xmax - dx;
/*  215 */       coordinates[offset++] = ymin;
/*      */     } 
/*  217 */     assert offset == coordinates.length;
/*  218 */     xform(transform, coordinates, coordinates);
/*  221 */     if (targetEnvelope == null)
/*  222 */       targetEnvelope = new Envelope(); 
/*  225 */     for (t = 0; t < offset;)
/*  226 */       targetEnvelope.expandToInclude(coordinates[t++], coordinates[t++]); 
/*  229 */     return targetEnvelope;
/*      */   }
/*      */   
/*      */   public static ReferencedEnvelope3D transformTo3D(ReferencedEnvelope sourceEnvelope, CoordinateReferenceSystem targetCRS, boolean lenient, int npoints) throws TransformException, OperationNotFoundException, FactoryException {
/*  250 */     double xmin = sourceEnvelope.getMinX();
/*  251 */     double xmax = sourceEnvelope.getMaxX();
/*  252 */     double ymin = sourceEnvelope.getMinY();
/*  253 */     double ymax = sourceEnvelope.getMaxY();
/*  254 */     double scaleX = (xmax - xmin) / npoints;
/*  255 */     double scaleY = (ymax - ymin) / npoints;
/*  256 */     ReferencedEnvelope3D targetEnvelope = new ReferencedEnvelope3D(targetCRS);
/*  262 */     CoordinateOperationFactory coordinateOperationFactory = CRS.getCoordinateOperationFactory(lenient);
/*  264 */     CoordinateOperation operation1 = coordinateOperationFactory.createOperation(sourceEnvelope.getCoordinateReferenceSystem(), (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84);
/*  266 */     MathTransform transform1 = operation1.getMathTransform();
/*  267 */     CoordinateOperation operation2 = coordinateOperationFactory.createOperation((CoordinateReferenceSystem)DefaultGeographicCRS.WGS84_3D, targetCRS);
/*  269 */     MathTransform transform2 = operation2.getMathTransform();
/*  271 */     for (int t = 0; t < npoints; t++) {
/*  272 */       double dx = scaleX * t;
/*  273 */       double dy = scaleY * t;
/*  275 */       GeneralDirectPosition left = new GeneralDirectPosition(xmin, ymin + dy);
/*  276 */       DirectPosition pt = transformTo3D(left, transform1, transform2);
/*  277 */       targetEnvelope.expandToInclude(pt);
/*  279 */       GeneralDirectPosition top = new GeneralDirectPosition(xmin + dx, ymax);
/*  280 */       pt = transformTo3D(top, transform1, transform2);
/*  281 */       targetEnvelope.expandToInclude(pt);
/*  283 */       GeneralDirectPosition right = new GeneralDirectPosition(xmax, ymax - dy);
/*  284 */       pt = transformTo3D(right, transform1, transform2);
/*  285 */       targetEnvelope.expandToInclude(pt);
/*  287 */       GeneralDirectPosition bottom = new GeneralDirectPosition(xmax - dx, ymin);
/*  288 */       pt = transformTo3D(bottom, transform1, transform2);
/*  289 */       targetEnvelope.expandToInclude(pt);
/*      */     } 
/*  291 */     return targetEnvelope;
/*      */   }
/*      */   
/*      */   public static ReferencedEnvelope transformTo2D(ReferencedEnvelope sourceEnvelope, CoordinateReferenceSystem targetCRS, boolean lenient, int npoints) throws TransformException, OperationNotFoundException, FactoryException {
/*  309 */     double xmin = sourceEnvelope.getMinX();
/*  310 */     double xmax = sourceEnvelope.getMaxX();
/*  311 */     double ymin = sourceEnvelope.getMinY();
/*  312 */     double ymax = sourceEnvelope.getMaxY();
/*  313 */     double scaleX = (xmax - xmin) / npoints;
/*  314 */     double scaleY = (ymax - ymin) / npoints;
/*  316 */     double zmin = sourceEnvelope.getMinimum(2);
/*  317 */     double zmax = sourceEnvelope.getMaximum(2);
/*  318 */     double scaleZ = (zmax - zmin) / npoints;
/*  322 */     ReferencedEnvelope targetEnvelope = new ReferencedEnvelope(targetCRS);
/*  328 */     CoordinateOperationFactory coordinateOperationFactory = CRS.getCoordinateOperationFactory(lenient);
/*  330 */     CoordinateReferenceSystem sourceCRS = sourceEnvelope.getCoordinateReferenceSystem();
/*  331 */     CoordinateOperation operation1 = coordinateOperationFactory.createOperation(sourceCRS, (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84_3D);
/*  333 */     MathTransform transform1 = operation1.getMathTransform();
/*  334 */     CoordinateOperation operation2 = coordinateOperationFactory.createOperation((CoordinateReferenceSystem)DefaultGeographicCRS.WGS84, targetCRS);
/*  336 */     MathTransform transform2 = operation2.getMathTransform();
/*  338 */     for (int t = 0; t < npoints; t++) {
/*  339 */       double dx = scaleX * t;
/*  340 */       double dy = scaleY * t;
/*  341 */       for (int u = 0; u < npoints; u++) {
/*  342 */         double dz = scaleZ * u;
/*  343 */         double z = zmin + dz;
/*  345 */         GeneralDirectPosition left = new GeneralDirectPosition(xmin, ymin + dy, z);
/*  347 */         DirectPosition pt = transformTo2D(left, transform1, transform2);
/*  348 */         targetEnvelope.expandToInclude(pt);
/*  350 */         GeneralDirectPosition top = new GeneralDirectPosition(xmin + dx, ymax, z);
/*  351 */         pt = transformTo2D(top, transform1, transform2);
/*  352 */         targetEnvelope.expandToInclude(pt);
/*  354 */         GeneralDirectPosition right = new GeneralDirectPosition(xmax, ymax - dy, z);
/*  355 */         pt = transformTo2D(right, transform1, transform2);
/*  356 */         targetEnvelope.expandToInclude(pt);
/*  358 */         GeneralDirectPosition bottom = new GeneralDirectPosition(xmax - dx, ymax, z);
/*  359 */         pt = transformTo2D(bottom, transform1, transform2);
/*  360 */         targetEnvelope.expandToInclude(pt);
/*  362 */         if (zmin == zmax)
/*      */           break; 
/*      */       } 
/*      */     } 
/*  367 */     return targetEnvelope;
/*      */   }
/*      */   
/*      */   private static DirectPosition transformTo3D(GeneralDirectPosition srcPosition, MathTransform transformToWGS84, MathTransform transformFromWGS84_3D) throws TransformException {
/*  382 */     DirectPosition world2D = transformToWGS84.transform((DirectPosition)srcPosition, null);
/*  384 */     GeneralDirectPosition generalDirectPosition = new GeneralDirectPosition((CoordinateReferenceSystem)DefaultGeographicCRS.WGS84_3D);
/*  385 */     generalDirectPosition.setOrdinate(0, world2D.getOrdinate(0));
/*  386 */     generalDirectPosition.setOrdinate(1, world2D.getOrdinate(1));
/*  387 */     generalDirectPosition.setOrdinate(2, 0.0D);
/*  389 */     DirectPosition targetPosition = transformFromWGS84_3D.transform((DirectPosition)generalDirectPosition, null);
/*  390 */     return targetPosition;
/*      */   }
/*      */   
/*      */   private static DirectPosition transformTo2D(GeneralDirectPosition srcPosition, MathTransform transformToWGS84_3D, MathTransform transformFromWGS84) throws TransformException {
/*  405 */     if (Double.isNaN(srcPosition.getOrdinate(2)))
/*  406 */       srcPosition.setOrdinate(2, 0.0D); 
/*  408 */     DirectPosition world3D = transformToWGS84_3D.transform((DirectPosition)srcPosition, null);
/*  410 */     GeneralDirectPosition generalDirectPosition = new GeneralDirectPosition((CoordinateReferenceSystem)DefaultGeographicCRS.WGS84);
/*  411 */     generalDirectPosition.setOrdinate(0, world3D.getOrdinate(0));
/*  412 */     generalDirectPosition.setOrdinate(1, world3D.getOrdinate(1));
/*  414 */     DirectPosition targetPosition = transformFromWGS84.transform((DirectPosition)generalDirectPosition, null);
/*  415 */     return targetPosition;
/*      */   }
/*      */   
/*      */   public static Geometry transform(Geometry geom, MathTransform transform) throws MismatchedDimensionException, TransformException {
/*  434 */     GeometryCoordinateSequenceTransformer transformer = new GeometryCoordinateSequenceTransformer();
/*  435 */     transformer.setMathTransform(transform);
/*  437 */     return transformer.transform(geom);
/*      */   }
/*      */   
/*      */   public static Coordinate transform(Coordinate source, Coordinate dest, MathTransform transform) throws TransformException {
/*  455 */     ensureNonNull("source", source);
/*  456 */     ensureNonNull("transform", transform);
/*  458 */     if (dest == null)
/*  459 */       dest = new Coordinate(); 
/*  462 */     double[] array = new double[transform.getTargetDimensions()];
/*  463 */     copy(source, array);
/*  464 */     transform.transform(array, 0, array, 0, 1);
/*  466 */     switch (transform.getTargetDimensions()) {
/*      */       case 3:
/*  468 */         dest.z = array[2];
/*      */       case 2:
/*  471 */         dest.y = array[1];
/*      */       case 1:
/*  474 */         dest.x = array[0];
/*      */         break;
/*      */     } 
/*  480 */     return dest;
/*      */   }
/*      */   
/*      */   public static Envelope toGeographic(Envelope envelope, CoordinateReferenceSystem crs) throws TransformException {
/*  500 */     if (CRS.equalsIgnoreMetadata(crs, DefaultGeographicCRS.WGS84)) {
/*  501 */       if (envelope instanceof ReferencedEnvelope)
/*  502 */         return envelope; 
/*  504 */       return ReferencedEnvelope.create(envelope, (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84);
/*      */     } 
/*  506 */     ReferencedEnvelope initial = ReferencedEnvelope.create(envelope, crs);
/*  507 */     return toGeographic(initial);
/*      */   }
/*      */   
/*      */   public static ReferencedEnvelope toGeographic(ReferencedEnvelope envelope) throws TransformException {
/*      */     try {
/*  523 */       return envelope.transform((CoordinateReferenceSystem)DefaultGeographicCRS.WGS84, true);
/*  524 */     } catch (FactoryException exception) {
/*  525 */       throw new TransformPathNotFoundException(Errors.format(33, exception));
/*      */     } 
/*      */   }
/*      */   
/*      */   public static void xform(MathTransform transform, double[] src, double[] dest) throws TransformException {
/*  547 */     ensureNonNull("transform", transform);
/*  549 */     int sourceDim = transform.getSourceDimensions();
/*  550 */     int targetDim = transform.getTargetDimensions();
/*  552 */     if (targetDim != sourceDim)
/*  553 */       throw new MismatchedDimensionException(); 
/*  556 */     TransformException firstError = null;
/*  557 */     boolean startPointTransformed = false;
/*      */     int i;
/*  559 */     for (i = 0; i < src.length; i += sourceDim) {
/*      */       try {
/*  561 */         transform.transform(src, i, dest, i, 1);
/*  563 */         if (!startPointTransformed) {
/*  564 */           startPointTransformed = true;
/*  566 */           for (int j = 0; j < i; j++)
/*  567 */             System.arraycopy(dest, j, dest, i, targetDim); 
/*      */         } 
/*  570 */       } catch (TransformException e) {
/*  571 */         if (firstError == null)
/*  572 */           firstError = e; 
/*  575 */         if (startPointTransformed)
/*  576 */           System.arraycopy(dest, i - targetDim, dest, i, targetDim); 
/*      */       } 
/*      */     } 
/*  581 */     if (!startPointTransformed && firstError != null)
/*  582 */       throw firstError; 
/*      */   }
/*      */   
/*      */   public static synchronized double orthodromicDistance(Coordinate p1, Coordinate p2, CoordinateReferenceSystem crs) throws TransformException {
/*  614 */     ensureNonNull("p1", p1);
/*  615 */     ensureNonNull("p2", p2);
/*  616 */     ensureNonNull("crs", crs);
/*  623 */     GeodeticCalculator gc = CALCULATORS.get(crs);
/*  625 */     if (gc == null) {
/*  626 */       gc = new GeodeticCalculator(crs);
/*  627 */       CALCULATORS.put(crs, gc);
/*      */     } 
/*  629 */     assert crs.equals(gc.getCoordinateReferenceSystem()) : crs;
/*  631 */     GeneralDirectPosition pos = POSITIONS[Math.min(POSITIONS.length - 1, crs.getCoordinateSystem().getDimension())];
/*  633 */     pos.setCoordinateReferenceSystem(crs);
/*  634 */     copy(p1, pos.ordinates);
/*  635 */     gc.setStartingPosition((Position)pos);
/*  636 */     copy(p2, pos.ordinates);
/*  637 */     gc.setDestinationPosition((Position)pos);
/*  639 */     return gc.getOrthodromicDistance();
/*      */   }
/*      */   
/*      */   public static DirectPosition toDirectPosition(final Coordinate point, final CoordinateReferenceSystem crs) {
/*  655 */     return (DirectPosition)new AbstractDirectPosition() {
/*      */         public CoordinateReferenceSystem getCoordinateReferenceSystem() {
/*  658 */           return crs;
/*      */         }
/*      */         
/*      */         public int getDimension() {
/*  662 */           return crs.getCoordinateSystem().getDimension();
/*      */         }
/*      */         
/*      */         public double getOrdinate(int dimension) throws IndexOutOfBoundsException {
/*  667 */           switch (dimension) {
/*      */             case 0:
/*  669 */               return point.x;
/*      */             case 1:
/*  671 */               return point.y;
/*      */             case 2:
/*  673 */               return point.z;
/*      */           } 
/*  675 */           return Double.NaN;
/*      */         }
/*      */         
/*      */         public void setOrdinate(int dimension, double value) throws IndexOutOfBoundsException {
/*  681 */           switch (dimension) {
/*      */             case 0:
/*  683 */               point.x = value;
/*      */               return;
/*      */             case 1:
/*  686 */               point.y = value;
/*      */               return;
/*      */             case 2:
/*  689 */               point.z = value;
/*      */               return;
/*      */           } 
/*      */         }
/*      */       };
/*      */   }
/*      */   
/*      */   public static void copy(Coordinate point, double[] ordinates) {
/*  709 */     ensureNonNull("point", point);
/*  710 */     ensureNonNull("ordinates", ordinates);
/*  712 */     switch (ordinates.length) {
/*      */       default:
/*  714 */         Arrays.fill(ordinates, 3, ordinates.length, Double.NaN);
/*      */       case 3:
/*  717 */         ordinates[2] = point.z;
/*      */       case 2:
/*  720 */         ordinates[1] = point.y;
/*      */       case 1:
/*  723 */         ordinates[0] = point.x;
/*      */         break;
/*      */       case 0:
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Geometry shapeToGeometry(Shape shape, GeometryFactory factory) {
/*  744 */     return toGeometry(shape, factory);
/*      */   }
/*      */   
/*      */   public static Geometry toGeometry(Shape shape) {
/*  759 */     return toGeometry(shape, new GeometryFactory());
/*      */   }
/*      */   
/*      */   public static Geometry toGeometry(Shape shape, GeometryFactory factory) {
/*  776 */     ensureNonNull("shape", shape);
/*  777 */     ensureNonNull("factory", factory);
/*  779 */     PathIterator iterator = shape.getPathIterator(null, ShapeUtilities.getFlatness(shape));
/*  781 */     double[] buffer = new double[6];
/*  782 */     List<Coordinate> coords = new ArrayList<Coordinate>();
/*  783 */     List<LineString> lines = new ArrayList<LineString>();
/*  785 */     while (!iterator.isDone()) {
/*  786 */       switch (iterator.currentSegment(buffer)) {
/*      */         case 4:
/*  792 */           if (!coords.isEmpty()) {
/*  793 */             coords.add(coords.get(0));
/*  794 */             lines.add(factory.createLinearRing(coords.<Coordinate>toArray(new Coordinate[coords.size()])));
/*  796 */             coords.clear();
/*      */           } 
/*      */           break;
/*      */         case 0:
/*  806 */           if (!coords.isEmpty()) {
/*  807 */             lines.add(factory.createLineString(coords.<Coordinate>toArray(new Coordinate[coords.size()])));
/*  809 */             coords.clear();
/*      */           } 
/*      */         case 1:
/*  816 */           coords.add(new Coordinate(buffer[0], buffer[1]));
/*      */           break;
/*      */         default:
/*  822 */           throw new IllegalPathStateException();
/*      */       } 
/*  825 */       iterator.next();
/*      */     } 
/*  831 */     if (!coords.isEmpty())
/*  832 */       lines.add(factory.createLineString(coords.<Coordinate>toArray(new Coordinate[coords.size()]))); 
/*  836 */     switch (lines.size()) {
/*      */       case 0:
/*  838 */         return null;
/*      */       case 1:
/*  841 */         return (Geometry)lines.get(0);
/*      */     } 
/*  844 */     return (Geometry)factory.createMultiLineString(GeometryFactory.toLineStringArray(lines));
/*      */   }
/*      */   
/*      */   public static Envelope2D getEnvelope2D(Envelope envelope, CoordinateReferenceSystem crs) throws MismatchedDimensionException {
/*  869 */     ensureNonNull("envelope", envelope);
/*  870 */     ensureNonNull("crs", crs);
/*  872 */     if (envelope instanceof ReferencedEnvelope) {
/*  873 */       ReferencedEnvelope referenced = (ReferencedEnvelope)envelope;
/*  874 */       CoordinateReferenceSystem implicitCRS = referenced.getCoordinateReferenceSystem();
/*  876 */       if (crs != null && !CRS.equalsIgnoreMetadata(crs, implicitCRS))
/*  877 */         throw new IllegalArgumentException(Errors.format(95, crs.getName().getCode(), implicitCRS.getName().getCode())); 
/*      */     } 
/*  884 */     SingleCRS singleCRS = CRS.getHorizontalCRS(crs);
/*  885 */     if (singleCRS == null)
/*  886 */       throw new MismatchedDimensionException(Errors.format(31, crs)); 
/*  889 */     return new Envelope2D((CoordinateReferenceSystem)singleCRS, envelope.getMinX(), envelope.getMinY(), envelope.getWidth(), envelope.getHeight());
/*      */   }
/*      */   
/*      */   public static Point toGeometry(DirectPosition position) {
/*  900 */     return toGeometry(position, (GeometryFactory)null);
/*      */   }
/*      */   
/*      */   public static Point toGeometry(DirectPosition position, GeometryFactory factory) {
/*  912 */     if (factory == null)
/*  913 */       factory = new GeometryFactory(); 
/*  916 */     Coordinate coordinate = new Coordinate(position.getOrdinate(0), position.getOrdinate(1));
/*  917 */     if (position.getDimension() == 3)
/*  918 */       coordinate.z = position.getOrdinate(2); 
/*  920 */     return factory.createPoint(coordinate);
/*      */   }
/*      */   
/*      */   public static Polygon toGeometry(Envelope env) {
/*  939 */     return toGeometry(env, new GeometryFactory());
/*      */   }
/*      */   
/*      */   public static Polygon toGeometry(Envelope env, GeometryFactory factory) {
/*  957 */     ensureNonNull("env", env);
/*  958 */     if (factory == null)
/*  959 */       factory = new GeometryFactory(); 
/*  961 */     return factory.createPolygon(factory.createLinearRing(new Coordinate[] { new Coordinate(env.getMinX(), env.getMinY()), new Coordinate(env.getMaxX(), env.getMinY()), new Coordinate(env.getMaxX(), env.getMaxY()), new Coordinate(env.getMinX(), env.getMaxY()), new Coordinate(env.getMinX(), env.getMinY()) }), null);
/*      */   }
/*      */   
/*      */   public static ReferencedEnvelope toEnvelope(Geometry geom) {
/*  979 */     if (geom == null)
/*  980 */       return null; 
/*  982 */     String srsName = null;
/*  983 */     Object userData = geom.getUserData();
/*  984 */     if (userData != null && userData instanceof String) {
/*  985 */       srsName = (String)userData;
/*  986 */     } else if (geom.getSRID() > 0) {
/*  987 */       srsName = "EPSG:" + geom.getSRID();
/*      */     } 
/*  989 */     CoordinateReferenceSystem crs = null;
/*  990 */     if (userData != null && userData instanceof CoordinateReferenceSystem) {
/*  991 */       crs = (CoordinateReferenceSystem)userData;
/*  992 */     } else if (srsName != null) {
/*      */       try {
/*  994 */         crs = CRS.decode(srsName);
/*  995 */       } catch (NoSuchAuthorityCodeException e) {
/*      */       
/*  997 */       } catch (FactoryException e) {}
/*      */     } 
/* 1001 */     return new ReferencedEnvelope(geom.getEnvelopeInternal(), crs);
/*      */   }
/*      */   
/*      */   public static Polygon toGeometry(ReferencedEnvelope bbox) {
/* 1019 */     return toGeometry(bbox, new GeometryFactory());
/*      */   }
/*      */   
/*      */   public static Polygon toGeometry(BoundingBox bbox, GeometryFactory factory, int npoints) {
/* 1034 */     npoints++;
/* 1035 */     if (bbox == null)
/* 1036 */       return null; 
/* 1038 */     if (factory == null)
/* 1039 */       factory = new GeometryFactory(); 
/* 1042 */     Coordinate[] coordinates = new Coordinate[4 * npoints];
/* 1043 */     double xmin = bbox.getMinX();
/* 1044 */     double xmax = bbox.getMaxX();
/* 1045 */     double ymin = bbox.getMinY();
/* 1046 */     double ymax = bbox.getMaxY();
/* 1047 */     double scaleX = (xmax - xmin) / npoints;
/* 1048 */     double scaleY = (ymax - ymin) / npoints;
/* 1050 */     int top = 0;
/* 1051 */     int right = npoints;
/* 1052 */     int bottom = npoints * 2;
/* 1053 */     int left = npoints * 3;
/* 1055 */     for (int t = 0; t < npoints; t++) {
/* 1056 */       double dx = scaleX * t;
/* 1057 */       double dy = scaleY * t;
/* 1058 */       coordinates[top + t] = new Coordinate(xmin + dx, ymax);
/* 1059 */       coordinates[left + t] = new Coordinate(xmin, ymin + dy);
/* 1060 */       coordinates[bottom + t] = new Coordinate(xmax - dx, ymin);
/* 1061 */       coordinates[right + t] = new Coordinate(xmax, ymax - dy);
/*      */     } 
/* 1064 */     return factory.createPolygon(factory.createLinearRing(coordinates), null);
/*      */   }
/*      */   
/*      */   public static Geometry toGeographic(Geometry geom, CoordinateReferenceSystem crs) throws TransformException {
/* 1083 */     if (crs == null)
/* 1084 */       return geom; 
/* 1086 */     if (crs.getCoordinateSystem().getDimension() >= 3)
/*      */       try {
/* 1088 */         MathTransform transform = CRS.findMathTransform(crs, (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84_3D);
/* 1089 */         Geometry geometry = transform(geom, transform);
/* 1091 */         return geometry;
/* 1092 */       } catch (FactoryException exception) {
/* 1093 */         throw new TransformException(Errors.format(30, crs));
/*      */       }  
/* 1097 */     if (CRS.equalsIgnoreMetadata(crs, DefaultGeographicCRS.WGS84))
/* 1098 */       return geom; 
/*      */     try {
/* 1102 */       MathTransform transform = CRS.findMathTransform(crs, (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84);
/* 1103 */       return transform(geom, transform);
/* 1104 */     } catch (FactoryException exception) {
/* 1105 */       throw new TransformException(Errors.format(30, crs));
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Polygon toGeometry(BoundingBox bbox) {
/* 1125 */     return toGeometry(bbox, new GeometryFactory());
/*      */   }
/*      */   
/*      */   public static Polygon toGeometry(BoundingBox bbox, GeometryFactory factory) {
/* 1143 */     ensureNonNull("bbox", bbox);
/* 1144 */     ensureNonNull("factory", factory);
/* 1146 */     return factory.createPolygon(factory.createLinearRing(new Coordinate[] { new Coordinate(bbox.getMinX(), bbox.getMinY()), new Coordinate(bbox.getMaxX(), bbox.getMinY()), new Coordinate(bbox.getMaxX(), bbox.getMaxY()), new Coordinate(bbox.getMinX(), bbox.getMaxY()), new Coordinate(bbox.getMinX(), bbox.getMinY()) }), null);
/*      */   }
/*      */   
/*      */   public static void checkCoordinatesRange(Geometry geom, CoordinateReferenceSystem crs) throws PointOutsideEnvelopeException {
/* 1170 */     CoordinateSystemAxis x = crs.getCoordinateSystem().getAxis(0);
/* 1171 */     CoordinateSystemAxis y = crs.getCoordinateSystem().getAxis(1);
/* 1175 */     boolean xUnbounded = (Double.isInfinite(x.getMinimumValue()) && Double.isInfinite(x.getMaximumValue()));
/* 1177 */     boolean yUnbounded = (Double.isInfinite(y.getMinimumValue()) && Double.isInfinite(y.getMaximumValue()));
/* 1180 */     if (xUnbounded && yUnbounded)
/*      */       return; 
/* 1185 */     Coordinate[] c = geom.getCoordinates();
/* 1187 */     for (int i = 0; i < c.length; i++) {
/* 1188 */       if (!xUnbounded && ((c[i]).x < x.getMinimumValue() || (c[i]).x > x.getMaximumValue()))
/* 1189 */         throw new PointOutsideEnvelopeException((c[i]).x + " outside of (" + x.getMinimumValue() + "," + x.getMaximumValue() + ")"); 
/* 1193 */       if (!yUnbounded && ((c[i]).y < y.getMinimumValue() || (c[i]).y > y.getMaximumValue()))
/* 1194 */         throw new PointOutsideEnvelopeException((c[i]).y + " outside of (" + y.getMinimumValue() + "," + y.getMaximumValue() + ")"); 
/*      */     } 
/*      */   }
/*      */   
/*      */   public static Geometry smooth(Geometry geom, double fit) {
/* 1224 */     return smooth(geom, fit, new GeometryFactory());
/*      */   }
/*      */   
/*      */   public static Geometry smooth(Geometry geom, double fit, GeometryFactory factory) {
/* 1254 */     ensureNonNull("geom", geom);
/* 1255 */     ensureNonNull("factory", factory);
/* 1258 */     fit = Math.max(0.0D, Math.min(1.0D, fit));
/* 1259 */     return smooth(geom, fit, factory, new GeometrySmoother(factory));
/*      */   }
/*      */   
/*      */   private static Geometry smooth(Geometry geom, double fit, GeometryFactory factory, GeometrySmoother smoother) {
/* 1265 */     switch (Geometries.get(geom)) {
/*      */       case POINT:
/*      */       case MULTIPOINT:
/* 1269 */         return geom;
/*      */       case LINESTRING:
/* 1273 */         return smoothLineString(factory, smoother, geom, fit);
/*      */       case MULTILINESTRING:
/* 1276 */         return smoothMultiLineString(factory, smoother, geom, fit);
/*      */       case POLYGON:
/* 1279 */         return (Geometry)smoother.smooth((Polygon)geom, fit);
/*      */       case MULTIPOLYGON:
/* 1282 */         return smoothMultiPolygon(factory, smoother, geom, fit);
/*      */       case GEOMETRYCOLLECTION:
/* 1285 */         return smoothGeometryCollection(factory, smoother, geom, fit);
/*      */     } 
/* 1288 */     throw new UnsupportedOperationException("No smoothing method available for " + geom.getGeometryType());
/*      */   }
/*      */   
/*      */   private static Geometry smoothLineString(GeometryFactory factory, GeometrySmoother smoother, Geometry geom, double fit) {
/* 1296 */     if (geom instanceof LinearRing) {
/* 1298 */       Polygon poly = factory.createPolygon((LinearRing)geom, null);
/* 1299 */       Polygon smoothed = smoother.smooth(poly, fit);
/* 1300 */       return (Geometry)smoothed.getExteriorRing();
/*      */     } 
/* 1303 */     return (Geometry)smoother.smooth((LineString)geom, fit);
/*      */   }
/*      */   
/*      */   private static Geometry smoothMultiLineString(GeometryFactory factory, GeometrySmoother smoother, Geometry geom, double fit) {
/* 1310 */     int N = geom.getNumGeometries();
/* 1311 */     LineString[] smoothed = new LineString[N];
/* 1313 */     for (int i = 0; i < N; i++)
/* 1314 */       smoothed[i] = (LineString)smoothLineString(factory, smoother, geom.getGeometryN(i), fit); 
/* 1318 */     return (Geometry)factory.createMultiLineString(smoothed);
/*      */   }
/*      */   
/*      */   private static Geometry smoothMultiPolygon(GeometryFactory factory, GeometrySmoother smoother, Geometry geom, double fit) {
/* 1324 */     int N = geom.getNumGeometries();
/* 1325 */     Polygon[] smoothed = new Polygon[N];
/* 1327 */     for (int i = 0; i < N; i++)
/* 1328 */       smoothed[i] = smoother.smooth((Polygon)geom.getGeometryN(i), fit); 
/* 1331 */     return (Geometry)factory.createMultiPolygon(smoothed);
/*      */   }
/*      */   
/*      */   private static Geometry smoothGeometryCollection(GeometryFactory factory, GeometrySmoother smoother, Geometry geom, double fit) {
/* 1337 */     int N = geom.getNumGeometries();
/* 1338 */     Geometry[] smoothed = new Geometry[N];
/* 1340 */     for (int i = 0; i < N; i++)
/* 1341 */       smoothed[i] = smooth(geom.getGeometryN(i), fit, factory, smoother); 
/* 1344 */     return (Geometry)factory.createGeometryCollection(smoothed);
/*      */   }
/*      */   
/*      */   public static ReferencedEnvelope bounds(Geometry geometry, CoordinateReferenceSystem crs) {
/* 1355 */     if (geometry == null)
/* 1356 */       return null; 
/* 1358 */     if (crs == null)
/* 1359 */       return new ReferencedEnvelope(geometry.getEnvelopeInternal(), null); 
/* 1360 */     if (crs.getCoordinateSystem().getDimension() >= 3) {
/* 1361 */       ReferencedEnvelope bounds = new ReferencedEnvelope3D(crs);
/* 1365 */       for (Coordinate coordinate : geometry.getCoordinates())
/* 1366 */         bounds.expandToInclude(coordinate); 
/* 1368 */       return bounds;
/*      */     } 
/* 1370 */     return new ReferencedEnvelope(geometry.getEnvelopeInternal(), crs);
/*      */   }
/*      */   
/*      */   static LineString removeCollinearVertices(LineString ls) {
/* 1381 */     if (ls == null)
/* 1382 */       throw new NullPointerException("The provided linestring is null"); 
/* 1385 */     int N = ls.getNumPoints();
/* 1386 */     boolean isLinearRing = ls instanceof LinearRing;
/* 1388 */     List<Coordinate> retain = new ArrayList<Coordinate>();
/* 1389 */     retain.add(ls.getCoordinateN(0));
/* 1391 */     int i0 = 0, i1 = 1, i2 = 2;
/* 1392 */     Coordinate firstCoord = ls.getCoordinateN(i0);
/* 1395 */     while (i2 < N) {
/* 1396 */       Coordinate midCoord = ls.getCoordinateN(i1);
/* 1397 */       Coordinate lastCoord = ls.getCoordinateN(i2);
/* 1399 */       int orientation = CGAlgorithms.computeOrientation(firstCoord, midCoord, lastCoord);
/* 1402 */       if (orientation != 0) {
/* 1404 */         retain.add(midCoord);
/* 1405 */         i0 = i1;
/* 1406 */         firstCoord = ls.getCoordinateN(i0);
/*      */       } 
/* 1408 */       i1++;
/* 1409 */       i2++;
/*      */     } 
/* 1411 */     retain.add(ls.getCoordinateN(N - 1));
/* 1416 */     int size = retain.size();
/* 1418 */     if (size == N) {
/* 1420 */       retain.clear();
/* 1422 */       return ls;
/*      */     } 
/* 1425 */     return isLinearRing ? (LineString)ls.getFactory().createLinearRing(retain.<Coordinate>toArray(new Coordinate[size])) : ls.getFactory().createLineString(retain.<Coordinate>toArray(new Coordinate[size]));
/*      */   }
/*      */   
/*      */   static Polygon removeCollinearVertices(Polygon polygon) {
/* 1437 */     if (polygon == null)
/* 1438 */       throw new NullPointerException("The provided Polygon is null"); 
/* 1442 */     GeometryFactory gf = polygon.getFactory();
/* 1445 */     LineString exterior = polygon.getExteriorRing();
/* 1446 */     LineString shell = removeCollinearVertices(exterior);
/* 1447 */     if (shell == null || shell.isEmpty())
/* 1448 */       return null; 
/* 1452 */     List<LineString> holes = new ArrayList<LineString>();
/* 1453 */     int size = polygon.getNumInteriorRing();
/* 1454 */     for (int i = 0; i < size; i++) {
/* 1455 */       LineString hole = polygon.getInteriorRingN(i);
/* 1456 */       hole = removeCollinearVertices(hole);
/* 1457 */       if (hole != null && !hole.isEmpty())
/* 1458 */         holes.add(hole); 
/*      */     } 
/* 1462 */     return gf.createPolygon((LinearRing)shell, holes.<LinearRing>toArray(new LinearRing[holes.size()]));
/*      */   }
/*      */   
/*      */   public static Geometry removeCollinearVertices(Geometry g) {
/* 1476 */     if (g == null)
/* 1477 */       throw new NullPointerException("The provided Geometry is null"); 
/* 1479 */     if (g instanceof LineString)
/* 1480 */       return (Geometry)removeCollinearVertices((LineString)g); 
/* 1481 */     if (g instanceof Polygon)
/* 1482 */       return (Geometry)removeCollinearVertices((Polygon)g); 
/* 1483 */     if (g instanceof MultiPolygon) {
/* 1484 */       MultiPolygon mp = (MultiPolygon)g;
/* 1485 */       Polygon[] parts = new Polygon[mp.getNumGeometries()];
/* 1486 */       for (int i = 0; i < mp.getNumGeometries(); i++) {
/* 1487 */         Polygon part = (Polygon)mp.getGeometryN(i);
/* 1488 */         part = removeCollinearVertices(part);
/* 1489 */         parts[i] = part;
/*      */       } 
/* 1492 */       return (Geometry)g.getFactory().createMultiPolygon(parts);
/*      */     } 
/* 1495 */     throw new IllegalArgumentException("This method can work on LineString, Polygon and Multipolygon: " + g.getClass());
/*      */   }
/*      */   
/*      */   public static Geometry removeCollinearVertices(Geometry geometry, int minPoints) {
/* 1511 */     ensureNonNull("geometry", geometry);
/* 1513 */     if (minPoints <= 0 || geometry.getNumPoints() < minPoints)
/* 1514 */       return geometry; 
/* 1517 */     if (geometry instanceof LineString)
/* 1518 */       return (Geometry)removeCollinearVertices((LineString)geometry); 
/* 1519 */     if (geometry instanceof Polygon)
/* 1520 */       return (Geometry)removeCollinearVertices((Polygon)geometry); 
/* 1521 */     if (geometry instanceof MultiPolygon) {
/* 1522 */       MultiPolygon mp = (MultiPolygon)geometry;
/* 1523 */       Polygon[] parts = new Polygon[mp.getNumGeometries()];
/* 1524 */       for (int i = 0; i < mp.getNumGeometries(); i++) {
/* 1525 */         Polygon part = (Polygon)mp.getGeometryN(i);
/* 1526 */         part = removeCollinearVertices(part);
/* 1527 */         parts[i] = part;
/*      */       } 
/* 1530 */       return (Geometry)geometry.getFactory().createMultiPolygon(parts);
/*      */     } 
/* 1533 */     throw new IllegalArgumentException("This method can work on LineString, Polygon and Multipolygon: " + geometry.getClass());
/*      */   }
/*      */   
/*      */   public static List<Polygon> makeValid(Polygon polygon, boolean removeHoles) {
/* 1546 */     final Polygonizer p = new Polygonizer();
/* 1547 */     polygon.apply(new CoordinateSequenceFilter() {
/*      */           public boolean isGeometryChanged() {
/* 1550 */             return false;
/*      */           }
/*      */           
/*      */           public boolean isDone() {
/* 1554 */             return false;
/*      */           }
/*      */           
/*      */           public void filter(CoordinateSequence seq, int i) {
/* 1558 */             if (i == 0)
/*      */               return; 
/* 1561 */             p.add((Geometry)(new GeometryFactory()).createLineString(new Coordinate[] { seq.getCoordinate(i - 1), seq.getCoordinate(i) }));
/*      */           }
/*      */         });
/* 1566 */     List<Polygon> result = new ArrayList<Polygon>(p.getPolygons());
/* 1569 */     if (removeHoles)
/* 1570 */       for (int i = 0; i < result.size(); i++) {
/* 1571 */         Polygon item = result.get(i);
/* 1572 */         if (item.getNumInteriorRing() > 0) {
/* 1573 */           GeometryFactory factory = item.getFactory();
/* 1574 */           Polygon noHoles = factory.createPolygon((LinearRing)item.getExteriorRing(), null);
/* 1576 */           result.set(i, noHoles);
/*      */         } 
/*      */       }  
/* 1581 */     return result;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\geometry\jts\JTS.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */