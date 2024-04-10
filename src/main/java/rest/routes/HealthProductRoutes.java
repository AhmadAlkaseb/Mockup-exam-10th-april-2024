package rest.routes;

import controllers.HealthProductController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class HealthProductRoutes {
    static HealthProductController healthProductController = new HealthProductController();

    public static EndpointGroup getHealthProductRoutes() {
        return () -> path("/api/healthproducts", () -> {

            get("/", ctx -> {
                healthProductController.getAll().handle(ctx);
            });

            get("/{id}", ctx -> {
                healthProductController.getById().handle(ctx);
            });

            post("/", ctx -> {
                healthProductController.create().handle(ctx);
            });

            put("/{id}", ctx -> {
                healthProductController.update().handle(ctx);
            });

            delete("/{id}", ctx -> {
                healthProductController.delete().handle(ctx);
            });
        });
    }
}
