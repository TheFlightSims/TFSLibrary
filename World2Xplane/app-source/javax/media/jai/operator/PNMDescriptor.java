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
/*     */ public class PNMDescriptor extends OperationDescriptorImpl {
/*  65 */   private static final String[][] resources = new String[][] { { "GlobalName", "PNM" }, { "LocalName", "PNM" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("PNMDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/PNMDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("PNMDescriptor1") } };
/*     */   
/*  76 */   private static final String[] paramNames = new String[] { "stream" };
/*     */   
/*  81 */   private static final Class[] paramClasses = new Class[] { SeekableStream.class };
/*     */   
/*  86 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/*     */   public PNMDescriptor() {
/*  92 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(SeekableStream stream, RenderingHints hints) {
/* 115 */     ParameterBlockJAI pb = new ParameterBlockJAI("PNM", "rendered");
/* 119 */     pb.setParameter("stream", stream);
/* 121 */     return JAI.create("PNM", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\PNMDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */