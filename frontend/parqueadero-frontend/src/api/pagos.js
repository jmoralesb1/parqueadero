import axios from "axios";

const API_URL = "http://localhost:8080/pagos";

export const getPagos = async () => {
  const response = await axios.get(`${API_URL}/listar`);
  return response.data;
};

export const registrarPagoPorPlaca = async (
  placa, monto, metodoPago, cedula, referencia, observaciones, estado
) => {
  const res = await axios.post(
    `${API_URL}/registrar-por-placa`,
    null,
    { params: { placa, monto, metodoPago, cedula, referencia, observaciones, estado } }
  );
  return res.data;
};

export const editarPago = async (id, data) => {
  const res = await axios.put(`${API_URL}/editar/${id}`, null, { params: data });
  return res.data;
};

export const anularPago = async (id, observaciones) => {
  const res = await axios.post(`${API_URL}/anular/${id}`, null, { params: { observaciones } });
  return res.data;
};
