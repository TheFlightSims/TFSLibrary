/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class OverlayDescriptor extends OperationDescriptorImpl {
/*  72 */   private static final String[][] resources = new String[][] { { "GlobalName", "Overlay" }, { "LocalName", "Overlay" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("OverlayDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/OverlayDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*  81 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public OverlayDescriptor() {
/*  88 */     super(resources, supportedModes, 2, null, null, null, null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 102 */     if (!super.validateSources(modeName, args, msg))
/* 103 */       return false; 
/* 106 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 107 */       return true; 
/* 109 */     RenderedImage src1 = args.getRenderedSource(0);
/* 110 */     RenderedImage src2 = args.getRenderedSource(1);
/* 112 */     SampleModel s1sm = src1.getSampleModel();
/* 113 */     SampleModel s2sm = src2.getSampleModel();
/* 115 */     if (s1sm.getNumBands() != s2sm.getNumBands() || s1sm.getTransferType() != s2sm.getTransferType()) {
/* 117 */       msg.append(getName() + " " + JaiI18N.getString("OverlayDescriptor1"));
/* 119 */       return false;
/*     */     } 
/* 122 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 148 */     ParameterBlockJAI pb = new ParameterBlockJAI("Overlay", "rendered");
/* 152 */     pb.setSource("source0", source0);
/* 153 */     pb.setSource("source1", source1);
/* 155 */     return JAI.create("Overlay", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 180 */     ParameterBlockJAI pb = new ParameterBlockJAI("Overlay", "renderable");
/* 184 */     pb.setSource("source0", source0);
/* 185 */     pb.setSource("source1", source1);
/* 187 */     return JAI.createRenderable("Overlay", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\OverlayDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */