/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.codec.ImageDecodeParam;
/*     */ import com.sun.media.jai.codec.SeekableStream;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class StreamDescriptor extends OperationDescriptorImpl {
/*  76 */   private static final String[][] resources = new String[][] { { "GlobalName", "Stream" }, { "LocalName", "Stream" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("StreamDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/StreamDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("StreamDescriptor1") }, { "arg1Desc", JaiI18N.getString("StreamDescriptor2") } };
/*     */   
/*  88 */   private static final String[] paramNames = new String[] { "stream", "param" };
/*     */   
/*  93 */   private static final Class[] paramClasses = new Class[] { SeekableStream.class, ImageDecodeParam.class };
/*     */   
/*  99 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, null };
/*     */   
/*     */   public StreamDescriptor() {
/* 105 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(SeekableStream stream, ImageDecodeParam param, RenderingHints hints) {
/* 131 */     ParameterBlockJAI pb = new ParameterBlockJAI("Stream", "rendered");
/* 135 */     pb.setParameter("stream", stream);
/* 136 */     pb.setParameter("param", param);
/* 138 */     return JAI.create("Stream", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\StreamDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */