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
/*     */ public class ExpDescriptor extends OperationDescriptorImpl {
/*  58 */   private static final String[][] resources = new String[][] { { "GlobalName", "Exp" }, { "LocalName", "Exp" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ExpDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ExpDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public ExpDescriptor() {
/*  69 */     super(resources, 1, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/*  74 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderingHints hints) {
/*  97 */     ParameterBlockJAI pb = new ParameterBlockJAI("Exp", "rendered");
/* 101 */     pb.setSource("source0", source0);
/* 103 */     return JAI.create("Exp", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderingHints hints) {
/* 125 */     ParameterBlockJAI pb = new ParameterBlockJAI("Exp", "renderable");
/* 129 */     pb.setSource("source0", source0);
/* 131 */     return JAI.createRenderable("Exp", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ExpDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */