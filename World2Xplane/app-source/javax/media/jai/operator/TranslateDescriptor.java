/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class TranslateDescriptor extends OperationDescriptorImpl {
/* 202 */   private static final String[][] resources = new String[][] { { "GlobalName", "Translate" }, { "LocalName", "Translate" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("TranslateDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/TranslateDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("TranslateDescriptor1") }, { "arg1Desc", JaiI18N.getString("TranslateDescriptor2") }, { "arg2Desc", JaiI18N.getString("TranslateDescriptor3") } };
/*     */   
/* 215 */   private static final String[] paramNames = new String[] { "xTrans", "yTrans", "interpolation" };
/*     */   
/* 220 */   private static final Class[] paramClasses = new Class[] { Float.class, Float.class, Interpolation.class };
/*     */   
/* 226 */   private static final Object[] paramDefaults = new Object[] { new Float(0.0F), new Float(0.0F), Interpolation.getInstance(0) };
/*     */   
/*     */   public TranslateDescriptor() {
/* 233 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 238 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 248 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 249 */     pg[0] = (PropertyGenerator)new TranslatePropertyGenerator();
/* 250 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Float xTrans, Float yTrans, Interpolation interpolation, RenderingHints hints) {
/* 282 */     ParameterBlockJAI pb = new ParameterBlockJAI("Translate", "rendered");
/* 286 */     pb.setSource("source0", source0);
/* 288 */     pb.setParameter("xTrans", xTrans);
/* 289 */     pb.setParameter("yTrans", yTrans);
/* 290 */     pb.setParameter("interpolation", interpolation);
/* 292 */     return JAI.create("Translate", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, Float xTrans, Float yTrans, Interpolation interpolation, RenderingHints hints) {
/* 323 */     ParameterBlockJAI pb = new ParameterBlockJAI("Translate", "renderable");
/* 327 */     pb.setSource("source0", source0);
/* 329 */     pb.setParameter("xTrans", xTrans);
/* 330 */     pb.setParameter("yTrans", yTrans);
/* 331 */     pb.setParameter("interpolation", interpolation);
/* 333 */     return JAI.createRenderable("Translate", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\TranslateDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */