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
/*     */ public class ThresholdDescriptor extends OperationDescriptorImpl {
/*  90 */   private static final String[][] resources = new String[][] { { "GlobalName", "Threshold" }, { "LocalName", "Threshold" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ThresholdDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ThresholdDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("ThresholdDescriptor1") }, { "arg1Desc", JaiI18N.getString("ThresholdDescriptor2") }, { "arg2Desc", JaiI18N.getString("ThresholdDescriptor3") } };
/*     */   
/* 103 */   private static final String[] paramNames = new String[] { "low", "high", "constants" };
/*     */   
/* 108 */   private static final Class[] paramClasses = new Class[] { (array$D == null) ? (array$D = class$("[D")) : array$D, (array$D == null) ? (array$D = class$("[D")) : array$D, (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/* 109 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/* 109 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/* 113 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, NO_PARAMETER_DEFAULT, NO_PARAMETER_DEFAULT };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public ThresholdDescriptor() {
/* 119 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 124 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 130 */     int numParams = args.getNumParameters();
/* 131 */     if (numParams < 3) {
/* 132 */       msg.append(getName() + " " + JaiI18N.getString("ThresholdDescriptor4"));
/* 134 */       return false;
/*     */     } 
/* 137 */     for (int i = 0; i < 3; i++) {
/* 138 */       Object p = args.getObjectParameter(i);
/* 140 */       if (p == null) {
/* 141 */         msg.append(getName() + " " + JaiI18N.getString("ThresholdDescriptor5"));
/* 143 */         return false;
/*     */       } 
/* 146 */       if (!(p instanceof double[])) {
/* 147 */         msg.append(getName() + " " + JaiI18N.getString("ThresholdDescriptor6"));
/* 149 */         return false;
/*     */       } 
/* 152 */       if (((double[])p).length < 1) {
/* 153 */         msg.append(getName() + " " + JaiI18N.getString("ThresholdDescriptor7"));
/* 155 */         return false;
/*     */       } 
/*     */     } 
/* 159 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, double[] low, double[] high, double[] constants, RenderingHints hints) {
/* 191 */     ParameterBlockJAI pb = new ParameterBlockJAI("Threshold", "rendered");
/* 195 */     pb.setSource("source0", source0);
/* 197 */     pb.setParameter("low", low);
/* 198 */     pb.setParameter("high", high);
/* 199 */     pb.setParameter("constants", constants);
/* 201 */     return JAI.create("Threshold", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, double[] low, double[] high, double[] constants, RenderingHints hints) {
/* 232 */     ParameterBlockJAI pb = new ParameterBlockJAI("Threshold", "renderable");
/* 236 */     pb.setSource("source0", source0);
/* 238 */     pb.setParameter("low", low);
/* 239 */     pb.setParameter("high", high);
/* 240 */     pb.setParameter("constants", constants);
/* 242 */     return JAI.createRenderable("Threshold", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ThresholdDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */