/*    */ package javax.media.jai.registry;
/*    */ 
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.OperationDescriptor;
/*    */ import javax.media.jai.RegistryMode;
/*    */ import javax.media.jai.RenderableCollectionImageFactory;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class RenderableCollectionRegistryMode extends RegistryMode {
/*    */   public static final String MODE_NAME = "renderableCollection";
/*    */   
/* 30 */   private static Method factoryMethod = null;
/*    */   
/*    */   private static Method getThisFactoryMethod() {
/* 34 */     if (factoryMethod != null)
/* 35 */       return factoryMethod; 
/* 38 */     Class factoryClass = RenderableCollectionImageFactory.class;
/*    */     try {
/* 42 */       Class[] paramTypes = { ParameterBlock.class };
/* 45 */       factoryMethod = factoryClass.getMethod("create", paramTypes);
/* 47 */     } catch (NoSuchMethodException e) {
/* 48 */       ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/* 50 */       String message = JaiI18N.getString("RegistryMode0") + " " + factoryClass.getName() + ".";
/* 52 */       listener.errorOccurred(message, e, RenderableCollectionRegistryMode.class, false);
/*    */     } 
/* 57 */     return factoryMethod;
/*    */   }
/*    */   
/*    */   public RenderableCollectionRegistryMode() {
/* 68 */     super("renderableCollection", OperationDescriptor.class, getThisFactoryMethod().getReturnType(), getThisFactoryMethod(), false, true);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\RenderableCollectionRegistryMode.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */