/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Vocabulary;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ import org.opengis.referencing.operation.PlanarProjection;
/*     */ 
/*     */ public abstract class Orthographic extends MapProjection {
/*     */   private static final long serialVersionUID = -6489939032996419868L;
/*     */   
/*     */   private static final double EPSILON = 1.0E-6D;
/*     */   
/*     */   protected Orthographic(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  95 */     super(parameters);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/* 102 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 110 */     if (object == this)
/* 112 */       return true; 
/* 115 */     return super.equals(object);
/*     */   }
/*     */   
/*     */   public static final class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 3180410512573499562L;
/*     */     
/* 148 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Orthographic"), new NamedIdentifier(Citations.GEOTIFF, "CT_Orthographic"), new NamedIdentifier(Citations.ESRI, "Orthographic"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(160)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, LATITUDE_OF_ORIGIN, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 165 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<PlanarProjection> getOperationType() {
/* 173 */       return PlanarProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException, FactoryException {
/* 187 */       double latitudeOfOrigin = Math.abs(MapProjection.AbstractProvider.doubleValue(LATITUDE_OF_ORIGIN, parameters));
/* 188 */       if (!isSpherical(parameters))
/* 189 */         MapProjection.LOGGER.log(Level.FINE, "GeoTools Orthographic is defined only on the sphere, we're going to use spherical equations even if the projection is using an ellipsoid"); 
/* 193 */       if (Math.abs(latitudeOfOrigin - 1.5707963267948966D) < 1.0E-6D)
/* 194 */         return (MathTransform)new PolarOrthographic(parameters); 
/* 197 */       if (latitudeOfOrigin < 1.0E-6D)
/* 198 */         return (MathTransform)new EquatorialOrthographic(parameters); 
/* 202 */       return (MathTransform)new ObliqueOrthographic(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\Orthographic.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */