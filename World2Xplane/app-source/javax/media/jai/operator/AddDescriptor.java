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
/*     */ public class AddDescriptor extends OperationDescriptorImpl {
/*  79 */   private static final String[][] resources = new String[][] { { "GlobalName", "Add" }, { "LocalName", "Add" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("AddDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/AddDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public AddDescriptor() {
/*  90 */     super(resources, 2, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/*  95 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 121 */     ParameterBlockJAI pb = new ParameterBlockJAI("Add", "rendered");
/* 125 */     pb.setSource("source0", source0);
/* 126 */     pb.setSource("source1", source1);
/* 128 */     return JAI.create("Add", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 153 */     ParameterBlockJAI pb = new ParameterBlockJAI("Add", "renderable");
/* 157 */     pb.setSource("source0", source0);
/* 158 */     pb.setSource("source1", source1);
/* 160 */     return JAI.createRenderable("Add", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\AddDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */