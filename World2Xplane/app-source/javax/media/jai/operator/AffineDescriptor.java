/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
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
/*     */ public class AffineDescriptor extends OperationDescriptorImpl {
/* 236 */   private static final String[][] resources = new String[][] { { "GlobalName", "Affine" }, { "LocalName", "Affine" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("AffineDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/AffineDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("AffineDescriptor1") }, { "arg1Desc", JaiI18N.getString("AffineDescriptor2") }, { "arg2Desc", JaiI18N.getString("AffineDescriptor3") } };
/*     */   
/* 249 */   private static final Class[] paramClasses = new Class[] { AffineTransform.class, Interpolation.class, (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/* 256 */   private static final String[] paramNames = new String[] { "transform", "interpolation", "backgroundValues" };
/*     */   
/* 261 */   private static final Object[] paramDefaults = new Object[] { new AffineTransform(), Interpolation.getInstance(0), { 0.0D } };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public AffineDescriptor() {
/* 269 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 274 */     return true;
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 284 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 285 */     pg[0] = (PropertyGenerator)new AffinePropertyGenerator();
/* 286 */     return pg;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer message) {
/* 298 */     if (!super.validateParameters(args, message))
/* 299 */       return false; 
/* 302 */     AffineTransform transform = (AffineTransform)args.getObjectParameter(0);
/*     */     try {
/* 305 */       AffineTransform itransform = transform.createInverse();
/* 306 */     } catch (NoninvertibleTransformException e) {
/* 307 */       message.append(getName() + " " + JaiI18N.getString("AffineDescriptor4"));
/* 309 */       return false;
/*     */     } 
/* 312 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, AffineTransform transform, Interpolation interpolation, double[] backgroundValues, RenderingHints hints) {
/* 344 */     ParameterBlockJAI pb = new ParameterBlockJAI("Affine", "rendered");
/* 348 */     pb.setSource("source0", source0);
/* 350 */     pb.setParameter("transform", transform);
/* 351 */     pb.setParameter("interpolation", interpolation);
/* 352 */     pb.setParameter("backgroundValues", backgroundValues);
/* 354 */     return JAI.create("Affine", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, AffineTransform transform, Interpolation interpolation, double[] backgroundValues, RenderingHints hints) {
/* 385 */     ParameterBlockJAI pb = new ParameterBlockJAI("Affine", "renderable");
/* 389 */     pb.setSource("source0", source0);
/* 391 */     pb.setParameter("transform", transform);
/* 392 */     pb.setParameter("interpolation", interpolation);
/* 393 */     pb.setParameter("backgroundValues", backgroundValues);
/* 395 */     return JAI.createRenderable("Affine", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\AffineDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */