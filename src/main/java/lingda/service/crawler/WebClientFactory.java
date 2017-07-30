package lingda.service.crawler;

import com.gargoylesoftware.htmlunit.ScriptException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class WebClientFactory {
    private static final Logger logger = LoggerFactory.getLogger(WebClientFactory.class);

    public static WebClient get() {
        WebClient webClient = new WebClient();
//        TODO this loglevel does not seem to work
        System.getProperties().put("org.apache.commons.logging.simplelog.defaultlog", "error");
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.getOptions().setJavaScriptEnabled(true);
        webClient.getOptions().setTimeout(20000);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setRedirectEnabled(true);
        webClient.setJavaScriptErrorListener(new JavaScriptErrorListener() {
            @Override
            public void scriptException(HtmlPage page, ScriptException scriptException) {
                logger.error(scriptException.getMessage());
            }

            @Override
            public void timeoutError(HtmlPage page, long allowedTime, long executionTime) {
                logger.error("JS timeout error");
            }

            @Override
            public void malformedScriptURL(HtmlPage page, String url, MalformedURLException malformedURLException) {
                logger.error(malformedURLException.getMessage());
            }

            @Override
            public void loadScriptError(HtmlPage page, URL scriptUrl, Exception exception) {
                logger.error(exception.getMessage());
            }
        });
        return webClient;
    }
}
