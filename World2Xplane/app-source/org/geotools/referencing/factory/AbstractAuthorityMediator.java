/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import javax.measure.unit.Unit;
/*     */ import org.apache.commons.pool.ObjectPool;
/*     */ import org.apache.commons.pool.PoolableObjectFactory;
/*     */ import org.apache.commons.pool.impl.GenericObjectPool;
/*     */ import org.apache.commons.pool.impl.GenericObjectPoolFactory;
/*     */ import org.geotools.factory.BufferedFactory;
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
/*     */ public abstract class AbstractAuthorityMediator extends AbstractAuthorityFactory implements AuthorityFactory, CRSAuthorityFactory, CSAuthorityFactory, DatumAuthorityFactory, CoordinateOperationAuthorityFactory, BufferedFactory {
/*     */   static final int PRIORITY = 90;
/*     */   
/*     */   ObjectCache cache;
/*     */   
/*     */   ObjectCache findCache;
/*     */   
/*     */   private ObjectPool workers;
/*     */   
/* 143 */   GenericObjectPool.Config poolConfig = new GenericObjectPool.Config();
/*     */   
/*     */   protected final ReferencingFactoryContainer factories;
/*     */   
/*     */   protected AbstractAuthorityMediator() {
/* 154 */     this(90);
/*     */   }
/*     */   
/*     */   protected AbstractAuthorityMediator(Hints hints) {
/* 163 */     this(90, hints);
/*     */   }
/*     */   
/*     */   protected AbstractAuthorityMediator(int priority) {
/* 171 */     this(priority, ObjectCaches.create("weak", 50), ReferencingFactoryContainer.instance(null));
/*     */   }
/*     */   
/*     */   protected AbstractAuthorityMediator(int priority, Hints hints) {
/* 180 */     this(priority, ObjectCaches.create(hints), ReferencingFactoryContainer.instance(hints));
/* 182 */     this.poolConfig.minIdle = Hints.AUTHORITY_MIN_IDLE.toValue(hints);
/* 183 */     this.poolConfig.maxIdle = Hints.AUTHORITY_MAX_IDLE.toValue(hints);
/* 184 */     this.poolConfig.maxActive = Hints.AUTHORITY_MAX_ACTIVE.toValue(hints);
/* 185 */     this.poolConfig.minEvictableIdleTimeMillis = Hints.AUTHORITY_MIN_EVICT_IDLETIME.toValue(hints);
/* 186 */     this.poolConfig.softMinEvictableIdleTimeMillis = Hints.AUTHORITY_SOFTMIN_EVICT_IDLETIME.toValue(hints);
/* 188 */     this.poolConfig.timeBetweenEvictionRunsMillis = Hints.AUTHORITY_TIME_BETWEEN_EVICTION_RUNS.toValue(hints);
/* 192 */     this.poolConfig.maxWait = -1L;
/* 193 */     this.poolConfig.whenExhaustedAction = 1;
/*     */   }
/*     */   
/*     */   protected AbstractAuthorityMediator(int priority, ObjectCache cache, ReferencingFactoryContainer container) {
/* 208 */     super(priority);
/* 209 */     this.factories = container;
/* 210 */     this.cache = cache;
/* 211 */     this.findCache = ObjectCaches.chain(ObjectCaches.create("weak", 0), cache);
/*     */   }
/*     */   
/*     */   protected void completeHints() {
/* 215 */     this.hints.put(Hints.DATUM_AUTHORITY_FACTORY, this);
/* 216 */     this.hints.put(Hints.CS_AUTHORITY_FACTORY, this);
/* 217 */     this.hints.put(Hints.CRS_AUTHORITY_FACTORY, this);
/* 218 */     this.hints.put(Hints.COORDINATE_OPERATION_AUTHORITY_FACTORY, this);
/*     */   }
/*     */   
/*     */   public boolean isConnected() {
/* 228 */     return (this.workers.getNumActive() + this.workers.getNumIdle() > 0);
/*     */   }
/*     */   
/*     */   ObjectPool getPool() {
/* 232 */     if (this.workers == null) {
/* 234 */       PoolableObjectFactory objectFactory = new AuthorityPoolableObjectFactory();
/* 235 */       GenericObjectPoolFactory genericObjectPoolFactory = new GenericObjectPoolFactory(objectFactory, this.poolConfig);
/* 236 */       setPool(genericObjectPoolFactory.createPool());
/*     */     } 
/* 238 */     return this.workers;
/*     */   }
/*     */   
/*     */   void setPool(ObjectPool pool) {
/* 242 */     this.workers = pool;
/*     */   }
/*     */   
/*     */   protected String toKey(String code) {
/* 249 */     return ObjectCaches.toKey(getAuthority(), code);
/*     */   }
/*     */   
/*     */   protected String trimAuthority(String code) {
/* 261 */     return toKey(code);
/*     */   }
/*     */   
/*     */   public abstract Citation getAuthority();
/*     */   
/*     */   public Set getAuthorityCodes(Class type) throws FactoryException {
/* 270 */     Set codes = (Set)this.cache.get(type);
/* 271 */     if (codes == null)
/*     */       try {
/* 273 */         this.cache.writeLock(type);
/* 274 */         codes = (Set)this.cache.peek(type);
/* 275 */         if (codes == null) {
/* 276 */           AbstractCachedAuthorityFactory worker = null;
/*     */           try {
/* 278 */             worker = (AbstractCachedAuthorityFactory)getPool().borrowObject();
/* 279 */             codes = worker.getAuthorityCodes(type);
/* 280 */             this.cache.put(type, codes);
/* 281 */           } catch (FactoryException e) {
/* 282 */             throw e;
/* 283 */           } catch (Exception e) {
/* 284 */             throw new FactoryException(e);
/*     */           } finally {
/*     */             try {
/* 287 */               getPool().returnObject(worker);
/* 288 */             } catch (Exception e) {
/* 289 */               LOGGER.log(Level.WARNING, "Unable to return worker " + e, e);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } finally {
/* 294 */         this.cache.writeUnLock(type);
/*     */       }  
/* 297 */     return codes;
/*     */   }
/*     */   
/*     */   public abstract InternationalString getDescriptionText(String paramString) throws FactoryException;
/*     */   
/*     */   public IdentifiedObject createObject(String code) throws FactoryException {
/*     */     DerivedCRS derivedCRS;
/* 303 */     String key = toKey(code);
/* 304 */     IdentifiedObject obj = (IdentifiedObject)this.cache.get(key);
/* 305 */     if (obj == null)
/*     */       try {
/* 307 */         this.cache.writeLock(key);
/* 308 */         obj = (IdentifiedObject)this.cache.peek(key);
/* 309 */         if (obj == null) {
/* 310 */           AbstractCachedAuthorityFactory worker = null;
/*     */           try {
/* 312 */             worker = (AbstractCachedAuthorityFactory)getPool().borrowObject();
/* 313 */             derivedCRS = worker.createDerivedCRS(code);
/* 314 */             this.cache.put(key, derivedCRS);
/* 315 */           } catch (FactoryException e) {
/* 316 */             throw e;
/* 317 */           } catch (Exception e) {
/* 318 */             throw new FactoryException(e);
/*     */           } finally {
/*     */             try {
/* 321 */               getPool().returnObject(worker);
/* 322 */             } catch (Exception e) {
/* 323 */               LOGGER.log(Level.WARNING, "Unable to return worker " + e, e);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } finally {
/* 328 */         this.cache.writeUnLock(key);
/*     */       }  
/* 331 */     return (IdentifiedObject)derivedCRS;
/*     */   }
/*     */   
/*     */   public synchronized CompoundCRS createCompoundCRS(String code) throws FactoryException {
/* 338 */     String key = toKey(code);
/* 339 */     CompoundCRS crs = (CompoundCRS)this.cache.get(key);
/* 340 */     if (crs == null)
/*     */       try {
/* 342 */         this.cache.writeLock(key);
/* 343 */         crs = (CompoundCRS)this.cache.peek(key);
/* 344 */         if (crs == null) {
/* 345 */           AbstractCachedAuthorityFactory worker = null;
/*     */           try {
/* 347 */             worker = (AbstractCachedAuthorityFactory)getPool().borrowObject();
/* 348 */             crs = worker.createCompoundCRS(code);
/* 349 */             this.cache.put(key, crs);
/* 350 */           } catch (FactoryException e) {
/* 351 */             throw e;
/* 352 */           } catch (Exception e) {
/* 353 */             throw new FactoryException(e);
/*     */           } finally {
/*     */             try {
/* 356 */               getPool().returnObject(worker);
/* 357 */             } catch (Exception e) {
/* 358 */               LOGGER.log(Level.WARNING, "Unable to return worker " + e, e);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } finally {
/* 363 */         this.cache.writeUnLock(key);
/*     */       }  
/* 366 */     return crs;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/* 371 */     final String key = toKey(code);
/* 372 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 375 */             return worker.createCoordinateReferenceSystem(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public DerivedCRS createDerivedCRS(String code) throws FactoryException {
/* 382 */     final String key = toKey(code);
/* 383 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 386 */             return worker.createEngineeringCRS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
/* 392 */     final String key = toKey(code);
/* 393 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 396 */             return worker.createGeocentricCRS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public GeographicCRS createGeographicCRS(String code) throws FactoryException {
/* 402 */     final String key = toKey(code);
/* 403 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 406 */             return worker.createGeographicCRS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public ImageCRS createImageCRS(String code) throws FactoryException {
/* 412 */     final String key = toKey(code);
/* 413 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 416 */             return worker.createImageCRS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public ProjectedCRS createProjectedCRS(String code) throws FactoryException {
/* 422 */     final String key = toKey(code);
/* 423 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 426 */             return worker.createProjectedCRS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public TemporalCRS createTemporalCRS(String code) throws FactoryException {
/* 431 */     final String key = toKey(code);
/* 432 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 435 */             return worker.createTemporalCRS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public VerticalCRS createVerticalCRS(String code) throws FactoryException {
/* 442 */     final String key = toKey(code);
/* 443 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 446 */             return worker.createVerticalCRS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public CartesianCS createCartesianCS(String code) throws FactoryException {
/* 455 */     final String key = toKey(code);
/* 456 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 459 */             return worker.createCartesianCS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public CoordinateSystem createCoordinateSystem(String code) throws FactoryException {
/* 465 */     final String key = toKey(code);
/* 466 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 469 */             return worker.createCoordinateSystem(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public CoordinateSystemAxis createCoordinateSystemAxis(String code) throws FactoryException {
/* 476 */     final String key = toKey(code);
/* 477 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 480 */             return worker.createCoordinateSystemAxis(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public CylindricalCS createCylindricalCS(String code) throws FactoryException {
/* 486 */     final String key = toKey(code);
/* 487 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 490 */             return worker.createCylindricalCS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public EllipsoidalCS createEllipsoidalCS(String code) throws FactoryException {
/* 496 */     final String key = toKey(code);
/* 497 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 500 */             return worker.createEllipsoidalCS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public PolarCS createPolarCS(String code) throws FactoryException {
/* 506 */     final String key = toKey(code);
/* 507 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 510 */             return worker.createPolarCS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public SphericalCS createSphericalCS(String code) throws FactoryException {
/* 516 */     final String key = toKey(code);
/* 517 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 520 */             return worker.createSphericalCS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public TimeCS createTimeCS(String code) throws FactoryException {
/* 526 */     final String key = toKey(code);
/* 527 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 530 */             return worker.createTimeCS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Unit<?> createUnit(String code) throws FactoryException {
/* 536 */     final String key = toKey(code);
/* 537 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 540 */             return worker.createUnit(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public VerticalCS createVerticalCS(String code) throws FactoryException {
/* 546 */     final String key = toKey(code);
/* 547 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 550 */             return worker.createVerticalCS(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Datum createDatum(String code) throws FactoryException {
/* 559 */     final String key = toKey(code);
/* 560 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 563 */             return worker.createDatum(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public Ellipsoid createEllipsoid(String code) throws FactoryException {
/* 569 */     final String key = toKey(code);
/* 570 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 573 */             return worker.createEllipsoid(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public EngineeringDatum createEngineeringDatum(String code) throws FactoryException {
/* 579 */     final String key = toKey(code);
/* 580 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 583 */             return worker.createEngineeringDatum(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public GeodeticDatum createGeodeticDatum(String code) throws FactoryException {
/* 589 */     final String key = toKey(code);
/* 590 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 593 */             return worker.createGeodeticDatum(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public ImageDatum createImageDatum(String code) throws FactoryException {
/* 599 */     final String key = toKey(code);
/* 600 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 603 */             return worker.createImageDatum(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public PrimeMeridian createPrimeMeridian(String code) throws FactoryException {
/* 609 */     final String key = toKey(code);
/* 610 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 613 */             return worker.createPrimeMeridian(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public TemporalDatum createTemporalDatum(String code) throws FactoryException {
/* 619 */     final String key = toKey(code);
/* 620 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 623 */             return worker.createTemporalDatum(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public VerticalDatum createVerticalDatum(String code) throws FactoryException {
/* 629 */     final String key = toKey(code);
/* 630 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 633 */             return worker.createVerticalDatum(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public CoordinateOperation createCoordinateOperation(String code) throws FactoryException {
/* 639 */     final String key = toKey(code);
/* 640 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 643 */             return worker.createCoordinateOperation(key);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public synchronized Set createFromCoordinateReferenceSystemCodes(final String sourceCode, final String targetCode) throws FactoryException {
/* 651 */     Object key = ObjectCaches.toKey(getAuthority(), sourceCode, targetCode);
/* 652 */     return createWith(key, new WorkerSafeRunnable() {
/*     */           public Object run(AbstractCachedAuthorityFactory worker) throws FactoryException {
/* 655 */             return worker.createFromCoordinateReferenceSystemCodes(sourceCode, targetCode);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   protected <T> T createWith(Object key, WorkerSafeRunnable runner) throws FactoryException {
/* 704 */     T value = (T)this.cache.get(key);
/* 705 */     if (value == null)
/*     */       try {
/* 707 */         this.cache.writeLock(key);
/* 708 */         value = (T)this.cache.peek(key);
/* 709 */         if (value == null) {
/* 710 */           AbstractCachedAuthorityFactory worker = null;
/*     */           try {
/* 712 */             worker = (AbstractCachedAuthorityFactory)getPool().borrowObject();
/* 713 */             value = (T)runner.run(worker);
/* 714 */           } catch (FactoryException e) {
/* 715 */             throw e;
/* 716 */           } catch (Exception e) {
/* 717 */             throw new FactoryException(e);
/*     */           } finally {
/*     */             try {
/* 720 */               getPool().returnObject(worker);
/* 721 */             } catch (Exception e) {
/* 722 */               LOGGER.log(Level.WARNING, "Unable to return worker " + e, e);
/*     */             } 
/*     */           } 
/* 725 */           this.cache.put(key, value);
/*     */         } 
/*     */       } finally {
/* 728 */         this.cache.writeUnLock(key);
/*     */       }  
/* 731 */     return value;
/*     */   }
/*     */   
/*     */   protected abstract class WorkerSafeRunnable {
/*     */     public abstract Object run(AbstractCachedAuthorityFactory param1AbstractCachedAuthorityFactory) throws FactoryException;
/*     */   }
/*     */   
/*     */   public String getBackingStoreDescription() throws FactoryException {
/* 743 */     AbstractCachedAuthorityFactory worker = null;
/*     */     try {
/* 745 */       worker = (AbstractCachedAuthorityFactory)getPool().borrowObject();
/* 746 */       return worker.getBackingStoreDescription();
/* 747 */     } catch (FactoryException e) {
/* 748 */       throw e;
/* 749 */     } catch (Exception e) {
/* 750 */       throw new FactoryException(e);
/*     */     } finally {
/*     */       try {
/* 754 */         getPool().returnObject(worker);
/* 755 */       } catch (Exception e) {
/* 756 */         LOGGER.log(Level.WARNING, "Unable to return worker " + e, e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void dispose() throws FactoryException {
/* 769 */     if (this.workers != null) {
/*     */       try {
/* 771 */         this.workers.clear();
/* 772 */       } catch (FactoryException e) {
/* 773 */         throw e;
/* 774 */       } catch (Exception e) {
/* 775 */         throw new FactoryException(e);
/*     */       } 
/* 777 */       this.workers = null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract void activateWorker(AbstractCachedAuthorityFactory paramAbstractCachedAuthorityFactory) throws Exception;
/*     */   
/*     */   protected abstract void destroyWorker(AbstractCachedAuthorityFactory paramAbstractCachedAuthorityFactory) throws Exception;
/*     */   
/*     */   protected abstract AbstractCachedAuthorityFactory makeWorker() throws Exception;
/*     */   
/*     */   protected abstract void passivateWorker(AbstractCachedAuthorityFactory paramAbstractCachedAuthorityFactory) throws Exception;
/*     */   
/*     */   protected abstract boolean validateWorker(AbstractCachedAuthorityFactory paramAbstractCachedAuthorityFactory);
/*     */   
/*     */   private class AuthorityPoolableObjectFactory implements PoolableObjectFactory {
/*     */     public void activateObject(Object obj) throws Exception {
/* 792 */       AbstractCachedAuthorityFactory worker = (AbstractCachedAuthorityFactory)obj;
/* 793 */       worker.cache = AbstractAuthorityMediator.this.cache;
/* 794 */       AbstractAuthorityMediator.this.activateWorker(worker);
/*     */     }
/*     */     
/*     */     public void destroyObject(Object obj) throws Exception {
/* 798 */       AbstractAuthorityMediator.this.destroyWorker((AbstractCachedAuthorityFactory)obj);
/*     */     }
/*     */     
/*     */     public Object makeObject() throws Exception {
/* 802 */       AbstractCachedAuthorityFactory worker = AbstractAuthorityMediator.this.makeWorker();
/* 803 */       return worker;
/*     */     }
/*     */     
/*     */     public void passivateObject(Object obj) throws Exception {
/* 807 */       AbstractAuthorityMediator.this.passivateWorker((AbstractCachedAuthorityFactory)obj);
/*     */     }
/*     */     
/*     */     public boolean validateObject(Object obj) {
/* 811 */       return AbstractAuthorityMediator.this.validateWorker((AbstractCachedAuthorityFactory)obj);
/*     */     }
/*     */   }
/*     */   
/*     */   public IdentifiedObjectFinder getIdentifiedObjectFinder(Class type) throws FactoryException {
/* 858 */     return new LazyCachedFinder(type);
/*     */   }
/*     */   
/*     */   private final class LazyCachedFinder extends IdentifiedObjectFinder {
/*     */     private Class type;
/*     */     
/*     */     LazyCachedFinder(Class type) {
/* 878 */       super(AbstractAuthorityMediator.this, type);
/* 879 */       this.type = type;
/*     */     }
/*     */     
/*     */     public IdentifiedObject find(IdentifiedObject object) throws FactoryException {
/* 890 */       IdentifiedObject candidate = (IdentifiedObject)AbstractAuthorityMediator.this.findCache.get(object);
/* 891 */       if (candidate != null)
/* 892 */         return candidate; 
/*     */       try {
/*     */         IdentifiedObject found;
/* 895 */         AbstractAuthorityMediator.this.findCache.writeLock(object);
/* 897 */         AbstractCachedAuthorityFactory worker = null;
/*     */         try {
/* 899 */           worker = (AbstractCachedAuthorityFactory)AbstractAuthorityMediator.this.getPool().borrowObject();
/* 900 */           worker.cache = ObjectCaches.chain(ObjectCaches.create("weak", 3000), AbstractAuthorityMediator.this.cache);
/* 901 */           worker.findCache = AbstractAuthorityMediator.this.findCache;
/* 903 */           setProxy(AuthorityFactoryProxy.getInstance(worker, this.type));
/* 905 */           found = super.find(object);
/* 906 */         } catch (Exception e) {
/* 907 */           throw new FactoryException(e);
/*     */         } finally {
/* 910 */           setProxy(null);
/* 911 */           worker.cache = AbstractAuthorityMediator.this.cache;
/* 912 */           worker.findCache = AbstractAuthorityMediator.this.findCache;
/*     */           try {
/* 914 */             AbstractAuthorityMediator.this.getPool().returnObject(worker);
/* 915 */           } catch (Exception e) {
/* 916 */             LOGGER.log(Level.WARNING, "Unable to return worker " + e, e);
/*     */           } 
/*     */         } 
/* 919 */         if (found == null)
/* 920 */           return null; 
/* 922 */         candidate = (IdentifiedObject)AbstractAuthorityMediator.this.findCache.peek(object);
/* 923 */         if (candidate == null) {
/* 924 */           AbstractAuthorityMediator.this.findCache.put(object, found);
/* 925 */           return found;
/*     */         } 
/* 928 */         return candidate;
/*     */       } finally {
/* 931 */         AbstractAuthorityMediator.this.findCache.writeUnLock(object);
/*     */       } 
/*     */     }
/*     */     
/*     */     protected Citation getAuthority() {
/* 935 */       return AbstractAuthorityMediator.this.getAuthority();
/*     */     }
/*     */     
/*     */     public String findIdentifier(IdentifiedObject object) throws FactoryException {
/* 943 */       IdentifiedObject candidate = (IdentifiedObject)AbstractAuthorityMediator.this.findCache.get(object);
/* 944 */       if (candidate != null)
/* 945 */         return getIdentifier(candidate); 
/* 947 */       return super.findIdentifier(object);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\AbstractAuthorityMediator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */