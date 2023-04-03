/*    */ package javax.media.jai.registry;
/*    */ 
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.awt.image.renderable.RenderContext;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.RegistryMode;
/*    */ import javax.media.jai.remote.RemoteCRIF;
/*    */ import javax.media.jai.remote.RemoteDescriptor;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class RemoteRenderableRegistryMode extends RegistryMode {
/*    */   public static final String MODE_NAME = "remoteRenderable";
/*    */   
/* 30 */   private static Method factoryMethod = null;
/*    */   
/*    */   private static Method getThisFactoryMethod() {
/* 34 */     if (factoryMethod != null)
/* 35 */       return factoryMethod; 
/* 38 */     Class factoryClass = RemoteCRIF.class;
/*    */     try {
/* 42 */       Class[] paramTypes = { String.class, String.class, RenderContext.class, ParameterBlock.class };
/* 48 */       factoryMethod = factoryClass.getMethod("create", paramTypes);
/* 50 */     } catch (NoSuchMethodException e) {
/* 51 */       ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/* 53 */       String message = JaiI18N.getString("RegistryMode0") + " " + factoryClass.getName() + ".";
/* 55 */       listener.errorOccurred(message, e, RemoteRenderableRegistryMode.class, false);
/*    */     } 
/* 60 */     return factoryMethod;
/*    */   }
/*    */   
/*    */   public RemoteRenderableRegistryMode() {
/* 68 */     super("remoteRenderable", RemoteDescriptor.class, getThisFactoryMethod().getReturnType(), getThisFactoryMethod(), false, false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\RemoteRenderableRegistryMode.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */