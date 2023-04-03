package akka.actor;

import scala.collection.Iterable;
import scala.concurrent.ExecutionContextExecutor;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005}aaB\001\003!\003\r\ta\002\002\020\003\016$xN\035*fM\032\0137\r^8ss*\0211\001B\001\006C\016$xN\035\006\002\013\005!\021m[6b\007\001\031\"\001\001\005\021\005%aQ\"\001\006\013\003-\tQa]2bY\006L!!\004\006\003\r\005s\027PU3g\021\025y\001\001\"\001\021\003\031!\023N\\5uIQ\t\021\003\005\002\n%%\0211C\003\002\005+:LG\017C\003\026\001\031Ea#\001\006tsN$X-\\%na2,\022a\006\t\0031ei\021AA\005\0035\t\021q\"Q2u_J\034\026p\035;f[&k\007\017\034\005\0069\0011\t\"H\001\taJ|g/\0333feV\ta\004\005\002\031?%\021\001E\001\002\021\003\016$xN\035*fMB\023xN^5eKJDQA\t\001\007\004\r\n!\002Z5ta\006$8\r[3s+\005!\003CA\023)\033\0051#BA\024\013\003)\031wN\\2veJ,g\016^\005\003S\031\022\001$\022=fGV$\030n\0348D_:$X\r\037;Fq\026\034W\017^8s\021\025Y\003A\"\005-\003!9W/\031:eS\006tW#A\027\021\005aq\023BA\030\003\005AIe\016^3s]\006d\027i\031;peJ+g\rC\0032\001\031EA&\001\006m_>\\W\017\035*p_RDQa\r\001\007\002Q\nq!Y2u_J|e\r\006\0026qA\021\001DN\005\003o\t\021\001\"Q2u_J\024VM\032\005\006sI\002\rAO\001\006aJ|\007o\035\t\0031mJ!\001\020\002\003\013A\023x\016]:\t\013M\002a\021\001 \025\007Uz\004\tC\003:{\001\007!\bC\003B{\001\007!)\001\003oC6,\007CA\"G\035\tIA)\003\002F\025\0051\001K]3eK\032L!a\022%\003\rM#(/\0338h\025\t)%\002C\003K\001\021\0051*\001\005bGR|'OR8s)\t)D\nC\003N\023\002\007a*\001\003qCRD\007C\001\rP\023\t\001&AA\005BGR|'\017U1uQ\"\"\021JU+X!\tI1+\003\002U\025\tQA-\0329sK\016\fG/\0323\"\003Y\013a%^:fA\005\034Go\034:TK2,7\r^5p]\002Jgn\035;fC\022\004sN\032\021bGR|'OR8sC\005A\026a\001\032/e!)!\n\001C\0015R\021Qg\027\005\006\033f\003\rA\021\025\0053J+v\013C\003K\001\021\005a\f\006\0026?\")Q*\030a\001AB\031\021-\033\"\017\005\t<gBA2g\033\005!'BA3\007\003\031a$o\\8u}%\t1\"\003\002i\025\0059\001/Y2lC\036,\027B\0016l\005!IE/\032:bE2,'B\0015\013Q\021i&+V,\t\013)\003A\021\0018\025\005Uz\007\"B'n\001\004\001\bcA9w\0056\t!O\003\002ti\006!A.\0318h\025\005)\030\001\0026bm\006L!A\033:)\t5\024Vk\026\005\006s\002!\tA_\001\017C\016$xN]*fY\026\034G/[8o)\tYh\020\005\002\031y&\021QP\001\002\017\003\016$xN]*fY\026\034G/[8o\021\025i\005\0201\001C\021\031I\b\001\"\001\002\002Q\03110a\001\t\0135{\b\031\001(\t\017\005\035\001A\"\001\002\n\005!1\017^8q)\r\t\0221\002\005\007\007\005\025\001\031A\033)\013\001\ty!a\007\021\t\005E\021qC\007\003\003'Q1!!\006\013\003)\tgN\\8uCRLwN\\\005\005\0033\t\031B\001\tj[Bd\027nY5u\035>$hi\\;oI\006\022\021QD\001\0020%l\007\017\\5dSR\004\023i\031;peJ+gMR1di>\024\030\020\t:fcVL'/\0323;A%4\007e\\;ug&$W\rI8gA\005t\007%Q2u_J\004\023p\\;!]\026,G\rI1oA%l\007\017\\5dSR\004\023i\031;peNK8\017^3nY\001Jgn]5eK\002zg\rI1oA\005\034Go\034:!i\"L7\017I:i_VdG\r\t2fAQDW\rI5na2L7-\033;!\003\016$xN]\"p]R,\007\020\036")
public interface ActorRefFactory {
  ActorSystemImpl systemImpl();
  
  ActorRefProvider provider();
  
  ExecutionContextExecutor dispatcher();
  
  InternalActorRef guardian();
  
  InternalActorRef lookupRoot();
  
  ActorRef actorOf(Props paramProps);
  
  ActorRef actorOf(Props paramProps, String paramString);
  
  ActorRef actorFor(ActorPath paramActorPath);
  
  ActorRef actorFor(String paramString);
  
  ActorRef actorFor(Iterable<String> paramIterable);
  
  ActorRef actorFor(Iterable<String> paramIterable);
  
  ActorSelection actorSelection(String paramString);
  
  ActorSelection actorSelection(ActorPath paramActorPath);
  
  void stop(ActorRef paramActorRef);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\actor\ActorRefFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */