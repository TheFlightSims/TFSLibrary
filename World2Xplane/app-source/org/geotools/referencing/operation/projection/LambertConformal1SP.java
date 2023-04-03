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
/*     */ public class LambertConformal1SP extends LambertConformal {
/*     */   private static final long serialVersionUID = 149783452790829983L;
/*     */   
/*     */   protected LambertConformal1SP(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  59 */     super(parameters);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/*  66 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = -4243116402872545772L;
/*     */     
/* 101 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Lambert_Conformal_Conic_1SP"), new NamedIdentifier(Citations.EPSG, "Lambert Conic Conformal (1SP)"), new NamedIdentifier(Citations.EPSG, "9801"), new NamedIdentifier(Citations.GEOTIFF, "CT_LambertConfConic_1SP"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(119)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 119 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<ConicProjection> getOperationType() {
/* 127 */       return ConicProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 140 */       return (MathTransform)new LambertConformal1SP(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\LambertConformal1SP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */