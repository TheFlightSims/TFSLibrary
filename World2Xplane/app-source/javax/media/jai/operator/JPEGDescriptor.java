/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.codec.SeekableStream;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class JPEGDescriptor extends OperationDescriptorImpl {
/*  62 */   private static final String[][] resources = new String[][] { { "GlobalName", "JPEG" }, { "LocalName", "JPEG" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("JPEGDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/JPEGDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("JPEGDescriptor1") } };
/*     */   
/*  73 */   private static final String[] paramNames = new String[] { "stream" };
/*     */   
/*  78 */   private static final Class[] paramClasses = new Class[] { SeekableStream.class };
/*     */   
/*  83 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/*     */   public JPEGDescriptor() {
/*  89 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(SeekableStream stream, RenderingHints hints) {
/* 112 */     ParameterBlockJAI pb = new ParameterBlockJAI("JPEG", "rendered");
/* 116 */     pb.setParameter("stream", stream);
/* 118 */     return JAI.create("JPEG", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\JPEGDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */