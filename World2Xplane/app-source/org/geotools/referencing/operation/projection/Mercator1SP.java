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
/*     */ public class Mercator1SP extends Mercator {
/*     */   private static final long serialVersionUID = 8391549772210490073L;
/*     */   
/*     */   protected Mercator1SP(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  59 */     super(parameters);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/*  66 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   private static final class Spherical extends Mercator.Spherical {
/*     */     private static final long serialVersionUID = 1347778643385433516L;
/*     */     
/*     */     protected Spherical(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  92 */       super(parameters);
/*     */     }
/*     */     
/*     */     public ParameterDescriptorGroup getParameterDescriptors() {
/*  99 */       return Mercator1SP.Provider.PARAMETERS;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = -5886510621481710072L;
/*     */     
/* 134 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.OGC, "Mercator_1SP"), new NamedIdentifier(Citations.EPSG, "Mercator (1SP)"), new NamedIdentifier(Citations.EPSG, "Mercator (1SP) (Spherical)"), new NamedIdentifier(Citations.EPSG, "Mercator (variant A)"), new NamedIdentifier(Citations.EPSG, "9804"), new NamedIdentifier(Citations.GEOTIFF, "CT_Mercator"), new NamedIdentifier(Citations.GEOTOOLS, Vocabulary.formatInternational(34)) }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, LATITUDE_OF_ORIGIN, CENTRAL_MERIDIAN, SCALE_FACTOR, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 153 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<CylindricalProjection> getOperationType() {
/* 161 */       return CylindricalProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException {
/* 174 */       if (isSpherical(parameters))
/* 175 */         return (MathTransform)new Mercator1SP.Spherical(parameters); 
/* 177 */       return (MathTransform)new Mercator1SP(parameters);
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\Mercator1SP.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */