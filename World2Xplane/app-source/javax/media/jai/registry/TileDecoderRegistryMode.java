/*    */ package javax.media.jai.registry;
/*    */ 
/*    */ import java.io.InputStream;
/*    */ import java.lang.reflect.Method;
/*    */ import javax.media.jai.JAI;
/*    */ import javax.media.jai.RegistryMode;
/*    */ import javax.media.jai.tilecodec.TileCodecDescriptor;
/*    */ import javax.media.jai.tilecodec.TileCodecParameterList;
/*    */ import javax.media.jai.tilecodec.TileDecoderFactory;
/*    */ import javax.media.jai.util.ImagingListener;
/*    */ 
/*    */ public class TileDecoderRegistryMode extends RegistryMode {
/*    */   public static final String MODE_NAME = "tileDecoder";
/*    */   
/* 33 */   private static Method factoryMethod = null;
/*    */   
/*    */   private static Method getThisFactoryMethod() {
/* 37 */     if (factoryMethod != null)
/* 38 */       return factoryMethod; 
/* 41 */     Class factoryClass = TileDecoderFactory.class;
/*    */     try {
/* 44 */       Class[] paramTypes = { InputStream.class, TileCodecParameterList.class };
/* 47 */       factoryMethod = factoryClass.getMethod("createDecoder", paramTypes);
/* 49 */     } catch (NoSuchMethodException e) {
/* 50 */       ImagingListener listener = JAI.getDefaultInstance().getImagingListener();
/* 52 */       String message = JaiI18N.getString("RegistryMode0") + " " + factoryClass.getName() + ".";
/* 54 */       listener.errorOccurred(message, e, TileDecoderRegistryMode.class, false);
/*    */     } 
/* 59 */     return factoryMethod;
/*    */   }
/*    */   
/*    */   public TileDecoderRegistryMode() {
/* 67 */     super("tileDecoder", TileCodecDescriptor.class, getThisFactoryMethod().getReturnType(), getThisFactoryMethod(), true, false);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\TileDecoderRegistryMode.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */