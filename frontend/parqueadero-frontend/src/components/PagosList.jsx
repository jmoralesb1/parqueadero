import React, { useEffect, useState } from "react";
import { getPagos, anularPago, editarPago } from "../api/pagos";

function PagosList() {
  const [pagos, setPagos] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [modalTipo, setModalTipo] = useState(""); // "editar" o "anular"
  const [pagoSeleccionado, setPagoSeleccionado] = useState(null);

  // Estados para editar/anular
  const [monto, setMonto] = useState("");
  const [metodoPago, setMetodoPago] = useState("");
  const [referencia, setReferencia] = useState("");
  const [observaciones, setObservaciones] = useState("");
  const [estado, setEstado] = useState("");

  useEffect(() => {
    cargarPagos();
  }, []);

  const cargarPagos = async () => {
    setPagos(await getPagos());
  };

  // --- MODAL ---
  const abrirModalAnular = (pago) => {
    setPagoSeleccionado(pago);
    setObservaciones(""); // Limpiar observaciones
    setModalTipo("anular");
    setShowModal(true);
  };

  const abrirModalEditar = (pago) => {
    setPagoSeleccionado(pago);
    setMonto(pago.monto || "");
    setMetodoPago(pago.metodoPago || "");
    setReferencia(pago.referencia || "");
    setObservaciones(pago.observaciones || "");
    setEstado(pago.estado || "");
    setModalTipo("editar");
    setShowModal(true);
  };

  const cerrarModal = () => {
    setShowModal(false);
    setPagoSeleccionado(null);
    setModalTipo("");
    setMonto("");
    setMetodoPago("");
    setReferencia("");
    setObservaciones("");
    setEstado("");
  };

  // --- ACCIONES ---
  const confirmarAnular = async () => {
    try {
      await anularPago(pagoSeleccionado.id, observaciones);
      cerrarModal();
      cargarPagos();
    } catch (err) {
      alert("Error al anular el pago");
    }
  };

  const confirmarEditar = async () => {
    try {
      await editarPago(pagoSeleccionado.id, {
        monto,
        metodoPago,
        referencia,
        observaciones,
        estado,
      });
      cerrarModal();
      cargarPagos();
    } catch (err) {
      alert("Error al editar el pago");
    }
  };

  return (
    <div className="pagos-list">
      <h2>Pagos Registrados</h2>
      <div className="table-responsive">
        <table className="app-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Placa</th>
              <th>Vehículo</th>
              <th>Monto</th>
              <th>Fecha de Pago</th>
              <th>Usuario</th>
              <th>Método</th>
              <th>Referencia</th>
              <th>Observaciones</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {pagos.map((pago) => (
              <tr key={pago.id}>
                <td>{pago.id}</td>
                <td>{pago.registro?.vehiculo?.placa}</td>
                <td>
                  {pago.registro?.vehiculo
                    ? `${pago.registro.vehiculo.marca} ${pago.registro.vehiculo.modelo}`
                    : ""}
                </td>
                <td>
                  {pago.monto?.toLocaleString("es-CO", {
                    style: "currency",
                    currency: "COP",
                    minimumFractionDigits: 2,
                  })}
                </td>
                <td>{pago.fechaPago?.replace("T", " ").slice(0, 19)}</td>
                <td>{pago.usuario?.nombre || pago.usuario?.cedula || ""}</td>
                <td>{pago.metodoPago}</td>
                <td>{pago.referencia}</td>
                <td>{pago.observaciones}</td>
                <td>{pago.estado}</td>
                <td>
                  <button onClick={() => abrirModalEditar(pago)}>Editar</button>
                  &nbsp;
                  <button onClick={() => abrirModalAnular(pago)}>Anular</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* MODAL REUTILIZABLE PARA ANULAR O EDITAR */}
      {showModal && (
        <div className="app-modal-backdrop">
          <div className="app-modal">
            {modalTipo === "anular" ? (
              <>
                <h2>Anular Pago</h2>
                <p>
                  ¿Estás seguro de anular el pago #{pagoSeleccionado?.id}?
                </p>
                <input
                  type="text"
                  placeholder="Observaciones (opcional)"
                  value={observaciones}
                  onChange={(e) => setObservaciones(e.target.value)}
                  style={{
                    width: "100%",
                    marginBottom: "1rem",
                    padding: "0.8rem 1.2rem",
                    borderRadius: "1.2rem",
                    border: "1.3px solid #b8c7e2",
                    fontSize: "1.07rem",
                  }}
                />
                <div className="modal-actions">
                  <button onClick={confirmarAnular}>Sí, Anular</button>
                  <button className="cancel-btn" onClick={cerrarModal}>
                    Cancelar
                  </button>
                </div>
              </>
            ) : (
              <>
                <h2>Editar Pago #{pagoSeleccionado?.id}</h2>
                <input
                  type="number"
                  placeholder="Monto"
                  value={monto}
                  onChange={(e) => setMonto(e.target.value)}
                />
                <select
                  value={metodoPago}
                  onChange={(e) => setMetodoPago(e.target.value)}
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
                  onChange={(e) => setReferencia(e.target.value)}
                />
                <input
                  type="text"
                  placeholder="Observaciones"
                  value={observaciones}
                  onChange={(e) => setObservaciones(e.target.value)}
                />
                <input
                  type="text"
                  placeholder="Estado"
                  value={estado}
                  onChange={(e) => setEstado(e.target.value)}
                />
                <div className="modal-actions">
                  <button onClick={confirmarEditar}>Guardar</button>
                  <button className="cancel-btn" onClick={cerrarModal}>
                    Cancelar
                  </button>
                </div>
              </>
            )}
          </div>
        </div>
      )}
    </div>
  );
}

export default PagosList;
