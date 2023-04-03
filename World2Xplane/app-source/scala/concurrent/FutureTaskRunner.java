package scala.concurrent;

import scala.Function0;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\00153q!\001\002\021\002G\005qA\001\tGkR,(/\032+bg.\024VO\0348fe*\0211\001B\001\013G>t7-\036:sK:$(\"A\003\002\013M\034\027\r\\1\004\001M\031\001\001\003\007\021\005%QQ\"\001\003\n\005-!!AB!osJ+g\r\005\002\016\0355\t!!\003\002\020\005\tQA+Y:l%Vtg.\032:\005\013E\001!\021\001\n\003\r\031+H/\036:f+\t\031\"$\005\002\025/A\021\021\"F\005\003-\021\021qAT8uQ&tw\r\005\002\n1%\021\021\004\002\002\004\003:LH!B\016\021\005\004\031\"!\001+\t\013u\001a1\001\020\002!\031,H/\036:f\003N4UO\\2uS>tWCA\020&)\t\001s\005E\002\nC\rJ!A\t\003\003\023\031+hn\031;j_:\004\004C\001\023&\031\001!QA\n\017C\002M\021\021a\025\005\006Qq\001\r!K\001\002qB\031!\006E\022\016\003\001AQ\001\f\001\007\0025\naa];c[&$XC\001\0302)\ty#\007E\002+!A\002\"\001J\031\005\013\031Z#\031A\n\t\013MZ\003\031\001\033\002\tQ\f7o\033\t\004UU\002\024B\001\034\017\005\021!\026m]6\t\013a\002a\021A\035\002\0315\fg.Y4fI\ncwnY6\025\005ij\004CA\005<\023\taDA\001\003V]&$\b\"\002 8\001\004y\024a\0022m_\016\\WM\035\t\003\033\001K!!\021\002\003\0355\013g.Y4fI\ncwnY6fe\"\"qg\021$I!\tIA)\003\002F\t\tQA-\0329sK\016\fG/\0323\"\003\035\013q#V:fA\001\024Gn\\2lS:<\007\rI5ogR,\027\r\032\030\"\003%\013aA\r\0302a9\002\004\006\002\001D\027\"\013\023\001T\001 +N,\007\005Y#yK\016,H/[8o\007>tG/\032=uA\002Jgn\035;fC\022t\003")
public interface FutureTaskRunner extends TaskRunner {
  <S> Function0<S> futureAsFunction(Object paramObject);
  
  <S> Object submit(Object paramObject);
  
  void managedBlock(ManagedBlocker paramManagedBlocker);
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\scala\concurrent\FutureTaskRunner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */