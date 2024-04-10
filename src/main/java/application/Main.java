package application;

import daos.HealthProductDAOMock;
import io.javalin.Javalin;
import rest.routes.HealthProductRoutes;

public class Main {
    public static void main(String[] args) {
        Javalin lyngbyHealthStore = Javalin.create().start(7007);
        lyngbyHealthStore.routes(HealthProductRoutes.getHealthProductRoutes());
    }
}
