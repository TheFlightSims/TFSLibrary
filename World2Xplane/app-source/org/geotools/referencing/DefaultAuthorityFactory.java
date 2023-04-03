/*     */ package org.geotools.referencing;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.GeoTools;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.crs.DefaultGeographicCRS;
/*     */ import org.geotools.referencing.factory.AbstractAuthorityFactory;
/*     */ import org.geotools.referencing.factory.ManyAuthoritiesFactory;
/*     */ import org.geotools.referencing.factory.ThreadedAuthorityFactory;
/*     */ import org.geotools.resources.UnmodifiableArrayList;
/*     */ import org.opengis.metadata.Identifier;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ 
/*     */ final class DefaultAuthorityFactory extends ThreadedAuthorityFactory implements CRSAuthorityFactory {
/*  61 */   private static List<String> AUTHORITY_LESS = (List<String>)UnmodifiableArrayList.wrap((Object[])new String[] { "WGS84(DD)" });
/*     */   
/*     */   DefaultAuthorityFactory(boolean longitudeFirst) {
/*  69 */     super(getBackingFactory(longitudeFirst));
/*     */   }
/*     */   
/*     */   private static AbstractAuthorityFactory getBackingFactory(boolean longitudeFirst) {
/*  77 */     Hints hints = GeoTools.getDefaultHints();
/*  78 */     if (longitudeFirst)
/*  79 */       hints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE); 
/*  88 */     Collection<CRSAuthorityFactory> factories = ReferencingFactoryFinder.getCRSAuthorityFactories(hints);
/*  90 */     if (Boolean.TRUE.equals(hints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.FALSE))) {
/*  96 */       factories = new ArrayList<CRSAuthorityFactory>(factories);
/*  97 */       Set<Citation> authorities = new LinkedHashSet<Citation>();
/*  98 */       for (CRSAuthorityFactory factory : factories)
/*  99 */         authorities.add(factory.getAuthority()); 
/* 102 */       label27: for (CRSAuthorityFactory factory : ReferencingFactoryFinder.getCRSAuthorityFactories(hints)) {
/* 104 */         Citation authority = factory.getAuthority();
/* 105 */         if (authorities.contains(authority))
/*     */           continue; 
/* 108 */         for (Citation check : authorities) {
/* 109 */           if (Citations.identifierMatches(authority, check))
/*     */             continue label27; 
/*     */         } 
/* 113 */         factories.add(factory);
/*     */       } 
/*     */     } 
/* 116 */     return (AbstractAuthorityFactory)new ManyAuthoritiesFactory(factories);
/*     */   }
/*     */   
/*     */   static Set<String> getSupportedCodes(String authority) {
/* 124 */     Set<String> result = new LinkedHashSet<String>(AUTHORITY_LESS);
/* 125 */     for (CRSAuthorityFactory factory : ReferencingFactoryFinder.getCRSAuthorityFactories(null)) {
/* 126 */       if (Citations.identifierMatches(factory.getAuthority(), authority)) {
/*     */         Set<String> codes;
/*     */         try {
/* 129 */           codes = factory.getAuthorityCodes(CoordinateReferenceSystem.class);
/* 130 */         } catch (Exception exception) {
/* 137 */           CRS.unexpectedException("getSupportedCodes", exception);
/*     */           continue;
/*     */         } 
/* 140 */         if (codes != null)
/* 141 */           result.addAll(codes); 
/*     */       } 
/*     */     } 
/* 145 */     return result;
/*     */   }
/*     */   
/*     */   static Set<String> getSupportedAuthorities(boolean returnAliases) {
/* 153 */     Set<String> result = new LinkedHashSet<String>();
/* 154 */     for (CRSAuthorityFactory factory : ReferencingFactoryFinder.getCRSAuthorityFactories(null)) {
/* 155 */       for (Identifier id : factory.getAuthority().getIdentifiers()) {
/* 156 */         result.add(id.getCode());
/* 157 */         if (!returnAliases)
/*     */           break; 
/*     */       } 
/*     */     } 
/* 162 */     return result;
/*     */   }
/*     */   
/*     */   public CoordinateReferenceSystem createCoordinateReferenceSystem(String code) throws FactoryException {
/* 172 */     if (code != null) {
/* 173 */       code = code.trim();
/* 174 */       if (code.equalsIgnoreCase("WGS84(DD)"))
/* 175 */         return (CoordinateReferenceSystem)DefaultGeographicCRS.WGS84; 
/*     */     } 
/* 178 */     assert !AUTHORITY_LESS.contains(code) : code;
/* 179 */     return super.createCoordinateReferenceSystem(code);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\DefaultAuthorityFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */