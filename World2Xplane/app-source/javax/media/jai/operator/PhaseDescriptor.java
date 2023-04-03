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
/*     */ public class PhaseDescriptor extends OperationDescriptorImpl {
/*  76 */   private static final String[][] resources = new String[][] { { "GlobalName", "Phase" }, { "LocalName", "Phase" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("PhaseDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/PhaseDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*  85 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public PhaseDescriptor() {
/*  92 */     super(resources, supportedModes, 1, null, null, null, null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 105 */     if (!super.validateSources(modeName, args, msg))
/* 106 */       return false; 
/* 109 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 110 */       return true; 
/* 112 */     RenderedImage src = args.getRenderedSource(0);
/* 114 */     int bands = src.getSampleModel().getNumBands();
/* 116 */     if (bands % 2 != 0) {
/* 117 */       msg.append(getName() + " " + JaiI18N.getString("PhaseDescriptor1"));
/* 119 */       return false;
/*     */     } 
/* 122 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/* 132 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 133 */     pg[0] = (PropertyGenerator)new ComplexPropertyGenerator();
/* 134 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderingHints hints) {
/* 157 */     ParameterBlockJAI pb = new ParameterBlockJAI("Phase", "rendered");
/* 161 */     pb.setSource("source0", source0);
/* 163 */     return JAI.create("Phase", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderingHints hints) {
/* 185 */     ParameterBlockJAI pb = new ParameterBlockJAI("Phase", "renderable");
/* 189 */     pb.setSource("source0", source0);
/* 191 */     return JAI.createRenderable("Phase", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\PhaseDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */