import { useEffect, useState } from "react";
import { getUsuarios } from "../api/usuarios";

function UsuarioList({ onEditar }) {
  const [usuarios, setUsuarios] = useState([]);

  useEffect(() => {
    getUsuarios().then(setUsuarios);
  }, []);

  return (
    <div>
      <h2>Usuarios Registrados</h2>
      <div className="table-responsive">
        <table className="app-table">
          <thead>
            <tr>
              <th>ID</th>
              <th>Nombre</th>
              <th>Correo</th>
              <th>Cédula</th>
              <th>Rol</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {usuarios.map((u) => (
              <tr key={u.id}>
                <td>{u.id}</td>
                <td>{u.nombre}</td>
                <td>{u.correo}</td>
                <td>{u.cedula}</td>
                <td>{u.rol}</td>
                <td>
                  <button onClick={() => onEditar(u)}>Editar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default UsuarioList;
