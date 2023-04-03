/*     */ package org.geotools.referencing.factory;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.opengis.referencing.AuthorityFactory;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.IdentifiedObject;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ 
/*     */ public class AllAuthoritiesFactory extends ManyAuthoritiesFactory {
/*  60 */   public static AllAuthoritiesFactory DEFAULT = new AllAuthoritiesFactory(null);
/*     */   
/*     */   private Collection<String> authorityNames;
/*     */   
/*     */   public AllAuthoritiesFactory(Hints hints) {
/*  73 */     super((Collection<? extends AuthorityFactory>)null);
/*  74 */     addImplementationHints((RenderingHints)hints);
/*     */   }
/*     */   
/*     */   public Set<String> getAuthorityNames() {
/*  85 */     return ReferencingFactoryFinder.getAuthorityNames();
/*     */   }
/*     */   
/*     */   Collection<AuthorityFactory> getFactories() {
/*  95 */     Collection<String> authorities = ReferencingFactoryFinder.getAuthorityNames();
/*  96 */     if (authorities != this.authorityNames) {
/*  97 */       this.authorityNames = authorities;
/*  98 */       Hints hints = getHints();
/*  99 */       Set<AuthorityFactory> factories = new LinkedHashSet<AuthorityFactory>();
/* 100 */       factories.addAll(ReferencingFactoryFinder.getCRSAuthorityFactories(hints));
/* 101 */       factories.addAll(ReferencingFactoryFinder.getCSAuthorityFactories(hints));
/* 102 */       factories.addAll(ReferencingFactoryFinder.getDatumAuthorityFactories(hints));
/* 103 */       factories.addAll(ReferencingFactoryFinder.getCoordinateOperationAuthorityFactories(hints));
/* 104 */       setFactories(factories);
/*     */     } 
/* 106 */     return super.getFactories();
/*     */   }
/*     */   
/*     */   final <T extends AuthorityFactory> T fromFactoryRegistry(String authority, Class<T> type) throws FactoryRegistryException {
/*     */     AuthorityFactory f;
/* 117 */     if (CRSAuthorityFactory.class.equals(type)) {
/* 118 */       CRSAuthorityFactory cRSAuthorityFactory = ReferencingFactoryFinder.getCRSAuthorityFactory(authority, getHints());
/* 119 */     } else if (CSAuthorityFactory.class.equals(type)) {
/* 120 */       CSAuthorityFactory cSAuthorityFactory = ReferencingFactoryFinder.getCSAuthorityFactory(authority, getHints());
/* 121 */     } else if (DatumAuthorityFactory.class.equals(type)) {
/* 122 */       DatumAuthorityFactory datumAuthorityFactory = ReferencingFactoryFinder.getDatumAuthorityFactory(authority, getHints());
/* 123 */     } else if (CoordinateOperationAuthorityFactory.class.equals(type)) {
/* 124 */       CoordinateOperationAuthorityFactory coordinateOperationAuthorityFactory = ReferencingFactoryFinder.getCoordinateOperationAuthorityFactory(authority, getHints());
/*     */     } else {
/* 126 */       f = super.fromFactoryRegistry(authority, type);
/*     */     } 
/* 128 */     return type.cast(f);
/*     */   }
/*     */   
/*     */   private Hints getHints() {
/* 135 */     if (this.hints.isEmpty())
/* 136 */       return ReferencingFactoryFinder.EMPTY_HINTS; 
/* 139 */     Hints hints = ReferencingFactoryFinder.EMPTY_HINTS.clone();
/* 140 */     hints.putAll(this.hints);
/* 141 */     return hints;
/*     */   }
/*     */   
/*     */   public IdentifiedObjectFinder getIdentifiedObjectFinder(Class<? extends IdentifiedObject> type) throws FactoryException {
/* 155 */     return new Finder(this, type);
/*     */   }
/*     */   
/*     */   private static final class Finder extends ManyAuthoritiesFactory.Finder {
/*     */     protected Finder(ManyAuthoritiesFactory factory, Class<? extends IdentifiedObject> type) {
/* 168 */       super(factory, type);
/*     */     }
/*     */     
/*     */     private Set<AuthorityFactory> fromFactoryRegistry() {
/* 175 */       ManyAuthoritiesFactory factory = (ManyAuthoritiesFactory)getProxy().getAuthorityFactory();
/* 176 */       Class<? extends AuthorityFactory> type = getProxy().getType();
/* 177 */       Set<AuthorityFactory> factories = new LinkedHashSet<AuthorityFactory>();
/* 178 */       for (String authority : ReferencingFactoryFinder.getAuthorityNames())
/* 179 */         factory.fromFactoryRegistry(authority, type, factories); 
/* 182 */       Collection<AuthorityFactory> done = getFactories();
/* 183 */       if (done != null)
/* 184 */         factories.removeAll(done); 
/* 186 */       return factories;
/*     */     }
/*     */     
/*     */     public IdentifiedObject find(IdentifiedObject object) throws FactoryException {
/* 194 */       IdentifiedObject candidate = super.find(object);
/* 195 */       if (candidate != null)
/* 196 */         return candidate; 
/* 199 */       Iterator<AuthorityFactory> it = fromFactoryRegistry().iterator();
/*     */       IdentifiedObjectFinder finder;
/* 200 */       while ((finder = next(it)) != null) {
/* 201 */         candidate = finder.find(object);
/* 202 */         if (candidate != null)
/*     */           break; 
/*     */       } 
/* 206 */       return candidate;
/*     */     }
/*     */     
/*     */     public String findIdentifier(IdentifiedObject object) throws FactoryException {
/* 214 */       String candidate = super.findIdentifier(object);
/* 215 */       if (candidate != null)
/* 216 */         return candidate; 
/* 219 */       Iterator<AuthorityFactory> it = fromFactoryRegistry().iterator();
/*     */       IdentifiedObjectFinder finder;
/* 220 */       while ((finder = next(it)) != null) {
/* 221 */         candidate = finder.findIdentifier(object);
/* 222 */         if (candidate != null)
/*     */           break; 
/*     */       } 
/* 226 */       return candidate;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\AllAuthoritiesFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */