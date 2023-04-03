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
/*     */ public class DivideDescriptor extends OperationDescriptorImpl {
/*  83 */   private static final String[][] resources = new String[][] { { "GlobalName", "Divide" }, { "LocalName", "Divide" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("DivideDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/DivideDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public DivideDescriptor() {
/*  94 */     super(resources, 2, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/*  99 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 125 */     ParameterBlockJAI pb = new ParameterBlockJAI("Divide", "rendered");
/* 129 */     pb.setSource("source0", source0);
/* 130 */     pb.setSource("source1", source1);
/* 132 */     return JAI.create("Divide", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 157 */     ParameterBlockJAI pb = new ParameterBlockJAI("Divide", "renderable");
/* 161 */     pb.setSource("source0", source0);
/* 162 */     pb.setSource("source1", source1);
/* 164 */     return JAI.createRenderable("Divide", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\DivideDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */