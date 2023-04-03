/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.util.PropertyGeneratorImpl;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.GeometricOpImage;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.ROIShape;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ class AffinePropertyGenerator extends PropertyGeneratorImpl {
/*     */   public AffinePropertyGenerator() {
/*  43 */     super(new String[] { "ROI" }, new Class[] { ROI.class }, new Class[] { RenderedOp.class });
/*     */   }
/*     */   
/*     */   public Object getProperty(String name, Object opNode) {
/*  56 */     validate(name, opNode);
/*  58 */     if (opNode instanceof RenderedOp && name.equalsIgnoreCase("roi")) {
/*  60 */       RenderedOp op = (RenderedOp)opNode;
/*  62 */       ParameterBlock pb = op.getParameterBlock();
/*  65 */       RenderedImage src = pb.getRenderedSource(0);
/*  66 */       Object property = src.getProperty("ROI");
/*  67 */       if (property == null || property.equals(Image.UndefinedProperty) || !(property instanceof ROI))
/*  70 */         return Image.UndefinedProperty; 
/*  72 */       ROI srcROI = (ROI)property;
/*  75 */       Interpolation interp = (Interpolation)pb.getObjectParameter(1);
/*  78 */       Rectangle srcBounds = null;
/*  79 */       PlanarImage dst = op.getRendering();
/*  80 */       if (dst instanceof GeometricOpImage && ((GeometricOpImage)dst).getBorderExtender() == null) {
/*  82 */         srcBounds = new Rectangle(src.getMinX() + interp.getLeftPadding(), src.getMinY() + interp.getTopPadding(), src.getWidth() - interp.getWidth() + 1, src.getHeight() - interp.getHeight() + 1);
/*     */       } else {
/*  88 */         srcBounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/*     */       } 
/*  95 */       if (!srcBounds.contains(srcROI.getBounds()))
/*  96 */         srcROI = srcROI.intersect((ROI)new ROIShape(srcBounds)); 
/* 100 */       AffineTransform transform = (AffineTransform)pb.getObjectParameter(0);
/* 104 */       ROI dstROI = srcROI.transform(transform);
/* 107 */       Rectangle dstBounds = op.getBounds();
/* 111 */       if (!dstBounds.contains(dstROI.getBounds()))
/* 112 */         dstROI = dstROI.intersect((ROI)new ROIShape(dstBounds)); 
/* 116 */       return dstROI;
/*     */     } 
/* 119 */     return Image.UndefinedProperty;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\AffinePropertyGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */