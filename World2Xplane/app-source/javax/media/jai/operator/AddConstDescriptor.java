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
/*     */ public class AddConstDescriptor extends OperationDescriptorImpl {
/*  79 */   private static final String[][] resources = new String[][] { { "GlobalName", "AddConst" }, { "LocalName", "AddConst" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("AddConstDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/AddConstDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("AddConstDescriptor1") } };
/*     */   
/*  90 */   private static final String[] paramNames = new String[] { "constants" };
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
/* 106 */   private static final Object[] paramDefaults = new Object[] { { 0.0D } };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public AddConstDescriptor() {
/* 112 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 117 */     return true;
/*     */   }
/*     */   
/*     */   protected boolean validateParameters(ParameterBlock args, StringBuffer message) {
/* 129 */     if (!super.validateParameters(args, message))
/* 130 */       return false; 
/* 133 */     int length = ((double[])args.getObjectParameter(0)).length;
/* 134 */     if (length < 1) {
/* 135 */       message.append(getName() + " " + JaiI18N.getString("AddConstDescriptor2"));
/* 137 */       return false;
/*     */     } 
/* 140 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, double[] constants, RenderingHints hints) {
/* 166 */     ParameterBlockJAI pb = new ParameterBlockJAI("AddConst", "rendered");
/* 170 */     pb.setSource("source0", source0);
/* 172 */     pb.setParameter("constants", constants);
/* 174 */     return JAI.create("AddConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, double[] constants, RenderingHints hints) {
/* 199 */     ParameterBlockJAI pb = new ParameterBlockJAI("AddConst", "renderable");
/* 203 */     pb.setSource("source0", source0);
/* 205 */     pb.setParameter("constants", constants);
/* 207 */     return JAI.createRenderable("AddConst", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\AddConstDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */