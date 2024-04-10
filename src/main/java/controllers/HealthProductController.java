package controllers;

import daos.HealthProductDAOMock;
import dtos.HealthProductDTO;
import exceptions.APIException;
import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;
import persistence.Populate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HealthProductController implements IHealthProductController {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String timestamp = dateFormat.format(new Date());
    private static HealthProductDAOMock healthProductDAOMock = new HealthProductDAOMock();

    private static EntityManagerFactory emf;

    public HealthProductController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Handler getAll() {
        return ctx -> {
            if (!healthProductDAOMock.getAll().isEmpty()) {
                ctx.json(healthProductDAOMock.getAll());
            } else {
                throw new APIException(404, "No data found.", "" + timestamp);
            }
        };
    }

    @Override
    public Handler getById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HealthProductDTO healthProductDTO = healthProductDAOMock.getById(id);
            if (healthProductDTO != null) {
                ctx.json(healthProductDTO);
            } else {
                throw new APIException(404, "No data found.", "" + timestamp);
            }
        };
    }

    @Override
    public Handler create() {
        return ctx -> {
            HealthProductDTO product = ctx.bodyAsClass(HealthProductDTO.class);
            if (product != null) {
                ctx.json(product);
                ctx.json(healthProductDAOMock.create(product));
            } else {
                throw new APIException(500, "No data found.", "" + timestamp);
            }
        };
    }

    @Override
    public Handler update() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HealthProductDTO product = ctx.bodyAsClass(HealthProductDTO.class);
            product.setId(id);
            HealthProductDTO updated = healthProductDAOMock.update(product);
            if (updated != null) {
                ctx.json(updated);
            } else {
                throw new APIException(404, "No data found.", "" + timestamp);
            }
        };
    }

    @Override
    public Handler delete() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HealthProductDTO deleted = healthProductDAOMock.delete(id);
            if (deleted != null) {
                ctx.json(deleted);
            } else {
                throw new APIException(404, "No data found.", "" + timestamp);
            }
        };
    }
}
