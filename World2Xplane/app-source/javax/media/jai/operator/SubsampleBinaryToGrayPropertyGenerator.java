/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.util.PropertyGeneratorImpl;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.ROIShape;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ class SubsampleBinaryToGrayPropertyGenerator extends PropertyGeneratorImpl {
/*     */   public SubsampleBinaryToGrayPropertyGenerator() {
/*  41 */     super(new String[] { "ROI" }, new Class[] { ROI.class }, new Class[] { RenderedOp.class });
/*     */   }
/*     */   
/*     */   public Object getProperty(String name, Object opNode) {
/*  54 */     validate(name, opNode);
/*  56 */     if (opNode instanceof RenderedOp && name.equalsIgnoreCase("roi")) {
/*  58 */       RenderedOp op = (RenderedOp)opNode;
/*  60 */       ParameterBlock pb = op.getParameterBlock();
/*  63 */       RenderedImage src = pb.getRenderedSource(0);
/*  65 */       Object property = src.getProperty("ROI");
/*  66 */       if (property == null || property.equals(Image.UndefinedProperty) || !(property instanceof ROI))
/*  69 */         return Image.UndefinedProperty; 
/*  71 */       ROI srcROI = (ROI)property;
/*  74 */       Rectangle srcBounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/*  80 */       if (!srcBounds.contains(srcROI.getBounds()))
/*  81 */         srcROI = srcROI.intersect((ROI)new ROIShape(srcBounds)); 
/*  85 */       float sx = pb.getFloatParameter(0);
/*  86 */       float sy = pb.getFloatParameter(1);
/*  89 */       AffineTransform transform = new AffineTransform(sx, 0.0D, 0.0D, sy, 0.0D, 0.0D);
/*  93 */       ROI dstROI = srcROI.transform(transform);
/*  96 */       Rectangle dstBounds = op.getBounds();
/*  99 */       if (!dstBounds.contains(dstROI.getBounds()))
/* 100 */         dstROI = dstROI.intersect((ROI)new ROIShape(dstBounds)); 
/* 104 */       return dstROI;
/*     */     } 
/* 107 */     return Image.UndefinedProperty;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\SubsampleBinaryToGrayPropertyGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */