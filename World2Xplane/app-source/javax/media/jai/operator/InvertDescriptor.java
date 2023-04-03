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
/*     */ public class InvertDescriptor extends OperationDescriptorImpl {
/*  62 */   private static final String[][] resources = new String[][] { { "GlobalName", "Invert" }, { "LocalName", "Invert" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("InvertDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/InvertDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public InvertDescriptor() {
/*  73 */     super(resources, 1, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/*  78 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderingHints hints) {
/* 101 */     ParameterBlockJAI pb = new ParameterBlockJAI("Invert", "rendered");
/* 105 */     pb.setSource("source0", source0);
/* 107 */     return JAI.create("Invert", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderingHints hints) {
/* 129 */     ParameterBlockJAI pb = new ParameterBlockJAI("Invert", "renderable");
/* 133 */     pb.setSource("source0", source0);
/* 135 */     return JAI.createRenderable("Invert", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\InvertDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */