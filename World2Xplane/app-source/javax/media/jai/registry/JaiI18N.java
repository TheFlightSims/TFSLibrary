/*    */ package javax.media.jai.registry;
/*    */ 
/*    */ import com.sun.media.jai.util.PropertyUtil;
/*    */ 
/*    */ class JaiI18N {
/* 17 */   static String packageName = "javax.media.jai.registry";
/*    */   
/*    */   public static String getString(String key) {
/* 20 */     return PropertyUtil.getString(packageName, key);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\registry\JaiI18N.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */