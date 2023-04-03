/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class DCTDescriptor extends OperationDescriptorImpl {
/*  55 */   private static final String[][] resources = new String[][] { { "GlobalName", "DCT" }, { "LocalName", "DCT" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("DCTDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/DCTDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public DCTDescriptor() {
/*  66 */     super(resources, 1, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/*  71 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderingHints hints) {
/*  95 */     ParameterBlockJAI pb = new ParameterBlockJAI("DCT", "rendered");
/*  99 */     pb.setSource("source0", source0);
/* 101 */     return JAI.create("DCT", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderingHints hints) {
/* 123 */     ParameterBlockJAI pb = new ParameterBlockJAI("DCT", "renderable");
/* 127 */     pb.setSource("source0", source0);
/* 129 */     return JAI.createRenderable("DCT", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\DCTDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */