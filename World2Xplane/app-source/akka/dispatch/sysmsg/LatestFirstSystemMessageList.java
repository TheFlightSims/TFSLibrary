/*    */ package akka.dispatch.sysmsg;
/*    */ 
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001Q4Q!\001\002\003\r!\021A\004T1uKN$h)\033:tiNK8\017^3n\033\026\0348/Y4f\031&\034HO\003\002\004\t\00511/_:ng\036T!!\002\004\002\021\021L7\017]1uG\"T\021aB\001\005C.\\\027m\005\002\001\023A\021!\"D\007\002\027)\tA\"A\003tG\006d\027-\003\002\017\027\t1\021I\\=WC2D\001\002\005\001\003\006\004%\tAE\001\005Q\026\fGm\001\001\026\003M\001\"\001F\013\016\003\tI!A\006\002\003\033MK8\017^3n\033\026\0348/Y4f\021!A\002A!A!\002\023\031\022!\0025fC\022\004\003\"\002\016\001\t\003Y\022A\002\037j]&$h\b\006\002\035;A\021A\003\001\005\006!e\001\ra\005\005\006?\001!)\001I\001\bSN,U\016\035;z+\005\t\003C\001\006#\023\t\0313BA\004C_>dW-\0318\t\013\025\002AQ\001\021\002\0219|g.R7qifDQa\n\001\005\006!\nAa]5{KV\t\021\006\005\002\013U%\0211f\003\002\004\023:$\b\"B\027\001\t\013q\023\001\002;bS2,\022\001\b\005\006a\001!)!M\001\be\0264XM]:f+\005\021\004C\001\0134\023\t!$A\001\020FCJd\027.Z:u\r&\0248\017^*zgR,W.T3tg\006<W\rT5ti\")a\007\001C\003o\005aAeY8m_:$3m\0347p]R\021A\004\017\005\006sU\002\raE\001\004[N<\007bB\036\001\003\003%\t\005P\001\tQ\006\034\bnQ8eKR\t\021\006C\004?\001\005\005I\021I \002\r\025\fX/\0317t)\t\t\003\tC\004B{\005\005\t\031\001\"\002\007a$\023\007\005\002\013\007&\021Ai\003\002\004\003:Lx\001\003$\003\003\003E\tAB$\00291\013G/Z:u\r&\0248\017^*zgR,W.T3tg\006<W\rT5tiB\021A\003\023\004\t\003\t\t\t\021#\001\007\023N\021\001J\023\t\003\025-K!\001T\006\003\r\005s\027PU3g\021\025Q\002\n\"\001O)\0059\005\"\002)I\t\013\t\026!E5t\0136\004H/\037\023fqR,gn]5p]R\021\021E\025\005\006'>\003\r\001H\001\006IQD\027n\035\005\006+\"#)AV\001\023]>tW)\0349us\022*\007\020^3og&|g\016\006\002\"/\")1\013\026a\0019!)\021\f\023C\0035\006q1/\033>fI\025DH/\0328tS>tGCA\025\\\021\025\031\006\f1\001\035\021\025i\006\n\"\002_\0039!\030-\0337%Kb$XM\\:j_:$\"\001H0\t\013Mc\006\031\001\017\t\013\005DEQ\0012\002#I,g/\032:tK\022*\007\020^3og&|g\016\006\0023G\")1\013\031a\0019!)Q\r\023C\003M\0061BeY8m_:$3m\0347p]\022*\007\020^3og&|g\016\006\002hSR\021A\004\033\005\006s\021\004\ra\005\005\006'\022\004\r\001\b\005\bW\"\013\t\021\"\002m\003IA\027m\0355D_\022,G%\032=uK:\034\030n\0348\025\005qj\007\"B*k\001\004a\002bB8I\003\003%)\001]\001\021KF,\030\r\\:%Kb$XM\\:j_:$\"!]:\025\005\005\022\bbB!o\003\003\005\rA\021\005\006':\004\r\001\b")
/*    */ public final class LatestFirstSystemMessageList {
/*    */   private final SystemMessage head;
/*    */   
/*    */   public SystemMessage head() {
/* 47 */     return this.head;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 47 */     return LatestFirstSystemMessageList$.MODULE$.hashCode$extension(head());
/*    */   }
/*    */   
/*    */   public boolean equals(Object x$1) {
/* 47 */     return LatestFirstSystemMessageList$.MODULE$.equals$extension(head(), x$1);
/*    */   }
/*    */   
/*    */   public LatestFirstSystemMessageList(SystemMessage head) {}
/*    */   
/*    */   public final boolean isEmpty() {
/* 53 */     return LatestFirstSystemMessageList$.MODULE$.isEmpty$extension(head());
/*    */   }
/*    */   
/*    */   public final boolean nonEmpty() {
/* 58 */     return LatestFirstSystemMessageList$.MODULE$.nonEmpty$extension(head());
/*    */   }
/*    */   
/*    */   public final int size() {
/* 63 */     return LatestFirstSystemMessageList$.MODULE$.size$extension(head());
/*    */   }
/*    */   
/*    */   public final SystemMessage tail() {
/* 72 */     return LatestFirstSystemMessageList$.MODULE$.tail$extension(head());
/*    */   }
/*    */   
/*    */   public final SystemMessage reverse() {
/* 80 */     return LatestFirstSystemMessageList$.MODULE$.reverse$extension(head());
/*    */   }
/*    */   
/*    */   public final SystemMessage $colon$colon(SystemMessage msg) {
/* 85 */     return LatestFirstSystemMessageList$.MODULE$.$colon$colon$extension(head(), msg);
/*    */   }
/*    */   
/*    */   public static boolean equals$extension(SystemMessage paramSystemMessage, Object paramObject) {
/*    */     return LatestFirstSystemMessageList$.MODULE$.equals$extension(paramSystemMessage, paramObject);
/*    */   }
/*    */   
/*    */   public static int hashCode$extension(SystemMessage paramSystemMessage) {
/*    */     return LatestFirstSystemMessageList$.MODULE$.hashCode$extension(paramSystemMessage);
/*    */   }
/*    */   
/*    */   public static SystemMessage $colon$colon$extension(SystemMessage paramSystemMessage1, SystemMessage paramSystemMessage2) {
/*    */     return LatestFirstSystemMessageList$.MODULE$.$colon$colon$extension(paramSystemMessage1, paramSystemMessage2);
/*    */   }
/*    */   
/*    */   public static SystemMessage reverse$extension(SystemMessage paramSystemMessage) {
/*    */     return LatestFirstSystemMessageList$.MODULE$.reverse$extension(paramSystemMessage);
/*    */   }
/*    */   
/*    */   public static SystemMessage tail$extension(SystemMessage paramSystemMessage) {
/*    */     return LatestFirstSystemMessageList$.MODULE$.tail$extension(paramSystemMessage);
/*    */   }
/*    */   
/*    */   public static int size$extension(SystemMessage paramSystemMessage) {
/*    */     return LatestFirstSystemMessageList$.MODULE$.size$extension(paramSystemMessage);
/*    */   }
/*    */   
/*    */   public static boolean nonEmpty$extension(SystemMessage paramSystemMessage) {
/*    */     return LatestFirstSystemMessageList$.MODULE$.nonEmpty$extension(paramSystemMessage);
/*    */   }
/*    */   
/*    */   public static boolean isEmpty$extension(SystemMessage paramSystemMessage) {
/*    */     return LatestFirstSystemMessageList$.MODULE$.isEmpty$extension(paramSystemMessage);
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\sysmsg\LatestFirstSystemMessageList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */