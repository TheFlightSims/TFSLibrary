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
/*     */ public class BinarizeDescriptor extends OperationDescriptorImpl {
/*  71 */   private static final String[][] resources = new String[][] { { "GlobalName", "Binarize" }, { "LocalName", "Binarize" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("BinarizeDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/BinarizeDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("BinarizeDescriptor1") } };
/*     */   
/*  82 */   private static final String[] paramNames = new String[] { "threshold" };
/*     */   
/*  90 */   private static final Class[] paramClasses = new Class[] { Double.class };
/*     */   
/*  95 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/*  99 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public BinarizeDescriptor() {
/* 106 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 120 */     if (!super.validateSources(modeName, args, msg))
/* 121 */       return false; 
/* 124 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 125 */       return true; 
/* 127 */     RenderedImage source = (RenderedImage)args.getSource(0);
/* 128 */     int numBands = source.getSampleModel().getNumBands();
/* 129 */     if (numBands != 1) {
/* 130 */       msg.append(getName() + " " + JaiI18N.getString("BinarizeDescriptor2"));
/* 132 */       return false;
/*     */     } 
/* 135 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Double threshold, RenderingHints hints) {
/* 161 */     ParameterBlockJAI pb = new ParameterBlockJAI("Binarize", "rendered");
/* 165 */     pb.setSource("source0", source0);
/* 167 */     pb.setParameter("threshold", threshold);
/* 169 */     return JAI.create("Binarize", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, Double threshold, RenderingHints hints) {
/* 194 */     ParameterBlockJAI pb = new ParameterBlockJAI("Binarize", "renderable");
/* 198 */     pb.setSource("source0", source0);
/* 200 */     pb.setParameter("threshold", threshold);
/* 202 */     return JAI.createRenderable("Binarize", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\BinarizeDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */