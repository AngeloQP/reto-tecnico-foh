package com.reto.backend.controller;

import com.reto.backend.entities.Cliente;
import com.reto.backend.service.ClienteService;
import com.reto.backend.util.Constantes;
import com.reto.backend.util.MensajeError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1/retotecnico/api")
public class ClienteController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job job;

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/cargarArchivo")
    public ResponseEntity<?> cargarArchivo(@RequestParam(name = "archivo") MultipartFile multipartFile) {
        String nombreArchivo = multipartFile.getOriginalFilename();

        try {
            Path ruta = Paths.get(Constantes.PATH_ARCHIVO_EXCEL + nombreArchivo);

            Files.createDirectories(ruta.getParent());
            Files.copy(multipartFile.getInputStream(), ruta, StandardCopyOption.REPLACE_EXISTING);

            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("cli", "cliente")
                    .addLong("id", System.currentTimeMillis())
                    .addDate("fecha", new Date())
                    .toJobParameters();

            jobLauncher.run(job, jobParameters);

            Map<String, String> response = new HashMap<>();
            response.put("archivo", nombreArchivo);
            response.put("estado", Constantes.MENSAJE_STATUS_OK);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error(MensajeError.MSJ_ERROR_RUNTIME, e.getMessage());
            throw new RuntimeException();
        }
    }

    @GetMapping("/listarClientes")
    public ResponseEntity<?> listarClientes(){
        List<Cliente> clientes = clienteService.listarClientes();
        if(clientes.isEmpty()){
            Map<String, Object> responseError = new HashMap<>();
            responseError.put("mensaje", MensajeError.MSJ_ERROR_CLIENTES);
            responseError.put("status", "false");
            return ResponseEntity.ok(responseError);
        }
        return ResponseEntity.ok(clientes);
    }
}
