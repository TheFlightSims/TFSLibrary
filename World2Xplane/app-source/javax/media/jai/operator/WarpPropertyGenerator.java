/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.util.PropertyGeneratorImpl;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.GeometricOpImage;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.ROIShape;
/*     */ import javax.media.jai.RenderedOp;
/*     */ import javax.media.jai.Warp;
/*     */ 
/*     */ class WarpPropertyGenerator extends PropertyGeneratorImpl {
/*     */   public WarpPropertyGenerator() {
/*  44 */     super(new String[] { "ROI" }, new Class[] { ROI.class }, new Class[] { RenderedOp.class });
/*     */   }
/*     */   
/*     */   public Object getProperty(String name, Object opNode) {
/*  57 */     validate(name, opNode);
/*  59 */     if (opNode instanceof RenderedOp && name.equalsIgnoreCase("roi")) {
/*  61 */       RenderedOp op = (RenderedOp)opNode;
/*  63 */       ParameterBlock pb = op.getParameterBlock();
/*  66 */       RenderedImage src = pb.getRenderedSource(0);
/*  67 */       Object property = src.getProperty("ROI");
/*  68 */       if (property == null || property.equals(Image.UndefinedProperty) || !(property instanceof ROI))
/*  71 */         return Image.UndefinedProperty; 
/*  75 */       ROI srcROI = (ROI)property;
/*  76 */       if (srcROI.getBounds().isEmpty())
/*  77 */         return Image.UndefinedProperty; 
/*  81 */       Interpolation interp = (Interpolation)pb.getObjectParameter(1);
/*  84 */       Rectangle srcBounds = null;
/*  85 */       PlanarImage dst = op.getRendering();
/*  86 */       if (dst instanceof GeometricOpImage && ((GeometricOpImage)dst).getBorderExtender() == null) {
/*  88 */         srcBounds = new Rectangle(src.getMinX() + interp.getLeftPadding(), src.getMinY() + interp.getTopPadding(), src.getWidth() - interp.getWidth() + 1, src.getHeight() - interp.getHeight() + 1);
/*     */       } else {
/*  94 */         srcBounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/*     */       } 
/* 101 */       if (!srcBounds.contains(srcROI.getBounds()))
/* 102 */         srcROI = srcROI.intersect((ROI)new ROIShape(srcBounds)); 
/* 106 */       Interpolation interpNN = (interp instanceof javax.media.jai.InterpolationNearest) ? interp : Interpolation.getInstance(0);
/* 111 */       Warp warp = (Warp)pb.getObjectParameter(0);
/* 114 */       ROI dstROI = new ROI((RenderedImage)JAI.create("warp", (RenderedImage)srcROI.getAsImage(), warp, interpNN));
/* 118 */       Rectangle dstBounds = op.getBounds();
/* 121 */       if (!dstBounds.contains(dstROI.getBounds()))
/* 122 */         dstROI = dstROI.intersect((ROI)new ROIShape(dstBounds)); 
/* 126 */       return dstROI;
/*     */     } 
/* 129 */     return Image.UndefinedProperty;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\WarpPropertyGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */