/*     */ package org.geotools.referencing.factory.epsg;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import org.geotools.factory.FactoryNotFoundException;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.factory.AbstractAuthorityFactory;
/*     */ import org.geotools.referencing.factory.AbstractEpsgMediator;
/*     */ import org.geotools.referencing.factory.DeferredAuthorityFactory;
/*     */ import org.geotools.referencing.factory.FactoryNotFoundException;
/*     */ import org.geotools.referencing.factory.OrderedAxisAuthorityFactory;
/*     */ import org.opengis.metadata.citation.Citation;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ 
/*     */ public class LongitudeFirstEpsgDecorator extends DeferredAuthorityFactory implements CRSAuthorityFactory, CSAuthorityFactory, CoordinateOperationAuthorityFactory, DatumAuthorityFactory {
/*     */   public LongitudeFirstEpsgDecorator() {
/*  85 */     this(null);
/*     */   }
/*     */   
/*     */   public LongitudeFirstEpsgDecorator(Hints userHints) {
/*  94 */     super(userHints, 40 + relativePriority(userHints));
/*  97 */     this.hints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
/*  98 */     put(userHints, Hints.FORCE_STANDARD_AXIS_DIRECTIONS);
/*  99 */     put(userHints, Hints.FORCE_STANDARD_AXIS_UNITS);
/*     */   }
/*     */   
/*     */   private void put(Hints userHints, Hints.Key key) {
/* 106 */     Object value = null;
/* 107 */     if (userHints != null)
/* 108 */       value = userHints.get(key); 
/* 110 */     if (value == null)
/* 111 */       value = Boolean.FALSE; 
/* 113 */     this.hints.put(key, value);
/*     */   }
/*     */   
/*     */   private static int relativePriority(Hints userHints) {
/*     */     try {
/* 125 */       if (Boolean.getBoolean("org.geotools.referencing.forceXY"))
/* 126 */         return 10; 
/* 134 */     } catch (SecurityException e) {}
/* 137 */     return -10;
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 147 */     return Citations.EPSG;
/*     */   }
/*     */   
/*     */   protected AbstractAuthorityFactory createBackingStore() throws FactoryException {
/*     */     AbstractAuthorityFactory factory;
/* 172 */     Hints backingStoreHints = new Hints((RenderingHints.Key)Hints.CRS_AUTHORITY_FACTORY, AbstractEpsgMediator.class);
/* 173 */     backingStoreHints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.FALSE);
/* 174 */     backingStoreHints.put(Hints.FORCE_STANDARD_AXIS_DIRECTIONS, Boolean.FALSE);
/* 175 */     backingStoreHints.put(Hints.FORCE_STANDARD_AXIS_UNITS, Boolean.FALSE);
/*     */     try {
/* 178 */       factory = (AbstractAuthorityFactory)ReferencingFactoryFinder.getCRSAuthorityFactory("EPSG", backingStoreHints);
/* 180 */     } catch (FactoryNotFoundException exception) {
/* 181 */       throw new FactoryNotFoundException(exception);
/* 182 */     } catch (FactoryRegistryException exception) {
/* 183 */       throw new FactoryException(exception);
/*     */     } 
/* 185 */     return (AbstractAuthorityFactory)new OrderedAxisAuthorityFactory(factory, new Hints(this.hints), null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\LongitudeFirstEpsgDecorator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */