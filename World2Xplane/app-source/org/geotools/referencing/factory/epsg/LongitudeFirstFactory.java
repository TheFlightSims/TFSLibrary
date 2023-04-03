/*     */ package org.geotools.referencing.factory.epsg;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import org.geotools.factory.FactoryNotFoundException;
/*     */ import org.geotools.factory.FactoryRegistryException;
/*     */ import org.geotools.factory.Hints;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.referencing.factory.AbstractAuthorityFactory;
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
/*     */ public class LongitudeFirstFactory extends DeferredAuthorityFactory implements CRSAuthorityFactory, CSAuthorityFactory, CoordinateOperationAuthorityFactory, DatumAuthorityFactory {
/*     */   public static final String SYSTEM_DEFAULT_KEY = "org.geotools.referencing.forceXY";
/*     */   
/*     */   public LongitudeFirstFactory() {
/* 105 */     this(null);
/*     */   }
/*     */   
/*     */   public LongitudeFirstFactory(Hints userHints) {
/* 114 */     super(userHints, 90 + relativePriority());
/* 116 */     this.hints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
/* 117 */     put(userHints, Hints.FORCE_STANDARD_AXIS_DIRECTIONS);
/* 118 */     put(userHints, Hints.FORCE_STANDARD_AXIS_UNITS);
/*     */   }
/*     */   
/*     */   private void put(Hints userHints, Hints.Key key) {
/* 125 */     Object value = null;
/* 126 */     if (userHints != null)
/* 127 */       value = userHints.get(key); 
/* 129 */     if (value == null)
/* 130 */       value = Boolean.FALSE; 
/* 132 */     this.hints.put(key, value);
/*     */   }
/*     */   
/*     */   private static int relativePriority() {
/*     */     try {
/* 144 */       if (Boolean.getBoolean("org.geotools.referencing.forceXY"))
/* 145 */         return 7; 
/* 147 */     } catch (SecurityException e) {}
/* 150 */     return -7;
/*     */   }
/*     */   
/*     */   public Citation getAuthority() {
/* 159 */     Citation authority = super.getAuthority();
/* 160 */     return (authority != null) ? authority : Citations.EPSG;
/*     */   }
/*     */   
/*     */   protected AbstractAuthorityFactory createBackingStore() throws FactoryException {
/*     */     AbstractAuthorityFactory factory;
/* 185 */     Hints backingStoreHints = new Hints((RenderingHints.Key)Hints.CRS_AUTHORITY_FACTORY, ThreadedEpsgFactory.class);
/* 186 */     backingStoreHints.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.FALSE);
/* 187 */     backingStoreHints.put(Hints.FORCE_STANDARD_AXIS_DIRECTIONS, Boolean.FALSE);
/* 188 */     backingStoreHints.put(Hints.FORCE_STANDARD_AXIS_UNITS, Boolean.FALSE);
/*     */     try {
/* 191 */       factory = (AbstractAuthorityFactory)ReferencingFactoryFinder.getCRSAuthorityFactory("EPSG", backingStoreHints);
/* 193 */     } catch (FactoryNotFoundException exception) {
/* 194 */       throw new FactoryNotFoundException(exception);
/* 195 */     } catch (FactoryRegistryException exception) {
/* 196 */       throw new FactoryException(exception);
/*     */     } 
/* 198 */     return (AbstractAuthorityFactory)new OrderedAxisAuthorityFactory(factory, new Hints(this.hints), null);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\factory\epsg\LongitudeFirstFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */