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
/*     */ public class DivideByConstDescriptor extends OperationDescriptorImpl {
/*  84 */   private static final String[][] resources = new String[][] { { "GlobalName", "DivideByConst" }, { "LocalName", "DivideByConst" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("DivideByConstDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/DivideByConstDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("DivideByConstDescriptor1") } };
/*     */   
/* 101 */   private static final Class[] paramClasses = new Class[] { (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/* 102 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/* 102 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/* 106 */   private static final String[] paramNames = new String[] { "constants" };
/*     */   
/* 111 */   private static final Object[] paramDefaults = new Object[] { { 1.0D } };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public DivideByConstDescriptor() {
/* 117 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 122 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer message) {
/* 134 */     if (!super.validateParameters(args, message))
/* 135 */       return false; 
/* 138 */     int length = ((double[])args.getObjectParameter(0)).length;
/* 139 */     if (length < 1) {
/* 140 */       message.append(getName() + " " + JaiI18N.getString("DivideByConstDescriptor2"));
/* 142 */       return false;
/*     */     } 
/* 145 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, double[] constants, RenderingHints hints) {
/* 171 */     ParameterBlockJAI pb = new ParameterBlockJAI("DivideByConst", "rendered");
/* 175 */     pb.setSource("source0", source0);
/* 177 */     pb.setParameter("constants", constants);
/* 179 */     return JAI.create("DivideByConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, double[] constants, RenderingHints hints) {
/* 204 */     ParameterBlockJAI pb = new ParameterBlockJAI("DivideByConst", "renderable");
/* 208 */     pb.setSource("source0", source0);
/* 210 */     pb.setParameter("constants", constants);
/* 212 */     return JAI.createRenderable("DivideByConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\DivideByConstDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */