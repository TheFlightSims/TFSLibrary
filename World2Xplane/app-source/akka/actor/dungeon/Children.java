/*     */ package akka.actor.dungeon;
/*     */ 
/*     */ import akka.actor.ActorCell;
/*     */ import akka.actor.ActorRef;
/*     */ import akka.actor.ChildRestartStats;
/*     */ import akka.actor.ChildStats;
/*     */ import akka.actor.InternalActorRef;
/*     */ import akka.actor.Props;
/*     */ import akka.serialization.Serialization;
/*     */ import scala.Option;
/*     */ import scala.Serializable;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.Set;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.TraitSetter;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\tMbAC\001\003!\003\r\tA\002\005\003\016\tA1\t[5mIJ,gN\003\002\004\t\0059A-\0368hK>t'BA\003\007\003\025\t7\r^8s\025\0059\021\001B1lW\006\034\"\001A\005\021\005)iQ\"A\006\013\0031\tQa]2bY\006L!AD\006\003\r\005s\027PU3g\021\025\001\002\001\"\001\023\003\031!\023N\\5uI\r\001A#A\n\021\005)!\022BA\013\f\005\021)f.\033;\t\017]\001\001\031!C\0051\005\001sl\0315jY\022\024XM\034*fMN$uNT8u\007\006dG.T3ESJ,7\r\0367z+\005I\002C\001\016\034\033\005\021\021B\001\017\003\005E\031\005.\0337ee\026t7i\0348uC&tWM\035\005\b=\001\001\r\021\"\003 \003\021z6\r[5mIJ,gNU3gg\022{gj\034;DC2dW*\032#je\026\034G\017\\=`I\025\fHCA\n!\021\035\tS$!AA\002e\t1\001\037\0232\021\031\031\003\001)Q\0053\005\tsl\0315jY\022\024XM\034*fMN$uNT8u\007\006dG.T3ESJ,7\r\0367zA!\022!%\n\t\003\025\031J!aJ\006\003\021Y|G.\031;jY\026DQ!\013\001\005\002a\tAb\0315jY\022\024XM\034*fMNDQa\013\001\005\0061\n\001b\0315jY\022\024XM\\\013\002[A\031afM\033\016\003=R!\001M\031\002\023%lW.\036;bE2,'B\001\032\f\003)\031w\016\0347fGRLwN\\\005\003i=\022\001\"\023;fe\006\024G.\032\t\003m]j\021\001B\005\003q\021\021\001\"Q2u_J\024VM\032\005\006u\001!)aO\001\fO\026$8\t[5mIJ,g\016F\001=!\ri$)N\007\002})\021q\bQ\001\005Y\006twMC\001B\003\021Q\027M^1\n\005Qr\004\"\002#\001\t\013)\025!B2iS2$GC\001$J!\rQq)N\005\003\021.\021aa\0249uS>t\007\"\002&D\001\004Y\025\001\0028b[\026\004\"\001T(\017\005)i\025B\001(\f\003\031\001&/\0323fM&\021\001+\025\002\007'R\024\030N\\4\013\0059[\001\"B*\001\t\013!\026\001C4fi\016C\027\016\0343\025\005U*\006\"\002&S\001\004Y\005\"B,\001\t\003A\026aB1di>\024xJ\032\013\003keCQA\027,A\002m\013Q\001\035:paN\004\"A\016/\n\005u#!!\002)s_B\034\b\"B,\001\t\003yFcA\033aC\")!L\030a\0017\")!J\030a\001\027\"11\r\001C\001\r\021\f1\"\031;uC\016D7\t[5mIR\031Q'\0324\t\013i\023\007\031A.\t\013\035\024\007\031\0015\002\033ML8\017^3n'\026\024h/[2f!\tQ\021.\003\002k\027\t9!i\\8mK\006t\007BB2\001\t\0031A\016\006\0036[:|\007\"\002.l\001\004Y\006\"\002&l\001\004Y\005\"B4l\001\004A\007bB9\001\001\004%IA]\001\035?:,\007\020\036(b[\026$uNT8u\007\006dG.T3ESJ,7\r\0367z+\005\031\bC\001\006u\023\t)8B\001\003M_:<\007bB<\001\001\004%I\001_\001!?:,\007\020\036(b[\026$uNT8u\007\006dG.T3ESJ,7\r\0367z?\022*\027\017\006\002\024s\"9\021E^A\001\002\004\031\bBB>\001A\003&1/A\017`]\026DHOT1nK\022{gj\034;DC2dW*\032#je\026\034G\017\\=!Q\tQX\005C\003\001\021Uq0\001\006sC:$w.\034(b[\026$\022a\023\005\b\003\007\001AQAA\003\003\021\031Ho\0349\025\007M\t9\001\003\004\006\003\003\001\r!\016\005\b\003\027\001AQBA\007\003A\031x/\0319DQ&dGM]3o%\02647\017F\003i\003\037\t\031\002C\004\002\022\005%\001\031A\r\002\027=dGm\0215jY\022\024XM\034\005\b\003+\tI\0011\001\032\003-qWm^\"iS2$'/\0328)\t\005%\021\021\004\t\004\025\005m\021bAA\017\027\t1\021N\0347j]\026Dq!!\t\001\t\013\t\031#\001\007sKN,'O^3DQ&dG\rF\002i\003KAaASA\020\001\004Y\005\006BA\020\003S\001B!a\013\00225\021\021Q\006\006\004\003_Y\021AC1o]>$\030\r^5p]&!\0211GA\027\005\035!\030-\0337sK\016Dq!a\016\001\t+\tI$\001\bv]J,7/\032:wK\016C\027\016\0343\025\007!\fY\004\003\004K\003k\001\ra\023\025\005\003k\tI\003C\004\002B\001!)!a\021\002\023%t\027\016^\"iS2$G\003BA#\003\033\002BAC$\002HA\031a'!\023\n\007\005-CAA\tDQ&dGMU3ti\006\024Ho\025;biNDq!a\024\002@\001\007Q'A\002sK\032DC!a\020\002*!9\021Q\013\001\005\026\005]\023\001H:fi\016C\027\016\0343sK:$VM]7j]\006$\030n\0348SK\006\034xN\034\013\004Q\006e\003\002CA.\003'\002\r!!\030\002\rI,\027m]8o!\021\ty&!\032\017\007i\t\t'C\002\002d\t\t\021c\0215jY\022\024XM\\\"p]R\f\027N\\3s\023\021\t9'!\033\003\033M+8\017]3oIJ+\027m]8o\025\r\t\031G\001\025\005\003'\nI\003\003\004\002p\001!)BE\001\016g\026$H+\032:nS:\fG/\0323\t\017\005M\004\001\"\005\002v\005A\021n\035(pe6\fG.F\001i\021\035\tI\b\001C\t\003k\nQ\"[:UKJl\027N\\1uS:<\007bBA?\001\021E\021qP\001\031o\006LG/\0338h\r>\0248\t[5mIJ,gn\024:Ok2dWCAAA%\031\t\031)!\030\002\b\0321\021Q\021\001\001\003\003\023A\002\020:fM&tW-\\3oiz\002B!a\030\002\n&!\0211RA5\005I9\026-\033;j]\0364uN]\"iS2$'/\0328\t\017\005=\005\001\"\005\002\022\006y1/^:qK:$7\t[5mIJ,g\016F\002\024\003'C!\"!&\002\016B\005\t\031AAL\003%)\007pY3qi\032{'\017\005\003M\0033+\024bAAN#\n\0311+\032;\t\017\005}\005\001\"\005\002\"\006q!/Z:v[\026\034\005.\0337ee\026tG#B\n\002$\006}\006\002CAS\003;\003\r!a*\002\037\r\fWo]3e\005f4\025-\0337ve\026\004B!!+\002::!\0211VA[\035\021\ti+a-\016\005\005=&bAAY#\0051AH]8pizJ\021\001D\005\004\003o[\021a\0029bG.\fw-Z\005\005\003w\013iLA\005UQJ|w/\0312mK*\031\021qW\006\t\017\005\005\027Q\024a\001k\005!\001/\032:q\021\035\t)\r\001C\001\003\017\fabZ3u\007\"LG\016\032\"z\035\006lW\r\006\003\002J\006E\007\003\002\006H\003\027\0042ANAg\023\r\ty\r\002\002\013\007\"LG\016Z*uCR\034\bB\002&\002D\002\0071\nC\004\002V\002!\t\"a6\002\033\035,Go\0215jY\022\024\025PU3g)\021\t)%!7\t\017\005=\0231\033a\001k!9\021Q\034\001\005\022\005}\027\001E4fi\006cGn\0215jY\022\034F/\031;t+\t\t\t\017\005\003/g\005\035\003bBAs\001\021\005\023q]\001\017O\026$8+\0338hY\026\034\005.\0337e)\021\tI/a<\021\007Y\nY/C\002\002n\022\021\001#\0238uKJt\027\r\\!di>\024(+\0324\t\r)\013\031\0171\001L\021\035\t\031\020\001C\t\003k\fAD]3n_Z,7\t[5mI\006sGmR3u'R\fG/Z\"iC:<W\r\006\003\002x\006e\b\003\002\006H\003;Ba\001RAy\001\004)\004bBA\001\021%\021q`\001\nG\",7m\033(b[\026$2a\023B\001\021\031Q\0251 a\001\027\"9!Q\001\001\005\n\t\035\021!C7bW\026\034\005.\0337e)-)$\021\002B\n\005+\0219Ba\007\t\021\t-!1\001a\001\005\033\tAaY3mYB\031aGa\004\n\007\tEAAA\005BGR|'oQ3mY\"1!La\001A\002mCaA\023B\002\001\004Y\005b\002B\r\005\007\001\r\001[\001\006CNLhn\031\005\007O\n\r\001\031\0015\t\023\t}\001!%A\005\022\t\005\022!G:vgB,g\016Z\"iS2$'/\0328%I\0264\027-\0367uIE*\"Aa\t+\t\005]%QE\026\003\005O\001BA!\013\00305\021!1\006\006\005\005[\ti#A\005v]\016DWmY6fI&!!\021\007B\026\005E)hn\0315fG.,GMV1sS\006t7-\032")
/*     */ public interface Children {
/*     */   ChildrenContainer akka$actor$dungeon$Children$$_childrenRefsDoNotCallMeDirectly();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$actor$dungeon$Children$$_childrenRefsDoNotCallMeDirectly_$eq(ChildrenContainer paramChildrenContainer);
/*     */   
/*     */   ChildrenContainer childrenRefs();
/*     */   
/*     */   Iterable<ActorRef> children();
/*     */   
/*     */   Iterable<ActorRef> getChildren();
/*     */   
/*     */   Option<ActorRef> child(String paramString);
/*     */   
/*     */   ActorRef getChild(String paramString);
/*     */   
/*     */   ActorRef actorOf(Props paramProps);
/*     */   
/*     */   ActorRef actorOf(Props paramProps, String paramString);
/*     */   
/*     */   ActorRef attachChild(Props paramProps, boolean paramBoolean);
/*     */   
/*     */   ActorRef attachChild(Props paramProps, String paramString, boolean paramBoolean);
/*     */   
/*     */   long akka$actor$dungeon$Children$$_nextNameDoNotCallMeDirectly();
/*     */   
/*     */   @TraitSetter
/*     */   void akka$actor$dungeon$Children$$_nextNameDoNotCallMeDirectly_$eq(long paramLong);
/*     */   
/*     */   String randomName();
/*     */   
/*     */   void stop(ActorRef paramActorRef);
/*     */   
/*     */   boolean reserveChild(String paramString);
/*     */   
/*     */   boolean unreserveChild(String paramString);
/*     */   
/*     */   Option<ChildRestartStats> initChild(ActorRef paramActorRef);
/*     */   
/*     */   boolean setChildrenTerminationReason(ChildrenContainer.SuspendReason paramSuspendReason);
/*     */   
/*     */   void setTerminated();
/*     */   
/*     */   boolean isNormal();
/*     */   
/*     */   boolean isTerminating();
/*     */   
/*     */   ChildrenContainer.SuspendReason waitingForChildrenOrNull();
/*     */   
/*     */   void suspendChildren(Set<ActorRef> paramSet);
/*     */   
/*     */   Set<ActorRef> suspendChildren$default$1();
/*     */   
/*     */   void resumeChildren(Throwable paramThrowable, ActorRef paramActorRef);
/*     */   
/*     */   Option<ChildStats> getChildByName(String paramString);
/*     */   
/*     */   Option<ChildRestartStats> getChildByRef(ActorRef paramActorRef);
/*     */   
/*     */   Iterable<ChildRestartStats> getAllChildStats();
/*     */   
/*     */   InternalActorRef getSingleChild(String paramString);
/*     */   
/*     */   Option<ChildrenContainer.SuspendReason> removeChildAndGetStateChange(ActorRef paramActorRef);
/*     */   
/*     */   public class Children$$anonfun$suspendChildren$1 extends AbstractFunction1<ChildRestartStats, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Set exceptFor$1;
/*     */     
/*     */     public final void apply(ChildRestartStats x0$1) {
/* 121 */       ChildRestartStats childRestartStats = x0$1;
/* 121 */       if (childRestartStats != null) {
/* 122 */         ActorRef child = childRestartStats.child();
/* 122 */         if (!this.exceptFor$1.contains(child)) {
/* 122 */           ((InternalActorRef)child).suspend();
/* 122 */           BoxedUnit boxedUnit1 = BoxedUnit.UNIT;
/*     */           return;
/*     */         } 
/*     */       } 
/* 123 */       BoxedUnit boxedUnit = BoxedUnit.UNIT;
/*     */     }
/*     */     
/*     */     public Children$$anonfun$suspendChildren$1(ActorCell $outer, Set exceptFor$1) {}
/*     */   }
/*     */   
/*     */   public class Children$$anonfun$resumeChildren$1 extends AbstractFunction1<ChildRestartStats, BoxedUnit> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Throwable causedByFailure$1;
/*     */     
/*     */     private final ActorRef perp$1;
/*     */     
/*     */     public final void apply(ChildRestartStats x0$2) {
/*     */       // Byte code:
/*     */       //   0: aload_1
/*     */       //   1: astore_2
/*     */       //   2: aload_2
/*     */       //   3: ifnull -> 74
/*     */       //   6: aload_2
/*     */       //   7: invokevirtual child : ()Lakka/actor/ActorRef;
/*     */       //   10: astore_3
/*     */       //   11: aload_3
/*     */       //   12: instanceof akka/actor/InternalActorRef
/*     */       //   15: ifeq -> 74
/*     */       //   18: aload_3
/*     */       //   19: checkcast akka/actor/InternalActorRef
/*     */       //   22: astore #4
/*     */       //   24: aload #4
/*     */       //   26: aload_0
/*     */       //   27: getfield perp$1 : Lakka/actor/ActorRef;
/*     */       //   30: aload #4
/*     */       //   32: astore #6
/*     */       //   34: dup
/*     */       //   35: ifnonnull -> 47
/*     */       //   38: pop
/*     */       //   39: aload #6
/*     */       //   41: ifnull -> 55
/*     */       //   44: goto -> 62
/*     */       //   47: aload #6
/*     */       //   49: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */       //   52: ifeq -> 62
/*     */       //   55: aload_0
/*     */       //   56: getfield causedByFailure$1 : Ljava/lang/Throwable;
/*     */       //   59: goto -> 65
/*     */       //   62: aconst_null
/*     */       //   63: pop
/*     */       //   64: aconst_null
/*     */       //   65: invokevirtual resume : (Ljava/lang/Throwable;)V
/*     */       //   68: getstatic scala/runtime/BoxedUnit.UNIT : Lscala/runtime/BoxedUnit;
/*     */       //   71: astore #5
/*     */       //   73: return
/*     */       //   74: new scala/MatchError
/*     */       //   77: dup
/*     */       //   78: aload_2
/*     */       //   79: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   82: athrow
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #127	-> 0
/*     */       //   #128	-> 6
/*     */       //   #129	-> 24
/*     */       //   #127	-> 73
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   0	83	0	this	Lakka/actor/dungeon/Children$$anonfun$resumeChildren$1;
/*     */       //   0	83	1	x0$2	Lakka/actor/ChildRestartStats;
/*     */       //   11	72	3	child	Lakka/actor/ActorRef;
/*     */     }
/*     */     
/*     */     public Children$$anonfun$resumeChildren$1(ActorCell $outer, Throwable causedByFailure$1, ActorRef perp$1) {}
/*     */   }
/*     */   
/*     */   public class Children$$anonfun$makeChild$2 extends AbstractFunction1<Object, Object> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Serialization ser$1;
/*     */     
/*     */     public Children$$anonfun$makeChild$2(ActorCell $outer, Serialization ser$1) {}
/*     */     
/*     */     public final boolean apply(Object arg) {
/* 191 */       return !(!(arg instanceof akka.actor.NoSerializationVerificationNeeded) && 
/* 192 */         this.ser$1.deserialize((byte[])this.ser$1.serialize(arg).get(), arg.getClass()).get() == null);
/*     */     }
/*     */   }
/*     */   
/*     */   public class Children$$anonfun$makeChild$1 extends AbstractFunction1.mcVI.sp implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final InternalActorRef actor$1;
/*     */     
/*     */     public final void apply(int _) {
/* 219 */       apply$mcVI$sp(_);
/*     */     }
/*     */     
/*     */     public void apply$mcVI$sp(int _) {
/* 219 */       this.actor$1.suspend();
/*     */     }
/*     */     
/*     */     public Children$$anonfun$makeChild$1(ActorCell $outer, InternalActorRef actor$1) {}
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\dungeon\Children.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */