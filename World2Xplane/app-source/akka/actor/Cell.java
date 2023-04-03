package akka.actor;

import akka.actor.dungeon.ChildrenContainer;
import akka.dispatch.Envelope;
import akka.dispatch.sysmsg.SystemMessage;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005ub\001C\001\003!\003\r\t\001\002\004\003\t\r+G\016\034\006\003\007\021\tQ!Y2u_JT\021!B\001\005C.\\\027m\005\002\001\017A\021\001bC\007\002\023)\t!\"A\003tG\006d\027-\003\002\r\023\t1\021I\\=SK\032DQA\004\001\005\002A\ta\001J5oSR$3\001\001\013\002#A\021\001BE\005\003'%\021A!\0268ji\")Q\003\001D\001-\005!1/\0327g+\0059\002C\001\r\032\033\005\021\021B\001\016\003\005!\t5\r^8s%\0264\007\"\002\017\001\r\003i\022AB:zgR,W.F\001\037!\tAr$\003\002!\005\tY\021i\031;peNK8\017^3n\021\025\021\003A\"\001$\003)\031\030p\035;f[&k\007\017\\\013\002IA\021\001$J\005\003M\t\021q\"Q2u_J\034\026p\035;f[&k\007\017\034\005\006Q\0011\t!K\001\006gR\f'\017\036\013\002U5\t\001\001C\003-\001\031\005\001#A\004tkN\004XM\0343\t\0139\002a\021A\030\002\rI,7/^7f)\t\t\002\007C\0032[\001\007!'A\bdCV\034X\r\032\"z\r\006LG.\036:f!\t\0314H\004\0025s9\021Q\007O\007\002m)\021qgD\001\007yI|w\016\036 \n\003)I!AO\005\002\017A\f7m[1hK&\021A(\020\002\n)\"\024xn^1cY\026T!AO\005\t\013}\002a\021\001!\002\017I,7\017^1siR\021\021#\021\005\006\005z\002\rAM\001\006G\006,8/\032\005\006\t\0021\t\001E\001\005gR|\007\017C\003G\001\031\005q)\001\007jgR+'/\\5oCR,G-F\001I!\tA\021*\003\002K\023\t9!i\\8mK\006t\007\"\002'\001\r\003i\025A\0029be\026tG/F\001O!\tAr*\003\002Q\005\t\001\022J\034;fe:\fG.Q2u_J\024VM\032\005\006%\0021\taU\001\rG\"LG\016\032:f]J+gm]\013\002)B\021Q\013W\007\002-*\021qKA\001\bIVtw-Z8o\023\tIfKA\tDQ&dGM]3o\007>tG/Y5oKJDQa\027\001\007\002q\013abZ3u\007\"LG\016\032\"z\035\006lW\r\006\002^GB\031\001B\0301\n\005}K!AB(qi&|g\016\005\002\031C&\021!M\001\002\013\007\"LG\016Z*uCR\034\b\"\0023[\001\004)\027\001\0028b[\026\004\"AZ5\017\005!9\027B\0015\n\003\031\001&/\0323fM&\021!n\033\002\007'R\024\030N\\4\013\005!L\001\"B7\001\r\003q\027AD4fiNKgn\0327f\007\"LG\016\032\013\003\035>DQ\001\0327A\002\025DQ!\035\001\007\002I\f1b]3oI6+7o]1hKR\021\021c\035\005\006iB\004\r!^\001\004[N<\007C\001<z\033\0059(B\001=\005\003!!\027n\0359bi\016D\027B\001>x\005!)eN^3m_B,\007\"B9\001\t\013aH\003B\t~\003\013AQA`>A\002}\fq!\\3tg\006<W\rE\002\t\003\003I1!a\001\n\005\r\te.\037\005\007\003\017Y\b\031A\f\002\rM,g\016Z3s\021\035\tY\001\001D\001\003\033\t\021c]3oINK8\017^3n\033\026\0348/Y4f)\r\t\022q\002\005\bi\006%\001\031AA\t!\021\t\031\"!\007\016\005\005U!bAA\fo\00611/_:ng\036LA!a\007\002\026\ti1+_:uK6lUm]:bO\026Da!a\b\001\r\0039\025aB5t\031>\034\027\r\034\005\007\003G\001a\021A$\002\027!\f7/T3tg\006<Wm\035\005\b\003O\001a\021AA\025\003AqW/\0342fe>3W*Z:tC\036,7/\006\002\002,A\031\001\"!\f\n\007\005=\022BA\002J]RDq!a\r\001\r\003\t)$A\003qe>\0048/\006\002\0028A\031\001$!\017\n\007\005m\"AA\003Qe>\0048\017")
public interface Cell {
  ActorRef self();
  
  ActorSystem system();
  
  ActorSystemImpl systemImpl();
  
  Cell start();
  
  void suspend();
  
  void resume(Throwable paramThrowable);
  
  void restart(Throwable paramThrowable);
  
  void stop();
  
  boolean isTerminated();
  
  InternalActorRef parent();
  
  ChildrenContainer childrenRefs();
  
  Option<ChildStats> getChildByName(String paramString);
  
  InternalActorRef getSingleChild(String paramString);
  
  void sendMessage(Envelope paramEnvelope);
  
  void sendMessage(Object paramObject, ActorRef paramActorRef);
  
  void sendSystemMessage(SystemMessage paramSystemMessage);
  
  boolean isLocal();
  
  boolean hasMessages();
  
  int numberOfMessages();
  
  Props props();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\Cell.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */