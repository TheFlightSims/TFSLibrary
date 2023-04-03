package scala.collection.convert;

import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import scala.collection.Iterable;
import scala.collection.Iterator;
import scala.collection.concurrent.Map;
import scala.collection.mutable.Buffer;
import scala.collection.mutable.ConcurrentMap;
import scala.collection.mutable.Map;
import scala.collection.mutable.Set;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\005-faB\001\003!\003\r\t!\003\002\f/J\f\007/Q:TG\006d\027M\003\002\004\t\00591m\0348wKJ$(BA\003\007\003)\031w\016\0347fGRLwN\034\006\002\017\005)1oY1mC\016\0011c\001\001\013\035A\0211\002D\007\002\r%\021QB\002\002\007\003:L(+\0324\021\005=\001R\"\001\002\n\005E\021!A\006'poB\023\030n\034:jif<&/\0319BgN\033\027\r\\1\t\013M\001A\021\001\013\002\r\021Jg.\033;%)\005)\002CA\006\027\023\t9bA\001\003V]&$\b\"B\r\001\t\007Q\022aD1t'\016\fG.Y%uKJ\fGo\034:\026\005m\021CC\001\017,!\rib\004I\007\002\t%\021q\004\002\002\t\023R,'/\031;peB\021\021E\t\007\001\t\025\031\003D1\001%\005\005\t\025CA\023)!\tYa%\003\002(\r\t9aj\034;iS:<\007CA\006*\023\tQcAA\002B]fDQ\001\f\rA\0025\n!!\033;\021\0079\032\004%D\0010\025\t\001\024'\001\003vi&d'\"\001\032\002\t)\fg/Y\005\003?=BQ!\016\001\005\004Y\n!$\0328v[\026\024\030\r^5p]\006\0338kY1mC&#XM]1u_J,\"a\016\036\025\005aZ\004cA\017\037sA\021\021E\017\003\006GQ\022\r\001\n\005\006yQ\002\r!P\001\002SB\031aFP\035\n\005}z#aC#ok6,'/\031;j_:DQ!\021\001\005\004\t\013q#\033;fe\006\024G.Z!t'\016\fG.Y%uKJ\f'\r\\3\026\005\rCEC\001#J!\riRiR\005\003\r\022\021\001\"\023;fe\006\024G.\032\t\003C!#Qa\t!C\002\021BQ\001\020!A\002)\0032a\023(H\033\005a%BA'2\003\021a\027M\\4\n\005\031c\005\"\002)\001\t\007\t\026!G2pY2,7\r^5p]\006\0338kY1mC&#XM]1cY\026,\"AU+\025\005M3\006cA\017F)B\021\021%\026\003\006G=\023\r\001\n\005\006y=\003\ra\026\t\004]a#\026BA-0\005)\031u\016\0347fGRLwN\034\005\0067\002!\031\001X\001\016CN\0346-\0317b\005V4g-\032:\026\005u+GC\0010g!\ry&\rZ\007\002A*\021\021\rB\001\b[V$\030M\0317f\023\t\031\007M\001\004Ck\0324WM\035\t\003C\025$Qa\t.C\002\021BQa\032.A\002!\f\021\001\034\t\004]%$\027B\00160\005\021a\025n\035;\t\0131\004A1A7\002\025\005\0348kY1mCN+G/\006\002ogR\021q\016\036\t\004?B\024\030BA9a\005\r\031V\r\036\t\003CM$QaI6C\002\021BQ!^6A\002Y\f\021a\035\t\004]]\024\030BA90\021\025I\b\001b\001{\0035i\027\r]!t'\016\fG.Y'baV)10!\001\002\006Q\031A0!\003\021\013}kx0a\001\n\005y\004'aA'baB\031\021%!\001\005\013\rB(\031\001\023\021\007\005\n)\001\002\004\002\ba\024\r\001\n\002\002\005\"9\0211\002=A\002\0055\021!A7\021\r9\nya`A\002\023\tqx\006C\004\002\024\001!\t!!\006\002)\005\0348kY1mC\016{gnY;se\026tG/T1q+\031\t9\"!\t\002&Q!\021\021DA\024!\035y\0261DA\020\003GI1!!\ba\0055\031uN\\2veJ,g\016^'baB\031\021%!\t\005\r\r\n\tB1\001%!\r\t\023Q\005\003\b\003\017\t\tB1\001%\021!\tY!!\005A\002\005%\002\003CA\026\003c\ty\"a\t\016\005\0055\"bAA\030_\005Q1m\0348dkJ\024XM\034;\n\t\005u\021Q\006\025\t\003#\t)$a\017\002@A\0311\"a\016\n\007\005ebA\001\006eKB\024XmY1uK\022\f#!!\020\0027V\033X\r\t1nCB\f5oU2bY\006\034uN\\2veJ,g\016^'ba\002\004\023N\\:uK\006$G\006I1oI\002*8/\032\021aG>t7-\036:sK:$h&T1qA\002Jgn\035;fC\022\004sN\032\021a\007>t7-\036:sK:$X*\0319a]\005\022\021\021I\001\007e9\n\004G\f\031\t\017\005\025\003\001b\001\002H\0059R.\0319BgN\033\027\r\\1D_:\034WO\035:f]Rl\025\r]\013\007\003\023\n)&!\027\025\t\005-\0231\f\t\t\003\033\n\t&a\025\002X5\021\021q\n\006\004\003_!\021b\001@\002PA\031\021%!\026\005\r\r\n\031E1\001%!\r\t\023\021\f\003\b\003\017\t\031E1\001%\021!\tY!a\021A\002\005u\003\003CA\026\003c\t\031&a\026\t\017\005\005\004\001b\001\002d\005!B-[2uS>t\027M]=BgN\033\027\r\\1NCB,b!!\032\002l\005=D\003BA4\003c\002baX?\002j\0055\004cA\021\002l\02111%a\030C\002\021\0022!IA8\t\035\t9!a\030C\002\021B\001\"a\035\002`\001\007\021QO\001\002aB9a&a\036\002j\0055\024bAA=_\tQA)[2uS>t\027M]=\t\017\005u\004\001b\001\002\000\005!\002O]8qKJ$\030.Z:BgN\033\027\r\\1NCB$B!!!\002\022B1q,`AB\003\007\003B!!\"\002\f:\0311\"a\"\n\007\005%e!\001\004Qe\026$WMZ\005\005\003\033\013yI\001\004TiJLgn\032\006\004\003\0233\001\002CA:\003w\002\r!a%\021\0079\n)*C\002\002\030>\022!\002\025:pa\026\024H/[3t\017\035\tYJ\001E\001\003;\0131b\026:ba\006\0338kY1mCB\031q\"a(\007\r\005\021\001\022AAQ'\025\tyJCAR!\ty\001\001\003\005\002(\006}E\021AAU\003\031a\024N\\5u}Q\021\021Q\024")
public interface WrapAsScala extends LowPriorityWrapAsScala {
  <A> Iterator<A> asScalaIterator(Iterator<A> paramIterator);
  
  <A> Iterator<A> enumerationAsScalaIterator(Enumeration<A> paramEnumeration);
  
  <A> Iterable<A> iterableAsScalaIterable(Iterable<A> paramIterable);
  
  <A> Iterable<A> collectionAsScalaIterable(Collection<A> paramCollection);
  
  <A> Buffer<A> asScalaBuffer(List<A> paramList);
  
  <A> Set<A> asScalaSet(Set<A> paramSet);
  
  <A, B> Map<A, B> mapAsScalaMap(Map<A, B> paramMap);
  
  <A, B> ConcurrentMap<A, B> asScalaConcurrentMap(ConcurrentMap<A, B> paramConcurrentMap);
  
  <A, B> Map<A, B> mapAsScalaConcurrentMap(ConcurrentMap<A, B> paramConcurrentMap);
  
  <A, B> Map<A, B> dictionaryAsScalaMap(Dictionary<A, B> paramDictionary);
  
  Map<String, String> propertiesAsScalaMap(Properties paramProperties);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\collection\convert\WrapAsScala.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */