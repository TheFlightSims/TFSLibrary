/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class FormatDescriptor extends OperationDescriptorImpl {
/* 158 */   private static final String[][] resources = new String[][] { { "GlobalName", "Format" }, { "LocalName", "Format" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("FormatDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/FormatDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", "The output data type (from java.awt.image.DataBuffer)." } };
/*     */   
/* 169 */   private static final Class[] paramClasses = new Class[] { Integer.class };
/*     */   
/* 174 */   private static final String[] paramNames = new String[] { "dataType" };
/*     */   
/* 179 */   private static final Object[] paramDefaults = new Object[] { new Integer(0) };
/*     */   
/*     */   public FormatDescriptor() {
/* 185 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public boolean isRenderableSupported() {
/* 190 */     return true;
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 198 */     if (index == 0)
/* 199 */       return new Integer(0); 
/* 201 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public Number getParamMaxValue(int index) {
/* 210 */     if (index == 0)
/* 211 */       return new Integer(5); 
/* 213 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Integer dataType, RenderingHints hints) {
/* 240 */     ParameterBlockJAI pb = new ParameterBlockJAI("Format", "rendered");
/* 244 */     pb.setSource("source0", source0);
/* 246 */     pb.setParameter("dataType", dataType);
/* 248 */     return JAI.create("Format", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(RenderableImage source0, Integer dataType, RenderingHints hints) {
/* 273 */     ParameterBlockJAI pb = new ParameterBlockJAI("Format", "renderable");
/* 277 */     pb.setSource("source0", source0);
/* 279 */     pb.setParameter("dataType", dataType);
/* 281 */     return JAI.createRenderable("Format", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\FormatDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */