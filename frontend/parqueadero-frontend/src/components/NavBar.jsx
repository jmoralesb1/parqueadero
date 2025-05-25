import { useLocation, Link } from "react-router-dom";
import "./NavBar.css"; // Recuerda crear este archivo, abajo te lo dejo

function NavBar() {
  const location = useLocation();
  // Ocultar menú en Dashboard y Home
  if (location.pathname === "/dashboard" || location.pathname === "/") return null;

  // Páginas y rutas principales
  const links = [
    { path: "/vehiculos", label: "Vehículos" },
    { path: "/celdas", label: "Celdas" },
    { path: "/registros", label: "Registros" },
    { path: "/usuarios", label: "Usuarios" },
    { path: "/dashboard", label: "Dashboard" },
  ];

  return (
    <nav className="navbar">
      <div className="navbar-container">
        {links.map((item) => (
          <Link
            key={item.path}
            to={item.path}
            className={`navbar-link${location.pathname === item.path ? " active" : ""}${
              item.path === "/dashboard" ? " navbar-dashboard" : ""
            }`}
          >
            {item.label}
          </Link>
        ))}
      </div>
    </nav>
  );
}

export default NavBar;





