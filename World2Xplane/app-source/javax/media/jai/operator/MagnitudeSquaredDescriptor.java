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
/*     */ public class MagnitudeSquaredDescriptor extends OperationDescriptorImpl {
/*  75 */   private static final String[][] resources = new String[][] { { "GlobalName", "MagnitudeSquared" }, { "LocalName", "MagnitudeSquared" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MagnitudeSquaredDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/MagnitudeSquaredDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*  84 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public MagnitudeSquaredDescriptor() {
/*  91 */     super(resources, supportedModes, 1, null, null, null, null);
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/* 101 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 102 */     pg[0] = (PropertyGenerator)new ComplexPropertyGenerator();
/* 103 */     return pg;
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 116 */     if (!super.validateSources(modeName, args, msg))
/* 117 */       return false; 
/* 120 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 121 */       return true; 
/* 123 */     RenderedImage src = args.getRenderedSource(0);
/* 125 */     int bands = src.getSampleModel().getNumBands();
/* 127 */     if (bands % 2 != 0) {
/* 128 */       msg.append(getName() + " " + JaiI18N.getString("MagnitudeSquaredDescriptor1"));
/* 130 */       return false;
/*     */     } 
/* 133 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderingHints hints) {
/* 156 */     ParameterBlockJAI pb = new ParameterBlockJAI("MagnitudeSquared", "rendered");
/* 160 */     pb.setSource("source0", source0);
/* 162 */     return JAI.create("MagnitudeSquared", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderingHints hints) {
/* 184 */     ParameterBlockJAI pb = new ParameterBlockJAI("MagnitudeSquared", "renderable");
/* 188 */     pb.setSource("source0", source0);
/* 190 */     return JAI.createRenderable("MagnitudeSquared", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MagnitudeSquaredDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */