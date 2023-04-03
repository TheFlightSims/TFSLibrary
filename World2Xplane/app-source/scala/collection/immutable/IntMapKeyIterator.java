/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001q2Q!\001\002\001\005!\021\021#\0238u\033\006\0048*Z=Ji\026\024\030\r^8s\025\t\031A!A\005j[6,H/\0312mK*\021QAB\001\013G>dG.Z2uS>t'\"A\004\002\013M\034\027\r\\1\026\005%\0012C\001\001\013!\021YABD\016\016\003\tI!!\004\002\003\035%sG/T1q\023R,'/\031;peB\021q\002\005\007\001\t\025\t\002A1\001\024\005\00516\001A\t\003)a\001\"!\006\f\016\003\031I!a\006\004\003\0179{G\017[5oOB\021Q#G\005\0035\031\0211!\0218z!\tarD\004\002\f;%\021aDA\001\f\023:$X*\0319Vi&d7/\003\002!C\t\031\021J\034;\n\005\001\022#BA\022%\0035\021\025\016^(qKJ\fG/[8og*\021Q\005B\001\bO\026tWM]5d\021!9\003A!A!\002\023A\023AA5u!\rY\021FD\005\003U\t\021a!\0238u\033\006\004\b\"\002\027\001\t\003i\023A\002\037j]&$h\b\006\002/_A\0311\002\001\b\t\013\035Z\003\031\001\025\t\013E\002A\021\001\032\002\017Y\fG.^3PMR\0211d\r\005\006iA\002\r!N\001\004i&\004\bc\001\034:\0359\0211bN\005\003q\t\ta!\0238u\033\006\004\030B\001\036<\005\r!\026\016\035\006\003q\t\001")
/*     */ public class IntMapKeyIterator<V> extends IntMapIterator<V, Object> {
/*     */   public IntMapKeyIterator(IntMap<V> it) {
/* 139 */     super(it);
/*     */   }
/*     */   
/*     */   public int valueOf(IntMap.Tip tip) {
/* 140 */     return tip.key();
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\IntMapKeyIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */