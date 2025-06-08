package com.ejemplo.parqueadero.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.parqueadero.model.Pagos;
import com.ejemplo.parqueadero.model.Registros;
import com.ejemplo.parqueadero.model.Usuario;
import com.ejemplo.parqueadero.repository.PagosRepository;
import com.ejemplo.parqueadero.repository.RegistroRepository;
import com.ejemplo.parqueadero.repository.UsuarioRepository;

@Service
public class PagosService {

    @Autowired
    private PagosRepository pagosRepository;

    @Autowired
    private RegistroRepository registroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Pagos registrarPagoPorPlaca(String placa, double monto, String metodoPago, int cedula, String referencia, String observaciones, String estado) {
        Registros registro = registroRepository
            .findTopByVehiculoPlacaOrderByFechaEntradaDesc(placa)
            .orElseThrow(() -> new RuntimeException("NO_PLACA"));

        if (registro.getFechaSalida() == null) {
            throw new RuntimeException("NO_SALIDA");
        }

        Usuario usuario = usuarioRepository.findByCedula(cedula)
            .orElseThrow(() -> new RuntimeException("NO_USUARIO"));

        Pagos pago = new Pagos();
        pago.setMonto(monto);
        pago.setMetodoPago(metodoPago);
        pago.setFechaPago(LocalDateTime.now());
        pago.setRegistro(registro);
        pago.setUsuario(usuario);
        pago.setReferencia(referencia);
        pago.setObservaciones(observaciones);
        pago.setEstado(estado);

        return pagosRepository.save(pago);
    }

    public List<Pagos> listarPagos() {
        return pagosRepository.findAll();
    }

    public List<Pagos> buscarPorPlaca(String placa) {
        return pagosRepository.findByRegistroVehiculoPlaca(placa);
    }

    public Pagos editarPago(Long id, Double monto, String metodoPago, String referencia, String observaciones, String estado) {
    Pagos pago = pagosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    if(monto != null) pago.setMonto(monto);
    if(metodoPago != null) pago.setMetodoPago(metodoPago);
    if(referencia != null) pago.setReferencia(referencia);
    if(observaciones != null) pago.setObservaciones(observaciones);
    if(estado != null) pago.setEstado(estado);
    return pagosRepository.save(pago);
    }

    public Pagos anularPago(Long id, String observaciones) {
    Pagos pago = pagosRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    pago.setEstado("Anulado");
    if(observaciones != null) pago.setObservaciones(observaciones);
    return pagosRepository.save(pago);
    }

}




