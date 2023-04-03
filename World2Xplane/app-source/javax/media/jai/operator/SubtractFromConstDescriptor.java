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
/*     */ public class SubtractFromConstDescriptor extends OperationDescriptorImpl {
/*  81 */   private static final String[][] resources = new String[][] { { "GlobalName", "SubtractFromConst" }, { "LocalName", "SubtractFromConst" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("SubtractFromConstDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/SubtractFromConstDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("SubtractFromConstDescriptor1") } };
/*     */   
/*  98 */   private static final Class[] paramClasses = new Class[] { (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/*  99 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/*  99 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/* 103 */   private static final String[] paramNames = new String[] { "constants" };
/*     */   
/* 108 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public SubtractFromConstDescriptor() {
/* 114 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 119 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer message) {
/* 131 */     if (!super.validateParameters(args, message))
/* 132 */       return false; 
/* 135 */     int length = ((double[])args.getObjectParameter(0)).length;
/* 136 */     if (length < 1) {
/* 137 */       message.append(getName() + " " + JaiI18N.getString("SubtractFromConstDescriptor2"));
/* 139 */       return false;
/*     */     } 
/* 142 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, double[] constants, RenderingHints hints) {
/* 168 */     ParameterBlockJAI pb = new ParameterBlockJAI("SubtractFromConst", "rendered");
/* 172 */     pb.setSource("source0", source0);
/* 174 */     pb.setParameter("constants", constants);
/* 176 */     return JAI.create("SubtractFromConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, double[] constants, RenderingHints hints) {
/* 201 */     ParameterBlockJAI pb = new ParameterBlockJAI("SubtractFromConst", "renderable");
/* 205 */     pb.setSource("source0", source0);
/* 207 */     pb.setParameter("constants", constants);
/* 209 */     return JAI.createRenderable("SubtractFromConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\SubtractFromConstDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */