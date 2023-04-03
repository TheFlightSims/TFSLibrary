/*     */ package akka.dispatch.sysmsg;
/*     */ 
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001}4Q!\001\002\003\r!\021a$R1sY&,7\017\036$jeN$8+_:uK6lUm]:bO\026d\025n\035;\013\005\r!\021AB:zg6\034xM\003\002\006\r\005AA-[:qCR\034\007NC\001\b\003\021\t7n[1\024\005\001I\001C\001\006\016\033\005Y!\"\001\007\002\013M\034\027\r\\1\n\0059Y!AB!osZ\013G\016\003\005\021\001\t\025\r\021\"\001\023\003\021AW-\0313\004\001U\t1\003\005\002\025+5\t!!\003\002\027\005\ti1+_:uK6lUm]:bO\026D\001\002\007\001\003\002\003\006IaE\001\006Q\026\fG\r\t\005\0065\001!\taG\001\007y%t\027\016\036 \025\005qi\002C\001\013\001\021\025\001\022\0041\001\024\021\025y\002\001\"\002!\003\035I7/R7qif,\022!\t\t\003\025\tJ!aI\006\003\017\t{w\016\\3b]\")Q\005\001C\003A\005Aan\0348F[B$\030\020C\003(\001\021\025\001&\001\003tSj,W#A\025\021\005)Q\023BA\026\f\005\rIe\016\036\005\006[\001!)AL\001\005i\006LG.F\001\035\021\025\001\004\001\"\0022\003\035\021XM^3sg\026,\022A\r\t\003)MJ!\001\016\002\00391\013G/Z:u\r&\0248\017^*zgR,W.T3tg\006<W\rT5ti\")a\007\001C\003o\005aAeY8m_:$3m\0347p]R\021A\004\017\005\006sU\002\raE\001\004[N<\007\"B\036\001\t\013a\024A\007:fm\026\0248/Z0%G>dwN\034\023d_2|g\016J2pY>tGC\001\017>\021\025q$\b1\0013\003\025yG\017[3s\021\035\001\005!!A\005B\005\013\001\002[1tQ\016{G-\032\013\002S!91\tAA\001\n\003\"\025AB3rk\006d7\017\006\002\"\013\"9aIQA\001\002\0049\025a\001=%cA\021!\002S\005\003\023.\0211!\0218z\017!Y%!!A\t\002\031a\025AH#be2LWm\035;GSJ\034HoU=ti\026lW*Z:tC\036,G*[:u!\t!RJ\002\005\002\005\005\005\t\022\001\004O'\tiu\n\005\002\013!&\021\021k\003\002\007\003:L(+\0324\t\013iiE\021A*\025\0031CQ!V'\005\006Y\013\021#[:F[B$\030\020J3yi\026t7/[8o)\t\ts\013C\003Y)\002\007A$A\003%i\"L7\017C\003[\033\022\0251,\001\no_:,U\016\035;zI\025DH/\0328tS>tGCA\021]\021\025A\026\f1\001\035\021\025qV\n\"\002`\0039\031\030N_3%Kb$XM\\:j_:$\"!\0131\t\013ak\006\031\001\017\t\013\tlEQA2\002\035Q\f\027\016\034\023fqR,gn]5p]R\021A\004\032\005\0061\006\004\r\001\b\005\006M6#)aZ\001\022e\0264XM]:fI\025DH/\0328tS>tGC\001\032i\021\025AV\r1\001\035\021\025QW\n\"\002l\003Y!3m\0347p]\022\032w\016\\8oI\025DH/\0328tS>tGC\0017o)\taR\016C\003:S\002\0071\003C\003YS\002\007A\004C\003q\033\022\025\021/\001\023sKZ,'o]3`I\r|Gn\0348%G>dwN\034\023d_2|g\016J3yi\026t7/[8o)\t\021H\017\006\002\035g\")ah\034a\001e!)\001l\034a\0019!9a/TA\001\n\0139\030A\0055bg\"\034u\016Z3%Kb$XM\\:j_:$\"!\021=\t\013a+\b\031\001\017\t\017il\025\021!C\003w\006\001R-];bYN$S\r\037;f]NLwN\034\013\003yz$\"!I?\t\017\031K\030\021!a\001\017\")\001,\037a\0019\001")
/*     */ public final class EarliestFirstSystemMessageList {
/*     */   private final SystemMessage head;
/*     */   
/*     */   public SystemMessage head() {
/* 108 */     return this.head;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 108 */     return EarliestFirstSystemMessageList$.MODULE$.hashCode$extension(head());
/*     */   }
/*     */   
/*     */   public boolean equals(Object x$1) {
/* 108 */     return EarliestFirstSystemMessageList$.MODULE$.equals$extension(head(), x$1);
/*     */   }
/*     */   
/*     */   public EarliestFirstSystemMessageList(SystemMessage head) {}
/*     */   
/*     */   public final boolean isEmpty() {
/* 114 */     return EarliestFirstSystemMessageList$.MODULE$.isEmpty$extension(head());
/*     */   }
/*     */   
/*     */   public final boolean nonEmpty() {
/* 119 */     return EarliestFirstSystemMessageList$.MODULE$.nonEmpty$extension(head());
/*     */   }
/*     */   
/*     */   public final int size() {
/* 124 */     return EarliestFirstSystemMessageList$.MODULE$.size$extension(head());
/*     */   }
/*     */   
/*     */   public final SystemMessage tail() {
/* 133 */     return EarliestFirstSystemMessageList$.MODULE$.tail$extension(head());
/*     */   }
/*     */   
/*     */   public final SystemMessage reverse() {
/* 141 */     return EarliestFirstSystemMessageList$.MODULE$.reverse$extension(head());
/*     */   }
/*     */   
/*     */   public final SystemMessage $colon$colon(SystemMessage msg) {
/* 146 */     return EarliestFirstSystemMessageList$.MODULE$.$colon$colon$extension(head(), msg);
/*     */   }
/*     */   
/*     */   public final SystemMessage reverse_$colon$colon$colon(SystemMessage other) {
/* 159 */     return EarliestFirstSystemMessageList$.MODULE$.reverse_$colon$colon$colon$extension(head(), other);
/*     */   }
/*     */   
/*     */   public static boolean equals$extension(SystemMessage paramSystemMessage, Object paramObject) {
/*     */     return EarliestFirstSystemMessageList$.MODULE$.equals$extension(paramSystemMessage, paramObject);
/*     */   }
/*     */   
/*     */   public static int hashCode$extension(SystemMessage paramSystemMessage) {
/*     */     return EarliestFirstSystemMessageList$.MODULE$.hashCode$extension(paramSystemMessage);
/*     */   }
/*     */   
/*     */   public static SystemMessage reverse_$colon$colon$colon$extension(SystemMessage paramSystemMessage1, SystemMessage paramSystemMessage2) {
/*     */     return EarliestFirstSystemMessageList$.MODULE$.reverse_$colon$colon$colon$extension(paramSystemMessage1, paramSystemMessage2);
/*     */   }
/*     */   
/*     */   public static SystemMessage $colon$colon$extension(SystemMessage paramSystemMessage1, SystemMessage paramSystemMessage2) {
/*     */     return EarliestFirstSystemMessageList$.MODULE$.$colon$colon$extension(paramSystemMessage1, paramSystemMessage2);
/*     */   }
/*     */   
/*     */   public static SystemMessage reverse$extension(SystemMessage paramSystemMessage) {
/*     */     return EarliestFirstSystemMessageList$.MODULE$.reverse$extension(paramSystemMessage);
/*     */   }
/*     */   
/*     */   public static SystemMessage tail$extension(SystemMessage paramSystemMessage) {
/*     */     return EarliestFirstSystemMessageList$.MODULE$.tail$extension(paramSystemMessage);
/*     */   }
/*     */   
/*     */   public static int size$extension(SystemMessage paramSystemMessage) {
/*     */     return EarliestFirstSystemMessageList$.MODULE$.size$extension(paramSystemMessage);
/*     */   }
/*     */   
/*     */   public static boolean nonEmpty$extension(SystemMessage paramSystemMessage) {
/*     */     return EarliestFirstSystemMessageList$.MODULE$.nonEmpty$extension(paramSystemMessage);
/*     */   }
/*     */   
/*     */   public static boolean isEmpty$extension(SystemMessage paramSystemMessage) {
/*     */     return EarliestFirstSystemMessageList$.MODULE$.isEmpty$extension(paramSystemMessage);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\EarliestFirstSystemMessageList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */