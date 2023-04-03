package akka.actor;

import scala.Option;
import scala.collection.Iterable;
import scala.concurrent.Future;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(bytes = "\006\001\005udaB\001\003!\003\r\na\002\002\021\003\016$xN\035*fMB\023xN^5eKJT!a\001\003\002\013\005\034Go\034:\013\003\025\tA!Y6lC\016\0011C\001\001\t!\tIA\"D\001\013\025\005Y\021!B:dC2\f\027BA\007\013\005\031\te.\037*fM\")q\002\001D\001!\005a!o\\8u\017V\f'\017Z5b]V\t\021\003\005\002\023'5\t!!\003\002\025\005\t\001\022J\034;fe:\fG.Q2u_J\024VM\032\005\006-\0011\taF\001\017e>|GoR;be\022L\027M\\!u)\tA2\004\005\002\0233%\021!D\001\002\t\003\016$xN\035*fM\")A$\006a\001;\0059\021\r\0323sKN\034\bC\001\n\037\023\ty\"AA\004BI\022\024Xm]:\t\013\005\002a\021\001\022\002\021\035,\030M\0353jC:,\022a\t\t\003%\021J!!\n\002\003\0331{7-\0317BGR|'OU3g\021\0259\003A\"\001#\0039\031\030p\035;f[\036+\030M\0353jC:DQ!\013\001\007\002)\n1\002Z3bI2+G\017^3sgV\t\001\004C\003-\001\031\005Q&\001\005s_>$\b+\031;i+\005q\003C\001\n0\023\t\001$AA\005BGR|'\017U1uQ\")!\007\001D\001g\005A1/\032;uS:<7/F\0015!\t)\004H\004\002\023m%\021qGA\001\f\003\016$xN]*zgR,W.\003\002:u\tA1+\032;uS:<7O\003\0028\005!)A\b\001D\001{\005!\021N\\5u)\tq\024\t\005\002\n%\021\001I\003\002\005+:LG\017C\003Cw\001\0071)\001\004tsN$X-\034\t\003%\021K!!\022\002\003\037\005\033Go\034:TsN$X-\\%na2DQa\022\001\007\002!\013\001\002Z3qY>LXM]\013\002\023B\021!CS\005\003\027\n\021\001\002R3qY>LXM\035\005\006\033\0021\tAT\001\ti\026l\007\017U1uQR\ta\006C\003Q\001\031\005\001#A\007uK6\0048i\0348uC&tWM\035\005\006%\0021\taU\001\022e\026<\027n\035;feR+W\016]!di>\024Hc\001 U-\")Q+\025a\001#\005A\021m\031;peJ+g\rC\003X#\002\007a&\001\003qCRD\007\"B-\001\r\003Q\026aE;oe\026<\027n\035;feR+W\016]!di>\024HC\001 \\\021\0259\006\f1\001/\021\025i\006A\"\001_\003\035\t7\r^8s\037\032$\022\"E0aK\036DW.^<\t\013\tc\006\031A\"\t\013\005d\006\031\0012\002\013A\024x\016]:\021\005I\031\027B\0013\003\005\025\001&o\0349t\021\0251G\f1\001\022\003)\031X\017]3sm&\034xN\035\005\006/r\003\rA\f\005\006Sr\003\rA[\001\016gf\034H/Z7TKJ4\030nY3\021\005%Y\027B\0017\013\005\035\021un\0347fC:DQA\034/A\002=\fa\001Z3qY>L\bcA\005qe&\021\021O\003\002\007\037B$\030n\0348\021\005I\031\030B\001;\003\005\031!U\r\0357ps\")a\017\030a\001U\006aAn\\8lkB$U\r\0357ps\")\001\020\030a\001U\006)\021m]=oG\")!\020\001D\001w\006A\021m\031;pe\032{'\017\006\002\022y\")q+\037a\001]!2\021P`A\002\003\017\001\"!C@\n\007\005\005!B\001\006eKB\024XmY1uK\022\f#!!\002\002MU\034X\rI1di>\0248+\0327fGRLwN\034\021j]N$X-\0313!_\032\004\023m\031;pe\032{'/\t\002\002\n\005\031!G\f\032\t\ri\004a\021AA\007)\025\t\022qBA\n\021\035\t\t\"a\003A\002E\t1A]3g\021!\t)\"a\003A\002\005]\021!A:\021\t\005e\021q\004\b\004\023\005m\021bAA\017\025\0051\001K]3eK\032LA!!\t\002$\t11\013\036:j]\036T1!!\b\013Q\035\tYA`A\002\003\017AaA\037\001\007\002\005%B#B\t\002,\0055\002bBA\t\003O\001\r!\005\005\t\003_\t9\0031\001\0022\005\t\001\017\005\004\0024\005\r\023q\003\b\005\003k\tyD\004\003\0028\005uRBAA\035\025\r\tYDB\001\007yI|w\016\036 \n\003-I1!!\021\013\003\035\001\030mY6bO\026LA!!\022\002H\tA\021\n^3sC\ndWMC\002\002B)As!a\n\003\007\t9\001C\004\002N\0011\t!a\024\002\037I,7o\0347wK\006\033Go\034:SK\032$2\001GA)\021\0359\0261\na\001\003/Aq!!\024\001\r\003\t)\006F\002\031\003/BaaVA*\001\004q\003bBA.\001\031\005\021QL\001\022i\026\024X.\0338bi&|gNR;ukJ,WCAA0!\025\t\t'a\032?\033\t\t\031GC\002\002f)\t!bY8oGV\024(/\0328u\023\021\tI'a\031\003\r\031+H/\036:f\021\035\ti\007\001D\001\003_\nQcZ3u\013b$XM\0358bY\006#GM]3tg\032{'\017\006\003\002r\005M\004cA\005q;!9\021QOA6\001\004i\022\001B1eIJDq!!\037\001\r\003\tY(A\thKR$UMZ1vYR\fE\r\032:fgN,\022!\b")
public interface ActorRefProvider {
  InternalActorRef rootGuardian();
  
  ActorRef rootGuardianAt(Address paramAddress);
  
  LocalActorRef guardian();
  
  LocalActorRef systemGuardian();
  
  ActorRef deadLetters();
  
  ActorPath rootPath();
  
  ActorSystem.Settings settings();
  
  void init(ActorSystemImpl paramActorSystemImpl);
  
  Deployer deployer();
  
  ActorPath tempPath();
  
  InternalActorRef tempContainer();
  
  void registerTempActor(InternalActorRef paramInternalActorRef, ActorPath paramActorPath);
  
  void unregisterTempActor(ActorPath paramActorPath);
  
  InternalActorRef actorOf(ActorSystemImpl paramActorSystemImpl, Props paramProps, InternalActorRef paramInternalActorRef, ActorPath paramActorPath, boolean paramBoolean1, Option<Deploy> paramOption, boolean paramBoolean2, boolean paramBoolean3);
  
  InternalActorRef actorFor(ActorPath paramActorPath);
  
  InternalActorRef actorFor(InternalActorRef paramInternalActorRef, String paramString);
  
  InternalActorRef actorFor(InternalActorRef paramInternalActorRef, Iterable<String> paramIterable);
  
  ActorRef resolveActorRef(String paramString);
  
  ActorRef resolveActorRef(ActorPath paramActorPath);
  
  Future<BoxedUnit> terminationFuture();
  
  Option<Address> getExternalAddressFor(Address paramAddress);
  
  Address getDefaultAddress();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorRefProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */