/*     */ package org.geotools.referencing.factory.wms;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.cs.DefaultEllipsoidalCS;
/*     */ import org.geotools.referencing.datum.DefaultEllipsoid;
/*     */ import org.geotools.referencing.datum.DefaultPrimeMeridian;
/*     */ import org.geotools.referencing.factory.DirectAuthorityFactory;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.GeographicCRS;
/*     */ import org.opengis.referencing.cs.EllipsoidalCS;
/*     */ import org.opengis.referencing.datum.Ellipsoid;
/*     */ import org.opengis.referencing.datum.GeodeticDatum;
/*     */ import org.opengis.referencing.datum.PrimeMeridian;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class WebCRSFactory extends DirectAuthorityFactory implements CRSAuthorityFactory {
/*     */   private static final String PREFIX = "CRS";
/*     */   
/*  79 */   private final Map<Integer, CoordinateReferenceSystem> crsMap = new TreeMap<Integer, CoordinateReferenceSystem>();
/*     */   
/*     */   public WebCRSFactory() {
/*  86 */     this((Hints)null);
/*     */   }
/*     */   
/*     */   public WebCRSFactory(Hints hints) {
/*  93 */     super(hints, 50);
/*     */   }
/*     */   
/*     */   private synchronized void ensureInitialized() throws FactoryException {
/* 104 */     if (this.crsMap.isEmpty()) {
/* 105 */       add(84, "WGS84", (Ellipsoid)DefaultEllipsoid.WGS84);
/* 106 */       add(83, "NAD83", (Ellipsoid)DefaultEllipsoid.GRS80);
/* 107 */       add(27, "NAD27", (Ellipsoid)DefaultEllipsoid.CLARKE_1866);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void add(int code, String name, Ellipsoid ellipsoid) throws FactoryException {
/* 125 */     assert Thread.holdsLock(this);
/* 126 */     Map<Object, Object> properties = new HashMap<Object, Object>();
/* 127 */     Citation authority = getAuthority();
/* 128 */     String text = String.valueOf(code);
/* 129 */     properties.put("name", name);
/* 130 */     properties.put("authority", authority);
/* 131 */     GeodeticDatum datum = this.factories.getDatumFactory().createGeodeticDatum(properties, ellipsoid, (PrimeMeridian)DefaultPrimeMeridian.GREENWICH);
/* 134 */     properties.put("identifiers", new NamedIdentifier[] { new NamedIdentifier(authority, text), new NamedIdentifier(authority, "CRS" + text) });
/* 138 */     GeographicCRS geographicCRS = this.factories.getCRSFactory().createGeographicCRS(properties, datum, (EllipsoidalCS)DefaultEllipsoidalCS.GEODETIC_2D);
/* 140 */     if (this.crsMap.put(Integer.valueOf(code), geographicCRS) != null)
/* 141 */       throw new IllegalArgumentException(text); 
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 149 */     return Citations.CRS;
/*     */   }
/*     */   
/*     */   public Set getAuthorityCodes(Class type) throws FactoryException {
/* 164 */     ensureInitialized();
/* 165 */     Set<String> set = new LinkedHashSet();
/* 166 */     for (Iterator<Map.Entry> it = this.crsMap.entrySet().iterator(); it.hasNext(); ) {
/* 167 */       Map.Entry entry = it.next();
/* 168 */       CoordinateReferenceSystem crs = (CoordinateReferenceSystem)entry.getValue();
/* 169 */       if (type.isAssignableFrom(crs.getClass())) {
/* 170 */         Integer code = (Integer)entry.getKey();
/* 171 */         set.add(String.valueOf(code));
/*     */       } 
/*     */     } 
/* 174 */     return set;
/*     */   }
/*     */   
/*     */   public InternationalString getDescriptionText(String code) throws FactoryException {
/* 181 */     return (InternationalString)new SimpleInternationalString(createObject(code).getName().getCode());
/*     */   }
/*     */   
/*     */   public IdentifiedObject createObject(String code) throws FactoryException {
/* 189 */     return (IdentifiedObject)createCoordinateReferenceSystem(code);
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/*     */     int i;
/* 198 */     String c = trimAuthority(code).toUpperCase();
/* 199 */     if (c.startsWith("CRS"))
/* 200 */       c = c.substring("CRS".length()); 
/*     */     try {
/* 204 */       i = Integer.parseInt(c);
/* 205 */     } catch (NumberFormatException exception) {
/* 207 */       NoSuchAuthorityCodeException e = noSuchAuthorityCode(CoordinateReferenceSystem.class, code);
/* 208 */       e.initCause(exception);
/* 209 */       throw e;
/*     */     } 
/* 211 */     ensureInitialized();
/* 212 */     CoordinateReferenceSystem crs = this.crsMap.get(Integer.valueOf(i));
/* 213 */     if (crs != null)
/* 214 */       return crs; 
/* 216 */     throw noSuchAuthorityCode(CoordinateReferenceSystem.class, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\wms\WebCRSFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */