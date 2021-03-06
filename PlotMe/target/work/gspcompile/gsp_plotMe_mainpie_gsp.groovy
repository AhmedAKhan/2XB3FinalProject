import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_plotMe_mainpie_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/main/pie.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
createTagBody(1, {->
printHtmlPart(1)
expressionOut.print(resource(dir: 'assets', file: 'bootstrap.min.css'))
printHtmlPart(2)
expressionOut.print(resource(dir: 'assets', file: 'bootstrap-theme.min.css'))
printHtmlPart(3)
})
invokeTag('captureHead','sitemesh',18,[:],1)
printHtmlPart(4)
createTagBody(1, {->
printHtmlPart(5)
expressionOut.print(objJSON)
printHtmlPart(6)
expressionOut.print(resource(dir: 'assets', file: 'jquery.min.js'))
printHtmlPart(7)
expressionOut.print(resource(dir: 'assets', file: 'pieChart.js'))
printHtmlPart(8)
})
invokeTag('captureBody','sitemesh',62,[:],1)
printHtmlPart(9)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1428718158000L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
