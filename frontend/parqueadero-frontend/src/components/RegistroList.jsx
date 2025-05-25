import { useEffect, useState } from "react";
import { getRegistros } from "../api/registros";
import { format } from "date-fns";

function RegistroList() {
  const [registros, setRegistros] = useState([]);

  useEffect(() => {
    getRegistros().then(setRegistros);
  }, []);

  const formatoFecha = (fecha) => {
    if (!fecha) return "Pendiente";
    return format(new Date(fecha), 'dd-MM-yyyy hh:mm:ss a');
  };

  return (
    <div>
      <h2>Registros de Entradas y Salidas</h2>
      <div className="table-responsive">
        <table className="app-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Vehículo</th>
              <th>Celda</th>
              <th>Fecha Entrada</th>
              <th>Fecha Salida</th>
            </tr>
          </thead>
          <tbody>
            {registros.map((r) => (
              <tr key={r.id}>
                <td>{r.id}</td>
                <td>{r.vehiculo?.placa}</td>
                <td>{r.celda?.id}</td>
                <td>{formatoFecha(r.fechaEntrada)}</td>
                <td>{formatoFecha(r.fechaSalida)}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default RegistroList;


