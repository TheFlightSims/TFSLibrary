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
/*     */ public class MultiplyConstDescriptor extends OperationDescriptorImpl {
/*  80 */   private static final String[][] resources = new String[][] { { "GlobalName", "MultiplyConst" }, { "LocalName", "MultiplyConst" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MultiplyConstDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/MultiplyConstDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("MultiplyConstDescriptor1") } };
/*     */   
/*  97 */   private static final Class[] paramClasses = new Class[] { (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/*  98 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/*  98 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/* 102 */   private static final String[] paramNames = new String[] { "constants" };
/*     */   
/* 107 */   private static final Object[] paramDefaults = new Object[] { { 1.0D } };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public MultiplyConstDescriptor() {
/* 113 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 118 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer message) {
/* 130 */     if (!super.validateParameters(args, message))
/* 131 */       return false; 
/* 134 */     int length = ((double[])args.getObjectParameter(0)).length;
/* 135 */     if (length < 1) {
/* 136 */       message.append(getName() + " " + JaiI18N.getString("MultiplyConstDescriptor2"));
/* 138 */       return false;
/*     */     } 
/* 141 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, double[] constants, RenderingHints hints) {
/* 167 */     ParameterBlockJAI pb = new ParameterBlockJAI("MultiplyConst", "rendered");
/* 171 */     pb.setSource("source0", source0);
/* 173 */     pb.setParameter("constants", constants);
/* 175 */     return JAI.create("MultiplyConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, double[] constants, RenderingHints hints) {
/* 200 */     ParameterBlockJAI pb = new ParameterBlockJAI("MultiplyConst", "renderable");
/* 204 */     pb.setSource("source0", source0);
/* 206 */     pb.setParameter("constants", constants);
/* 208 */     return JAI.createRenderable("MultiplyConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MultiplyConstDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */