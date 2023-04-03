/*     */ package org.geotools.referencing.datum;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.AbstractIdentifiedObject;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.operation.matrix.XMatrix;
/*     */ import org.geotools.referencing.wkt.Formatter;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.datum.Datum;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ import org.opengis.referencing.datum.PrimeMeridian;
/*     */ import org.opengis.referencing.operation.Matrix;
/*     */ 
/*     */ public class DefaultGeodeticDatum extends AbstractDatum implements GeodeticDatum {
/*     */   private static final long serialVersionUID = 8832100095648302943L;
/*     */   
/*     */   public static final DefaultGeodeticDatum WGS84;
/*     */   
/*     */   public static final String BURSA_WOLF_KEY = "bursaWolf";
/*     */   
/*     */   private final Ellipsoid ellipsoid;
/*     */   
/*     */   private final PrimeMeridian primeMeridian;
/*     */   
/*     */   private final BursaWolfParameters[] bursaWolf;
/*     */   
/*     */   static {
/*  70 */     ReferenceIdentifier[] identifiers = { (ReferenceIdentifier)new NamedIdentifier(Citations.OGC, "WGS84"), (ReferenceIdentifier)new NamedIdentifier(Citations.ORACLE, "WGS 84"), (ReferenceIdentifier)new NamedIdentifier(null, "WGS_84"), (ReferenceIdentifier)new NamedIdentifier(null, "WGS 1984"), (ReferenceIdentifier)new NamedIdentifier(Citations.EPSG, "WGS_1984"), (ReferenceIdentifier)new NamedIdentifier(Citations.ESRI, "D_WGS_1984"), (ReferenceIdentifier)new NamedIdentifier(Citations.EPSG, "World Geodetic System 1984") };
/*  79 */     Map<String, Object> properties = new HashMap<String, Object>(4);
/*  80 */     properties.put("name", identifiers[0]);
/*  81 */     properties.put("alias", identifiers);
/*  82 */     WGS84 = new DefaultGeodeticDatum(properties, DefaultEllipsoid.WGS84, DefaultPrimeMeridian.GREENWICH);
/*     */   }
/*     */   
/*     */   public DefaultGeodeticDatum(GeodeticDatum datum) {
/* 117 */     super((Datum)datum);
/* 118 */     this.ellipsoid = datum.getEllipsoid();
/* 119 */     this.primeMeridian = datum.getPrimeMeridian();
/* 120 */     this.bursaWolf = (datum instanceof DefaultGeodeticDatum) ? ((DefaultGeodeticDatum)datum).bursaWolf : null;
/*     */   }
/*     */   
/*     */   public DefaultGeodeticDatum(String name, Ellipsoid ellipsoid, PrimeMeridian primeMeridian) {
/* 135 */     this(Collections.singletonMap("name", name), ellipsoid, primeMeridian);
/*     */   }
/*     */   
/*     */   public DefaultGeodeticDatum(Map<String, ?> properties, Ellipsoid ellipsoid, PrimeMeridian primeMeridian) {
/* 164 */     super(properties);
/*     */     BursaWolfParameters[] bursaWolf;
/* 165 */     this.ellipsoid = ellipsoid;
/* 166 */     this.primeMeridian = primeMeridian;
/* 167 */     ensureNonNull("ellipsoid", ellipsoid);
/* 168 */     ensureNonNull("primeMeridian", primeMeridian);
/* 170 */     Object object = properties.get("bursaWolf");
/* 171 */     if (object instanceof BursaWolfParameters) {
/* 172 */       bursaWolf = new BursaWolfParameters[] { ((BursaWolfParameters)object).clone() };
/*     */     } else {
/* 176 */       bursaWolf = (BursaWolfParameters[])object;
/* 177 */       if (bursaWolf != null)
/* 178 */         if (bursaWolf.length == 0) {
/* 179 */           bursaWolf = null;
/*     */         } else {
/* 181 */           Set<BursaWolfParameters> s = new LinkedHashSet<BursaWolfParameters>();
/* 182 */           for (int i = 0; i < bursaWolf.length; i++)
/* 183 */             s.add(bursaWolf[i].clone()); 
/* 185 */           bursaWolf = s.<BursaWolfParameters>toArray(new BursaWolfParameters[s.size()]);
/*     */         }  
/*     */     } 
/* 189 */     this.bursaWolf = bursaWolf;
/*     */   }
/*     */   
/*     */   public Ellipsoid getEllipsoid() {
/* 196 */     return this.ellipsoid;
/*     */   }
/*     */   
/*     */   public PrimeMeridian getPrimeMeridian() {
/* 203 */     return this.primeMeridian;
/*     */   }
/*     */   
/*     */   public BursaWolfParameters[] getBursaWolfParameters() {
/* 213 */     if (this.bursaWolf != null)
/* 214 */       return (BursaWolfParameters[])this.bursaWolf.clone(); 
/* 216 */     return new BursaWolfParameters[0];
/*     */   }
/*     */   
/*     */   public BursaWolfParameters getBursaWolfParameters(GeodeticDatum target) {
/* 228 */     if (this.bursaWolf != null)
/* 229 */       for (int i = 0; i < this.bursaWolf.length; i++) {
/* 230 */         BursaWolfParameters candidate = this.bursaWolf[i];
/* 231 */         if (equals((IdentifiedObject)target, (IdentifiedObject)candidate.targetDatum, false))
/* 232 */           return candidate.clone(); 
/*     */       }  
/* 236 */     return null;
/*     */   }
/*     */   
/*     */   public static Matrix getAffineTransform(GeodeticDatum source, GeodeticDatum target) {
/* 252 */     return (Matrix)getAffineTransform(source, target, (Set<GeodeticDatum>)null);
/*     */   }
/*     */   
/*     */   private static XMatrix getAffineTransform(GeodeticDatum source, GeodeticDatum target, Set<GeodeticDatum> exclusion) {
/* 271 */     ensureNonNull("source", source);
/* 272 */     ensureNonNull("target", target);
/* 273 */     if (source instanceof DefaultGeodeticDatum) {
/* 274 */       BursaWolfParameters[] bursaWolf = ((DefaultGeodeticDatum)source).bursaWolf;
/* 275 */       if (bursaWolf != null)
/* 276 */         for (int i = 0; i < bursaWolf.length; i++) {
/* 277 */           BursaWolfParameters transformation = bursaWolf[i];
/* 278 */           if (equals((IdentifiedObject)target, (IdentifiedObject)transformation.targetDatum, false))
/* 279 */             return transformation.getAffineTransform(); 
/*     */         }  
/*     */     } 
/* 288 */     if (target instanceof DefaultGeodeticDatum) {
/* 289 */       BursaWolfParameters[] bursaWolf = ((DefaultGeodeticDatum)target).bursaWolf;
/* 290 */       if (bursaWolf != null)
/* 291 */         for (int i = 0; i < bursaWolf.length; i++) {
/* 292 */           BursaWolfParameters transformation = bursaWolf[i];
/* 293 */           if (equals((IdentifiedObject)source, (IdentifiedObject)transformation.targetDatum, false)) {
/* 294 */             XMatrix matrix = transformation.getAffineTransform();
/* 295 */             matrix.invert();
/* 296 */             return matrix;
/*     */           } 
/*     */         }  
/*     */     } 
/* 309 */     if (source instanceof DefaultGeodeticDatum && target instanceof DefaultGeodeticDatum) {
/* 310 */       BursaWolfParameters[] sourceParam = ((DefaultGeodeticDatum)source).bursaWolf;
/* 311 */       BursaWolfParameters[] targetParam = ((DefaultGeodeticDatum)target).bursaWolf;
/* 312 */       if (sourceParam != null && targetParam != null)
/* 315 */         for (int i = 0; i < sourceParam.length; i++) {
/* 316 */           GeodeticDatum sourceStep = (sourceParam[i]).targetDatum;
/* 317 */           for (int j = 0; j < targetParam.length; j++) {
/* 318 */             GeodeticDatum targetStep = (targetParam[j]).targetDatum;
/* 319 */             if (equals((IdentifiedObject)sourceStep, (IdentifiedObject)targetStep, false)) {
/* 321 */               if (exclusion == null)
/* 322 */                 exclusion = new HashSet<GeodeticDatum>(); 
/* 324 */               if (exclusion.add(source)) {
/* 325 */                 if (exclusion.add(target)) {
/* 326 */                   XMatrix step1 = getAffineTransform(source, sourceStep, exclusion);
/* 327 */                   if (step1 != null) {
/* 328 */                     XMatrix step2 = getAffineTransform(targetStep, target, exclusion);
/* 329 */                     if (step2 != null) {
/* 337 */                       step2.multiply((Matrix)step1);
/* 338 */                       return step2;
/*     */                     } 
/*     */                   } 
/* 341 */                   exclusion.remove(target);
/*     */                 } 
/* 343 */                 exclusion.remove(source);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         }  
/*     */     } 
/* 350 */     return null;
/*     */   }
/*     */   
/*     */   public static boolean isWGS84(Datum datum) {
/* 360 */     if (datum instanceof AbstractIdentifiedObject)
/* 361 */       return WGS84.equals((AbstractIdentifiedObject)datum, false); 
/* 364 */     return (datum != null && datum.equals(WGS84));
/*     */   }
/*     */   
/*     */   public boolean equals(AbstractIdentifiedObject object, boolean compareMetadata) {
/* 377 */     if (object == this)
/* 378 */       return true; 
/* 380 */     if (super.equals(object, compareMetadata)) {
/* 381 */       DefaultGeodeticDatum that = (DefaultGeodeticDatum)object;
/* 382 */       if (equals((IdentifiedObject)this.ellipsoid, (IdentifiedObject)that.ellipsoid, compareMetadata) && equals((IdentifiedObject)this.primeMeridian, (IdentifiedObject)that.primeMeridian, compareMetadata))
/* 395 */         return (!compareMetadata || Arrays.equals((Object[])this.bursaWolf, (Object[])that.bursaWolf)); 
/*     */     } 
/* 398 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 413 */     int code = 0x15CF875F ^ 37 * (super.hashCode() ^ 37 * (this.ellipsoid.hashCode() ^ 37 * this.primeMeridian.hashCode()));
/* 417 */     return code;
/*     */   }
/*     */   
/*     */   protected String formatWKT(Formatter formatter) {
/* 432 */     formatter.append((IdentifiedObject)this.ellipsoid);
/* 433 */     if (this.bursaWolf != null)
/* 434 */       for (int i = 0; i < this.bursaWolf.length; i++) {
/* 435 */         BursaWolfParameters transformation = this.bursaWolf[i];
/* 436 */         if (isWGS84((Datum)transformation.targetDatum)) {
/* 437 */           formatter.append(transformation);
/*     */           break;
/*     */         } 
/*     */       }  
/* 442 */     return "DATUM";
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\datum\DefaultGeodeticDatum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */