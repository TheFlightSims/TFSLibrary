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
/*     */ public class PolarToComplexDescriptor extends OperationDescriptorImpl {
/*  80 */   private static final String[][] resources = new String[][] { { "GlobalName", "PolarToComplex" }, { "LocalName", "PolarToComplex" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("PolarToComplexDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/PolarToComplexDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*  89 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public PolarToComplexDescriptor() {
/*  96 */     super(resources, supportedModes, 2, null, null, null, null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 109 */     if (!super.validateSources(modeName, args, msg))
/* 110 */       return false; 
/* 113 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 114 */       return true; 
/* 116 */     RenderedImage src1 = args.getRenderedSource(0);
/* 117 */     RenderedImage src2 = args.getRenderedSource(1);
/* 119 */     if (src1.getSampleModel().getNumBands() != src2.getSampleModel().getNumBands()) {
/* 121 */       msg.append(getName() + " " + JaiI18N.getString("PolarToComplexDescriptor1"));
/* 123 */       return false;
/*     */     } 
/* 126 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/* 136 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 137 */     pg[0] = (PropertyGenerator)new ComplexPropertyGenerator();
/* 138 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 164 */     ParameterBlockJAI pb = new ParameterBlockJAI("PolarToComplex", "rendered");
/* 168 */     pb.setSource("source0", source0);
/* 169 */     pb.setSource("source1", source1);
/* 171 */     return JAI.create("PolarToComplex", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 196 */     ParameterBlockJAI pb = new ParameterBlockJAI("PolarToComplex", "renderable");
/* 200 */     pb.setSource("source0", source0);
/* 201 */     pb.setSource("source1", source1);
/* 203 */     return JAI.createRenderable("PolarToComplex", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\PolarToComplexDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */