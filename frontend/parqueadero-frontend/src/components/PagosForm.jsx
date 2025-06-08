import React, { useState } from "react";
import { registrarPagoPorPlaca } from "../api/pagos";
import { getUsuarioPorPlaca } from "../api/usuarios";

function PagosForm({ onPagoRegistrado }) {
  const [placa, setPlaca] = useState("");
  const [monto, setMonto] = useState("");
  const [metodoPago, setMetodoPago] = useState("");
  const [cedula, setCedula] = useState("");
  const [referencia, setReferencia] = useState("");
  const [observaciones, setObservaciones] = useState("");
  const [estado, setEstado] = useState("");
  const [successMsg, setSuccessMsg] = useState("");
  const [errorMsg, setErrorMsg] = useState("");

  const buscarCedulaPorPlaca = async () => {
    if (placa.trim() !== "") {
      try {
        const usuario = await getUsuarioPorPlaca(placa.trim());
        setCedula(usuario.cedula);
        setErrorMsg("");
      } catch (err) {
        setCedula("");
        setErrorMsg("No se encontró usuario para la placa ingresada");
      }
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await registrarPagoPorPlaca(
        placa, monto, metodoPago, parseInt(cedula, 10), referencia, observaciones, estado
      );
      setSuccessMsg("Pago registrado exitosamente");
      setErrorMsg("");
      setPlaca(""); setMonto(""); setMetodoPago(""); setCedula("");
      setReferencia(""); setObservaciones(""); setEstado("");
      if (onPagoRegistrado) onPagoRegistrado();
    } catch (err) {
      setSuccessMsg("");
      setErrorMsg(
        "Error al registrar el pago: " +
        (err.response?.data?.message || err.message)
      );
    }
  };

  return (
    <form className="form-modern" onSubmit={handleSubmit}>
      <h2>Registrar Pago</h2>
      <input
        type="text"
        placeholder="Placa"
        value={placa}
        onChange={e => setPlaca(e.target.value.toUpperCase())}
        onBlur={buscarCedulaPorPlaca}
        required
      />
      <input
        type="number"
        placeholder="Monto"
        value={monto}
        onChange={e => setMonto(e.target.value)}
        min="0"
        step="0.01"
        required
      />
      <input
        type="number"
        placeholder="Cédula usuario"
        value={cedula}
        readOnly
        required
      />
      <select
        value={metodoPago}
        onChange={e => setMetodoPago(e.target.value)}
        required
      >
        <option value="">Selecciona método de pago</option>
        <option value="Efectivo">Efectivo</option>
        <option value="Tarjeta">Tarjeta</option>
        <option value="Transferencia">Transferencia</option>
      </select>
      <input
        type="text"
        placeholder="Referencia"
        value={referencia}
        onChange={e => setReferencia(e.target.value)}
      />
      <input
        type="text"
        placeholder="Observaciones"
        value={observaciones}
        onChange={e => setObservaciones(e.target.value)}
      />
      <input
        type="text"
        placeholder="Estado"
        value={estado}
        onChange={e => setEstado(e.target.value)}
      />
      <button type="submit">Registrar Pago</button>
      {successMsg && <div className="success">{successMsg}</div>}
      {errorMsg && <div className="error">{errorMsg}</div>}
    </form>
  );
}

export default PagosForm;
