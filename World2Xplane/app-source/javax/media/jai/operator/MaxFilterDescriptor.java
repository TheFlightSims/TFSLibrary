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
/*     */ public class MaxFilterDescriptor extends OperationDescriptorImpl {
/* 127 */   public static final MaxFilterShape MAX_MASK_SQUARE = new MaxFilterShape("MAX_MASK_SQUARE", 1);
/*     */   
/* 131 */   public static final MaxFilterShape MAX_MASK_PLUS = new MaxFilterShape("MAX_MASK_PLUS", 2);
/*     */   
/* 135 */   public static final MaxFilterShape MAX_MASK_X = new MaxFilterShape("MAX_MASK_X", 3);
/*     */   
/* 139 */   public static final MaxFilterShape MAX_MASK_SQUARE_SEPARABLE = new MaxFilterShape("MAX_MASK_SQUARE_SEPARABLE", 4);
/*     */   
/* 146 */   private static final String[][] resources = new String[][] { { "GlobalName", "MaxFilter" }, { "LocalName", "MaxFilter" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MaxFilterDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jaiapi/javax.media.jai.operator.MaxFilterDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion2") }, { "arg0Desc", JaiI18N.getString("MaxFilterDescriptor1") }, { "arg1Desc", JaiI18N.getString("MaxFilterDescriptor2") } };
/*     */   
/* 158 */   private static final Class[] paramClasses = new Class[] { MaxFilterShape.class, Integer.class };
/*     */   
/* 163 */   private static final String[] paramNames = new String[] { "maskShape", "maskSize" };
/*     */   
/* 168 */   private static final Object[] paramDefaults = new Object[] { MAX_MASK_SQUARE, new Integer(3) };
/*     */   
/*     */   public MaxFilterDescriptor() {
/* 174 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 182 */     if (index == 0)
/* 183 */       return null; 
/* 184 */     if (index == 1)
/* 185 */       return new Integer(1); 
/* 187 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public Number getParamMaxValue(int index) {
/* 196 */     if (index == 0)
/* 197 */       return null; 
/* 198 */     if (index == 1)
/* 199 */       return new Integer(2147483647); 
/* 201 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 212 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 213 */     pg[0] = (PropertyGenerator)new AreaOpPropertyGenerator();
/* 214 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, MaxFilterShape maskShape, Integer maskSize, RenderingHints hints) {
/* 243 */     ParameterBlockJAI pb = new ParameterBlockJAI("MaxFilter", "rendered");
/* 247 */     pb.setSource("source0", source0);
/* 249 */     pb.setParameter("maskShape", maskShape);
/* 250 */     pb.setParameter("maskSize", maskSize);
/* 252 */     return JAI.create("MaxFilter", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MaxFilterDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */