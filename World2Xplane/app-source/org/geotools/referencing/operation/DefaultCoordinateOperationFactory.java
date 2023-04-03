/*      */ package org.geotools.referencing.operation;
/*      */ 
/*      */ import java.util.Collections;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import javax.measure.quantity.Angle;
/*      */ import javax.measure.quantity.Duration;
/*      */ import javax.measure.unit.NonSI;
/*      */ import javax.measure.unit.SI;
/*      */ import javax.measure.unit.Unit;
/*      */ import javax.vecmath.SingularMatrixException;
/*      */ import org.geotools.factory.Hints;
/*      */ import org.geotools.referencing.AbstractIdentifiedObject;
/*      */ import org.geotools.referencing.CRS;
/*      */ import org.geotools.referencing.crs.DefaultCompoundCRS;
/*      */ import org.geotools.referencing.crs.DefaultEngineeringCRS;
/*      */ import org.geotools.referencing.cs.DefaultCartesianCS;
/*      */ import org.geotools.referencing.cs.DefaultEllipsoidalCS;
/*      */ import org.geotools.referencing.datum.BursaWolfParameters;
/*      */ import org.geotools.referencing.datum.DefaultGeodeticDatum;
/*      */ import org.geotools.referencing.datum.DefaultPrimeMeridian;
/*      */ import org.geotools.referencing.factory.ReferencingFactoryContainer;
/*      */ import org.geotools.referencing.operation.matrix.Matrix4;
/*      */ import org.geotools.referencing.operation.matrix.MatrixFactory;
/*      */ import org.geotools.referencing.operation.matrix.XMatrix;
/*      */ import org.geotools.resources.Classes;
/*      */ import org.geotools.resources.i18n.Errors;
/*      */ import org.opengis.parameter.ParameterValueGroup;
/*      */ import org.opengis.referencing.FactoryException;
/*      */ import org.opengis.referencing.IdentifiedObject;
/*      */ import org.opengis.referencing.ReferenceIdentifier;
/*      */ import org.opengis.referencing.crs.CRSFactory;
/*      */ import org.opengis.referencing.crs.CompoundCRS;
/*      */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*      */ import org.opengis.referencing.crs.GeneralDerivedCRS;
/*      */ import org.opengis.referencing.crs.GeocentricCRS;
/*      */ import org.opengis.referencing.crs.GeographicCRS;
/*      */ import org.opengis.referencing.crs.ProjectedCRS;
/*      */ import org.opengis.referencing.crs.SingleCRS;
/*      */ import org.opengis.referencing.crs.TemporalCRS;
/*      */ import org.opengis.referencing.crs.VerticalCRS;
/*      */ import org.opengis.referencing.cs.AxisDirection;
/*      */ import org.opengis.referencing.cs.CartesianCS;
/*      */ import org.opengis.referencing.cs.CoordinateSystem;
/*      */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*      */ import org.opengis.referencing.cs.EllipsoidalCS;
/*      */ import org.opengis.referencing.cs.TimeCS;
/*      */ import org.opengis.referencing.cs.VerticalCS;
/*      */ import org.opengis.referencing.datum.Datum;
/*      */ import org.opengis.referencing.datum.Ellipsoid;
/*      */ import org.opengis.referencing.datum.GeodeticDatum;
/*      */ import org.opengis.referencing.datum.PrimeMeridian;
/*      */ import org.opengis.referencing.datum.TemporalDatum;
/*      */ import org.opengis.referencing.datum.VerticalDatum;
/*      */ import org.opengis.referencing.datum.VerticalDatumType;
/*      */ import org.opengis.referencing.operation.Conversion;
/*      */ import org.opengis.referencing.operation.CoordinateOperation;
/*      */ import org.opengis.referencing.operation.MathTransform;
/*      */ import org.opengis.referencing.operation.Matrix;
/*      */ import org.opengis.referencing.operation.NoninvertibleTransformException;
/*      */ import org.opengis.referencing.operation.Operation;
/*      */ import org.opengis.referencing.operation.OperationMethod;
/*      */ import org.opengis.referencing.operation.OperationNotFoundException;
/*      */ import org.opengis.referencing.operation.Projection;
/*      */ 
/*      */ public class DefaultCoordinateOperationFactory extends AbstractCoordinateOperationFactory {
/*      */   static final int PRIORITY = 50;
/*      */   
/*      */   private static final double EPS = 1.0E-10D;
/*      */   
/*   92 */   private static final Unit<Duration> MILLISECOND = SI.MILLI((Unit)SI.SECOND);
/*      */   
/*      */   private final String molodenskiMethod;
/*      */   
/*      */   private final boolean lenientDatumShift;
/*      */   
/*      */   public DefaultCoordinateOperationFactory() {
/*  115 */     this((Hints)null);
/*      */   }
/*      */   
/*      */   public DefaultCoordinateOperationFactory(Hints userHints) {
/*  127 */     this(userHints, 50);
/*      */   }
/*      */   
/*      */   public DefaultCoordinateOperationFactory(Hints userHints, int priority) {
/*  144 */     super(userHints, priority);
/*  148 */     String molodenskiMethod = "Molodenski";
/*  149 */     boolean lenientDatumShift = false;
/*  153 */     if (userHints != null) {
/*  154 */       Object candidate = userHints.get(Hints.DATUM_SHIFT_METHOD);
/*  155 */       if (candidate instanceof String) {
/*  156 */         molodenskiMethod = (String)candidate;
/*  157 */         if (molodenskiMethod.trim().equalsIgnoreCase("Geocentric"))
/*  158 */           molodenskiMethod = null; 
/*      */       } 
/*  161 */       candidate = userHints.get(Hints.LENIENT_DATUM_SHIFT);
/*  162 */       if (candidate instanceof Boolean)
/*  163 */         lenientDatumShift = ((Boolean)candidate).booleanValue(); 
/*      */     } 
/*  169 */     this.molodenskiMethod = molodenskiMethod;
/*  170 */     this.lenientDatumShift = lenientDatumShift;
/*  171 */     this.hints.put(Hints.DATUM_SHIFT_METHOD, molodenskiMethod);
/*  172 */     this.hints.put(Hints.LENIENT_DATUM_SHIFT, Boolean.valueOf(lenientDatumShift));
/*      */   }
/*      */   
/*      */   public CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) throws OperationNotFoundException, FactoryException {
/*  195 */     ensureNonNull("sourceCRS", sourceCRS);
/*  196 */     ensureNonNull("targetCRS", targetCRS);
/*  197 */     if (CRS.equalsIgnoreMetadata(sourceCRS, targetCRS)) {
/*  198 */       int dim = getDimension(sourceCRS);
/*  199 */       assert dim == getDimension(targetCRS) : dim;
/*  200 */       return createFromAffineTransform(IDENTITY, sourceCRS, targetCRS, (Matrix)MatrixFactory.create(dim + 1));
/*      */     } 
/*  204 */     CoordinateOperation candidate = createFromDatabase(sourceCRS, targetCRS);
/*  205 */     if (candidate != null)
/*  206 */       return candidate; 
/*  216 */     if (isWildcard(sourceCRS) || isWildcard(targetCRS)) {
/*  218 */       int dimSource = getDimension(sourceCRS);
/*  219 */       int dimTarget = getDimension(targetCRS);
/*  220 */       if (dimTarget == dimSource) {
/*  221 */         XMatrix xMatrix = MatrixFactory.create(dimTarget + 1, dimSource + 1);
/*  222 */         return createFromAffineTransform(IDENTITY, sourceCRS, targetCRS, (Matrix)xMatrix);
/*      */       } 
/*      */     } 
/*  234 */     if (sourceCRS instanceof CompoundCRS) {
/*  235 */       List<SingleCRS> sources = DefaultCompoundCRS.getSingleCRS(sourceCRS);
/*  236 */       List<SingleCRS> targets = DefaultCompoundCRS.getSingleCRS(targetCRS);
/*  237 */       if (containsIgnoreMetadata(sources, targets)) {
/*  238 */         CompoundCRS source = (CompoundCRS)sourceCRS;
/*  239 */         if (targetCRS instanceof CompoundCRS) {
/*  240 */           CompoundCRS target = (CompoundCRS)targetCRS;
/*  241 */           return createOperationStep(source, target);
/*      */         } 
/*  243 */         if (targetCRS instanceof SingleCRS) {
/*  244 */           SingleCRS target = (SingleCRS)targetCRS;
/*  245 */           return createOperationStep(source, target);
/*      */         } 
/*      */       } 
/*      */     } 
/*  254 */     if (sourceCRS instanceof GeographicCRS) {
/*  255 */       GeographicCRS source = (GeographicCRS)sourceCRS;
/*  256 */       if (targetCRS instanceof GeographicCRS) {
/*  257 */         GeographicCRS target = (GeographicCRS)targetCRS;
/*  258 */         return createOperationStep(source, target);
/*      */       } 
/*  260 */       if (targetCRS instanceof ProjectedCRS) {
/*  261 */         ProjectedCRS target = (ProjectedCRS)targetCRS;
/*  262 */         return createOperationStep(source, target);
/*      */       } 
/*  264 */       if (targetCRS instanceof GeocentricCRS) {
/*  265 */         GeocentricCRS target = (GeocentricCRS)targetCRS;
/*  266 */         return createOperationStep(source, target);
/*      */       } 
/*  268 */       if (targetCRS instanceof VerticalCRS) {
/*  269 */         VerticalCRS target = (VerticalCRS)targetCRS;
/*  270 */         return createOperationStep(source, target);
/*      */       } 
/*      */     } 
/*  278 */     if (sourceCRS instanceof ProjectedCRS) {
/*  279 */       ProjectedCRS source = (ProjectedCRS)sourceCRS;
/*  280 */       if (targetCRS instanceof ProjectedCRS) {
/*  281 */         ProjectedCRS target = (ProjectedCRS)targetCRS;
/*  282 */         return createOperationStep(source, target);
/*      */       } 
/*  284 */       if (targetCRS instanceof GeographicCRS) {
/*  285 */         GeographicCRS target = (GeographicCRS)targetCRS;
/*  286 */         return createOperationStep(source, target);
/*      */       } 
/*      */     } 
/*  294 */     if (sourceCRS instanceof GeocentricCRS) {
/*  295 */       GeocentricCRS source = (GeocentricCRS)sourceCRS;
/*  296 */       if (targetCRS instanceof GeocentricCRS) {
/*  297 */         GeocentricCRS target = (GeocentricCRS)targetCRS;
/*  298 */         return createOperationStep(source, target);
/*      */       } 
/*  300 */       if (targetCRS instanceof GeographicCRS) {
/*  301 */         GeographicCRS target = (GeographicCRS)targetCRS;
/*  302 */         return createOperationStep(source, target);
/*      */       } 
/*      */     } 
/*  310 */     if (sourceCRS instanceof VerticalCRS) {
/*  311 */       VerticalCRS source = (VerticalCRS)sourceCRS;
/*  312 */       if (targetCRS instanceof VerticalCRS) {
/*  313 */         VerticalCRS target = (VerticalCRS)targetCRS;
/*  314 */         return createOperationStep(source, target);
/*      */       } 
/*      */     } 
/*  322 */     if (sourceCRS instanceof TemporalCRS) {
/*  323 */       TemporalCRS source = (TemporalCRS)sourceCRS;
/*  324 */       if (targetCRS instanceof TemporalCRS) {
/*  325 */         TemporalCRS target = (TemporalCRS)targetCRS;
/*  326 */         return createOperationStep(source, target);
/*      */       } 
/*      */     } 
/*  334 */     if (targetCRS instanceof GeneralDerivedCRS) {
/*  338 */       GeneralDerivedCRS target = (GeneralDerivedCRS)targetCRS;
/*  339 */       CoordinateReferenceSystem base = target.getBaseCRS();
/*  340 */       CoordinateOperation step1 = createOperation(sourceCRS, base);
/*  341 */       Conversion conversion = target.getConversionFromBase();
/*  342 */       return concatenate(step1, (CoordinateOperation)conversion);
/*      */     } 
/*  349 */     if (sourceCRS instanceof GeneralDerivedCRS) {
/*  353 */       GeneralDerivedCRS source = (GeneralDerivedCRS)sourceCRS;
/*  354 */       CoordinateReferenceSystem base = source.getBaseCRS();
/*  355 */       CoordinateOperation step2 = createOperation(base, targetCRS);
/*  356 */       Conversion conversion = source.getConversionFromBase();
/*  357 */       MathTransform transform = conversion.getMathTransform();
/*      */       try {
/*  359 */         transform = transform.inverse();
/*  360 */       } catch (NoninvertibleTransformException exception) {
/*  361 */         throw new OperationNotFoundException(getErrorMessage(sourceCRS, base), exception);
/*      */       } 
/*  363 */       CoordinateOperation coordinateOperation1 = createFromMathTransform(INVERSE_OPERATION, sourceCRS, base, transform);
/*  364 */       return concatenate(coordinateOperation1, step2);
/*      */     } 
/*  371 */     if (sourceCRS instanceof CompoundCRS) {
/*  372 */       CompoundCRS source = (CompoundCRS)sourceCRS;
/*  373 */       if (targetCRS instanceof CompoundCRS) {
/*  374 */         CompoundCRS target = (CompoundCRS)targetCRS;
/*  375 */         return createOperationStep(source, target);
/*      */       } 
/*  377 */       if (targetCRS instanceof SingleCRS) {
/*  378 */         SingleCRS target = (SingleCRS)targetCRS;
/*  379 */         return createOperationStep(source, target);
/*      */       } 
/*      */     } 
/*  382 */     if (targetCRS instanceof CompoundCRS) {
/*  383 */       CompoundCRS target = (CompoundCRS)targetCRS;
/*  384 */       if (sourceCRS instanceof SingleCRS) {
/*  385 */         SingleCRS source = (SingleCRS)sourceCRS;
/*  386 */         return createOperationStep(source, target);
/*      */       } 
/*      */     } 
/*  389 */     throw new OperationNotFoundException(getErrorMessage(sourceCRS, targetCRS));
/*      */   }
/*      */   
/*      */   boolean isWildcard(CoordinateReferenceSystem sourceCRS) {
/*  397 */     return (sourceCRS instanceof DefaultEngineeringCRS && ((DefaultEngineeringCRS)sourceCRS).isWildcard());
/*      */   }
/*      */   
/*      */   public CoordinateOperation createOperation(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS, OperationMethod method) throws OperationNotFoundException, FactoryException {
/*  424 */     return createOperation(sourceCRS, targetCRS);
/*      */   }
/*      */   
/*      */   private GeocentricCRS normalize(GeocentricCRS crs, GeodeticDatum datum) throws FactoryException {
/*  454 */     DefaultCartesianCS defaultCartesianCS = DefaultCartesianCS.GEOCENTRIC;
/*  455 */     GeodeticDatum candidate = crs.getDatum();
/*  456 */     if (equalsIgnorePrimeMeridian(candidate, datum) && 
/*  457 */       getGreenwichLongitude(candidate.getPrimeMeridian()) == getGreenwichLongitude(datum.getPrimeMeridian()))
/*  460 */       if (hasStandardAxis(crs.getCoordinateSystem(), (CoordinateSystem)defaultCartesianCS))
/*  461 */         return crs;  
/*  465 */     CRSFactory crsFactory = getFactoryContainer().getCRSFactory();
/*  466 */     return crsFactory.createGeocentricCRS(getTemporaryName((IdentifiedObject)crs), datum, (CartesianCS)defaultCartesianCS);
/*      */   }
/*      */   
/*      */   private GeographicCRS normalize(GeographicCRS crs, boolean forceGreenwich) throws FactoryException {
/*      */     TemporaryDatum temporaryDatum;
/*  485 */     GeodeticDatum datum = crs.getDatum();
/*  486 */     EllipsoidalCS cs = crs.getCoordinateSystem();
/*  487 */     DefaultEllipsoidalCS defaultEllipsoidalCS = (cs.getDimension() <= 2) ? DefaultEllipsoidalCS.GEODETIC_2D : DefaultEllipsoidalCS.GEODETIC_3D;
/*  490 */     if (forceGreenwich && getGreenwichLongitude(datum.getPrimeMeridian()) != 0.0D) {
/*  491 */       temporaryDatum = new TemporaryDatum(datum);
/*  492 */     } else if (hasStandardAxis((CoordinateSystem)cs, (CoordinateSystem)defaultEllipsoidalCS)) {
/*  493 */       return crs;
/*      */     } 
/*  499 */     CRSFactory crsFactory = getFactoryContainer().getCRSFactory();
/*  500 */     return crsFactory.createGeographicCRS(getTemporaryName((IdentifiedObject)crs), (GeodeticDatum)temporaryDatum, (EllipsoidalCS)defaultEllipsoidalCS);
/*      */   }
/*      */   
/*      */   private static final class TemporaryDatum extends DefaultGeodeticDatum {
/*      */     private static final long serialVersionUID = -8964199103509187219L;
/*      */     
/*      */     private final GeodeticDatum datum;
/*      */     
/*      */     public TemporaryDatum(GeodeticDatum datum) {
/*  516 */       super(AbstractCoordinateOperationFactory.getTemporaryName((IdentifiedObject)datum), datum.getEllipsoid(), (PrimeMeridian)DefaultPrimeMeridian.GREENWICH);
/*  517 */       this.datum = datum;
/*      */     }
/*      */     
/*      */     public static GeodeticDatum unwrap(GeodeticDatum datum) {
/*  522 */       while (datum instanceof TemporaryDatum)
/*  523 */         datum = ((TemporaryDatum)datum).datum; 
/*  525 */       return datum;
/*      */     }
/*      */     
/*      */     public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/*  533 */       if (super.equals(object, compareMetadata)) {
/*  534 */         GeodeticDatum other = ((TemporaryDatum)object).datum;
/*  535 */         return compareMetadata ? this.datum.equals(other) : CRS.equalsIgnoreMetadata(this.datum, other);
/*      */       } 
/*  537 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   private static boolean hasStandardAxis(CoordinateSystem cs, CoordinateSystem standard) {
/*  553 */     int dimension = standard.getDimension();
/*  554 */     if (cs.getDimension() != dimension)
/*  555 */       return false; 
/*  557 */     for (int i = 0; i < dimension; i++) {
/*  558 */       CoordinateSystemAxis a1 = cs.getAxis(i);
/*  559 */       CoordinateSystemAxis a2 = standard.getAxis(i);
/*  560 */       if (!a1.getDirection().equals(a2.getDirection()) || !a1.getUnit().equals(a2.getUnit()))
/*  563 */         return false; 
/*      */     } 
/*  566 */     return true;
/*      */   }
/*      */   
/*      */   private Matrix swapAndScaleAxis(EllipsoidalCS sourceCS, EllipsoidalCS targetCS, PrimeMeridian sourcePM, PrimeMeridian targetPM) throws OperationNotFoundException {
/*  601 */     Matrix matrix = swapAndScaleAxis((CoordinateSystem)sourceCS, (CoordinateSystem)targetCS);
/*  602 */     for (int i = targetCS.getDimension(); --i >= 0; ) {
/*  603 */       CoordinateSystemAxis axis = targetCS.getAxis(i);
/*  604 */       AxisDirection direction = axis.getDirection();
/*  605 */       if (AxisDirection.EAST.equals(direction.absolute())) {
/*  614 */         Unit<Angle> unit = axis.getUnit().asType(Angle.class);
/*  615 */         double sourceLongitude = getGreenwichLongitude(sourcePM, unit);
/*  616 */         double targetLongitude = getGreenwichLongitude(targetPM, unit);
/*  617 */         int lastMatrixColumn = matrix.getNumCol() - 1;
/*  618 */         double rotate = sourceLongitude - targetLongitude;
/*  619 */         if (AxisDirection.WEST.equals(direction))
/*  620 */           rotate = -rotate; 
/*  622 */         rotate += matrix.getElement(i, lastMatrixColumn);
/*  623 */         matrix.setElement(i, lastMatrixColumn, rotate);
/*      */       } 
/*      */     } 
/*  626 */     return matrix;
/*      */   }
/*      */   
/*      */   private static double getGreenwichLongitude(PrimeMeridian pm, Unit<Angle> unit) {
/*  634 */     return pm.getAngularUnit().getConverterTo(unit).convert(pm.getGreenwichLongitude());
/*      */   }
/*      */   
/*      */   private static double getGreenwichLongitude(PrimeMeridian pm) {
/*  641 */     return getGreenwichLongitude(pm, NonSI.DEGREE_ANGLE);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(TemporalCRS sourceCRS, TemporalCRS targetCRS) throws FactoryException {
/*  669 */     TemporalDatum sourceDatum = sourceCRS.getDatum();
/*  670 */     TemporalDatum targetDatum = targetCRS.getDatum();
/*  671 */     if (!CRS.equalsIgnoreMetadata(sourceDatum, targetDatum))
/*  672 */       throw new OperationNotFoundException(getErrorMessage(sourceDatum, targetDatum)); 
/*  680 */     TimeCS sourceCS = sourceCRS.getCoordinateSystem();
/*  681 */     TimeCS targetCS = targetCRS.getCoordinateSystem();
/*  682 */     Unit targetUnit = targetCS.getAxis(0).getUnit();
/*  683 */     double epochShift = (sourceDatum.getOrigin().getTime() - targetDatum.getOrigin().getTime());
/*  685 */     epochShift = MILLISECOND.getConverterTo(targetUnit).convert(epochShift);
/*  697 */     Matrix matrix = swapAndScaleAxis((CoordinateSystem)sourceCS, (CoordinateSystem)targetCS);
/*  698 */     int translationColumn = matrix.getNumCol() - 1;
/*  699 */     if (translationColumn >= 0) {
/*  700 */       double translation = matrix.getElement(0, translationColumn);
/*  701 */       matrix.setElement(0, translationColumn, translation + epochShift);
/*      */     } 
/*  703 */     return createFromAffineTransform(AXIS_CHANGES, (CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)targetCRS, matrix);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(VerticalCRS sourceCRS, VerticalCRS targetCRS) throws FactoryException {
/*  720 */     VerticalDatum sourceDatum = sourceCRS.getDatum();
/*  721 */     VerticalDatum targetDatum = targetCRS.getDatum();
/*  722 */     if (!CRS.equalsIgnoreMetadata(sourceDatum, targetDatum))
/*  723 */       throw new OperationNotFoundException(getErrorMessage(sourceDatum, targetDatum)); 
/*  725 */     VerticalCS sourceCS = sourceCRS.getCoordinateSystem();
/*  726 */     VerticalCS targetCS = targetCRS.getCoordinateSystem();
/*  727 */     Matrix matrix = swapAndScaleAxis((CoordinateSystem)sourceCS, (CoordinateSystem)targetCS);
/*  728 */     return createFromAffineTransform(AXIS_CHANGES, (CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)targetCRS, matrix);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(GeographicCRS sourceCRS, VerticalCRS targetCRS) throws FactoryException {
/*  749 */     if (VerticalDatumType.ELLIPSOIDAL.equals(targetCRS.getDatum().getVerticalDatumType())) {
/*  750 */       Matrix matrix = swapAndScaleAxis((CoordinateSystem)sourceCRS.getCoordinateSystem(), (CoordinateSystem)targetCRS.getCoordinateSystem());
/*  752 */       return createFromAffineTransform(AXIS_CHANGES, (CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)targetCRS, matrix);
/*      */     } 
/*  754 */     throw new OperationNotFoundException(getErrorMessage(sourceCRS, targetCRS));
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(GeographicCRS sourceCRS, GeographicCRS targetCRS) throws FactoryException {
/*      */     GeocentricCRS stepCRS;
/*  775 */     EllipsoidalCS sourceCS = sourceCRS.getCoordinateSystem();
/*  776 */     EllipsoidalCS targetCS = targetCRS.getCoordinateSystem();
/*  777 */     GeodeticDatum sourceDatum = sourceCRS.getDatum();
/*  778 */     GeodeticDatum targetDatum = targetCRS.getDatum();
/*  779 */     PrimeMeridian sourcePM = sourceDatum.getPrimeMeridian();
/*  780 */     PrimeMeridian targetPM = targetDatum.getPrimeMeridian();
/*  781 */     if (equalsIgnorePrimeMeridian(sourceDatum, targetDatum)) {
/*  790 */       Matrix matrix = swapAndScaleAxis(sourceCS, targetCS, sourcePM, targetPM);
/*  791 */       return createFromAffineTransform(AXIS_CHANGES, (CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)targetCRS, matrix);
/*      */     } 
/*  801 */     if (this.molodenskiMethod != null) {
/*  802 */       ReferenceIdentifier identifier = DATUM_SHIFT;
/*  803 */       BursaWolfParameters bursaWolf = null;
/*  804 */       if (sourceDatum instanceof DefaultGeodeticDatum)
/*  805 */         bursaWolf = ((DefaultGeodeticDatum)sourceDatum).getBursaWolfParameters(targetDatum); 
/*  807 */       if (bursaWolf == null) {
/*  812 */         Matrix shift = DefaultGeodeticDatum.getAffineTransform(sourceDatum, targetDatum);
/*  813 */         if (shift != null) {
/*      */           try {
/*  814 */             bursaWolf = new BursaWolfParameters(targetDatum);
/*  815 */             bursaWolf.setAffineTransform(shift, 1.0E-4D);
/*  816 */           } catch (IllegalArgumentException ignore) {}
/*  822 */         } else if (this.lenientDatumShift) {
/*  830 */           bursaWolf = new BursaWolfParameters(targetDatum);
/*  831 */           identifier = ELLIPSOID_SHIFT;
/*      */         } 
/*      */       } 
/*  839 */       if (bursaWolf != null && bursaWolf.isTranslation()) {
/*  840 */         Ellipsoid sourceEllipsoid = sourceDatum.getEllipsoid();
/*  841 */         Ellipsoid targetEllipsoid = targetDatum.getEllipsoid();
/*  842 */         if (bursaWolf.isIdentity() && CRS.equalsIgnoreMetadata(sourceEllipsoid, targetEllipsoid)) {
/*  843 */           Matrix matrix = swapAndScaleAxis(sourceCS, targetCS, sourcePM, targetPM);
/*  844 */           return createFromAffineTransform(identifier, (CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)targetCRS, matrix);
/*      */         } 
/*  846 */         int sourceDim = getDimension((CoordinateReferenceSystem)sourceCRS);
/*  847 */         int targetDim = getDimension((CoordinateReferenceSystem)targetCRS);
/*  849 */         ParameterValueGroup parameters = getMathTransformFactory().getDefaultParameters(this.molodenskiMethod);
/*  850 */         parameters.parameter("src_semi_major").setValue(sourceEllipsoid.getSemiMajorAxis());
/*  851 */         parameters.parameter("src_semi_minor").setValue(sourceEllipsoid.getSemiMinorAxis());
/*  852 */         parameters.parameter("tgt_semi_major").setValue(targetEllipsoid.getSemiMajorAxis());
/*  853 */         parameters.parameter("tgt_semi_minor").setValue(targetEllipsoid.getSemiMinorAxis());
/*  854 */         parameters.parameter("dx").setValue(bursaWolf.dx);
/*  855 */         parameters.parameter("dy").setValue(bursaWolf.dy);
/*  856 */         parameters.parameter("dz").setValue(bursaWolf.dz);
/*  857 */         parameters.parameter("dim").setValue(sourceDim);
/*  858 */         if (sourceDim == targetDim) {
/*  860 */           GeographicCRS normSourceCRS = normalize(sourceCRS, true);
/*  861 */           GeographicCRS normTargetCRS = normalize(targetCRS, true);
/*  862 */           CoordinateOperation coordinateOperation1 = createOperationStep(sourceCRS, normSourceCRS);
/*  863 */           CoordinateOperation coordinateOperation2 = createFromParameters(identifier, (CoordinateReferenceSystem)normSourceCRS, (CoordinateReferenceSystem)normTargetCRS, parameters);
/*  864 */           CoordinateOperation step3 = createOperationStep(normTargetCRS, targetCRS);
/*  865 */           return concatenate(coordinateOperation1, coordinateOperation2, step3);
/*      */         } 
/*      */       } 
/*      */     } 
/*  881 */     DefaultCartesianCS defaultCartesianCS = DefaultCartesianCS.GEOCENTRIC;
/*  883 */     CRSFactory crsFactory = getFactoryContainer().getCRSFactory();
/*  884 */     if (getGreenwichLongitude(targetPM) == 0.0D) {
/*  885 */       stepCRS = crsFactory.createGeocentricCRS(getTemporaryName((IdentifiedObject)targetCRS), targetDatum, (CartesianCS)defaultCartesianCS);
/*      */     } else {
/*  888 */       stepCRS = crsFactory.createGeocentricCRS(getTemporaryName((IdentifiedObject)sourceCRS), sourceDatum, (CartesianCS)defaultCartesianCS);
/*      */     } 
/*  891 */     CoordinateOperation step1 = createOperationStep(sourceCRS, stepCRS);
/*  892 */     CoordinateOperation step2 = createOperationStep(stepCRS, targetCRS);
/*  893 */     return concatenate(step1, step2);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(ProjectedCRS sourceCRS, ProjectedCRS targetCRS) throws FactoryException {
/*  929 */     Matrix linear = ProjectionAnalyzer.createLinearConversion(sourceCRS, targetCRS, 1.0E-10D);
/*  930 */     if (linear != null)
/*  931 */       return createFromAffineTransform(AXIS_CHANGES, (CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)targetCRS, linear); 
/*  941 */     GeographicCRS sourceGeo = sourceCRS.getBaseCRS();
/*  942 */     GeographicCRS targetGeo = targetCRS.getBaseCRS();
/*  944 */     CoordinateOperation step1 = tryDB((SingleCRS)sourceCRS, (SingleCRS)sourceGeo);
/*  944 */     if (step1 == null)
/*  944 */       step1 = createOperationStep(sourceCRS, sourceGeo); 
/*  945 */     CoordinateOperation step2 = tryDB((SingleCRS)sourceGeo, (SingleCRS)targetGeo);
/*  945 */     if (step2 == null)
/*  945 */       step2 = createOperationStep(sourceGeo, targetGeo); 
/*  946 */     CoordinateOperation step3 = tryDB((SingleCRS)targetGeo, (SingleCRS)targetCRS);
/*  946 */     if (step3 == null)
/*  946 */       step3 = createOperationStep(targetGeo, targetCRS); 
/*  947 */     return concatenate(step1, step2, step3);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(GeographicCRS sourceCRS, ProjectedCRS targetCRS) throws FactoryException {
/*  971 */     GeographicCRS base = targetCRS.getBaseCRS();
/*  972 */     Projection projection = targetCRS.getConversionFromBase();
/*  973 */     CoordinateOperation step1 = tryDB((SingleCRS)sourceCRS, (SingleCRS)base);
/*  974 */     if (step1 == null)
/*  975 */       step1 = createOperationStep(sourceCRS, base); 
/*  977 */     return concatenate(step1, (CoordinateOperation)projection);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(ProjectedCRS sourceCRS, GeographicCRS targetCRS) throws FactoryException {
/* 1004 */     GeographicCRS base = sourceCRS.getBaseCRS();
/* 1005 */     Projection projection = sourceCRS.getConversionFromBase();
/* 1006 */     CoordinateOperation step2 = tryDB((SingleCRS)base, (SingleCRS)targetCRS);
/* 1007 */     if (step2 == null)
/* 1008 */       step2 = createOperationStep(base, targetCRS); 
/* 1010 */     MathTransform transform = projection.getMathTransform();
/*      */     try {
/* 1012 */       transform = transform.inverse();
/* 1013 */     } catch (NoninvertibleTransformException exception) {
/* 1014 */       throw new OperationNotFoundException(getErrorMessage(sourceCRS, base), exception);
/*      */     } 
/* 1016 */     CoordinateOperation coordinateOperation1 = createFromMathTransform(INVERSE_OPERATION, (CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)base, transform);
/* 1017 */     return concatenate(coordinateOperation1, step2);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(GeocentricCRS sourceCRS, GeocentricCRS targetCRS) throws FactoryException {
/*      */     Matrix4 matrix4;
/* 1037 */     GeodeticDatum sourceDatum = sourceCRS.getDatum();
/* 1038 */     GeodeticDatum targetDatum = targetCRS.getDatum();
/* 1039 */     CoordinateSystem sourceCS = sourceCRS.getCoordinateSystem();
/* 1040 */     CoordinateSystem targetCS = targetCRS.getCoordinateSystem();
/* 1042 */     double sourcePM = getGreenwichLongitude(sourceDatum.getPrimeMeridian());
/* 1043 */     double targetPM = getGreenwichLongitude(targetDatum.getPrimeMeridian());
/* 1044 */     if (equalsIgnorePrimeMeridian(sourceDatum, targetDatum) && 
/* 1045 */       sourcePM == targetPM) {
/* 1051 */       Matrix matrix = swapAndScaleAxis(sourceCS, targetCS);
/* 1052 */       return createFromAffineTransform(AXIS_CHANGES, (CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)targetCRS, matrix);
/*      */     } 
/* 1056 */     if (sourcePM != targetPM)
/* 1057 */       throw new OperationNotFoundException("Rotation of prime meridian not yet implemented"); 
/* 1070 */     DefaultCartesianCS defaultCartesianCS = DefaultCartesianCS.GEOCENTRIC;
/* 1072 */     ReferenceIdentifier identifier = DATUM_SHIFT;
/*      */     try {
/*      */       Matrix4 matrix41;
/* 1074 */       Matrix datumShift = DefaultGeodeticDatum.getAffineTransform(TemporaryDatum.unwrap(sourceDatum), TemporaryDatum.unwrap(targetDatum));
/* 1077 */       if (datumShift == null)
/* 1078 */         if (this.lenientDatumShift) {
/* 1079 */           matrix41 = new Matrix4();
/* 1080 */           identifier = ELLIPSOID_SHIFT;
/*      */         } else {
/* 1082 */           throw new OperationNotFoundException(Errors.format(18));
/*      */         }  
/* 1086 */       Matrix normalizeSource = swapAndScaleAxis(sourceCS, (CoordinateSystem)defaultCartesianCS);
/* 1087 */       Matrix normalizeTarget = swapAndScaleAxis((CoordinateSystem)defaultCartesianCS, targetCS);
/* 1096 */       matrix4 = new Matrix4(normalizeTarget);
/* 1097 */       matrix4.multiply((Matrix)matrix41);
/* 1098 */       matrix4.multiply(normalizeSource);
/* 1099 */     } catch (SingularMatrixException cause) {
/* 1100 */       throw new OperationNotFoundException(getErrorMessage(sourceDatum, targetDatum), cause);
/*      */     } 
/* 1102 */     return createFromAffineTransform(identifier, (CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)targetCRS, (Matrix)matrix4);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(GeographicCRS sourceCRS, GeocentricCRS targetCRS) throws FactoryException {
/* 1132 */     GeographicCRS normSourceCRS = normalize(sourceCRS, true);
/* 1133 */     GeodeticDatum datum = normSourceCRS.getDatum();
/* 1134 */     GeocentricCRS normTargetCRS = normalize(targetCRS, datum);
/* 1135 */     Ellipsoid ellipsoid = datum.getEllipsoid();
/* 1136 */     Unit unit = ellipsoid.getAxisUnit();
/* 1138 */     ParameterValueGroup param = getMathTransformFactory().getDefaultParameters("Ellipsoid_To_Geocentric");
/* 1139 */     param.parameter("semi_major").setValue(ellipsoid.getSemiMajorAxis(), unit);
/* 1140 */     param.parameter("semi_minor").setValue(ellipsoid.getSemiMinorAxis(), unit);
/* 1141 */     param.parameter("dim").setValue(getDimension((CoordinateReferenceSystem)normSourceCRS));
/* 1144 */     CoordinateOperation step1 = createOperationStep(sourceCRS, normSourceCRS);
/* 1145 */     CoordinateOperation step2 = createFromParameters(GEOCENTRIC_CONVERSION, (CoordinateReferenceSystem)normSourceCRS, (CoordinateReferenceSystem)normTargetCRS, param);
/* 1146 */     CoordinateOperation step3 = createOperationStep(normTargetCRS, targetCRS);
/* 1147 */     return concatenate(step1, step2, step3);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(GeocentricCRS sourceCRS, GeographicCRS targetCRS) throws FactoryException {
/* 1163 */     GeographicCRS normTargetCRS = normalize(targetCRS, true);
/* 1164 */     GeodeticDatum datum = normTargetCRS.getDatum();
/* 1165 */     GeocentricCRS normSourceCRS = normalize(sourceCRS, datum);
/* 1166 */     Ellipsoid ellipsoid = datum.getEllipsoid();
/* 1167 */     Unit unit = ellipsoid.getAxisUnit();
/* 1169 */     ParameterValueGroup param = getMathTransformFactory().getDefaultParameters("Geocentric_To_Ellipsoid");
/* 1170 */     param.parameter("semi_major").setValue(ellipsoid.getSemiMajorAxis(), unit);
/* 1171 */     param.parameter("semi_minor").setValue(ellipsoid.getSemiMinorAxis(), unit);
/* 1172 */     param.parameter("dim").setValue(getDimension((CoordinateReferenceSystem)normTargetCRS));
/* 1175 */     CoordinateOperation step1 = createOperationStep(sourceCRS, normSourceCRS);
/* 1176 */     CoordinateOperation step2 = createFromParameters(GEOCENTRIC_CONVERSION, (CoordinateReferenceSystem)normSourceCRS, (CoordinateReferenceSystem)normTargetCRS, param);
/* 1177 */     CoordinateOperation step3 = createOperationStep(normTargetCRS, targetCRS);
/* 1178 */     return concatenate(step1, step2, step3);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(CompoundCRS sourceCRS, SingleCRS targetCRS) throws FactoryException {
/* 1205 */     List<SingleCRS> sources = DefaultCompoundCRS.getSingleCRS((CoordinateReferenceSystem)sourceCRS);
/* 1206 */     if (sources.size() == 1)
/* 1207 */       return createOperation((CoordinateReferenceSystem)sources.get(0), (CoordinateReferenceSystem)targetCRS); 
/* 1209 */     if (!needsGeodetic3D(sources, targetCRS)) {
/* 1211 */       List<SingleCRS> targets = Collections.singletonList(targetCRS);
/* 1212 */       return createOperationStep((CoordinateReferenceSystem)sourceCRS, sources, (CoordinateReferenceSystem)targetCRS, targets);
/*      */     } 
/* 1220 */     CoordinateReferenceSystem source3D = getFactoryContainer().toGeodetic3D(sourceCRS);
/* 1221 */     if (source3D != sourceCRS)
/* 1222 */       return createOperation(source3D, (CoordinateReferenceSystem)targetCRS); 
/* 1228 */     throw new OperationNotFoundException(getErrorMessage(sourceCRS, targetCRS));
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(SingleCRS sourceCRS, CompoundCRS targetCRS) throws FactoryException {
/* 1243 */     List<SingleCRS> targets = DefaultCompoundCRS.getSingleCRS((CoordinateReferenceSystem)targetCRS);
/* 1244 */     if (targets.size() == 1)
/* 1245 */       return createOperation((CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)targets.get(0)); 
/* 1252 */     CoordinateReferenceSystem target3D = getFactoryContainer().toGeodetic3D(targetCRS);
/* 1253 */     if (target3D != targetCRS)
/* 1254 */       return createOperation((CoordinateReferenceSystem)sourceCRS, target3D); 
/* 1256 */     List<SingleCRS> sources = Collections.singletonList(sourceCRS);
/* 1257 */     return createOperationStep((CoordinateReferenceSystem)sourceCRS, sources, (CoordinateReferenceSystem)targetCRS, targets);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createOperationStep(CompoundCRS sourceCRS, CompoundCRS targetCRS) throws FactoryException {
/* 1272 */     List<SingleCRS> sources = DefaultCompoundCRS.getSingleCRS((CoordinateReferenceSystem)sourceCRS);
/* 1273 */     List<SingleCRS> targets = DefaultCompoundCRS.getSingleCRS((CoordinateReferenceSystem)targetCRS);
/* 1274 */     if (targets.size() == 1)
/* 1275 */       return createOperation((CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)targets.get(0)); 
/* 1277 */     if (sources.size() == 1)
/* 1278 */       return createOperation((CoordinateReferenceSystem)sources.get(0), (CoordinateReferenceSystem)targetCRS); 
/* 1286 */     for (SingleCRS target : targets) {
/* 1287 */       if (needsGeodetic3D(sources, target)) {
/* 1288 */         ReferencingFactoryContainer factories = getFactoryContainer();
/* 1289 */         CoordinateReferenceSystem source3D = factories.toGeodetic3D(sourceCRS);
/* 1290 */         CoordinateReferenceSystem target3D = factories.toGeodetic3D(targetCRS);
/* 1291 */         if (source3D != sourceCRS || target3D != targetCRS)
/* 1292 */           return createOperation(source3D, target3D); 
/* 1298 */         throw new OperationNotFoundException(getErrorMessage(sourceCRS, targetCRS));
/*      */       } 
/*      */     } 
/* 1302 */     return createOperationStep((CoordinateReferenceSystem)sourceCRS, sources, (CoordinateReferenceSystem)targetCRS, targets);
/*      */   }
/*      */   
/*      */   private CoordinateOperation createOperationStep(CoordinateReferenceSystem sourceCRS, List<SingleCRS> sources, CoordinateReferenceSystem targetCRS, List<SingleCRS> targets) throws FactoryException {
/*      */     CompoundCRS compoundCRS;
/* 1337 */     CoordinateReferenceSystem[] ordered = new CoordinateReferenceSystem[targets.size()];
/* 1338 */     CoordinateOperation[] steps = new CoordinateOperation[targets.size()];
/* 1339 */     boolean[] done = new boolean[sources.size()];
/* 1340 */     int[] indices = new int[getDimension(sourceCRS)];
/* 1341 */     int count = 0, dimensions = 0;
/* 1342 */     for (int j = 0; j < targets.size(); j++) {
/* 1343 */       int m = 0;
/* 1344 */       CoordinateReferenceSystem target = (CoordinateReferenceSystem)targets.get(j);
/* 1345 */       OperationNotFoundException cause = null;
/* 1346 */       int n = 0;
/*      */       while (true) {
/* 1346 */         if (n < sources.size()) {
/* 1347 */           CoordinateReferenceSystem source = (CoordinateReferenceSystem)sources.get(n);
/* 1348 */           int lower = m;
/* 1349 */           m += getDimension(source);
/* 1350 */           if (done[n]) {
/*      */             n++;
/*      */             continue;
/*      */           } 
/*      */           try {
/* 1352 */             steps[count] = createOperation(source, target);
/* 1353 */           } catch (OperationNotFoundException exception) {
/* 1356 */             if (cause == null || n == j)
/* 1357 */               cause = exception; 
/*      */           } 
/* 1361 */           ordered[count++] = source;
/* 1362 */           while (lower < m)
/* 1363 */             indices[dimensions++] = lower++; 
/* 1365 */           done[n] = true;
/*      */           break;
/*      */         } 
/* 1372 */         throw new OperationNotFoundException(getErrorMessage(sourceCRS, targetCRS), cause);
/*      */       } 
/*      */     } 
/* 1380 */     assert count == targets.size() : count;
/* 1381 */     while (count != 0 && steps[--count].getMathTransform().isIdentity());
/* 1382 */     ReferencingFactoryContainer factories = getFactoryContainer();
/* 1383 */     CoordinateOperation operation = null;
/* 1384 */     CoordinateReferenceSystem sourceStepCRS = sourceCRS;
/* 1385 */     XMatrix select = MatrixFactory.create(dimensions + 1, indices.length + 1);
/* 1386 */     select.setZero();
/* 1387 */     select.setElement(dimensions, indices.length, 1.0D);
/* 1388 */     for (int k = 0; k < dimensions; k++)
/* 1389 */       select.setElement(k, indices[k], 1.0D); 
/* 1391 */     if (!select.isIdentity()) {
/* 1392 */       if (ordered.length == 1) {
/* 1393 */         sourceStepCRS = ordered[0];
/*      */       } else {
/* 1395 */         compoundCRS = factories.getCRSFactory().createCompoundCRS(getTemporaryName((IdentifiedObject)sourceCRS), ordered);
/*      */       } 
/* 1398 */       operation = createFromAffineTransform(AXIS_CHANGES, sourceCRS, (CoordinateReferenceSystem)compoundCRS, (Matrix)select);
/*      */     } 
/* 1406 */     int upper = 0;
/* 1407 */     for (int i = 0; i < targets.size(); i++) {
/*      */       CompoundCRS compoundCRS1;
/* 1408 */       CoordinateOperation step = steps[i];
/* 1409 */       Map<String, ?> properties = AbstractIdentifiedObject.getProperties((IdentifiedObject)step);
/* 1410 */       CoordinateReferenceSystem source = ordered[i];
/* 1411 */       CoordinateReferenceSystem target = (CoordinateReferenceSystem)targets.get(i);
/* 1413 */       ordered[i] = target;
/* 1414 */       MathTransform mt = step.getMathTransform();
/* 1415 */       if (i >= count) {
/* 1416 */         CoordinateReferenceSystem targetStepCRS = targetCRS;
/* 1417 */       } else if (mt.isIdentity()) {
/* 1418 */         compoundCRS1 = compoundCRS;
/* 1419 */       } else if (ordered.length == 1) {
/* 1420 */         CoordinateReferenceSystem targetStepCRS = ordered[0];
/*      */       } else {
/* 1422 */         compoundCRS1 = factories.getCRSFactory().createCompoundCRS(getTemporaryName((IdentifiedObject)target), ordered);
/*      */       } 
/* 1425 */       int lower = upper;
/* 1426 */       upper += getDimension(source);
/* 1427 */       if (lower != 0 || upper != dimensions) {
/* 1433 */         if (!(step instanceof Operation)) {
/* 1434 */           MathTransform stepMT = step.getMathTransform();
/* 1435 */           step = DefaultOperation.create(AbstractIdentifiedObject.getProperties((IdentifiedObject)step), step.getSourceCRS(), step.getTargetCRS(), stepMT, new DefaultOperationMethod(stepMT), (Class)step.getClass());
/*      */         } 
/* 1439 */         mt = getMathTransformFactory().createPassThroughTransform(lower, mt, dimensions - upper);
/* 1440 */         step = new DefaultPassThroughOperation(properties, (CoordinateReferenceSystem)compoundCRS, (CoordinateReferenceSystem)compoundCRS1, (Operation)step, mt);
/*      */       } 
/* 1443 */       operation = (operation == null) ? step : concatenate(operation, step);
/* 1444 */       compoundCRS = compoundCRS1;
/*      */     } 
/* 1446 */     assert upper == dimensions : upper;
/* 1447 */     return operation;
/*      */   }
/*      */   
/*      */   private static boolean needsGeodetic3D(List<SingleCRS> sourceCRS, SingleCRS targetCRS) {
/*      */     boolean targetGeodetic;
/* 1472 */     Datum targetDatum = targetCRS.getDatum();
/* 1473 */     if (targetDatum instanceof GeodeticDatum) {
/* 1474 */       targetGeodetic = true;
/* 1475 */     } else if (targetDatum instanceof VerticalDatum) {
/* 1476 */       targetGeodetic = false;
/*      */     } else {
/* 1478 */       return false;
/*      */     } 
/* 1480 */     boolean horizontal = false;
/* 1481 */     boolean vertical = false;
/* 1482 */     boolean shift = false;
/* 1483 */     for (SingleCRS crs : sourceCRS) {
/*      */       boolean sourceGeodetic;
/* 1484 */       Datum sourceDatum = crs.getDatum();
/* 1486 */       if (sourceDatum instanceof GeodeticDatum) {
/* 1487 */         horizontal = true;
/* 1488 */         sourceGeodetic = true;
/* 1489 */       } else if (sourceDatum instanceof VerticalDatum) {
/* 1490 */         vertical = true;
/* 1491 */         sourceGeodetic = false;
/*      */       } else {
/*      */         continue;
/*      */       } 
/* 1495 */       if (!shift && sourceGeodetic == targetGeodetic) {
/* 1496 */         shift = !CRS.equalsIgnoreMetadata(sourceDatum, targetDatum);
/* 1497 */         assert Classes.sameInterfaces(sourceDatum.getClass(), targetDatum.getClass(), Datum.class);
/*      */       } 
/*      */     } 
/* 1501 */     return (horizontal && vertical && (shift || targetCRS.getCoordinateSystem().getDimension() >= 3));
/*      */   }
/*      */   
/*      */   private static boolean equalsIgnorePrimeMeridian(GeodeticDatum object1, GeodeticDatum object2) {
/* 1527 */     object1 = TemporaryDatum.unwrap(object1);
/* 1528 */     object2 = TemporaryDatum.unwrap(object2);
/* 1529 */     if (CRS.equalsIgnoreMetadata(object1.getEllipsoid(), object2.getEllipsoid()))
/* 1530 */       return (AbstractIdentifiedObject.nameMatches((IdentifiedObject)object1, object2.getName().getCode()) || AbstractIdentifiedObject.nameMatches((IdentifiedObject)object2, object1.getName().getCode())); 
/* 1533 */     return false;
/*      */   }
/*      */   
/*      */   private static boolean containsIgnoreMetadata(List<SingleCRS> container, List<SingleCRS> candidates) {
/* 1543 */     label13: for (SingleCRS crs : candidates) {
/* 1544 */       for (SingleCRS c : container) {
/* 1545 */         if (CRS.equalsIgnoreMetadata(crs, c))
/*      */           continue label13; 
/*      */       } 
/* 1549 */       return false;
/*      */     } 
/* 1551 */     return true;
/*      */   }
/*      */   
/*      */   private final CoordinateOperation tryDB(SingleCRS sourceCRS, SingleCRS targetCRS) {
/* 1562 */     return (sourceCRS == targetCRS) ? null : createFromDatabase((CoordinateReferenceSystem)sourceCRS, (CoordinateReferenceSystem)targetCRS);
/*      */   }
/*      */   
/*      */   protected CoordinateOperation createFromDatabase(CoordinateReferenceSystem sourceCRS, CoordinateReferenceSystem targetCRS) {
/* 1597 */     return null;
/*      */   }
/*      */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\DefaultCoordinateOperationFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */