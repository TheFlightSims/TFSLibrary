/*    */ package akka.serialization;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Predef$;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\r3Q!\001\002\002\002\035\0211BS*fe&\fG.\033>fe*\0211\001B\001\016g\026\024\030.\0317ju\006$\030n\0348\013\003\025\tA!Y6lC\016\0011c\001\001\t\035A\021\021\002D\007\002\025)\t1\"A\003tG\006d\027-\003\002\016\025\t1\021I\\=SK\032\004\"a\004\t\016\003\tI!!\005\002\003\025M+'/[1mSj,'\017C\003\024\001\021\005A#\001\004=S:LGO\020\013\002+A\021q\002\001\005\006/\001!)\001G\001\013MJ|WNQ5oCJLHc\001\005\032C!)!D\006a\0017\005)!-\037;fgB\031\021\002\b\020\n\005uQ!!B!se\006L\bCA\005 \023\t\001#B\001\003CsR,\007\"\002\022\027\001\004\031\023\001C7b]&4Wm\035;\021\007%!c%\003\002&\025\t1q\n\035;j_:\004$a\n\031\021\007!ZcF\004\002\nS%\021!FC\001\007!J,G-\0324\n\0051j#!B\"mCN\034(B\001\026\013!\ty\003\007\004\001\005\023E\n\023\021!A\001\006\003\021$aA0%gE\0211G\016\t\003\023QJ!!\016\006\003\0179{G\017[5oOB\021\021bN\005\003q)\0211!\0218z\021\025Q\004A\"\005<\00391'o\\7CS:\f'/\037&bm\006$2\001\003\037>\021\025Q\022\b1\001\034\021\025\021\023\b1\001?a\ty\024\tE\002)W\001\003\"aL!\005\023\tk\024\021!A\001\006\003\021$aA0%i\001")
/*    */ public abstract class JSerializer implements Serializer {
/*    */   public final Object fromBinary(byte[] bytes) {
/* 74 */     return Serializer$class.fromBinary(this, bytes);
/*    */   }
/*    */   
/*    */   public final Object fromBinary(byte[] bytes, Class clazz) {
/* 74 */     return Serializer$class.fromBinary(this, bytes, clazz);
/*    */   }
/*    */   
/*    */   public JSerializer() {
/* 74 */     Serializer$class.$init$(this);
/*    */   }
/*    */   
/*    */   public final Object fromBinary(byte[] bytes, Option manifest) {
/* 76 */     return fromBinaryJava(bytes, (Class)manifest.orNull(Predef$.MODULE$.conforms()));
/*    */   }
/*    */   
/*    */   public abstract Object fromBinaryJava(byte[] paramArrayOfbyte, Class<?> paramClass);
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\serialization\JSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */