package akka.dispatch;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005%daB\001\003!\003\r\ta\002\002\030\013b,7-\036;peN+'O^5dK\022+G.Z4bi\026T!a\001\003\002\021\021L7\017]1uG\"T\021!B\001\005C.\\\027m\001\001\024\007\001A\001\003\005\002\n\0355\t!B\003\002\f\031\005!A.\0318h\025\005i\021\001\0026bm\006L!a\004\006\003\r=\023'.Z2u!\t\tb#D\001\023\025\t\031B#\001\006d_:\034WO\035:f]RT!!\006\007\002\tU$\030\016\\\005\003/I\021q\"\022=fGV$xN]*feZL7-\032\005\0063\001!\tAG\001\007I%t\027\016\036\023\025\003m\001\"\001H\020\016\003uQ\021AH\001\006g\016\fG.Y\005\003Au\021A!\0268ji\")!\005\001D\001G\005AQ\r_3dkR|'/F\001\021\021\025)\003\001\"\001'\003\035)\0070Z2vi\026$\"aG\024\t\013!\"\003\031A\025\002\017\r|W.\\1oIB\021\021BK\005\003W)\021\001BU;o]\006\024G.\032\005\006[\001!\tAG\001\tg\",H\017Z8x]\")q\006\001C\001a\005Y1\017[;uI><hNT8x)\005\t\004c\001\0324S5\tA#\003\0025)\t!A*[:u\021\0251\004\001\"\0018\003)I7o\0255vi\022|wO\034\013\002qA\021A$O\005\003uu\021qAQ8pY\026\fg\016C\003=\001\021\005q'\001\007jgR+'/\\5oCR,G\rC\003?\001\021\005q(\001\tbo\006LG\017V3s[&t\027\r^5p]R\031\001\bQ#\t\013\005k\004\031\001\"\002\0031\004\"\001H\"\n\005\021k\"\001\002'p]\036DQAR\037A\002\035\013\001\002^5nKVs\027\016\036\t\003#!K!!\023\n\003\021QKW.Z+oSRDQa\023\001\005\0021\013aa];c[&$XCA'T)\tqE\fE\002\022\037FK!\001\025\n\003\r\031+H/\036:f!\t\0216\013\004\001\005\013QS%\031A+\003\003Q\013\"AV-\021\005q9\026B\001-\036\005\035qu\016\0365j]\036\004\"\001\b.\n\005mk\"aA!os\")QL\023a\001=\006A1-\0317mC\ndW\rE\002\022?FK!\001\031\n\003\021\r\013G\016\\1cY\026DQa\023\001\005\002\t,\"a\0314\025\007\021<\027\016E\002\022\037\026\004\"A\0254\005\013Q\013'\031A+\t\013!\f\007\031A\025\002\021I,hN\\1cY\026DQA[1A\002\025\f\021\001\036\005\006\027\002!\t\001\034\013\003[J\004$A\0349\021\007Eyu\016\005\002Sa\022I\021o[A\001\002\003\025\t!\026\002\003ABQ\001[6A\002%BQ\001\036\001\005\002U\f\021\"\0338w_.,\027\t\0347\026\005YTHCA<|!\r\0214\007\037\t\004#=K\bC\001*{\t\025!6O1\001V\021\025a8\0171\001~\003%\031\027\r\0347bE2,7\017M\002\003\013\001BAM@\002\004%\031\021\021\001\013\003\025\r{G\016\\3di&|g\016E\002S\003\013!1\"a\002|\003\003\005\tQ!\001\002\n\t\031q\fJ\031\022\007Y\013Y\001E\002\022?fDa\001\036\001\005\002\005=Q\003BA\t\0033!\002\"a\005\002\034\005-\022Q\006\t\005eM\n)\002\005\003\022\037\006]\001c\001*\002\032\0211A+!\004C\002UCq\001`A\007\001\004\ti\002\r\003\002 \005\r\002\003\002\032\000\003C\0012AUA\022\t1\t)#a\007\002\002\003\005)\021AA\024\005\ryFEM\t\004-\006%\002\003B\t`\003/Aa!QA\007\001\004\021\005B\002$\002\016\001\007q\tC\004\0022\001!\t!a\r\002\023%tgo\\6f\003:LX\003BA\033\003s!B!a\016\002<A\031!+!\017\005\rQ\013yC1\001V\021\035a\030q\006a\001\003{\001D!a\020\002DA!!g`A!!\r\021\0261\t\003\r\003\013\nY$!A\001\002\013\005\021q\t\002\004?\022\032\024c\001,\002JA!\021cXA\034\021\035\t\t\004\001C\001\003\033*B!a\024\002TQA\021\021KA+\003K\n9\007E\002S\003'\"a\001VA&\005\004)\006b\002?\002L\001\007\021q\013\031\005\0033\ni\006\005\0033\006m\003c\001*\002^\021a\021qLA+\003\003\005\tQ!\001\002b\t\031q\f\n\033\022\007Y\013\031\007\005\003\022?\006E\003BB!\002L\001\007!\t\003\004G\003\027\002\ra\022")
public interface ExecutorServiceDelegate extends ExecutorService {
  ExecutorService executor();
  
  void execute(Runnable paramRunnable);
  
  void shutdown();
  
  List<Runnable> shutdownNow();
  
  boolean isShutdown();
  
  boolean isTerminated();
  
  boolean awaitTermination(long paramLong, TimeUnit paramTimeUnit);
  
  <T> Future<T> submit(Callable<T> paramCallable);
  
  <T> Future<T> submit(Runnable paramRunnable, T paramT);
  
  Future<?> submit(Runnable paramRunnable);
  
  <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> paramCollection);
  
  <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> paramCollection, long paramLong, TimeUnit paramTimeUnit);
  
  <T> T invokeAny(Collection<? extends Callable<T>> paramCollection);
  
  <T> T invokeAny(Collection<? extends Callable<T>> paramCollection, long paramLong, TimeUnit paramTimeUnit);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\dispatch\ExecutorServiceDelegate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */