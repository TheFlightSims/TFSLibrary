/*     */ package akka.serialization;
/*     */ 
/*     */ import scala.Option;
/*     */ import scala.collection.mutable.StringBuilder;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001)3A!\001\002\001\017\t\031\")\037;f\003J\024\030-_*fe&\fG.\033>fe*\0211\001B\001\016g\026\024\030.\0317ju\006$\030n\0348\013\003\025\tA!Y6lC\016\0011c\001\001\t\035A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\025M+'/[1mSj,'\017C\003\024\001\021\005A#\001\004=S:LGO\020\013\002+A\021q\002\001\005\006/\001!\t\001G\001\020S:\034G.\0363f\033\006t\027NZ3tiV\t\021\004\005\002\n5%\0211D\003\002\b\005>|G.Z1o\021\025i\002\001\"\001\037\003)IG-\0328uS\032LWM]\013\002?A\021\021\002I\005\003C)\0211!\0238u\021\025\031\003\001\"\001%\003!!xNQ5oCJLHCA\023,!\rIa\005K\005\003O)\021Q!\021:sCf\004\"!C\025\n\005)R!\001\002\"zi\026DQ\001\f\022A\002!\t\021a\034\005\006]\001!\taL\001\013MJ|WNQ5oCJLHc\001\0051e!)\021'\fa\001K\005)!-\037;fg\")1'\fa\001i\005)1\r\\1{uB\031\021\"N\034\n\005YR!AB(qi&|g\016\r\0029\003B\031\021\bP \017\005%Q\024BA\036\013\003\031\001&/\0323fM&\021QH\020\002\006\0072\f7o\035\006\003w)\001\"\001Q!\r\001\021I!IMA\001\002\003\025\ta\021\002\004?\022:\024C\001#H!\tIQ)\003\002G\025\t9aj\034;iS:<\007CA\005I\023\tI%BA\002B]f\004")
/*     */ public class ByteArraySerializer implements Serializer {
/*     */   public final Object fromBinary(byte[] bytes) {
/* 157 */     return Serializer$class.fromBinary(this, bytes);
/*     */   }
/*     */   
/*     */   public final Object fromBinary(byte[] bytes, Class clazz) {
/* 157 */     return Serializer$class.fromBinary(this, bytes, clazz);
/*     */   }
/*     */   
/*     */   public ByteArraySerializer() {
/* 157 */     Serializer$class.$init$(this);
/*     */   }
/*     */   
/*     */   public boolean includeManifest() {
/* 158 */     return false;
/*     */   }
/*     */   
/*     */   public int identifier() {
/* 159 */     return 4;
/*     */   }
/*     */   
/*     */   public byte[] toBinary(Object o) {
/* 160 */     Object object = o;
/* 161 */     if (object == null) {
/* 161 */       null;
/* 161 */       Object object1 = null;
/*     */     } else {
/* 162 */       if (object instanceof byte[]) {
/*     */         byte[] arrayOfByte;
/* 162 */         return arrayOfByte = (byte[])object;
/*     */       } 
/* 163 */       throw new IllegalArgumentException((new StringBuilder()).append("ByteArraySerializer only serializes byte arrays, not [").append(object).append("]").toString());
/*     */     } 
/*     */     return (byte[])SYNTHETIC_LOCAL_VARIABLE_3;
/*     */   }
/*     */   
/*     */   public Object fromBinary(byte[] bytes, Option clazz) {
/* 165 */     return bytes;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\serialization\ByteArraySerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */