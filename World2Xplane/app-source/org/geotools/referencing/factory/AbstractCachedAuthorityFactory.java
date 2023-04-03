/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.Set;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.factory.BufferedFactory;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.util.NameFactory;
/*     */ import org.geotools.util.ObjectCache;
/*     */ import org.geotools.util.ObjectCaches;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CompoundCRS;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.DerivedCRS;
/*     */ import org.opengis.referencing.crs.EngineeringCRS;
/*     */ import org.opengis.referencing.crs.GeocentricCRS;
/*     */ import org.opengis.referencing.crs.GeographicCRS;
/*     */ import org.opengis.referencing.crs.ImageCRS;
/*     */ import org.opengis.referencing.crs.ProjectedCRS;
/*     */ import org.opengis.referencing.crs.TemporalCRS;
/*     */ import org.opengis.referencing.crs.VerticalCRS;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CartesianCS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.cs.CoordinateSystemAxis;
/*     */ import org.opengis.referencing.cs.CylindricalCS;
/*     */ import org.opengis.referencing.cs.EllipsoidalCS;
/*     */ import org.opengis.referencing.cs.PolarCS;
/*     */ import org.opengis.referencing.cs.SphericalCS;
/*     */ import org.opengis.referencing.cs.TimeCS;
/*     */ import org.opengis.referencing.cs.VerticalCS;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ import org.opengis.referencing.datum.EngineeringDatum;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ import org.opengis.referencing.datum.ImageDatum;
/*     */ import org.opengis.referencing.datum.PrimeMeridian;
/*     */ import org.opengis.referencing.datum.TemporalDatum;
/*     */ import org.opengis.referencing.datum.VerticalDatum;
/*     */ import org.opengis.referencing.operation.CoordinateOperation;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ import org.opengis.util.GenericName;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public abstract class AbstractCachedAuthorityFactory extends AbstractAuthorityFactory implements AuthorityFactory, CRSAuthorityFactory, CSAuthorityFactory, DatumAuthorityFactory, CoordinateOperationAuthorityFactory, BufferedFactory {
/*     */   protected ObjectCache cache;
/*     */   
/*     */   ObjectCache findCache;
/*     */   
/*     */   protected ReferencingFactoryContainer factories;
/*     */   
/*     */   protected AbstractCachedAuthorityFactory(int priority) {
/* 134 */     this(priority, ObjectCaches.create("weak", 50), ReferencingFactoryContainer.instance(null));
/*     */   }
/*     */   
/*     */   protected AbstractCachedAuthorityFactory(int priority, Hints hints) {
/* 144 */     this(priority, ObjectCaches.create(hints), ReferencingFactoryContainer.instance(hints));
/*     */   }
/*     */   
/*     */   protected AbstractCachedAuthorityFactory(int priority, ObjectCache cache, ReferencingFactoryContainer container) {
/* 162 */     super(priority);
/* 163 */     this.factories = container;
/* 164 */     this.cache = cache;
/* 165 */     this.findCache = ObjectCaches.create("weak", 0);
/*     */   }
/*     */   
/*     */   final void completeHints() {
/* 169 */     this.hints.put(Hints.DATUM_AUTHORITY_FACTORY, this);
/* 170 */     this.hints.put(Hints.CS_AUTHORITY_FACTORY, this);
/* 171 */     this.hints.put(Hints.CRS_AUTHORITY_FACTORY, this);
/* 172 */     this.hints.put(Hints.COORDINATE_OPERATION_AUTHORITY_FACTORY, this);
/*     */   }
/*     */   
/*     */   protected String toKey(String code) {
/* 179 */     return ObjectCaches.toKey(getAuthority(), code);
/*     */   }
/*     */   
/*     */   protected String trimAuthority(String code) {
/* 196 */     code = code.trim();
/* 197 */     GenericName name = NameFactory.create(code);
/* 198 */     GenericName scope = name.scope().name();
/* 199 */     if (scope == null)
/* 200 */       return code; 
/* 202 */     if (Citations.identifierMatches(getAuthority(), scope.toString()))
/* 203 */       return name.tip().toString().trim(); 
/* 205 */     return code;
/*     */   }
/*     */   
/*     */   protected NoSuchAuthorityCodeException noSuchAuthorityCode(Class type, String code, ClassCastException cause) {
/* 223 */     NoSuchAuthorityCodeException exception = noSuchAuthorityCode(type, code);
/* 224 */     exception.initCause(cause);
/* 225 */     return exception;
/*     */   }
/*     */   
/*     */   public abstract Citation getAuthority();
/*     */   
/*     */   public Set getAuthorityCodes(Class type) throws FactoryException {
/* 232 */     Set codes = (Set)this.cache.get(type);
/* 233 */     if (codes == null)
/*     */       try {
/* 235 */         this.cache.writeLock(type);
/* 236 */         codes = (Set)this.cache.peek(type);
/* 237 */         if (codes == null) {
/* 238 */           codes = generateAuthorityCodes(type);
/* 239 */           this.cache.put(type, codes);
/*     */         } 
/*     */       } finally {
/* 242 */         this.cache.writeUnLock(type);
/*     */       }  
/* 245 */     return codes;
/*     */   }
/*     */   
/*     */   protected abstract Set generateAuthorityCodes(Class paramClass) throws FactoryException;
/*     */   
/*     */   public abstract InternationalString getDescriptionText(String paramString) throws FactoryException;
/*     */   
/*     */   public IdentifiedObject createObject(String code) throws FactoryException {
/* 251 */     String key = toKey(code);
/* 252 */     IdentifiedObject obj = (IdentifiedObject)this.cache.get(key);
/* 253 */     if (obj == null)
/*     */       try {
/* 255 */         this.cache.writeLock(key);
/* 256 */         obj = (IdentifiedObject)this.cache.peek(key);
/* 257 */         if (obj == null) {
/* 258 */           obj = generateObject(code);
/* 259 */           this.cache.put(key, obj);
/*     */         } 
/*     */       } finally {
/* 262 */         this.cache.writeUnLock(key);
/*     */       }  
/* 265 */     return obj;
/*     */   }
/*     */   
/*     */   protected abstract IdentifiedObject generateObject(String paramString) throws FactoryException;
/*     */   
/*     */   public CompoundCRS createCompoundCRS(String code) throws FactoryException {
/* 280 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 282 */       return (CompoundCRS)crs;
/* 283 */     } catch (ClassCastException exception) {
/* 284 */       throw noSuchAuthorityCode(CompoundCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/* 290 */     String key = toKey(code);
/* 291 */     CoordinateReferenceSystem crs = (CoordinateReferenceSystem)this.cache.get(key);
/* 293 */     if (crs == null)
/*     */       try {
/* 295 */         this.cache.writeLock(key);
/* 296 */         crs = (CoordinateReferenceSystem)this.cache.peek(key);
/* 297 */         if (crs == null) {
/* 298 */           crs = generateCoordinateReferenceSystem(code);
/* 299 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 302 */         this.cache.writeUnLock(key);
/*     */       }  
/* 305 */     return crs;
/*     */   }
/*     */   
/*     */   protected abstract CoordinateReferenceSystem generateCoordinateReferenceSystem(String paramString) throws FactoryException;
/*     */   
/*     */   public DerivedCRS createDerivedCRS(String code) throws FactoryException {
/* 310 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 312 */       return (DerivedCRS)crs;
/* 313 */     } catch (ClassCastException exception) {
/* 314 */       throw noSuchAuthorityCode(DerivedCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public EngineeringCRS createEngineeringCRS(String code) throws FactoryException {
/* 319 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 321 */       return (EngineeringCRS)crs;
/* 322 */     } catch (ClassCastException exception) {
/* 323 */       throw noSuchAuthorityCode(EngineeringCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
/* 328 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 330 */       return (GeocentricCRS)crs;
/* 331 */     } catch (ClassCastException exception) {
/* 332 */       throw noSuchAuthorityCode(GeocentricCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GeographicCRS createGeographicCRS(String code) throws FactoryException {
/* 337 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 339 */       return (GeographicCRS)crs;
/* 340 */     } catch (ClassCastException exception) {
/* 341 */       throw noSuchAuthorityCode(GeographicCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ImageCRS createImageCRS(String code) throws FactoryException {
/* 346 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 348 */       return (ImageCRS)crs;
/* 349 */     } catch (ClassCastException exception) {
/* 350 */       throw noSuchAuthorityCode(ImageCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ProjectedCRS createProjectedCRS(String code) throws FactoryException {
/* 355 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 357 */       return (ProjectedCRS)crs;
/* 358 */     } catch (ClassCastException exception) {
/* 359 */       throw noSuchAuthorityCode(ProjectedCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TemporalCRS createTemporalCRS(String code) throws FactoryException {
/* 364 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 366 */       return (TemporalCRS)crs;
/* 367 */     } catch (ClassCastException exception) {
/* 368 */       throw noSuchAuthorityCode(TemporalCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public VerticalCRS createVerticalCRS(String code) throws FactoryException {
/* 373 */     CoordinateReferenceSystem crs = createCoordinateReferenceSystem(code);
/*     */     try {
/* 375 */       return (VerticalCRS)crs;
/* 376 */     } catch (ClassCastException exception) {
/* 377 */       throw noSuchAuthorityCode(VerticalCRS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CartesianCS createCartesianCS(String code) throws FactoryException {
/* 394 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 396 */       return (CartesianCS)cs;
/* 397 */     } catch (ClassCastException exception) {
/* 398 */       throw noSuchAuthorityCode(CartesianCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CoordinateSystem createCoordinateSystem(String code) throws FactoryException {
/* 404 */     String key = toKey(code);
/* 405 */     CoordinateSystem cs = (CoordinateSystem)this.cache.get(key);
/* 406 */     if (cs == null)
/*     */       try {
/* 408 */         this.cache.writeLock(key);
/* 409 */         cs = (CoordinateSystem)this.cache.peek(key);
/* 410 */         if (cs == null) {
/* 411 */           cs = generateCoordinateSystem(code);
/* 412 */           this.cache.put(key, cs);
/*     */         } 
/*     */       } finally {
/* 415 */         this.cache.writeUnLock(key);
/*     */       }  
/* 418 */     return cs;
/*     */   }
/*     */   
/*     */   protected abstract CoordinateSystem generateCoordinateSystem(String paramString) throws FactoryException;
/*     */   
/*     */   public CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException {
/* 426 */     String key = toKey(code);
/* 427 */     CoordinateSystemAxis axis = (CoordinateSystemAxis)this.cache.get(key);
/* 428 */     if (axis == null)
/*     */       try {
/* 430 */         this.cache.writeLock(key);
/* 431 */         axis = (CoordinateSystemAxis)this.cache.peek(key);
/* 432 */         if (axis == null) {
/* 433 */           axis = generateCoordinateSystemAxis(code);
/* 434 */           this.cache.put(key, axis);
/*     */         } 
/*     */       } finally {
/* 437 */         this.cache.writeUnLock(key);
/*     */       }  
/* 440 */     return axis;
/*     */   }
/*     */   
/*     */   protected abstract CoordinateSystemAxis generateCoordinateSystemAxis(String paramString) throws FactoryException;
/*     */   
/*     */   public CylindricalCS createCylindricalCS(String code) throws FactoryException {
/* 454 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 456 */       return (CylindricalCS)cs;
/* 457 */     } catch (ClassCastException exception) {
/* 458 */       throw noSuchAuthorityCode(CylindricalCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public EllipsoidalCS createEllipsoidalCS(String code) throws FactoryException {
/* 463 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 465 */       return (EllipsoidalCS)cs;
/* 466 */     } catch (ClassCastException exception) {
/* 467 */       throw noSuchAuthorityCode(EllipsoidalCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public PolarCS createPolarCS(String code) throws FactoryException {
/* 472 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 474 */       return (PolarCS)cs;
/* 475 */     } catch (ClassCastException exception) {
/* 476 */       throw noSuchAuthorityCode(PolarCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SphericalCS createSphericalCS(String code) throws FactoryException {
/* 481 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 483 */       return (SphericalCS)cs;
/* 484 */     } catch (ClassCastException exception) {
/* 485 */       throw noSuchAuthorityCode(SphericalCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public TimeCS createTimeCS(String code) throws FactoryException {
/* 490 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 492 */       return (TimeCS)cs;
/* 493 */     } catch (ClassCastException exception) {
/* 494 */       throw noSuchAuthorityCode(TimeCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Unit<?> createUnit(String code) throws FactoryException {
/* 499 */     String key = toKey(code);
/* 500 */     Unit<?> unit = (Unit)this.cache.get(key);
/* 501 */     if (unit == null)
/*     */       try {
/* 503 */         this.cache.writeLock(key);
/* 504 */         unit = (Unit)this.cache.peek(key);
/* 505 */         if (unit == null) {
/* 506 */           unit = generateUnit(code);
/* 507 */           this.cache.put(key, unit);
/*     */         } 
/*     */       } finally {
/* 510 */         this.cache.writeUnLock(key);
/*     */       }  
/* 513 */     return unit;
/*     */   }
/*     */   
/*     */   protected abstract Unit<?> generateUnit(String paramString) throws FactoryException;
/*     */   
/*     */   public VerticalCS createVerticalCS(String code) throws FactoryException {
/* 519 */     CoordinateSystem cs = createCoordinateSystem(code);
/*     */     try {
/* 521 */       return (VerticalCS)cs;
/* 522 */     } catch (ClassCastException exception) {
/* 523 */       throw noSuchAuthorityCode(VerticalCS.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public Datum createDatum(String code) throws FactoryException {
/* 531 */     String key = toKey(code);
/* 532 */     Datum datum = (Datum)this.cache.get(key);
/* 533 */     if (datum == null)
/*     */       try {
/* 535 */         this.cache.writeLock(key);
/* 536 */         datum = (Datum)this.cache.peek(key);
/* 537 */         if (datum == null) {
/* 538 */           datum = generateDatum(code);
/* 539 */           this.cache.put(key, datum);
/*     */         } 
/*     */       } finally {
/* 542 */         this.cache.writeUnLock(key);
/*     */       }  
/* 545 */     return datum;
/*     */   }
/*     */   
/*     */   protected abstract Datum generateDatum(String paramString) throws FactoryException;
/*     */   
/*     */   public Ellipsoid createEllipsoid(String code) throws FactoryException {
/* 551 */     String key = toKey(code);
/* 552 */     Ellipsoid ellipsoid = (Ellipsoid)this.cache.get(key);
/* 553 */     if (ellipsoid == null)
/*     */       try {
/* 555 */         this.cache.writeLock(key);
/* 556 */         ellipsoid = (Ellipsoid)this.cache.peek(key);
/* 557 */         if (ellipsoid == null) {
/* 558 */           ellipsoid = generateEllipsoid(code);
/* 559 */           this.cache.put(key, ellipsoid);
/*     */         } 
/*     */       } finally {
/* 562 */         this.cache.writeUnLock(key);
/*     */       }  
/* 565 */     return ellipsoid;
/*     */   }
/*     */   
/*     */   protected abstract Ellipsoid generateEllipsoid(String paramString) throws FactoryException;
/*     */   
/*     */   public EngineeringDatum createEngineeringDatum(String code) throws FactoryException {
/* 571 */     Datum datum = createDatum(code);
/*     */     try {
/* 573 */       return (EngineeringDatum)datum;
/* 574 */     } catch (ClassCastException exception) {
/* 575 */       throw noSuchAuthorityCode(EngineeringDatum.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public GeodeticDatum createGeodeticDatum(String code) throws FactoryException {
/* 580 */     Datum datum = createDatum(code);
/*     */     try {
/* 582 */       return (GeodeticDatum)datum;
/* 583 */     } catch (ClassCastException exception) {
/* 584 */       throw noSuchAuthorityCode(GeodeticDatum.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public ImageDatum createImageDatum(String code) throws FactoryException {
/* 589 */     Datum datum = createDatum(code);
/*     */     try {
/* 591 */       return (ImageDatum)datum;
/* 592 */     } catch (ClassCastException exception) {
/* 593 */       throw noSuchAuthorityCode(ImageDatum.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public PrimeMeridian createPrimeMeridian(String code) throws FactoryException {
/* 599 */     String key = toKey(code);
/* 600 */     PrimeMeridian datum = (PrimeMeridian)this.cache.get(key);
/* 601 */     if (datum == null)
/*     */       try {
/* 603 */         this.cache.writeLock(key);
/* 604 */         datum = (PrimeMeridian)this.cache.peek(key);
/* 605 */         if (datum == null) {
/* 606 */           datum = generatePrimeMeridian(code);
/* 607 */           this.cache.put(key, datum);
/*     */         } 
/*     */       } finally {
/* 610 */         this.cache.writeUnLock(key);
/*     */       }  
/* 613 */     return datum;
/*     */   }
/*     */   
/*     */   protected abstract PrimeMeridian generatePrimeMeridian(String paramString) throws FactoryException;
/*     */   
/*     */   public TemporalDatum createTemporalDatum(String code) throws FactoryException {
/* 619 */     Datum datum = createDatum(code);
/*     */     try {
/* 621 */       return (TemporalDatum)datum;
/* 622 */     } catch (ClassCastException exception) {
/* 623 */       throw noSuchAuthorityCode(TemporalDatum.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public VerticalDatum createVerticalDatum(String code) throws FactoryException {
/* 628 */     Datum datum = createDatum(code);
/*     */     try {
/* 630 */       return (VerticalDatum)datum;
/* 631 */     } catch (ClassCastException exception) {
/* 632 */       throw noSuchAuthorityCode(VerticalDatum.class, code, exception);
/*     */     } 
/*     */   }
/*     */   
/*     */   public CoordinateOperation createCoordinateOperation(String code) throws FactoryException {
/* 638 */     String key = toKey(code);
/* 639 */     CoordinateOperation operation = (CoordinateOperation)this.cache.get(key);
/* 640 */     if (operation == null)
/*     */       try {
/* 642 */         this.cache.writeLock(key);
/* 643 */         operation = (CoordinateOperation)this.cache.peek(key);
/* 644 */         if (operation == null) {
/* 645 */           operation = generateCoordinateOperation(code);
/* 646 */           this.cache.put(key, operation);
/*     */         } 
/*     */       } finally {
/* 649 */         this.cache.writeUnLock(key);
/*     */       }  
/* 652 */     return operation;
/*     */   }
/*     */   
/*     */   protected abstract CoordinateOperation generateCoordinateOperation(String paramString) throws FactoryException;
/*     */   
/*     */   public synchronized Set createFromCoordinateReferenceSystemCodes(String sourceCode, String targetCode) throws FactoryException {
/* 661 */     Object key = ObjectCaches.toKey(getAuthority(), sourceCode, targetCode);
/* 662 */     Set operations = (Set)this.cache.get(key);
/* 663 */     if (operations == null)
/*     */       try {
/* 665 */         this.cache.writeLock(key);
/* 666 */         operations = (Set)this.cache.peek(key);
/* 667 */         if (operations == null) {
/* 668 */           operations = generateFromCoordinateReferenceSystemCodes(sourceCode, targetCode);
/* 672 */           this.cache.put(key, operations);
/*     */         } 
/*     */       } finally {
/* 676 */         this.cache.writeUnLock(key);
/*     */       }  
/* 679 */     return operations;
/*     */   }
/*     */   
/*     */   protected abstract Set generateFromCoordinateReferenceSystemCodes(String paramString1, String paramString2) throws FactoryException;
/*     */   
/*     */   public void dispose() throws FactoryException {
/* 687 */     this.cache = null;
/* 688 */     this.factories = null;
/*     */   }
/*     */   
/*     */   public synchronized IdentifiedObjectFinder getIdentifiedObjectFinder(Class type) throws FactoryException {
/* 702 */     return new CachedFinder(type);
/*     */   }
/*     */   
/*     */   private final class CachedFinder extends IdentifiedObjectFinder {
/*     */     CachedFinder(Class type) {
/* 724 */       super(AbstractCachedAuthorityFactory.this, type);
/*     */     }
/*     */     
/*     */     public IdentifiedObject find(IdentifiedObject object) throws FactoryException {
/* 736 */       IdentifiedObject candidate = (IdentifiedObject)AbstractCachedAuthorityFactory.this.findCache.get(object);
/* 737 */       if (candidate != null)
/* 738 */         return candidate; 
/*     */       try {
/* 741 */         AbstractCachedAuthorityFactory.this.findCache.writeLock(object);
/* 742 */         IdentifiedObject found = super.find(object);
/* 743 */         if (found == null)
/* 744 */           return null; 
/* 746 */         candidate = (IdentifiedObject)AbstractCachedAuthorityFactory.this.findCache.peek(object);
/* 747 */         if (candidate == null) {
/* 748 */           AbstractCachedAuthorityFactory.this.findCache.put(object, found);
/* 749 */           return found;
/*     */         } 
/* 752 */         return candidate;
/*     */       } finally {
/* 755 */         AbstractCachedAuthorityFactory.this.findCache.writeUnLock(object);
/*     */       } 
/*     */     }
/*     */     
/*     */     public String findIdentifier(IdentifiedObject object) throws FactoryException {
/* 765 */       IdentifiedObject candidate = (IdentifiedObject)AbstractCachedAuthorityFactory.this.findCache.get(object);
/* 766 */       if (candidate != null)
/* 767 */         return getIdentifier(candidate); 
/* 769 */       return super.findIdentifier(object);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\AbstractCachedAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */