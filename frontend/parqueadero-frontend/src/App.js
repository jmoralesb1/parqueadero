import React from "react";
import { Routes, Route } from "react-router-dom";
import NavBar from "./components/NavBar";
import Dashboard from "./pages/Dashboard";
import Vehiculos from "./pages/VehiculosPage";
import Usuarios from "./pages/UsuariosPage";
import Celdas from "./pages/CeldasPage";
import Registros from "./pages/RegistrosPage";
import ResumenPanel from "./components/ResumenPanel";
import PagosPage from "./pages/PagosPage";
import "./styles/AppTheme.css";

function App() {
  return (
    <div>
      <NavBar />
      <Routes>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/vehiculos" element={<Vehiculos />} />
        <Route path="/usuarios" element={<Usuarios />} />
        <Route path="/celdas" element={<Celdas />} />
        <Route path="/registros" element={<Registros />} />
        <Route path="/resumen" element={<ResumenPanel />} />
        <Route path="/pagos" element={<PagosPage />} />
      </Routes>
    </div>
  );
}

export default App;



