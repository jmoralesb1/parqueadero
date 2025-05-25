import React, { useState } from "react";
import { getHistorialPorPlaca } from "../api/registros";
import "./HistorialPorPlaca.css"; // Nuevo archivo de estilos

function HistorialPorPlaca() {
  const [placa, setPlaca] = useState("");
  const [historial, setHistorial] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleBuscar = async (e) => {
    e.preventDefault();
    setLoading(true);
    try {
      const data = await getHistorialPorPlaca(placa.trim().toUpperCase());
      setHistorial(data);
      setError("");
    } catch (err) {
      setError("No se pudo encontrar la información.");
      setHistorial([]);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="historialporplaca-container">
      <form className="historialporplaca-form" onSubmit={handleBuscar}>
        <h3>Consulta de Historial por Placa</h3>
        <div className="form-group">
          <input
            type="text"
            placeholder="Ingrese placa"
            value={placa}
            maxLength={8}
            onChange={(e) => setPlaca(e.target.value.toUpperCase())}
            className="input-placa"
            required
          />
          <button type="submit" className="btn-buscar">
            Buscar
          </button>
        </div>
      </form>
      {loading && <div className="historialporplaca-cargando">Cargando...</div>}
      {error && <div className="historialporplaca-error">{error}</div>}
      {!loading && historial.length > 0 && (
        <div>
          <p className="historialporplaca-resumen">
            <strong>El vehículo con placa <span className="placa-color">{placa}</span> ha ingresado {historial.length} veces.</strong>
          </p>
          <div className="historialporplaca-table-wrapper">
            <table className="historialporplaca-table">
              <thead>
                <tr>
                  <th>ID Registro</th>
                  <th>Fecha Entrada</th>
                  <th>Fecha Salida</th>
                  <th>ID Celda</th>
                </tr>
              </thead>
              <tbody>
                {historial.map((registro) => (
                  <tr key={registro.id}>
                    <td>{registro.id}</td>
                    <td>{registro.fechaEntrada?.slice(0, 19).replace("T", " ")}</td>
                    <td>{registro.fechaSalida ? registro.fechaSalida.slice(0, 19).replace("T", " ") : "—"}</td>
                    <td>{registro.celda?.id}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
      {!loading && historial.length === 0 && !error && (
        <div className="historialporplaca-vacio">Sin registros para esta placa</div>
      )}
    </div>
  );
}

export default HistorialPorPlaca;

