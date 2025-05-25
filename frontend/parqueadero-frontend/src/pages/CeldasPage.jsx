import { useState } from "react";
import CeldaList from "../components/CeldaList";
import CeldaForm from "../components/CeldaForm";

function CeldasPage() {
  const [refresh, setRefresh] = useState(false);

  const handleCeldaRegistrada = () => setRefresh(!refresh);

  return (
    <div className="page-container">
      <h1 className="page-title">Gestión de Celdas</h1>
      <div className="page-content">
        <CeldaForm onCeldaRegistrada={handleCeldaRegistrada} />
        <CeldaList key={refresh} />
      </div>
    </div>
  );
}

export default CeldasPage;

