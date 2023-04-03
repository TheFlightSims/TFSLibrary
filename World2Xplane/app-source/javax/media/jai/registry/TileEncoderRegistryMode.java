/*    */ package javax.media.jai.registry;
/*    */ 
/*    */ import java.awt.image.SampleModel;
/*    */ import java.io.OutputStream;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.RegistryMode;
/*    */ import javax.media.jai.tilecodec.TileCodecDescriptor;
/*    */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*    */ import javax.media.jai.tilecodec.TileEncoderFactory;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class TileEncoderRegistryMode extends RegistryMode {
/*    */   public static final String MODE_NAME = "tileEncoder";
/*    */   
/* 33 */   private static Method factoryMethod = null;
/*    */   
/*    */   private static Method getThisFactoryMethod() {
/* 37 */     if (factoryMethod != null)
/* 38 */       return factoryMethod; 
/* 41 */     Class factoryClass = TileEncoderFactory.class;
/*    */     try {
/* 44 */       Class[] paramTypes = { OutputStream.class, TileCodecParameterList.class, SampleModel.class };
/* 48 */       factoryMethod = factoryClass.getMethod("createEncoder", paramTypes);
/* 50 */     } catch (NoSuchMethodException e) {
/* 51 */       ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/* 53 */       String message = JaiI18N.getString("RegistryMode0") + " " + factoryClass.getName() + ".";
/* 55 */       listener.errorOccurred(message, e, TileEncoderRegistryMode.class, false);
/*    */     } 
/* 60 */     return factoryMethod;
/*    */   }
/*    */   
/*    */   public TileEncoderRegistryMode() {
/* 68 */     super("tileEncoder", TileCodecDescriptor.class, getThisFactoryMethod().getReturnType(), getThisFactoryMethod(), true, false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\TileEncoderRegistryMode.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */