// @SOURCE:/Users/pparso/git/rulesdemo/conf/routes
// @HASH:3c317d5800beeed210fb93784d1ced892fcf30c7
// @DATE:Tue Jul 02 12:24:22 EDT 2013


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix  
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" } 


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:8
private[this] lazy val controllers_Application_getTrades1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("trades"))))
        

// @LINE:9
private[this] lazy val controllers_Application_clearTrades2 = Route("DELETE", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("trades"))))
        

// @LINE:10
private[this] lazy val controllers_Application_generateTrades3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("tradegenerator"))))
        

// @LINE:11
private[this] lazy val controllers_Application_getCustomers4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("customers"))))
        

// @LINE:12
private[this] lazy val controllers_Application_applyRules5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("rules"))))
        

// @LINE:15
private[this] lazy val controllers_Assets_at6 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """trades""","""controllers.Application.getTrades()"""),("""DELETE""", prefix + (if(prefix.endsWith("/")) "" else "/") + """trades""","""controllers.Application.clearTrades()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """tradegenerator""","""controllers.Application.generateTrades()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """customers""","""controllers.Application.getCustomers()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """rules""","""controllers.Application.applyRules()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
       
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:8
case controllers_Application_getTrades1(params) => {
   call { 
        invokeHandler(controllers.Application.getTrades(), HandlerDef(this, "controllers.Application", "getTrades", Nil,"GET", """""", Routes.prefix + """trades"""))
   }
}
        

// @LINE:9
case controllers_Application_clearTrades2(params) => {
   call { 
        invokeHandler(controllers.Application.clearTrades(), HandlerDef(this, "controllers.Application", "clearTrades", Nil,"DELETE", """""", Routes.prefix + """trades"""))
   }
}
        

// @LINE:10
case controllers_Application_generateTrades3(params) => {
   call { 
        invokeHandler(controllers.Application.generateTrades(), HandlerDef(this, "controllers.Application", "generateTrades", Nil,"GET", """""", Routes.prefix + """tradegenerator"""))
   }
}
        

// @LINE:11
case controllers_Application_getCustomers4(params) => {
   call { 
        invokeHandler(controllers.Application.getCustomers(), HandlerDef(this, "controllers.Application", "getCustomers", Nil,"GET", """""", Routes.prefix + """customers"""))
   }
}
        

// @LINE:12
case controllers_Application_applyRules5(params) => {
   call { 
        invokeHandler(controllers.Application.applyRules(), HandlerDef(this, "controllers.Application", "applyRules", Nil,"GET", """""", Routes.prefix + """rules"""))
   }
}
        

// @LINE:15
case controllers_Assets_at6(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        
}
    
}
        