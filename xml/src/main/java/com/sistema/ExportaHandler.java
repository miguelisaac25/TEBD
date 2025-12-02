package com.sistema;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.Collections;
import org.jetbrains.annotations.NotNull;

public class ExportaHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {

        int id = (int) (System.currentTimeMillis() % 10000);
        String titulo = ctx.formParam("titulo");
        String descricao = ctx.formParam("descricao");
        String ingredientes = ctx.formParam("ingredientes");
        String modoPreparo = ctx.formParam("modoPreparo");
        String emojiFoto = ctx.formParam("emojiFoto");
        String imageUrl = ctx.formParam("imageUrl");

        Receita novaReceita = new Receita(
                id, titulo, descricao, ingredientes, modoPreparo, emojiFoto, imageUrl);

        ListaReceitas lista = new ListaReceitas();
        lista.setReceitas(Collections.singletonList(novaReceita));

        JAXBContext context = JAXBContext.newInstance(ListaReceitas.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

        StringWriter sw = new StringWriter();
        m.marshal(lista, sw);
        String xmlContent = sw.toString();

        ctx.header("Content-Type", "application/xml; charset=UTF-8");
        ctx.header("Content-Disposition",
                "attachment; filename=\"receita_" + titulo.replaceAll("\\s+", "_") + ".xml\"");
        ctx.result(xmlContent);
    }
}