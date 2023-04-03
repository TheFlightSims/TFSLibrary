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
/*     */ public class RescaleDescriptor extends OperationDescriptorImpl {
/*  86 */   private static final String[][] resources = new String[][] { { "GlobalName", "Rescale" }, { "LocalName", "Rescale" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("RescaleDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/RescaleDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("RescaleDescriptor1") }, { "arg1Desc", JaiI18N.getString("RescaleDescriptor2") } };
/*     */   
/*  98 */   private static final Class[] paramClasses = new Class[] { (array$D == null) ? (array$D = class$("[D")) : array$D, (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/*  99 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/*  99 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/* 103 */   private static final String[] paramNames = new String[] { "constants", "offsets" };
/*     */   
/* 108 */   private static final Object[] paramDefaults = new Object[] { { 1.0D }, { 0.0D } };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public RescaleDescriptor() {
/* 114 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 119 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer msg) {
/* 131 */     if (!super.validateParameters(args, msg))
/* 132 */       return false; 
/* 135 */     int constantsLength = ((double[])args.getObjectParameter(0)).length;
/* 136 */     int offsetsLength = ((double[])args.getObjectParameter(1)).length;
/* 138 */     if (constantsLength < 1) {
/* 139 */       msg.append(getName() + " " + JaiI18N.getString("RescaleDescriptor3"));
/* 141 */       return false;
/*     */     } 
/* 144 */     if (offsetsLength < 1) {
/* 145 */       msg.append(getName() + ": " + JaiI18N.getString("RescaleDescriptor4"));
/* 147 */       return false;
/*     */     } 
/* 150 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, double[] constants, double[] offsets, RenderingHints hints) {
/* 179 */     ParameterBlockJAI pb = new ParameterBlockJAI("Rescale", "rendered");
/* 183 */     pb.setSource("source0", source0);
/* 185 */     pb.setParameter("constants", constants);
/* 186 */     pb.setParameter("offsets", offsets);
/* 188 */     return JAI.create("Rescale", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, double[] constants, double[] offsets, RenderingHints hints) {
/* 216 */     ParameterBlockJAI pb = new ParameterBlockJAI("Rescale", "renderable");
/* 220 */     pb.setSource("source0", source0);
/* 222 */     pb.setParameter("constants", constants);
/* 223 */     pb.setParameter("offsets", offsets);
/* 225 */     return JAI.createRenderable("Rescale", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\RescaleDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */