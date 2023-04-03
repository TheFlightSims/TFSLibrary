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
/*     */ public class MinFilterDescriptor extends OperationDescriptorImpl {
/* 126 */   public static final MinFilterShape MIN_MASK_SQUARE = new MinFilterShape("MIN_MASK_SQUARE", 1);
/*     */   
/* 130 */   public static final MinFilterShape MIN_MASK_PLUS = new MinFilterShape("MIN_MASK_PLUS", 2);
/*     */   
/* 134 */   public static final MinFilterShape MIN_MASK_X = new MinFilterShape("MIN_MASK_X", 3);
/*     */   
/* 138 */   public static final MinFilterShape MIN_MASK_SQUARE_SEPARABLE = new MinFilterShape("MIN_MASK_SQUARE_SEPARABLE", 4);
/*     */   
/* 145 */   private static final String[][] resources = new String[][] { { "GlobalName", "MinFilter" }, { "LocalName", "MinFilter" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("MinFilterDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jaiapi/javax.media.jai.operator.MinFilterDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion2") }, { "arg0Desc", JaiI18N.getString("MinFilterDescriptor1") }, { "arg1Desc", JaiI18N.getString("MinFilterDescriptor2") } };
/*     */   
/* 157 */   private static final Class[] paramClasses = new Class[] { MinFilterShape.class, Integer.class };
/*     */   
/* 162 */   private static final String[] paramNames = new String[] { "maskShape", "maskSize" };
/*     */   
/* 167 */   private static final Object[] paramDefaults = new Object[] { MIN_MASK_SQUARE, new Integer(3) };
/*     */   
/*     */   public MinFilterDescriptor() {
/* 173 */     super(resources, 1, paramClasses, paramNames, paramDefaults);
/*     */   }
/*     */   
/*     */   public Number getParamMinValue(int index) {
/* 181 */     if (index == 0)
/* 182 */       return null; 
/* 183 */     if (index == 1)
/* 184 */       return new Integer(1); 
/* 186 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public Number getParamMaxValue(int index) {
/* 195 */     if (index == 0)
/* 196 */       return null; 
/* 197 */     if (index == 1)
/* 198 */       return new Integer(2147483647); 
/* 200 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public PropertyGenerator[] getPropertyGenerators() {
/* 211 */     PropertyGenerator[] pg = new PropertyGenerator[1];
/* 212 */     pg[0] = (PropertyGenerator)new AreaOpPropertyGenerator();
/* 213 */     return pg;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(RenderedImage source0, MinFilterShape maskShape, Integer maskSize, RenderingHints hints) {
/* 242 */     ParameterBlockJAI pb = new ParameterBlockJAI("MinFilter", "rendered");
/* 246 */     pb.setSource("source0", source0);
/* 248 */     pb.setParameter("maskShape", maskShape);
/* 249 */     pb.setParameter("maskSize", maskSize);
/* 251 */     return JAI.create("MinFilter", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\MinFilterDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */