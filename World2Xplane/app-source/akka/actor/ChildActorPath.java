/*     */ package akka.actor;
/*     */ 
/*     */ import akka.routing.MurmurHash$;
/*     */ import scala.Function1;
/*     */ import scala.MatchError;
/*     */ import scala.Predef$;
/*     */ import scala.Serializable;
/*     */ import scala.Tuple2;
/*     */ import scala.collection.Iterable;
/*     */ import scala.collection.immutable.Iterable;
/*     */ import scala.collection.immutable.List;
/*     */ import scala.collection.immutable.Nil$;
/*     */ import scala.collection.immutable.StringOps;
/*     */ import scala.reflect.ScalaSignature;
/*     */ import scala.runtime.AbstractFunction1;
/*     */ import scala.runtime.BoxedUnit;
/*     */ import scala.runtime.BoxesRunTime;
/*     */ import scala.runtime.ScalaRunTime$;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005}b\001B\001\003\005\035\021ab\0215jY\022\f5\r^8s!\006$\bN\003\002\004\t\005)\021m\031;pe*\tQ!\001\003bW.\f7\001A\n\004\001!q\001CA\005\r\033\005Q!\"A\006\002\013M\034\027\r\\1\n\0055Q!AB!osJ+g\r\005\002\020!5\t!!\003\002\022\005\tI\021i\031;peB\013G\017\033\005\t'\001\021)\031!C\001)\0051\001/\031:f]R,\022A\004\005\t-\001\021\t\021)A\005\035\0059\001/\031:f]R\004\003\002\003\r\001\005\013\007I\021A\r\002\t9\fW.Z\013\0025A\0211D\b\b\003\023qI!!\b\006\002\rA\023X\rZ3g\023\ty\002E\001\004TiJLgn\032\006\003;)A\001B\t\001\003\002\003\006IAG\001\006]\006lW\r\t\005\nI\001\021)\031!C!\t\025\n1!^5e+\0051\003CA\005(\023\tA#BA\002J]RD\001B\013\001\003\002\003\006IAJ\001\005k&$\007\005\003\004-\001\021\005A!L\001\007y%t\027\016\036 \025\t9z\003'\r\t\003\037\001AQaE\026A\0029AQ\001G\026A\002iAQ\001J\026A\002\031BQ\001\f\001\005\002M\"2A\f\0336\021\025\031\"\0071\001\017\021\025A\"\0071\001\033\021\0259\004\001\"\0219\003\035\tG\r\032:fgN,\022!\017\t\003\037iJ!a\017\002\003\017\005#GM]3tg\")Q\b\001C!}\005!A\005Z5w)\tqq\bC\003Ay\001\007!$A\003dQ&dG\rC\003C\001\021\0053)\001\005fY\026lWM\034;t+\005!\005cA#K55\taI\003\002H\021\006I\021.\\7vi\006\024G.\032\006\003\023*\t!bY8mY\026\034G/[8o\023\tYeI\001\005Ji\026\024\030M\0317f\021\025i\005\001\"\021O\003\021\021xn\034;\026\003=\003\"a\004)\n\005E\023!!\004*p_R\f5\r^8s!\006$\b\016\003\004T\001\021\005C\001V\001\bo&$\b.V5e)\tqQ\013C\003%%\002\007a\005C\003X\001\021\005\003,\001\005u_N#(/\0338h)\005Q\002\"\002.\001\t\003J\022!\006;p'\026\024\030.\0317ju\006$\030n\0348G_Jl\027\r\036\005\0069\002!I!J\001\017i>\034FO]5oO2+gn\032;i\021\035q\006A1A\005\n\025\na\002^8TiJLgnZ(gMN,G\017\003\004a\001\001\006IAJ\001\020i>\034FO]5oO>3gm]3uA!)!\r\001C!G\006\031Bo\\*ue&twmV5uQ\006#GM]3tgR\021!\004\032\005\006K\006\004\r!O\001\005C\022$'\017C\003h\001\021\005\003.\001\021u_N+'/[1mSj\fG/[8o\r>\024X.\031;XSRD\027\t\0323sKN\034HC\001\016j\021\025)g\r1\001:\021\025Y\007\001\"\003m\003]\tG\r\032:fgN\034FO]5oO2+gn\032;i\t&4g\r\006\002'[\")QM\033a\001s!)q\016\001C\005a\006i!-^5mIR{7\013\036:j]\036$R!]=|{~\004\"A]<\016\003MT!\001^;\002\t1\fgn\032\006\002m\006!!.\031<b\023\tA8OA\007TiJLgn\032\"vS2$WM\035\005\006u:\004\r!]\001\003g\nDQ\001 8A\002\031\na\001\\3oORD\007\"\002@o\001\0041\023\001\0023jM\032Dq!!\001o\001\004\t\031!\001\006s_>$8\013\036:j]\036\004R!CA\003\037jI1!a\002\013\005%1UO\\2uS>t\027\007C\004\002\f\001!I!!\004\002#\005\004\b/\0328e+&$gI]1h[\026tG\017F\002r\003\037AaA_A\005\001\004\t\bbBA\n\001\021\005\023QC\001\007KF,\030\r\\:\025\t\005]\021Q\004\t\004\023\005e\021bAA\016\025\t9!i\\8mK\006t\007\002CA\020\003#\001\r!!\t\002\013=$\b.\032:\021\007%\t\031#C\002\002&)\0211!\0218z\021\035\tI\003\001C!\003W\t\001\002[1tQ\016{G-\032\013\002M!9\021q\006\001\005B\005E\022!C2p[B\f'/\032+p)\r1\0231\007\005\b\003?\ti\0031\001\017Q\025\001\021qGA\037!\rI\021\021H\005\004\003wQ!\001E*fe&\fGNV3sg&|g.V%E=\005\t\001")
/*     */ public final class ChildActorPath implements ActorPath {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   private final ActorPath parent;
/*     */   
/*     */   private final String name;
/*     */   
/*     */   private final int uid;
/*     */   
/*     */   private final int toStringOffset;
/*     */   
/*     */   public ActorPath child(String child) {
/* 195 */     return ActorPath$class.child(this, child);
/*     */   }
/*     */   
/*     */   public ActorPath $div(Iterable child) {
/* 195 */     return ActorPath$class.$div(this, child);
/*     */   }
/*     */   
/*     */   public ActorPath descendant(Iterable names) {
/* 195 */     return ActorPath$class.descendant(this, names);
/*     */   }
/*     */   
/*     */   public Iterable<String> getElements() {
/* 195 */     return ActorPath$class.getElements(this);
/*     */   }
/*     */   
/*     */   public String toStringWithoutAddress() {
/* 195 */     return ActorPath$class.toStringWithoutAddress(this);
/*     */   }
/*     */   
/*     */   public ActorPath parent() {
/* 195 */     return this.parent;
/*     */   }
/*     */   
/*     */   public String name() {
/* 195 */     return this.name;
/*     */   }
/*     */   
/*     */   public int uid() {
/* 195 */     return this.uid;
/*     */   }
/*     */   
/*     */   public ChildActorPath(ActorPath parent, String name, int uid) {
/*     */     int i;
/* 195 */     ActorPath$class.$init$(this);
/* 196 */     if (name.indexOf('/') != -1)
/* 196 */       throw new IllegalArgumentException((new StringOps(Predef$.MODULE$.augmentString("/ is a path separator and is not legal in ActorPath names: [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { name }))); 
/* 197 */     if (name.indexOf('#') != -1)
/* 197 */       throw new IllegalArgumentException((new StringOps(Predef$.MODULE$.augmentString("# is a fragment separator and is not legal in ActorPath names: [%s]"))).format(Predef$.MODULE$.genericWrapArray(new Object[] { name }))); 
/* 246 */     ActorPath actorPath = parent;
/* 247 */     if (actorPath instanceof RootActorPath) {
/* 247 */       RootActorPath rootActorPath = (RootActorPath)actorPath;
/* 247 */       i = rootActorPath.address().toString().length() + rootActorPath.name().length();
/* 248 */     } else if (actorPath instanceof ChildActorPath) {
/* 248 */       ChildActorPath childActorPath = (ChildActorPath)actorPath;
/* 248 */       i = childActorPath.toStringLength() + 1;
/*     */     } else {
/*     */       throw new MatchError(actorPath);
/*     */     } 
/*     */     this.toStringOffset = i;
/*     */   }
/*     */   
/*     */   public ChildActorPath(ActorPath parent, String name) {
/*     */     this(parent, name, 0);
/*     */   }
/*     */   
/*     */   public Address address() {
/*     */     return root().address();
/*     */   }
/*     */   
/*     */   public ActorPath $div(String child) {
/*     */     Tuple2<String, Object> tuple2 = ActorCell$.MODULE$.splitNameAndUid(child);
/*     */     if (tuple2 != null) {
/*     */       String childName = (String)tuple2._1();
/*     */       int uid = tuple2._2$mcI$sp();
/*     */       Tuple2 tuple22 = new Tuple2(childName, BoxesRunTime.boxToInteger(uid)), tuple21 = tuple22;
/*     */       String str1 = (String)tuple21._1();
/*     */       int i = tuple21._2$mcI$sp();
/*     */       return new ChildActorPath(this, str1, i);
/*     */     } 
/*     */     throw new MatchError(tuple2);
/*     */   }
/*     */   
/*     */   private final Iterable rec$1(ActorPath p, List acc) {
/*     */     while (true) {
/*     */       ActorPath actorPath = p;
/*     */       if (actorPath instanceof RootActorPath)
/*     */         return (Iterable)acc; 
/*     */       String str = p.name();
/*     */       acc = acc.$colon$colon(str);
/*     */       p = p.parent();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Iterable<String> elements() {
/*     */     return rec$1(this, (List)Nil$.MODULE$);
/*     */   }
/*     */   
/*     */   private final RootActorPath rec$2(ActorPath p) {
/*     */     while (true) {
/*     */       ActorPath actorPath = p;
/*     */       if (actorPath instanceof RootActorPath) {
/*     */         RootActorPath rootActorPath;
/*     */         return rootActorPath = (RootActorPath)actorPath;
/*     */       } 
/*     */       p = p.parent();
/*     */     } 
/*     */   }
/*     */   
/*     */   public RootActorPath root() {
/*     */     return rec$2(this);
/*     */   }
/*     */   
/*     */   public ActorPath withUid(int uid) {
/*     */     return (uid == uid()) ? this : new ChildActorPath(parent(), name(), uid);
/*     */   }
/*     */   
/*     */   public String toString() {
/*     */     int length = toStringLength();
/*     */     return buildToString(new StringBuilder(length), length, 0, (Function1<RootActorPath, String>)new ChildActorPath$$anonfun$toString$1(this)).toString();
/*     */   }
/*     */   
/*     */   public class ChildActorPath$$anonfun$toString$1 extends AbstractFunction1<RootActorPath, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(RootActorPath x$5) {
/*     */       return x$5.toString();
/*     */     }
/*     */     
/*     */     public ChildActorPath$$anonfun$toString$1(ChildActorPath $outer) {}
/*     */   }
/*     */   
/*     */   public String toSerializationFormat() {
/*     */     int length = toStringLength();
/*     */     StringBuilder sb = buildToString(new StringBuilder(length + 12), length, 0, (Function1<RootActorPath, String>)new ChildActorPath$$anonfun$1(this));
/*     */     return appendUidFragment(sb).toString();
/*     */   }
/*     */   
/*     */   public class ChildActorPath$$anonfun$1 extends AbstractFunction1<RootActorPath, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     public final String apply(RootActorPath x$6) {
/*     */       return x$6.toString();
/*     */     }
/*     */     
/*     */     public ChildActorPath$$anonfun$1(ChildActorPath $outer) {}
/*     */   }
/*     */   
/*     */   private int toStringLength() {
/*     */     return toStringOffset() + name().length();
/*     */   }
/*     */   
/*     */   private int toStringOffset() {
/*     */     return this.toStringOffset;
/*     */   }
/*     */   
/*     */   public String toStringWithAddress(Address addr) {
/* 252 */     int diff = addressStringLengthDiff(addr);
/* 253 */     int length = toStringLength() + diff;
/* 254 */     return buildToString(new StringBuilder(length), length, diff, (Function1<RootActorPath, String>)new ChildActorPath$$anonfun$toStringWithAddress$1(this, addr)).toString();
/*     */   }
/*     */   
/*     */   public class ChildActorPath$$anonfun$toStringWithAddress$1 extends AbstractFunction1<RootActorPath, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Address addr$1;
/*     */     
/*     */     public final String apply(RootActorPath x$7) {
/* 254 */       return x$7.toStringWithAddress(this.addr$1);
/*     */     }
/*     */     
/*     */     public ChildActorPath$$anonfun$toStringWithAddress$1(ChildActorPath $outer, Address addr$1) {}
/*     */   }
/*     */   
/*     */   public String toSerializationFormatWithAddress(Address addr) {
/* 258 */     int diff = addressStringLengthDiff(addr);
/* 259 */     int length = toStringLength() + diff;
/* 260 */     StringBuilder sb = buildToString(new StringBuilder(length + 12), length, diff, (Function1<RootActorPath, String>)new ChildActorPath$$anonfun$2(this, addr));
/* 261 */     return appendUidFragment(sb).toString();
/*     */   }
/*     */   
/*     */   public class ChildActorPath$$anonfun$2 extends AbstractFunction1<RootActorPath, String> implements Serializable {
/*     */     public static final long serialVersionUID = 0L;
/*     */     
/*     */     private final Address addr$2;
/*     */     
/*     */     public final String apply(RootActorPath x$8) {
/*     */       return x$8.toStringWithAddress(this.addr$2);
/*     */     }
/*     */     
/*     */     public ChildActorPath$$anonfun$2(ChildActorPath $outer, Address addr$2) {}
/*     */   }
/*     */   
/*     */   private int addressStringLengthDiff(Address addr) {
/* 265 */     RootActorPath r = root();
/* 266 */     return r.address().host().isDefined() ? 0 : (
/* 267 */       addr.toString().length() - r.address().toString().length());
/*     */   }
/*     */   
/*     */   private final StringBuilder rec$3(ActorPath p, StringBuilder sb$1, int diff$1, Function1 rootString$1) {
/*     */     while (true) {
/* 282 */       ActorPath actorPath = p;
/* 283 */       if (actorPath instanceof RootActorPath) {
/* 283 */         RootActorPath rootActorPath = (RootActorPath)actorPath;
/* 284 */         String rootStr = (String)rootString$1.apply(rootActorPath);
/* 285 */         return sb$1.replace(0, rootStr.length(), rootStr);
/*     */       } 
/* 286 */       if (actorPath instanceof ChildActorPath) {
/* 286 */         ChildActorPath childActorPath = (ChildActorPath)actorPath;
/* 287 */         int start = childActorPath.toStringOffset() + diff$1;
/* 288 */         int end = start + childActorPath.name().length();
/* 289 */         sb$1.replace(start, end, childActorPath.name());
/* 290 */         (childActorPath != this) ? 
/* 291 */           sb$1.replace(end, end + 1, "/") : BoxedUnit.UNIT;
/* 292 */         p = childActorPath.parent();
/*     */         continue;
/*     */       } 
/*     */       throw new MatchError(actorPath);
/*     */     } 
/*     */   }
/*     */   
/*     */   private StringBuilder buildToString(StringBuilder sb, int length, int diff, Function1 rootString) {
/* 295 */     sb.setLength(length);
/* 296 */     return rec$3(this, sb, diff, rootString);
/*     */   }
/*     */   
/*     */   private StringBuilder appendUidFragment(StringBuilder sb) {
/* 300 */     return (uid() == 0) ? sb : 
/* 301 */       sb.append("#").append(uid());
/*     */   }
/*     */   
/*     */   private final boolean rec$4(ActorPath left, ActorPath right) {
/*     */     // Byte code:
/*     */     //   0: aload_1
/*     */     //   1: aload_2
/*     */     //   2: if_acmpne -> 9
/*     */     //   5: iconst_1
/*     */     //   6: goto -> 92
/*     */     //   9: aload_1
/*     */     //   10: instanceof akka/actor/RootActorPath
/*     */     //   13: ifeq -> 24
/*     */     //   16: aload_1
/*     */     //   17: aload_2
/*     */     //   18: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   21: goto -> 92
/*     */     //   24: aload_2
/*     */     //   25: instanceof akka/actor/RootActorPath
/*     */     //   28: ifeq -> 39
/*     */     //   31: aload_2
/*     */     //   32: aload_1
/*     */     //   33: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   36: goto -> 92
/*     */     //   39: aload_1
/*     */     //   40: invokeinterface name : ()Ljava/lang/String;
/*     */     //   45: aload_2
/*     */     //   46: invokeinterface name : ()Ljava/lang/String;
/*     */     //   51: astore #4
/*     */     //   53: dup
/*     */     //   54: ifnonnull -> 66
/*     */     //   57: pop
/*     */     //   58: aload #4
/*     */     //   60: ifnull -> 74
/*     */     //   63: goto -> 91
/*     */     //   66: aload #4
/*     */     //   68: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   71: ifeq -> 91
/*     */     //   74: aload_1
/*     */     //   75: invokeinterface parent : ()Lakka/actor/ActorPath;
/*     */     //   80: aload_2
/*     */     //   81: invokeinterface parent : ()Lakka/actor/ActorPath;
/*     */     //   86: astore_2
/*     */     //   87: astore_1
/*     */     //   88: goto -> 0
/*     */     //   91: iconst_0
/*     */     //   92: ireturn
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #307	-> 0
/*     */     //   #308	-> 9
/*     */     //   #309	-> 24
/*     */     //   #310	-> 39
/*     */     //   #306	-> 92
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   0	93	0	this	Lakka/actor/ChildActorPath;
/*     */     //   0	93	1	left	Lakka/actor/ActorPath;
/*     */     //   0	93	2	right	Lakka/actor/ActorPath;
/*     */   }
/*     */   
/*     */   public boolean equals(Object other) {
/*     */     boolean bool;
/* 312 */     Object object = other;
/* 313 */     if (object instanceof ActorPath) {
/* 313 */       ActorPath actorPath = (ActorPath)object;
/* 313 */       bool = rec$4(this, actorPath);
/*     */     } else {
/* 314 */       bool = false;
/*     */     } 
/*     */     return bool;
/*     */   }
/*     */   
/*     */   private final int rec$5(ActorPath p, int h, int c, int k) {
/*     */     while (true) {
/* 323 */       ActorPath actorPath = p;
/* 324 */       if (actorPath instanceof RootActorPath) {
/* 324 */         RootActorPath rootActorPath = (RootActorPath)actorPath;
/* 324 */         return MurmurHash$.MODULE$.extendHash(h, ScalaRunTime$.MODULE$.hash(rootActorPath), c, k);
/*     */       } 
/* 325 */       k = MurmurHash$.MODULE$.nextMagicB(k);
/* 325 */       c = MurmurHash$.MODULE$.nextMagicA(c);
/* 325 */       h = MurmurHash$.MODULE$.extendHash(h, MurmurHash$.MODULE$.stringHash(name()), c, k);
/* 325 */       p = p.parent();
/*     */     } 
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 328 */     return MurmurHash$.MODULE$.finalizeHash(rec$5(this, MurmurHash$.MODULE$.startHash(42), MurmurHash$.MODULE$.startMagicA(), MurmurHash$.MODULE$.startMagicB()));
/*     */   }
/*     */   
/*     */   private final int rec$6(ActorPath left, ActorPath right) {
/*     */     while (true) {
/* 338 */       int x = left.name().compareTo(right.name());
/* 339 */       if (x == 0) {
/* 339 */         right = right.parent();
/* 339 */         left = left.parent();
/*     */         continue;
/*     */       } 
/* 340 */       return (left == right) ? 0 : ((left instanceof RootActorPath) ? left.compareTo(right) : ((right instanceof RootActorPath) ? -right.compareTo(left) : x));
/*     */     } 
/*     */   }
/*     */   
/*     */   public int compareTo(ActorPath other) {
/* 343 */     return rec$6(this, other);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ChildActorPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */