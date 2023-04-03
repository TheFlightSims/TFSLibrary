/*     */ package scala.xml;
/*     */ 
/*     */ import scala.Function1;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple5;
/*     */ import scala.collection.Seq;
/*     */ import scala.collection.Seq$;
/*     */ import scala.collection.TraversableOnce;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005%v!B\001\003\021\0039\021\001B#mK6T!a\001\003\002\007alGNC\001\006\003\025\0318-\0317b\007\001\001\"\001C\005\016\003\t1QA\003\002\t\002-\021A!\0227f[N\031\021\002\004\t\021\0055qQ\"\001\003\n\005=!!AB!osJ+g\r\005\002\016#%\021!\003\002\002\r'\026\024\030.\0317ju\006\024G.\032\005\006)%!\t!F\001\007y%t\027\016\036 \025\003\035AQaF\005\005\002a\tQ!\0319qYf$2\"GA/\003?\n\t'a\031\002fA\021\001B\007\004\005\025\t\0011dE\002\0339A\001\"\001C\017\n\005y\021!\001\002(pI\026D\001\002\t\016\003\006\004%\t%I\001\007aJ,g-\033=\026\003\t\002\"a\t\024\017\0055!\023BA\023\005\003\031\001&/\0323fM&\021q\005\013\002\007'R\024\030N\\4\013\005\025\"\001\002\003\026\033\005\003\005\013\021\002\022\002\017A\024XMZ5yA!AAF\007BC\002\023\005\021%A\003mC\n,G\016\003\005/5\t\005\t\025!\003#\003\031a\027MY3mA!A\001G\007B\001B\003%\021'A\006biR\024\030NY;uKN\f\004C\001\0053\023\t\031$A\001\005NKR\fG)\031;b\021!)$D!b\001\n\0032\024!B:d_B,W#A\034\021\005!A\024BA\035\003\005Aq\025-\\3ta\006\034WMQ5oI&tw\r\003\005<5\t\005\t\025!\0038\003\031\0318m\0349fA!AQH\007BC\002\023\005a(A\007nS:LW.\033>f\0136\004H/_\013\002A\021Q\002Q\005\003\003\022\021qAQ8pY\026\fg\016\003\005D5\t\005\t\025!\003@\0039i\027N\\5nSj,W)\0349us\002B\001\"\022\016\003\006\004%\tAR\001\006G\"LG\016Z\013\002\017B\031Q\002\023\017\n\005%#!A\003\037sKB,\027\r^3e}!A1J\007B\001B\003%q)\001\004dQ&dG\r\t\005\006)i!\t!\024\013\b39{\005+\025*T\021\025\001C\n1\001#\021\025aC\n1\001#\021\025\001D\n1\0012\021\025)D\n1\0018\021\025iD\n1\001@\021\025)E\n1\001H\021\025!\"\004\"\001V)\031Ibk\026-[7\")\001\005\026a\001E!)A\006\026a\001E!)\021\f\026a\001c\005Q\021\r\036;sS\n,H/Z:\t\013U\"\006\031A\034\t\013\025#\006\031A$)\tQk\006M\031\t\003\033yK!a\030\003\003\025\021,\007O]3dCR,G-I\001b\003\005eB\013[5tA\r|gn\035;sk\016$xN\035\021jg\002\022X\r^1j]\026$\007EZ8sA\t\f7m[<be\022\0043m\\7qCRL'-\0337jift\003\005\0257fCN,\007%^:fAQDW\r\t9sS6\f'/\037\021d_:\034HO];di>\024H\006I<iS\016D\007\005\\3ug\002Jx.\036\021ta\026\034\027NZ=!s>,(\017I8x]\002\002(/\0324fe\026t7-\032\021g_J\004\003-\\5oS6L'0Z#naRL\bML\021\002G\0061!GL\0311]ABQ!\032\016\005Fy\n1\003Z8D_2dWm\031;OC6,7\017]1dKNDQa\032\016\005Fy\n1\002Z8Ue\006t7OZ8s[\"9\021L\007b\001\n\003JW#A\031\t\r-T\002\025!\0032\003-\tG\017\036:jEV$Xm\035\021\t\0135TB\021\0138\002!\t\f7/[:G_JD\025m\0355D_\022,W#A8\021\007AD8P\004\002rm:\021!/^\007\002g*\021AOB\001\007yI|w\016\036 \n\003\025I!a\036\003\002\017A\f7m[1hK&\021\021P\037\002\004'\026\f(BA<\005!\tiA0\003\002~\t\t\031\021I\\=\t\r}TBQAA\001\003!!\003/\032:dK:$HcA\r\002\004!1\021Q\001@A\002E\nq!\0369eCR,7\017C\004\002\ni!\t!a\003\002\t\r|\007/\037\013\0163\0055\021qBA\t\003'\t)\"a\006\t\021\001\n9\001%AA\002\tB\001\002LA\004!\003\005\rA\t\005\t3\006\035\001\023!a\001c!AQ'a\002\021\002\003\007q\007\003\005>\003\017\001\n\0211\001@\021%)\025q\001I\001\002\004\tI\002E\002qqrAa!!\b\033\t\003\n\023\001\002;fqRD\021\"!\t\033#\003%\t!a\t\002\035\r|\007/\037\023eK\032\fW\017\034;%cU\021\021Q\005\026\004E\005\0352FAA\025!\021\tY#!\016\016\005\0055\"\002BA\030\003c\t\021\"\0368dQ\026\0347.\0323\013\007\005MB!\001\006b]:|G/\031;j_:LA!a\016\002.\t\tRO\\2iK\016\\W\r\032,be&\fgnY3\t\023\005m\"$%A\005\002\005\r\022AD2paf$C-\0324bk2$HE\r\005\n\003Q\022\023!C\001\003\003\nabY8qs\022\"WMZ1vYR$3'\006\002\002D)\032\021'a\n\t\023\005\035#$%A\005\002\005%\023AD2paf$C-\0324bk2$H\005N\013\003\003\027R3aNA\024\021%\tyEGI\001\n\003\t\t&\001\bd_BLH\005Z3gCVdG\017J\033\026\005\005M#fA \002(!I\021q\013\016\022\002\023\005\021\021L\001\017G>\004\030\020\n3fM\006,H\016\036\0237+\t\tYF\013\003\002\032\005\035\002\"\002\021\027\001\004\021\003\"\002\027\027\001\004\021\003\"B-\027\001\004\t\004\"B\033\027\001\0049\004\"B#\027\001\0049\005&\002\f^\003S\022\027EAA6\003%*6/\032\021uQ\026\004s\016\0365fe\002\n\007\017\0357zA5,G\017[8eA%t\007\005\0365jg\002z'M[3di\"1q#\003C\001\003_\"R\"GA9\003g\n)(a\036\002z\005m\004B\002\021\002n\001\007!\005\003\004-\003[\002\rA\t\005\0073\0065\004\031A\031\t\rU\ni\0071\0018\021\031i\024Q\016a\001!1Q)!\034A\002\035Cq!a \n\t\003\t\t)\001\006v]\006\004\b\017\\=TKF$B!a!\002\020B)Q\"!\"\002\n&\031\021q\021\003\003\r=\003H/[8o!%i\0211\022\022#c]\nI\"C\002\002\016\022\021a\001V;qY\026,\004bBAI\003{\002\r\001H\001\002]\"I\021QS\005\002\002\023%\021qS\001\fe\026\fGMU3t_24X\r\006\002\002\032B!\0211TAS\033\t\tiJ\003\003\002 \006\005\026\001\0027b]\036T!!a)\002\t)\fg/Y\005\005\003O\013iJ\001\004PE*,7\r\036")
/*     */ public class Elem extends Node implements Serializable {
/*     */   private final String prefix;
/*     */   
/*     */   private final String label;
/*     */   
/*     */   private final NamespaceBinding scope;
/*     */   
/*     */   private final boolean minimizeEmpty;
/*     */   
/*     */   private final Seq<Node> child;
/*     */   
/*     */   private final MetaData attributes;
/*     */   
/*     */   public String prefix() {
/*  54 */     return this.prefix;
/*     */   }
/*     */   
/*     */   public Elem(String prefix, String label, MetaData attributes1, NamespaceBinding scope, boolean minimizeEmpty, Seq<Node> child) {
/*  70 */     this.attributes = MetaData$.MODULE$.normalize(attributes1, scope);
/*  72 */     if (prefix == null) {
/*  72 */       if ("" != null)
/*  75 */         if (scope == null)
/*  76 */           throw new IllegalArgumentException("scope is null, use scala.xml.TopScope for empty scope");  
/*     */     } else {
/*     */       if (prefix.equals(""))
/*     */         throw new IllegalArgumentException("prefix of zero length, use null instead"); 
/*     */       if (scope == null)
/*  76 */         throw new IllegalArgumentException("scope is null, use scala.xml.TopScope for empty scope"); 
/*     */     } 
/*     */     throw new IllegalArgumentException("prefix of zero length, use null instead");
/*     */   }
/*     */   
/*     */   public String label() {
/*     */     return this.label;
/*     */   }
/*     */   
/*     */   public NamespaceBinding scope() {
/*     */     return this.scope;
/*     */   }
/*     */   
/*     */   public boolean minimizeEmpty() {
/*     */     return this.minimizeEmpty;
/*     */   }
/*     */   
/*     */   public Seq<Node> child() {
/*     */     return this.child;
/*     */   }
/*     */   
/*     */   public Elem(String prefix, String label, MetaData attributes, NamespaceBinding scope, Seq<Node> child) {
/*     */     this(prefix, label, attributes, scope, child.isEmpty(), child);
/*     */   }
/*     */   
/*     */   public final boolean doCollectNamespaces() {
/*     */     return true;
/*     */   }
/*     */   
/*     */   public final boolean doTransform() {
/*     */     return true;
/*     */   }
/*     */   
/*     */   public MetaData attributes() {
/*     */     return this.attributes;
/*     */   }
/*     */   
/*     */   public Seq<Object> basisForHashCode() {
/*  83 */     String str1 = prefix(), str2 = label();
/*  83 */     MetaData metaData = attributes();
/*  83 */     return (Seq<Object>)child().toList().$colon$colon(metaData).$colon$colon(str2).$colon$colon(str1);
/*     */   }
/*     */   
/*     */   public final Elem $percent(MetaData updates) {
/*  92 */     MetaData x$5 = MetaData$.MODULE$.update(attributes(), scope(), updates);
/*  92 */     String x$6 = copy$default$1(), x$7 = copy$default$2();
/*  92 */     NamespaceBinding x$8 = copy$default$4();
/*  92 */     boolean x$9 = copy$default$5();
/*  92 */     Seq<Node> x$10 = copy$default$6();
/*  92 */     return copy(x$6, x$7, x$5, x$8, x$9, x$10);
/*     */   }
/*     */   
/*     */   public String copy$default$1() {
/* 100 */     return prefix();
/*     */   }
/*     */   
/*     */   public String copy$default$2() {
/* 101 */     return label();
/*     */   }
/*     */   
/*     */   public MetaData copy$default$3() {
/* 102 */     return attributes();
/*     */   }
/*     */   
/*     */   public NamespaceBinding copy$default$4() {
/* 103 */     return scope();
/*     */   }
/*     */   
/*     */   public boolean copy$default$5() {
/* 104 */     return minimizeEmpty();
/*     */   }
/*     */   
/*     */   public Seq<Node> copy$default$6() {
/* 105 */     return child().toSeq();
/*     */   }
/*     */   
/*     */   public Elem copy(String prefix, String label, MetaData attributes, NamespaceBinding scope, boolean minimizeEmpty, Seq<Node> child) {
/* 106 */     return Elem$.MODULE$.apply(prefix, label, attributes, scope, minimizeEmpty, child);
/*     */   }
/*     */   
/*     */   public String text() {
/* 110 */     return ((TraversableOnce)child().map((Function1)new Elem$$anonfun$text$1(this), Seq$.MODULE$.canBuildFrom())).mkString();
/*     */   }
/*     */   
/*     */   public static Option<Tuple5<String, String, MetaData, NamespaceBinding, Seq<Node>>> unapplySeq(Node paramNode) {
/*     */     return Elem$.MODULE$.unapplySeq(paramNode);
/*     */   }
/*     */   
/*     */   public class Elem$$anonfun$text$1 extends AbstractFunction1<Node, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(Node x$4) {
/* 110 */       return x$4.text();
/*     */     }
/*     */     
/*     */     public Elem$$anonfun$text$1(Elem $outer) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\xml\Elem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */