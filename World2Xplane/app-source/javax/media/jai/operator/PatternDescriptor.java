/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class PatternDescriptor extends OperationDescriptorImpl {
/*  64 */   private static final String[][] resources = new String[][] { { "GlobalName", "Pattern" }, { "LocalName", "Pattern" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("PatternDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/PatternDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("PatternDescriptor1") }, { "arg1Desc", JaiI18N.getString("PatternDescriptor2") } };
/*     */   
/*  76 */   private static final Class[] paramClasses = new Class[] { Integer.class, Integer.class };
/*     */   
/*  82 */   private static final String[] paramNames = new String[] { "width", "height" };
/*     */   
/*  87 */   private static final Object[] paramDefaults = new Object[] { NO_PARAMETER_DEFAULT, NO_PARAMETER_DEFAULT };
/*     */   
/*     */   public PatternDescriptor() {
/*  93 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/*  97 */     if (index == 0 || index == 1)
/*  98 */       return new Integer(1); 
/* 100 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, Integer width, Integer height, RenderingHints hints) {
/* 130 */     ParameterBlockJAI pb = new ParameterBlockJAI("Pattern", "rendered");
/* 134 */     pb.setSource("source0", source0);
/* 136 */     pb.setParameter("width", width);
/* 137 */     pb.setParameter("height", height);
/* 139 */     return JAI.create("Pattern", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\PatternDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */