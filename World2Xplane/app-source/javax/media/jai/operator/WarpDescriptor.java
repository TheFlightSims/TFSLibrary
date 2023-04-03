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
/*     */ import javax.media.jai.Warp;
/*     */ 
/*     */ public class WarpDescriptor extends OperationDescriptorImpl {
/* 218 */   private static final String[][] resources = new String[][] { { "GlobalName", "Warp" }, { "LocalName", "Warp" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("WarpDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/WarpDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("WarpDescriptor1") }, { "arg1Desc", JaiI18N.getString("WarpDescriptor2") }, { "arg2Desc", JaiI18N.getString("WarpDescriptor3") } };
/*     */   
/* 231 */   private static final String[] paramNames = new String[] { "warp", "interpolation", "backgroundValues" };
/*     */   
/* 236 */   private static final Class[] paramClasses = new Class[] { Warp.class, Interpolation.class, (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/* 242 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, Interpolation.getInstance(0), { 0.0D } };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public WarpDescriptor() {
/* 250 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 260 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 261 */     pg[0] = (PropertyGenerator)new WarpPropertyGenerator();
/* 262 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Warp warp, Interpolation interpolation, double[] backgroundValues, RenderingHints hints) {
/* 294 */     ParameterBlockJAI pb = new ParameterBlockJAI("Warp", "rendered");
/* 298 */     pb.setSource("source0", source0);
/* 300 */     pb.setParameter("warp", warp);
/* 301 */     pb.setParameter("interpolation", interpolation);
/* 302 */     pb.setParameter("backgroundValues", backgroundValues);
/* 304 */     return JAI.create("Warp", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\WarpDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */