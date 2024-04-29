# Casos de uso:

- Manipular arquivos
- Gerenciar diretórios
- Compartilhar arquivos

# Recursos

- Arquivos
- Diretórios
- Compartilhamento de links

# Endpoints

## Arquivos

- Enviar arquivo (`POST`)
- Obter arquivo (`GET`)
- Renomear arquivo (`PUT` ou `PATCH`)
- Excluir arquivo (`DELETE`)

## Diretórios

- Criar diretório (`POST`)
- Listar conteúdo do diretório (`GET`)

## Compartilhamento

- Gerar link de compartilhamento (`POST`)
- Acessar link compartilhado (`GET`)

---

## Requisição: Enviar arquivo

**POST** /arquivos/

````json
{
    "nome": "meuarquivo.pdf",
    "conteudo": "dados codificados do arquivo (b64)",
    "diretorio": "/documentos/",
}
````

### Erros esperados:

- Diretório especificado não existe;
- Arquivo com mesmo nome já existe no diretório;
- Falha ao decodificar conteúdo;
- Tamanho do arquivo excede o limite.

---

## Requisição: Obter arquivo

**GET** /arquivos/{arquivo-id}

Retorna os detalhes de um arquivo específico.

### Resposta de sucesso: Obter arquivo

````json
{
    "id": "12345",
    "nome": "meuarquivo.pdf",
    "tamanho": "2.5 MB",
    "tipo": "pdf",
    "dataCriacao": "2024-04-29T10:30:00Z",
    "dataModificacao": "2024-04-29T10:35:00Z",
    "diretorio": "/documentos/",
    "linkCompartilhado": "https://example.com/shared/12345",
    "proprietario": "user123"
}
````

### Erros esperados:

- Arquivo não encontrado;
- Falha ao recuperar informações do arquivo.

---

## Requisição: Renomear arquivo

**PATCH** /arquivos/{arquivo-id}

````json
{
    "novoNome": "novo_nome.pdf"
}
````

### Erros esperados:

- Arquivo não encontrado;
- Nome inválido (caracteres especiais não permitidos);
- Nome já existente no mesmo diretório.

---

## Requisição: Excluir arquivo

**DELETE** /arquivos/{arquivo-id}

Remove um arquivo específico.

### Resposta de sucesso: Excluir arquivo

- Código: `204 No Content`
  (Sem conteúdo)

##### Erros esperados: Excluir arquivo

- Arquivo não encontrado;
- Falha ao excluir arquivo;
- Permissões insuficientes para excluir o arquivo.

---

## Requisição: Criar diretório

**POST** /diretorios/

````json
{
    "nome": "novodiretorio",
    "diretorioPai": "/documentos/" 
}
````

### Erros esperados:

- Diretório pai não encontrado;
- Diretório com mesmo nome já existe no diretório pai;
- Nome inválido (caracteres especiais não permitidos).

---

## Requisição: Listar conteúdo do diretório

**GET** /diretorios/{diretorio-id}

Retorna uma lista de arquivos e subdiretórios em um diretório específico.

### Resposta de sucesso: Listar conteúdo do diretório

````json
{
    "diretorio": "/documentos/",
    "conteudo": [
        {
            "tipo": "diretorio",
            "nome": "fotos"
        },
        {
            "tipo": "arquivo",
            "id": "12345",
            "nome": "meuarquivo.pdf",
            "tamanho": "2.5 MB",
            "tipoArquivo": "pdf",
            "dataModificacao": "2024-04-29T10:35:00Z",
            "link": "https://example.com/arquivos/12345"
        },
        {
            "tipo": "arquivo",
            "id": "12345",
            "nome": "relatorio.docx",
            "tamanho": "1.8 MB",
            "tipoArquivo": "docx",
            "dataModificacao": "2024-04-28T15:20:00Z",
            "link": "https://example.com/arquivos/67890"
        }
    ]
}
````

### Erros esperados:

- Diretório não encontrado;
- Falha ao recuperar conteúdo do diretório.

---

## Requisição: Gerar link de compartilhamento

**POST** /compartilhar/

````json
{
    "arquivo": "12345",
    "validade": "2024-05-30T12:00:00Z"
}
````

### Erros esperados:

- Arquivo não encontrado;
- Data de validade inválida;
- Arquivo não pode ser compartilhado.

Este exemplo apresenta um esboço de documentação para uma API REST de um serviço de armazenamento de arquivos. Cada endpoint descreve uma operação sobre recursos como arquivos, diretórios e compartilhamentos, indicando os dados esperados nas requisições e os possíveis erros que podem ocorrer.

---

## Requisição: Acessar link compartilhado

**GET** /compartilhar/{link-id}

Retorna o arquivo associado a um link de compartilhamento.

### Resposta de sucesso: Acessar link compartilhado

Redireciona para o arquivo ou exibe uma página de visualização.

### Erros esperados:

- Link de compartilhamento expirado;
- Link inválido;
- Arquivo não disponível para acesso.

Este exemplo detalha as requisições `GET` para obter informações sobre arquivos e diretórios, bem como acessar conteúdo por meio de links compartilhados em uma API REST para um serviço de armazenamento de arquivos. Cada requisição especifica os parâmetros esperados e os possíveis resultados, incluindo as respostas de sucesso e os erros esperados.