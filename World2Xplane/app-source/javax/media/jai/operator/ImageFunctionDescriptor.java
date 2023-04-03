/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.ImageFunction;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class ImageFunctionDescriptor extends OperationDescriptorImpl {
/* 167 */   private static final String[][] resources = new String[][] { 
/* 167 */       { "GlobalName", "ImageFunction" }, { "LocalName", "ImageFunction" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ImageFunctionDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ImageFunctionDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("ImageFunctionDescriptor1") }, { "arg1Desc", JaiI18N.getString("ImageFunctionDescriptor2") }, { "arg2Desc", JaiI18N.getString("ImageFunctionDescriptor3") }, { "arg3Desc", JaiI18N.getString("ImageFunctionDescriptor4") }, 
/* 167 */       { "arg4Desc", JaiI18N.getString("ImageFunctionDescriptor5") }, { "arg5Desc", JaiI18N.getString("ImageFunctionDescriptor6") }, { "arg6Desc", JaiI18N.getString("ImageFunctionDescriptor7") } };
/*     */   
/* 184 */   private static final Class[] paramClasses = new Class[] { ImageFunction.class, Integer.class, Integer.class, Float.class, Float.class, Float.class, Float.class };
/*     */   
/* 192 */   private static final String[] paramNames = new String[] { "function", "width", "height", "xScale", "yScale", "xTrans", "yTrans" };
/*     */   
/* 197 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, NO_PARAMETER_DEFAULT, NO_PARAMETER_DEFAULT, new Float(1.0F), new Float(1.0F), new Float(0.0F), new Float(0.0F) };
/*     */   
/*     */   public ImageFunctionDescriptor() {
/* 205 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 215 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 216 */     pg[0] = (PropertyGenerator)new ImageFunctionPropertyGenerator();
/* 217 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(ImageFunction function, Integer width, Integer height, Float xScale, Float yScale, Float xTrans, Float yTrans, RenderingHints hints) {
/* 258 */     ParameterBlockJAI pb = new ParameterBlockJAI("ImageFunction", "rendered");
/* 262 */     pb.setParameter("function", function);
/* 263 */     pb.setParameter("width", width);
/* 264 */     pb.setParameter("height", height);
/* 265 */     pb.setParameter("xScale", xScale);
/* 266 */     pb.setParameter("yScale", yScale);
/* 267 */     pb.setParameter("xTrans", xTrans);
/* 268 */     pb.setParameter("yTrans", yTrans);
/* 270 */     return JAI.create("ImageFunction", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ImageFunctionDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */