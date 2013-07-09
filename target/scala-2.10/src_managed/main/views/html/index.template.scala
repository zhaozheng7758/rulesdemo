
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template0[play.api.templates.Html] {

    /**/
    def apply():play.api.templates.Html = {
        _display_ {

Seq[Any](format.raw/*1.1*/("""<!DOCTYPE html>
<html ng-app="RuleApp">
    <head>
        <title></title>
        <link rel="stylesheet" media="screen" href=""""),_display_(Seq[Any](/*5.54*/routes/*5.60*/.Assets.at("bootstrap/css/bootstrap.min.css"))),format.raw/*5.105*/("""" />
        <link rel="stylesheet" type="text/css" href=""""),_display_(Seq[Any](/*6.55*/routes/*6.61*/.Assets.at("stylesheets/ng-grid.min.css"))),format.raw/*6.102*/("""" />
        <link rel="stylesheet" type="text/css" href=""""),_display_(Seq[Any](/*7.55*/routes/*7.61*/.Assets.at("stylesheets/main.css"))),format.raw/*7.95*/("""" />
        <link rel="shortcut icon" type="image/png" href=""""),_display_(Seq[Any](/*8.59*/routes/*8.65*/.Assets.at("images/favicon.png"))),format.raw/*8.97*/("""" />        
    </head>
    <body ng-controller="TradeCtrl">
        <div class="ngGrid" style="pagebody" >
        <h3>Rules Engine Demo</h3>
        <hr class="titleHR"></hr>
          <div class="bs-docs-example">
            <ul class="nav nav-pills navbar">
              <li class="dropdown">
                <a class="dropdown-toggle" id="drop4" role="button" data-toggle="dropdown" href="#">Data <b class="caret"></b></a>
                <ul id="menu1" class="dropdown-menu" role="menu" aria-labelledby="drop4">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#" ng-click="generateTrades()">Generate Trades...</a></li>
                  <li role="presentation" class="divider"></li>
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#" ng-click="clearTrades()">Clear Trade Data</a></li>
                </ul>
              </li>
              <li class="dropdown">
                <a class="dropdown-toggle" id="drop5" role="button" data-toggle="dropdown" href="#">Rules<b class="caret"></b></a>
                <ul id="menu2" class="dropdown-menu" role="menu" aria-labelledby="drop5">
                  <li role="presentation"><a role="menuitem" tabindex="-1" href="#" ng-click="fireRules()">Execute Eligibility Rules</a></li>
                </ul>
              </li>
            </ul> <!-- /tabs -->
            <hr class="navbar"></hr>
            <h4>Trades</h4>
          </div>
          <div ng-grid="tradetable" class="well" ></div>
            <h5>&nbsp;&nbsp;Rule Results</h5>
            <div class"tradeDetail">&nbsp;&nbsp"""),format.raw/*36.48*/("""{"""),format.raw/*36.49*/("""{"""),format.raw/*36.50*/("""tradeSelection[0].params"""),format.raw/*36.74*/("""}"""),format.raw/*36.75*/("""}"""),format.raw/*36.76*/("""</div>
          </div>
        <script src=""""),_display_(Seq[Any](/*38.23*/routes/*38.29*/.Assets.at("javascripts/jquery-1.9.0.min.js"))),format.raw/*38.74*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*39.23*/routes/*39.29*/.Assets.at("javascripts/angular-1.1.5/angular.min.js"))),format.raw/*39.83*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*40.23*/routes/*40.29*/.Assets.at("javascripts/angular-1.1.5/angular-resource.min.js"))),format.raw/*40.92*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*41.23*/routes/*41.29*/.Assets.at("javascripts/ng-grid-2.0.6.min.js"))),format.raw/*41.75*/("""" type="text/javascript"></script>        
        <script src=""""),_display_(Seq[Any](/*42.23*/routes/*42.29*/.Assets.at("javascripts/ng-grid-flexible-height.js"))),format.raw/*42.81*/("""" type="text/javascript"></script>        
        <script src=""""),_display_(Seq[Any](/*43.23*/routes/*43.29*/.Assets.at("bootstrap/js/bootstrap.min.js"))),format.raw/*43.72*/("""" type="text/javascript"></script>
        <script src=""""),_display_(Seq[Any](/*44.23*/routes/*44.29*/.Assets.at("javascripts/main.js"))),format.raw/*44.62*/("""" type="text/javascript"></script>
    </body>
</html>
"""))}
    }
    
    def render(): play.api.templates.Html = apply()
    
    def f:(() => play.api.templates.Html) = () => apply()
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Jul 02 14:35:17 EDT 2013
                    SOURCE: /Users/pparso/git/rulesdemo/app/views/index.scala.html
                    HASH: 5ae89171307163702268d7657dbee0c91ad5f8ae
                    MATRIX: 787->0|950->128|964->134|1031->179|1125->238|1139->244|1202->285|1296->344|1310->350|1365->384|1463->447|1477->453|1530->485|3166->2093|3195->2094|3224->2095|3276->2119|3305->2120|3334->2121|3416->2167|3431->2173|3498->2218|3591->2275|3606->2281|3682->2335|3775->2392|3790->2398|3875->2461|3968->2518|3983->2524|4051->2570|4152->2635|4167->2641|4241->2693|4342->2758|4357->2764|4422->2807|4515->2864|4530->2870|4585->2903
                    LINES: 29->1|33->5|33->5|33->5|34->6|34->6|34->6|35->7|35->7|35->7|36->8|36->8|36->8|64->36|64->36|64->36|64->36|64->36|64->36|66->38|66->38|66->38|67->39|67->39|67->39|68->40|68->40|68->40|69->41|69->41|69->41|70->42|70->42|70->42|71->43|71->43|71->43|72->44|72->44|72->44
                    -- GENERATED --
                */
            