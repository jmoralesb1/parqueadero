import { useState } from "react";
import axios from "axios";

function VehiculoForm({ onVehiculoRegistrado }) {
  const [form, setForm] = useState({
    placa: "",
    marca: "",
    modelo: "",
    color: "",
    tipo: "AUTOMOVIL",
    cedula: ""
  });
  const [mensaje, setMensaje] = useState("");
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setMensaje("");
    setError("");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMensaje("");
    setError("");
    try {
      await axios.post(
        "http://localhost:8080/vehiculos/registrar-con-cedula?cedula=" + form.cedula,
        {
          placa: form.placa,
          marca: form.marca,
          modelo: form.modelo,
          color: form.color,
          tipo: form.tipo
        }
      );
      setMensaje("¡Vehículo registrado exitosamente!");
      setForm({
        placa: "",
        marca: "",
        modelo: "",
        color: "",
        tipo: "AUTOMOVIL",
        cedula: ""
      });
      if (onVehiculoRegistrado) onVehiculoRegistrado();
    } catch (err) {
      setError(
        err.response?.data?.message ||
        "Error al registrar el vehículo. Verifica la cédula y los datos."
      );
    }
  };

  return (
    <form onSubmit={handleSubmit} className="form-modern">
      <h2>Registrar Vehículo</h2>
      {mensaje && <div className="success">{mensaje}</div>}
      {error && <div className="error">{error}</div>}
      <input name="placa" placeholder="Placa" value={form.placa} onChange={handleChange} required />
      <input name="marca" placeholder="Marca" value={form.marca} onChange={handleChange} required />
      <input name="modelo" placeholder="Modelo" value={form.modelo} onChange={handleChange} required />
      <input name="color" placeholder="Color" value={form.color} onChange={handleChange} required />
      <select name="tipo" value={form.tipo} onChange={handleChange}>
        <option value="AUTOMOVIL">Automóvil</option>
        <option value="MOTOCICLETA">Motocicleta</option>
      </select>
      <input
        name="cedula"
        placeholder="Cédula del Usuario"
        value={form.cedula}
        onChange={handleChange}
        required
        type="number"
      />
      <button type="submit">Registrar</button>
    </form>

  );
}

export default VehiculoForm;