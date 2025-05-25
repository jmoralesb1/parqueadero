import { useEffect, useState } from "react";
import { getVehiculos } from "../api/vehiculos";

function VehiculoList() {
  const [vehiculos, setVehiculos] = useState([]);

  useEffect(() => {
    getVehiculos().then(setVehiculos);
  }, []);

  return (
    <div>
      <h2>Vehículos Registrados</h2>
      <div className="table-responsive">
        <table className="app-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Placa</th>
              <th>Marca</th>
              <th>Modelo</th>
              <th>Color</th>
              <th>Tipo</th>
            </tr>
          </thead>
          <tbody>
            {vehiculos.map((v) => (
              <tr key={v.id}>
                <td>{v.id}</td>
                <td>{v.placa}</td>
                <td>{v.marca}</td>
                <td>{v.modelo}</td>
                <td>{v.color}</td>
                <td>{v.tipo}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default VehiculoList;
