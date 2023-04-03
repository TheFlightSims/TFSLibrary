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
/*     */ public class BandMergeDescriptor extends OperationDescriptorImpl {
/*  68 */   private static final String[][] resources = new String[][] { { "GlobalName", "BandMerge" }, { "LocalName", "BandMerge" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("BandMergeDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/BandMergeDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*     */   public BandMergeDescriptor() {
/*  80 */     super(resources, 2, null, null, null);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/*  85 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 111 */     ParameterBlockJAI pb = new ParameterBlockJAI("BandMerge", "rendered");
/* 115 */     pb.setSource("source0", source0);
/* 116 */     pb.setSource("source1", source1);
/* 118 */     return JAI.create("BandMerge", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 143 */     ParameterBlockJAI pb = new ParameterBlockJAI("BandMerge", "renderable");
/* 147 */     pb.setSource("source0", source0);
/* 148 */     pb.setSource("source1", source1);
/* 150 */     return JAI.createRenderable("BandMerge", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\BandMergeDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */