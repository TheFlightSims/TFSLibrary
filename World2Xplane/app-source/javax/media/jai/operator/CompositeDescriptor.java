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
/*     */ public class CompositeDescriptor extends OperationDescriptorImpl {
/* 153 */   public static final CompositeDestAlpha NO_DESTINATION_ALPHA = new CompositeDestAlpha("NO_DESTINATION_ALPHA", 0);
/*     */   
/* 157 */   public static final CompositeDestAlpha DESTINATION_ALPHA_FIRST = new CompositeDestAlpha("DESTINATION_ALPHA_FIRST", 1);
/*     */   
/* 161 */   public static final CompositeDestAlpha DESTINATION_ALPHA_LAST = new CompositeDestAlpha("DESTINATION_ALPHA_LAST", 2);
/*     */   
/* 168 */   protected static final String[][] resources = new String[][] { { "GlobalName", "Composite" }, { "LocalName", "Composite" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("CompositeDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/CompositeDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion2") }, { "arg0Desc", JaiI18N.getString("CompositeDescriptor1") }, { "arg1Desc", JaiI18N.getString("CompositeDescriptor2") }, { "arg2Desc", JaiI18N.getString("CompositeDescriptor3") }, { "arg3Desc", JaiI18N.getString("CompositeDescriptor4") } };
/*     */   
/* 181 */   private static final Class[][] sourceClasses = new Class[][] { { RenderedImage.class, RenderedImage.class }, { RenderableImage.class, RenderableImage.class } };
/*     */   
/* 193 */   private static final Class[][] paramClasses = new Class[][] { { RenderedImage.class, RenderedImage.class, Boolean.class, CompositeDestAlpha.class }, { RenderableImage.class, RenderableImage.class, Boolean.class, CompositeDestAlpha.class } };
/*     */   
/* 209 */   private static final String[] paramNames = new String[] { "source1Alpha", "source2Alpha", "alphaPremultiplied", "destAlpha" };
/*     */   
/* 215 */   private static final Object[][] paramDefaults = new Object[][] { { NO_PARAMETER_DEFAULT, null, Boolean.FALSE, NO_DESTINATION_ALPHA }, { NO_PARAMETER_DEFAULT, null, Boolean.FALSE, NO_DESTINATION_ALPHA } };
/*     */   
/* 226 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public CompositeDescriptor() {
/* 233 */     super(resources, supportedModes, null, sourceClasses, paramNames, paramClasses, paramDefaults, (Object[][])null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer msg) {
/* 255 */     if (!super.validateArguments(modeName, args, msg))
/* 256 */       return false; 
/* 259 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 260 */       return true; 
/* 262 */     RenderedImage src1 = args.getRenderedSource(0);
/* 263 */     RenderedImage src2 = args.getRenderedSource(1);
/* 265 */     SampleModel s1sm = src1.getSampleModel();
/* 266 */     SampleModel s2sm = src2.getSampleModel();
/* 267 */     if (s1sm.getNumBands() != s2sm.getNumBands() || s1sm.getTransferType() != s2sm.getTransferType()) {
/* 269 */       msg.append(getName() + " " + JaiI18N.getString("CompositeDescriptor8"));
/* 271 */       return false;
/*     */     } 
/* 275 */     RenderedImage afa1 = (RenderedImage)args.getObjectParameter(0);
/* 276 */     if (src1.getMinX() != afa1.getMinX() || src1.getMinY() != afa1.getMinY() || src1.getWidth() != afa1.getWidth() || src1.getHeight() != afa1.getHeight()) {
/* 280 */       msg.append(getName() + " " + JaiI18N.getString("CompositeDescriptor12"));
/* 282 */       return false;
/*     */     } 
/* 285 */     SampleModel a1sm = afa1.getSampleModel();
/* 286 */     if (s1sm.getTransferType() != a1sm.getTransferType()) {
/* 287 */       msg.append(getName() + " " + JaiI18N.getString("CompositeDescriptor13"));
/* 289 */       return false;
/*     */     } 
/* 292 */     RenderedImage afa2 = (RenderedImage)args.getObjectParameter(1);
/* 293 */     if (afa2 != null) {
/* 294 */       if (src2.getMinX() != afa2.getMinX() || src2.getMinY() != afa2.getMinY() || src2.getWidth() != afa2.getWidth() || src2.getHeight() != afa2.getHeight()) {
/* 298 */         msg.append(getName() + " " + JaiI18N.getString("CompositeDescriptor15"));
/* 300 */         return false;
/*     */       } 
/* 303 */       SampleModel a2sm = afa2.getSampleModel();
/* 304 */       if (s2sm.getTransferType() != a2sm.getTransferType()) {
/* 305 */         msg.append(getName() + " " + JaiI18N.getString("CompositeDescriptor16"));
/* 307 */         return false;
/*     */       } 
/*     */     } 
/* 311 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, RenderedImage source1, RenderedImage source1Alpha, RenderedImage source2Alpha, Boolean alphaPremultiplied, CompositeDestAlpha destAlpha, RenderingHints hints) {
/* 349 */     ParameterBlockJAI pb = new ParameterBlockJAI("Composite", "rendered");
/* 353 */     pb.setSource("source0", source0);
/* 354 */     pb.setSource("source1", source1);
/* 356 */     pb.setParameter("source1Alpha", source1Alpha);
/* 357 */     pb.setParameter("source2Alpha", source2Alpha);
/* 358 */     pb.setParameter("alphaPremultiplied", alphaPremultiplied);
/* 359 */     pb.setParameter("destAlpha", destAlpha);
/* 361 */     return JAI.create("Composite", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, RenderableImage source1, RenderableImage source1Alpha, RenderableImage source2Alpha, Boolean alphaPremultiplied, CompositeDestAlpha destAlpha, RenderingHints hints) {
/* 398 */     ParameterBlockJAI pb = new ParameterBlockJAI("Composite", "renderable");
/* 402 */     pb.setSource("source0", source0);
/* 403 */     pb.setSource("source1", source1);
/* 405 */     pb.setParameter("source1Alpha", source1Alpha);
/* 406 */     pb.setParameter("source2Alpha", source2Alpha);
/* 407 */     pb.setParameter("alphaPremultiplied", alphaPremultiplied);
/* 408 */     pb.setParameter("destAlpha", destAlpha);
/* 410 */     return JAI.createRenderable("Composite", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\CompositeDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */