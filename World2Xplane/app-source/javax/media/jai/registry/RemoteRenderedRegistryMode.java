/*    */ package javax.media.jai.registry;
/*    */ 
/*    */ import java.awt.RenderingHints;
/*    */ import java.awt.image.renderable.ParameterBlock;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.RegistryMode;
/*    */ import javax.media.jai.remote.RemoteDescriptor;
/*    */ import javax.media.jai.remote.RemoteRIF;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class RemoteRenderedRegistryMode extends RegistryMode {
/*    */   public static final String MODE_NAME = "remoteRendered";
/*    */   
/* 30 */   private static Method factoryMethod = null;
/*    */   
/*    */   private static Method getThisFactoryMethod() {
/* 34 */     if (factoryMethod != null)
/* 35 */       return factoryMethod; 
/* 38 */     Class factoryClass = RemoteRIF.class;
/*    */     try {
/* 42 */       Class[] paramTypes = { String.class, String.class, ParameterBlock.class, RenderingHints.class };
/* 48 */       factoryMethod = factoryClass.getMethod("create", paramTypes);
/* 49 */     } catch (NoSuchMethodException e) {
/* 50 */       ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/* 52 */       String message = JaiI18N.getString("RegistryMode0") + " " + factoryClass.getName() + ".";
/* 54 */       listener.errorOccurred(message, e, RemoteRenderedRegistryMode.class, false);
/*    */     } 
/* 59 */     return factoryMethod;
/*    */   }
/*    */   
/*    */   public RemoteRenderedRegistryMode() {
/* 67 */     super("remoteRendered", RemoteDescriptor.class, getThisFactoryMethod().getReturnType(), getThisFactoryMethod(), false, false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\RemoteRenderedRegistryMode.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */