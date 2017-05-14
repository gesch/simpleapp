package de.solutionsdirekt;

import io.prometheus.client.Counter;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@Path("/hello")
public class Endpoint {

    Log log = LogFactory.getLog(Endpoint.class);

    private final Service service;
    static final Summary requestLatency = Summary.build()
            .name("sd_requests_latency")
            .help("Request latency.").register();
    static final Counter requests = Counter.build()
            .name("sd_requests_total")
            .help("Request counter total.").register();
    static String hostname;

    static {
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public Endpoint(Service service) {
        this.service = service;
    }

    @GET
    public String message() {
        requests.inc();
        Summary.Timer requestTimer = requestLatency.startTimer();
        HelloWorld hello = new HelloWorld("Hello... ... ");
        String message = hello.getMessage() + this.service.message() + " ("+ hostname+")";
        double time =requestTimer.observeDuration();
        log.info("endpoint accessed...(Host: "+hostname+"): "+time);
        return message;


    }

}