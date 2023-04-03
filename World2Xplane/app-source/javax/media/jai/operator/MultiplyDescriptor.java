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
/*     */ public class MultiplyDescriptor extends OperationDescriptorImpl {
/*  81 */   private static final String[][] resources = new String[][] { { "GlobalName", "Multiply" }, { "LocalName", "Multiply" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MultiplyDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/MultiplyDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public MultiplyDescriptor() {
/*  92 */     super(resources, 2, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/*  97 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 123 */     ParameterBlockJAI pb = new ParameterBlockJAI("Multiply", "rendered");
/* 127 */     pb.setSource("source0", source0);
/* 128 */     pb.setSource("source1", source1);
/* 130 */     return JAI.create("Multiply", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 155 */     ParameterBlockJAI pb = new ParameterBlockJAI("Multiply", "renderable");
/* 159 */     pb.setSource("source0", source0);
/* 160 */     pb.setSource("source1", source1);
/* 162 */     return JAI.createRenderable("Multiply", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MultiplyDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */