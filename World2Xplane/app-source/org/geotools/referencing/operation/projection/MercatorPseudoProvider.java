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
/*     */ import org.opengis.referencing.operation.CylindricalProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class MercatorPseudoProvider extends MapProjection.AbstractProvider {
/*     */   private static final long serialVersionUID = 118002069939741891L;
/*     */   
/*  50 */   static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.EPSG, "Popular Visualisation Pseudo Mercator"), new NamedIdentifier(Citations.EPSG, "1024"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(34)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, LATITUDE_OF_ORIGIN, CENTRAL_MERIDIAN, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */   
/*     */   public MercatorPseudoProvider() {
/*  65 */     super(PARAMETERS);
/*     */   }
/*     */   
/*     */   public Class<CylindricalProjection> getOperationType() {
/*  73 */     return CylindricalProjection.class;
/*     */   }
/*     */   
/*     */   protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  87 */     parameters.parameter("semi_minor").setValue(parameters.parameter("semi_major").getValue());
/*  88 */     return (MathTransform)new Spherical(parameters);
/*     */   }
/*     */   
/*     */   private static final class Spherical extends Mercator.Spherical {
/*     */     private static final long serialVersionUID = -7583892502939355783L;
/*     */     
/*     */     protected Spherical(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 110 */       super(parameters);
/*     */     }
/*     */     
/*     */     public ParameterDescriptorGroup getParameterDescriptors() {
/* 117 */       return MercatorPseudoProvider.PARAMETERS;
/*     */     }
/*     */     
/*     */     protected double getToleranceForAssertions(double longitude, double latitude) {
/* 123 */       double delta = Math.abs(longitude - this.centralMeridian) / 2.0D + Math.abs(latitude - this.latitudeOfOrigin);
/* 125 */       if (delta > 40.0D)
/* 127 */         return 1.0D; 
/* 130 */       return 0.1D;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\MercatorPseudoProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */