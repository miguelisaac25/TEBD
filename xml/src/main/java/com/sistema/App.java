package com.sistema;

import io.javalin.Javalin;
import io.javalin.rendering.JavalinRenderer;
import io.javalin.rendering.template.JavalinFreemarker;

public class App {

  public static void main(String[] args) {

    try {
      JavalinRenderer.register(new JavalinFreemarker(), ".ftl");
    } catch (Exception e) {
      System.err.println("ERRO: Falha ao registrar o Freemarker. Verifique o pom.xml e a sincronização.");
      e.printStackTrace();
      return;
    }

    Javalin app = Javalin.create().start(7000);

    app.get("/", ctx -> {
      ctx.render("index.ftl");
    });

    app.post("/exportar", new ExportaHandler());
    app.post("/importar", new ImportaHandler());

    System.out.println("Servidor Javalin iniciado na porta http://localhost:7000");
  }
}