package application;

import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;
import persistence.config.HibernateConfig;
import rest.routes.HealthProductRoutes;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig(false);
        //Populate.populateDatabase(emf);
        HealthProductRoutes routes = new HealthProductRoutes(emf);
        Javalin lyngbyHealthStore = Javalin.create().start(7007);
        lyngbyHealthStore.routes(routes.getHealthProductRoutes());
    }
}
