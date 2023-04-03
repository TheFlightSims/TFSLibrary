/*     */ package akka.routing;
/*     */ 
/*     */ import akka.actor.ActorPath;
/*     */ import akka.actor.Props;
/*     */ import scala.Option;
/*     */ import scala.collection.Iterator;
/*     */ import scala.reflect.ScalaSignature;
/*     */ 
/*     */ @ScalaSignature(bytes = "\006\001\005]a!B\001\003\003\0039!\001\003(p%>,H/\032:\013\005\r!\021a\002:pkRLgn\032\006\002\013\005!\021m[6b\007\001\0312\001\001\005\017!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fMB\021q\002E\007\002\005%\021\021C\001\002\r%>,H/\032:D_:4\027n\032\005\006'\001!\t\001F\001\007y%t\027\016\036 \025\003U\001\"a\004\001)\007\0019\"\004\005\002\n1%\021\021D\003\002\021'\026\024\030.\0317WKJ\034\030n\0348V\023\022s\022!A\004\0069\tA\t)H\001\t\035>\024v.\036;feB\021qB\b\004\006\003\tA\tiH\n\005=U\0013\005\005\002\nC%\021!E\003\002\b!J|G-^2u!\tIA%\003\002&\025\ta1+\032:jC2L'0\0312mK\")1C\bC\001OQ\tQ\004C\003*=\021\005#&\001\007de\026\fG/\032*pkR,'\017\006\002,]A\021q\002L\005\003[\t\021aAU8vi\026\024\b\"B\030)\001\004\001\024AB:zgR,W\016\005\0022i5\t!G\003\0024\t\005)\021m\031;pe&\021QG\r\002\f\003\016$xN]*zgR,W\016\003\0048=\021\005C\001O\001\022GJ,\027\r^3S_V$XM]!di>\024H#A\035\021\005=Q\024BA\036\003\005-\021v.\036;fe\006\033Go\034:\t\013urB\021\t \002!I|W\017^3s\t&\034\b/\031;dQ\026\024X#A \021\005\001\033eBA\005B\023\t\021%\"\001\004Qe\026$WMZ\005\003\t\026\023aa\025;sS:<'B\001\"\013\021\0259e\004\"\021I\00319\030\016\0365GC2d'-Y2l)\tq\021\nC\003K\r\002\007a\"A\003pi\",'\017C\003M=\021\005Q*A\006hKRLen\035;b]\016,W#\001(\017\005=Y\002\"\002)\037\t\003\t\026!\0029s_B\034HC\001*V!\t\t4+\003\002Ue\t)\001K]8qg\")ak\024a\001%\006Y!o\\;uK\026\004&o\0349t\021\035Af$!A\005Be\013Q\002\035:pIV\034G\017\025:fM&DX#\001.\021\005m\003W\"\001/\013\005us\026\001\0027b]\036T\021aX\001\005U\0064\030-\003\002E9\"9!MHA\001\n\003\031\027\001\0049s_\022,8\r^!sSRLX#\0013\021\005%)\027B\0014\013\005\rIe\016\036\005\bQz\t\t\021\"\001j\0039\001(o\0343vGR,E.Z7f]R$\"A[7\021\005%Y\027B\0017\013\005\r\te.\037\005\b]\036\f\t\0211\001e\003\rAH%\r\005\baz\t\t\021\"\021r\003=\001(o\0343vGRLE/\032:bi>\024X#\001:\021\007M4(.D\001u\025\t)(\"\001\006d_2dWm\031;j_:L!a\036;\003\021%#XM]1u_JDq!\037\020\002\002\023\005!0\001\005dC:,\025/^1m)\tYh\020\005\002\ny&\021QP\003\002\b\005>|G.Z1o\021\035q\0070!AA\002)D\021\"!\001\037\003\003%\t%a\001\002\021!\f7\017[\"pI\026$\022\001\032\005\n\003\017q\022\021!C!\003\023\t\001\002^8TiJLgn\032\013\0025\"I\021Q\002\020\002\002\023%\021qB\001\fe\026\fGMU3t_24X\r\006\002\002\022A\0311,a\005\n\007\005UAL\001\004PE*,7\r\036")
/*     */ public abstract class NoRouter implements RouterConfig {
/*     */   public static final long serialVersionUID = 1L;
/*     */   
/*     */   public static boolean canEqual(Object paramObject) {
/*     */     return NoRouter$.MODULE$.canEqual(paramObject);
/*     */   }
/*     */   
/*     */   public static Iterator<Object> productIterator() {
/*     */     return NoRouter$.MODULE$.productIterator();
/*     */   }
/*     */   
/*     */   public static Object productElement(int paramInt) {
/*     */     return NoRouter$.MODULE$.productElement(paramInt);
/*     */   }
/*     */   
/*     */   public static int productArity() {
/*     */     return NoRouter$.MODULE$.productArity();
/*     */   }
/*     */   
/*     */   public static String productPrefix() {
/*     */     return NoRouter$.MODULE$.productPrefix();
/*     */   }
/*     */   
/*     */   public static Props props(Props paramProps) {
/*     */     return NoRouter$.MODULE$.props(paramProps);
/*     */   }
/*     */   
/*     */   public static NoRouter$ getInstance() {
/*     */     return NoRouter$.MODULE$.getInstance();
/*     */   }
/*     */   
/*     */   public Option<Props> routingLogicController(RoutingLogic routingLogic) {
/* 335 */     return RouterConfig$class.routingLogicController(this, routingLogic);
/*     */   }
/*     */   
/*     */   public boolean isManagementMessage(Object msg) {
/* 335 */     return RouterConfig$class.isManagementMessage(this, msg);
/*     */   }
/*     */   
/*     */   public boolean stopRouterWhenAllRouteesRemoved() {
/* 335 */     return RouterConfig$class.stopRouterWhenAllRouteesRemoved(this);
/*     */   }
/*     */   
/*     */   public RouterConfig withFallback(RouterConfig other) {
/* 335 */     return RouterConfig$class.withFallback(this, other);
/*     */   }
/*     */   
/*     */   public void verifyConfig(ActorPath path) {
/* 335 */     RouterConfig$class.verifyConfig(this, path);
/*     */   }
/*     */   
/*     */   public NoRouter() {
/* 335 */     RouterConfig$class.$init$(this);
/*     */   }
/*     */ }


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\NoRouter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */