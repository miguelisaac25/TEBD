# 📄 Projeto: Validação e Comunicação XML (Java/Javalin)

Este projeto é um endpoint de serviço web simples que demonstra a comunicação e a segurança de dados ao trocar informações no formato **XML**, usando um **contrato XSD** para garantir a validade dos dados.

## 🎯 Conceitos-Chave Demonstrados

* [cite_start]**XML (eXtensible Markup Language):** Formato padrão para estruturação e troca de dados semiestruturados[cite: 20, 21].
* [cite_start]**Esquema XML (XSD):** Atua como o **contrato de dados**[cite: 100, 102]. [cite_start]É o mecanismo moderno para **validar** a estrutura e o tipo de conteúdo do XML[cite: 109, 114].
* **POO (Mapeamento de Objetos):** Uso do **JAXB** (Java Architecture for XML Binding) para:
    * **Marshalling:** Converter objetos Java (`Receita`) para XML (Exportação).
    * **Unmarshalling:** Converter XML válido para objetos Java (Importação).

---

## 🛠️ Tecnologias Utilizadas

| Componente | Função |
| :--- | :--- |
| **Linguagem** | Java 17 |
| **Framework Web** | Javalin (Micro-framework leve) |
| **Visualização** | Freemarker (.ftl) + Tailwind CSS |
| **XML/POO** | JAXB (Para Mapeamento Objeto ↔ XML) |
| **Contrato** | Esquema XML (`receitas.xsd`) |
| **Gerenciador** | Maven (`pom.xml`) |

---

## ⚙️ Funcionalidades do Sistema

O sistema é acessado via `http://localhost:7000/` e possui duas funções:

### 1. 📤 Exportar XML (Java → XML)

Recebe dados do formulário e converte o **Objeto Java** em um documento XML formatado, pronto para download.

### 2. ✅ Importar e Validar XML (XML → XSD → Java)

Permite o upload de um arquivo XML e executa o processo de **validação obrigatória**:

1.  O sistema verifica o XML de entrada contra o **contrato `receitas.xsd`**.
2.  Se a validação **falhar** (ex: falta a tag `<titulo>`), o XML é **rejeitado**, provando que o XSD protege a integridade do sistema.
3.  Se a validação for **bem-sucedida**, o JAXB converte o XML em **Objetos `Receita`** em Java.

---

## 🚀 Cenários de Teste (Para Demonstração)

| Teste | Ação | Conceito Comprovado |
| :--- | :--- | :--- |
| **Sucesso** | Gere um XML usando o formulário e, em seguida, use o botão **Importar** com o mesmo arquivo. | Marshalling, Unmarshalling e Validação bem-sucedida. |
| **Falha** | Abra o XML exportado, **remova a tag `<titulo>`** e tente importar o arquivo modificado. | O **XSD (Contrato)** está protegendo o sistema ao rejeitar dados com estrutura inválida. |

---

## 🏃 Como Executar

1.  **Requisitos:** Java 17 e Maven.
2.  **Compilação:** Abra o terminal na pasta raiz do projeto e execute:
    ```bash
    mvn clean install
    ```
3.  **Execução:** Rode o método `main` na classe `App.java`.
4.  **Acesso:** Abra seu navegador em: `http://localhost:7000`
