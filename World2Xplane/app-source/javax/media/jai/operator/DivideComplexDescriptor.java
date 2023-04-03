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
/*     */ public class DivideComplexDescriptor extends OperationDescriptorImpl {
/*  92 */   private static final String[][] resources = new String[][] { { "GlobalName", "DivideComplex" }, { "LocalName", "DivideComplex" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("DivideComplexDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/DivideComplexDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/* 101 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public DivideComplexDescriptor() {
/* 108 */     super(resources, supportedModes, 2, null, null, null, null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 121 */     if (!super.validateSources(modeName, args, msg))
/* 122 */       return false; 
/* 125 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 126 */       return true; 
/* 128 */     RenderedImage src1 = args.getRenderedSource(0);
/* 129 */     RenderedImage src2 = args.getRenderedSource(1);
/* 131 */     if (src1.getSampleModel().getNumBands() % 2 != 0 || src2.getSampleModel().getNumBands() % 2 != 0) {
/* 133 */       msg.append(getName() + " " + JaiI18N.getString("DivideComplexDescriptor1"));
/* 135 */       return false;
/*     */     } 
/* 138 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/* 148 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 149 */     pg[0] = (PropertyGenerator)new ComplexPropertyGenerator();
/* 150 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderingHints hints) {
/* 176 */     ParameterBlockJAI pb = new ParameterBlockJAI("DivideComplex", "rendered");
/* 180 */     pb.setSource("source0", source0);
/* 181 */     pb.setSource("source1", source1);
/* 183 */     return JAI.create("DivideComplex", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderingHints hints) {
/* 208 */     ParameterBlockJAI pb = new ParameterBlockJAI("DivideComplex", "renderable");
/* 212 */     pb.setSource("source0", source0);
/* 213 */     pb.setSource("source1", source1);
/* 215 */     return JAI.createRenderable("DivideComplex", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\DivideComplexDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */