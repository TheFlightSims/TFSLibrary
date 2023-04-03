/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.GeometricOpImage;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.ROIShape;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ class SubsampleAveragePropertyGenerator implements PropertyGenerator {
/*     */   public String[] getPropertyNames() {
/*  52 */     String[] properties = new String[1];
/*  53 */     properties[0] = "ROI";
/*  54 */     return properties;
/*     */   }
/*     */   
/*     */   public Class getClass(String propertyName) {
/*  62 */     if (propertyName == null)
/*  63 */       throw new IllegalArgumentException(JaiI18N.getString("SubsampleAveragePropertyGenerator0")); 
/*  65 */     if (propertyName.equalsIgnoreCase("roi"))
/*  66 */       return ROI.class; 
/*  69 */     return null;
/*     */   }
/*     */   
/*     */   public boolean canGenerateProperties(Object opNode) {
/*  76 */     if (opNode == null)
/*  77 */       throw new IllegalArgumentException(JaiI18N.getString("SubsampleAveragePropertyGenerator1")); 
/*  81 */     return opNode instanceof RenderedOp;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name, Object opNode) {
/*  93 */     if (name == null || opNode == null)
/*  94 */       throw new IllegalArgumentException(JaiI18N.getString("SubsampleAveragePropertyGenerator2")); 
/*  96 */     if (!canGenerateProperties(opNode))
/*  97 */       throw new IllegalArgumentException(opNode.getClass().getName() + JaiI18N.getString("SubsampleAveragePropertyGenerator3")); 
/* 102 */     return (opNode instanceof RenderedOp) ? getProperty(name, (RenderedOp)opNode) : null;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name, RenderedOp op) {
/* 114 */     if (name == null || op == null)
/* 115 */       throw new IllegalArgumentException(JaiI18N.getString("SubsampleAveragePropertyGenerator4")); 
/* 119 */     if (name.equals("roi")) {
/* 120 */       ParameterBlock pb = op.getParameterBlock();
/* 123 */       PlanarImage src = (PlanarImage)pb.getRenderedSource(0);
/* 124 */       Object property = src.getProperty("ROI");
/* 125 */       if (property == null || property.equals(Image.UndefinedProperty) || !(property instanceof ROI))
/* 128 */         return null; 
/* 130 */       ROI srcROI = (ROI)property;
/* 133 */       Rectangle srcBounds = null;
/* 134 */       PlanarImage dst = op.getRendering();
/* 135 */       if (dst instanceof GeometricOpImage && ((GeometricOpImage)dst).getBorderExtender() == null) {
/* 137 */         GeometricOpImage geomIm = (GeometricOpImage)dst;
/* 138 */         Interpolation interp = geomIm.getInterpolation();
/* 139 */         srcBounds = new Rectangle(src.getMinX() + interp.getLeftPadding(), src.getMinY() + interp.getTopPadding(), src.getWidth() - interp.getWidth() + 1, src.getHeight() - interp.getHeight() + 1);
/*     */       } else {
/* 145 */         srcBounds = src.getBounds();
/*     */       } 
/* 149 */       if (!srcBounds.contains(srcROI.getBounds()))
/* 150 */         srcROI = srcROI.intersect((ROI)new ROIShape(srcBounds)); 
/* 154 */       double sx = pb.getDoubleParameter(0);
/* 155 */       double sy = pb.getDoubleParameter(1);
/* 158 */       AffineTransform transform = new AffineTransform(sx, 0.0D, 0.0D, sy, 0.0D, 0.0D);
/* 162 */       ROI dstROI = srcROI.transform(transform);
/* 165 */       Rectangle dstBounds = op.getBounds();
/* 168 */       if (!dstBounds.contains(dstROI.getBounds()))
/* 169 */         dstROI = dstROI.intersect((ROI)new ROIShape(dstBounds)); 
/* 173 */       return dstROI;
/*     */     } 
/* 175 */     return null;
/*     */   }
/*     */   
/*     */   public Object getProperty(String name, RenderableOp op) {
/* 187 */     if (name == null || op == null)
/* 188 */       throw new IllegalArgumentException(JaiI18N.getString("SubsampleAveragePropertyGenerator2")); 
/* 192 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\SubsampleAveragePropertyGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */