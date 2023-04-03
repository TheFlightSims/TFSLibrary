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
/*     */ public class RotateDescriptor extends OperationDescriptorImpl {
/* 212 */   private static final String[][] resources = new String[][] { 
/* 212 */       { "GlobalName", "Rotate" }, { "LocalName", "Rotate" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("RotateDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/RotateDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("RotateDescriptor1") }, { "arg1Desc", JaiI18N.getString("RotateDescriptor2") }, { "arg2Desc", JaiI18N.getString("RotateDescriptor3") }, { "arg3Desc", JaiI18N.getString("RotateDescriptor4") }, 
/* 212 */       { "arg4Desc", JaiI18N.getString("RotateDescriptor5") } };
/*     */   
/* 227 */   private static final String[] paramNames = new String[] { "xOrigin", "yOrigin", "angle", "interpolation", "backgroundValues" };
/*     */   
/* 232 */   private static final Class[] paramClasses = new Class[] { Float.class, Float.class, Float.class, Interpolation.class, (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/* 239 */   private static final Object[] paramDefaults = new Object[] { new Float(0.0F), new Float(0.0F), new Float(0.0F), Interpolation.getInstance(0), { 0.0D } };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public RotateDescriptor() {
/* 247 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 252 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 262 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 263 */     pg[0] = (PropertyGenerator)new RotatePropertyGenerator();
/* 264 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Float xOrigin, Float yOrigin, Float angle, Interpolation interpolation, double[] backgroundValues, RenderingHints hints) {
/* 302 */     ParameterBlockJAI pb = new ParameterBlockJAI("Rotate", "rendered");
/* 306 */     pb.setSource("source0", source0);
/* 308 */     pb.setParameter("xOrigin", xOrigin);
/* 309 */     pb.setParameter("yOrigin", yOrigin);
/* 310 */     pb.setParameter("angle", angle);
/* 311 */     pb.setParameter("interpolation", interpolation);
/* 312 */     pb.setParameter("backgroundValues", backgroundValues);
/* 314 */     return JAI.create("Rotate", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, Float xOrigin, Float yOrigin, Float angle, Interpolation interpolation, double[] backgroundValues, RenderingHints hints) {
/* 351 */     ParameterBlockJAI pb = new ParameterBlockJAI("Rotate", "renderable");
/* 355 */     pb.setSource("source0", source0);
/* 357 */     pb.setParameter("xOrigin", xOrigin);
/* 358 */     pb.setParameter("yOrigin", yOrigin);
/* 359 */     pb.setParameter("angle", angle);
/* 360 */     pb.setParameter("interpolation", interpolation);
/* 361 */     pb.setParameter("backgroundValues", backgroundValues);
/* 363 */     return JAI.createRenderable("Rotate", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\RotateDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */