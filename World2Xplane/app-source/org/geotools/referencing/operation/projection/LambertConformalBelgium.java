/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.ConicProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class LambertConformalBelgium extends LambertConformal {
/*     */   private static final long serialVersionUID = -3441696724046319189L;
/*     */   
/*     */   protected LambertConformalBelgium(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  59 */     super(parameters, true);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/*  66 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public static final class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = -6388030784088639876L;
/*     */     
/* 100 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Lambert_Conformal_Conic_2SP_Belgium"), new NamedIdentifier(Citations.EPSG, "Lambert Conic Conformal (2SP Belgium)"), new NamedIdentifier(Citations.EPSG, "9803"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(119)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, STANDARD_PARALLEL_1, STANDARD_PARALLEL_2, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 117 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<ConicProjection> getOperationType() {
/* 125 */       return ConicProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 138 */       return (MathTransform)new LambertConformalBelgium(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\LambertConformalBelgium.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */