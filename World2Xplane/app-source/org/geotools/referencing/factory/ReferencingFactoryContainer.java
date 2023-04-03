/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.geotools.factory.Factory;
/*     */ import org.geotools.factory.FactoryCreator;
/*     */ import org.geotools.factory.FactoryRegistry;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.crs.DefaultCompoundCRS;
/*     */ import org.geotools.referencing.cs.AbstractCS;
/*     */ import org.geotools.referencing.operation.DefaultMathTransformFactory;
/*     */ import org.geotools.referencing.operation.DefiningConversion;
/*     */ import org.geotools.resources.XArray;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchIdentifierException;
/*     */ import org.opengis.referencing.crs.CRSFactory;
/*     */ import org.opengis.referencing.crs.CompoundCRS;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.GeographicCRS;
/*     */ import org.opengis.referencing.crs.ProjectedCRS;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.crs.VerticalCRS;
/*     */ import org.opengis.referencing.cs.CSFactory;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.EllipsoidalCS;
/*     */ import org.opengis.referencing.datum.DatumFactory;
/*     */ import org.opengis.referencing.datum.VerticalDatumType;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.MathTransformFactory;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ import org.opengis.referencing.operation.Operation;
/*     */ import org.opengis.referencing.operation.OperationMethod;
/*     */ import org.opengis.referencing.operation.Projection;
/*     */ 
/*     */ public class ReferencingFactoryContainer extends ReferencingFactory {
/*     */   private static FactoryRegistry cache;
/*     */   
/*     */   private DatumFactory datumFactory;
/*     */   
/*     */   private CSFactory csFactory;
/*     */   
/*     */   private CRSFactory crsFactory;
/*     */   
/*     */   private MathTransformFactory mtFactory;
/*     */   
/*     */   public ReferencingFactoryContainer(Hints userHints) {
/* 107 */     Hints reduced = new Hints((RenderingHints)userHints);
/* 114 */     this.datumFactory = (DatumFactory)extract((Map<?, ?>)reduced, (Hints.Key)Hints.DATUM_FACTORY);
/* 115 */     this.csFactory = (CSFactory)extract((Map<?, ?>)reduced, (Hints.Key)Hints.CS_FACTORY);
/* 116 */     this.crsFactory = (CRSFactory)extract((Map<?, ?>)reduced, (Hints.Key)Hints.CRS_FACTORY);
/* 117 */     this.mtFactory = (MathTransformFactory)extract((Map<?, ?>)reduced, (Hints.Key)Hints.MATH_TRANSFORM_FACTORY);
/* 122 */     if (!reduced.isEmpty()) {
/* 123 */       setHintsInto((Map<? super RenderingHints.Key, Object>)reduced);
/* 124 */       addImplementationHints((RenderingHints)reduced);
/* 125 */       initialize();
/* 126 */       this.hints.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Factory extract(Map<?, ?> reduced, Hints.Key key) {
/* 135 */     if (reduced != null) {
/* 136 */       Object candidate = reduced.get(key);
/* 137 */       if (candidate instanceof Factory) {
/* 138 */         reduced.remove(key);
/* 139 */         return (Factory)candidate;
/*     */       } 
/*     */     } 
/* 142 */     return null;
/*     */   }
/*     */   
/*     */   public static ReferencingFactoryContainer instance(Hints hints) {
/* 154 */     Hints completed = GeoTools.getDefaultHints();
/* 155 */     if (hints != null)
/* 156 */       completed.add((RenderingHints)hints); 
/* 164 */     synchronized (ReferencingFactoryFinder.class) {
/* 165 */       if (cache == null) {
/* 166 */         cache = (FactoryRegistry)new FactoryCreator(Arrays.asList((Class<?>[][])new Class[] { ReferencingFactoryContainer.class }));
/* 169 */         cache.registerServiceProvider(new ReferencingFactoryContainer(null), ReferencingFactoryContainer.class);
/*     */       } 
/* 172 */       return (ReferencingFactoryContainer)cache.getServiceProvider(ReferencingFactoryContainer.class, null, completed, null);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void initialize() {
/* 182 */     this.mtFactory = getMathTransformFactory();
/* 183 */     this.datumFactory = getDatumFactory();
/* 184 */     this.csFactory = getCSFactory();
/* 185 */     this.crsFactory = getCRSFactory();
/*     */   }
/*     */   
/*     */   private void setHintsInto(Map<? super RenderingHints.Key, Object> hints) {
/* 192 */     if (this.crsFactory != null)
/* 192 */       hints.put(Hints.CRS_FACTORY, this.crsFactory); 
/* 193 */     if (this.csFactory != null)
/* 193 */       hints.put(Hints.CS_FACTORY, this.csFactory); 
/* 194 */     if (this.datumFactory != null)
/* 194 */       hints.put(Hints.DATUM_FACTORY, this.datumFactory); 
/* 195 */     if (this.mtFactory != null)
/* 195 */       hints.put(Hints.MATH_TRANSFORM_FACTORY, this.mtFactory); 
/*     */   }
/*     */   
/*     */   public Map<RenderingHints.Key, ?> getImplementationHints() {
/* 205 */     synchronized (this.hints) {
/* 206 */       if (this.hints.isEmpty()) {
/* 207 */         initialize();
/* 208 */         setHintsInto(this.hints);
/*     */       } 
/*     */     } 
/* 211 */     return super.getImplementationHints();
/*     */   }
/*     */   
/*     */   private Hints hints() {
/* 220 */     Hints completed = new Hints(this.hints);
/* 221 */     setHintsInto((Map<? super RenderingHints.Key, Object>)completed);
/* 222 */     return completed;
/*     */   }
/*     */   
/*     */   public DatumFactory getDatumFactory() {
/* 231 */     if (this.datumFactory == null)
/* 232 */       synchronized (this.hints) {
/* 233 */         this.datumFactory = ReferencingFactoryFinder.getDatumFactory(hints());
/*     */       }  
/* 236 */     return this.datumFactory;
/*     */   }
/*     */   
/*     */   public CSFactory getCSFactory() {
/* 245 */     if (this.csFactory == null)
/* 246 */       synchronized (this.hints) {
/* 247 */         this.csFactory = ReferencingFactoryFinder.getCSFactory(hints());
/*     */       }  
/* 250 */     return this.csFactory;
/*     */   }
/*     */   
/*     */   public CRSFactory getCRSFactory() {
/* 259 */     if (this.crsFactory == null)
/* 260 */       synchronized (this.hints) {
/* 261 */         this.crsFactory = ReferencingFactoryFinder.getCRSFactory(hints());
/*     */       }  
/* 264 */     return this.crsFactory;
/*     */   }
/*     */   
/*     */   public MathTransformFactory getMathTransformFactory() {
/* 273 */     if (this.mtFactory == null)
/* 274 */       synchronized (this.hints) {
/* 275 */         this.mtFactory = ReferencingFactoryFinder.getMathTransformFactory(hints());
/*     */       }  
/* 278 */     return this.mtFactory;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public OperationMethod getOperationMethod(String name) throws NoSuchIdentifierException {
/* 304 */     MathTransformFactory mtFactory = getMathTransformFactory();
/* 305 */     if (mtFactory instanceof DefaultMathTransformFactory)
/* 307 */       return ((DefaultMathTransformFactory)mtFactory).getOperationMethod(name); 
/* 310 */     for (OperationMethod method : mtFactory.getAvailableMethods(Operation.class)) {
/* 311 */       if (AbstractIdentifiedObject.nameMatches((IdentifiedObject)method, name))
/* 312 */         return method; 
/*     */     } 
/* 315 */     throw new NoSuchIdentifierException(Errors.format(141, name), name);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public OperationMethod getLastUsedMethod() {
/* 337 */     return getMathTransformFactory().getLastMethodUsed();
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public MathTransform createParameterizedTransform(ParameterValueGroup parameters) throws NoSuchIdentifierException, FactoryException {
/* 360 */     return getMathTransformFactory().createParameterizedTransform(parameters);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public MathTransform createBaseToDerived(CoordinateReferenceSystem baseCRS, ParameterValueGroup parameters, CoordinateSystem derivedCS) throws NoSuchIdentifierException, FactoryException {
/* 390 */     return getMathTransformFactory().createBaseToDerived(baseCRS, parameters, derivedCS);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ProjectedCRS createProjectedCRS(Map<String, ?> properties, GeographicCRS baseCRS, Conversion conversionFromBase, CartesianCS derivedCS) throws FactoryException {
/* 411 */     return getCRSFactory().createProjectedCRS(properties, baseCRS, conversionFromBase, derivedCS);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ProjectedCRS createProjectedCRS(Map<String, ?> properties, GeographicCRS baseCRS, OperationMethod method, ParameterValueGroup parameters, CartesianCS derivedCS) throws FactoryException {
/* 438 */     MathTransform mt = createBaseToDerived((CoordinateReferenceSystem)baseCRS, parameters, (CoordinateSystem)derivedCS);
/* 439 */     if (method == null)
/* 440 */       method = getLastUsedMethod(); 
/* 442 */     return ((ReferencingObjectFactory)getCRSFactory()).createProjectedCRS(properties, method, baseCRS, mt, derivedCS);
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem toGeodetic3D(CompoundCRS crs) throws FactoryException {
/* 460 */     List<SingleCRS> components = DefaultCompoundCRS.getSingleCRS((CoordinateReferenceSystem)crs);
/* 461 */     int count = components.size();
/* 462 */     SingleCRS horizontal = null;
/* 463 */     VerticalCRS vertical = null;
/* 464 */     int hi = 0, vi = 0;
/* 465 */     for (int i = 0; i < count; i++) {
/* 466 */       SingleCRS candidate = components.get(i);
/* 467 */       if (candidate instanceof VerticalCRS) {
/* 468 */         if (vertical == null) {
/* 469 */           vertical = (VerticalCRS)candidate;
/* 470 */           if (VerticalDatumType.ELLIPSOIDAL.equals(vertical.getDatum().getVerticalDatumType())) {
/* 473 */             vi = i;
/*     */           } else {
/* 477 */             return (CoordinateReferenceSystem)crs;
/*     */           } 
/*     */         } else {
/* 477 */           return (CoordinateReferenceSystem)crs;
/*     */         } 
/* 479 */       } else if (candidate instanceof GeographicCRS || candidate instanceof ProjectedCRS) {
/* 480 */         if (horizontal == null) {
/* 481 */           horizontal = candidate;
/* 482 */           if (horizontal.getCoordinateSystem().getDimension() == 2) {
/* 483 */             hi = i;
/*     */           } else {
/* 487 */             return (CoordinateReferenceSystem)crs;
/*     */           } 
/*     */         } else {
/* 487 */           return (CoordinateReferenceSystem)crs;
/*     */         } 
/*     */       } 
/*     */     } 
/* 490 */     if (horizontal != null && vertical != null && Math.abs(vi - hi) == 1) {
/* 496 */       boolean xyFirst = (hi < vi);
/* 497 */       SingleCRS single = toGeodetic3D((count == 2) ? crs : null, horizontal, vertical, xyFirst);
/* 499 */       if (count == 2)
/* 500 */         return (CoordinateReferenceSystem)single; 
/* 502 */       int j = xyFirst ? hi : vi;
/* 503 */       components = new ArrayList<SingleCRS>(components);
/* 504 */       components.remove(j);
/* 505 */       components.set(j, single);
/* 506 */       SingleCRS[] c = components.<SingleCRS>toArray(new SingleCRS[components.size()]);
/* 507 */       return (CoordinateReferenceSystem)this.crsFactory.createCompoundCRS(AbstractIdentifiedObject.getProperties((IdentifiedObject)crs), (CoordinateReferenceSystem[])c);
/*     */     } 
/* 509 */     return (CoordinateReferenceSystem)crs;
/*     */   }
/*     */   
/*     */   private SingleCRS toGeodetic3D(CompoundCRS crs, SingleCRS horizontal, VerticalCRS vertical, boolean xyFirst) throws FactoryException {
/*     */     Map<String, ?> csName, crsName;
/* 535 */     CoordinateSystemAxis[] axis = new CoordinateSystemAxis[3];
/* 536 */     CoordinateSystem cs = horizontal.getCoordinateSystem();
/* 537 */     axis[xyFirst ? 0 : 1] = cs.getAxis(0);
/* 538 */     axis[xyFirst ? 1 : 2] = cs.getAxis(1);
/* 539 */     axis[xyFirst ? 2 : 0] = vertical.getCoordinateSystem().getAxis(0);
/* 541 */     if (crs != null) {
/* 542 */       csName = AbstractIdentifiedObject.getProperties((IdentifiedObject)crs.getCoordinateSystem());
/* 543 */       crsName = AbstractIdentifiedObject.getProperties((IdentifiedObject)crs);
/*     */     } else {
/* 545 */       csName = getTemporaryName((IdentifiedObject)cs);
/* 546 */       crsName = getTemporaryName((IdentifiedObject)horizontal);
/*     */     } 
/* 548 */     CSFactory csFactory = getCSFactory();
/* 549 */     CRSFactory crsFactory = getCRSFactory();
/* 550 */     if (horizontal instanceof GeographicCRS) {
/* 555 */       GeographicCRS sourceCRS = (GeographicCRS)horizontal;
/* 556 */       EllipsoidalCS targetCS = csFactory.createEllipsoidalCS(csName, axis[0], axis[1], axis[2]);
/* 557 */       return (SingleCRS)crsFactory.createGeographicCRS(crsName, sourceCRS.getDatum(), targetCS);
/*     */     } 
/* 559 */     if (horizontal instanceof ProjectedCRS) {
/*     */       DefiningConversion definingConversion;
/* 568 */       ProjectedCRS sourceCRS = (ProjectedCRS)horizontal;
/* 569 */       CartesianCS targetCS = csFactory.createCartesianCS(csName, axis[0], axis[1], axis[2]);
/* 570 */       GeographicCRS base2D = sourceCRS.getBaseCRS();
/* 571 */       GeographicCRS base3D = (GeographicCRS)toGeodetic3D((CompoundCRS)null, (SingleCRS)base2D, vertical, xyFirst);
/* 572 */       Matrix prepend = toStandard((CoordinateReferenceSystem)base2D, false);
/* 573 */       Matrix append = toStandard((CoordinateReferenceSystem)sourceCRS, true);
/* 574 */       Projection projection = sourceCRS.getConversionFromBase();
/* 575 */       if (!prepend.isIdentity() || !append.isIdentity()) {
/* 576 */         MathTransformFactory mtFactory = getMathTransformFactory();
/* 577 */         MathTransform mt = projection.getMathTransform();
/* 578 */         mt = mtFactory.createConcatenatedTransform(mtFactory.createConcatenatedTransform(mtFactory.createAffineTransform(prepend), mt), mtFactory.createAffineTransform(append));
/* 582 */         definingConversion = new DefiningConversion(AbstractCS.getProperties((IdentifiedObject)projection), projection.getMethod(), mt);
/*     */       } 
/* 585 */       return (SingleCRS)crsFactory.createProjectedCRS(crsName, base3D, (Conversion)definingConversion, targetCS);
/*     */     } 
/* 588 */     throw new AssertionError(horizontal);
/*     */   }
/*     */   
/*     */   private static Matrix toStandard(CoordinateReferenceSystem crs, boolean inverse) {
/* 592 */     CoordinateSystem sourceCS = crs.getCoordinateSystem();
/* 593 */     CoordinateSystem targetCS = AbstractCS.standard(sourceCS);
/* 594 */     if (inverse)
/* 595 */       return AbstractCS.swapAndScaleAxis(targetCS, sourceCS); 
/* 597 */     return AbstractCS.swapAndScaleAxis(sourceCS, targetCS);
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem separate(CoordinateReferenceSystem crs, int[] dimensions) throws FactoryException {
/* 615 */     int length = dimensions.length;
/* 616 */     int crsDimension = crs.getCoordinateSystem().getDimension();
/* 617 */     if (length == 0 || dimensions[0] < 0 || dimensions[length - 1] >= crsDimension || !XArray.isStrictlySorted(dimensions))
/* 620 */       throw new IllegalArgumentException(Errors.format(57, "dimension")); 
/* 623 */     if (length == crsDimension)
/* 624 */       return crs; 
/* 631 */     if (crs instanceof CompoundCRS) {
/* 632 */       int count = 0, lowerDimension = 0, lowerIndex = 0;
/* 635 */       List<CoordinateReferenceSystem> sources = ((CompoundCRS)crs).getCoordinateReferenceSystems();
/* 636 */       CoordinateReferenceSystem[] targets = new CoordinateReferenceSystem[sources.size()];
/* 637 */       label39: for (CoordinateReferenceSystem source : sources) {
/* 638 */         int upperDimension = lowerDimension + source.getCoordinateSystem().getDimension();
/* 644 */         if (lowerIndex == dimensions.length)
/*     */           break; 
/* 647 */         while (dimensions[lowerIndex] < lowerDimension) {
/* 648 */           if (++lowerIndex == dimensions.length)
/*     */             break label39; 
/*     */         } 
/* 652 */         int upperIndex = lowerIndex;
/*     */         do {
/*     */         
/* 653 */         } while (dimensions[upperIndex] < upperDimension && 
/* 654 */           ++upperIndex != dimensions.length);
/* 658 */         if (lowerIndex != upperIndex) {
/* 659 */           int[] sub = new int[upperIndex - lowerIndex];
/* 660 */           for (int j = 0; j < sub.length; j++)
/* 661 */             sub[j] = dimensions[j + lowerIndex] - lowerDimension; 
/* 663 */           targets[count++] = separate(source, sub);
/*     */         } 
/* 665 */         lowerDimension = upperDimension;
/* 666 */         lowerIndex = upperIndex;
/*     */       } 
/* 668 */       if (count == 1)
/* 669 */         return targets[0]; 
/* 671 */       return (CoordinateReferenceSystem)getCRSFactory().createCompoundCRS(getTemporaryName((IdentifiedObject)crs), (CoordinateReferenceSystem[])XArray.resize((Object[])targets, count));
/*     */     } 
/* 679 */     throw new FactoryException(Errors.format(31, crs.getName().getCode()));
/*     */   }
/*     */   
/*     */   private static Map<String, ?> getTemporaryName(IdentifiedObject source) {
/* 687 */     return Collections.singletonMap("name", source.getName().getCode() + " (3D)");
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\ReferencingFactoryContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */