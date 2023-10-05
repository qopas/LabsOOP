package Lab1.Utils;

import java.io.IOException;
import java.util.logging.*;

/**
 * LogManager class
 * contain configuration of Logger
 * 1 - no output of logs in console
 * 2 - file to save all logs, with append = true to not overwrite logs
 */
public class LogManager {
    public static final Logger LOGGER = Logger.getLogger("UniversityLogs");

    static {
        try {
            Logger rootLogger = Logger.getLogger("");
            Handler[] handlers = rootLogger.getHandlers();
            for (Handler handler : handlers) {
                if (handler instanceof ConsoleHandler) {
                    rootLogger.removeHandler(handler);
                }
            }
            FileHandler fileHandler = new FileHandler(Utils.PATH_logs, true);
            fileHandler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fileHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
