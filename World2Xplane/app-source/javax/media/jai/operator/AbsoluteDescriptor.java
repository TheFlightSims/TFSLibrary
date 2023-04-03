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
/*     */ public class AbsoluteDescriptor extends OperationDescriptorImpl {
/*  65 */   private static final String[][] resources = new String[][] { { "GlobalName", "Absolute" }, { "LocalName", "Absolute" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("AbsoluteDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/AbsoluteDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public AbsoluteDescriptor() {
/*  76 */     super(resources, 1, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/*  81 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderingHints hints) {
/* 104 */     ParameterBlockJAI pb = new ParameterBlockJAI("Absolute", "rendered");
/* 108 */     pb.setSource("source0", source0);
/* 110 */     return JAI.create("Absolute", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderingHints hints) {
/* 132 */     ParameterBlockJAI pb = new ParameterBlockJAI("Absolute", "renderable");
/* 136 */     pb.setSource("source0", source0);
/* 138 */     return JAI.createRenderable("Absolute", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\AbsoluteDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */