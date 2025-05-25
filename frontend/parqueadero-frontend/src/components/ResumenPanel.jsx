import { useEffect, useState } from "react";
import axios from "axios";

function ResumenPanel() {
  const [resumen, setResumen] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios.get("http://localhost:8080/registro/resumen")
      .then(response => {
        setResumen(response.data);
        setLoading(false);
      })
      .catch(error => {
        setLoading(false);
        alert("Error cargando resumen");
      });
  }, []);

  if (loading) return <div>Cargando resumen...</div>;
  if (!resumen) return <div>No hay datos</div>;

  return (
    <div className="panel-resumen">
      <h2>Resumen Diario del Parqueadero</h2>
      <div>
        <strong>Entradas de hoy:</strong> {resumen.entradasHoy}
      </div>
      <div>
        <strong>Salidas de hoy:</strong> {resumen.salidasHoy}
      </div>
      <div>
        <strong>Celdas ocupadas:</strong> {resumen.celdasOcupadas}
      </div>
      <div>
        <strong>Celdas disponibles:</strong> {resumen.celdasDisponibles}
      </div>
      <div>
        <strong>Vehículos más frecuentes:</strong>
        <ul>
          {Object.entries(resumen.vehiculosMasFrecuentes).map(([placa, count]) => (
            <li key={placa}>{placa}: {count} veces</li>
          ))}
        </ul>
      </div>
    </div>
  );
}

export default ResumenPanel;
