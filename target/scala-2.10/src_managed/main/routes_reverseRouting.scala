// @SOURCE:/Users/pparso/git/rulesdemo/conf/routes
// @HASH:3c317d5800beeed210fb93784d1ced892fcf30c7
// @DATE:Tue Jul 02 12:24:22 EDT 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._
import java.net.URLEncoder

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
package controllers {

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def clearTrades(): Call = {
   Call("DELETE", _prefix + { _defaultPrefix } + "trades")
}
                                                

// @LINE:12
def applyRules(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "rules")
}
                                                

// @LINE:8
def getTrades(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "trades")
}
                                                

// @LINE:10
def generateTrades(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "tradegenerator")
}
                                                

// @LINE:11
def getCustomers(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "customers")
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                
    
}
                          

// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          
}
                  


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
package controllers.javascript {

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def clearTrades : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.clearTrades",
   """
      function() {
      return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "trades"})
      }
   """
)
                        

// @LINE:12
def applyRules : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.applyRules",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "rules"})
      }
   """
)
                        

// @LINE:8
def getTrades : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getTrades",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "trades"})
      }
   """
)
                        

// @LINE:10
def generateTrades : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.generateTrades",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "tradegenerator"})
      }
   """
)
                        

// @LINE:11
def getCustomers : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.getCustomers",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "customers"})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        
    
}
              

// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              
}
        


// @LINE:15
// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
package controllers.ref {

// @LINE:12
// @LINE:11
// @LINE:10
// @LINE:9
// @LINE:8
// @LINE:6
class ReverseApplication {
    

// @LINE:9
def clearTrades(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.clearTrades(), HandlerDef(this, "controllers.Application", "clearTrades", Seq(), "DELETE", """""", _prefix + """trades""")
)
                      

// @LINE:12
def applyRules(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.applyRules(), HandlerDef(this, "controllers.Application", "applyRules", Seq(), "GET", """""", _prefix + """rules""")
)
                      

// @LINE:8
def getTrades(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getTrades(), HandlerDef(this, "controllers.Application", "getTrades", Seq(), "GET", """""", _prefix + """trades""")
)
                      

// @LINE:10
def generateTrades(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.generateTrades(), HandlerDef(this, "controllers.Application", "generateTrades", Seq(), "GET", """""", _prefix + """tradegenerator""")
)
                      

// @LINE:11
def getCustomers(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.getCustomers(), HandlerDef(this, "controllers.Application", "getCustomers", Seq(), "GET", """""", _prefix + """customers""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      
    
}
                          

// @LINE:15
class ReverseAssets {
    

// @LINE:15
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          
}
                  
      