package com.ejemplo.parqueadero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import com.ejemplo.parqueadero.model.Pagos;
import com.ejemplo.parqueadero.service.PagosService;

@RestController
@RequestMapping("/pagos")
@CrossOrigin(origins = "*")
public class PagosController {

    @Autowired
    private PagosService pagosService;

    @PostMapping("/registrar-por-placa")
    public Pagos registrarPagoPorPlaca(
        @RequestParam String placa,
        @RequestParam double monto,
        @RequestParam String metodoPago,
        @RequestParam int cedula,
        @RequestParam(required = false) String referencia,
        @RequestParam(required = false) String observaciones,
        @RequestParam(required = false) String estado
    ) {
        return pagosService.registrarPagoPorPlaca(placa, monto, metodoPago, cedula, referencia, observaciones, estado);
    }

    @GetMapping("/listar")
    public List<Pagos> listarTodos() {
        return pagosService.listarPagos();
    }

    @GetMapping("/buscar")
    public List<Pagos> buscarPorPlaca(@RequestParam String placa) {
        return pagosService.buscarPorPlaca(placa);
    }

    // Manejo de excepciones
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        String msg;
        switch (ex.getMessage()) {
            case "NO_PLACA":
                msg = "Placa no registrada";
                break;
            case "NO_SALIDA":
                msg = "El vehículo no ha salido";
                break;
            case "NO_USUARIO":
                msg = "Usuario no registrado";
                break;
            default:
                msg = "Error interno: " + ex.getMessage();
        }
        return ResponseEntity.status(404).body(Map.of("message", msg));
    }

    //Editar pago
    @PutMapping("/editar/{id}")
    public Pagos editarPago(@PathVariable Long id,
                        @RequestParam(required = false) Double monto,
                        @RequestParam(required = false) String metodoPago,
                        @RequestParam(required = false) String referencia,
                        @RequestParam(required = false) String observaciones,
                        @RequestParam(required = false) String estado) {
    return pagosService.editarPago(id, monto, metodoPago, referencia, observaciones, estado);
    }
    
    // Anular pago no eliminar
    @PostMapping("/anular/{id}")
    public Pagos anularPago(@PathVariable Long id, @RequestParam(required = false) String observaciones) {
        return pagosService.anularPago(id, observaciones);
    }

}

