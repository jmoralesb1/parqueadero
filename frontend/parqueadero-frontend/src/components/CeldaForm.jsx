import { useState } from "react";
import { registrarCelda } from "../api/celdas";

function CeldaForm({ onCeldaRegistrada }) {
  const [form, setForm] = useState({
    tipo: "AUTOMOVIL", // o "MOTOCICLETA"
    estado: "DISPONIBLE"
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    await registrarCelda(form);
    setForm({ tipo: "AUTOMOVIL", estado: "DISPONIBLE" });
    if (onCeldaRegistrada) onCeldaRegistrada();
  };

  return (
  <form onSubmit={handleSubmit} className="form-modern">
    <h2>Registrar Celda</h2>
    <select name="tipo" value={form.tipo} onChange={handleChange}>
      <option value="AUTOMOVIL">Automóvil</option>
      <option value="MOTOCICLETA">Motocicleta</option>
    </select>
    <select name="estado" value={form.estado} onChange={handleChange}>
      <option value="DISPONIBLE">Disponible</option>
      <option value="OCUPADO">Ocupado</option>
    </select>
    <button type="submit">Registrar</button>
  </form>
  );
}

export default CeldaForm;