package server;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

/**
 * Created by Mykola on 14.09.2019.
 */
// This class prepares REST Assured with a default target
// This class is not used.

public class srv {
        @BeforeClass
        public static void setup() {
            String port = System.getProperty("server.port");
            if (port == null) {
                RestAssured.port = Integer.valueOf(8080);
            }
            else{
                RestAssured.port = Integer.valueOf(port);
            }

            String basePath = System.getProperty("server.base");
            if(basePath==null){
                basePath = "/basePath/";
            }
            RestAssured.basePath = basePath;

            String baseHost = System.getProperty("server.host");
            if(baseHost==null){
                baseHost = "baseHost";
            }
            RestAssured.baseURI = baseHost;
        }
    }

