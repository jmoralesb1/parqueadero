import axios from "axios";

const API_URL = "http://localhost:8080/registro";

export const getRegistros = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

export const registrarEntradaPorPlaca = async (placa) => {
  const response = await axios.post(`${API_URL}/entrada/placa/${placa}`);
  return response.data;
};

export const registrarSalidaPorPlaca = async (placa) => {
  const response = await axios.put(`${API_URL}/salida/placa/${placa}`);
  return response.data;
};

export const getHistorialPorPlaca = async (placa) => {
  const response = await axios.get(`${API_URL}/placa/${placa}`);
  return response.data; // lista de registros
};
