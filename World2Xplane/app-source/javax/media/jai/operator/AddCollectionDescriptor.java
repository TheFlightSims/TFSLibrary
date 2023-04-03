/*     */ package javax.media.jai.operator;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.renderable.ParameterBlock;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import javax.media.jai.JAI;
/*     */ import javax.media.jai.OperationDescriptorImpl;
/*     */ import javax.media.jai.ParameterBlockJAI;
/*     */ import javax.media.jai.ParameterListDescriptor;
/*     */ import javax.media.jai.RenderableOp;
/*     */ import javax.media.jai.RenderedOp;
/*     */ 
/*     */ public class AddCollectionDescriptor extends OperationDescriptorImpl {
/*  87 */   private static final String[][] resources = new String[][] { { "GlobalName", "AddCollection" }, { "LocalName", "AddCollection" }, { "Vendor", "com.sun.media.jai" }, { "Description", JaiI18N.getString("AddCollectionDescriptor0") }, { "DocURL", "http://java.sun.com/products/java-media/jai/forDevelopers/jai-apidocs/javax/media/jai/operator/AddCollectionDescriptor.html" }, { "Version", JaiI18N.getString("DescriptorVersion") } };
/*     */   
/*  97 */   private static final Class[][] sourceClasses = new Class[][] { { Collection.class }, { Collection.class } };
/*     */   
/* 102 */   private static final String[] supportedModes = new String[] { "rendered", "renderable" };
/*     */   
/*     */   public AddCollectionDescriptor() {
/* 109 */     super(resources, supportedModes, null, sourceClasses, (ParameterListDescriptor)null);
/*     */   }
/*     */   
/*     */   protected boolean validateSources(String modeName, ParameterBlock args, StringBuffer msg) {
/* 117 */     if (!super.validateSources(modeName, args, msg))
/* 118 */       return false; 
/* 121 */     Collection col = (Collection)args.getSource(0);
/* 123 */     if (col.size() < 2) {
/* 124 */       msg.append(getName() + " " + JaiI18N.getString("AddCollectionDescriptor1"));
/* 126 */       return false;
/*     */     } 
/* 129 */     Iterator iter = col.iterator();
/* 130 */     if (modeName.equalsIgnoreCase("rendered")) {
/* 131 */       while (iter.hasNext()) {
/* 132 */         Object o = iter.next();
/* 133 */         if (!(o instanceof java.awt.image.RenderedImage)) {
/* 134 */           msg.append(getName() + " " + JaiI18N.getString("AddCollectionDescriptor2"));
/* 136 */           return false;
/*     */         } 
/*     */       } 
/* 139 */     } else if (modeName.equalsIgnoreCase("renderable")) {
/* 140 */       while (iter.hasNext()) {
/* 141 */         Object o = iter.next();
/* 142 */         if (!(o instanceof java.awt.image.renderable.RenderableImage)) {
/* 143 */           msg.append(getName() + " " + JaiI18N.getString("AddCollectionDescriptor3"));
/* 145 */           return false;
/*     */         } 
/*     */       } 
/*     */     } 
/* 150 */     return true;
/*     */   }
/*     */   
/*     */   public static RenderedOp create(Collection source0, RenderingHints hints) {
/* 173 */     ParameterBlockJAI pb = new ParameterBlockJAI("AddCollection", "rendered");
/* 177 */     pb.setSource("source0", source0);
/* 179 */     return JAI.create("AddCollection", (ParameterBlock)pb, hints);
/*     */   }
/*     */   
/*     */   public static RenderableOp createRenderable(Collection source0, RenderingHints hints) {
/* 201 */     ParameterBlockJAI pb = new ParameterBlockJAI("AddCollection", "renderable");
/* 205 */     pb.setSource("source0", source0);
/* 207 */     return JAI.createRenderable("AddCollection", (ParameterBlock)pb, hints);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\operator\AddCollectionDescriptor.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */