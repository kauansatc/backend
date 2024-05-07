package com.filemanager.filemanager.controllers;

import com.filemanager.filemanager.models.*;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FIleManagerController {
    private ArrayList<Directory> diretorios = new ArrayList<>();
    {
        diretorios.add(new Directory(".", ""));
    }
    private ArrayList<FileDetails> arquivos = new ArrayList<>();
    private ArrayList<SharedFile> sharedFiles = new ArrayList<>();

    @PostMapping("/arquivos")
    public ResponseEntity<?> uploadFile(@RequestBody FileUploadRequest request) {
        var arquivo = new FileDetails(request);

        String[] dirOpts = arquivo.getDiretorio().split("\\.");

        boolean validDir = false;
        for (var diretorio : diretorios) {
            if ((dirOpts[0] != null || diretorio.getDiretorioPai() == dirOpts[0])
                    && diretorio.getNome().equals(dirOpts[1])) {
                validDir = true;
            }
        }
        if (!validDir)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diretório não encontrado");

        for (var arq : arquivos) {
            if (arq.getNome().equals(arquivo.getNome()) && arq.getDiretorio().equals(arquivo.getDiretorio())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Arquivo já existe");
            }
        }

        arquivos.add(arquivo);
        return ResponseEntity.status(HttpStatus.CREATED).body(arquivo.getId());
    }

    @GetMapping("/arquivos/{arquivo-id}")
    public ResponseEntity<?> getFile(@PathVariable("arquivo-id") String fileId) {
        for (var arquivo : arquivos) {
            if (arquivo.getId().equals(fileId)) {
                return ResponseEntity.ok(arquivo);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo não encontrado");
    }

    @PatchMapping("/arquivos/{arquivo-id}")
    public ResponseEntity<?> renameFile(@PathVariable("arquivo-id") String fileId,
            @RequestBody FileRenameRequest request) {
        FileDetails renomeado = null;

        for (var arquivo : arquivos) {
            if (arquivo.getId().equals(fileId)) {
                renomeado = arquivo;
            }
        }

        if (renomeado == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo não encontrado");

        for (var arquivo : arquivos) {
            if (arquivo.getNome().equals(request.getNovoNome())
                    && arquivo.getDiretorio().equals(renomeado.getDiretorio())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Arquivo já existe");
            }
        }

        renomeado.setNome(request.getNovoNome());
        renomeado.setDataModificacao(String.valueOf(new Date(System.currentTimeMillis())));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/arquivos/{arquivo-id}")
    public ResponseEntity<?> deleteFile(@PathVariable("arquivo-id") String fileId) {
        for (var arquivo : arquivos) {
            if (arquivo.getId().equals(fileId)) {
                arquivos.remove(arquivo);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo não encontrado");
    }

    @PostMapping("/diretorios")
    public ResponseEntity<?> createDirectory(@RequestBody Directory request) {
        boolean foundParent = false;

        for (var diretorio : diretorios) {
            if (diretorio.getNome().equals(request.getDiretorioPai()))
                foundParent = true;

            if (diretorio.getDiretorioPai().equals(request.getDiretorioPai())
                    && diretorio.getNome().equals(request.getNome())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Diretório já existe");
            }
        }

        if (!foundParent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diretório pai não encontrado");
        }

        diretorios.add(request);
        System.out.println("Diretório criado");
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/diretorios/{path}")
    public ResponseEntity<?> getDirectoryContent(@PathVariable("path") String path) {
        String[] dirOpts = path.split("\\.");

        for (var diretorio : diretorios) {
            if ((dirOpts[0] != null || diretorio.getDiretorioPai() == dirOpts[0])
                    && diretorio.getNome().equals(dirOpts[1])) {
                FileDetails[] conteudo = arquivos.stream().filter(arquivo -> arquivo.getDiretorio().equals(path))
                        .toArray(FileDetails[]::new);

                return ResponseEntity.ok(new DirectoryContent(path, conteudo));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Diretório não encontrado");
    }

    @PostMapping("/compartilhar")
    public ResponseEntity<?> shareFile(@RequestBody FileShareRequest request) {
        // Implement file sharing logic here
        var share = new SharedFile(request);

        for (var arquivo : arquivos) {
            if (arquivo.getId().equals(share.getIdArquivo())) {
                sharedFiles.add(share);
                return ResponseEntity.status(HttpStatus.CREATED).body(share.getId());
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo não encontrado");
    }

    @GetMapping("/compartilhar/{link-id}")
    public ResponseEntity<?> accessSharedLink(@PathVariable("link-id") String linkId) {
        for (var sharedFile : sharedFiles) {
            if (sharedFile.getId().equals(linkId)) {
                if (sharedFile.getValidade().before(new Date(System.currentTimeMillis()))) {
                    return ResponseEntity.status(HttpStatus.GONE).body("Link expirado");
                }

                return ResponseEntity.status(HttpStatus.FOUND)
                        .header("Location", "../arquivos/" + sharedFile.getIdArquivo()).build();
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Link não encontrado");
    }
}