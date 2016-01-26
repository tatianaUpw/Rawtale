package tests.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomLogger {
    private Logger log;

    public CustomLogger()
    {
        this.log = LogManager.getLogger();
    }

    public void error(String message)
    {
        this.log.error(message);
        TestResult.ErrCount += 1;
        System.err.println(message);
    }

    public void info(String message)
    {
        this.log.info(message);
        System.out.println(message);
    }
}
