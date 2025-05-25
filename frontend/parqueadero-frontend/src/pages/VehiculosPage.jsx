import { useState } from "react";
import VehiculoList from "../components/VehiculoList";
import VehiculoForm from "../components/VehiculoForm";

function VehiculosPage() {
  const [refresh, setRefresh] = useState(false);

  const handleVehiculoRegistrado = () => setRefresh(!refresh);

  return (
    <div className="page-container">
      <h1 className="page-title">Gestión de Vehículos</h1>
      <div className="page-content">
        <VehiculoForm onVehiculoRegistrado={handleVehiculoRegistrado} />
        <VehiculoList key={refresh} />
      </div>
    </div>
  );
}

export default VehiculosPage;

