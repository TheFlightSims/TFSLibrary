/*    */ package javax.media.jai;
/*    */ 
/*    */ import com.sun.media.jai.util.PropertyUtil;
/*    */ import java.text.MessageFormat;
/*    */ import java.util.Locale;
/*    */ 
/*    */ class JaiI18N {
/* 19 */   static String packageName = "javax.media.jai";
/*    */   
/*    */   public static String getString(String key) {
/* 22 */     return PropertyUtil.getString(packageName, key);
/*    */   }
/*    */   
/*    */   public static String formatMsg(String key, Object[] args) {
/* 26 */     MessageFormat mf = new MessageFormat(getString(key));
/* 27 */     mf.setLocale(Locale.getDefault());
/* 29 */     return mf.format(args);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\javax\media\jai\JaiI18N.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */