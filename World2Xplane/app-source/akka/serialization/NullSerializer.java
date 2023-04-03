/*     */ package akka.serialization;
/*     */ 
/*     */ import scala.Array$;
/*     */ import scala.Option;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.reflect.ClassTag$;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001Y;Q!\001\002\t\002\035\taBT;mYN+'/[1mSj,'O\003\002\004\t\005i1/\032:jC2L'0\031;j_:T\021!B\001\005C.\\\027m\001\001\021\005!IQ\"\001\002\007\013)\021\001\022A\006\003\0359+H\016\\*fe&\fG.\033>feN\021\021\002\004\t\003\02151AA\003\002\001\035M\031QbD\013\021\005A\031R\"A\t\013\003I\tQa]2bY\006L!\001F\t\003\r\005s\027PU3g!\tAa#\003\002\030\005\tQ1+\032:jC2L'0\032:\t\013eiA\021\001\016\002\rqJg.\033;?)\005a\001b\002\017\016\005\004%\t!H\001\f]VdG.Q:CsR,7/F\001\037!\r\001r$I\005\003AE\021Q!\021:sCf\004\"\001\005\022\n\005\r\n\"\001\002\"zi\026Da!J\007!\002\023q\022\001\0048vY2\f5OQ=uKN\004\003\"B\024\016\t\003A\023aD5oG2,H-Z'b]&4Wm\035;\026\003%\002\"\001\005\026\n\005-\n\"a\002\"p_2,\027M\034\005\006[5!\tAL\001\013S\022,g\016^5gS\026\024X#A\030\021\005A\001\024BA\031\022\005\rIe\016\036\005\006g5!\t\001N\001\ti>\024\025N\\1ssR\021a$\016\005\006mI\002\raD\001\002_\")\001(\004C\001s\005QaM]8n\005&t\027M]=\025\007=QD\bC\003<o\001\007a$A\003csR,7\017C\003>o\001\007a(A\003dY\006T(\020E\002\021\005K!\001Q\t\003\r=\003H/[8oa\t\0215\nE\002D\r&s!\001\005#\n\005\025\013\022A\002)sK\022,g-\003\002H\021\n)1\t\\1tg*\021Q)\005\t\003\025.c\001\001B\005My\005\005\t\021!B\001\033\n\031q\f\n\034\022\0059\013\006C\001\tP\023\t\001\026CA\004O_RD\027N\\4\021\005A\021\026BA*\022\005\r\te.\037\005\0063%!\t!\026\013\002\017\001")
/*     */ public class NullSerializer implements Serializer {
/*     */   private final byte[] nullAsBytes;
/*     */   
/*     */   public final Object fromBinary(byte[] bytes) {
/* 145 */     return Serializer$class.fromBinary(this, bytes);
/*     */   }
/*     */   
/*     */   public final Object fromBinary(byte[] bytes, Class clazz) {
/* 145 */     return Serializer$class.fromBinary(this, bytes, clazz);
/*     */   }
/*     */   
/*     */   public NullSerializer() {
/* 145 */     Serializer$class.$init$(this);
/* 146 */     this.nullAsBytes = (byte[])Array$.MODULE$.apply((Seq)Nil$.MODULE$, ClassTag$.MODULE$.Byte());
/*     */   }
/*     */   
/*     */   public byte[] nullAsBytes() {
/* 146 */     return this.nullAsBytes;
/*     */   }
/*     */   
/*     */   public boolean includeManifest() {
/* 147 */     return false;
/*     */   }
/*     */   
/*     */   public int identifier() {
/* 148 */     return 0;
/*     */   }
/*     */   
/*     */   public byte[] toBinary(Object o) {
/* 149 */     return nullAsBytes();
/*     */   }
/*     */   
/*     */   public Object fromBinary(byte[] bytes, Option clazz) {
/* 150 */     null;
/* 150 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\serialization\NullSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */