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
/*     */ public class MaxDescriptor extends OperationDescriptorImpl {
/*  70 */   private static final String[][] resources = new String[][] { { "GlobalName", "Max" }, { "LocalName", "Max" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MaxDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/MaxDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public MaxDescriptor() {
/*  81 */     super(resources, 2, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/*  86 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 112 */     ParameterBlockJAI pb = new ParameterBlockJAI("Max", "rendered");
/* 116 */     pb.setSource("source0", source0);
/* 117 */     pb.setSource("source1", source1);
/* 119 */     return JAI.create("Max", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 144 */     ParameterBlockJAI pb = new ParameterBlockJAI("Max", "renderable");
/* 148 */     pb.setSource("source0", source0);
/* 149 */     pb.setSource("source1", source1);
/* 151 */     return JAI.createRenderable("Max", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MaxDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */