/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import com.sun.media.jai.util.AreaOpPropertyGenerator;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.PropertyGenerator;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class MedianFilterDescriptor extends OperationDescriptorImpl {
/* 125 */   public static final MedianFilterShape MEDIAN_MASK_SQUARE = new MedianFilterShape("MEDIAN_MASK_SQUARE", 1);
/*     */   
/* 129 */   public static final MedianFilterShape MEDIAN_MASK_PLUS = new MedianFilterShape("MEDIAN_MASK_PLUS", 2);
/*     */   
/* 133 */   public static final MedianFilterShape MEDIAN_MASK_X = new MedianFilterShape("MEDIAN_MASK_X", 3);
/*     */   
/* 137 */   public static final MedianFilterShape MEDIAN_MASK_SQUARE_SEPARABLE = new MedianFilterShape("MEDIAN_MASK_SQUARE_SEPARABLE", 4);
/*     */   
/* 144 */   private static final String[][] resources = new String[][] { { "GlobalName", "MedianFilter" }, { "LocalName", "MedianFilter" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MedianFilterDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jaiapi/javax.media.jai.operator.MedianFilterDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion2") }, { "arg0Desc", JaiI18N.getString("MedianFilterDescriptor1") }, { "arg1Desc", JaiI18N.getString("MedianFilterDescriptor2") } };
/*     */   
/* 156 */   private static final Class[] paramClasses = new Class[] { MedianFilterShape.class, Integer.class };
/*     */   
/* 161 */   private static final String[] paramNames = new String[] { "maskShape", "maskSize" };
/*     */   
/* 166 */   private static final Object[] paramDefaults = new Object[] { MEDIAN_MASK_SQUARE, new Integer(3) };
/*     */   
/*     */   public MedianFilterDescriptor() {
/* 172 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 180 */     if (index == 0)
/* 181 */       return null; 
/* 182 */     if (index == 1)
/* 183 */       return new Integer(1); 
/* 185 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public Number getParamMaxValue(int index) {
/* 194 */     if (index == 0)
/* 195 */       return null; 
/* 196 */     if (index == 1)
/* 197 */       return new Integer(2147483647); 
/* 199 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 210 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 211 */     pg[0] = (PropertyGenerator)new AreaOpPropertyGenerator();
/* 212 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, MedianFilterShape maskShape, Integer maskSize, RenderingHints hints) {
/* 241 */     ParameterBlockJAI pb = new ParameterBlockJAI("MedianFilter", "rendered");
/* 245 */     pb.setSource("source0", source0);
/* 247 */     pb.setParameter("maskShape", maskShape);
/* 248 */     pb.setParameter("maskSize", maskSize);
/* 250 */     return JAI.create("MedianFilter", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MedianFilterDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */