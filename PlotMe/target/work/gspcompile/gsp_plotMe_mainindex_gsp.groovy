import java.lang.BootstrapMethodError
import org.codehaus.groovy.grails.plugins.metadata.GrailsPlugin
import org.codehaus.groovy.grails.web.pages.GroovyPage
import org.codehaus.groovy.grails.web.taglib.*
import org.codehaus.groovy.grails.web.taglib.exceptions.GrailsTagException
import org.springframework.web.util.*
import grails.util.GrailsUtil

class gsp_plotMe_mainindex_gsp extends GroovyPage {
public String getGroovyPageFileName() { "/WEB-INF/grails-app/views/main/index.gsp" }
public Object run() {
Writer out = getOut()
Writer expressionOut = getExpressionOut()
registerSitemeshPreprocessMode()
printHtmlPart(0)
printHtmlPart(1)
createTagBody(1, {->
printHtmlPart(2)
invokeTag('captureMeta','sitemesh',5,['gsp_sm_xmlClosingForEmptyTag':(""),'charset':("UTF-8")],-1)
printHtmlPart(2)
createTagBody(2, {->
createClosureForHtmlPart(3, 3)
invokeTag('captureTitle','sitemesh',6,[:],3)
})
invokeTag('wrapTitleTag','sitemesh',6,[:],2)
printHtmlPart(4)
expressionOut.print(resource(dir: 'assets', file: 'bootstrap.min.css'))
printHtmlPart(5)
expressionOut.print(resource(dir: 'assets', file: 'bootstrap-theme.min.css'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'assets', file: 'sidebar.css'))
printHtmlPart(6)
expressionOut.print(resource(dir: 'assets', file: 'custom.css'))
printHtmlPart(7)
})
invokeTag('captureHead','sitemesh',15,['lang':("en")],1)
printHtmlPart(8)
createTagBody(1, {->
printHtmlPart(9)
createClosureForHtmlPart(10, 2)
invokeTag('uploadForm','g',37,['action':("fileUpload"),'method':("post")],2)
printHtmlPart(11)
createClosureForHtmlPart(12, 2)
invokeTag('uploadForm','g',59,['action':("drawGraph"),'method':("post")],2)
printHtmlPart(13)
expressionOut.print(resource(dir: 'main', file: 'titanic.jsp'))
printHtmlPart(14)
expressionOut.print(resource(dir: 'assets/graph-pics', file: 'sequences_pic.png'))
printHtmlPart(15)
expressionOut.print(resource(dir: 'main', file: 'histogram.jsp'))
printHtmlPart(16)
expressionOut.print(resource(dir: 'assets/graph-pics', file: 'histogram_pic.png'))
printHtmlPart(17)
expressionOut.print(resource(dir: 'main', file: 'pie_chart.jsp'))
printHtmlPart(14)
expressionOut.print(resource(dir: 'assets/graph-pics', file: 'zoomable_pie_pic.png'))
printHtmlPart(15)
expressionOut.print(resource(dir: 'main', file: 'pie_line.jsp'))
printHtmlPart(14)
expressionOut.print(resource(dir: 'assets/graph-pics', file: 'pie_line_pic.png'))
printHtmlPart(18)
expressionOut.print(resource(dir: 'assets', file: 'jquery.min.js'))
printHtmlPart(19)
expressionOut.print(resource(dir: 'assets', file: 'bootstrap.min.js'))
printHtmlPart(20)
expressionOut.print(resource(dir: 'assets', file: 'custom.js'))
printHtmlPart(21)
})
invokeTag('captureBody','sitemesh',114,[:],1)
printHtmlPart(22)
}
public static final Map JSP_TAGS = new HashMap()
protected void init() {
	this.jspTags = JSP_TAGS
}
public static final String CONTENT_TYPE = 'text/html;charset=UTF-8'
public static final long LAST_MODIFIED = 1428542584509L
public static final String EXPRESSION_CODEC = 'html'
public static final String STATIC_CODEC = 'none'
public static final String OUT_CODEC = 'html'
public static final String TAGLIB_CODEC = 'none'
}
