import { useState, useEffect } from "react";

function UsuarioForm({ onUsuarioGuardado, usuarioInicial }) {
  const [form, setForm] = useState({
    nombre: "",
    correo: "",
    contrasena: "",
    cedula: "",
    rol: "USUARIO"
  });

  useEffect(() => {
    if (usuarioInicial) setForm(usuarioInicial);
  }, [usuarioInicial]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    onUsuarioGuardado(form);
    setForm({
      nombre: "",
      correo: "",
      contrasena: "",
      cedula: "",
      rol: "USUARIO"
    });
  };

  return (
    <form onSubmit={handleSubmit} className="form-modern">
      <h2>{usuarioInicial ? "Editar Usuario" : "Registrar Usuario"}</h2>
      <input name="nombre" placeholder="Nombre" value={form.nombre} onChange={handleChange} required />
      <input name="correo" placeholder="Correo" value={form.correo} onChange={handleChange} required />
      <input name="contrasena" placeholder="Contraseña" value={form.contrasena} onChange={handleChange} required type="password" />
      <input name="cedula" placeholder="Cédula" value={form.cedula} onChange={handleChange} required />
      <select name="rol" value={form.rol} onChange={handleChange}>
        <option value="USUARIO">Usuario</option>
        <option value="ADMINISTRADOR">Admin</option>
      </select>
      <button type="submit">{usuarioInicial ? "Guardar Cambios" : "Registrar"}</button>
    </form>
  );
}

export default UsuarioForm;