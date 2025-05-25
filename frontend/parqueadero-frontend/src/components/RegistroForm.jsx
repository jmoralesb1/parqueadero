import { useState } from "react";
import { registrarEntradaPorPlaca, registrarSalidaPorPlaca } from "../api/registros";

function RegistroForm({ onRegistroCambiado }) {
  const [placaEntrada, setPlacaEntrada] = useState("");
  const [placaSalida, setPlacaSalida] = useState("");
  const [mensaje, setMensaje] = useState("");

  const handleEntrada = async (e) => {
    e.preventDefault();
    await registrarEntradaPorPlaca(placaEntrada);
    setPlacaEntrada("");
    setMensaje("¡Entrada registrada con éxito!");
    if (onRegistroCambiado) onRegistroCambiado();
    setTimeout(() => setMensaje(""), 2000);
  };

  const handleSalida = async (e) => {
    e.preventDefault();
    await registrarSalidaPorPlaca(placaSalida);
    setPlacaSalida("");
    setMensaje("¡Salida registrada con éxito!");
    if (onRegistroCambiado) onRegistroCambiado();
    setTimeout(() => setMensaje(""), 2000);
  };

  return (
  <div>
    {mensaje && <div className="success">{mensaje}</div>}
    <div className="formularios-contenedor">
      <form onSubmit={handleEntrada} className="form-modern">
        <h3>Registrar Entrada</h3>
        <input
          type="text"
          placeholder="Placa del Vehículo"
          value={placaEntrada}
          onChange={(e) => setPlacaEntrada(e.target.value)}
          required
        />
        <button type="submit">Registrar Entrada</button>
      </form>
      <form onSubmit={handleSalida} className="form-modern">
        <h3>Registrar Salida</h3>
        <input
          type="text"
          placeholder="Placa del Vehículo"
          value={placaSalida}
          onChange={(e) => setPlacaSalida(e.target.value)}
          required
        />
        <button type="submit">Registrar Salida</button>
      </form>
    </div>
  </div>
  );
}

export default RegistroForm;

