/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class MultiplyComplexDescriptor extends OperationDescriptorImpl {
/*  91 */   private static final String[][] resources = new String[][] { { "GlobalName", "MultiplyComplex" }, { "LocalName", "MultiplyComplex" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MultiplyComplexDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/MultiplyComplexDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/* 100 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public MultiplyComplexDescriptor() {
/* 107 */     super(resources, supportedModes, 2, null, null, null, null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 120 */     if (!super.validateSources(modeName, args, msg))
/* 121 */       return false; 
/* 124 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 125 */       return true; 
/* 127 */     RenderedImage src1 = args.getRenderedSource(0);
/* 128 */     RenderedImage src2 = args.getRenderedSource(1);
/* 130 */     if (src1.getSampleModel().getNumBands() % 2 != 0 || src2.getSampleModel().getNumBands() % 2 != 0) {
/* 132 */       msg.append(getName() + " " + JaiI18N.getString("MultiplyComplexDescriptor1"));
/* 134 */       return false;
/*     */     } 
/* 137 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/* 147 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 148 */     pg[0] = (PropertyGenerator)new ComplexPropertyGenerator();
/* 149 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 175 */     ParameterBlockJAI pb = new ParameterBlockJAI("MultiplyComplex", "rendered");
/* 179 */     pb.setSource("source0", source0);
/* 180 */     pb.setSource("source1", source1);
/* 182 */     return JAI.create("MultiplyComplex", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 207 */     ParameterBlockJAI pb = new ParameterBlockJAI("MultiplyComplex", "renderable");
/* 211 */     pb.setSource("source0", source0);
/* 212 */     pb.setSource("source1", source1);
/* 214 */     return JAI.createRenderable("MultiplyComplex", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MultiplyComplexDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */