import axios from "axios";

// Cambia el host si tu backend está en otra IP o puerto
const BASE_URL = "http://localhost:8080";

// Obtener vehículos actualmente en parqueadero
export const getVehiculosEnParqueadero = async () => {
  const res = await axios.get(`${BASE_URL}/registro/en-parqueadero`);
  return res.data;
};

// Obtener celdas disponibles
export const getCeldasDisponibles = async () => {
  const res = await axios.get(`${BASE_URL}/celda/disponibles`);
  return res.data;
};

// Obtener TODOS los registros de entradas/salidas
export const getRegistros = async () => {
  const res = await axios.get(`${BASE_URL}/registro`);
  return res.data;
};
