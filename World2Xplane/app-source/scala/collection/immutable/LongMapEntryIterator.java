/*     */ package scala.collection.immutable;
/*     */ 
/*     */ import scala.Tuple2;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001}2Q!\001\002\001\005!\021A\003T8oO6\013\007/\0228uefLE/\032:bi>\024(BA\002\005\003%IW.\\;uC\ndWM\003\002\006\r\005Q1m\0347mK\016$\030n\0348\013\003\035\tQa]2bY\006,\"!\003\t\024\005\001Q\001\003B\006\r\035mi\021AA\005\003\033\t\021q\002T8oO6\013\007/\023;fe\006$xN\035\t\003\037Aa\001\001B\003\022\001\t\0071CA\001W\007\001\t\"\001\006\r\021\005U1R\"\001\004\n\005]1!a\002(pi\"Lgn\032\t\003+eI!A\007\004\003\007\005s\027\020\005\003\0269yq\021BA\017\007\005\031!V\017\0357feA\021qD\t\b\003\027\001J!!\t\002\002\0311{gnZ'baV#\030\016\\:\n\005\r\"#\001\002'p]\036L!aI\023\013\005\031:\023!\004\"ji>\003XM]1uS>t7O\003\002)\t\0059q-\0328fe&\034\007\002\003\026\001\005\003\005\013\021B\026\002\005%$\bcA\006-\035%\021QF\001\002\b\031>tw-T1q\021\025y\003\001\"\0011\003\031a\024N\\5u}Q\021\021G\r\t\004\027\001q\001\"\002\026/\001\004Y\003\"\002\033\001\t\003)\024a\002<bYV,wJ\032\013\0037YBQaN\032A\002a\n1\001^5q!\rIDH\004\b\003\027iJ!a\017\002\002\0171{gnZ'ba&\021QH\020\002\004)&\004(BA\036\003\001")
/*     */ public class LongMapEntryIterator<V> extends LongMapIterator<V, Tuple2<Object, V>> {
/*     */   public LongMapEntryIterator(LongMap<V> it) {
/* 127 */     super(it);
/*     */   }
/*     */   
/*     */   public Tuple2<Object, V> valueOf(LongMap.Tip tip) {
/* 128 */     return new Tuple2(BoxesRunTime.boxToLong(tip.key()), tip.value());
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\immutable\LongMapEntryIterator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */