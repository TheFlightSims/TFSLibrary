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
/*     */ public class ConjugateDescriptor extends OperationDescriptorImpl {
/*  72 */   private static final String[][] resources = new String[][] { { "GlobalName", "Conjugate" }, { "LocalName", "Conjugate" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ConjugateDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ConjugateDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*  81 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public ConjugateDescriptor() {
/*  88 */     super(resources, supportedModes, 1, null, null, null, null);
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/*  98 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/*  99 */     pg[0] = (PropertyGenerator)new ComplexPropertyGenerator();
/* 100 */     return pg;
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 113 */     if (!super.validateSources(modeName, args, msg))
/* 114 */       return false; 
/* 117 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 118 */       return true; 
/* 120 */     RenderedImage src = args.getRenderedSource(0);
/* 122 */     if (src.getSampleModel().getNumBands() % 2 != 0) {
/* 123 */       msg.append(getName() + " " + JaiI18N.getString("ConjugateDescriptor1"));
/* 125 */       return false;
/*     */     } 
/* 128 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderingHints hints) {
/* 151 */     ParameterBlockJAI pb = new ParameterBlockJAI("Conjugate", "rendered");
/* 155 */     pb.setSource("source0", source0);
/* 157 */     return JAI.create("Conjugate", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderingHints hints) {
/* 179 */     ParameterBlockJAI pb = new ParameterBlockJAI("Conjugate", "renderable");
/* 183 */     pb.setSource("source0", source0);
/* 185 */     return JAI.createRenderable("Conjugate", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ConjugateDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */