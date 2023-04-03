/*     */ package org.geotools.referencing.factory.epsg;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.referencing.crs.DefaultEngineeringCRS;
/*     */ import org.geotools.referencing.cs.DefaultCartesianCS;
/*     */ import org.geotools.referencing.datum.DefaultEngineeringDatum;
/*     */ import org.geotools.referencing.factory.DirectAuthorityFactory;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.EngineeringCRS;
/*     */ import org.opengis.referencing.cs.CoordinateSystem;
/*     */ import org.opengis.referencing.datum.EngineeringDatum;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class CartesianAuthorityFactory extends DirectAuthorityFactory implements CRSAuthorityFactory {
/*     */   static final String GENERIC_2D_CODE = "404000";
/*     */   
/*  61 */   public static final DefaultEngineeringCRS GENERIC_2D = new DefaultEngineeringCRS(buildProperties("Wildcard 2D cartesian plane in metric unit", Citations.EPSG, "404000"), (EngineeringDatum)DefaultEngineeringDatum.UNKNOWN, (CoordinateSystem)DefaultCartesianCS.GENERIC_2D, true);
/*     */   
/*     */   static Map<String, ?> buildProperties(String name, Citation authority, String code) {
/*  66 */     Map<String, Object> props = new HashMap<String, Object>();
/*  67 */     props.put("name", name);
/*  68 */     props.put("identifiers", new NamedIdentifier(authority, code));
/*  69 */     return props;
/*     */   }
/*     */   
/*     */   public CartesianAuthorityFactory() {
/*  73 */     this(null);
/*     */   }
/*     */   
/*     */   public CartesianAuthorityFactory(Hints hints) {
/*  77 */     super(hints, 80);
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/*  82 */     return Citations.EPSG;
/*     */   }
/*     */   
/*     */   public Set<String> getAuthorityCodes(Class<? extends IdentifiedObject> type) throws FactoryException {
/*  87 */     if (type.isAssignableFrom(EngineeringCRS.class)) {
/*  88 */       Set<String> set = new LinkedHashSet();
/*  89 */       set.add("404000");
/*  90 */       return set;
/*     */     } 
/*  92 */     return Collections.EMPTY_SET;
/*     */   }
/*     */   
/*     */   public InternationalString getDescriptionText(String code) throws NoSuchAuthorityCodeException, FactoryException {
/*  98 */     if (code.equals("EPSG:404000"))
/*  99 */       return (InternationalString)new SimpleInternationalString("A two-dimensional wildcard coordinate system with X,Y axis in meters"); 
/* 102 */     throw noSuchAuthorityException(code);
/*     */   }
/*     */   
/*     */   public IdentifiedObject createObject(String code) throws FactoryException {
/* 112 */     return (IdentifiedObject)createCoordinateReferenceSystem(code);
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/* 121 */     return (CoordinateReferenceSystem)createEngineeringCRS(code);
/*     */   }
/*     */   
/*     */   public EngineeringCRS createEngineeringCRS(String code) throws NoSuchAuthorityCodeException, FactoryException {
/* 127 */     if ("404000".equals(code) || "EPSG:404000".equals(code))
/* 128 */       return (EngineeringCRS)GENERIC_2D; 
/* 130 */     throw noSuchAuthorityException(code);
/*     */   }
/*     */   
/*     */   private NoSuchAuthorityCodeException noSuchAuthorityException(String code) throws NoSuchAuthorityCodeException {
/* 135 */     String authority = "EPSG";
/* 136 */     return new NoSuchAuthorityCodeException(Errors.format(138, code, authority, EngineeringCRS.class), authority, code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\CartesianAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */