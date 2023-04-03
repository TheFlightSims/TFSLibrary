/*     */ package akka.util;
/*     */ 
/*     */ import scala.Serializable;
/*     */ import scala.math.Integral;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%r!B\001\003\021\0039\021!E\"p[B\f7\r\036\"zi\026\034FO]5oO*\0211\001B\001\005kRLGNC\001\006\003\021\t7n[1\004\001A\021\001\"C\007\002\005\031)!B\001E\001\027\t\t2i\\7qC\016$()\037;f'R\024\030N\\4\024\007%a!\003\005\002\016!5\taBC\001\020\003\025\0318-\0317b\023\t\tbB\001\004B]f\024VM\032\t\003\033MI!\001\006\b\003\031M+'/[1mSj\f'\r\\3\t\013YIA\021A\f\002\rqJg.\033;?)\0059\001\"B\r\n\t\003Q\022!B1qa2LHCA\0163!\tAADB\003\013\005\005\005RdE\002\035=I\001\"\001C\020\n\005\001\022!A\003\"zi\026\034FO]5oO\")a\003\bC\001EQ\t1\004C\003%9\021\005Q%A\005jg\016{W\016]1diV\ta\005\005\002\016O%\021\001F\004\002\b\005>|G.Z1o\021\025QC\004\"\001,\003\035\031w.\0349bGR,\022\001L\007\0029%\022ADL\005\003_A\022ABQ=uKN#(/\0338hc\rS!!\r\002\002\025\tKH/Z*ue&tw\rC\00341\001\007A'A\003csR,7\017E\002\016k]J!A\016\b\003\013\005\023(/Y=\021\0055A\024BA\035\017\005\021\021\025\020^3\t\013eIA\021A\036\025\005ma\004\"B\032;\001\004i\004cA\007?o%\021qH\004\002\013yI,\007/Z1uK\022t\004\"B\r\n\t\003\tUC\001\"U)\t\031U\f\006\002\034\t\")Q\t\021a\002\r\006\031a.^7\021\007\035{%K\004\002I\033:\021\021\nT\007\002\025*\0211JB\001\007yI|w\016\036 \n\003=I!A\024\b\002\017A\f7m[1hK&\021\001+\025\002\t\023:$Xm\032:bY*\021aJ\004\t\003'Rc\001\001B\003V\001\n\007aKA\001U#\t9&\f\005\002\0161&\021\021L\004\002\b\035>$\b.\0338h!\ti1,\003\002]\035\t\031\021I\\=\t\013M\002\005\031\0010\021\0075q$\013C\003\032\023\021\005\001\r\006\002\034C\")1g\030a\001EB\0211\r[\007\002I*\021QMZ\001\004]&|'\"A4\002\t)\fg/Y\005\003S\022\024!BQ=uK\n+hMZ3s\021\025I\022\002\"\001l)\tYB\016C\003nU\002\007a.\001\004tiJLgn\032\t\003_Jt!!\0049\n\005Et\021A\002)sK\022,g-\003\002ti\n11\013\036:j]\036T!!\035\b\t\013eIA\021\001<\025\007m9\b\020C\003nk\002\007a\016C\003zk\002\007a.A\004dQ\006\0248/\032;\t\013mLA\021\001?\002\023\031\024x.\\!se\006LH#B\016~\006%\001\"\002@{\001\004!\024!B1se\006L\bbBA\001u\002\007\0211A\001\007_\03247/\032;\021\0075\t)!C\002\002\b9\0211!\0238u\021\035\tYA\037a\001\003\007\ta\001\\3oORD\007\"CA\b\023\t\007I\021AA\t\003\025)W\016\035;z+\005Y\002bBA\013\023\001\006IaG\001\007K6\004H/\037\021\t\023\005e\021\"!A\005\n\005m\021a\003:fC\022\024Vm]8mm\026$\"!!\b\021\t\005}\021QE\007\003\003CQ1!a\tg\003\021a\027M\\4\n\t\005\035\022\021\005\002\007\037\nTWm\031;")
/*     */ public abstract class CompactByteString extends ByteString implements Serializable {
/*     */   public static CompactByteString empty() {
/*     */     return CompactByteString$.MODULE$.empty();
/*     */   }
/*     */   
/*     */   public static CompactByteString fromArray(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) {
/*     */     return CompactByteString$.MODULE$.fromArray(paramArrayOfbyte, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */   public static class CompactByteString$$anonfun$apply$4 extends AbstractFunction1<T, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Integral num$1;
/*     */     
/*     */     public final byte apply(Object x) {
/* 429 */       return (byte)this.num$1.toInt(x);
/*     */     }
/*     */     
/*     */     public CompactByteString$$anonfun$apply$4(Integral num$1) {}
/*     */   }
/*     */   
/*     */   public boolean isCompact() {
/* 480 */     return true;
/*     */   }
/*     */   
/*     */   public CompactByteString compact() {
/* 481 */     return this;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akk\\util\CompactByteString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */