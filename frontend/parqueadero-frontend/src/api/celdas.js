import axios from "axios";

const API_URL = "http://localhost:8080/celdas";

export const getCeldas = async () => {
  const response = await axios.get(API_URL);
  return response.data;
};

export const registrarCelda = async (celda) => {
  const response = await axios.post(API_URL, celda);
  return response.data;
};