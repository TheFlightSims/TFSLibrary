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
/*     */ public class SubtractDescriptor extends OperationDescriptorImpl {
/*  85 */   private static final String[][] resources = new String[][] { { "GlobalName", "Subtract" }, { "LocalName", "Subtract" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("SubtractDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/SubtractDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public SubtractDescriptor() {
/*  96 */     super(resources, 2, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 101 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 127 */     ParameterBlockJAI pb = new ParameterBlockJAI("Subtract", "rendered");
/* 131 */     pb.setSource("source0", source0);
/* 132 */     pb.setSource("source1", source1);
/* 134 */     return JAI.create("Subtract", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 159 */     ParameterBlockJAI pb = new ParameterBlockJAI("Subtract", "renderable");
/* 163 */     pb.setSource("source0", source0);
/* 164 */     pb.setSource("source1", source1);
/* 166 */     return JAI.createRenderable("Subtract", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\SubtractDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */