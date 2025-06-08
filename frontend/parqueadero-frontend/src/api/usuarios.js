import axios from "axios";

const API_URL = "http://localhost:8080/usuarios";

export const getUsuarios = async () => {
  const res = await axios.get(`${API_URL}/listar`);
  return res.data;
};

export const registrarUsuario = async (usuario) => {
  const response = await axios.post(API_URL, usuario);
  return response.data;
};

export const actualizarUsuario = async (id, usuario) => {
  const response = await axios.put(`${API_URL}/${id}`, usuario);
  return response.data;
};

export const getUsuarioPorPlaca = async (placa) => {
  const res = await axios.get(`${API_URL}/por-placa`, { params: { placa } });
  return res.data;
};