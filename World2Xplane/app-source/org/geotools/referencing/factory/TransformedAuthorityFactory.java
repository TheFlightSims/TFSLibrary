/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.cs.DefaultCoordinateSystemAxis;
/*     */ import org.geotools.referencing.operation.DefiningConversion;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.CanonicalSet;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CRSFactory;
/*     */ import org.opengis.referencing.crs.CompoundCRS;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.EngineeringCRS;
/*     */ import org.opengis.referencing.crs.GeneralDerivedCRS;
/*     */ import org.opengis.referencing.crs.GeocentricCRS;
/*     */ import org.opengis.referencing.crs.GeographicCRS;
/*     */ import org.opengis.referencing.crs.ImageCRS;
/*     */ import org.opengis.referencing.crs.ProjectedCRS;
/*     */ import org.opengis.referencing.crs.SingleCRS;
/*     */ import org.opengis.referencing.crs.TemporalCRS;
/*     */ import org.opengis.referencing.crs.VerticalCRS;
/*     */ import org.opengis.referencing.cs.AffineCS;
/*     */ import org.opengis.referencing.cs.AxisDirection;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CSFactory;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.CylindricalCS;
/*     */ import org.opengis.referencing.cs.EllipsoidalCS;
/*     */ import org.opengis.referencing.cs.LinearCS;
/*     */ import org.opengis.referencing.cs.PolarCS;
/*     */ import org.opengis.referencing.cs.SphericalCS;
/*     */ import org.opengis.referencing.cs.TimeCS;
/*     */ import org.opengis.referencing.cs.UserDefinedCS;
/*     */ import org.opengis.referencing.cs.VerticalCS;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.datum.EngineeringDatum;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ import org.opengis.referencing.datum.ImageDatum;
/*     */ import org.opengis.referencing.datum.TemporalDatum;
/*     */ import org.opengis.referencing.datum.VerticalDatum;
/*     */ import org.opengis.referencing.operation.Conversion;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*     */ 
/*     */ public class TransformedAuthorityFactory extends AuthorityFactoryAdapter {
/*  80 */   private static final DefaultCoordinateSystemAxis[] RENAMEABLE = new DefaultCoordinateSystemAxis[] { DefaultCoordinateSystemAxis.NORTHING, DefaultCoordinateSystemAxis.SOUTHING, DefaultCoordinateSystemAxis.EASTING, DefaultCoordinateSystemAxis.WESTING };
/*     */   
/*     */   private transient CoordinateOperationFactory opFactory;
/*     */   
/*  93 */   private final CanonicalSet pool = new CanonicalSet();
/*     */   
/*     */   protected TransformedAuthorityFactory(AuthorityFactory factory) {
/* 101 */     super(factory);
/*     */   }
/*     */   
/*     */   protected TransformedAuthorityFactory(CRSAuthorityFactory crsFactory, CSAuthorityFactory csFactory, DatumAuthorityFactory datumFactory, CoordinateOperationAuthorityFactory opFactory) {
/* 120 */     super(crsFactory, csFactory, datumFactory, opFactory);
/*     */   }
/*     */   
/*     */   protected TransformedAuthorityFactory(String authority, Hints userHints) throws FactoryRegistryException {
/* 138 */     super(authority, userHints);
/*     */   }
/*     */   
/*     */   public int getPriority() {
/* 151 */     return this.priority + 1;
/*     */   }
/*     */   
/*     */   protected Unit<?> replace(Unit<?> units) throws FactoryException {
/* 165 */     return units;
/*     */   }
/*     */   
/*     */   protected AxisDirection replace(AxisDirection direction) throws FactoryException {
/* 178 */     return direction;
/*     */   }
/*     */   
/*     */   protected CoordinateSystemAxis replace(CoordinateSystemAxis axis) throws FactoryException {
/*     */     DefaultCoordinateSystemAxis defaultCoordinateSystemAxis;
/*     */     CoordinateSystemAxis coordinateSystemAxis;
/* 192 */     AxisDirection oldDirection = axis.getDirection();
/* 193 */     AxisDirection newDirection = replace(oldDirection);
/* 194 */     Unit<?> oldUnits = axis.getUnit();
/* 195 */     Unit<?> newUnits = replace(oldUnits);
/* 196 */     boolean directionChanged = !oldDirection.equals(newDirection);
/* 197 */     if (directionChanged) {
/* 203 */       String name = axis.getName().getCode();
/* 204 */       for (int i = 0; i < RENAMEABLE.length; i++) {
/* 205 */         if (RENAMEABLE[i].nameMatches(name)) {
/* 206 */           for (i = 0; i < RENAMEABLE.length; i++) {
/* 207 */             DefaultCoordinateSystemAxis defaultCoordinateSystemAxis1 = RENAMEABLE[i];
/* 208 */             if (newDirection.equals(defaultCoordinateSystemAxis1.getDirection())) {
/* 209 */               defaultCoordinateSystemAxis = defaultCoordinateSystemAxis1;
/* 210 */               oldUnits = defaultCoordinateSystemAxis.getUnit();
/* 211 */               directionChanged = false;
/*     */               break;
/*     */             } 
/*     */           } 
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/* 219 */     if (directionChanged || !oldUnits.equals(newUnits)) {
/* 220 */       ReferencingFactoryContainer factories = getFactoryContainer(false);
/* 221 */       CSFactory csFactory = factories.getCSFactory();
/* 222 */       Map<String, ?> properties = getProperties((IdentifiedObject)defaultCoordinateSystemAxis);
/* 223 */       coordinateSystemAxis = csFactory.createCoordinateSystemAxis(properties, defaultCoordinateSystemAxis.getAbbreviation(), newDirection, newUnits);
/* 225 */       coordinateSystemAxis = (CoordinateSystemAxis)this.pool.unique(coordinateSystemAxis);
/*     */     } 
/* 227 */     return coordinateSystemAxis;
/*     */   }
/*     */   
/*     */   protected CoordinateSystem replace(CoordinateSystem cs) throws FactoryException {
/* 242 */     int dimension = cs.getDimension();
/* 243 */     CoordinateSystemAxis[] orderedAxis = new CoordinateSystemAxis[dimension];
/*     */     int i;
/* 244 */     for (i = 0; i < dimension; i++)
/* 245 */       orderedAxis[i] = replace(cs.getAxis(i)); 
/* 247 */     if (this instanceof Comparator)
/* 248 */       Arrays.sort(orderedAxis, (Comparator<? super CoordinateSystemAxis>)this); 
/* 250 */     for (i = 0; i < dimension; i++) {
/* 251 */       if (!orderedAxis[i].equals(cs.getAxis(i))) {
/* 252 */         CoordinateSystem modified = createCS(cs.getClass(), getProperties((IdentifiedObject)cs), orderedAxis);
/* 253 */         assert Classes.sameInterfaces(cs.getClass(), modified.getClass(), CoordinateSystem.class);
/* 254 */         modified = (CoordinateSystem)this.pool.unique(modified);
/* 255 */         return modified;
/*     */       } 
/*     */     } 
/* 259 */     return cs;
/*     */   }
/*     */   
/*     */   protected Datum replace(Datum datum) throws FactoryException {
/* 273 */     return super.replace(datum);
/*     */   }
/*     */   
/*     */   protected CoordinateReferenceSystem replace(CoordinateReferenceSystem crs) throws FactoryException {
/*     */     Datum oldDatum, datum;
/*     */     CompoundCRS compoundCRS;
/* 294 */     CoordinateSystem oldCS = crs.getCoordinateSystem();
/* 295 */     CoordinateSystem cs = replace(oldCS);
/* 297 */     if (crs instanceof SingleCRS) {
/* 298 */       oldDatum = ((SingleCRS)crs).getDatum();
/* 299 */       datum = replace(oldDatum);
/*     */     } else {
/* 301 */       datum = oldDatum = null;
/*     */     } 
/* 303 */     boolean sameCS = (Utilities.equals(cs, oldCS) && Utilities.equals(datum, oldDatum));
/* 310 */     if (crs instanceof GeneralDerivedCRS) {
/* 311 */       GeneralDerivedCRS derivedCRS = (GeneralDerivedCRS)crs;
/* 312 */       CoordinateReferenceSystem oldBaseCRS = derivedCRS.getBaseCRS();
/* 313 */       CoordinateReferenceSystem baseCRS = replace(oldBaseCRS);
/* 314 */       if (sameCS && Utilities.equals(baseCRS, oldBaseCRS))
/* 315 */         return crs; 
/* 317 */       Map<String, ?> properties = getProperties((IdentifiedObject)crs);
/* 318 */       ReferencingFactoryContainer factories = getFactoryContainer(true);
/* 319 */       CRSFactory crsFactory = factories.getCRSFactory();
/* 320 */       Conversion fromBase = derivedCRS.getConversionFromBase();
/* 321 */       DefiningConversion definingConversion = new DefiningConversion(getProperties((IdentifiedObject)fromBase), fromBase.getMethod(), fromBase.getParameterValues());
/* 323 */       if (crs instanceof ProjectedCRS) {
/* 324 */         ProjectedCRS projectedCRS = crsFactory.createProjectedCRS(properties, (GeographicCRS)baseCRS, (Conversion)definingConversion, (CartesianCS)cs);
/*     */       } else {
/* 328 */         throw new FactoryException(Errors.format(197, crs.getName().getCode()));
/*     */       } 
/*     */     } else {
/* 331 */       if (sameCS)
/* 332 */         return crs; 
/* 334 */       Map<String, ?> properties = getProperties((IdentifiedObject)crs);
/* 335 */       ReferencingFactoryContainer factories = getFactoryContainer(true);
/* 336 */       CRSFactory crsFactory = factories.getCRSFactory();
/* 337 */       if (crs instanceof GeographicCRS) {
/* 338 */         GeographicCRS geographicCRS = crsFactory.createGeographicCRS(properties, (GeodeticDatum)datum, (EllipsoidalCS)cs);
/* 340 */       } else if (crs instanceof GeocentricCRS) {
/* 341 */         GeodeticDatum gd = (GeodeticDatum)datum;
/* 342 */         if (cs instanceof CartesianCS) {
/* 343 */           GeocentricCRS geocentricCRS = crsFactory.createGeocentricCRS(properties, gd, (CartesianCS)cs);
/*     */         } else {
/* 345 */           GeocentricCRS geocentricCRS = crsFactory.createGeocentricCRS(properties, gd, (SphericalCS)cs);
/*     */         } 
/* 347 */       } else if (crs instanceof VerticalCRS) {
/* 348 */         VerticalCRS verticalCRS = crsFactory.createVerticalCRS(properties, (VerticalDatum)datum, (VerticalCS)cs);
/* 350 */       } else if (crs instanceof TemporalCRS) {
/* 351 */         TemporalCRS temporalCRS = crsFactory.createTemporalCRS(properties, (TemporalDatum)datum, (TimeCS)cs);
/* 353 */       } else if (crs instanceof ImageCRS) {
/* 354 */         ImageCRS imageCRS = crsFactory.createImageCRS(properties, (ImageDatum)datum, (AffineCS)cs);
/* 356 */       } else if (crs instanceof EngineeringCRS) {
/* 357 */         EngineeringCRS engineeringCRS = crsFactory.createEngineeringCRS(properties, (EngineeringDatum)datum, cs);
/* 359 */       } else if (crs instanceof CompoundCRS) {
/* 360 */         List<CoordinateReferenceSystem> elements = ((CompoundCRS)crs).getCoordinateReferenceSystems();
/* 362 */         CoordinateReferenceSystem[] m = new CoordinateReferenceSystem[elements.size()];
/* 363 */         for (int i = 0; i < m.length; i++)
/* 364 */           m[i] = replace(elements.get(i)); 
/* 366 */         compoundCRS = crsFactory.createCompoundCRS(properties, m);
/*     */       } else {
/* 368 */         throw new FactoryException(Errors.format(197, crs.getName().getCode()));
/*     */       } 
/*     */     } 
/* 372 */     CoordinateReferenceSystem modified = (CoordinateReferenceSystem)this.pool.unique(compoundCRS);
/* 373 */     return modified;
/*     */   }
/*     */   
/*     */   protected CoordinateOperation replace(CoordinateOperation operation) throws FactoryException {
/* 391 */     CoordinateReferenceSystem oldSrcCRS = operation.getSourceCRS();
/* 392 */     CoordinateReferenceSystem oldTgtCRS = operation.getTargetCRS();
/* 393 */     CoordinateReferenceSystem sourceCRS = (oldSrcCRS != null) ? replace(oldSrcCRS) : null;
/* 394 */     CoordinateReferenceSystem targetCRS = (oldTgtCRS != null) ? replace(oldTgtCRS) : null;
/* 395 */     if (Utilities.equals(oldSrcCRS, sourceCRS) && Utilities.equals(oldTgtCRS, targetCRS))
/* 396 */       return operation; 
/* 398 */     if (this.opFactory == null)
/* 399 */       this.opFactory = getCoordinateOperationFactory(); 
/* 402 */     CoordinateOperation modified = this.opFactory.createOperation(sourceCRS, targetCRS);
/* 403 */     modified = (CoordinateOperation)this.pool.unique(modified);
/* 404 */     return modified;
/*     */   }
/*     */   
/*     */   private CoordinateSystem createCS(Class<?> type, Map properties, CoordinateSystemAxis[] axis) throws FactoryException {
/* 424 */     int dimension = axis.length;
/* 425 */     ReferencingFactoryContainer factories = getFactoryContainer(false);
/* 426 */     CSFactory csFactory = factories.getCSFactory();
/* 427 */     if (CartesianCS.class.isAssignableFrom(type)) {
/* 428 */       switch (dimension) {
/*     */         case 2:
/* 429 */           return (CoordinateSystem)csFactory.createCartesianCS(properties, axis[0], axis[1]);
/*     */         case 3:
/* 430 */           return (CoordinateSystem)csFactory.createCartesianCS(properties, axis[0], axis[1], axis[2]);
/*     */       } 
/* 432 */     } else if (EllipsoidalCS.class.isAssignableFrom(type)) {
/* 433 */       switch (dimension) {
/*     */         case 2:
/* 434 */           return (CoordinateSystem)csFactory.createEllipsoidalCS(properties, axis[0], axis[1]);
/*     */         case 3:
/* 435 */           return (CoordinateSystem)csFactory.createEllipsoidalCS(properties, axis[0], axis[1], axis[2]);
/*     */       } 
/* 437 */     } else if (SphericalCS.class.isAssignableFrom(type)) {
/* 438 */       switch (dimension) {
/*     */         case 3:
/* 439 */           return (CoordinateSystem)csFactory.createSphericalCS(properties, axis[0], axis[1], axis[2]);
/*     */       } 
/* 441 */     } else if (CylindricalCS.class.isAssignableFrom(type)) {
/* 442 */       switch (dimension) {
/*     */         case 3:
/* 443 */           return (CoordinateSystem)csFactory.createCylindricalCS(properties, axis[0], axis[1], axis[2]);
/*     */       } 
/* 445 */     } else if (PolarCS.class.isAssignableFrom(type)) {
/* 446 */       switch (dimension) {
/*     */         case 2:
/* 447 */           return (CoordinateSystem)csFactory.createPolarCS(properties, axis[0], axis[1]);
/*     */       } 
/* 449 */     } else if (VerticalCS.class.isAssignableFrom(type)) {
/* 450 */       switch (dimension) {
/*     */         case 1:
/* 451 */           return (CoordinateSystem)csFactory.createVerticalCS(properties, axis[0]);
/*     */       } 
/* 453 */     } else if (TimeCS.class.isAssignableFrom(type)) {
/* 454 */       switch (dimension) {
/*     */         case 1:
/* 455 */           return (CoordinateSystem)csFactory.createTimeCS(properties, axis[0]);
/*     */       } 
/* 457 */     } else if (LinearCS.class.isAssignableFrom(type)) {
/* 458 */       switch (dimension) {
/*     */         case 1:
/* 459 */           return (CoordinateSystem)csFactory.createLinearCS(properties, axis[0]);
/*     */       } 
/* 461 */     } else if (UserDefinedCS.class.isAssignableFrom(type)) {
/* 462 */       switch (dimension) {
/*     */         case 2:
/* 463 */           return (CoordinateSystem)csFactory.createUserDefinedCS(properties, axis[0], axis[1]);
/*     */         case 3:
/* 464 */           return (CoordinateSystem)csFactory.createUserDefinedCS(properties, axis[0], axis[1], axis[2]);
/*     */       } 
/*     */     } 
/* 467 */     throw new FactoryException(Errors.format(196, type));
/*     */   }
/*     */   
/*     */   private Map<String, ?> getProperties(IdentifiedObject object) {
/* 482 */     Citation authority = getAuthority();
/* 483 */     if (!Utilities.equals(authority, object.getName().getAuthority()))
/* 484 */       return AbstractIdentifiedObject.getProperties(object, authority); 
/* 486 */     return AbstractIdentifiedObject.getProperties(object);
/*     */   }
/*     */   
/*     */   public Set<CoordinateOperation> createFromCoordinateReferenceSystemCodes(String sourceCode, String targetCode) throws FactoryException {
/* 502 */     Set<CoordinateOperation> operations = super.createFromCoordinateReferenceSystemCodes(sourceCode, targetCode);
/* 503 */     Set<CoordinateOperation> modified = new LinkedHashSet((int)(operations.size() / 0.75F) + 1);
/* 504 */     for (Iterator<CoordinateOperation> it = operations.iterator(); it.hasNext(); ) {
/*     */       CoordinateOperation operation;
/*     */       try {
/* 507 */         operation = it.next();
/* 508 */       } catch (BackingStoreException exception) {
/* 509 */         Throwable cause = exception.getCause();
/* 510 */         if (cause instanceof FactoryException)
/* 511 */           throw (FactoryException)cause; 
/* 513 */         throw exception;
/*     */       } 
/* 516 */       modified.add(replace(operation));
/*     */     } 
/* 518 */     return modified;
/*     */   }
/*     */   
/*     */   public synchronized void dispose() throws FactoryException {
/* 528 */     this.pool.clear();
/* 529 */     super.dispose();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\TransformedAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */