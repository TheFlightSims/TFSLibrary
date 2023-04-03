/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.geotools.util.Utilities;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.PlanarProjection;
/*     */ import org.opengis.util.InternationalString;
/*     */ 
/*     */ public abstract class Stereographic extends MapProjection {
/*     */   private static final long serialVersionUID = -176731870235252852L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   private final ParameterDescriptorGroup descriptor;
/*     */   
/*     */   Stereographic(ParameterValueGroup parameters, ParameterDescriptorGroup descriptor) throws ParameterNotFoundException {
/* 165 */     super(parameters, descriptor.descriptors());
/* 166 */     this.descriptor = descriptor;
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 173 */     return this.descriptor;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 187 */     if (object == this)
/* 189 */       return true; 
/* 191 */     if (super.equals(object)) {
/* 192 */       Stereographic that = (Stereographic)object;
/* 193 */       return Utilities.equals(this.descriptor, that.descriptor);
/*     */     } 
/* 195 */     return false;
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 1243300263948365065L;
/*     */     
/* 230 */     static final InternationalString NAME = Vocabulary.formatInternational(208);
/*     */     
/* 236 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Stereographic"), new NamedIdentifier(Citations.GEOTIFF, "CT_Stereographic"), new NamedIdentifier(Citations.GEOTOOLS, NAME) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 251 */       this(PARAMETERS);
/*     */     }
/*     */     
/*     */     protected Provider(ParameterDescriptorGroup parameters) {
/* 262 */       super(parameters);
/*     */     }
/*     */     
/*     */     public Class<PlanarProjection> getOperationType() {
/* 270 */       return PlanarProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 284 */       double latitudeOfOrigin = Math.abs(MapProjection.AbstractProvider.doubleValue(LATITUDE_OF_ORIGIN, parameters));
/* 285 */       boolean isSpherical = isSpherical(parameters);
/* 286 */       ParameterDescriptorGroup descriptor = getParameters();
/* 288 */       if (Math.abs(latitudeOfOrigin - 1.5707963267948966D) < 1.0E-6D) {
/* 289 */         if (isSpherical)
/* 290 */           return (MathTransform)new PolarStereographic.Spherical(parameters, descriptor, null); 
/* 292 */         return (MathTransform)new PolarStereographic(parameters, descriptor, null);
/*     */       } 
/* 296 */       if (latitudeOfOrigin < 1.0E-6D) {
/* 297 */         if (isSpherical)
/* 298 */           return (MathTransform)new EquatorialStereographic.Spherical(parameters, descriptor); 
/* 300 */         return createMathTransform(parameters, descriptor);
/*     */       } 
/* 304 */       if (isSpherical)
/* 305 */         return (MathTransform)new StereographicUSGS.Spherical(parameters, descriptor); 
/* 307 */       return createMathTransform(parameters, descriptor);
/*     */     }
/*     */     
/*     */     MathTransform createMathTransform(ParameterValueGroup parameters, ParameterDescriptorGroup descriptor) throws ParameterNotFoundException {
/* 318 */       return (MathTransform)new StereographicUSGS(parameters, descriptor);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\Stereographic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */