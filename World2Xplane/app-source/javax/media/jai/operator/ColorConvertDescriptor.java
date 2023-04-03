/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class ColorConvertDescriptor extends OperationDescriptorImpl {
/*  89 */   private static final String[][] resources = new String[][] { { "GlobalName", "ColorConvert" }, { "LocalName", "ColorConvert" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("ColorConvertDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/ColorConvertDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion2") }, { "arg0Desc", JaiI18N.getString("ColorConvertDescriptor1") } };
/*     */   
/* 102 */   private static final Class[] paramClasses = new Class[] { ColorModel.class };
/*     */   
/* 107 */   private static final String[] paramNames = new String[] { "colorModel" };
/*     */   
/* 112 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/*     */   public ColorConvertDescriptor() {
/* 118 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 123 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, ColorModel colorModel, RenderingHints hints) {
/* 149 */     ParameterBlockJAI pb = new ParameterBlockJAI("ColorConvert", "rendered");
/* 153 */     pb.setSource("source0", source0);
/* 155 */     pb.setParameter("colorModel", colorModel);
/* 157 */     return JAI.create("ColorConvert", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, ColorModel colorModel, RenderingHints hints) {
/* 182 */     ParameterBlockJAI pb = new ParameterBlockJAI("ColorConvert", "renderable");
/* 186 */     pb.setSource("source0", source0);
/* 188 */     pb.setParameter("colorModel", colorModel);
/* 190 */     return JAI.createRenderable("ColorConvert", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\ColorConvertDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */