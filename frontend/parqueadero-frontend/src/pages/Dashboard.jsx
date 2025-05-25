import React from "react";
import { FaCar, FaMotorcycle, FaListAlt, FaUser, FaThLarge } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import "./Dashboard.css";

const MenuCard = ({ icon, label, to }) => {
  const navigate = useNavigate();
  return (
    <div
      className="menu-card"
      onClick={() => navigate(to)}
      tabIndex={0}
      onKeyPress={e => e.key === "Enter" && navigate(to)}
    >
      <div className="menu-icon">{icon}</div>
      <div className="menu-label">{label}</div>
    </div>
  );
};

const Dashboard = () => (
  <div className="dashboard-container">
    <h1 className="main-title">Sistema de Parqueadero</h1>
    <div className="menu-grid">
      <MenuCard icon={<FaCar size={60} />} label="Vehículos" to="/vehiculos" />
      <MenuCard icon={<FaMotorcycle size={60} />} label="Celdas" to="/celdas" />
      <MenuCard icon={<FaListAlt size={60} />} label="Registros" to="/registros" />
      <MenuCard icon={<FaUser size={60} />} label="Usuarios" to="/usuarios" />
      <MenuCard icon={<FaThLarge size={60} />} label="Resumen" to="/resumen" />
    </div>
  </div>
);

export default Dashboard;
