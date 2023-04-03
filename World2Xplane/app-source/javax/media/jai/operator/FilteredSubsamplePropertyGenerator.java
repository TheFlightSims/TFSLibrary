/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.util.PropertyGeneratorImpl;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.OpImage;
/*     */ import javax.media.jai.PlanarImage;
/*     */ import javax.media.jai.ROI;
/*     */ import javax.media.jai.ROIShape;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ import javax.media.jai.WarpOpImage;
/*     */ 
/*     */ class FilteredSubsamplePropertyGenerator extends PropertyGeneratorImpl {
/*     */   public FilteredSubsamplePropertyGenerator() {
/*  45 */     super(new String[] { "FilteredSubsample" }, new Class[] { boolean.class }, new Class[] { RenderedOp.class, RenderableOp.class });
/*     */   }
/*     */   
/*     */   public Object getProperty(String name, Object opNode) {
/*  58 */     validate(name, opNode);
/*  60 */     if (opNode instanceof RenderedOp && name.equalsIgnoreCase("roi")) {
/*  62 */       RenderedOp op = (RenderedOp)opNode;
/*  64 */       ParameterBlock pb = op.getParameterBlock();
/*  67 */       RenderedImage src = pb.getRenderedSource(0);
/*  68 */       Object property = src.getProperty("ROI");
/*  69 */       if (property == null || property.equals(Image.UndefinedProperty) || !(property instanceof ROI))
/*  72 */         return null; 
/*  74 */       ROI srcROI = (ROI)property;
/*  77 */       Rectangle srcBounds = null;
/*  78 */       PlanarImage dst = op.getRendering();
/*  79 */       if (dst instanceof WarpOpImage && !((OpImage)dst).hasExtender(0)) {
/*  80 */         WarpOpImage warpIm = (WarpOpImage)dst;
/*  81 */         srcBounds = new Rectangle(src.getMinX() + warpIm.getLeftPadding(), src.getMinY() + warpIm.getTopPadding(), src.getWidth() - warpIm.getWidth() + 1, src.getHeight() - warpIm.getHeight() + 1);
/*     */       } else {
/*  87 */         srcBounds = new Rectangle(src.getMinX(), src.getMinY(), src.getWidth(), src.getHeight());
/*     */       } 
/*  94 */       if (!srcBounds.contains(srcROI.getBounds()))
/*  95 */         srcROI = srcROI.intersect((ROI)new ROIShape(srcBounds)); 
/*  99 */       float sx = 1.0F / pb.getIntParameter(1);
/* 100 */       float sy = 1.0F / pb.getIntParameter(2);
/* 103 */       AffineTransform transform = new AffineTransform(sx, 0.0D, 0.0D, sy, 0.0D, 0.0D);
/* 107 */       ROI dstROI = srcROI.transform(transform);
/* 110 */       Rectangle dstBounds = op.getBounds();
/* 113 */       if (!dstBounds.contains(dstROI.getBounds()))
/* 114 */         dstROI = dstROI.intersect((ROI)new ROIShape(dstBounds)); 
/* 118 */       return dstROI;
/*     */     } 
/* 120 */     return null;
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 126 */     String[] properties = new String[1];
/* 127 */     properties[0] = "roi";
/* 128 */     return properties;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\FilteredSubsamplePropertyGenerator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */