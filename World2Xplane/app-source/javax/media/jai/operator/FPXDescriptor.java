/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.codec.FPXDecodeParam;
/*     */ import com.sun.media.jai.codec.SeekableStream;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class FPXDescriptor extends OperationDescriptorImpl {
/*  68 */   public static final Integer MAX_RESOLUTION = new Integer(-1);
/*     */   
/*  74 */   private static final String[][] resources = new String[][] { { "GlobalName", "FPX" }, { "LocalName", "FPX" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("FPXDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/FPXDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("FPXDescriptor1") }, { "arg1Desc", JaiI18N.getString("FPXDescriptor2") } };
/*     */   
/*  86 */   private static final String[] paramNames = new String[] { "stream", "param" };
/*     */   
/*  91 */   private static final Class[] paramClasses = new Class[] { SeekableStream.class, FPXDecodeParam.class };
/*     */   
/*  97 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, null };
/*     */   
/*     */   public FPXDescriptor() {
/* 103 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(SeekableStream stream, FPXDecodeParam param, RenderingHints hints) {
/* 129 */     ParameterBlockJAI pb = new ParameterBlockJAI("FPX", "rendered");
/* 133 */     pb.setParameter("stream", stream);
/* 134 */     pb.setParameter("param", param);
/* 136 */     return JAI.create("FPX", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\FPXDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */