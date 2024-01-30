package com.reto.backend.steps;

import com.reto.backend.entities.Cliente;
import com.reto.backend.util.Utilitario;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.batch.item.ItemProcessor;

public class ClienteItemProcessor implements ItemProcessor<Row, Cliente> {

    @Override
    public Cliente process(Row row) throws Exception {
        String nombres = Utilitario.getStringCellValue(row, 0);
        String apellidos = Utilitario.getStringCellValue(row, 1);
        String dni = Utilitario.getNumericStringValue(row, 2);
        String telefono = Utilitario.getNumericStringValue(row, 3);
        String email = Utilitario.getStringCellValue(row, 4);

        Cliente cliente = new Cliente();
        cliente.setNombres(nombres);
        cliente.setApellidos(apellidos);
        cliente.setDni(dni);
        cliente.setTelefono(telefono);
        cliente.setEmail(email);
        return cliente;
    }




}
