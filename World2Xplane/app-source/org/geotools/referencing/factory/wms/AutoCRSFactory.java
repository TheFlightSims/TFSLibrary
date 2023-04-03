/*     */ package org.geotools.referencing.factory.wms;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.CitationImpl;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.factory.DirectAuthorityFactory;
/*     */ import org.geotools.util.SimpleInternationalString;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.NoSuchAuthorityCodeException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.crs.ProjectedCRS;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public class AutoCRSFactory extends DirectAuthorityFactory implements CRSAuthorityFactory {
/*     */   private static final Citation AUTHORITY;
/*     */   
/*     */   static {
/*  71 */     CitationImpl c = new CitationImpl(Citations.AUTO2);
/*  72 */     c.getIdentifiers().addAll(Citations.AUTO.getIdentifiers());
/*  73 */     AUTHORITY = (Citation)c.unmodifiable();
/*     */   }
/*     */   
/*  81 */   private final Map<Integer, Factlet> factlets = new TreeMap<Integer, Factlet>();
/*     */   
/*     */   public AutoCRSFactory() {
/*  87 */     this((Hints)null);
/*     */   }
/*     */   
/*     */   public AutoCRSFactory(Hints hints) {
/*  94 */     super(hints, 50);
/*  95 */     add(Auto42001.DEFAULT);
/*  96 */     add(Auto42002.DEFAULT);
/*  97 */     add(Auto42003.DEFAULT);
/*  98 */     add(Auto42004.DEFAULT);
/*     */   }
/*     */   
/*     */   private void add(Factlet f) {
/* 107 */     int code = f.code();
/* 108 */     if (this.factlets.put(Integer.valueOf(code), f) != null)
/* 109 */       throw new IllegalArgumentException(String.valueOf(code)); 
/*     */   }
/*     */   
/*     */   private Factlet findFactlet(Code code) throws NoSuchAuthorityCodeException {
/* 121 */     if (code.authority.equalsIgnoreCase("AUTO") || code.authority.equalsIgnoreCase("AUTO2")) {
/* 124 */       Integer key = Integer.valueOf(code.code);
/* 125 */       Factlet fac = this.factlets.get(key);
/* 126 */       if (fac != null)
/* 127 */         return fac; 
/*     */     } 
/* 130 */     throw noSuchAuthorityCode(code.type, code.toString());
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 137 */     return AUTHORITY;
/*     */   }
/*     */   
/*     */   public Set getAuthorityCodes(Class type) throws FactoryException {
/* 153 */     if (type.isAssignableFrom(ProjectedCRS.class)) {
/* 154 */       Set<String> set = new LinkedHashSet();
/* 155 */       for (Iterator<Integer> it = this.factlets.keySet().iterator(); it.hasNext(); ) {
/* 156 */         Integer code = it.next();
/* 157 */         set.add(String.valueOf(code));
/*     */       } 
/* 159 */       return set;
/*     */     } 
/* 161 */     return Collections.EMPTY_SET;
/*     */   }
/*     */   
/*     */   public InternationalString getDescriptionText(String code) throws FactoryException {
/* 169 */     Code c = new Code(code, ProjectedCRS.class);
/* 170 */     return (InternationalString)new SimpleInternationalString(findFactlet(c).getName());
/*     */   }
/*     */   
/*     */   public IdentifiedObject createObject(String code) throws FactoryException {
/* 178 */     return (IdentifiedObject)createCoordinateReferenceSystem(code);
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/* 188 */     return (CoordinateReferenceSystem)createProjectedCRS(code);
/*     */   }
/*     */   
/*     */   public ProjectedCRS createProjectedCRS(String code) throws FactoryException {
/* 195 */     Code c = new Code(code, ProjectedCRS.class);
/* 196 */     return findFactlet(c).create(c, this.factories);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\wms\AutoCRSFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */