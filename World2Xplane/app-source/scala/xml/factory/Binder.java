/*    */ package scala.xml.factory;
/*    */ 
/*    */ import scala.Function1;
/*    */ import scala.MatchError;
/*    */ import scala.Serializable;
/*    */ import scala.collection.Seq;
/*    */ import scala.reflect.ScalaSignature;
/*    */ import scala.runtime.AbstractFunction1;
/*    */ import scala.runtime.BoxedUnit;
/*    */ import scala.xml.Comment;
/*    */ import scala.xml.Elem;
/*    */ import scala.xml.EntityRef;
/*    */ import scala.xml.Node;
/*    */ import scala.xml.NodeBuffer;
/*    */ import scala.xml.NodeSeq;
/*    */ import scala.xml.NodeSeq$;
/*    */ import scala.xml.ProcInstr;
/*    */ import scala.xml.Text;
/*    */ import scala.xml.Text$;
/*    */ import scala.xml.parsing.ValidatingMarkupHandler;
/*    */ 
/*    */ @ScalaSignature(bytes = "\006\001A4Q!\001\002\002\002%\021aAQ5oI\026\024(BA\002\005\003\0351\027m\031;pefT!!\002\004\002\007alGNC\001\b\003\025\0318-\0317b\007\001\031\"\001\001\006\021\005-qQ\"\001\007\013\0055!\021a\0029beNLgnZ\005\003\0371\021qCV1mS\022\fG/\0338h\033\006\0248.\0369IC:$G.\032:\t\021E\001!Q1A\005\002I\t!\002\035:fg\026\024h/Z,T+\005\031\002C\001\013\026\033\0051\021B\001\f\007\005\035\021un\0347fC:D\001\002\007\001\003\002\003\006IaE\001\faJ,7/\032:wK^\033\006\005C\003\033\001\021\0051$\001\004=S:LGO\020\013\0039y\001\"!\b\001\016\003\tAQ!E\rA\002MAq\001\t\001A\002\023\005\021%\001\004sKN,H\016^\013\002EA\0211\005J\007\002\t%\021Q\005\002\002\013\035>$WMQ;gM\026\024\bbB\024\001\001\004%\t\001K\001\013e\026\034X\017\034;`I\025\fHCA\025-!\t!\"&\003\002,\r\t!QK\\5u\021\035ic%!AA\002\t\n1\001\037\0232\021\031y\003\001)Q\005E\0059!/Z:vYR\004\003\"B\031\001\t\003\021\024!\005:fa>\024HoU=oi\006DXI\035:peR\031\021f\r\035\t\013Q\002\004\031A\033\002\007A|7\017\005\002\025m%\021qG\002\002\004\023:$\b\"B\0351\001\004Q\024aA:ueB\0211H\020\b\003)qJ!!\020\004\002\rA\023X\rZ3g\023\ty\004I\001\004TiJLgn\032\006\003{\031AQA\021\001\005\006\r\013\021\002\035:pG&s7\017\036:\025\t\021;\005J\023\t\003G\025K!A\022\003\003\023A\023xnY%ogR\024\b\"\002\033B\001\004)\004\"B%B\001\004Q\024A\002;be\036,G\017C\003L\003\002\007!(A\002uqRDQ!\024\001\005\0069\013qaY8n[\026tG\017F\002P%N\003\"a\t)\n\005E#!aB\"p[6,g\016\036\005\006i1\003\r!\016\005\006\0272\003\rA\017\005\006+\002!)AV\001\nK:$\030\016^=SK\032$2a\026.\\!\t\031\003,\003\002Z\t\tIQI\034;jif\024VM\032\005\006iQ\003\r!\016\005\0069R\003\rAO\001\002]\")a\f\001C\003?\006!A/\032=u)\r\0017\r\032\t\003G\005L!A\031\003\003\tQ+\007\020\036\005\006iu\003\r!\016\005\006\027v\003\rA\017\005\006M\002!)aZ\001\tiJ\fg/\032:tKR\021\021\006\033\005\0069\026\004\r!\033\t\003G)L!a\033\003\003\t9{G-\032\005\006[\002!)A\\\001\tm\006d\027\016Z1uKR\021\021n\034\005\00692\004\r!\033")
/*    */ public abstract class Binder extends ValidatingMarkupHandler {
/*    */   private final boolean preserveWS;
/*    */   
/*    */   public boolean preserveWS() {
/* 19 */     return this.preserveWS;
/*    */   }
/*    */   
/* 21 */   private NodeBuffer result = new NodeBuffer();
/*    */   
/*    */   public NodeBuffer result() {
/* 21 */     return this.result;
/*    */   }
/*    */   
/*    */   public void result_$eq(NodeBuffer x$1) {
/* 21 */     this.result = x$1;
/*    */   }
/*    */   
/*    */   public void reportSyntaxError(int pos, String str) {}
/*    */   
/*    */   public final ProcInstr procInstr(int pos, String target, String txt) {
/* 26 */     return new ProcInstr(target, txt);
/*    */   }
/*    */   
/*    */   public final Comment comment(int pos, String txt) {
/* 29 */     return new Comment(txt);
/*    */   }
/*    */   
/*    */   public final EntityRef entityRef(int pos, String n) {
/* 32 */     return new EntityRef(n);
/*    */   }
/*    */   
/*    */   public final Text text(int pos, String txt) {
/* 35 */     return Text$.MODULE$.apply(txt);
/*    */   }
/*    */   
/*    */   public final void traverse(Node n) {
/* 37 */     if (n instanceof ProcInstr) {
/* 37 */       ProcInstr procInstr = (ProcInstr)n;
/* 39 */       result().$amp$plus(procInstr(0, procInstr.target(), procInstr.text()));
/* 40 */     } else if (n instanceof Comment) {
/* 40 */       Comment comment = (Comment)n;
/* 41 */       result().$amp$plus(comment(0, comment.text()));
/* 42 */     } else if (n instanceof Text) {
/* 42 */       Text text = (Text)n;
/* 43 */       result().$amp$plus(text(0, (String)text.data()));
/* 44 */     } else if (n instanceof EntityRef) {
/* 44 */       EntityRef entityRef = (EntityRef)n;
/* 45 */       result().$amp$plus(entityRef(0, entityRef.entityName()));
/*    */     } else {
/* 46 */       if (n instanceof Elem) {
/* 46 */         Elem elem = (Elem)n;
/* 47 */         elemStart(0, elem.prefix(), elem.label(), elem.attributes(), elem.scope());
/* 48 */         NodeBuffer old = result();
/* 49 */         result_$eq(new NodeBuffer());
/* 50 */         elem.child().foreach((Function1)new Binder$$anonfun$traverse$1(this));
/* 51 */         result_$eq(old.$amp$plus(elem(0, elem.prefix(), elem.label(), elem.attributes(), elem.scope(), elem.minimizeEmpty(), NodeSeq$.MODULE$.fromSeq((Seq)result())).toList()));
/* 52 */         elemEnd(0, elem.prefix(), elem.label());
/*    */         return;
/*    */       } 
/*    */       throw new MatchError(n);
/*    */     } 
/*    */   }
/*    */   
/*    */   public class Binder$$anonfun$traverse$1 extends AbstractFunction1<Node, BoxedUnit> implements Serializable {
/*    */     public static final long serialVersionUID = 0L;
/*    */     
/*    */     public final void apply(Node m) {
/*    */       this.$outer.traverse(m);
/*    */     }
/*    */     
/*    */     public Binder$$anonfun$traverse$1(Binder $outer) {}
/*    */   }
/*    */   
/*    */   public final Node validate(Node n) {
/* 56 */     rootLabel_$eq(n.label());
/* 57 */     traverse(n);
/* 58 */     return (Node)result().apply(0);
/*    */   }
/*    */   
/*    */   public Binder(boolean preserveWS) {}
/*    */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\factory\Binder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */