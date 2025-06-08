import React, { useState } from "react";
import { editarPago } from "../api/pagos";

function PagosEditForm({ pago, onClose, onEditSuccess }) {
  const [monto, setMonto] = useState(pago.monto);
  const [metodoPago, setMetodoPago] = useState(pago.metodoPago);
  const [referencia, setReferencia] = useState(pago.referencia || "");
  const [observaciones, setObservaciones] = useState(pago.observaciones || "");
  const [estado, setEstado] = useState(pago.estado || "");
  const [error, setError] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await editarPago(pago.id, { monto, metodoPago, referencia, observaciones, estado });
      onEditSuccess();
      onClose();
    } catch (err) {
      setError(err.response?.data?.message || err.message);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="edit-form">
      <h3>Editar Pago #{pago.id}</h3>
      <input
        type="number"
        placeholder="Monto"
        value={monto}
        onChange={e => setMonto(e.target.value)}
        min="0"
        step="0.01"
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
      <button type="submit">Guardar</button>
      <button type="button" onClick={onClose}>Cancelar</button>
      {error && <div className="error">{error}</div>}
    </form>
  );
}

export default PagosEditForm;
