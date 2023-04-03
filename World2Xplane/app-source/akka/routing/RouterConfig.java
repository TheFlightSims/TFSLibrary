package akka.routing;

import akka.actor.ActorPath;
import akka.actor.ActorSystem;
import akka.actor.Props;
import scala.Option;
import scala.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(bytes = "\006\001\0254q!\001\002\021\002\007\005qA\001\007S_V$XM]\"p]\032LwM\003\002\004\t\0059!o\\;uS:<'\"A\003\002\t\005\\7.Y\002\001'\r\001\001B\004\t\003\0231i\021A\003\006\002\027\005)1oY1mC&\021QB\003\002\007\003:L(+\0324\021\005%y\021B\001\t\013\0051\031VM]5bY&T\030M\0317f\021\025\021\002\001\"\001\024\003\031!\023N\\5uIQ\tA\003\005\002\n+%\021aC\003\002\005+:LG\017C\003\031\001\031\005\021$\001\007de\026\fG/\032*pkR,'\017\006\002\033=A\0211\004H\007\002\005%\021QD\001\002\007%>,H/\032:\t\013}9\002\031\001\021\002\rML8\017^3n!\t\tC%D\001#\025\t\031C!A\003bGR|'/\003\002&E\tY\021i\031;peNK8\017^3n\021\0259\003A\"\001)\003A\021x.\036;fe\022K7\017]1uG\",'/F\001*!\tQSF\004\002\nW%\021AFC\001\007!J,G-\0324\n\0059z#AB*ue&twM\003\002-\025!)\021\007\001C\001e\0051\"o\\;uS:<Gj\\4jG\016{g\016\036:pY2,'\017\006\0024sA\031\021\002\016\034\n\005UR!AB(qi&|g\016\005\002\"o%\021\001H\t\002\006!J|\007o\035\005\006uA\002\raO\001\re>,H/\0338h\031><\027n\031\t\0037qJ!!\020\002\003\031I{W\017^5oO2{w-[2\t\013}\002A\021\001!\002'%\034X*\0318bO\026lWM\034;NKN\034\030mZ3\025\005\005#\005CA\005C\023\t\031%BA\004C_>dW-\0318\t\013\025s\004\031\001$\002\0075\034x\r\005\002\n\017&\021\001J\003\002\004\003:L\b\"\002&\001\t\003Y\025aH:u_B\024v.\036;fe^CWM\\!mYJ{W\017^3fgJ+Wn\034<fIV\t\021\tC\003N\001\021\005a*\001\007xSRDg)\0317mE\006\0347\016\006\002P!B\0211\004\001\005\006#2\003\raT\001\006_RDWM\035\005\006'\002!\t\001V\001\rm\026\024\030NZ=D_:4\027n\032\013\003)UCQA\026*A\002]\013A\001]1uQB\021\021\005W\005\0033\n\022\021\"Q2u_J\004\026\r\0365\t\rm\003a\021\001\003]\003E\031'/Z1uKJ{W\017^3s\003\016$xN\035\013\002;B\0211DX\005\003?\n\0211BU8vi\026\024\030i\031;pe\"\032\001!\0313\021\005%\021\027BA2\013\005A\031VM]5bYZ+'o]5p]VKEIH\001\002\001")
public interface RouterConfig extends Serializable {
  public static final long serialVersionUID = 1L;
  
  Router createRouter(ActorSystem paramActorSystem);
  
  String routerDispatcher();
  
  Option<Props> routingLogicController(RoutingLogic paramRoutingLogic);
  
  boolean isManagementMessage(Object paramObject);
  
  boolean stopRouterWhenAllRouteesRemoved();
  
  RouterConfig withFallback(RouterConfig paramRouterConfig);
  
  void verifyConfig(ActorPath paramActorPath);
  
  RouterActor createRouterActor();
}


/* Location:              D:\#source-code\TheFlightSims\TFSLibrary\World2Xplane\World2XPlane.jar!\akka\routing\RouterConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */