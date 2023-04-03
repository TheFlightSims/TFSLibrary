/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.ColorCube;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.KernelJAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class OrderedDitherDescriptor extends OperationDescriptorImpl {
/*  83 */   private static final String[][] resources = new String[][] { { "GlobalName", "OrderedDither" }, { "LocalName", "OrderedDither" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("OrderedDitherDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/OrderedDitherDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("OrderedDitherDescriptor1") }, { "arg1Desc", JaiI18N.getString("OrderedDitherDescriptor2") } };
/*     */   
/*  95 */   private static final String[] paramNames = new String[] { "colorMap", "ditherMask" };
/*     */   
/* 100 */   private static final Class[] paramClasses = new Class[] { ColorCube.class, (array$Ljavax$media$jai$KernelJAI == null) ? (array$Ljavax$media$jai$KernelJAI = class$("[Ljavax.media.jai.KernelJAI;")) : array$Ljavax$media$jai$KernelJAI };
/*     */   
/* 106 */   private static final Object[] paramDefaults = new Object[] { ColorCube.BYTE_496, KernelJAI.DITHER_MASK_443 };
/*     */   
/* 111 */   private static final String[] supportedModes = new String[] { "rendered" };
/*     */   
/*     */   static Class array$Ljavax$media$jai$KernelJAI;
/*     */   
/*     */   private static boolean isValidColorMap(RenderedImage sourceImage, ColorCube colorMap, StringBuffer msg) {
/* 129 */     SampleModel srcSampleModel = sourceImage.getSampleModel();
/* 131 */     if (colorMap.getDataType() != srcSampleModel.getTransferType()) {
/* 132 */       msg.append(JaiI18N.getString("OrderedDitherDescriptor3"));
/* 133 */       return false;
/*     */     } 
/* 134 */     if (colorMap.getNumBands() != srcSampleModel.getNumBands()) {
/* 135 */       msg.append(JaiI18N.getString("OrderedDitherDescriptor4"));
/* 136 */       return false;
/*     */     } 
/* 139 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean isValidDitherMask(RenderedImage sourceImage, KernelJAI[] ditherMask, StringBuffer msg) {
/* 159 */     if (ditherMask.length != sourceImage.getSampleModel().getNumBands()) {
/* 160 */       msg.append(JaiI18N.getString("OrderedDitherDescriptor5"));
/* 161 */       return false;
/*     */     } 
/* 164 */     int maskWidth = ditherMask[0].getWidth();
/* 165 */     int maskHeight = ditherMask[0].getHeight();
/* 166 */     for (int band = 0; band < ditherMask.length; band++) {
/* 167 */       if (ditherMask[band].getWidth() != maskWidth || ditherMask[band].getHeight() != maskHeight) {
/* 169 */         msg.append(JaiI18N.getString("OrderedDitherDescriptor6"));
/* 170 */         return false;
/*     */       } 
/* 172 */       float[] kernelData = ditherMask[band].getKernelData();
/* 173 */       for (int i = 0; i < kernelData.length; i++) {
/* 174 */         if (kernelData[i] < 0.0F || kernelData[i] > 1.0D) {
/* 175 */           msg.append(JaiI18N.getString("OrderedDitherDescriptor7"));
/* 176 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 181 */     return true;
/*     */   }
/*     */   
/*     */   public OrderedDitherDescriptor() {
/* 186 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer msg) {
/* 200 */     if (!super.validateArguments(modeName, args, msg))
/* 201 */       return false; 
/* 204 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 205 */       return true; 
/* 208 */     RenderedImage src = args.getRenderedSource(0);
/* 209 */     ColorCube colorMap = (ColorCube)args.getObjectParameter(0);
/* 210 */     KernelJAI[] ditherMask = (KernelJAI[])args.getObjectParameter(1);
/* 213 */     if (!isValidColorMap(src, colorMap, msg))
/* 214 */       return false; 
/* 218 */     if (!isValidDitherMask(src, ditherMask, msg))
/* 219 */       return false; 
/* 222 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, ColorCube colorMap, KernelJAI[] ditherMask, RenderingHints hints) {
/* 251 */     ParameterBlockJAI pb = new ParameterBlockJAI("OrderedDither", "rendered");
/* 255 */     pb.setSource("source0", source0);
/* 257 */     pb.setParameter("colorMap", colorMap);
/* 258 */     pb.setParameter("ditherMask", ditherMask);
/* 260 */     return JAI.create("OrderedDither", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\OrderedDitherDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */