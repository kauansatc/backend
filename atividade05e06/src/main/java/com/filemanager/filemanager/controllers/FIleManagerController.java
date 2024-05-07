package com.filemanager.filemanager.controllers;

import com.filemanager.filemanager.models.*;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.filemanager.filemanager.models.FileDetails;

@RestController
public class FIleManagerController {
    private ArrayList<FileDetails> arquivos = new ArrayList<>();

    @PostMapping("/arquivos")
    public void uploadFile(@RequestBody FileUploadRequest request) {
        var arquivo = new FileDetails(request);
        System.out.printf("uploaded %s", arquivo.getId());
        arquivos.add(arquivo);
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
        for (var arquivo : arquivos) {
            if (arquivo.getId().equals(fileId)) {
                arquivo.setNome(request.getNovoNome());
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arquivo não encontrado");
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

    /*
     * @PostMapping("/diretorios/")
     * public void createDirectory(@RequestBody DirectoryCreateRequest request) {
     * // Implement directory creation logic here
     * }
     * 
     * @GetMapping("/diretorios/{diretorio-id}")
     * public DirectoryContent getDirectoryContent(@PathVariable("diretorio-id")
     * String directoryId) {
     * // Implement directory content retrieval logic here
     * }
     * 
     * @PostMapping("/compartilhar/")
     * public void shareFile(@RequestBody FileShareRequest request) {
     * // Implement file sharing logic here
     * }
     * 
     * @GetMapping("/compartilhar/{link-id}")
     * public void accessSharedLink(@PathVariable("link-id") String linkId) {
     * // Implement shared link access logic here
     * }
     */
}