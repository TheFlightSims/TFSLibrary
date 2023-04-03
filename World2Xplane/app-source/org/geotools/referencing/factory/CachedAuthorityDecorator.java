/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.Set;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.geotools.factory.BufferedFactory;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.util.ObjectCache;
/*     */ import org.geotools.util.ObjectCaches;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
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
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public final class CachedAuthorityDecorator extends AbstractAuthorityFactory implements AuthorityFactory, CRSAuthorityFactory, CSAuthorityFactory, DatumAuthorityFactory, CoordinateOperationAuthorityFactory, BufferedFactory {
/*     */   ObjectCache cache;
/*     */   
/*     */   private AuthorityFactory authority;
/*     */   
/*     */   private CRSAuthorityFactory crsAuthority;
/*     */   
/*     */   private CSAuthorityFactory csAuthority;
/*     */   
/*     */   private DatumAuthorityFactory datumAuthority;
/*     */   
/*     */   private CoordinateOperationAuthorityFactory operationAuthority;
/*     */   
/*     */   private AbstractAuthorityFactory delegate;
/*     */   
/*     */   public CachedAuthorityDecorator(AuthorityFactory factory) {
/* 136 */     this(factory, createCache(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   protected CachedAuthorityDecorator(AuthorityFactory factory, ObjectCache cache) {
/* 159 */     super(((ReferencingFactory)factory).getPriority());
/* 160 */     this.cache = cache;
/* 161 */     this.authority = factory;
/* 162 */     this.crsAuthority = (CRSAuthorityFactory)factory;
/* 163 */     this.csAuthority = (CSAuthorityFactory)factory;
/* 164 */     this.datumAuthority = (DatumAuthorityFactory)factory;
/* 165 */     this.operationAuthority = (CoordinateOperationAuthorityFactory)factory;
/* 166 */     this.delegate = (AbstractAuthorityFactory)factory;
/*     */   }
/*     */   
/*     */   protected static ObjectCache createCache(Hints hints) throws FactoryRegistryException {
/* 172 */     return ObjectCaches.create(hints);
/*     */   }
/*     */   
/*     */   protected String toKey(String code) {
/* 179 */     return ObjectCaches.toKey(getAuthority(), code);
/*     */   }
/*     */   
/*     */   public IdentifiedObject createObject(String code) throws FactoryException {
/* 186 */     String key = toKey(code);
/* 187 */     IdentifiedObject obj = (IdentifiedObject)this.cache.get(key);
/* 188 */     if (obj == null)
/*     */       try {
/* 190 */         this.cache.writeLock(key);
/* 191 */         obj = (IdentifiedObject)this.cache.peek(key);
/* 192 */         if (obj == null) {
/* 193 */           obj = this.authority.createObject(code);
/* 194 */           this.cache.put(key, obj);
/*     */         } 
/*     */       } finally {
/* 197 */         this.cache.writeUnLock(key);
/*     */       }  
/* 200 */     return obj;
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 204 */     return this.authority.getAuthority();
/*     */   }
/*     */   
/*     */   public Set getAuthorityCodes(Class type) throws FactoryException {
/* 208 */     return this.authority.getAuthorityCodes(type);
/*     */   }
/*     */   
/*     */   public InternationalString getDescriptionText(String code) throws FactoryException {
/* 213 */     return this.authority.getDescriptionText(code);
/*     */   }
/*     */   
/*     */   public synchronized CompoundCRS createCompoundCRS(String code) throws FactoryException {
/* 221 */     String key = toKey(code);
/* 222 */     CompoundCRS crs = (CompoundCRS)this.cache.get(key);
/* 223 */     if (crs == null)
/*     */       try {
/* 225 */         this.cache.writeLock(key);
/* 226 */         crs = (CompoundCRS)this.cache.peek(key);
/* 227 */         if (crs == null) {
/* 228 */           crs = this.crsAuthority.createCompoundCRS(code);
/* 229 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 232 */         this.cache.writeUnLock(key);
/*     */       }  
/* 235 */     return crs;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/* 240 */     String key = toKey(code);
/* 241 */     CoordinateReferenceSystem crs = (CoordinateReferenceSystem)this.cache.get(key);
/* 243 */     if (crs == null)
/*     */       try {
/* 245 */         this.cache.writeLock(key);
/* 246 */         crs = (CoordinateReferenceSystem)this.cache.peek(key);
/* 247 */         if (crs == null) {
/* 248 */           crs = this.crsAuthority.createCoordinateReferenceSystem(code);
/* 249 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 252 */         this.cache.writeUnLock(key);
/*     */       }  
/* 255 */     return crs;
/*     */   }
/*     */   
/*     */   public DerivedCRS createDerivedCRS(String code) throws FactoryException {
/* 259 */     String key = toKey(code);
/* 260 */     DerivedCRS crs = (DerivedCRS)this.cache.get(key);
/* 261 */     if (crs == null)
/*     */       try {
/* 263 */         this.cache.writeLock(key);
/* 264 */         crs = (DerivedCRS)this.cache.peek(key);
/* 265 */         if (crs == null) {
/* 266 */           crs = this.crsAuthority.createDerivedCRS(code);
/* 267 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 270 */         this.cache.writeUnLock(key);
/*     */       }  
/* 273 */     return crs;
/*     */   }
/*     */   
/*     */   public EngineeringCRS createEngineeringCRS(String code) throws FactoryException {
/* 278 */     String key = toKey(code);
/* 279 */     EngineeringCRS crs = (EngineeringCRS)this.cache.get(key);
/* 280 */     if (crs == null)
/*     */       try {
/* 282 */         this.cache.writeLock(key);
/* 283 */         crs = (EngineeringCRS)this.cache.peek(key);
/* 284 */         if (crs == null) {
/* 285 */           crs = this.crsAuthority.createEngineeringCRS(code);
/* 286 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 289 */         this.cache.writeUnLock(key);
/*     */       }  
/* 292 */     return crs;
/*     */   }
/*     */   
/*     */   public GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
/* 297 */     String key = toKey(code);
/* 298 */     GeocentricCRS crs = (GeocentricCRS)this.cache.get(key);
/* 299 */     if (crs == null)
/*     */       try {
/* 301 */         this.cache.writeLock(key);
/* 302 */         crs = (GeocentricCRS)this.cache.peek(key);
/* 303 */         if (crs == null) {
/* 304 */           crs = this.crsAuthority.createGeocentricCRS(code);
/* 305 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 308 */         this.cache.writeUnLock(key);
/*     */       }  
/* 311 */     return crs;
/*     */   }
/*     */   
/*     */   public GeographicCRS createGeographicCRS(String code) throws FactoryException {
/* 316 */     String key = toKey(code);
/* 317 */     GeographicCRS crs = (GeographicCRS)this.cache.get(key);
/* 318 */     if (crs == null)
/*     */       try {
/* 320 */         this.cache.writeLock(key);
/* 321 */         crs = (GeographicCRS)this.cache.peek(key);
/* 322 */         if (crs == null) {
/* 323 */           crs = this.crsAuthority.createGeographicCRS(code);
/* 324 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 327 */         this.cache.writeUnLock(key);
/*     */       }  
/* 330 */     return crs;
/*     */   }
/*     */   
/*     */   public ImageCRS createImageCRS(String code) throws FactoryException {
/* 334 */     String key = toKey(code);
/* 335 */     ImageCRS crs = (ImageCRS)this.cache.get(key);
/* 336 */     if (crs == null)
/*     */       try {
/* 338 */         this.cache.writeLock(key);
/* 339 */         crs = (ImageCRS)this.cache.peek(key);
/* 340 */         if (crs == null) {
/* 341 */           crs = this.crsAuthority.createImageCRS(code);
/* 342 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 345 */         this.cache.writeUnLock(key);
/*     */       }  
/* 348 */     return crs;
/*     */   }
/*     */   
/*     */   public ProjectedCRS createProjectedCRS(String code) throws FactoryException {
/* 352 */     String key = toKey(code);
/* 353 */     ProjectedCRS crs = (ProjectedCRS)this.cache.get(key);
/* 354 */     if (crs == null)
/*     */       try {
/* 356 */         this.cache.writeLock(key);
/* 357 */         crs = (ProjectedCRS)this.cache.peek(key);
/* 358 */         if (crs == null) {
/* 359 */           crs = this.crsAuthority.createProjectedCRS(code);
/* 360 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 363 */         this.cache.writeUnLock(key);
/*     */       }  
/* 366 */     return crs;
/*     */   }
/*     */   
/*     */   public TemporalCRS createTemporalCRS(String code) throws FactoryException {
/* 370 */     String key = toKey(code);
/* 371 */     TemporalCRS crs = (TemporalCRS)this.cache.get(key);
/* 372 */     if (crs == null)
/*     */       try {
/* 374 */         this.cache.writeLock(key);
/* 375 */         crs = (TemporalCRS)this.cache.peek(key);
/* 376 */         if (crs == null) {
/* 377 */           crs = this.crsAuthority.createTemporalCRS(code);
/* 378 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 381 */         this.cache.writeUnLock(key);
/*     */       }  
/* 384 */     return crs;
/*     */   }
/*     */   
/*     */   public VerticalCRS createVerticalCRS(String code) throws FactoryException {
/* 388 */     String key = toKey(code);
/* 389 */     VerticalCRS crs = (VerticalCRS)this.cache.get(key);
/* 390 */     if (crs == null)
/*     */       try {
/* 392 */         this.cache.writeLock(key);
/* 393 */         crs = (VerticalCRS)this.cache.peek(key);
/* 394 */         if (crs == null) {
/* 395 */           crs = this.crsAuthority.createVerticalCRS(code);
/* 396 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 399 */         this.cache.writeUnLock(key);
/*     */       }  
/* 402 */     return crs;
/*     */   }
/*     */   
/*     */   public CartesianCS createCartesianCS(String code) throws FactoryException {
/* 409 */     String key = toKey(code);
/* 410 */     CartesianCS cs = (CartesianCS)this.cache.get(key);
/* 411 */     if (cs == null)
/*     */       try {
/* 413 */         this.cache.writeLock(key);
/* 414 */         cs = (CartesianCS)this.cache.peek(key);
/* 415 */         if (cs == null) {
/* 416 */           cs = this.csAuthority.createCartesianCS(code);
/* 417 */           this.cache.put(key, cs);
/*     */         } 
/*     */       } finally {
/* 420 */         this.cache.writeUnLock(key);
/*     */       }  
/* 423 */     return cs;
/*     */   }
/*     */   
/*     */   public CoordinateSystem createCoordinateSystem(String code) throws FactoryException {
/* 428 */     String key = toKey(code);
/* 429 */     CoordinateSystem cs = (CoordinateSystem)this.cache.get(key);
/* 430 */     if (cs == null)
/*     */       try {
/* 432 */         this.cache.writeLock(key);
/* 433 */         cs = (CoordinateSystem)this.cache.peek(key);
/* 434 */         if (cs == null) {
/* 435 */           cs = this.csAuthority.createCoordinateSystem(code);
/* 436 */           this.cache.put(key, cs);
/*     */         } 
/*     */       } finally {
/* 439 */         this.cache.writeUnLock(key);
/*     */       }  
/* 442 */     return cs;
/*     */   }
/*     */   
/*     */   public CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException {
/* 448 */     String key = toKey(code);
/* 449 */     CoordinateSystemAxis axis = (CoordinateSystemAxis)this.cache.get(key);
/* 450 */     if (axis == null)
/*     */       try {
/* 452 */         this.cache.writeLock(key);
/* 453 */         axis = (CoordinateSystemAxis)this.cache.peek(key);
/* 454 */         if (axis == null) {
/* 455 */           axis = this.csAuthority.createCoordinateSystemAxis(code);
/* 456 */           this.cache.put(key, axis);
/*     */         } 
/*     */       } finally {
/* 459 */         this.cache.writeUnLock(key);
/*     */       }  
/* 462 */     return axis;
/*     */   }
/*     */   
/*     */   public CylindricalCS createCylindricalCS(String code) throws FactoryException {
/* 467 */     String key = toKey(code);
/* 468 */     CylindricalCS cs = (CylindricalCS)this.cache.get(key);
/* 469 */     if (cs == null)
/*     */       try {
/* 471 */         this.cache.writeLock(key);
/* 472 */         cs = (CylindricalCS)this.cache.peek(key);
/* 473 */         if (cs == null) {
/* 474 */           cs = this.csAuthority.createCylindricalCS(code);
/* 475 */           this.cache.put(key, cs);
/*     */         } 
/*     */       } finally {
/* 478 */         this.cache.writeUnLock(key);
/*     */       }  
/* 481 */     return cs;
/*     */   }
/*     */   
/*     */   public EllipsoidalCS createEllipsoidalCS(String code) throws FactoryException {
/* 486 */     String key = toKey(code);
/* 487 */     EllipsoidalCS cs = (EllipsoidalCS)this.cache.get(key);
/* 488 */     if (cs == null)
/*     */       try {
/* 490 */         this.cache.writeLock(key);
/* 491 */         cs = (EllipsoidalCS)this.cache.peek(key);
/* 492 */         if (cs == null) {
/* 493 */           cs = this.csAuthority.createEllipsoidalCS(code);
/* 494 */           this.cache.put(key, cs);
/*     */         } 
/*     */       } finally {
/* 497 */         this.cache.writeUnLock(key);
/*     */       }  
/* 500 */     return cs;
/*     */   }
/*     */   
/*     */   public PolarCS createPolarCS(String code) throws FactoryException {
/* 504 */     String key = toKey(code);
/* 505 */     PolarCS cs = (PolarCS)this.cache.get(key);
/* 506 */     if (cs == null)
/*     */       try {
/* 508 */         this.cache.writeLock(key);
/* 509 */         cs = (PolarCS)this.cache.peek(key);
/* 510 */         if (cs == null) {
/* 511 */           cs = this.csAuthority.createPolarCS(code);
/* 512 */           this.cache.put(key, cs);
/*     */         } 
/*     */       } finally {
/* 515 */         this.cache.writeUnLock(key);
/*     */       }  
/* 518 */     return cs;
/*     */   }
/*     */   
/*     */   public SphericalCS createSphericalCS(String code) throws FactoryException {
/* 522 */     String key = toKey(code);
/* 523 */     SphericalCS cs = (SphericalCS)this.cache.get(key);
/* 524 */     if (cs == null)
/*     */       try {
/* 526 */         this.cache.writeLock(key);
/* 527 */         cs = (SphericalCS)this.cache.peek(key);
/* 528 */         if (cs == null) {
/* 529 */           cs = this.csAuthority.createSphericalCS(code);
/* 530 */           this.cache.put(key, cs);
/*     */         } 
/*     */       } finally {
/* 533 */         this.cache.writeUnLock(key);
/*     */       }  
/* 536 */     return cs;
/*     */   }
/*     */   
/*     */   public TimeCS createTimeCS(String code) throws FactoryException {
/* 540 */     String key = toKey(code);
/* 541 */     TimeCS cs = (TimeCS)this.cache.get(key);
/* 542 */     if (cs == null)
/*     */       try {
/* 544 */         this.cache.writeLock(key);
/* 545 */         cs = (TimeCS)this.cache.peek(key);
/* 546 */         if (cs == null) {
/* 547 */           cs = this.csAuthority.createTimeCS(code);
/* 548 */           this.cache.put(key, cs);
/*     */         } 
/*     */       } finally {
/* 551 */         this.cache.writeUnLock(key);
/*     */       }  
/* 554 */     return cs;
/*     */   }
/*     */   
/*     */   public Unit<?> createUnit(String code) throws FactoryException {
/* 558 */     String key = toKey(code);
/* 559 */     Unit<?> unit = (Unit)this.cache.get(key);
/* 560 */     if (unit == null)
/*     */       try {
/* 562 */         this.cache.writeLock(key);
/* 563 */         unit = (Unit)this.cache.peek(key);
/* 564 */         if (unit == null) {
/* 565 */           unit = this.csAuthority.createUnit(code);
/* 566 */           this.cache.put(key, unit);
/*     */         } 
/*     */       } finally {
/* 569 */         this.cache.writeUnLock(key);
/*     */       }  
/* 572 */     return unit;
/*     */   }
/*     */   
/*     */   public VerticalCS createVerticalCS(String code) throws FactoryException {
/* 576 */     String key = toKey(code);
/* 577 */     VerticalCS cs = (VerticalCS)this.cache.get(key);
/* 578 */     if (cs == null)
/*     */       try {
/* 580 */         this.cache.writeLock(key);
/* 581 */         cs = (VerticalCS)this.cache.peek(key);
/* 582 */         if (cs == null) {
/* 583 */           cs = this.csAuthority.createVerticalCS(code);
/* 584 */           this.cache.put(key, cs);
/*     */         } 
/*     */       } finally {
/* 587 */         this.cache.writeUnLock(key);
/*     */       }  
/* 590 */     return cs;
/*     */   }
/*     */   
/*     */   public Datum createDatum(String code) throws FactoryException {
/* 597 */     String key = toKey(code);
/* 598 */     Datum datum = (Datum)this.cache.get(key);
/* 599 */     if (datum == null)
/*     */       try {
/* 601 */         this.cache.writeLock(key);
/* 602 */         datum = (Datum)this.cache.peek(key);
/* 603 */         if (datum == null) {
/* 604 */           datum = this.datumAuthority.createDatum(code);
/* 605 */           this.cache.put(key, datum);
/*     */         } 
/*     */       } finally {
/* 608 */         this.cache.writeUnLock(key);
/*     */       }  
/* 611 */     return datum;
/*     */   }
/*     */   
/*     */   public Ellipsoid createEllipsoid(String code) throws FactoryException {
/* 615 */     String key = toKey(code);
/* 616 */     Ellipsoid ellipsoid = (Ellipsoid)this.cache.get(key);
/* 617 */     if (ellipsoid == null)
/*     */       try {
/* 619 */         this.cache.writeLock(key);
/* 620 */         ellipsoid = (Ellipsoid)this.cache.peek(key);
/* 621 */         if (ellipsoid == null) {
/* 622 */           ellipsoid = this.datumAuthority.createEllipsoid(code);
/* 623 */           this.cache.put(key, ellipsoid);
/*     */         } 
/*     */       } finally {
/* 626 */         this.cache.writeUnLock(key);
/*     */       }  
/* 629 */     return ellipsoid;
/*     */   }
/*     */   
/*     */   public EngineeringDatum createEngineeringDatum(String code) throws FactoryException {
/* 634 */     String key = toKey(code);
/* 635 */     EngineeringDatum datum = (EngineeringDatum)this.cache.get(key);
/* 636 */     if (datum == null)
/*     */       try {
/* 638 */         this.cache.writeLock(key);
/* 639 */         datum = (EngineeringDatum)this.cache.peek(key);
/* 640 */         if (datum == null) {
/* 641 */           datum = this.datumAuthority.createEngineeringDatum(code);
/* 642 */           this.cache.put(key, datum);
/*     */         } 
/*     */       } finally {
/* 645 */         this.cache.writeUnLock(key);
/*     */       }  
/* 648 */     return datum;
/*     */   }
/*     */   
/*     */   public GeodeticDatum createGeodeticDatum(String code) throws FactoryException {
/* 653 */     String key = toKey(code);
/* 654 */     GeodeticDatum datum = (GeodeticDatum)this.cache.get(key);
/* 655 */     if (datum == null)
/*     */       try {
/* 657 */         this.cache.writeLock(key);
/* 658 */         datum = (GeodeticDatum)this.cache.peek(key);
/* 659 */         if (datum == null) {
/* 660 */           datum = this.datumAuthority.createGeodeticDatum(code);
/* 661 */           this.cache.put(key, datum);
/*     */         } 
/*     */       } finally {
/* 664 */         this.cache.writeUnLock(key);
/*     */       }  
/* 667 */     return datum;
/*     */   }
/*     */   
/*     */   public ImageDatum createImageDatum(String code) throws FactoryException {
/* 671 */     String key = toKey(code);
/* 672 */     ImageDatum datum = (ImageDatum)this.cache.get(key);
/* 673 */     if (datum == null)
/*     */       try {
/* 675 */         this.cache.writeLock(key);
/* 676 */         datum = (ImageDatum)this.cache.peek(key);
/* 677 */         if (datum == null) {
/* 678 */           datum = this.datumAuthority.createImageDatum(code);
/* 679 */           this.cache.put(key, datum);
/*     */         } 
/*     */       } finally {
/* 682 */         this.cache.writeUnLock(key);
/*     */       }  
/* 685 */     return datum;
/*     */   }
/*     */   
/*     */   public PrimeMeridian createPrimeMeridian(String code) throws FactoryException {
/* 690 */     String key = toKey(code);
/* 691 */     PrimeMeridian datum = (PrimeMeridian)this.cache.get(key);
/* 692 */     if (datum == null)
/*     */       try {
/* 694 */         this.cache.writeLock(key);
/* 695 */         datum = (PrimeMeridian)this.cache.peek(key);
/* 696 */         if (datum == null) {
/* 697 */           datum = this.datumAuthority.createPrimeMeridian(code);
/* 698 */           this.cache.put(key, datum);
/*     */         } 
/*     */       } finally {
/* 701 */         this.cache.writeUnLock(key);
/*     */       }  
/* 704 */     return datum;
/*     */   }
/*     */   
/*     */   public TemporalDatum createTemporalDatum(String code) throws FactoryException {
/* 709 */     String key = toKey(code);
/* 710 */     TemporalDatum datum = (TemporalDatum)this.cache.get(key);
/* 711 */     if (datum == null)
/*     */       try {
/* 713 */         this.cache.writeLock(key);
/* 714 */         datum = (TemporalDatum)this.cache.peek(key);
/* 715 */         if (datum == null) {
/* 716 */           datum = this.datumAuthority.createTemporalDatum(code);
/* 717 */           this.cache.put(key, datum);
/*     */         } 
/*     */       } finally {
/* 720 */         this.cache.writeUnLock(key);
/*     */       }  
/* 723 */     return datum;
/*     */   }
/*     */   
/*     */   public VerticalDatum createVerticalDatum(String code) throws FactoryException {
/* 728 */     String key = toKey(code);
/* 729 */     VerticalDatum datum = (VerticalDatum)this.cache.get(key);
/* 730 */     if (datum == null)
/*     */       try {
/* 732 */         this.cache.writeLock(key);
/* 733 */         datum = (VerticalDatum)this.cache.peek(key);
/* 734 */         if (datum == null) {
/* 735 */           datum = this.datumAuthority.createVerticalDatum(code);
/* 736 */           this.cache.put(key, datum);
/*     */         } 
/*     */       } finally {
/* 739 */         this.cache.writeUnLock(key);
/*     */       }  
/* 742 */     return datum;
/*     */   }
/*     */   
/*     */   public CoordinateOperation createCoordinateOperation(String code) throws FactoryException {
/* 747 */     String key = toKey(code);
/* 748 */     CoordinateOperation operation = (CoordinateOperation)this.cache.get(key);
/* 749 */     if (operation == null)
/*     */       try {
/* 751 */         this.cache.writeLock(key);
/* 752 */         operation = (CoordinateOperation)this.cache.peek(key);
/* 753 */         if (operation == null) {
/* 754 */           operation = this.operationAuthority.createCoordinateOperation(code);
/* 756 */           this.cache.put(key, operation);
/*     */         } 
/*     */       } finally {
/* 759 */         this.cache.writeUnLock(key);
/*     */       }  
/* 762 */     return operation;
/*     */   }
/*     */   
/*     */   public synchronized Set createFromCoordinateReferenceSystemCodes(String sourceCode, String targetCode) throws FactoryException {
/* 769 */     Object key = ObjectCaches.toKey(getAuthority(), sourceCode, targetCode);
/* 770 */     Set operations = (Set)this.cache.get(key);
/* 771 */     if (operations == null)
/*     */       try {
/* 773 */         this.cache.writeLock(key);
/* 774 */         operations = (Set)this.cache.peek(key);
/* 775 */         if (operations == null) {
/* 776 */           operations = this.operationAuthority.createFromCoordinateReferenceSystemCodes(sourceCode, targetCode);
/* 780 */           this.cache.put(key, operations);
/*     */         } 
/*     */       } finally {
/* 784 */         this.cache.writeUnLock(key);
/*     */       }  
/* 787 */     return operations;
/*     */   }
/*     */   
/*     */   public IdentifiedObjectFinder getIdentifiedObjectFinder(Class<? extends IdentifiedObject> type) throws FactoryException {
/* 793 */     return this.delegate.getIdentifiedObjectFinder(type);
/*     */   }
/*     */   
/*     */   public void dispose() throws FactoryException {
/* 797 */     this.delegate.dispose();
/* 798 */     this.cache.clear();
/* 799 */     this.cache = null;
/* 800 */     this.delegate = null;
/*     */   }
/*     */   
/*     */   public String getBackingStoreDescription() throws FactoryException {
/* 804 */     return this.delegate.getBackingStoreDescription();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\CachedAuthorityDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */