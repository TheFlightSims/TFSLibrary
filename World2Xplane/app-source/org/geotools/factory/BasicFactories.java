/*     */ package org.geotools.factory;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.Map;
/*     */ import org.geotools.referencing.ReferencingFactoryFinder;
/*     */ import org.geotools.resources.Classes;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.feature.type.FeatureTypeFactory;
/*     */ import org.opengis.filter.FilterFactory;
/*     */ import org.opengis.geometry.coordinate.GeometryFactory;
/*     */ import org.opengis.geometry.primitive.PrimitiveFactory;
/*     */ import org.opengis.metadata.citation.CitationFactory;
/*     */ import org.opengis.referencing.crs.CRSAuthorityFactory;
/*     */ import org.opengis.referencing.crs.CRSFactory;
/*     */ import org.opengis.referencing.crs.CoordinateReferenceSystem;
/*     */ import org.opengis.referencing.cs.CSAuthorityFactory;
/*     */ import org.opengis.referencing.cs.CSFactory;
/*     */ import org.opengis.referencing.datum.DatumAuthorityFactory;
/*     */ import org.opengis.referencing.datum.DatumFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationAuthorityFactory;
/*     */ import org.opengis.referencing.operation.CoordinateOperationFactory;
/*     */ import org.opengis.util.NameFactory;
/*     */ 
/*     */ public class BasicFactories {
/*     */   private static final String DEFAULT_AUTHORITY = "EPSG";
/*     */   
/*     */   private static BasicFactories DEFAULT;
/*     */   
/*     */   protected final Hints hints;
/*     */   
/*     */   public BasicFactories(Map<? extends RenderingHints.Key, ?> hints) {
/*  84 */     this.hints = (hints != null) ? new Hints(hints) : null;
/*     */   }
/*     */   
/*     */   public static synchronized BasicFactories getDefault() {
/*  91 */     if (DEFAULT == null)
/*  92 */       DEFAULT = new BasicFactories(new Hints(Hints.LENIENT_DATUM_SHIFT, Boolean.TRUE)); 
/*  94 */     return DEFAULT;
/*     */   }
/*     */   
/*     */   private static String unsupportedFactory(Class type) {
/* 104 */     return Errors.format(49, Classes.getShortName(type));
/*     */   }
/*     */   
/*     */   public FeatureTypeFactory getTypeFactory() throws FactoryRegistryException {
/* 119 */     return getFeatureTypeFactory();
/*     */   }
/*     */   
/*     */   public FeatureTypeFactory getFeatureTypeFactory() throws FactoryRegistryException {
/* 135 */     throw new FactoryNotFoundException(unsupportedFactory(FeatureTypeFactory.class));
/*     */   }
/*     */   
/*     */   public FilterFactory getFilterFactory() throws FactoryRegistryException {
/* 148 */     throw new FactoryNotFoundException(unsupportedFactory(FilterFactory.class));
/*     */   }
/*     */   
/*     */   public NameFactory getNameFactory() throws FactoryRegistryException {
/* 161 */     throw new FactoryNotFoundException(unsupportedFactory(NameFactory.class));
/*     */   }
/*     */   
/*     */   public CitationFactory getCitationFactory() throws FactoryRegistryException {
/* 174 */     throw new FactoryNotFoundException(unsupportedFactory(CitationFactory.class));
/*     */   }
/*     */   
/*     */   public CRSAuthorityFactory getCRSAuthorityFactory() throws FactoryRegistryException {
/* 184 */     return ReferencingFactoryFinder.getCRSAuthorityFactory("EPSG", this.hints);
/*     */   }
/*     */   
/*     */   public CRSFactory getCRSFactory() throws FactoryRegistryException {
/* 194 */     return ReferencingFactoryFinder.getCRSFactory(this.hints);
/*     */   }
/*     */   
/*     */   public CSAuthorityFactory getCSAuthorityFactory() throws FactoryRegistryException {
/* 204 */     return ReferencingFactoryFinder.getCSAuthorityFactory("EPSG", this.hints);
/*     */   }
/*     */   
/*     */   public CSFactory getCSFactory() throws FactoryRegistryException {
/* 214 */     return ReferencingFactoryFinder.getCSFactory(this.hints);
/*     */   }
/*     */   
/*     */   public DatumAuthorityFactory getDatumAuthorityFactory() throws FactoryRegistryException {
/* 224 */     return ReferencingFactoryFinder.getDatumAuthorityFactory("EPSG", this.hints);
/*     */   }
/*     */   
/*     */   public DatumFactory getDatumFactory() throws FactoryRegistryException {
/* 234 */     return ReferencingFactoryFinder.getDatumFactory(this.hints);
/*     */   }
/*     */   
/*     */   public CoordinateOperationAuthorityFactory getCoordinateOperationAuthorityFactory() throws FactoryRegistryException {
/* 247 */     return ReferencingFactoryFinder.getCoordinateOperationAuthorityFactory("EPSG", this.hints);
/*     */   }
/*     */   
/*     */   public CoordinateOperationFactory getCoordinateOperationFactory() throws FactoryRegistryException {
/* 259 */     return ReferencingFactoryFinder.getCoordinateOperationFactory(this.hints);
/*     */   }
/*     */   
/*     */   public GeometryFactory getGeometryFactory(CoordinateReferenceSystem crs) throws FactoryRegistryException {
/* 280 */     throw new FactoryNotFoundException(unsupportedFactory(GeometryFactory.class));
/*     */   }
/*     */   
/*     */   public PrimitiveFactory getPrimitiveFactory(CoordinateReferenceSystem crs) throws FactoryRegistryException {
/* 301 */     throw new FactoryNotFoundException(unsupportedFactory(PrimitiveFactory.class));
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\factory\BasicFactories.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */