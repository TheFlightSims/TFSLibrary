/*    */ package javax.media.jai.registry;
/*    */ 
/*    */ import java.awt.image.renderable.ContextualRenderedImageFactory;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderContext;
/*    */ import java.awt.image.renderable.RenderableImage;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.OperationDescriptor;
/*    */ import javax.media.jai.RegistryMode;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class RenderableRegistryMode extends RegistryMode {
/*    */   public static final String MODE_NAME = "renderable";
/*    */   
/* 31 */   private static Method factoryMethod = null;
/*    */   
/*    */   private static Method getThisFactoryMethod() {
/* 35 */     if (factoryMethod != null)
/* 36 */       return factoryMethod; 
/* 39 */     Class factoryClass = ContextualRenderedImageFactory.class;
/*    */     try {
/* 43 */       Class[] paramTypes = { RenderContext.class, ParameterBlock.class };
/* 47 */       factoryMethod = factoryClass.getMethod("create", paramTypes);
/* 49 */     } catch (NoSuchMethodException e) {
/* 50 */       ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/* 52 */       String message = JaiI18N.getString("RegistryMode0") + " " + factoryClass.getName() + ".";
/* 54 */       listener.errorOccurred(message, e, RenderableRegistryMode.class, false);
/*    */     } 
/* 59 */     return factoryMethod;
/*    */   }
/*    */   
/*    */   public RenderableRegistryMode() {
/* 70 */     super("renderable", OperationDescriptor.class, RenderableImage.class, getThisFactoryMethod(), false, true);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\RenderableRegistryMode.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */