package rest.routes;

import controllers.HealthProductController;
import exceptions.APIException;
import io.javalin.apibuilder.EndpointGroup;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.javalin.apibuilder.ApiBuilder.*;

public class HealthProductRoutes {
    private static EntityManagerFactory emf;
    private static final Logger logger = Logger.getLogger(HealthProductRoutes.class.getName());

    // Write above the name of the class,
    // that it is being used in.
    static {
        String logFilePath = "C:\\Users\\baban\\OneDrive\\Skrivebord\\Datamatiker\\3 Tredje Semester\\Mockup-exam-10th-april-2024\\src\\main\\java\\exceptions\\exceptioncatcherfile.txt";
        try {
            File logFile = new File(logFilePath);
            if (!logFile.exists()) {
                logFile.createNewFile();
            }
            logger.addHandler(new FileHandler(logFilePath));
        } catch (IOException e) {
            throw new RuntimeException("Error creating log file: " + logFilePath, e);
        }
    }

    public HealthProductRoutes(EntityManagerFactory emf) {
        this.emf = emf;
    }

    private static HealthProductController healthProductController = new HealthProductController(emf);

    public EndpointGroup getHealthProductRoutes() {
        return () -> path("/api/healthproducts", () -> {
            get("/", handleExceptions(ctx -> healthProductController.getAll().handle(ctx)));

            get("/{id}", handleExceptions(ctx -> healthProductController.getById().handle(ctx)));

            post("/", handleExceptions(ctx -> healthProductController.create().handle(ctx)));

            put("/{id}", handleExceptions(ctx -> healthProductController.update().handle(ctx)));

            delete("/{id}", handleExceptions(ctx -> healthProductController.delete().handle(ctx)));
        });
    }

    // Helper method to handle exceptions and log them
    private static Handler handleExceptions(Handler handler) {
        return ctx -> {
            try {
                handler.handle(ctx);
            } catch (APIException e) {
                String response = "{\"status\": \"" + e.getStatusCode() + "\", \"message\": \"" + e.getMessage() + "\", \"timestamp\": \"" + e.getTimeStamp() + "\"}";
                logException(ctx, response);
                ctx.json("{\"status\": \"" + e.getStatusCode() + "\", \"message\": \"" + e.getMessage() + "\", \"timestamp\": \"" + e.getTimeStamp() + "\"}");
            }
        };
    }

    // Helper method to log exceptions
    private static void logException(Context ctx, String response) {
        logger.log(Level.SEVERE, "Exception occurred while processing request to " + ctx.path(), response + "IP Address: " + ctx.ip());
    }
}