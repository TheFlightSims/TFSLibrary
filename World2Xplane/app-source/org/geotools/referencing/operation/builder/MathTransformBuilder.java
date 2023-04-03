/*     */ package org.geotools.referencing.operation.builder;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.StringWriter;
/*     */ import java.io.Writer;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import javax.vecmath.MismatchedSizeException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.geometry.GeneralDirectPosition;
/*     */ import org.geotools.geometry.GeneralEnvelope;
/*     */ import org.geotools.io.TableWriter;
/*     */ import org.geotools.math.Statistics;
/*     */ import org.geotools.metadata.iso.extent.ExtentImpl;
/*     */ import org.geotools.metadata.iso.extent.GeographicBoundingBoxImpl;
/*     */ import org.geotools.metadata.iso.quality.PositionalAccuracyImpl;
/*     */ import org.geotools.metadata.iso.quality.QuantitativeResultImpl;
/*     */ import org.geotools.referencing.CRS;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.cs.DefaultCartesianCS;
/*     */ import org.geotools.referencing.operation.DefaultOperationMethod;
/*     */ import org.geotools.referencing.operation.DefaultTransformation;
/*     */ import org.geotools.resources.CRSUtilities;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.geometry.DirectPosition;
/*     */ import org.opengis.geometry.Envelope;
/*     */ import org.opengis.geometry.MismatchedDimensionException;
/*     */ import org.opengis.geometry.MismatchedReferenceSystemException;
/*     */ import org.opengis.metadata.extent.GeographicBoundingBox;
/*     */ import org.opengis.metadata.quality.EvaluationMethodType;
/*     */ import org.opengis.metadata.quality.Result;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CRSFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.EngineeringCRS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.datum.DatumFactory;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransformFactory;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.TransformException;
/*     */ import org.opengis.referencing.operation.Transformation;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public abstract class MathTransformBuilder {
/* 116 */   private final List<MappedPosition> positions = new ArrayList<MappedPosition>();
/*     */   
/* 121 */   private final List<MappedPosition> unmodifiablePositions = Collections.unmodifiableList(this.positions);
/*     */   
/*     */   private CoordinateReferenceSystem sourceCRS;
/*     */   
/*     */   private CoordinateReferenceSystem targetCRS;
/*     */   
/*     */   private transient MathTransform transform;
/*     */   
/*     */   private transient Transformation transformation;
/*     */   
/*     */   protected final MathTransformFactory mtFactory;
/*     */   
/*     */   private final CRSFactory crsFactory;
/*     */   
/*     */   private final DatumFactory datumFactory;
/*     */   
/*     */   public MathTransformBuilder() {
/* 159 */     this(null);
/*     */   }
/*     */   
/*     */   public MathTransformBuilder(Hints hints) {
/* 166 */     this.mtFactory = ReferencingFactoryFinder.getMathTransformFactory(hints);
/* 167 */     this.crsFactory = ReferencingFactoryFinder.getCRSFactory(hints);
/* 168 */     this.datumFactory = ReferencingFactoryFinder.getDatumFactory(hints);
/*     */   }
/*     */   
/*     */   public String getName() {
/* 176 */     return Classes.getShortClassName(this) + " fit";
/*     */   }
/*     */   
/*     */   public abstract int getMinimumPointCount();
/*     */   
/*     */   public int getDimension() {
/* 192 */     return 2;
/*     */   }
/*     */   
/*     */   public List<MappedPosition> getMappedPositions() {
/* 199 */     return this.unmodifiablePositions;
/*     */   }
/*     */   
/*     */   public void setMappedPositions(List<MappedPosition> positions) throws MismatchedSizeException, MismatchedDimensionException, MismatchedReferenceSystemException {
/* 215 */     CoordinateReferenceSystem source = ensureValid(getPoints(positions, false), "sourcePoints");
/* 216 */     CoordinateReferenceSystem target = ensureValid(getPoints(positions, true), "targetPoints");
/* 221 */     this.positions.clear();
/* 222 */     this.positions.addAll(positions);
/* 223 */     this.sourceCRS = source;
/* 224 */     this.targetCRS = target;
/* 225 */     this.transform = null;
/*     */   }
/*     */   
/*     */   private static DirectPosition[] getPoints(List<MappedPosition> positions, boolean target) {
/* 236 */     DirectPosition[] points = new DirectPosition[positions.size()];
/* 237 */     for (int i = 0; i < points.length; i++) {
/* 238 */       MappedPosition mp = positions.get(i);
/* 239 */       points[i] = target ? mp.getTarget() : mp.getSource();
/*     */     } 
/* 241 */     return points;
/*     */   }
/*     */   
/*     */   private void setPoints(DirectPosition[] points, boolean target) throws MismatchedSizeException {
/* 257 */     this.transform = null;
/* 258 */     boolean add = this.positions.isEmpty();
/* 259 */     if (!add && points.length != this.positions.size())
/* 260 */       throw new MismatchedSizeException(Errors.format(91)); 
/* 262 */     int dimension = getDimension();
/* 263 */     for (int i = 0; i < points.length; i++) {
/*     */       MappedPosition mp;
/* 265 */       if (add) {
/* 266 */         mp = new MappedPosition(dimension);
/* 267 */         this.positions.add(mp);
/*     */       } else {
/* 269 */         mp = this.positions.get(i);
/*     */       } 
/* 271 */       DirectPosition point = points[i];
/* 272 */       if (target) {
/* 273 */         mp.setTarget(point);
/*     */       } else {
/* 275 */         mp.setSource(point);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public DirectPosition[] getSourcePoints() {
/* 285 */     DirectPosition[] points = getPoints(getMappedPositions(), false);
/* 286 */     assert ensureValid(points, "sourcePoints", this.sourceCRS);
/* 287 */     return points;
/*     */   }
/*     */   
/*     */   public void setSourcePoints(DirectPosition[] points) throws MismatchedSizeException, MismatchedDimensionException, MismatchedReferenceSystemException {
/* 305 */     this.sourceCRS = ensureValid(points, "sourcePoints");
/* 306 */     setPoints(points, false);
/*     */   }
/*     */   
/*     */   public DirectPosition[] getTargetPoints() {
/* 314 */     DirectPosition[] points = getPoints(getMappedPositions(), true);
/* 315 */     assert ensureValid(points, "targetPoints", this.targetCRS);
/* 316 */     return points;
/*     */   }
/*     */   
/*     */   public void setTargetPoints(DirectPosition[] points) throws MismatchedSizeException, MismatchedDimensionException, MismatchedReferenceSystemException {
/* 334 */     this.targetCRS = ensureValid(points, "targetPoints");
/* 335 */     setPoints(points, true);
/*     */   }
/*     */   
/*     */   public void printPoints(Writer out, Locale locale) throws IOException {
/* 348 */     if (locale == null)
/* 349 */       locale = Locale.getDefault(); 
/* 351 */     NumberFormat source = getNumberFormat(locale, false);
/* 352 */     NumberFormat target = getNumberFormat(locale, true);
/* 353 */     TableWriter table = new TableWriter(out, " │ ");
/* 354 */     table.setAlignment(1);
/* 355 */     table.writeHorizontalSeparator();
/*     */     try {
/* 357 */       CoordinateSystem sourceCS = getSourceCRS().getCoordinateSystem();
/* 358 */       CoordinateSystem targetCS = getTargetCRS().getCoordinateSystem();
/* 359 */       int dimension = sourceCS.getDimension();
/*     */       int i;
/* 360 */       for (i = 0; i < dimension; i++) {
/* 361 */         table.write(sourceCS.getAxis(i).getName().getCode());
/* 362 */         table.nextColumn();
/*     */       } 
/* 364 */       dimension = targetCS.getDimension();
/* 365 */       for (i = 0; i < dimension; i++) {
/* 366 */         table.write(targetCS.getAxis(i).getName().getCode());
/* 367 */         table.nextColumn();
/*     */       } 
/* 369 */       table.writeHorizontalSeparator();
/* 370 */     } catch (FactoryException e) {}
/* 376 */     table.setAlignment(2);
/* 377 */     for (Iterator<MappedPosition> it = getMappedPositions().iterator(); it.hasNext(); ) {
/* 378 */       MappedPosition mp = it.next();
/* 379 */       DirectPosition point = mp.getSource();
/* 380 */       int dimension = point.getDimension();
/*     */       int i;
/* 381 */       for (i = 0; i < dimension; i++) {
/* 382 */         table.write(source.format(point.getOrdinate(i)));
/* 383 */         table.nextColumn();
/*     */       } 
/* 385 */       point = mp.getTarget();
/* 386 */       dimension = point.getDimension();
/* 387 */       for (i = 0; i < dimension; i++) {
/* 388 */         table.write(target.format(point.getOrdinate(i)));
/* 389 */         table.nextColumn();
/*     */       } 
/* 391 */       table.nextLine();
/*     */     } 
/* 393 */     table.writeHorizontalSeparator();
/* 394 */     table.flush();
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getSourceCRS() throws FactoryException {
/* 413 */     if (this.sourceCRS == null)
/* 414 */       this.sourceCRS = (CoordinateReferenceSystem)createEngineeringCRS(false); 
/* 416 */     assert this.sourceCRS.getCoordinateSystem().getDimension() == getDimension();
/* 417 */     return this.sourceCRS;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem getTargetCRS() throws FactoryException {
/* 436 */     if (this.targetCRS == null)
/* 437 */       this.targetCRS = (CoordinateReferenceSystem)createEngineeringCRS(true); 
/* 439 */     assert this.targetCRS.getCoordinateSystem().getDimension() == getDimension();
/* 440 */     return this.targetCRS;
/*     */   }
/*     */   
/*     */   private EngineeringCRS createEngineeringCRS(boolean target) throws FactoryException {
/*     */     DefaultCartesianCS defaultCartesianCS;
/* 455 */     Map<String, Object> properties = new HashMap<String, Object>(4);
/* 456 */     properties.put("name", Vocabulary.format(252));
/* 457 */     GeographicBoundingBox geographicBoundingBox = getValidArea(target);
/* 458 */     if (geographicBoundingBox != null) {
/* 459 */       ExtentImpl extent = new ExtentImpl();
/* 460 */       extent.getGeographicElements().add(geographicBoundingBox);
/* 461 */       properties.put("domainOfValidity", extent.unmodifiable());
/*     */     } 
/* 463 */     CoordinateReferenceSystem oppositeCRS = target ? this.sourceCRS : this.targetCRS;
/* 465 */     if (oppositeCRS != null) {
/* 466 */       CoordinateSystem cs = oppositeCRS.getCoordinateSystem();
/*     */     } else {
/* 468 */       switch (getDimension()) {
/*     */         case 2:
/* 469 */           defaultCartesianCS = DefaultCartesianCS.GENERIC_2D;
/* 474 */           return this.crsFactory.createEngineeringCRS(properties, this.datumFactory.createEngineeringDatum(properties), (CoordinateSystem)defaultCartesianCS);
/*     */         case 3:
/*     */           defaultCartesianCS = DefaultCartesianCS.GENERIC_3D;
/* 474 */           return this.crsFactory.createEngineeringCRS(properties, this.datumFactory.createEngineeringDatum(properties), (CoordinateSystem)defaultCartesianCS);
/*     */       } 
/*     */       throw new FactoryException(Errors.format(193));
/*     */     } 
/* 474 */     return this.crsFactory.createEngineeringCRS(properties, this.datumFactory.createEngineeringDatum(properties), (CoordinateSystem)defaultCartesianCS);
/*     */   }
/*     */   
/*     */   private NumberFormat getNumberFormat(Locale locale, boolean target) {
/* 483 */     NumberFormat format = NumberFormat.getNumberInstance(locale);
/* 484 */     GeneralEnvelope envelope = getEnvelope(target);
/* 485 */     double length = 0.0D;
/* 486 */     for (int i = envelope.getDimension(); --i >= 0; ) {
/* 487 */       double candidate = envelope.getSpan(i);
/* 488 */       if (candidate > length)
/* 489 */         length = candidate; 
/*     */     } 
/* 492 */     if (length > 0.0D) {
/* 493 */       int digits = Math.max(0, 3 - (int)Math.ceil(Math.log10(length)));
/* 494 */       if (digits < 16) {
/* 495 */         format.setMinimumFractionDigits(digits);
/* 496 */         format.setMaximumFractionDigits(digits);
/*     */       } 
/*     */     } 
/* 499 */     return format;
/*     */   }
/*     */   
/*     */   private GeneralEnvelope getEnvelope(boolean target) {
/* 510 */     GeneralEnvelope envelope = null;
/* 511 */     CoordinateReferenceSystem crs = null;
/* 512 */     for (Iterator<MappedPosition> it = getMappedPositions().iterator(); it.hasNext(); ) {
/* 513 */       MappedPosition mp = it.next();
/* 514 */       DirectPosition point = target ? mp.getTarget() : mp.getSource();
/* 515 */       if (point != null) {
/* 516 */         if (envelope == null) {
/* 517 */           double[] coordinates = point.getCoordinate();
/* 518 */           envelope = new GeneralEnvelope(coordinates, coordinates);
/*     */         } else {
/* 520 */           envelope.add(point);
/*     */         } 
/* 522 */         crs = getCoordinateReferenceSystem(point, crs);
/*     */       } 
/*     */     } 
/* 525 */     if (envelope != null)
/* 526 */       envelope.setCoordinateReferenceSystem(crs); 
/* 528 */     return envelope;
/*     */   }
/*     */   
/*     */   private GeographicBoundingBox getValidArea(boolean target) {
/* 539 */     GeneralEnvelope envelope = getEnvelope(target);
/* 540 */     if (envelope != null)
/*     */       try {
/* 541 */         return (GeographicBoundingBox)new GeographicBoundingBoxImpl((Envelope)envelope);
/* 542 */       } catch (TransformException exception) {} 
/* 549 */     return null;
/*     */   }
/*     */   
/*     */   private static CoordinateReferenceSystem getCoordinateReferenceSystem(DirectPosition point, CoordinateReferenceSystem previousCRS) throws MismatchedReferenceSystemException {
/* 560 */     CoordinateReferenceSystem candidate = point.getCoordinateReferenceSystem();
/* 561 */     if (candidate != null) {
/* 562 */       if (previousCRS == null)
/* 563 */         return candidate; 
/* 569 */       if (!previousCRS.equals(candidate))
/* 570 */         throw new MismatchedReferenceSystemException(Errors.format(92)); 
/*     */     } 
/* 574 */     return previousCRS;
/*     */   }
/*     */   
/*     */   public Class<? extends CoordinateSystem> getCoordinateSystemType() {
/* 583 */     return CoordinateSystem.class;
/*     */   }
/*     */   
/*     */   private CoordinateReferenceSystem ensureValid(DirectPosition[] points, String label) throws MismatchedSizeException, MismatchedDimensionException, MismatchedReferenceSystemException {
/* 603 */     int necessaryNumber = getMinimumPointCount();
/* 604 */     if (points.length < necessaryNumber)
/* 605 */       throw new MismatchedSizeException(Errors.format(82, Integer.valueOf(points.length), Integer.valueOf(necessaryNumber))); 
/* 608 */     CoordinateReferenceSystem crs = null;
/* 609 */     int dimension = getDimension();
/* 610 */     for (int i = 0; i < points.length; i++) {
/* 611 */       DirectPosition point = points[i];
/* 612 */       int pointDim = point.getDimension();
/* 613 */       if (pointDim != dimension)
/* 614 */         throw new MismatchedDimensionException(Errors.format(94, label + '[' + i + ']', Integer.valueOf(pointDim), Integer.valueOf(dimension))); 
/* 617 */       crs = getCoordinateReferenceSystem(point, crs);
/*     */     } 
/* 619 */     if (crs != null) {
/* 620 */       CoordinateSystem cs = crs.getCoordinateSystem();
/* 621 */       if (!getCoordinateSystemType().isAssignableFrom(cs.getClass()))
/* 622 */         throw new MismatchedReferenceSystemException(Errors.format(196, cs.getName())); 
/*     */     } 
/* 626 */     return crs;
/*     */   }
/*     */   
/*     */   private boolean ensureValid(DirectPosition[] points, String label, CoordinateReferenceSystem expected) {
/* 635 */     CoordinateReferenceSystem actual = ensureValid(points, label);
/* 636 */     return (actual == null || actual == expected);
/*     */   }
/*     */   
/*     */   public Statistics getErrorStatistics() throws FactoryException {
/* 648 */     MathTransform mt = getMathTransform();
/* 649 */     Statistics stats = new Statistics();
/* 650 */     GeneralDirectPosition generalDirectPosition = new GeneralDirectPosition(getDimension());
/* 651 */     for (Iterator<MappedPosition> it = getMappedPositions().iterator(); it.hasNext(); ) {
/*     */       double error;
/* 652 */       MappedPosition mp = it.next();
/*     */       try {
/* 664 */         error = mp.getError(mt, (DirectPosition)generalDirectPosition);
/* 665 */       } catch (TransformException e) {
/* 666 */         throw new FactoryException(Errors.format(34), e);
/*     */       } 
/* 668 */       stats.add(error);
/*     */     } 
/* 670 */     return stats;
/*     */   }
/*     */   
/*     */   protected abstract MathTransform computeMathTransform() throws FactoryException;
/*     */   
/*     */   public final MathTransform getMathTransform() throws FactoryException {
/* 689 */     if (this.transform == null)
/* 690 */       this.transform = computeMathTransform(); 
/* 692 */     return this.transform;
/*     */   }
/*     */   
/*     */   public Transformation getTransformation() throws FactoryException {
/* 702 */     if (this.transformation == null) {
/*     */       GeographicBoundingBoxImpl geographicBoundingBoxImpl;
/* 703 */       Map<String, Object> properties = new HashMap<String, Object>();
/* 704 */       properties.put("name", getName());
/* 708 */       CoordinateReferenceSystem sourceCRS = getSourceCRS();
/* 709 */       CoordinateReferenceSystem targetCRS = getTargetCRS();
/* 710 */       GeographicBoundingBox sourceBox = CRS.getGeographicBoundingBox(sourceCRS);
/* 711 */       GeographicBoundingBox targetBox = CRS.getGeographicBoundingBox(targetCRS);
/* 713 */       if (sourceBox == null) {
/* 714 */         GeographicBoundingBox validArea = targetBox;
/* 715 */       } else if (targetBox == null) {
/* 716 */         GeographicBoundingBox validArea = sourceBox;
/*     */       } else {
/* 718 */         GeneralEnvelope area = new GeneralEnvelope(sourceBox);
/* 719 */         area.intersect((Envelope)new GeneralEnvelope(sourceBox));
/*     */         try {
/* 721 */           geographicBoundingBoxImpl = new GeographicBoundingBoxImpl((Envelope)area);
/* 722 */         } catch (TransformException e) {
/* 724 */           throw new AssertionError(e);
/*     */         } 
/*     */       } 
/* 727 */       if (geographicBoundingBoxImpl != null) {
/* 728 */         ExtentImpl extent = new ExtentImpl();
/* 729 */         extent.getGeographicElements().add(geographicBoundingBoxImpl);
/* 730 */         properties.put("domainOfValidity", extent.unmodifiable());
/*     */       } 
/* 736 */       double error = getErrorStatistics().rms();
/* 737 */       if (!Double.isNaN(error)) {
/* 738 */         InternationalString description = Vocabulary.formatInternational(184);
/* 740 */         QuantitativeResultImpl result = new QuantitativeResultImpl();
/* 741 */         result.setValues(new double[] { error });
/* 743 */         result.setValueUnit(CRSUtilities.getUnit(targetCRS.getCoordinateSystem()));
/* 744 */         result.setErrorStatistic(description);
/* 745 */         PositionalAccuracyImpl accuracy = new PositionalAccuracyImpl((Result)result);
/* 746 */         accuracy.setEvaluationMethodType(EvaluationMethodType.DIRECT_INTERNAL);
/* 747 */         accuracy.setEvaluationMethodDescription(description);
/* 748 */         properties.put("coordinateOperationAccuracy", accuracy.unmodifiable());
/*     */       } 
/* 753 */       MathTransform transform = getMathTransform();
/* 754 */       this.transformation = (Transformation)new DefaultTransformation(properties, sourceCRS, targetCRS, transform, (OperationMethod)new DefaultOperationMethod(transform));
/*     */     } 
/* 757 */     return this.transformation;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 766 */     StringWriter out = new StringWriter();
/*     */     try {
/* 768 */       printPoints(out, null);
/* 769 */     } catch (IOException e) {
/* 771 */       throw new AssertionError(e);
/*     */     } 
/* 773 */     return out.toString();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\builder\MathTransformBuilder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */