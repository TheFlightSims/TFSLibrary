/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.codec.SeekableStream;
/*     */ import com.sun.media.jai.codec.TIFFDecodeParam;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class TIFFDescriptor extends OperationDescriptorImpl {
/* 150 */   private static final String[][] resources = new String[][] { { "GlobalName", "TIFF" }, { "LocalName", "TIFF" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("TIFFDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/TIFFDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("TIFFDescriptor1") }, { "arg1Desc", JaiI18N.getString("TIFFDescriptor2") }, { "arg2Desc", JaiI18N.getString("TIFFDescriptor3") } };
/*     */   
/* 163 */   private static final String[] paramNames = new String[] { "stream", "param", "page" };
/*     */   
/* 168 */   private static final Class[] paramClasses = new Class[] { SeekableStream.class, TIFFDecodeParam.class, Integer.class };
/*     */   
/* 175 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, null, new Integer(0) };
/*     */   
/*     */   public TIFFDescriptor() {
/* 181 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(SeekableStream stream, TIFFDecodeParam param, Integer page, RenderingHints hints) {
/* 210 */     ParameterBlockJAI pb = new ParameterBlockJAI("TIFF", "rendered");
/* 214 */     pb.setParameter("stream", stream);
/* 215 */     pb.setParameter("param", param);
/* 216 */     pb.setParameter("page", page);
/* 218 */     return JAI.create("TIFF", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\TIFFDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */