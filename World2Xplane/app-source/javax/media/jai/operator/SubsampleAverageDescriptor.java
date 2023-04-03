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
/*     */ import javax.media.jai.util.Range;
/*     */ 
/*     */ public class SubsampleAverageDescriptor extends OperationDescriptorImpl {
/* 284 */   private static final String[][] resources = new String[][] { { "GlobalName", "SubsampleAverage" }, { "LocalName", "SubsampleAverage" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("SubsampleAverageDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/SubsampleAverageDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("SubsampleAverageDescriptor1") }, { "arg1Desc", JaiI18N.getString("SubsampleAverageDescriptor2") } };
/*     */   
/* 296 */   private static final Class[] paramClasses = new Class[] { Double.class, Double.class };
/*     */   
/* 301 */   private static final String[] paramNames = new String[] { "scaleX", "scaleY" };
/*     */   
/* 306 */   private static final Object[] paramDefaults = new Object[] { new Double(0.5D), null };
/*     */   
/* 311 */   private static final Object[] validParamValues = new Object[] { new Range(Double.class, new Double(Double.MIN_VALUE), new Double(1.0D)), new Range(Double.class, new Double(Double.MIN_VALUE), new Double(1.0D)) };
/*     */   
/*     */   public SubsampleAverageDescriptor() {
/* 318 */     super(resources, new String[] { "rendered", "renderable" }, 1, paramNames, paramClasses, paramDefaults, validParamValues);
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators(String modeName) {
/* 333 */     if (modeName == null)
/* 334 */       throw new IllegalArgumentException(JaiI18N.getString("SubsampleAverageDescriptor3")); 
/* 338 */     if (!"rendered".equalsIgnoreCase(modeName)) {
/* 339 */       PropertyGenerator[] pg = new PropertyGenerator[1];
/* 340 */       pg[0] = new SubsampleAveragePropertyGenerator();
/* 341 */       return pg;
/*     */     } 
/* 344 */     return null;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(String modeName, ParameterBlock args, StringBuffer msg) {
/* 357 */     if (!super.validateParameters(modeName, args, msg))
/* 358 */       return false; 
/* 361 */     if (args.getNumParameters() < 2 || args.getObjectParameter(1) == null)
/* 362 */       args.set(args.getObjectParameter(0), 1); 
/* 365 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Double scaleX, Double scaleY, RenderingHints hints) {
/* 394 */     ParameterBlockJAI pb = new ParameterBlockJAI("SubsampleAverage", "rendered");
/* 398 */     pb.setSource("source0", source0);
/* 400 */     pb.setParameter("scaleX", scaleX);
/* 401 */     pb.setParameter("scaleY", scaleY);
/* 403 */     return JAI.create("SubsampleAverage", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, Double scaleX, Double scaleY, RenderingHints hints) {
/* 431 */     ParameterBlockJAI pb = new ParameterBlockJAI("SubsampleAverage", "renderable");
/* 435 */     pb.setSource("source0", source0);
/* 437 */     pb.setParameter("scaleX", scaleX);
/* 438 */     pb.setParameter("scaleY", scaleY);
/* 440 */     return JAI.createRenderable("SubsampleAverage", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\SubsampleAverageDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */