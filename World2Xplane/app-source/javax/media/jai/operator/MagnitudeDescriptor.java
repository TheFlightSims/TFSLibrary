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
/*     */ public class MagnitudeDescriptor extends OperationDescriptorImpl {
/*  74 */   private static final String[][] resources = new String[][] { { "GlobalName", "Magnitude" }, { "LocalName", "Magnitude" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MagnitudeDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/MagnitudeDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*  83 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public MagnitudeDescriptor() {
/*  90 */     super(resources, supportedModes, 1, null, null, null, null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 103 */     if (!super.validateSources(modeName, args, msg))
/* 104 */       return false; 
/* 107 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 108 */       return true; 
/* 110 */     RenderedImage src = args.getRenderedSource(0);
/* 112 */     int bands = src.getSampleModel().getNumBands();
/* 114 */     if (bands % 2 != 0) {
/* 115 */       msg.append(getName() + " " + JaiI18N.getString("MagnitudeDescriptor1"));
/* 117 */       return false;
/*     */     } 
/* 120 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/* 130 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 131 */     pg[0] = (PropertyGenerator)new ComplexPropertyGenerator();
/* 132 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderingHints hints) {
/* 155 */     ParameterBlockJAI pb = new ParameterBlockJAI("Magnitude", "rendered");
/* 159 */     pb.setSource("source0", source0);
/* 161 */     return JAI.create("Magnitude", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderingHints hints) {
/* 183 */     ParameterBlockJAI pb = new ParameterBlockJAI("Magnitude", "renderable");
/* 187 */     pb.setSource("source0", source0);
/* 189 */     return JAI.createRenderable("Magnitude", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MagnitudeDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */