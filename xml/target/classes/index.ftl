<!DOCTYPE html>
<html>
<head>
    <title>Gerenciador de Receitas XML</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <meta charset="UTF-8">
</head>
<body class="bg-gray-50 flex items-center justify-center min-h-screen">
    <div class="max-w-4xl w-full bg-white p-8 rounded-xl shadow-2xl border border-gray-200">
        <h1 class="text-3xl font-extrabold text-indigo-700 mb-2">🍽️ Receitas XML Handler</h1>
        
        <div id="feedback" class="mb-6">
            <#if erro??>
                <div class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative">
                    <span class="font-bold">Falha na Validação!</span> ${erro}
                </div>
            <#else>
                <#if mensagem??>
                    <div class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative">
                        <span class="font-bold">Sucesso!</span> ${mensagem}
                    </div>
                </#if>
            </#if>
        </div>

        <div class="grid md:grid-cols-2 gap-8">
            
            <div class="bg-indigo-50 p-6 rounded-lg border border-indigo-200 shadow-md">
                <h2 class="text-xl font-bold text-indigo-800 mb-4">1. Gerar e Exportar XML</h2>
                <p class="text-sm text-gray-600 mb-4">Cria um novo XML a partir dos dados.</p>
                
                <form action="/exportar" method="post" class="space-y-3">
                    <input type="text" name="titulo" placeholder="Título da Receita" class="w-full p-2 border border-indigo-300 rounded focus:ring-indigo-500 focus:border-indigo-500 text-sm">
                    <textarea name="descricao" placeholder="Descrição breve" class="w-full p-2 border border-indigo-300 rounded focus:ring-indigo-500 focus:border-indigo-500 text-sm" rows="2"></textarea>
                    <textarea name="ingredientes" placeholder="Ingredientes (um por linha)" class="w-full p-2 border border-indigo-300 rounded focus:ring-indigo-500 focus:border-indigo-500 text-sm" rows="3"></textarea>
                    <textarea name="modoPreparo" placeholder="Modo de Preparo" class="w-full p-2 border border-indigo-300 rounded focus:ring-indigo-500 focus:border-indigo-500 text-sm" rows="3"></textarea>
                    <input type="text" name="emojiFoto" placeholder="Emoji (Ex: 🍕)" class="w-full p-2 border border-indigo-300 rounded focus:ring-indigo-500 focus:border-indigo-500 text-sm">
                    <input type="url" name="imageUrl" placeholder="URL da Imagem (opcional)" class="w-full p-2 border border-indigo-300 rounded focus:ring-indigo-500 focus:border-indigo-500 text-sm">

                    <button type="submit" class="w-full bg-indigo-600 text-white p-2 rounded-lg font-semibold hover:bg-indigo-700 transition duration-150">
                        Exportar XML 📤
                    </button>
                </form>
            </div>

            <div class="bg-teal-50 p-6 rounded-lg border border-teal-200 shadow-md">
                <h2 class="text-xl font-bold text-teal-800 mb-4">2. Importar e Validar XML</h2>
                <p class="text-sm text-gray-600 mb-4">Faz upload do XML e valida sua estrutura usando o contrato XSD.</p>
                
                <form action="/importar" method="post" enctype="multipart/form-data" class="space-y-3">
                    <input type="file" name="xmlFile" accept=".xml" required
                           class="w-full p-2 border border-teal-300 rounded bg-white text-sm file:mr-4 file:py-1 file:px-2 file:rounded-lg file:border-0 file:text-sm file:font-semibold file:bg-teal-100 file:text-teal-700 hover:file:bg-teal-200">
                    <button type="submit" class="w-full bg-teal-600 text-white p-2 rounded-lg font-semibold hover:bg-teal-700 transition duration-150">
                        Importar e Validar XML ✅
                    </button>
                </form>
            </div>
        </div>

        <#if receitas??>
            <div class="mt-8 pt-4 border-t border-gray-300">
                <h2 class="text-2xl font-bold text-gray-800 mb-4">Dados POO Importados</h2>
                <p class="text-sm text-gray-600 mb-4">Abaixo estão os objetos de Receita criados na memória após a validação bem-sucedida:</p>
                <div class="space-y-3">
                    <#list receitas as r>
                        <div class="p-4 border border-gray-100 bg-white shadow-sm rounded-lg flex justify-between items-center">
                            <div>
                                <span class="text-xl">${(r.emojiFoto)!} ${r.titulo}</span>
                                <p class="text-xs text-gray-500 mt-1">ID: ${r.id}, Curso: ${r.descricao}</p>
                            </div>
                        </div>
                    </#list>
                </div>
            </div>
        </#if>
    </div>
</body>
</html>