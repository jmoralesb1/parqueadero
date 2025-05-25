import { useState } from "react";
import UsuarioList from "../components/UsuarioList";
import UsuarioForm from "../components/UsuarioForm";
import { registrarUsuario, actualizarUsuario } from "../api/usuarios";

function UsuariosPage() {
  const [refresh, setRefresh] = useState(false);
  const [usuarioEditar, setUsuarioEditar] = useState(null);

  const handleUsuarioGuardado = async (usuario) => {
    if (usuario.id) {
      await actualizarUsuario(usuario.id, usuario);
    } else {
      await registrarUsuario(usuario);
    }
    setUsuarioEditar(null);
    setRefresh(!refresh);
  };

  const handleEditar = (usuario) => {
    setUsuarioEditar(usuario);
  };

  return (
    <div className="page-container">
      <h1 className="page-title">Gestión de Usuarios</h1>
      <div className="page-content">
        <UsuarioForm onUsuarioGuardado={handleUsuarioGuardado} usuarioInicial={usuarioEditar} />
        <UsuarioList key={refresh} onEditar={handleEditar} />
      </div>
    </div>
  );
}

export default UsuariosPage;


