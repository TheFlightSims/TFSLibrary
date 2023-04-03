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
/*     */ public class MinDescriptor extends OperationDescriptorImpl {
/*  71 */   private static final String[][] resources = new String[][] { { "GlobalName", "Min" }, { "LocalName", "Min" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MinDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/MinDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public MinDescriptor() {
/*  82 */     super(resources, 2, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/*  87 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 113 */     ParameterBlockJAI pb = new ParameterBlockJAI("Min", "rendered");
/* 117 */     pb.setSource("source0", source0);
/* 118 */     pb.setSource("source1", source1);
/* 120 */     return JAI.create("Min", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 145 */     ParameterBlockJAI pb = new ParameterBlockJAI("Min", "renderable");
/* 149 */     pb.setSource("source0", source0);
/* 150 */     pb.setSource("source1", source1);
/* 152 */     return JAI.createRenderable("Min", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MinDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */