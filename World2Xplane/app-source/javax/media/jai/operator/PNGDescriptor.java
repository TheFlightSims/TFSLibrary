/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.codec.PNGDecodeParam;
/*     */ import com.sun.media.jai.codec.SeekableStream;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class PNGDescriptor extends OperationDescriptorImpl {
/* 135 */   private static final String[][] resources = new String[][] { { "GlobalName", "PNG" }, { "LocalName", "PNG" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("PNGDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/PNGDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("PNGDescriptor1") }, { "arg1Desc", JaiI18N.getString("PNGDescriptor2") } };
/*     */   
/* 147 */   private static final String[] paramNames = new String[] { "stream", "param" };
/*     */   
/* 152 */   private static final Class[] paramClasses = new Class[] { SeekableStream.class, PNGDecodeParam.class };
/*     */   
/* 158 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, null };
/*     */   
/*     */   public PNGDescriptor() {
/* 164 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(SeekableStream stream, PNGDecodeParam param, RenderingHints hints) {
/* 190 */     ParameterBlockJAI pb = new ParameterBlockJAI("PNG", "rendered");
/* 194 */     pb.setParameter("stream", stream);
/* 195 */     pb.setParameter("param", param);
/* 197 */     return JAI.create("PNG", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\PNGDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */