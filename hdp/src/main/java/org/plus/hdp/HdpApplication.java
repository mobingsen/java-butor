package org.plus.hdp;

import org.plus.hdp.config.AppRun;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author mbs
 */
@SpringBootApplication
public class HdpApplication {

    public static void main(String[] args) {
        AppRun.run(HdpApplication.class, args);
    }

}
