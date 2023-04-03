/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.codec.ImageCodec;
/*     */ import com.sun.media.jai.codec.ImageEncodeParam;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.io.OutputStream;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class EncodeDescriptor extends OperationDescriptorImpl {
/*  80 */   private static final String[][] resources = new String[][] { { "GlobalName", "Encode" }, { "LocalName", "Encode" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("EncodeDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/EncodeDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("EncodeDescriptor1") }, { "arg1Desc", JaiI18N.getString("EncodeDescriptor2") }, { "arg2Desc", JaiI18N.getString("EncodeDescriptor3") } };
/*     */   
/*  93 */   private static final String[] paramNames = new String[] { "stream", "format", "param" };
/*     */   
/*  98 */   private static final Class[] paramClasses = new Class[] { OutputStream.class, String.class, ImageEncodeParam.class };
/*     */   
/* 105 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, "tiff", null };
/*     */   
/* 109 */   private static final String[] supportedModes = new String[] { "rendered" };
/*     */   
/*     */   public EncodeDescriptor() {
/* 115 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer msg) {
/* 131 */     if (!modeName.equalsIgnoreCase("rendered"))
/* 132 */       return true; 
/* 135 */     if (args.getNumParameters() < 3) {
/* 136 */       args = (ParameterBlock)args.clone();
/* 137 */       args.set((Object)null, 2);
/*     */     } 
/* 140 */     if (!super.validateArguments(modeName, args, msg))
/* 141 */       return false; 
/* 145 */     String format = (String)args.getObjectParameter(1);
/* 148 */     ImageCodec codec = ImageCodec.getCodec(format);
/* 151 */     if (codec == null) {
/* 152 */       msg.append(getName() + " " + JaiI18N.getString("EncodeDescriptor4"));
/* 154 */       return false;
/*     */     } 
/* 158 */     ImageEncodeParam param = (ImageEncodeParam)args.getObjectParameter(2);
/* 161 */     RenderedImage src = args.getRenderedSource(0);
/* 164 */     if (!codec.canEncodeImage(src, param)) {
/* 165 */       msg.append(getName() + " " + JaiI18N.getString("EncodeDescriptor5"));
/* 167 */       return false;
/*     */     } 
/* 170 */     return true;
/*     */   }
/*     */   
/*     */   public boolean isImmediate() {
/* 180 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, OutputStream stream, String format, ImageEncodeParam param, RenderingHints hints) {
/* 212 */     ParameterBlockJAI pb = new ParameterBlockJAI("Encode", "rendered");
/* 216 */     pb.setSource("source0", source0);
/* 218 */     pb.setParameter("stream", stream);
/* 219 */     pb.setParameter("format", format);
/* 220 */     pb.setParameter("param", param);
/* 222 */     return JAI.create("Encode", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\EncodeDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */