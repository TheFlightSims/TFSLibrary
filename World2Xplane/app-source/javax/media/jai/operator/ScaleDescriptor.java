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
/*     */ public class ScaleDescriptor extends OperationDescriptorImpl {
/* 263 */   private static final String[][] resources = new String[][] { 
/* 263 */       { "GlobalName", "Scale" }, { "LocalName", "Scale" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ScaleDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ScaleDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("ScaleDescriptor1") }, { "arg1Desc", JaiI18N.getString("ScaleDescriptor2") }, { "arg2Desc", JaiI18N.getString("ScaleDescriptor3") }, { "arg3Desc", JaiI18N.getString("ScaleDescriptor4") }, 
/* 263 */       { "arg4Desc", JaiI18N.getString("ScaleDescriptor5") } };
/*     */   
/* 278 */   private static final Class[] paramClasses = new Class[] { Float.class, Float.class, Float.class, Float.class, Interpolation.class };
/*     */   
/* 285 */   private static final String[] paramNames = new String[] { "xScale", "yScale", "xTrans", "yTrans", "interpolation" };
/*     */   
/* 290 */   private static final Object[] paramDefaults = new Object[] { new Float(1.0F), new Float(1.0F), new Float(0.0F), new Float(0.0F), Interpolation.getInstance(0) };
/*     */   
/*     */   public ScaleDescriptor() {
/* 298 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 303 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 313 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 314 */     pg[0] = (PropertyGenerator)new ScalePropertyGenerator();
/* 315 */     return pg;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 327 */     if (!super.validateParameters(args, msg))
/* 328 */       return false; 
/* 331 */     float xScale = args.getFloatParameter(0);
/* 332 */     float yScale = args.getFloatParameter(1);
/* 333 */     if (xScale <= 0.0F || yScale <= 0.0F) {
/* 334 */       msg.append(getName() + " " + JaiI18N.getString("ScaleDescriptor6"));
/* 336 */       return false;
/*     */     } 
/* 339 */     return true;
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 351 */     if (index == 0 || index == 1)
/* 352 */       return new Float(0.0F); 
/* 353 */     if (index == 2 || index == 3)
/* 354 */       return new Float(-3.4028235E38F); 
/* 355 */     if (index == 4)
/* 356 */       return null; 
/* 358 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Float xScale, Float yScale, Float xTrans, Float yTrans, Interpolation interpolation, RenderingHints hints) {
/* 397 */     ParameterBlockJAI pb = new ParameterBlockJAI("Scale", "rendered");
/* 401 */     pb.setSource("source0", source0);
/* 403 */     pb.setParameter("xScale", xScale);
/* 404 */     pb.setParameter("yScale", yScale);
/* 405 */     pb.setParameter("xTrans", xTrans);
/* 406 */     pb.setParameter("yTrans", yTrans);
/* 407 */     pb.setParameter("interpolation", interpolation);
/* 409 */     return JAI.create("Scale", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, Float xScale, Float yScale, Float xTrans, Float yTrans, Interpolation interpolation, RenderingHints hints) {
/* 446 */     ParameterBlockJAI pb = new ParameterBlockJAI("Scale", "renderable");
/* 450 */     pb.setSource("source0", source0);
/* 452 */     pb.setParameter("xScale", xScale);
/* 453 */     pb.setParameter("yScale", yScale);
/* 454 */     pb.setParameter("xTrans", xTrans);
/* 455 */     pb.setParameter("yTrans", yTrans);
/* 456 */     pb.setParameter("interpolation", interpolation);
/* 458 */     return JAI.createRenderable("Scale", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ScaleDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */