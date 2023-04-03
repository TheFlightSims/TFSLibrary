/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.util.Set;
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
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public final class CachedCRSAuthorityDecorator extends AbstractAuthorityFactory implements AuthorityFactory, CRSAuthorityFactory, BufferedFactory {
/*     */   ObjectCache cache;
/*     */   
/*     */   private CRSAuthorityFactory crsAuthority;
/*     */   
/*     */   private AbstractAuthorityFactory delegate;
/*     */   
/*     */   public CachedCRSAuthorityDecorator(CRSAuthorityFactory factory) {
/* 101 */     this(factory, createCache(GeoTools.getDefaultHints()));
/*     */   }
/*     */   
/*     */   protected CachedCRSAuthorityDecorator(CRSAuthorityFactory factory, ObjectCache cache) {
/* 124 */     super(((ReferencingFactory)factory).getPriority());
/* 125 */     this.cache = cache;
/* 126 */     this.crsAuthority = factory;
/* 127 */     this.delegate = (AbstractAuthorityFactory)factory;
/*     */   }
/*     */   
/*     */   protected static ObjectCache createCache(Hints hints) throws FactoryRegistryException {
/* 133 */     return ObjectCaches.create(hints);
/*     */   }
/*     */   
/*     */   protected String toKey(String code) {
/* 140 */     return ObjectCaches.toKey(getAuthority(), code);
/*     */   }
/*     */   
/*     */   public IdentifiedObject createObject(String code) throws FactoryException {
/* 147 */     String key = toKey(code);
/* 148 */     IdentifiedObject obj = (IdentifiedObject)this.cache.get(key);
/* 149 */     if (obj == null)
/*     */       try {
/* 151 */         this.cache.writeLock(key);
/* 152 */         obj = (IdentifiedObject)this.cache.peek(key);
/* 153 */         if (obj == null) {
/* 154 */           obj = this.crsAuthority.createObject(code);
/* 155 */           this.cache.put(key, obj);
/*     */         } 
/*     */       } finally {
/* 158 */         this.cache.writeUnLock(key);
/*     */       }  
/* 161 */     return obj;
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 165 */     return this.crsAuthority.getAuthority();
/*     */   }
/*     */   
/*     */   public Set getAuthorityCodes(Class type) throws FactoryException {
/* 169 */     return this.crsAuthority.getAuthorityCodes(type);
/*     */   }
/*     */   
/*     */   public InternationalString getDescriptionText(String code) throws FactoryException {
/* 174 */     return this.crsAuthority.getDescriptionText(code);
/*     */   }
/*     */   
/*     */   public synchronized CompoundCRS createCompoundCRS(String code) throws FactoryException {
/* 182 */     String key = toKey(code);
/* 183 */     CompoundCRS crs = (CompoundCRS)this.cache.get(key);
/* 184 */     if (crs == null)
/*     */       try {
/* 186 */         this.cache.writeLock(key);
/* 187 */         crs = (CompoundCRS)this.cache.peek(key);
/* 188 */         if (crs == null) {
/* 189 */           crs = this.crsAuthority.createCompoundCRS(code);
/* 190 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 193 */         this.cache.writeUnLock(key);
/*     */       }  
/* 196 */     return crs;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/* 201 */     String key = toKey(code);
/* 202 */     CoordinateReferenceSystem crs = (CoordinateReferenceSystem)this.cache.get(key);
/* 204 */     if (crs == null)
/*     */       try {
/* 206 */         this.cache.writeLock(key);
/* 207 */         crs = (CoordinateReferenceSystem)this.cache.peek(key);
/* 208 */         if (crs == null) {
/* 209 */           crs = this.crsAuthority.createCoordinateReferenceSystem(code);
/* 210 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 213 */         this.cache.writeUnLock(key);
/*     */       }  
/* 216 */     return crs;
/*     */   }
/*     */   
/*     */   public DerivedCRS createDerivedCRS(String code) throws FactoryException {
/* 220 */     String key = toKey(code);
/* 221 */     DerivedCRS crs = (DerivedCRS)this.cache.get(key);
/* 222 */     if (crs == null)
/*     */       try {
/* 224 */         this.cache.writeLock(key);
/* 225 */         crs = (DerivedCRS)this.cache.peek(key);
/* 226 */         if (crs == null) {
/* 227 */           crs = this.crsAuthority.createDerivedCRS(code);
/* 228 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 231 */         this.cache.writeUnLock(key);
/*     */       }  
/* 234 */     return crs;
/*     */   }
/*     */   
/*     */   public EngineeringCRS createEngineeringCRS(String code) throws FactoryException {
/* 239 */     String key = toKey(code);
/* 240 */     EngineeringCRS crs = (EngineeringCRS)this.cache.get(key);
/* 241 */     if (crs == null)
/*     */       try {
/* 243 */         this.cache.writeLock(key);
/* 244 */         crs = (EngineeringCRS)this.cache.peek(key);
/* 245 */         if (crs == null) {
/* 246 */           crs = this.crsAuthority.createEngineeringCRS(code);
/* 247 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 250 */         this.cache.writeUnLock(key);
/*     */       }  
/* 253 */     return crs;
/*     */   }
/*     */   
/*     */   public GeocentricCRS createGeocentricCRS(String code) throws FactoryException {
/* 258 */     String key = toKey(code);
/* 259 */     GeocentricCRS crs = (GeocentricCRS)this.cache.get(key);
/* 260 */     if (crs == null)
/*     */       try {
/* 262 */         this.cache.writeLock(key);
/* 263 */         crs = (GeocentricCRS)this.cache.peek(key);
/* 264 */         if (crs == null) {
/* 265 */           crs = this.crsAuthority.createGeocentricCRS(code);
/* 266 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 269 */         this.cache.writeUnLock(key);
/*     */       }  
/* 272 */     return crs;
/*     */   }
/*     */   
/*     */   public GeographicCRS createGeographicCRS(String code) throws FactoryException {
/* 277 */     String key = toKey(code);
/* 278 */     GeographicCRS crs = (GeographicCRS)this.cache.get(key);
/* 279 */     if (crs == null)
/*     */       try {
/* 281 */         this.cache.writeLock(key);
/* 282 */         crs = (GeographicCRS)this.cache.peek(key);
/* 283 */         if (crs == null) {
/* 284 */           crs = this.crsAuthority.createGeographicCRS(code);
/* 285 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 288 */         this.cache.writeUnLock(key);
/*     */       }  
/* 291 */     return crs;
/*     */   }
/*     */   
/*     */   public ImageCRS createImageCRS(String code) throws FactoryException {
/* 295 */     String key = toKey(code);
/* 296 */     ImageCRS crs = (ImageCRS)this.cache.get(key);
/* 297 */     if (crs == null)
/*     */       try {
/* 299 */         this.cache.writeLock(key);
/* 300 */         crs = (ImageCRS)this.cache.peek(key);
/* 301 */         if (crs == null) {
/* 302 */           crs = this.crsAuthority.createImageCRS(code);
/* 303 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 306 */         this.cache.writeUnLock(key);
/*     */       }  
/* 309 */     return crs;
/*     */   }
/*     */   
/*     */   public ProjectedCRS createProjectedCRS(String code) throws FactoryException {
/* 313 */     String key = toKey(code);
/* 314 */     ProjectedCRS crs = (ProjectedCRS)this.cache.get(key);
/* 315 */     if (crs == null)
/*     */       try {
/* 317 */         this.cache.writeLock(key);
/* 318 */         crs = (ProjectedCRS)this.cache.peek(key);
/* 319 */         if (crs == null) {
/* 320 */           crs = this.crsAuthority.createProjectedCRS(code);
/* 321 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 324 */         this.cache.writeUnLock(key);
/*     */       }  
/* 327 */     return crs;
/*     */   }
/*     */   
/*     */   public TemporalCRS createTemporalCRS(String code) throws FactoryException {
/* 331 */     String key = toKey(code);
/* 332 */     TemporalCRS crs = (TemporalCRS)this.cache.get(key);
/* 333 */     if (crs == null)
/*     */       try {
/* 335 */         this.cache.writeLock(key);
/* 336 */         crs = (TemporalCRS)this.cache.peek(key);
/* 337 */         if (crs == null) {
/* 338 */           crs = this.crsAuthority.createTemporalCRS(code);
/* 339 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 342 */         this.cache.writeUnLock(key);
/*     */       }  
/* 345 */     return crs;
/*     */   }
/*     */   
/*     */   public VerticalCRS createVerticalCRS(String code) throws FactoryException {
/* 349 */     String key = toKey(code);
/* 350 */     VerticalCRS crs = (VerticalCRS)this.cache.get(key);
/* 351 */     if (crs == null)
/*     */       try {
/* 353 */         this.cache.writeLock(key);
/* 354 */         crs = (VerticalCRS)this.cache.peek(key);
/* 355 */         if (crs == null) {
/* 356 */           crs = this.crsAuthority.createVerticalCRS(code);
/* 357 */           this.cache.put(key, crs);
/*     */         } 
/*     */       } finally {
/* 360 */         this.cache.writeUnLock(key);
/*     */       }  
/* 363 */     return crs;
/*     */   }
/*     */   
/*     */   public void dispose() throws FactoryException {
/* 370 */     this.delegate.dispose();
/* 371 */     this.cache.clear();
/* 372 */     this.cache = null;
/* 373 */     this.delegate = null;
/*     */   }
/*     */   
/*     */   public String getBackingStoreDescription() throws FactoryException {
/* 377 */     return this.delegate.getBackingStoreDescription();
/*     */   }
/*     */   
/*     */   public synchronized IdentifiedObjectFinder getIdentifiedObjectFinder(Class<? extends IdentifiedObject> type) throws FactoryException {
/* 391 */     return new Finder(this.delegate.getIdentifiedObjectFinder(type), ObjectCaches.create("weak", 250));
/*     */   }
/*     */   
/*     */   private final class Finder extends IdentifiedObjectFinder.Adapter {
/*     */     private ObjectCache findCache;
/*     */     
/*     */     Finder(IdentifiedObjectFinder finder, ObjectCache tempCache) {
/* 417 */       this.findCache = tempCache;
/*     */     }
/*     */     
/*     */     public IdentifiedObject find(IdentifiedObject object) throws FactoryException {
/* 437 */       IdentifiedObject candidate = (IdentifiedObject)this.findCache.get(object);
/* 439 */       if (candidate == null) {
/* 442 */         IdentifiedObject found = this.finder.find(object);
/* 443 */         if (found != null)
/*     */           try {
/* 445 */             this.findCache.writeLock(object);
/* 446 */             candidate = (IdentifiedObject)this.findCache.peek(object);
/* 447 */             if (candidate == null) {
/* 448 */               this.findCache.put(object, found);
/* 449 */               return found;
/*     */             } 
/*     */           } finally {
/* 453 */             this.findCache.writeLock(object);
/*     */           }  
/*     */       } 
/* 457 */       return candidate;
/*     */     }
/*     */     
/*     */     public String findIdentifier(IdentifiedObject object) throws FactoryException {
/* 466 */       IdentifiedObject candidate = (IdentifiedObject)this.findCache.get(object);
/* 467 */       if (candidate != null)
/* 468 */         return getIdentifier(candidate); 
/* 472 */       return this.finder.findIdentifier(object);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\CachedCRSAuthorityDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */