/*    */ package scala.runtime;
/*    */ 
/*    */ public final class Statics {
/*    */   public static int mix(int hash, int data) {
/*  8 */     int h = mixLast(hash, data);
/*  9 */     h = Integer.rotateLeft(h, 13);
/* 10 */     return h * 5 + -430675100;
/*    */   }
/*    */   
/*    */   public static int mixLast(int hash, int data) {
/* 14 */     int k = data;
/* 16 */     k *= -862048943;
/* 17 */     k = Integer.rotateLeft(k, 15);
/* 18 */     k *= 461845907;
/* 20 */     return hash ^ k;
/*    */   }
/*    */   
/*    */   public static int finalizeHash(int hash, int length) {
/* 24 */     return avalanche(hash ^ length);
/*    */   }
/*    */   
/*    */   public static int avalanche(int h) {
/* 29 */     h ^= h >>> 16;
/* 30 */     h *= -2048144789;
/* 31 */     h ^= h >>> 13;
/* 32 */     h *= -1028477387;
/* 33 */     h ^= h >>> 16;
/* 35 */     return h;
/*    */   }
/*    */   
/*    */   public static int longHash(long lv) {
/* 39 */     if ((int)lv == lv)
/* 40 */       return (int)lv; 
/* 42 */     return (int)(lv ^ lv >>> 32L);
/*    */   }
/*    */   
/*    */   public static int doubleHash(double dv) {
/* 46 */     int iv = (int)dv;
/* 47 */     if (iv == dv)
/* 48 */       return iv; 
/* 50 */     float fv = (float)dv;
/* 51 */     if (fv == dv)
/* 52 */       return Float.floatToIntBits(fv); 
/* 54 */     long lv = (long)dv;
/* 55 */     if (lv == dv)
/* 56 */       return (int)lv; 
/* 58 */     lv = Double.doubleToLongBits(dv);
/* 59 */     return (int)(lv ^ lv >>> 32L);
/*    */   }
/*    */   
/*    */   public static int floatHash(float fv) {
/* 63 */     int iv = (int)fv;
/* 64 */     if (iv == fv)
/* 65 */       return iv; 
/* 67 */     long lv = (long)fv;
/* 68 */     if ((float)lv == fv)
/* 69 */       return (int)(lv ^ lv >>> 32L); 
/* 71 */     return Float.floatToIntBits(fv);
/*    */   }
/*    */   
/*    */   public static int anyHash(Object x) {
/* 75 */     if (x == null)
/* 76 */       return 0; 
/* 78 */     if (x instanceof Long)
/* 79 */       return longHash(((Long)x).longValue()); 
/* 81 */     if (x instanceof Double)
/* 82 */       return doubleHash(((Double)x).doubleValue()); 
/* 84 */     if (x instanceof Float)
/* 85 */       return floatHash(((Float)x).floatValue()); 
/* 87 */     return x.hashCode();
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\runtime\Statics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */