import { useState } from "react";
import RegistroList from "../components/RegistroList";
import RegistroForm from "../components/RegistroForm";
import HistorialPorPlaca from "../components/HistorialPorPlaca";



function RegistrosPage() {
  const [refresh, setRefresh] = useState(false);

  const handleRegistroCambiado = () => setRefresh(!refresh);

  return (
    <div className="page-container">
      <h1 className="page-title">Gestión de Entradas y Salidas</h1>
      {/* - */}
      <HistorialPorPlaca />
      <div className="page-content">
        <RegistroForm onRegistroCambiado={handleRegistroCambiado} />
        <RegistroList key={refresh} />
      </div>
    </div>
  );
}

export default RegistrosPage;


