/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ 
/*     */ public class AddConstToCollectionDescriptor extends OperationDescriptorImpl {
/*  73 */   private static final String[][] resources = new String[][] { { "GlobalName", "AddConstToCollection" }, { "LocalName", "AddConstToCollection" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("AddConstToCollectionDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/AddConstToCollectionDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") }, { "arg0Desc", JaiI18N.getString("AddConstToCollectionDescriptor1") } };
/*     */   
/*  84 */   private static final String[] paramNames = new String[] { "constants" };
/*     */   
/*  89 */   private static final Class[] paramClasses = new Class[] { (array$D == null) ? (array$D = class$("[D")) : array$D };
/*     */   
/*     */   static Class class$(String x0) {
/*     */     try {
/*  90 */       return Class.forName(x0);
/*     */     } catch (ClassNotFoundException x1) {
/*  90 */       throw new NoClassDefFoundError(x1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*  94 */   private static final Object[] paramDefaults = new Object[] { { 0.0D } };
/*     */   
/*  98 */   private static final String[] supportedModes = new String[] { "collection" };
/*     */   
/*     */   static Class array$D;
/*     */   
/*     */   public AddConstToCollectionDescriptor() {
/* 104 */     super(resources, supportedModes, 1, paramNames, paramClasses, paramDefaults, null);
/*     */   }
/*     */   
/*     */   public boolean validateArguments(String modeName, ParameterBlock args, StringBuffer msg) {
/* 112 */     if (!super.validateArguments(modeName, args, msg))
/* 113 */       return false; 
/* 116 */     Collection col = (Collection)args.getSource(0);
/* 118 */     if (col.size() < 1) {
/* 119 */       msg.append(getName() + " " + JaiI18N.getString("AddConstToCollectionDescriptor2"));
/* 121 */       return false;
/*     */     } 
/* 124 */     Iterator iter = col.iterator();
/* 125 */     while (iter.hasNext()) {
/* 126 */       Object o = iter.next();
/* 127 */       if (!(o instanceof java.awt.image.RenderedImage)) {
/* 128 */         msg.append(getName() + " " + JaiI18N.getString("AddConstToCollectionDescriptor3"));
/* 130 */         return false;
/*     */       } 
/*     */     } 
/* 134 */     int length = ((double[])args.getObjectParameter(0)).length;
/* 135 */     if (length < 1) {
/* 136 */       msg.append(getName() + " " + JaiI18N.getString("AddConstToCollectionDescriptor4"));
/* 138 */       return false;
/*     */     } 
/* 141 */     return true;
/*     */   }
/*     */   
/*     */   public static Collection createCollection(Collection source0, double[] constants, RenderingHints hints) {
/* 167 */     ParameterBlockJAI pb = new ParameterBlockJAI("AddConstToCollection", "collection");
/* 171 */     pb.setSource("source0", source0);
/* 173 */     pb.setParameter("constants", constants);
/* 175 */     return JAI.createCollection("AddConstToCollection", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\AddConstToCollectionDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */