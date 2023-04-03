/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class BandCombineDescriptor extends OperationDescriptorImpl {
/*  95 */   private static final String[][] resources = new String[][] { { "GlobalName", "BandCombine" }, { "LocalName", "BandCombine" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("BandCombineDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/BandCombineDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("BandCombineDescriptor1") } };
/*     */   
/* 106 */   private static final Class[] paramClasses = new Class[] { (array$$D == null) ? (array$$D = class$("[[D")) : array$$D };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/* 107 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/* 107 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/* 111 */   private static final String[] paramNames = new String[] { "matrix" };
/*     */   
/* 116 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/* 120 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   static Class array$$D;
/*     */   
/*     */   public BandCombineDescriptor() {
/* 127 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer message) {
/* 144 */     if (!super.validateArguments(modeName, args, message))
/* 145 */       return false; 
/* 148 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 149 */       return true; 
/* 151 */     RenderedImage src = args.getRenderedSource(0);
/* 153 */     double[][] matrix = (double[][])args.getObjectParameter(0);
/* 154 */     SampleModel sm = src.getSampleModel();
/* 155 */     int rowLength = sm.getNumBands() + 1;
/* 157 */     if (matrix.length < 1) {
/* 158 */       message.append(getName() + ": " + JaiI18N.getString("BandCombineDescriptor2"));
/* 160 */       return false;
/*     */     } 
/* 163 */     for (int i = 0; i < matrix.length; i++) {
/* 164 */       if ((matrix[i]).length != rowLength) {
/* 165 */         message.append(getName() + ": " + JaiI18N.getString("BandCombineDescriptor2"));
/* 167 */         return false;
/*     */       } 
/*     */     } 
/* 171 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, double[][] matrix, RenderingHints hints) {
/* 197 */     ParameterBlockJAI pb = new ParameterBlockJAI("BandCombine", "rendered");
/* 201 */     pb.setSource("source0", source0);
/* 203 */     pb.setParameter("matrix", matrix);
/* 205 */     return JAI.create("BandCombine", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, double[][] matrix, RenderingHints hints) {
/* 230 */     ParameterBlockJAI pb = new ParameterBlockJAI("BandCombine", "renderable");
/* 234 */     pb.setSource("source0", source0);
/* 236 */     pb.setParameter("matrix", matrix);
/* 238 */     return JAI.createRenderable("BandCombine", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\BandCombineDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */