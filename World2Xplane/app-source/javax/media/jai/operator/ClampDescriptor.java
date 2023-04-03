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
/*     */ public class ClampDescriptor extends OperationDescriptorImpl {
/*  89 */   private static final String[][] resources = new String[][] { { "GlobalName", "Clamp" }, { "LocalName", "Clamp" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ClampDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ClampDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("ClampDescriptor1") }, { "arg1Desc", JaiI18N.getString("ClampDescriptor2") } };
/*     */   
/* 101 */   private static final Class[] paramClasses = new Class[] { (array$D == null) ? (array$D = class$("[D")) : array$D, (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/* 102 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/* 102 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/* 106 */   private static final String[] paramNames = new String[] { "low", "high" };
/*     */   
/* 111 */   private static final Object[] paramDefaults = new Object[] { { 0.0D }, { 255.0D } };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public ClampDescriptor() {
/* 117 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 122 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 135 */     if (!super.validateParameters(args, msg))
/* 136 */       return false; 
/* 139 */     double[] low = (double[])args.getObjectParameter(0);
/* 140 */     double[] high = (double[])args.getObjectParameter(1);
/* 142 */     if (low.length < 1 || high.length < 1) {
/* 143 */       msg.append(getName() + " " + JaiI18N.getString("ClampDescriptor3"));
/* 145 */       return false;
/*     */     } 
/* 148 */     int length = Math.min(low.length, high.length);
/* 149 */     for (int i = 0; i < length; i++) {
/* 150 */       if (low[i] > high[i]) {
/* 151 */         msg.append(getName() + " " + JaiI18N.getString("ClampDescriptor4"));
/* 153 */         return false;
/*     */       } 
/*     */     } 
/* 157 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, double[] low, double[] high, RenderingHints hints) {
/* 186 */     ParameterBlockJAI pb = new ParameterBlockJAI("Clamp", "rendered");
/* 190 */     pb.setSource("source0", source0);
/* 192 */     pb.setParameter("low", low);
/* 193 */     pb.setParameter("high", high);
/* 195 */     return JAI.create("Clamp", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, double[] low, double[] high, RenderingHints hints) {
/* 223 */     ParameterBlockJAI pb = new ParameterBlockJAI("Clamp", "renderable");
/* 227 */     pb.setSource("source0", source0);
/* 229 */     pb.setParameter("low", low);
/* 230 */     pb.setParameter("high", high);
/* 232 */     return JAI.createRenderable("Clamp", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ClampDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */