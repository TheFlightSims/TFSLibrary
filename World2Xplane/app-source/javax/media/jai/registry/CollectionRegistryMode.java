/*    */ package javax.media.jai.registry;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.media.jai.CollectionImageFactory;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.OperationDescriptor;
/*    */ import javax.media.jai.RegistryMode;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class CollectionRegistryMode extends RegistryMode {
/*    */   public static final String MODE_NAME = "collection";
/*    */   
/* 30 */   private static Method factoryMethod = null;
/*    */   
/*    */   private static Method getThisFactoryMethod() {
/* 34 */     if (factoryMethod != null)
/* 35 */       return factoryMethod; 
/* 38 */     Class factoryClass = CollectionImageFactory.class;
/*    */     try {
/* 42 */       Class[] paramTypes = { ParameterBlock.class, RenderingHints.class };
/* 46 */       factoryMethod = factoryClass.getMethod("create", paramTypes);
/* 48 */     } catch (NoSuchMethodException e) {
/* 49 */       ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/* 51 */       String message = JaiI18N.getString("RegistryMode0") + " " + factoryClass.getName() + ".";
/* 53 */       listener.errorOccurred(message, e, CollectionRegistryMode.class, false);
/*    */     } 
/* 58 */     return factoryMethod;
/*    */   }
/*    */   
/*    */   public CollectionRegistryMode() {
/* 67 */     super("collection", OperationDescriptor.class, getThisFactoryMethod().getReturnType(), getThisFactoryMethod(), true, true);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\CollectionRegistryMode.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */