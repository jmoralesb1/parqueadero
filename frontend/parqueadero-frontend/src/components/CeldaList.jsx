import { useEffect, useState } from "react";
import { getCeldas } from "../api/celdas";

function CeldaList() {
  const [celdas, setCeldas] = useState([]);

  useEffect(() => {
    getCeldas().then(setCeldas);
  }, []);

  return (
    <div>
      <h2>Celdas</h2>
      <div className="table-responsive">
        <table className="app-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Tipo</th>
              <th>Estado</th>
              <th>Vehículo Asignado</th>
            </tr>
          </thead>
          <tbody>
            {celdas.map((c) => (
              <tr key={c.id}>
                <td>{c.id}</td>
                <td>{c.tipo}</td>
                <td>{c.estado}</td>
                <td>{c.vehiculo ? c.vehiculo.placa : "Libre"}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default CeldaList;
