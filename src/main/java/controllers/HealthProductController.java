package controllers;

import daos.HealthProductDAOMock;
import dtos.HealthProductDTO;
import io.javalin.http.Handler;

public class HealthProductController implements IHealthProductController {
    @Override
    public Handler getAll() {
        return ctx -> {
            ctx.json(HealthProductDAOMock.getAll());
        };
    }

    @Override
    public Handler getById() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HealthProductDTO healthProductDTO = HealthProductDAOMock.getById(id);
            ctx.json(healthProductDTO);
        };
    }

    @Override
    public Handler create() {
        return ctx -> {
            HealthProductDTO product = ctx.bodyAsClass(HealthProductDTO.class);
            HealthProductDAOMock.create(product);
            ctx.json(product);
        };
    }

    @Override
    public Handler update() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            HealthProductDTO product = ctx.bodyAsClass(HealthProductDTO.class);
            product.setId(id);
            ctx.json(HealthProductDAOMock.update(product));
        };
    }

    @Override
    public Handler delete() {
        return ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            ctx.json(HealthProductDAOMock.delete(id));
        };
    }
}
