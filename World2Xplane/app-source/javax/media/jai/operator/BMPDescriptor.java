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
/*     */ public class BMPDescriptor extends OperationDescriptorImpl {
/*  69 */   private static final String[][] resources = new String[][] { { "GlobalName", "BMP" }, { "LocalName", "BMP" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("BMPDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/BMPDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("BMPDescriptor1") } };
/*     */   
/*  80 */   private static final String[] paramNames = new String[] { "stream" };
/*     */   
/*  85 */   private static final Class[] paramClasses = new Class[] { SeekableStream.class };
/*     */   
/*  90 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT };
/*     */   
/*     */   public BMPDescriptor() {
/*  96 */     super(resources, 0, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public static RenderedOp create(SeekableStream stream, RenderingHints hints) {
/* 119 */     ParameterBlockJAI pb = new ParameterBlockJAI("BMP", "rendered");
/* 123 */     pb.setParameter("stream", stream);
/* 125 */     return JAI.create("BMP", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\BMPDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */