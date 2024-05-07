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
    "diretorio": ".documentos",
    "tipo": "pdf"
}
````

### Sucesso:
- `201` Arquivo criado no servidor

### Erros esperados:

- `404` Diretório especificado não existe;
- `409` Arquivo com mesmo nome já existe no diretório;

---

## Requisição: Obter arquivo

**GET** /arquivos/{arquivo-id}

Retorna os detalhes de um arquivo específico.

### Sucesso :
- `200` OK
```json
{
    "id": "12345",
    "nome": "meuarquivo.pdf",
    "tamanho": "224",
    "tipo": "pdf",
    "dataCriacao": "2024-04-29T10:30:00Z",
    "dataModificacao": "2024-04-29T10:35:00Z",
    "diretorio": "/documentos/",
}
```

### Erros esperados:

- `404` Arquivo não encontrado;

---

## Requisição: Renomear arquivo

**PATCH** /arquivos/{arquivo-id}

````json
{
    "novoNome": "novo_nome.pdf"
}
````

### Sucesso:
- `200` Renomeado

### Erros esperados:

- `404` Arquivo não encontrado;
- `409` Nome já existente no mesmo diretório.

---

## Requisição: Excluir arquivo

**DELETE** /arquivos/{arquivo-id}

Remove um arquivo específico.

### Sucesso:

- `200` Deletado

##### Erros esperados: Excluir arquivo

- `404` Arquivo não encontrado;

---

## Requisição: Criar diretório

**POST** /diretorios/

````json
{
    "nome": "novodiretorio",
    "diretorioPai": ".documentos" 
}
````

## Sucesso:
- `201` Diretorio criado

### Erros esperados:

- `404` Diretório pai não encontrado;
- `409` Diretório com mesmo nome já existe no diretório pai;

---

## Requisição: Listar conteúdo do diretório

**GET** /diretorios/{path}

Retorna uma lista de arquivos e subdiretórios em um diretório específico.

### Sucesso:
-  `200` OK
````json
{
    "diretorio": ".documentos",
    "conteudo": [
        {
            "id": "12345",
            "tipo": "pdf",
            "nome": "meuarquivo.pdf",
            "tamanho": "12",
            "tipoArquivo": "pdf",
            "dataModificacao": "2024-04-29T10:35:00Z",
        },
        {
            "id": "12345",
            "tipo": "docx",
            "nome": "relatorio.docx",
            "tamanho": "1888",
            "tipoArquivo": "docx",
            "dataModificacao": "2024-04-28T15:20:00Z",
        }
    ]
}
````

### Erros esperados:

- `404` Diretório não encontrado;

---

## Requisição: Gerar link de compartilhamento

**POST** /compartilhar/

````json
{
    "arquivo": "12345",
    "validade": "2024-05-30T12:00:00Z"
}
````

### Sucesso:
- `201` Link criado

### Erros esperados:

- `404` Arquivo não encontrado;

---

## Requisição: Acessar link compartilhado

**GET** /compartilhar/{link-id}

Retorna o arquivo associado a um link de compartilhamento.

### Sucesso:
- `302` Redireciona ao arquivo

### Erros esperados:

- `410` Link de compartilhamento expirado;
- `400` Link inválido;
