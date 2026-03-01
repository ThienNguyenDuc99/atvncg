package example;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class BasicSimulation extends Simulation {

    private static final Logger LOGGER = LogManager.getLogger(BasicSimulation.class);

    HttpProtocolBuilder httpProtocol = http
            .baseUrl("http://localhost:8080")
            .contentTypeHeader("application/json");

    FeederBuilder<String> userFeeder =
            csv("users_1.csv").circular();

    ScenarioBuilder scn = scenario("Full Flow SSE Test")

            .feed(userFeeder)
            // LOGIN
//            .exec(session -> {
//                System.out.println("USERNAME: " + session.get("username"));
//                System.out.println("PASSWORD: " + session.get("password"));
//                return session;
//            })
            .exec(session -> {
                String body = String.format(
                        "{ \"username\": \"%s\", \"password\": \"%s\" }",
                        session.getString("username"),
                        session.getString("password")
                );

                System.out.println("Rendered body = " + body);
                return session;
            })
            .exec(
                    http("Login")
                            .post("/auth/login")
                            .header("Content-Type", "application/json")
                            .body(StringBody(session ->
                                    "{ \"username\": \"" + session.getString("username") +
                                            "\", \"password\": \"" + session.getString("password") + "\" }"
                            ))
                            .asJson()
                            .check(status().is(200))
                            .check(bodyString().saveAs("loginResponse"))
                            .check(jsonPath("$.token").saveAs("token"))
            )
            .exec(session -> {
                System.out.println("Login response: " + session.getString("loginResponse"));
                System.out.println("Token: " + session.getString("token"));
                return session;
            })

            // getAllEvents
            .exec(
                    http("Get Events")
                            .get("/business/getAllEvents")
                            .header("Authorization", session -> "Bearer " + session.getString("token"))
                            .check(status().is(200))
                            .check(bodyString().saveAs("getAllEvents"))
            ).exec(session -> {
                System.out.println("getAllEvents response: " + session.getString("getAllEvents"));
                System.out.println("Token: " + session.getString("token"));
                return session;
            })

            // getZones false
            .exec(
                    http("Get Zones False")
                            .get("/business/getZonesByEvent/1/false")
                            .header("Authorization", session -> "Bearer " + session.getString("token"))
                            .check(jsonPath("$.traceId").saveAs("traceId"))
                            .check(jsonPath("$.userId").saveAs("realUserId"))
                            .check(status().is(200))
                            .check(bodyString().saveAs("getZonesFalse"))
            ).exec(session -> {
                System.out.println("getZonesFalse response: " + session.getString("getZonesFalse"));
                return session;
            })

            .exec(
                    sse("SSE Connect")
                            .get(session ->
                                    "http://localhost:8081/business/sse/subscribe" +
                                            "?token=" + session.getString("token") +
                                            "&traceId=" + session.getString("traceId") +
                                            "&userId=" + session.getString("realUserId")
                            )
                            .await(30)
                            .on(
                                    sse.checkMessage("check1")
                                            .check(substring("data"))
                            )
            )
            .exec(
                    sse("Close SSE").close()
            )

            // getZones true
            .exec(
                    http("Get Zones True")
                            .get("/business/getZonesByEvent/1/true")
                            .header("Authorization", session -> "Bearer " + session.getString("token"))
                            .check(status().is(200))
                            .check(bodyString().saveAs("getZonesTrue"))
            ).exec(session -> {
                System.out.println("getZonesTrue response: " + session.getString("getZonesTrue"));
                return session;
            });

    {
        setUp(
                scn.injectOpen(
                        rampUsers(1).during(30)   // test nhỏ trước
                )
        ).protocols(httpProtocol);
    }
}

//                            .get(session ->
//                            "http://localhost:8081/business/sse/subscribe" +
//                            "?token=" + session.getString("token") +
//                            "&traceId=" + session.getString("traceId") +
//                            "&userId=" + session.getString("realUserId"))