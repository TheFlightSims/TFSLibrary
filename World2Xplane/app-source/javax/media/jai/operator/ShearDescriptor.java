/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.Interpolation;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class ShearDescriptor extends OperationDescriptorImpl {
/* 227 */   public static final ShearDir SHEAR_HORIZONTAL = new ShearDir("SHEAR_HORIZONTAL", 0);
/*     */   
/* 229 */   public static final ShearDir SHEAR_VERTICAL = new ShearDir("SHEAR_VERTICAL", 1);
/*     */   
/* 236 */   private static final String[][] resources = new String[][] { 
/* 236 */       { "GlobalName", "Shear" }, { "LocalName", "Shear" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ShearDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ShearDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion2") }, { "arg0Desc", JaiI18N.getString("ShearDescriptor1") }, { "arg1Desc", JaiI18N.getString("ShearDescriptor2") }, { "arg2Desc", JaiI18N.getString("ShearDescriptor3") }, { "arg3Desc", JaiI18N.getString("ShearDescriptor4") }, 
/* 236 */       { "arg4Desc", JaiI18N.getString("ShearDescriptor5") }, { "arg5Desc", JaiI18N.getString("ShearDescriptor6") } };
/*     */   
/* 252 */   private static final String[] paramNames = new String[] { "shear", "shearDir", "xTrans", "yTrans", "interpolation", "backgroundValues" };
/*     */   
/* 258 */   private static final Class[] paramClasses = new Class[] { Float.class, ShearDir.class, Float.class, Float.class, Interpolation.class, (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/* 266 */   private static final Object[] paramDefaults = new Object[] { new Float(0.0F), SHEAR_HORIZONTAL, new Float(0.0F), new Float(0.0F), Interpolation.getInstance(0), { 0.0D } };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public ShearDescriptor() {
/* 275 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 285 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 286 */     pg[0] = (PropertyGenerator)new ShearPropertyGenerator();
/* 287 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Float shear, ShearDir shearDir, Float xTrans, Float yTrans, Interpolation interpolation, double[] backgroundValues, RenderingHints hints) {
/* 328 */     ParameterBlockJAI pb = new ParameterBlockJAI("Shear", "rendered");
/* 332 */     pb.setSource("source0", source0);
/* 334 */     pb.setParameter("shear", shear);
/* 335 */     pb.setParameter("shearDir", shearDir);
/* 336 */     pb.setParameter("xTrans", xTrans);
/* 337 */     pb.setParameter("yTrans", yTrans);
/* 338 */     pb.setParameter("interpolation", interpolation);
/* 339 */     pb.setParameter("backgroundValues", backgroundValues);
/* 341 */     return JAI.create("Shear", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ShearDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */