/*    */ package scala.xml.dtd;
/*    */ 
/*    */ import scala.Option;
/*    */ import scala.Option$;
/*    */ import scala.Predef$;
/*    */ import scala.collection.Seq;
/*    */ import scala.collection.immutable.List;
/*    */ import scala.collection.immutable.Nil$;
/*    */ import scala.collection.immutable.StringLike;
/*    */ import scala.collection.immutable.StringOps;
/*    */ import scala.collection.mutable.HashMap;
/*    */ import scala.collection.mutable.Map;
/*    */ import scala.reflect.ScalaSignature;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001\005\005a!B\001\003\003\003I!a\001#U\t*\0211\001B\001\004IR$'BA\003\007\003\rAX\016\034\006\002\017\005)1oY1mC\016\0011C\001\001\013!\tYA\"D\001\007\023\tiaA\001\004B]f\024VM\032\005\006\037\001!\t\001E\001\007y%t\027\016\036 \025\003E\001\"A\005\001\016\003\tAq\001\006\001A\002\023\005Q#\001\006fqR,'O\\1m\023\022+\022A\006\t\003%]I!\001\007\002\003\025\025CH/\032:oC2LE\tC\004\033\001\001\007I\021A\016\002\035\025DH/\032:oC2LEi\030\023fcR\021Ad\b\t\003\027uI!A\b\004\003\tUs\027\016\036\005\bAe\t\t\0211\001\027\003\rAH%\r\005\007E\001\001\013\025\002\f\002\027\025DH/\032:oC2LE\t\t\005\bI\001\001\r\021\"\001&\003\025!Wm\0317t+\0051\003cA\0240e9\021\001&\f\b\003S1j\021A\013\006\003W!\ta\001\020:p_Rt\024\"A\004\n\00592\021a\0029bG.\fw-Z\005\003aE\022A\001T5ti*\021aF\002\t\003%MJ!\001\016\002\003\t\021+7\r\034\005\bm\001\001\r\021\"\0018\003%!Wm\0317t?\022*\027\017\006\002\035q!9\001%NA\001\002\0041\003B\002\036\001A\003&a%\001\004eK\016d7\017\t\005\006y\001!\t!P\001\n]>$\030\r^5p]N,\022A\020\t\004O}\n\025B\001!2\005\r\031V-\035\t\003%\tK!a\021\002\003\0319{G/\031;j_:$Um\0317\t\013\025\003A\021\001$\002!Ut\007/\031:tK\022,e\016^5uS\026\034X#A$\021\007\035z\004\n\005\002\023\023&\021!J\001\002\013\013:$\030\016^=EK\016d\007b\002'\001\001\004%\t!T\001\005K2,W.F\001O!\021yEKV/\016\003AS!!\025*\002\0175,H/\0312mK*\0211KB\001\013G>dG.Z2uS>t\027BA+Q\005\ri\025\r\035\t\003/js!a\003-\n\005e3\021A\002)sK\022,g-\003\002\\9\n11\013\036:j]\036T!!\027\004\021\005Iq\026BA0\003\005!)E.Z7EK\016d\007bB1\001\001\004%\tAY\001\tK2,Wn\030\023fcR\021Ad\031\005\bA\001\f\t\0211\001O\021\031)\007\001)Q\005\035\006)Q\r\\3nA!9q\r\001a\001\n\003A\027\001B1uiJ,\022!\033\t\005\037R3&\016\005\002\023W&\021AN\001\002\f\003R$H*[:u\t\026\034G\016C\004o\001\001\007I\021A8\002\021\005$HO]0%KF$\"\001\b9\t\017\001j\027\021!a\001S\"1!\017\001Q!\n%\fQ!\031;ue\002Bq\001\036\001A\002\023\005Q/A\002f]R,\022A\036\t\005\037R3\006\nC\004y\001\001\007I\021A=\002\017\025tGo\030\023fcR\021AD\037\005\bA]\f\t\0211\001w\021\031a\b\001)Q\005m\006!QM\034;!\021\025q\b\001\"\021\000\003!!xn\025;sS:<G#\001,")
/*    */ public abstract class DTD {
/* 20 */   private ExternalID externalID = null;
/*    */   
/*    */   public ExternalID externalID() {
/* 20 */     return this.externalID;
/*    */   }
/*    */   
/*    */   public void externalID_$eq(ExternalID x$1) {
/* 20 */     this.externalID = x$1;
/*    */   }
/*    */   
/* 21 */   private List<Decl> decls = (List<Decl>)Nil$.MODULE$;
/*    */   
/*    */   public List<Decl> decls() {
/* 21 */     return this.decls;
/*    */   }
/*    */   
/*    */   public void decls_$eq(List<Decl> x$1) {
/* 21 */     this.decls = x$1;
/*    */   }
/*    */   
/*    */   public Seq<NotationDecl> notations() {
/* 22 */     return (Seq<NotationDecl>)Nil$.MODULE$;
/*    */   }
/*    */   
/*    */   public Seq<EntityDecl> unparsedEntities() {
/* 23 */     return (Seq<EntityDecl>)Nil$.MODULE$;
/*    */   }
/*    */   
/* 25 */   private Map<String, ElemDecl> elem = (Map<String, ElemDecl>)new HashMap();
/*    */   
/*    */   public Map<String, ElemDecl> elem() {
/* 25 */     return this.elem;
/*    */   }
/*    */   
/*    */   public void elem_$eq(Map<String, ElemDecl> x$1) {
/* 25 */     this.elem = x$1;
/*    */   }
/*    */   
/* 26 */   private Map<String, AttListDecl> attr = (Map<String, AttListDecl>)new HashMap();
/*    */   
/*    */   public Map<String, AttListDecl> attr() {
/* 26 */     return this.attr;
/*    */   }
/*    */   
/*    */   public void attr_$eq(Map<String, AttListDecl> x$1) {
/* 26 */     this.attr = x$1;
/*    */   }
/*    */   
/* 27 */   private Map<String, EntityDecl> ent = (Map<String, EntityDecl>)new HashMap();
/*    */   
/*    */   public Map<String, EntityDecl> ent() {
/* 27 */     return this.ent;
/*    */   }
/*    */   
/*    */   public void ent_$eq(Map<String, EntityDecl> x$1) {
/* 27 */     this.ent = x$1;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 30 */     Predef$ predef$ = Predef$.MODULE$;
/*    */     Option option;
/* 30 */     return StringLike.class.format((StringLike)new StringOps("DTD [\n%s%s]"), (Seq)Predef$.MODULE$.genericWrapArray(new Object[] { (option = Option$.MODULE$.apply(externalID())).isEmpty() ? "" : option.get(), 
/* 32 */             decls().mkString("", "\n", "\n") }));
/*    */   }
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\dtd\DTD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */