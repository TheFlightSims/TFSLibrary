/*     */ package org.geotools.referencing.operation.projection;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import org.geotools.metadata.iso.citation.Citations;
/*     */ import org.geotools.referencing.NamedIdentifier;
/*     */ import org.geotools.resources.i18n.Errors;
/*     */ import org.opengis.parameter.GeneralParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptor;
/*     */ import org.opengis.parameter.ParameterDescriptorGroup;
/*     */ import org.opengis.parameter.ParameterNotFoundException;
/*     */ import org.opengis.parameter.ParameterValueGroup;
/*     */ import org.opengis.referencing.FactoryException;
/*     */ import org.opengis.referencing.ReferenceIdentifier;
/*     */ import org.opengis.referencing.operation.CylindricalProjection;
/*     */ import org.opengis.referencing.operation.MathTransform;
/*     */ 
/*     */ public class PlateCarree extends EquidistantCylindrical {
/*     */   private static final long serialVersionUID = -6041146276958636165L;
/*     */   
/*     */   protected PlateCarree(ParameterValueGroup parameters) throws ParameterNotFoundException {
/*  61 */     super(parameters);
/*     */   }
/*     */   
/*     */   public ParameterDescriptorGroup getParameterDescriptors() {
/*  69 */     return Provider.PARAMETERS;
/*     */   }
/*     */   
/*     */   protected Point2D transformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/*  81 */     if (ptDst != null) {
/*  82 */       ptDst.setLocation(x, y);
/*  83 */       return ptDst;
/*     */     } 
/*  85 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   protected Point2D inverseTransformNormalized(double x, double y, Point2D ptDst) throws ProjectionException {
/*  96 */     if (ptDst != null) {
/*  97 */       ptDst.setLocation(x, y);
/*  98 */       return ptDst;
/*     */     } 
/* 100 */     return new Point2D.Double(x, y);
/*     */   }
/*     */   
/*     */   public static class Provider extends MapProjection.AbstractProvider {
/*     */     private static final long serialVersionUID = 8535645757318203345L;
/*     */     
/* 134 */     static final ParameterDescriptorGroup PARAMETERS = createDescriptorGroup((ReferenceIdentifier[])new NamedIdentifier[] { new NamedIdentifier(Citations.ESRI, "Plate_Carree"), new NamedIdentifier(Citations.OGC, "Equirectangular"), new NamedIdentifier(Citations.GEOTIFF, "CT_Equirectangular") }(GeneralParameterDescriptor[])new ParameterDescriptor[] { SEMI_MAJOR, SEMI_MINOR, CENTRAL_MERIDIAN, FALSE_EASTING, FALSE_NORTHING });
/*     */     
/*     */     public Provider() {
/* 148 */       super(PARAMETERS);
/*     */     }
/*     */     
/*     */     public Class<CylindricalProjection> getOperationType() {
/* 156 */       return CylindricalProjection.class;
/*     */     }
/*     */     
/*     */     protected MathTransform createMathTransform(ParameterValueGroup parameters) throws ParameterNotFoundException, FactoryException {
/* 169 */       if (isSpherical(parameters))
/* 170 */         return (MathTransform)new PlateCarree(parameters); 
/* 172 */       throw new FactoryException(Errors.format(45));
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\org\geotools\referencing\operation\projection\PlateCarree.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */