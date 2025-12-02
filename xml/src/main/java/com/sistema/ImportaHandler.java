package com.sistema;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.XMLConstants;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class ImportaHandler implements Handler {

    @Override
    public void handle(@NotNull Context ctx) throws Exception {
        UploadedFile xmlFile = ctx.uploadedFile("xmlFile");

        Map<String, Object> model = new HashMap<>();
        Path tempFile = null;

        if (xmlFile == null) {
            model.put("erro", "Nenhum arquivo XML foi enviado.");
            ctx.status(400).render("index.ftl", model);
            return;
        }

        try {
            tempFile = Files.createTempFile("uploaded-receita-", ".xml");
            try (InputStream input = xmlFile.content()) {
                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            }
            File xmlSource = tempFile.toFile();

            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            File schemaFile = new File(getClass().getClassLoader().getResource("receitas.xsd").getFile());
            Schema schema = factory.newSchema(schemaFile);

            schema.newValidator().validate(new javax.xml.transform.stream.StreamSource(xmlSource));

            System.out.println("XML VALIDADO COM SUCESSO contra o XSD.");

            JAXBContext context = JAXBContext.newInstance(ListaReceitas.class);
            Unmarshaller um = context.createUnmarshaller();

            ListaReceitas lista = (ListaReceitas) um.unmarshal(xmlSource);

            model.put("mensagem",
                    "XML validado e importado com sucesso! (" + lista.getReceitas().size() + " receitas)");
            model.put("sucesso", true);
            model.put("receitas", lista.getReceitas());

            ctx.render("index.ftl", model);

        } catch (Exception e) {
            String erroMsg = "ERRO de Validação: O XML não segue o contrato XSD. Detalhe: " + e.getMessage();
            System.err.println(erroMsg);

            model.put("erro", erroMsg);

            ctx.status(400).render("index.ftl", model);

        } finally {
            if (tempFile != null) {
                Files.deleteIfExists(tempFile);
            }
        }
    }
}